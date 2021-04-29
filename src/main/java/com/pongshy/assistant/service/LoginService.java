package com.pongshy.assistant.service;

import com.pongshy.assistant.model.Result;

/**
 * @ClassName: LoginService
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/29 10:26
 **/
public interface LoginService {


    Result loginWX(String code);
}
