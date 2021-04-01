package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.model.mongodb.Book;
import com.pongshy.assistant.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TestServiceImpl
 * @Description: 测试接口Service层具体实现
 * @Author: pongshy
 * @Date: 2021/4/1-16:01
 * @Version: V1.0
 **/
@Service
@Slf4j
public class TestServiceImpl implements TestService {


    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public ResponseEntity<?> insertIntoMongodbTest() {
        Book book = new Book();

//        book.setId("abcd");
        book.setName("巴黎圣母院");
        book.setPublish("人民出版社");
        book.setPrice(100);
        book.setInfo("sdfasdfsda");
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());

        mongoTemplate.save(book);
        return ResponseEntity.ok("添加成功");
    }

    @Override
    public ResponseEntity<?> getAllRecords() {
        return ResponseEntity.ok(mongoTemplate.findAll(Book.class));
    }


}
