package com.company.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.http.HttpRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestLivyByJava {
    public static void main(String[] args) {
//        createSession();
//        getSessionList();
        submitSQL();
    }


    private static String LIVY_URL = "http://192.168.1.111:8998";


    private static void getSessionList(){
        String getSessionListUrl = LIVY_URL+"/sessions";
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(new Request.Builder().url(getSessionListUrl).build()).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            System.out.println(jsonObject.get("from"));
            System.out.println(jsonObject.get("total"));
            System.out.println(jsonObject.get("sessions"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void createSession(){
        String createSession = LIVY_URL+"/sessions";
        MediaType mediaType = MediaType.parse("application/json");
        Map<String,String> map = new HashMap<>();
        map.put("kind","spark");
        RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(map));
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(new Request.Builder().url(createSession).post(requestBody).addHeader("Content-Type", "application/json").build()).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static String submitSQL(){
        String resultStr ;
        String submitSQLUrl = LIVY_URL+"/sessions/"+3+"/statements";
        MediaType mediaType = MediaType.parse("application/json");
        Map<String,String> map = new HashMap<>();
        map.put("code"," spark.sql(\"select * from call_1\").show ");
        RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(map));
        String taskId;
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(new Request.Builder().url(submitSQLUrl).post(requestBody).addHeader("Content-Type", "application/json").build()).execute();
            String responseStr = response.body().string();
            taskId = JSON.parseObject(responseStr).get("id").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("执行之后的任务id为："+ taskId);
        //获取 session对应下的task的状态和结果
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String getTaskResultUrl = submitSQLUrl+"/"+taskId;
        try {
            Response response = client.newCall(new Request.Builder().url(getTaskResultUrl).build()).execute();
            resultStr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return resultStr;
    }



}
