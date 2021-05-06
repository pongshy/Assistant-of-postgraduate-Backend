package com.pongshy.assistant.model.response;

import lombok.Data;

/**
 * @ClassName: PersonalResponse
 * @Description: 个人主页返回类
 * @Author: pongshy
 * @Date: 2021/5/6-12:33
 * @Version: V1.0
 **/
@Data
public class PersonalResponse {


    private String name;

    private String imageUrl;

    private Integer taskNum = 0;

    private Integer finishedTaskNum = 0;

    private Integer signDays = 0;

    private Double percent = 0.0;

    private Integer isLogin = 0;

}
