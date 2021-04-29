package com.pongshy.assistant.model.request;

import com.pongshy.assistant.model.Tag;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: TaskModifyRequest
 * @Description: 任务修改接收类
 * @Author: pongshy
 * @Date: 2021/4/29 21:06
 **/
@Data
public class TaskModifyRequest {


    @NotEmpty
    private String id;

    private String taskName;

    private Integer priority;

    private String startTime;

    private String endTime;

    private Integer isFinish;

    private String description;

    private Tag tag;

}
