package pipeline;

import com.alibaba.fastjson.JSON;
import model.NewsArticle;
import model.WeixinArticle;
import okhttp3.*;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import utils.Url;

import java.io.IOException;
import java.util.List;

/**
 * Created by liu on 2018/4/15.
 */
public class PostGuangMing implements Pipeline {
    private final OkHttpClient okHttpClient;

    public PostGuangMing() {
        okHttpClient = new OkHttpClient();
    }
    public void process(ResultItems resultItems, Task task) {
        //构建请求
        if (resultItems.get("news")!=null){
            NewsArticle article = resultItems.get("news");
            String jsonString = JSON.toJSONString(article);
            System.out.println(jsonString);
            FormBody.Builder body = new FormBody.Builder();
            body.add("json",jsonString);
            Request request = new Request.Builder()
                    .url(Url.guangming_post)
                    .post(body.build())
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                public void onFailure(Call call, IOException e) {

                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (response.body().string().contains("成功")) {
                        System.out.println("入库成功");
                    }else {
                        System.out.println("入库失败");
                    }
                }
            });
        }
    }
}
