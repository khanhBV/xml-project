/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.main;

import java.util.Map;
import khanhbv.crawler.DMLProductDetailCrawler;
import khanhbv.crawler.DienMayLinhBrandCrawler;
import khanhbv.crawler.DienMayLinhCategoriesCrawler;
import khanhbv.crawler.DienMayLinhProductLinkByCategoryCrawler;
import khanhbv.crawler.DMGRCategoryCrawler;
import khanhbv.crawler.DMGRProductDetailCrawler;
import khanhbv.crawler.DMGRProductLinkByCategoryCrawler;
import khanhbv.crawler.DMRCategoryCrawler;
import khanhbv.crawler.DMRProductDetailCrawler;
import khanhbv.crawler.DMRProductLinkByCategoryCrawler;
import khanhbv.crawler.QMCategoryCrawler;
import khanhbv.crawler.QMProductDetailCrawler;
import khanhbv.crawler.QMProductLinkByCategoryCrawler;
import khanhbv.utils.Helper;
import khanhbv.utils.StringConstant;

/**
 *
 * @author buivankhanh
 */
public class MainCrawler {

    public static void main(String[] args) {
//        Map<String, String> categoryMap;
//        DienMayLinhBrandCrawler brandCrawler = new DienMayLinhBrandCrawler(null);
//        brandCrawler.getCategories("https://dienmaylinh.vn/");
//        DienMayLinhCategoriesCrawler categoriesCrawler = new DienMayLinhCategoriesCrawler(null);
//        categoriesCrawler.getCategories("https://dienmaylinh.vn/");
//          DienMayLinhProductLinkByCategoryCrawler productLinkCrawler = new DienMayLinhProductLinkByCategoryCrawler(null, "https://dienmaylinh.vn/-tivi", "TIVI");
//          productLinkCrawler.getProductLink();
//        DMLProductDetailCrawler detailCrawler = new DMLProductDetailCrawler(null, "TIVI", "https://dienmaylinh.vn/may-lanh-casper-1-5hp-ec-12tl22");
//        detailCrawler.getProductDetail();
//        DMGRCategoryCrawler categoryCrawler = new DMGRCategoryCrawler(null);
//        categoryCrawler.getCategories("https://dienmaygiare.net/");
//        DMGRProductLinkByCategoryCrawler productCrawler = new DMGRProductLinkByCategoryCrawler(null, "https://dienmaygiare.net/tivi/", "TIVI");
//        productCrawler.getProductLink();
//        DMGRProductDetailCrawler detailCrawler = new DMGRProductDetailCrawler(null, "TIVI", "https://dienmaygiare.net/quat-dieu-hoa-daikiosan-dka-04000b/");
//        detailCrawler.getProductDetail();
//        DMRCategoryCrawler categoryCrawler = new DMRCategoryCrawler(null);
//        categoryMap = categoryCrawler.getCategories("https://dienmayre.vn/");
//        for (Map.Entry<String, String> entry : categoryMap.entrySet()) {
//            System.out.println(entry.getKey() + "   "  +entry.getValue());
//            DMRProductLinkByCategoryCrawler productCrawler = new DMRProductLinkByCategoryCrawler(null, entry.getKey(), entry.getValue());
//            productCrawler.getProductLink();
//        }

//            DMRProductDetailCrawler detailCrawler = new DMRProductDetailCrawler(null, "TIVI", "https://dienmayre.vn/smart-tivi-sharp-50-inch-2t-c50ae1x.html");
//            detailCrawler.getProductDetail();
//        String data = Helper.findPowerNumberInSring("Điện năng tiêu thụ: 23.750hp");
//        System.out.println("data: " + data);
//        float result = Helper.converPower("Công suất lạnh 25000BTU (2.5HP)", "25000");
//        System.out.println("result: " + result);
//        CrawlerDMLService dmlService = new CrawlerDMLService();
//        dmlService.crawlDML();
//        DMRCrawlerService dmrService = new DMRCrawlerService();
//        dmrService.crawlDMR();

//        CrawlerDMGRService dmgrService = new CrawlerDMGRService();
//        dmgrService.crawlDMGR();


//        QMCategoryCrawler categoryCrawler = new QMCategoryCrawler(null);
//        categoryCrawler.getCategories(StringConstant.QM_URL_DOMAIN_NAME); 

//        QMProductLinkByCategoryCrawler productLinkByCategoryCrawler = new QMProductLinkByCategoryCrawler(null, "https://quangminh.vn/do-gia-dung/may-phat-dien/", "Máy phát điện");
//        productLinkByCategoryCrawler.getProductLink();
        
//            QMProductDetailCrawler detailCrawler = new QMProductDetailCrawler(null, "May Phat Dien", "https://quangminh.vn/may-phat-dien-honda-3800et.html");
//            detailCrawler.getProductDetail();

        CrawlerQLMService QMCrawlerService = new CrawlerQLMService();
        QMCrawlerService.crawlCategory();
    }

}
