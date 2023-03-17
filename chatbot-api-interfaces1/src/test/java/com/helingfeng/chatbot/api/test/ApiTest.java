package com.helingfeng.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author hairless
 * @description 单元测试
 * @github https://github.com/helingfengroot
 */

public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/88885884424522/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie","zsxq_access_token=7321124D-C305-7E98-AEF6-D46C0EF2BD2A_D603DF115E0A12CB; abtest_env=product; zsxqsessionid=d9e1f3f09ea4b0367b1cea932c6e78f1");
        get.addHeader("Content-Type","application/json, text/plain, */*");

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }


    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/214811244882121/answer");
        post.addHeader("cookie","zsxq_access_token=7321124D-C305-7E98-AEF6-D46C0EF2BD2A_D603DF115E0A12CB; abtest_env=product; zsxqsessionid=d9e1f3f09ea4b0367b1cea932c6e78f1");
        post.addHeader("Content-Type","application/json, charset=utf-8");
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }


}
