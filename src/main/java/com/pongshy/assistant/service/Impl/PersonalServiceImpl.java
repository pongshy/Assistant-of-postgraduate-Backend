package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.mongodb.*;
import com.pongshy.assistant.model.request.DayRequest;
import com.pongshy.assistant.model.request.DiaryHistoryRequest;
import com.pongshy.assistant.model.request.PlantHistoryRequest;
import com.pongshy.assistant.model.response.*;
import com.pongshy.assistant.service.PersonalService;
import com.pongshy.assistant.tool.NumTool;
import com.pongshy.assistant.tool.TimeTool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: PersonalServiceImpl
 * @Description: 个人主页实现类
 * @Author: pongshy
 * @Date: 2021/5/6-12:18
 * @Version: V1.0
 **/
@Service
@Slf4j
public class PersonalServiceImpl implements PersonalService {


    @Autowired
    private MongoTemplate mongoTemplate;


    /*
     * @Description: 获取个人主页的已完成任务数，总任务数，打卡天数
     * @Param: [openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/6
     * @Version: V1.0
     **/
    @Override
    public Result getPersonalInformation(String openid) {
        PersonalResponse response = new PersonalResponse();
        Query query = Query.query(Criteria.where("wechatId").is(openid).and("parentId").ne("0"));
        Query query2 = Query.query(Criteria.where("_id").is(openid));
        UserInfo userInfo = mongoTemplate.findOne(query2, UserInfo.class);
        List<TaskItem> taskItemList = mongoTemplate.find(query, TaskItem.class);

//        try {
//            if (ObjectUtils.isEmpty(userInfo)) {
//                throw new AllException(EmAllException.BAD_REQUEST, "用户不存在");
//            }
//        } catch (AllException e) {
//            return Result.error(e);
//        }
        if (ObjectUtils.isEmpty(userInfo)) {
//            response.setIsLogin(0);
            return Result.success(response);
        }
        if (userInfo.getWname() == null || userInfo.getWimage() == null) {
            return Result.success(response);
        }
        response.setImageUrl(userInfo.getWimage());
        response.setName(userInfo.getWname());
        if (ObjectUtils.isEmpty(taskItemList)) {
            response.setTaskNum(0);
            response.setFinishedTaskNum(0);
            response.setPercent(0.00);
        } else {
            response.setTaskNum(taskItemList.size());
            int count = 0;
            for (TaskItem taskItem : taskItemList) {
                if (taskItem.getIsFinish() == 1) {
                    count++;
                }
            }
            response.setFinishedTaskNum(count);
            // 今日任务完成百分比
            Date now = TimeTool.todayCreate().getTime();
            Query query1 = Query.query(
                    Criteria.where("wechatId").is(openid)
                            .and("startTime").lte(now)
                            .and("endTime").gte(now)
                            .and("parentId").ne("0")
            )
                    .with(Sort.by(Sort.Order.desc("createTime")));
            List<TaskItem> tmp_task = mongoTemplate.find(query1, TaskItem.class);

            if (ObjectUtils.isEmpty(tmp_task)) {
                response.setPercent(0.00);
            } else {
                Integer isFinish = 0;
                for (TaskItem tmp : tmp_task) {
                    if (tmp.getIsFinish() == 1) {
                        isFinish++;
                    }
                }
                Double percent = (double) isFinish / (double) tmp_task.size();
                response.setPercent(NumTool.saveTwoDecimal(percent));
            }
        }

        Query query1 = Query.query(Criteria.where("openid").is(openid));
        int cnt = (int) mongoTemplate.count(query1, Sign.class);
        response.setSignDays(cnt);
        response.setIsLogin(1);

        return Result.success(response);
    }

    /*
     * @Description: 设置倒数日和title
     * @Param: [dayRequest]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/6
     * @Version: V1.0
     **/
    @Override
    public Result saveCountdownDay(DayRequest dayRequest) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
//            Date endDay = df.parse(dayRequest.getEndDay());
            Query query = Query.query(Criteria.where("openid").is(dayRequest.getOpenid()));
            List<CountDown> countDownList = mongoTemplate.find(query, CountDown.class);
            Integer i = 0;
            if (!ObjectUtils.isEmpty(countDownList)) {
                i = -1;
                return Result.success(i);
            }
            CountDown record = new CountDown();

