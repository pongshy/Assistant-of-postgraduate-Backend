package com.pongshy.assistant.model.response;

import lombok.Data;

/**
 * @ClassName: FeelResponse
 * @Description: 心情返回类
 * @Author: pongshy
 * @Date: 2021/5/2-15:23
 * @Version: V1.0
 **/
@Data
public class FeelResponse {


    private String id;

    private String words;

    private String imageUrl;

    private String openid;

    private Integer sentiment;

    private String sentence;

    private String createTime;

//    private Integer isSetup = 0;
}
