package com.pongshy.assistant.controller;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.TaskRequest;
import com.pongshy.assistant.model.response.TaskResponse;
import com.pongshy.assistant.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: TaskController
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 16:25
 **/
@RestController
@CrossOrigin
@Api(tags = "任务模块说明")
@RequestMapping("/task")
public class TaskController {


    @Resource
    private TaskService taskService;

    /**
     * @Description: 增加一个任务
     * @Method: POST
     * @Return:
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 16:52
     */
    @ApiOperation(value = "增加一个任务")
    @PostMapping("/insert")
    public Result addTask(@RequestBody TaskRequest taskRequest) {
        return taskService.addOneTask(taskRequest);
    }


    /**
     * @Description: 获取所有父任务
     * @Method: [wechatId]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 20:07
     */
    @ApiOperation(value = "获取某用户所有父任务")
    @GetMapping("/parent")
    public Result getParentTasks(@RequestParam("wechatId") @NotEmpty String wechatId) {
        return taskService.getAllParentTasks(wechatId);
    }

    /*
     * @Description: 获取指定父任务下所有子任务
     * @Method: [wechatId, parentId]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 21:41
     */
    @ApiOperation(value = "获取指定父任务下所有子任务")
    @GetMapping("/child")
    public Result getChildTasks(@RequestParam("wechatId") @NotEmpty String wechatId,
                                @RequestParam("parentId") @NotEmpty String parentId) {
        return taskService.getSpecificTasks(wechatId, parentId);
    }

    /*
     * @Description: 删除指定任务
     * @Method: [id]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 21:41
     */
    @ApiOperation(value = "删除指定任务")
    @GetMapping("/delete")
    public Result deleteTasks(@RequestParam("id") @NotEmpty String id) {
        return taskService.deleteOneTask(id);
    }

    /**
     * @Description: 修改任务
     * @Method: [taskResponse]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 19:15
     */
    @ApiOperation(value = "修改任务，但还没有写好")
    @PostMapping("/modify")
    public Result modifyTask(@RequestBody TaskResponse taskResponse) {
        return taskService.modifyTheTask(taskResponse);
    }

}
