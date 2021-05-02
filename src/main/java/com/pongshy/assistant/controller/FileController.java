package com.pongshy.assistant.controller;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ClassName: FileController
 * @Description: 文件控制层
 * @Author: pongshy
 * @Date: 2021/5/2-12:39
 * @Version: V1.0
 **/
@RestController
@CrossOrigin
@Api(tags = "文件模块")
@RequestMapping("/file")
public class FileController {


    @Resource
    private FileService fileService;

    @ApiOperation(value = "上传文件(包括图片)")
    @PostMapping("/upload")
    public Result uploadFile(@RequestBody MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
