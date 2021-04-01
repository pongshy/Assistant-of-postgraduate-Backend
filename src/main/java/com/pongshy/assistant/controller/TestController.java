package com.pongshy.assistant.controller;

import com.pongshy.assistant.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: TestController
 * @Description: 测试接口
 * @Author: pongshy
 * @Date: 2021/4/1-16:02
 * @Version: V1.0
 **/
@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {


    @Resource
    private TestService testService;

    @GetMapping("/insert")
    public ResponseEntity<?> insertTest() {
        return testService.insertIntoMongodbTest();
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllRecords() {
        return testService.getAllRecords();
    }
}
