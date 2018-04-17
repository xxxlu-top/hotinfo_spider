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
    public static void main(String[] args) throws SchedulerException {
//        Spider.create(new GuangMing()).addUrl(Url.spider_guangming).addPipeline(new PostGuangMing()).run();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(WeixinJob.class)
                .withDescription("微信文章更新")
                .withIdentity("微信", "蜘蛛")
                .build();

        //创建触发器
//        long time=  System.currentTimeMillis() + 3*1000L; //3秒后启动任务
//        Date statTime = new Date(time);
        Trigger t = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("ramTrigger", "ramTriggerGroup")
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInHours(6)//每六小时执行一次
                        .repeatForever())
//                .startAt(statTime)  //默认当前时间启动
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")) //两秒执行一次
                .build();

        //注册任务和定时器
        scheduler.scheduleJob(jobDetail, t);

        //6.启动 调度器
        scheduler.start();
    }

    /**
     * 根据指定路径获取所有的文件
     *
     * @param path
     * @return
     * @throws Exception
     */
    public ArrayList<File> getFiles(String path) throws Exception {
//目标集合fileList
        ArrayList<File> fileList = new ArrayList<File>();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
//如果这个文件是目录，则进行递归搜索
                if (fileIndex.isDirectory()) {
                    getFiles(fileIndex.getPath());
                } else {
//如果文件是普通文件，则将文件句柄放入集合中
                    fileList.add(fileIndex);
                }
            }
        }
        return fileList;
    }

    private static String getHtmlString(String path) {
        String html = "";
        try {
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String s = "";
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s).append("\n");
                System.out.println(s);
            }
            bufferedReader.close();
            html = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }
}
