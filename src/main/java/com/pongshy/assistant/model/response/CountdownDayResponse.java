package com.pongshy.assistant.model.response;

import lombok.Data;

/**
 * @ClassName: CountdownDayResponse
 * @Description: 倒数日和标题返回类
 * @Author: pongshy
 * @Date: 2021/5/6-13:30
 * @Version: V1.0
 **/
@Data
public class CountdownDayResponse {


    private String title;

    private String endDay;

    private Integer isSetup = 0;
}
