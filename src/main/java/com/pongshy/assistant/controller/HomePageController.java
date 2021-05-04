package com.pongshy.assistant.controller;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.SentenceRequest;
import com.pongshy.assistant.service.HomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: HomePageController
 * @Description: 首页控制器
 * @Author: pongshy
 * @Date: 2021/4/30 22:01
 **/
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "首页模块")
@RequestMapping("/home")
public class HomePageController {


    @Resource
    private HomePageService homePageService;

    /*
     * @Description: 签到接口
     * @Method: [openid]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 22:05
     */
    @ApiOperation(value = "签到接口")
    @GetMapping("/sign/{openid}")
    public Result sginIn(@PathVariable(value = "openid") String openid) {
        return homePageService.signInOneDay(openid);
    }

    /*
     * @Description: 获取是否今天已签到
     * @Method: [openid]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 22:17
     */
    @ApiOperation(value = "获取是否今天已签到")
    @GetMapping("/getsign/{openid}")
    public Result getSign(@PathVariable(value = "openid") String openid) {
        return homePageService.getSignRecord(openid);
    }

    /*
     * @Description: 增加心灵鸡汤
     * @Method: [sentenceRequest]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 23:24
     */
    @ApiOperation(value = "增加心灵鸡汤")
    @PostMapping("/addsen")
    public Result insertSentence(@RequestBody SentenceRequest sentenceRequest) {
        return homePageService.insertSentence(sentenceRequest.getSentence());
    }

    /*
     * @Description: 获取心灵鸡汤
     * @Method: []
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 23:23
     */
    @ApiOperation(value = "获取心灵鸡汤")
    @GetMapping("/getsen")
    public Result getSentence() {
        return homePageService.getSentence();
    }


    @ApiOperation(value = "选择植物")
    @GetMapping("/plant")
    public Result setPlant(@RequestParam(value = "plantId") Integer plantId,
                           @RequestParam(value = "openid") String openid) {
        return homePageService.choosePlant(plantId, openid);
    }

    @ApiOperation(value = "获取植物和当天任务完成百分比")
    @GetMapping("/getplant/{openid}")
    public Result getPlantAndPercent(@PathVariable(value = "openid") String openid) {
        return homePageService.getPlantAndPercent(openid);
    }


}
