
import job.GuangMingJob;
import job.WeixinJob;
import org.quartz.*;
import page.GuangMing;
import pipeline.PostGuangMing;
import us.codecraft.webmagic.Spider;
import utils.QuartzManager;
import utils.TimeCofing;
import utils.Url;

/**
 * 爬虫控制主类
 * Created by liu on 2018/4/9.
 */
public class Main {
    public static String JOB_GROUP = "爬虫组";
    public static String WX_TRIGGER_GROUP = "爬虫触发器组";

    public static void main(String[] args) throws SchedulerException {
//        Spider.create(new GuangMing()).addUrl(Url.spider_guangming).addPipeline(new PostGuangMing()).run();
        QuartzManager.addJob("微信文章",JOB_GROUP,"微信触发器",WX_TRIGGER_GROUP, WeixinJob.class, TimeCofing.sHours2);
        QuartzManager.addJob("光明日报",JOB_GROUP,"光明日报触发器",WX_TRIGGER_GROUP, GuangMingJob.class, TimeCofing.sDay1_6);
    }
}
