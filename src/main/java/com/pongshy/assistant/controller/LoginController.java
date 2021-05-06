package com.pongshy.assistant.controller;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.WechatInforRequest;
import com.pongshy.assistant.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: LoginController
 * @Description: 登录模块
 * @Author: pongshy
 * @Date: 2021/4/29 10:25
 **/
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "登录模块")
public class LoginController {


    @Resource
    private LoginService loginService;

    @ApiOperation(value = "登录获取OpenId")
    @GetMapping("/login/{code}")
    public Result login(@PathVariable("code") String code) {
        return loginService.loginWX(code);
    }


    @ApiOperation(value = "保存微信名和微信头像")
    @PostMapping("/update")
    public Result updateUserInfor(@RequestBody @Validated WechatInforRequest wechatInforRequest) {
        return loginService.updateUserInfor(wechatInforRequest);
    }

}
