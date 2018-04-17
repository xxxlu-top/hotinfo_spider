package page;

import model.WeixinArticle;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2018/4/9.
 */
public class WeixinPage implements PageProcessor{
    private Site mSite = Site.me()
            .setRetryTimes(3)
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

    public void process(Page page) {
        //找出抓取区域
        Selectable body = page.getHtml().xpath("//*[@class=\"news-box\"]");
        if (body!=null){
            //获取文字部分区域
            List<Selectable> nodes = body.xpath("//ul/li").nodes();
            List<WeixinArticle> articleList = new ArrayList<WeixinArticle>();
            for (Selectable node : nodes) {
                WeixinArticle article = new WeixinArticle();
                //标题
                article.setTitle(node.xpath("//*[@class=\"txt-box\"]/h3/a/text()").toString());
                //地址
                article.setUrl(node.xpath("//*[@class=\"txt-box\"]/h3/a/@href").toString());
                //简介
                article.setContent(node.xpath("//*[@class=\"txt-box\"]/p/text()").toString());
                //作者
                article.setWriter(node.xpath("//*[@class=\"txt-box\"]/div/a/text()").toString());
                //时间
                article.setTime(node.xpath("//*[@class=\"txt-box\"]/div/span/@t").toString());
                //图片
                article.setImg(node.xpath("//*[@class=\"img-box\"]/a/img/@src").toString());
                System.out.println("抓取文章："+node.xpath("//*[@class=\"txt-box\"]/h3/a/text()").toString());
                articleList.add(article);
            }
            page.putField("weixin",articleList);
        }
    }

    public Site getSite() {
        return mSite;
    }
}
