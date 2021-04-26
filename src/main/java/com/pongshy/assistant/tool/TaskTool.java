package com.pongshy.assistant.tool;

import com.pongshy.assistant.model.Task;

/**
 * @ClassName: TaskTool
 * @Description: 任务处理工具类
 * @Author: pongshy
 * @Date: 2021/4/26 20:45
 **/
public class TaskTool {


    public static Integer getFinishedTaskCount(Task task) {
        if (task.getChildren() == null || task.getChildren().size() == 0) {
            return 0;
        }
        Integer count = 0;
        for (Task tmp : task.getChildren()) {
            if (tmp.getIsFinish() == 1) {
                count++;
            }
        }
        return count;
    }
}
