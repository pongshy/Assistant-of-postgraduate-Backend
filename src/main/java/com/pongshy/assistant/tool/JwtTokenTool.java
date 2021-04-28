package com.pongshy.assistant.tool;

import com.auth0.jwt.JWT;
import com.pongshy.assistant.model.MsgFeeling;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JwtTol
 * @Description: Jwt工具类
 * @Author: pongshy
 * @Date: 2021/4/28-15:52
 * @Version: V1.0
 **/
public class JwtTokenTool {



    public static String jwtData(String EncodingAESKey,
                                 String uid,
                                 MsgFeeling feel) {
        Map<String, Object> header = new HashMap<>();
        header.put("data", feel);
        header.put("uid", uid);
        return Jwts
                .builder()
                .setPayload(header.toString())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 24 * 5))
                .signWith(SignatureAlgorithm.HS256, EncodingAESKey)
                .compact();

    }
}
