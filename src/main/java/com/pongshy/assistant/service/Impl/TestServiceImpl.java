package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.model.mongodb.Book;
import com.pongshy.assistant.model.request.BookUpdateRequest;
import com.pongshy.assistant.model.response.Node;
import com.pongshy.assistant.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        book.setName("悲惨世界");
        book.setPublish("人民出版社");
        book.setPrice(200);
        book.setInfo("sdfasdfsda");
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());

        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node("1", "高等数学", 86.5));
        nodeList.add(new Node("1", "英语", 86.0));
        nodeList.add(new Node("1", "语文", 80.9));

        book.setNodes(nodeList);

        mongoTemplate.save(book);
        return ResponseEntity.ok("添加成功");
    }

    @Override
    public ResponseEntity<?> getAllRecords() {
        Query query = new Query()
                // 排序
                .with(Sort.by(Sort.Order.asc("_id")));
        List<Book> response = mongoTemplate.find(query, Book.class);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> updateBook(BookUpdateRequest request) {
        Query query = Query.query(Criteria.where("_id").is("abcd"));

        Update update = new Update();
        update.set("price", request.getPrice());
        update.set("info", request.getInfo());
        update.set("name", request.getName());

        mongoTemplate.updateMulti(query, update, Book.class);
        return ResponseEntity.ok("更新成功");
    }


}
