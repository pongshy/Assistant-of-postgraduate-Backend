package com.pongshy.assistant.service;

import com.pongshy.assistant.model.request.BookUpdateRequest;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;

/**
 * @ClassName: TestService
 * @Description: 测试接口Service层接口类
 * @Author: pongshy
 * @Date: 2021/4/1-16:01
 * @Version: V1.0
 **/
public interface TestService {


    public ResponseEntity<?> insertIntoMongodbTest();

    public ResponseEntity<?> getAllRecords();

    public ResponseEntity<?> updateBook(BookUpdateRequest request);
}
