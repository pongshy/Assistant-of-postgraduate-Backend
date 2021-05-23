package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.Tag;
import com.pongshy.assistant.model.Task;
import com.pongshy.assistant.model.mongodb.TaskItem;
import com.pongshy.assistant.model.request.TaskModifyRequest;
import com.pongshy.assistant.model.request.TaskRequest;
import com.pongshy.assistant.model.response.Priority;
import com.pongshy.assistant.model.response.TagResponse;
import com.pongshy.assistant.model.response.TaskResponse;
import com.pongshy.assistant.service.TaskService;
import com.pongshy.assistant.tool.TaskTool;
import com.pongshy.assistant.tool.TimeTool;
import com.pongshy.assistant.tool.TreeUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: TaskServiceImpl
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 16:27
 **/
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {



    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @Description: 增加一个任务
     * @Return:
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 16:32
     */
    @Override
    public Result addOneTask(TaskRequest taskRequest) {
        TaskItem taskItem = new TaskItem();

        taskItem.setWechatId(taskRequest.getWechatId());
        taskItem.setPriority(taskRequest.getPriority());
        taskItem.setTaskName(taskRequest.getTaskName());
        taskItem.setDescription(taskRequest.getDescription());
        taskItem.setStartTime(TimeTool.stringToDate(taskRequest.getStartTime()));
        taskItem.setEndTime(TimeTool.stringToDate(taskRequest.getEndTime()));
        taskItem.setCreateTime(new Date(System.currentTimeMillis()));
        taskItem.setIsFinish(0);
        taskItem.setTag(taskRequest.getTag());
        taskItem.setParentId(taskRequest.getParentId());

        mongoTemplate.save(taskItem, "TaskItem");
        return Result.success("添加成功");
    }

    /**
     * @Description: 获得某用户所有的任务
     * @Method:
     * @Return:
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 16:50
     */
    @Override
    public Result getAllTasks(String wechatId) {
        Criteria criteria = Criteria
                .where("wechatId").is(wechatId);
        Query query = Query.query(criteria)
                .with(Sort.by(
                        Sort.Order.asc("isFinish"),
                        Sort.Order.desc("createTime")));

        List<TaskItem> taskItemList = mongoTemplate.find(query, TaskItem.class);
//        List<TaskResponse> taskResponses = TreeUtils.buildTaskTree(taskItemList);
//        return Result.success(taskResponses);
        return null;
    }

    /**
     * @Description: 获取所有父任务
     * @Method: [wechatId]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 20:07
     */
    @Override
    public Result getAllParentTasks(String wechatId) {
        Criteria criteria = Criteria
                .where("wechatId").is(wechatId);
        Query query = Query.query(criteria)
                .with(Sort.by(
                        Sort.Order.asc("isFinish"),
                        Sort.Order.desc("_id")));
        List<TaskItem> taskItemList = mongoTemplate.find(query, TaskItem.class);
        if (ObjectUtils.isEmpty(taskItemList)) {
            return Result.success(null);
        }

        HashMap<String, Task> taskMap = TreeUtils.hashMapTask(taskItemList);
        List<TaskResponse> responses = new ArrayList<>();

        for (String key : taskMap.keySet()) {
            Task task = taskMap.get(key);
            TaskResponse tmp = new TaskResponse();

            tmp.setId(task.getId());
            if (!ObjectUtils.isEmpty(task.getStartTime())) {
                tmp.setStartTime(TimeTool.dateWithoutHMS(task.getStartTime()));
            }
            if (!ObjectUtils.isEmpty(task.getEndTime())) {
                tmp.setEndTime(TimeTool.dateWithoutHMS(task.getEndTime()));
            }
            tmp.setDescription(task.getDescription());
            tmp.setIsFinish(task.getIsFinish());
            if (task.getChildren() == null) {
                tmp.setAllTasks(0);
            } else {
                tmp.setAllTasks(task.getChildren().size());
            }
            tmp.setParentId(task.getParentId());
            if (!ObjectUtils.isEmpty(task.getStartTime()) && !ObjectUtils.isEmpty(task.getEndTime())) {
                tmp.setPeriod_time((int) (task.getEndTime().getTime() - task.getStartTime().getTime()) / (1000 * 3600 * 24));
                tmp.setPeriod_deadline(TimeTool.getDeadline(task.getEndTime()));
            }
            tmp.setFinishedTask(TaskTool.getFinishedTaskCount(task));
            if (!ObjectUtils.isEmpty(task.getPriority())) {
                tmp.setPriority(new Priority(task.getPriority()));
            }
            if (!ObjectUtils.isEmpty(task.getTag())) {
                tmp.setTag(new TagResponse(task.getTag()));
            }
            tmp.setTaskName(task.getTaskName());

            responses.add(tmp);
        }
        List<TaskResponse> taskResponseList = responses
                .stream()
                .sorted(Comparator.comparing(TaskResponse::getId).reversed())
                .collect(Collectors.toList());

        return Result.success(taskResponseList);
    }

    /**
     * @Description: 删除一个任务
     * @Method:
     * @Return:
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 18:50
     */
    @Override
    public Result deleteOneTask(String id) {
        Query query = Query.query(
                Criteria.where("id").is(id)
        );
        List<TaskItem> taskItemList = mongoTemplate.find(query, TaskItem.class);
        try {
            if (taskItemList.size() == 1) {
                TaskItem taskItem = taskItemList.get(0);

                if (taskItem.getParentId().equals("0")) {
                    // 先删除它的子任务，再删除它
                    Query query1 = Query.query(
                            Criteria.where("parentId").is(id)
                    );
                    mongoTemplate.remove(query1, TaskItem.class, "TaskItem");
                }
                // 删除该项任务
                mongoTemplate.remove(query, TaskItem.class, "TaskItem");
                return Result.success("删除成功");
            } else {
                throw new AllException(EmAllException.BAD_REQUEST, "id输入有误");
            }
        } catch (AllException ex) {
            return Result.error(ex);
        }

    }

    /*
     * @Description: 修改任务
     * @Method: [taskResponse]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 19:16
     */
    @Override
    public Result modifyTheTask(TaskModifyRequest taskModifyRequest) {
        Query query = Query.query(Criteria.where("_id").is(taskModifyRequest.getId()));
        Update update = Update.update("taskName", taskModifyRequest.getTaskName())
                .set("priority", taskModifyRequest.getPriority())
                .set("isFinish", taskModifyRequest.getIsFinish())
                .set("startTime", TimeTool.stringToDate(taskModifyRequest.getStartTime()))
                .set("endTime", TimeTool.stringToDate(taskModifyRequest.getEndTime()))
                .set("description", taskModifyRequest.getDescription())
                .set("tag.color", taskModifyRequest.getTag().getColor())
                .set("tag.tag_name", taskModifyRequest.getTag().getTag_name());

        mongoTemplate.updateFirst(query, update, TaskItem.class);

        return Result.success("更新成功");
    }

    /*
     * @Description: 获取指定父任务下的子任务
     * @Method: [wechatId, parentId]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 21:43
     */
    @Override
    public Result getSpecificTasks(String wechatId, String parentId) {
        Criteria criteria = Criteria
                .where("wechatId").is(wechatId)
                .and("parentId").is(parentId);
        Query query = Query.query(criteria)
                .with(Sort.by(
                        Sort.Order.asc("isFinish"),
                        Sort.Order.desc("createTime")));
        List<TaskItem> taskItemList = mongoTemplate.find(query, TaskItem.class);
        List<TaskResponse> responses = new ArrayList<>();

        for (TaskItem task: taskItemList) {
            TaskResponse tmp = new TaskResponse();

            tmp.setId(task.getId());
            tmp.setStartTime(TimeTool.dateWithoutHMS(task.getStartTime()));
            tmp.setEndTime(TimeTool.dateWithoutHMS(task.getEndTime()));
            tmp.setDescription(task.getDescription());
            tmp.setIsFinish(task.getIsFinish());
            tmp.setAllTasks(0);
            tmp.setParentId(task.getParentId());
            tmp.setPeriod_time((int)(task.getEndTime().getTime() - task.getStartTime().getTime()) / (1000 * 3600 * 24));
            tmp.setPeriod_deadline(TimeTool.getDeadline(task.getEndTime()));
            tmp.setFinishedTask(0);
            tmp.setPriority(new Priority(task.getPriority()));
            tmp.setTag(new TagResponse(task.getTag()));
            tmp.setTaskName(task.getTaskName());

            responses.add(tmp);
        }

        return Result.success(responses);
    }

    /*
     * @Description: 获取今日任务
     * @Method: [openid]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 19:06
     */
    @Override
    public Result getTodayTasks(String openid) {
        Date now = TimeTool.todayCreate().getTime();
        log.info(now.toString());
        Query query = Query.query(
                Criteria.where("wechatId").is(openid)
                        .and("startTime").lte(now)
                        .and("endTime").gte(now)
//                        .and("parentId").ne("0")
//                        .and("isFinish").ne(1)
                )
                .with(Sort.by(Sort.Order.desc("createTime")));

        List<TaskItem> taskItemList = mongoTemplate.find(query, TaskItem.class);
        if (ObjectUtils.isEmpty(taskItemList)) {
            return Result.success(null);
        }
        List<TaskResponse> responseList = new ArrayList<>();

        for (TaskItem task : taskItemList) {
            TaskResponse tmp = new TaskResponse();

            tmp.setId(task.getId());
            tmp.setStartTime(TimeTool.dateWithoutHMS(task.getStartTime()));
            tmp.setEndTime(TimeTool.dateWithoutHMS(task.getEndTime()));
            tmp.setDescription(task.getDescription());
            tmp.setIsFinish(task.getIsFinish());
            tmp.setAllTasks(0);
            tmp.setParentId(task.getParentId());
            tmp.setPeriod_time((int)((task.getEndTime().getTime() - task.getStartTime().getTime()) / (1000 * 3600 * 24)));
            tmp.setPeriod_deadline(TimeTool.getDeadline(task.getEndTime()));
            tmp.setFinishedTask(0);
            tmp.setPriority(new Priority(task.getPriority()));
            tmp.setTag(new TagResponse(task.getTag()));
            tmp.setTaskName(task.getTaskName());

            responseList.add(tmp);
        }

        return Result.success(responseList);
    }
}
