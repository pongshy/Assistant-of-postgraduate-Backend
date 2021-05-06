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

    private Integer taskNum;

    private Integer finishedTaskNum;

    private Integer signDays;

    private Double percent;

    private Integer isLogin;

}
