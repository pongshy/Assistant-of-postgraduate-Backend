package com.pongshy.assistant.model;

import com.pongshy.assistant.model.mongodb.TaskItem;
import com.pongshy.assistant.model.response.TagResponse;
import com.pongshy.assistant.model.response.TaskResponse;
import com.pongshy.assistant.tool.TimeTool;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: Task
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 16:23
 **/
@Data
public class Task {


    private String id;

    private String taskName;

    private Integer priority;

    private Integer isFinish;

    private Date startTime;

    private Date endTime;

    private String description;

    private String parentId;

    private List<Task> children;

    private TagResponse tag;

    public void addChild(TaskItem taskItem) {
        if (children == null) {
            children = new ArrayList<>();
        }
        Task task = new Task();
        BeanUtils.copyProperties(taskItem, task);
        task.setEndTime(taskItem.getEndTime());
        task.setStartTime(taskItem.getStartTime());
        if (taskItem.getTag() !=null) {
            if (taskItem.getTag().getTag_name() != null) {
                if (this.tag == null) {
                    this.tag = new TagResponse();
                }
                this.tag.setTag_name(taskItem.getTag().getTag_name());
            }
            this.tag.setColor(taskItem.getTag().getColor());
        }
        children.add(task);
    }

    public void setTag(Tag tag) {
        this.tag = new TagResponse(tag);
    }
}
