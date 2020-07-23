/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.crawler;

import java.io.BufferedReader;
import javax.servlet.ServletContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import khanhbv.dto.ProductTestDTO;
import khanhbv.utils.StringConstant;
import khanhbv.utils.XMLHelper;
import khanhbv.utils.XMLUtils;
import org.w3c.dom.Document;

/**
 *
 * @author vankhanhbui
 */
public class QMProductDetailCrawler extends BaseCrawler {

    private String url;
    private String category;
    private static String beginSyntax = "<div class=\"col-sm-7 popup-gallery\">";
    private static String endSyntax = "<div id=\"tab-review\" class=\"tab-content\">";

    public QMProductDetailCrawler(ServletContext context, String category, String url) {
        super(context);
        this.category = category;
        this.url = url;
    }

    public ProductTestDTO getProductDetail() {
        BufferedReader reader = null;
        ProductTestDTO dto = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String document = XMLHelper.findHTMLToCrawl(reader, beginSyntax, endSyntax);
                XMLHelper.writeTestFileDocument(document);
                domParserProductDetails(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;

    }

    public ProductTestDTO domParserProductDetails(String docString) throws XPathExpressionException {
        docString = docString.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(docString);

        ProductTestDTO dto;

        if (doc != null) {
            dto = new ProductTestDTO();
            XPath xPath = XMLUtils.creatXPath();
            String exp = "//h2";
            String name = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

            exp = "//div[@itemprop=\"description\"]/p[not (b)"
                    + " and (contains(text(),'"
                    + StringConstant.POWER_STRING_V24
                    + "'))]";
            String power = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

            if (power == null) {
                exp = "//div[@itemprop=\"description\"]/p[not (b)"
                        + " and (contains(text(),'"
                        + StringConstant.POWER_STRING_V23
                        + "'))]";
                power = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);
            }

            System.out.println("name: " + name);
            System.out.println("power: " + power);
        }
        return null;
    }

}