            record.setOpenid(dayRequest.getOpenid());
            record.setEndDay(dayRequest.getEndDay());
            record.setTitle(dayRequest.getTitle());
            mongoTemplate.save(record, "CountDown");
            i = 1;
            return Result.success(i);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(new AllException(EmAllException.INTERNAL_ERROR, "设置失败"));
        }
    }

    /*
     * @Description: 获取结束日期和标题
     * @Param: [openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/6
     * @Version: V1.0
     **/
    @Override
    public Result getCountdownDayAndTitle(String openid) {
        Query query = Query.query(Criteria.where("openid").is(openid));
        CountdownDayResponse response = new CountdownDayResponse();

        CountDown countDown = mongoTemplate.findOne(query, CountDown.class);
        if (!ObjectUtils.isEmpty(countDown)) {
            response.setTitle(countDown.getTitle());
            response.setEndDay(countDown.getEndDay().substring(0, countDown.getEndDay().indexOf(" ")));
            response.setIsSetup(1);
        }

        return Result.success(response);
    }

    /*
     * @Description: 修改倒数日和title
     * @Method: [dayRequest]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/6 22:17
     */
    @Override
    public Result modifyDayAndTitle(DayRequest dayRequest) {
        Query query = Query.query(Criteria.where("openid").is(dayRequest.getOpenid()));
        Update update = Update.update("title", dayRequest.getTitle())
                .set("endDay", dayRequest.getEndDay());
        mongoTemplate.updateMulti(query, update, CountDown.class);

        return Result.success((Object) 1);
    }

    /*
     * @Description: 根据日期返回用户所选择的植物和任务完成百分比
     * @Method: [plantHistoryRequest]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/7 22:13
     */
    @Override
    public Result getHistoryPlant(PlantHistoryRequest plantHistoryRequest)  {
        PlantResponse response = new PlantResponse();
        Query query = Query.query(
                Criteria.where("openid").is(plantHistoryRequest.getOpenid())
                        .and("createTime").is(plantHistoryRequest.getTime())
        )
                .with(
                        Sort.by(
                                Sort.Order.desc("createTime")
                        )
                );
        Plant plant = mongoTemplate.findOne(query, Plant.class);
        // 若不存在
        if (ObjectUtils.isEmpty(plant)) {
            return Result.success(response);
        }
        response.setPlant(plant.getPlantId());

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sf.parse(plantHistoryRequest.getTime());

            Query query1 = Query.query(
                    Criteria.where("wechatId").is(plantHistoryRequest.getOpenid())
                            .and("startTime").lte(date)
                            .and("endTime").gte(date)
//                            .and("parentId").ne("0")
            )
                    .with(Sort.by(Sort.Order.desc("createTime")));

            List<TaskItem> taskItemList = mongoTemplate.find(query1, TaskItem.class);
            // 如果为空
            if (ObjectUtils.isEmpty(taskItemList)) {
                response.setPercent(0.0);
            } else {
                Double finish = 0.0;
                for (TaskItem taskItem : taskItemList) {
                    if (taskItem.getIsFinish() == 1) {
                        finish++;
                    }
                }
                Double percent = finish / (double) taskItemList.size();
                response.setPercent(NumTool.saveTwoDecimal(percent));
            }
            response.setIsSetup(1);

            return Result.success(response);
        } catch (ParseException e) {
            return Result.error(new AllException(EmAllException.BAD_REQUEST, "时间输入有误"));
        }

    }

    /*
     * @Description: 根据日期返回用户的心情日记
     * @Method: [diaryHistoryRequest]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/7 23:05
     */
    @Override
    public Result getHistoryDiary(DiaryHistoryRequest diaryHistoryRequest) {
        AllFeelResponse allFeelResponse = new AllFeelResponse();
        List<FeelResponse> responseList = new ArrayList<>();
        try {
            Query query = Query.query(
                    Criteria.where("openid").is(diaryHistoryRequest.getOpenid())
                            .and("createTime").is(diaryHistoryRequest.getTime())
            )
                    .with(
                            Sort.by(
                                    Sort.Order.desc("_id")
                            )
                    );
            List<Feel> feelList = mongoTemplate.find(query, Feel.class, "Feel");
            // 如果不存在
            if (ObjectUtils.isEmpty(feelList)) {
                return Result.success(allFeelResponse);
            }
            for (Feel feel : feelList) {
                FeelResponse response = new FeelResponse();
                BeanUtils.copyProperties(feel, response);
                response.setCreateTime(TimeTool.DateToString(feel.getInsertTime()));
                responseList.add(response);
            }
            allFeelResponse.setIsSetup(1);
            allFeelResponse.setFeelList(responseList);
            return Result.success(allFeelResponse);
        } catch (Exception e) {
            return Result.success(allFeelResponse);
        }
    }
}
