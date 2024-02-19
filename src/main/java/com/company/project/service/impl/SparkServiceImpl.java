package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.service.SparkService;
import com.company.project.vo.resp.SparkSessionPageVO;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service("sparkService")
public class SparkServiceImpl implements SparkService {

    private static String LIVY_URL = "http://192.168.1.111:8998";
    @Override
    public SparkSessionPageVO getSessionList() {
        String getSessionListUrl = LIVY_URL+"/sessions";
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(new Request.Builder().url(getSessionListUrl).build()).execute();
            SparkSessionPageVO pageVO = JSON.parseObject(response.body().string(), SparkSessionPageVO.class);
            return pageVO;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String submitSparkCodeAsync(String sessionCode, String code) {
        return null;
    }

    //同步发送 代码并返回结果
    @Override
    public String submitSparkSync(String sessionCode, String code) {
        String resultStr ;
        String submitSQLUrl = LIVY_URL+"/sessions/"+sessionCode+"/statements";
        MediaType mediaType = MediaType.parse("application/json");
        Map<String,String> map = new HashMap<>();
        map.put("code", code );
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
        System.out.println(resultStr);
        return resultStr;
    }

    @Override
    public String getSubmittedSparkCodeResult(String sessionCode, String returnCode) {
        return null;
    }
}