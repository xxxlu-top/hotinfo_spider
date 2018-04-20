package page;

import model.NewsArticle;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 光明日报爬虫
 * Created by liu on 2018/4/10.
 */
public class GuangMing implements PageProcessor {
    private Site mSite = Site.me()
            .setRetryTimes(3)
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

    public void process(Page page) {
        if (page.getUrl().toString().contains("paperindex.htm")){
            String string = page.getHtml().xpath("//meta[4]").toString().replace("<meta HTTP-EQUIV=\"REFRESH\" CONTENT=\"0; URL=",
                    "http://epaper.gmw.cn/gmrb/").replace("\">","");
            page.addTargetRequest(string);
        }

        if (page.getUrl().toString().contains("nw.")){
            //新闻页
            NewsArticle article = new NewsArticle();
            Selectable body = page.getHtml().xpath("//*[@class=\"list_t\"]");
            String content = body.toString();
            //补全图片地址
            content = content.replace("../../..","http://epaper.gmw.cn/gmrb");
            article.setContent(content);
            article.setTitle(body.xpath("//div/h1/text()").toString());
            article.setUrl(page.getUrl().toString());
            article.setSource("光明日报");
            article.setWriter(body.xpath("//div/div/b/text()").toString());

            System.out.println(content);
            page.putField("news",article);
        }else if (page.getUrl().toString().contains("nbs.")){
            //列表页
            //将下个版面链接加入队列
            List<String> all = page.getHtml().xpath("//*[@id=\"pageLink\"]/@href").all();
            page.addTargetRequests(all);
            //将新闻页面加入队列
            List<String> pageurl = page.getHtml().xpath("//*[@id=\"titleList\"]").links().all();
            page.addTargetRequests(pageurl);
        }else {
            page.setSkip(true);
        }
    }

    public Site getSite() {
        return mSite;
    }
}
