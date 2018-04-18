package job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import page.WeixinPage;
import pipeline.PostData;
import us.codecraft.webmagic.Spider;
import utils.Url;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liu on 2018/4/17.
 */
public class TestJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行时间：" + sdf.format(d));
    }
}
