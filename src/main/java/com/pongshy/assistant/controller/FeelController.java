package com.pongshy.assistant.controller;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.FeelModifyRequest;
import com.pongshy.assistant.model.request.FeelRequest;
import com.pongshy.assistant.service.FeelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: FeelController
 * @Description: 心情模块
 * @Author: pongshy
 * @Date: 2021/5/2-12:11
 * @Version: V1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/feel")
@Api(tags = "心情模块")
public class FeelController {


    @Resource
    private FeelService feelService;

    @ApiOperation(value = "保存用户当天的图片和心情句子")
    @PostMapping("/commit")
    public Result commitFeelAndWords(@RequestBody FeelRequest feelRequest) {
        return feelService.commitFeelAndWords(feelRequest);
    }

    @ApiOperation(value = "获取当天用户的图片和心情句子，以及情感分析后的心情状态与鼓励的话")
    @GetMapping("/get/{openid}")
    public Result getImageAndWords(@PathVariable(value = "openid") String openid) {
        return feelService.getImageAndWord(openid);
    }

    @ApiOperation(value = "修改用户图片和句子")
    @PostMapping("/modify")
    public Result modifyFeeling(@RequestBody FeelModifyRequest feelRequest) {
        return feelService.modifyFeel(feelRequest);
    }

    @ApiOperation(value = "删除用户图片和句子")
    @GetMapping("/delete/{id}")
    public Result deleteImageAndWords(@PathVariable(value = "id") String id) {
        return feelService.deleteFeel(id);
    }


}
