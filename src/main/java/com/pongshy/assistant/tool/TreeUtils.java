package com.pongshy.assistant.tool;

import com.pongshy.assistant.model.Tag;
import com.pongshy.assistant.model.Task;
import com.pongshy.assistant.model.TestObject;
import com.pongshy.assistant.model.mongodb.TaskItem;
import com.pongshy.assistant.model.response.TaskResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: TreeUtils
 * @Description: 关于树的操作方法
 * @Author: pongshy
 * @Date: 2021/3/27 20:39
 **/
@Slf4j
public class TreeUtils {


    public static List<TestObject> buildTree(List<TestObject> testList) {
        List<TestObject> result = new ArrayList<>();
        int i = 0;
        for (TestObject testObject : testList) {
            if (testObject.parentId.equals("0")) {
                result.add(testObject);
            }
            log.info("i: " + i);
            for (TestObject tmp : testList) {
                log.info("TestObject-" + tmp.toString());
                if (tmp.parentId.equals(testObject.id)) {
                    testObject.addChild(tmp);
                }
            }
            ++i;
        }
        return result;
    }

    public static List<Task> buildTaskTree(List<TaskItem> taskItemList) {
        List<Task> result = new ArrayList<>();

        for (TaskItem taskItem : taskItemList) {
            if (taskItem.getParentId().equals("0")) {
                Task task = new Task();
//                BeanUtils.copyProperties(taskItem, task);
                task.setId(taskItem.getId());
                task.setTaskName(taskItem.getTaskName());
                if (!ObjectUtils.isEmpty(taskItem.getDescription())) {
                    task.setDescription(taskItem.getDescription());
                }
                if (!ObjectUtils.isEmpty(taskItem.getPriority())) {
                    task.setPriority(taskItem.getPriority());
                }
                task.setIsFinish(taskItem.getIsFinish());
                if (!ObjectUtils.isEmpty(taskItem.getEndTime())) {
                    task.setEndTime(taskItem.getEndTime());
                }
                if (!ObjectUtils.isEmpty(taskItem.getStartTime())) {
                    task.setStartTime(taskItem.getStartTime());
                }
                task.setParentId(taskItem.getParentId());
                if (!ObjectUtils.isEmpty(taskItem.getTag())) {
                    task.setTag(taskItem.getTag());
                }

                for (TaskItem tmp : taskItemList) {
                    if (tmp.getParentId().equals(taskItem.getId())) {
                        task.addChild(tmp);
                    }
                }
                result.add(task);
            }
        }
        return result;
    }

    public static HashMap<String, Task> hashMapTask(List<TaskItem> taskItemList) {
        List<Task> taskList = buildTaskTree(taskItemList);
        HashMap<String, Task> map = new HashMap<>();
        for (Task task : taskList) {
            map.put(task.getId(), task);
        }
        return map;
    }


}
