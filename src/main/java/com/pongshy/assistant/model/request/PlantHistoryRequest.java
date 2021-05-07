package com.pongshy.assistant.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: PlantHistoryRequest
 * @Description: 根据日期获取植物和任务完成百分比接受类
 * @Author: pongshy
 * @Date: 2021/5/7 22:09
 **/
@Data
@ApiModel(value = "根据日期获取植物和任务完成百分比接受类", description = "根据日期获取植物和任务完成百分比接受类")
public class PlantHistoryRequest {


    @ApiModelProperty(value = "微信用户openid", required = true, dataType = "String", example = "123456")
    @NotEmpty(message = "openid不能为空")
    private String openid;

    @ApiModelProperty(value = "日期", required = true, dataType = "String", example = "2021-05-07 00:00:00")
    @NotEmpty(message = "time不能为空")
    private String time;
}
