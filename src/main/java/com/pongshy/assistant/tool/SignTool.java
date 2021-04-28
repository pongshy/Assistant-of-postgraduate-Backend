package com.pongshy.assistant.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @ClassName: SignTool
 * @Description: 签名验证工具类
 * @Author: pongshy
 * @Date: 2021/4/28-15:16
 * @Version: V1.0
 **/
@Slf4j
public class SignTool {



    public static Boolean checkSignature(String Token ,String signature, String timestamp, String nonce) {
        log.info(Token);
        String[] arr = new String[]{Token, timestamp, nonce};
        Arrays.sort(arr);

        StringBuffer content = new StringBuffer();
        for (String tmp : arr) {
            content.append(tmp);
        }
        MessageDigest md = null;
        String sign = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            sign = StrTool.byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sign != null ? sign.equals(signature.toUpperCase()) : false;
    }
}
