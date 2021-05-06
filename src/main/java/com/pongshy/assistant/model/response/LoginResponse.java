package com.pongshy.assistant.model.response;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: LoginResponse
 * @Description: 登录返回类
 * @Author: pongshy
 * @Date: 2021/5/6 15:15
 **/
@Data
public class LoginResponse {


    @NotEmpty
    private String session_key;

    @NotEmpty
    private String openid;
}
