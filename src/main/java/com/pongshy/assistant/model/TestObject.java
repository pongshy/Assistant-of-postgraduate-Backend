package com.pongshy.assistant.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: test
 * @Description: 测试用例
 * @Author: pongshy
 * @Date: 2021/3/27 15:53
 **/
public class TestObject {


    public String id;

    public String name;

    public String parentId;

    public List<TestObject> children;

    public TestObject(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public void addChild(TestObject testObject) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(testObject);
    }
}
