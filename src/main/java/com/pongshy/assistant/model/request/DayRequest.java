package com.pongshy.assistant.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: DayRequest
 * @Description: 设置倒数日接受类
 * @Author: pongshy
 * @Date: 2021/5/6-12:57
 * @Version: V1.0
 **/
@Data
@ApiModel(value = "设置倒数日接受体", description = "设置倒数日接受体")
public class DayRequest {


    @ApiModelProperty(value = "openid",required = true, example = "123456")
    private String openid;

    @ApiModelProperty(value = "标题", required = true, example = "123456")
    private String title;

    @ApiModelProperty(value = "终止日期", required = true, example = "2021-05-06 00:00:00")
    private String endDay;
}
