/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.crawler;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import khanhbv.utils.StringConstant;
import khanhbv.utils.XMLHelper;
import khanhbv.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author vankhanhbui
 */
public class DMRProductLinkByCategoryCrawler extends BaseCrawler {

    private String url;
    private String category;
    private static String beginSyntax = "<ul class=\"products\" id=\"products\">";
    private static String endSyntax = "<div class=\"scroll col-xs-12 col-sm-12 col-md-12 col-lg-12\"></div>";

    private List<String> productLinkList;

    public DMRProductLinkByCategoryCrawler(ServletContext context, String category, String url) {
        super(context);
        this.category = category;
        this.url = url;
    }

    public List<String> getProductLink() {
        BufferedReader reader = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String document = XMLHelper.findHTMLToCrawl(reader, beginSyntax, endSyntax);
//                XMLHelper.writeTestFileDocument(document);
                int maxPage = getLastPage(document);
                domParserProductLink(document);

                for (int i = 2; i <= maxPage; i++) {
                    String urlPaging = url + StringConstant.PAGE_SYNTAX_DMR + i + StringConstant.PAGE_LAST_SYNTAX_DMR;
                    getProductLinkInPage(urlPaging);
                }//end for maxPage
                for (int i = 0; i < productLinkList.size(); i++) {
                    getDetailProduct(productLinkList.get(i), category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productLinkList;
    }

    public int getLastPage(String docString) throws XPathExpressionException {
        docString = docString.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(docString);
        int lastPage = 1;
        if (doc != null) {
            String exp = "//li/a[contains(text(),'Trang cuối')]";
            XPath xPath = XMLUtils.creatXPath();
            Node pagingNode = (Node) xPath.evaluate(exp, doc, XPathConstants.NODE);
            

            if (pagingNode != null) {
                String urlMaxPage = pagingNode.getAttributes().getNamedItem("href").getNodeValue();
                int begin = urlMaxPage.indexOf(StringConstant.PAGE_SYNTAX_DMR);
                int end = urlMaxPage.indexOf(StringConstant.PAGE_LAST_SYNTAX_DMR);

                if (begin != -1 && end != -1) {
                    String maxPageStr = urlMaxPage.substring(begin, end);
                    begin = maxPageStr.indexOf("=");
                    if (begin != -1) {
                        if (begin + 1 < maxPageStr.length()) {
                            maxPageStr = maxPageStr.substring(begin + 1);
                            if (maxPageStr.matches("\\d+")) {
                                lastPage = Integer.parseInt(maxPageStr.trim());
                                System.out.println(lastPage);
                            }
                        }
                    }

                }

            }//end pagingNode
            else {
                exp = "//ul[@class='page-numbers']/li[last()-1]/a";
                String pageStr = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);
                if (!pageStr.isEmpty()) {
                    lastPage = Integer.parseInt(pageStr.trim());
                }
            }

        }//end if doc

        return lastPage;

    }

    public void getProductLinkInPage(String url) {
        BufferedReader reader = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String document = XMLHelper.findHTMLToCrawl(reader, beginSyntax, endSyntax);
                domParserProductLink(document);
            }

        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public void domParserProductLink(String docString) throws XPathExpressionException {
        docString = docString.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(docString);
        if (productLinkList == null) {
            productLinkList = new ArrayList<>();
        }
        if (doc != null) {
            XPath xPath = XMLUtils.creatXPath();
            String exp = "//h3/a";
            NodeList listLink = (NodeList) xPath.evaluate(exp, doc, XPathConstants.NODESET);

            if (listLink.getLength() > 0) {
                for (int i = 0; i < listLink.getLength(); i++) {
                    String link = listLink.item(i).getAttributes().getNamedItem("href").getNodeValue();
                    if (!link.isEmpty()) {
//                        System.out.println(StringConstant.DMR_URL_DOMAIN_NAME + link);
                        productLinkList.add(StringConstant.DMR_URL_DOMAIN_NAME + link);
                    }
                }
            }
        }
    }

    public void getDetailProduct(String url, String category) {
        DMRProductDetailCrawler detailCrawler = new DMRProductDetailCrawler(null, category, url);
        detailCrawler.getProductDetail();
    }

}
