package com.pongshy.assistant;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.model.MsgFeeling;
import com.pongshy.assistant.model.TestObject;
import com.pongshy.assistant.tool.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AssistantApplicationTests {


    @Value("${wx.Token}")
    public String Token;

    @Value("${wx.EncodingAESKey}")
    public String EncodingAESKey;

    @Value("${wx.AppID}")
    public String AppID;

    @Value("${wx.AppSecret}")
    public String AppSecret;

    @Test
    void contextLoads() throws IOException, AllException {

//        List<TestObject> testList = new ArrayList<>();
//        testList.add(new TestObject("1","上海","0"));
//        testList.add(new TestObject("2","北京","0"));
//        testList.add(new TestObject("3","河南","0"));
//        testList.add(new TestObject("31","郑州","3"));
//        testList.add(new TestObject("32","洛阳","3"));
//        testList.add(new TestObject("321","洛龙","32"));
//        testList.add(new TestObject("11","松江","1"));
//        testList.add(new TestObject("111","泗泾","11"));
//
//        List<TestObject> list = TreeUtils.buildTree(testList);
//        System.out.println(list);
        System.out.println("access_token: " + ApiTool.getAccessToken(AppID, AppSecret));
        MsgFeeling msgFeeling = new MsgFeeling("今天天气真好!");
        System.out.println(JwtTokenTool.jwtData(EncodingAESKey, "123456", msgFeeling));
    }

}
