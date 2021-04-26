package com.pongshy.assistant.model.request;

import com.pongshy.assistant.model.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: TaskRequest
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 16:30
 **/
@Data
public class TaskRequest {

    @NotEmpty
    private String wechatId;

    private String taskName;

    private Integer priority;

    private String startTime;

    private String endTime;

    private String description;

    private String parentId;

    private Tag tag;
}
