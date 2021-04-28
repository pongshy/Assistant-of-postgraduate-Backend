package com.pongshy.assistant.controller;

import com.pongshy.assistant.tool.SignTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: CheckController
 * @Description: 微信开启消息推送验证
 * @Author: pongshy
 * @Date: 2021/4/28-15:05
 * @Version: V1.0
 **/
@RestController
@Slf4j
@CrossOrigin
public class CheckController {

    @Value("${wx.Token}")
    public String Token;


    @GetMapping
    public void checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if (SignTool.checkSignature(Token, signature, timestamp, nonce)) {
            log.info(echostr);
            out.print(echostr);
        }
        out.close();

    }
}
