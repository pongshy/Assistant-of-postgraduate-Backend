package com.pongshy.assistant.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "openid不能为空")
    private String openid;

    @ApiModelProperty(value = "标题", required = true, example = "123456")
    @NotEmpty(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "终止日期", required = true, example = "2021-05-06 00:00:00")
    @NotEmpty(message = "截止日期不能为空")
    private String endDay;
}
