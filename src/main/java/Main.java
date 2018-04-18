import job.GuangMingJob;
import job.TestJob;
import job.WeixinJob;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import page.GuangMing;
import page.WeixinPage;
import pipeline.HtmlData;
import pipeline.PostData;
import pipeline.PostGuangMing;
import us.codecraft.webmagic.Spider;
import utils.QuartzManager;
import utils.TimeCofing;
import utils.Url;
import utils.XmlWorkerHelperUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * 爬虫控制主类
 * Created by liu on 2018/4/9.
 */
public class Main {
    public static String JOB_GROUP = "爬虫组";
    public static String WX_TRIGGER_GROUP = "爬虫触发器组";

    public static void main(String[] args) throws SchedulerException {
        QuartzManager.addJob("微信文章",JOB_GROUP,"微信触发器",WX_TRIGGER_GROUP,WeixinJob.class, TimeCofing.sHours2);
        QuartzManager.addJob("光明日报",JOB_GROUP,"光明日报触发器",WX_TRIGGER_GROUP,GuangMingJob.class, TimeCofing.sDay1_6);
    }
}
