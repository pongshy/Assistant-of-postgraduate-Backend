package com.pongshy.assistant.model.response;

import lombok.Data;

/**
 * @ClassName: Node
 * @Description: 节点
 * @Author: pongshy
 * @Date: 2021/4/3 21:54
 **/
@Data
public class Node {


    private String id;

    private String name;

    private Double grade;

    public Node(String id, String name, Double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}
