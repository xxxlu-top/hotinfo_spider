package pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by liu on 2018/4/10.
 */
public class HtmlData implements Pipeline {
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.get("body")!=null){
            String path = System.getProperty("user.dir")+ File.separator+"html_doc"+File.separator+resultItems.get("title")+".html";
            WriteStringToFile(path,resultItems.get("body").toString());
        }
    }

    public void WriteStringToFile(String filePath,String body) {
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(body);// 往文件里写入字符串
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
