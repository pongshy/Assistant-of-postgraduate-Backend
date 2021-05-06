package com.pongshy.assistant.service;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.WechatInforRequest;

/**
 * @ClassName: LoginService
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/29 10:26
 **/
public interface LoginService {


    Result loginWX(String code);

    /*
     * @Description: 保存用户的微信名和头像
     * @Method: [wechatInforRequest]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/6 20:21
     */
    Result updateUserInfor(WechatInforRequest wechatInforRequest);
}
