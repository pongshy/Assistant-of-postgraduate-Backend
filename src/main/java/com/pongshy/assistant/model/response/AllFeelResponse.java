package com.pongshy.assistant.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AllFeelResponse
 * @Description: 当天所有心情返回类
 * @Author: pongshy
 * @Date: 2021/5/17 20:09
 **/
@Data
public class AllFeelResponse {


    private List<FeelResponse> feelList;

    private Integer isSetup = 0;

    public AllFeelResponse() {
        this.feelList = new ArrayList<>();
    }
}
