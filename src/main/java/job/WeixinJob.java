package job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import page.WeixinPage;
import pipeline.PostData;
import us.codecraft.webmagic.Spider;
import utils.Url;

/**
 * Created by liu on 2018/4/17.
 */
public class WeixinJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始执行微信文章更新");
        Spider.create(new WeixinPage()).addUrl(Url.spider_weixin).addPipeline(new PostData()).run();
    }
}
