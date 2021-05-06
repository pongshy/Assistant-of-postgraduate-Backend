package com.pongshy.assistant.controller;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.DayRequest;
import com.pongshy.assistant.model.response.PersonalResponse;
import com.pongshy.assistant.service.PersonalService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: PersonalController
 * @Description: 个人主页控制层
 * @Author: pongshy
 * @Date: 2021/5/6-12:16
 * @Version: V1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/personal")
@Api(tags = "个人主页模块")
public class PersonalController {


    @Resource
    private PersonalService personalService;

    @ApiOperation(value = "个人主页加载")
    @GetMapping("/get/{openid}")
    @ApiImplicitParam(name = "openid", value = "用户openid", dataType = "String", required = true)
    @ApiResponse(code = 200, message = "OK", response = PersonalResponse.class)
    public Result getPersonalPage(@PathVariable(value = "openid") String openid) {
        return personalService.getPersonalInformation(openid);
    }

    @ApiOperation(value = "设置倒数日")
    @PostMapping("/setday")
    public Result setCountdownDay(@RequestBody DayRequest dayRequest) {
        return personalService.saveCountdownDay(dayRequest);
    }

    @ApiOperation(value = "修改倒数日和标题")
    @PostMapping("/modifyday")
    public Result modifyDayAndTitle(@RequestBody DayRequest dayRequest) {
        return personalService.modifyDayAndTitle(dayRequest);
    }







}
