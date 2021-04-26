package com.pongshy.assistant.model.response;

import com.pongshy.assistant.model.myEnum.Color;
import lombok.Data;

/**
 * @ClassName: Priority
 * @Description: 优先级
 * @Author: pongshy
 * @Date: 2021/4/26 20:25
 **/
@Data
public class Priority {


    private String color;

    private Integer imp;

    public Priority(Integer imp) {
        this.color = Color.getColor(imp);
        this.imp = imp;
    }
}
