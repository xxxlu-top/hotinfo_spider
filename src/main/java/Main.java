import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

/**
 * 爬虫控制主类
 * Created by liu on 2018/4/9.
 */
public class Main {
    public static void main(String[] args) {
//        Spider.create(new WeixinPage()).addUrl(Url.spider_weixin).addPipeline(new PostData()).run();

        Spider.create(new GuangMing()).addUrl(Url.spider_guangming).addPipeline(new PostGuangMing()).run();

//        String html1 = getHtmlString("D:\\url\\doc\\超声-涨知识：超声综合筛查有助于预测死胎-丁香园最新文章.html");
//        String html2 = getHtmlString("D:\\url\\doc\\胃轻瘫的发病机制-医脉通资讯.html");
//
//
//        XmlWorkerHelperUtil.htmlToPDF(html1+html2,"D:\\url\\ceshi.pdf");
    }

    /**
     * 根据指定路径获取所有的文件
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
