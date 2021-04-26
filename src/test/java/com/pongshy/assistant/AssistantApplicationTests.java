package com.pongshy.assistant;

import com.pongshy.assistant.model.TestObject;
import com.pongshy.assistant.tool.TreeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AssistantApplicationTests {

    @Test
    void contextLoads() {

        List<TestObject> testList = new ArrayList<>();
        testList.add(new TestObject("1","上海","0"));
        testList.add(new TestObject("2","北京","0"));
        testList.add(new TestObject("3","河南","0"));
        testList.add(new TestObject("31","郑州","3"));
        testList.add(new TestObject("32","洛阳","3"));
        testList.add(new TestObject("321","洛龙","32"));
        testList.add(new TestObject("11","松江","1"));
        testList.add(new TestObject("111","泗泾","11"));

        List<TestObject> list = TreeUtils.buildTree(testList);
        System.out.println(list);

    }

}
