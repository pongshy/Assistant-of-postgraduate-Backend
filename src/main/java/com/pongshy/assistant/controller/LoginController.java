package com.pongshy.assistant.controller;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
