package com.pongshy.assistant.config;

import com.pongshy.assistant.tool.ApiTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AccessTokenGetFromStarting
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/29 21:02
 **/
@Slf4j
@Component
public class AccessTokenGetFromStarting implements CommandLineRunner {


    @Value("${baidu.ApiKey}")
    public String baiduApiKey;

    @Value("${baidu.SecretKey}")
    public String baiduSecretKey;

    /*
     * @Description: 启动时获取access_token
     * @Method: [args]
     * @Return: void
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/29 21:05
     */
    @Override
    public void run(String... args) throws Exception {
        String access_token = ApiTool.getBaiduAccessToken(baiduApiKey, baiduSecretKey);
        log.info("access_token已更新");
        log.info("access_token: " + access_token);
    }
}
