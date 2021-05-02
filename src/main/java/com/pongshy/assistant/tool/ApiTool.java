package com.pongshy.assistant.tool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import javafx.beans.binding.StringExpression;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @ClassName: ApiTool
 * @Description: 微信小程序API
 * @Author: pongshy
 * @Date: 2021/4/28-14:57
 * @Version: V1.0
 **/
@Slf4j
public class ApiTool {


    @Value("${wx.EncodingAESKey}")
    public static String EncodingAESKey;

    @Value("${wx.Token}")
    public static String Token;

    // 需要一个月更新一次
    public static String access_token;

    /*
     * @Description: 获取后端接口调用凭据(access_token)
     * @Author: pongshy
     * @Date: 2020/7/16
     **/
    public static String getAccessToken(String AppId, String secret) throws AllException, IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential" +
                "&appid=" + AppId +
                "&secret=" + secret;
        log.info(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //请求
        HttpGet httpGet = new HttpGet(url);
        //Get请求内容
        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(httpEntity));
        log.info(jsonObject.toString());
        String errcode = jsonObject.getString("errcode");
        log.info(errcode);
        if (StringUtils.isEmpty(errcode)) {
            access_token = jsonObject.getString("access_token");

            return access_token;
        } else if (errcode.equals("-1")) {
            throw new AllException(EmAllException.INTERNAL_ERROR, "系统繁忙，此时请开发者稍候再试");
        } else if (errcode.equals("40001")) {
            throw new AllException(EmAllException.BAD_REQUEST, "AppSecret 错误或者 AppSecret 不属于这个小程序，请开发者确认 AppSecret 的正确性");
        } else if (errcode.equals("40002")) {
            throw new AllException(EmAllException.INTERNAL_ERROR, "请确保 grant_type 字段值为 client_credential");
        } else if (errcode.equals("40013")) {
            throw new AllException(EmAllException.BAD_REQUEST, "不合法的 AppID，请开发者检查 AppID 的正确性，避免异常字符，注意大小写");
        } else if (errcode.equals("40125")) {
            throw new AllException(EmAllException.BAD_REQUEST, "invalid appsecret");
        } else {
            throw new AllException(EmAllException.BAD_REQUEST, "文档中未标明错误");
        }
    }


    /*
     * @Description: 获取百度AccessToken
     * @Method: [apiKey, secretKey]
     * @Return: java.lang.String
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/29 19:16
     */
    public static String getBaiduAccessToken(String apiKey, String secretKey) throws AllException, IOException {
        String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials" +
                "&client_id=" + apiKey +
                "&client_secret=" + secretKey;
        log.info(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 请求
        HttpGet httpGet = new HttpGet(url);
        // Get请求内容
        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(httpEntity));
        log.info(jsonObject.toString());
        String errcode = jsonObject.getString("error");

        if (StringUtils.isEmpty(errcode)) {
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } else if (errcode.equals("invalid_client")) {
            throw new AllException(EmAllException.BAD_REQUEST, "API Key不正确");
        } else if (errcode.equals("invalid_client")) {
            throw new AllException(EmAllException.BAD_REQUEST, "Secret Key不正确");
        } else {
            throw new AllException(EmAllException.BAD_REQUEST, "文档中未标明错误");
        }
    }

    /*
     * @Description: 获取情感分析结果
     * @Param: [access_token, words]
     * @return: java.lang.String
     * @Author: pongshy
     * @Date: 2021/4/30
     * @Version: V1.0
     **/
    public static String getFeeling(String access_token, String words) throws IOException {
        String url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/sentiment_classify?charset=UTF-8" +
                "&access_token=" + access_token;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text", words);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json; charset=UTF-8");
        httpPost.setEntity(stringEntity);

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(httpEntity));
        log.info(json.toString());

        JSONArray items = json.getJSONArray("items");
        String sentiment = items.getJSONObject(0).getString("sentiment");
        // 返回心情: 0:负向，1:中性，2:正向
        return sentiment;
    }


}
