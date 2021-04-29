package com.pongshy.assistant.service;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.TaskModifyRequest;
import com.pongshy.assistant.model.request.TaskRequest;
import com.pongshy.assistant.model.response.TaskResponse;
import org.springframework.http.ResponseEntity;

/**
 * @ClassName: TaskService
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 16:26
 **/
public interface TaskService {


    /*
     * @Description: 增加一个任务
     * @Method: [taskRequest]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 19:15
     */
    Result addOneTask(TaskRequest taskRequest);

    /*
     * @Description: 获得某用户所有的任务
     * @Method: [wechatId]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 19:15
     */
    Result getAllTasks(String wechatId);

    /**
     * @Description: 获取所有父任务
     * @Method: [wechatId]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 20:07
     */
    Result getAllParentTasks(String wechatId);

    /*
     * @Description: 删除指定任务
     * @Method: [id]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 19:15
     */
    Result deleteOneTask(String id);

    /*
     * @Description: 修改任务
     * @Method: [taskResponse]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 19:16
     */
    Result modifyTheTask(TaskModifyRequest taskModifyRequest);

    /*
     * @Description: 获取指定父任务下的子任务
     * @Method: [wechatId, parentId]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 21:43
     */
    Result getSpecificTasks(String wechatId, String parentId);
}
