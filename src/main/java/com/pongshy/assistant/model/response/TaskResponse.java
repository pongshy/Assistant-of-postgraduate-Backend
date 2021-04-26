package com.pongshy.assistant.model.response;

import com.pongshy.assistant.model.Tag;
import com.pongshy.assistant.model.mongodb.TaskItem;
import com.pongshy.assistant.tool.TimeTool;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: TaskResponse
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 17:39
 **/
@Data
public class TaskResponse {


    private String id;

    private String taskName;

    private Integer isFinish;

    private String startTime;

    private String endTime;

    private String description;

    private Priority priority;

    private Integer allTasks;

    private Integer finishedTask;

    private Integer period_time;

    private Tag tag;

    private String parentId;
}
