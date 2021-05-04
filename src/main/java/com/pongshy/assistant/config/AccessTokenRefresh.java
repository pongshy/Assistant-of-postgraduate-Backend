package com.pongshy.assistant.config;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.tool.ApiTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

/**
 * @ClassName: AccessTokenRefresh
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/29 20:54
 **/
@Configuration
@EnableScheduling
@Slf4j
public class AccessTokenRefresh {


    @Value("${baidu.ApiKey}")
    public String baiduApiKey;

    @Value("${baidu.SecretKey}")
    public String baiduSecretKey;

    /*
     * @Description: 每个月15号1:00自动更新access_token
     * @Method: []
     * @Return: void
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/29 21:05
     */
    @Scheduled(cron = "0 0 1 15 * ?")
    public void refreshAccessToken() throws IOException, AllException {
        String access_token = ApiTool.getBaiduAccessToken(baiduApiKey, baiduSecretKey);
        ApiTool.access_token = access_token;
        log.info("access_token已更新");
        log.info("access_token: " + access_token);
    }

}
