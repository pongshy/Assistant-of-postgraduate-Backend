package com.pongshy.assistant.model.request;

import com.pongshy.assistant.model.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: TaskRequest
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 16:30
 **/
@Data
public class TaskRequest {

    @NotEmpty(message = "微信openid不能为空")
    private String wechatId;

    @NotEmpty(message = "任务名不能为空")
    private String taskName;

    @NotNull
    private Integer priority;

    @NotEmpty
    private String startTime;

    @NotEmpty
    private String endTime;

    private String description;

    @NotNull
    private String parentId;

    @NotBlank
    private Tag tag;
}
