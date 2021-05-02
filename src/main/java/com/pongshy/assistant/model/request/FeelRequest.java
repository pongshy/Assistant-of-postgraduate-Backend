package com.pongshy.assistant.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: FeelRequest
 * @Description: 心情接受类
 * @Author: pongshy
 * @Date: 2021/5/2-12:13
 * @Version: V1.0
 **/
@Data
public class FeelRequest {


    private String words;

    private String imageUrl;

    @NotEmpty
    private String openid;
}
