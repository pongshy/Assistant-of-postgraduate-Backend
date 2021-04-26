package com.pongshy.assistant.model.request;

import lombok.Data;

/**
 * @ClassName: BookUpdateRequest
 * @Description: 书籍的更新
 * @Author: pongshy
 * @Date: 2021/4/3 21:08
 **/
@Data
public class BookUpdateRequest {


    private String name;

    private Double price;

    private String info;
}
