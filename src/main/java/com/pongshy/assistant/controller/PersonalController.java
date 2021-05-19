package com.pongshy.assistant.controller;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.DayRequest;
import com.pongshy.assistant.model.request.DiaryHistoryRequest;
import com.pongshy.assistant.model.request.PlantHistoryRequest;
import com.pongshy.assistant.model.response.PersonalResponse;
import com.pongshy.assistant.service.PersonalService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
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
@Slf4j
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
    public Result setCountdownDay(@RequestBody @Validated DayRequest dayRequest) {
        return personalService.saveCountdownDay(dayRequest);
    }

    @ApiOperation(value = "修改倒数日和标题")
    @PostMapping("/modifyday")
    public Result modifyDayAndTitle(@RequestBody @Validated DayRequest dayRequest) {
        return personalService.modifyDayAndTitle(dayRequest);
    }

    @ApiOperation(value = "根据日期返回植物和任务完成百分比")
    @PostMapping("/planthis")
    public Result getHistoryPlant(@RequestBody @Validated PlantHistoryRequest plantHistoryRequest) {
        return personalService.getHistoryPlant(plantHistoryRequest);
    }

    @ApiOperation(value = "根据日期返回心情日记")
    @PostMapping("/diaryhis")
    public Result getHistoryDiary(@RequestBody @Validated DiaryHistoryRequest diaryHistoryRequest) {
        log.info("Time: " + diaryHistoryRequest.getTime());
        return personalService.getHistoryDiary(diaryHistoryRequest);
    }







}
