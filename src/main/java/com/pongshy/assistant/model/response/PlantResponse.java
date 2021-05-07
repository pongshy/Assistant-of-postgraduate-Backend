package com.pongshy.assistant.model.response;

import lombok.Data;

/**
 * @ClassName: PlantResponse
 * @Description: 植物与任务完成百分比返回类
 * @Author: pongshy
 * @Date: 2021/5/4-22:47
 * @Version: V1.0
 **/
@Data
public class PlantResponse {


    private Integer plant;

    private Double percent;

    private Integer isSetup = 0;

}
