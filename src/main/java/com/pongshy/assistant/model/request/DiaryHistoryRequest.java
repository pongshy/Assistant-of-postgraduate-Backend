package com.pongshy.assistant.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: DiaryHistoryRequest
 * @Description: 心情日记历史接口接受类
 * @Author: pongshy
 * @Date: 2021/5/7 23:01
 **/
@Data
@ApiModel(value = "心情日记历史接口接受类", description = "心情日记历史接口接受类")
public class DiaryHistoryRequest {

    @ApiModelProperty(value = "openid", required = true, dataType = "String", example = "123456")
    @NotEmpty(message = "openid不能为空")
    private String openid;

    @ApiModelProperty(value = "日期", required = true, dataType = "String", example = "2021-05-07 00:00:00")
    @NotEmpty(message = "时间不能为空")
    private String time;



}
