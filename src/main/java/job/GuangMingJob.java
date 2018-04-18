package job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import page.GuangMing;
import page.WeixinPage;
import pipeline.PostData;
import pipeline.PostGuangMing;
import us.codecraft.webmagic.Spider;
import utils.Url;

/**
 * Created by liu on 2018/4/17.
 */
public class GuangMingJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始执行光明日报抓取");
        Spider.create(new GuangMing()).addUrl(Url.spider_guangming).addPipeline(new PostGuangMing()).run();
    }
}
