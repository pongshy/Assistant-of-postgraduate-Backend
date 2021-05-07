package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.mongodb.CountDown;
import com.pongshy.assistant.model.mongodb.Sign;
import com.pongshy.assistant.model.mongodb.TaskItem;
import com.pongshy.assistant.model.mongodb.UserInfo;
import com.pongshy.assistant.model.request.DayRequest;
import com.pongshy.assistant.model.response.CountdownDayResponse;
import com.pongshy.assistant.model.response.PersonalResponse;
import com.pongshy.assistant.service.PersonalService;
import com.pongshy.assistant.tool.NumTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        Query query = Query.query(Criteria.where("wechatId").is(openid));
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
            Double percent = (double) count / (double) taskItemList.size();
            response.setPercent(NumTool.saveTwoDecimal(percent));
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
}
