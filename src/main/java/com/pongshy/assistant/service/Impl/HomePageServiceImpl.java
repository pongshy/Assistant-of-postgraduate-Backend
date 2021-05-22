package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.mongodb.Plant;
import com.pongshy.assistant.model.mongodb.Sign;
import com.pongshy.assistant.model.mongodb.SoulSoup;
import com.pongshy.assistant.model.mongodb.TaskItem;
import com.pongshy.assistant.model.myEnum.PlantEnum;
import com.pongshy.assistant.model.response.PlantResponse;
import com.pongshy.assistant.service.HomePageService;
import com.pongshy.assistant.tool.TimeTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: HomePageServiceImpl
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/30 22:02
 **/
@Service
@Slf4j
public class HomePageServiceImpl implements HomePageService {


    @Resource
    private MongoTemplate mongoTemplate;

    /*
     * @Description: 签到接口
     * @Method: [openid]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 22:06
     */
    @Override
    public Result signInOneDay(String openid) {
        // 检测今天是否已经签过到
        Integer success = 0;
        String now = TimeTool.DateToString(TimeTool.todayCreate().getTime());
        Query query = Query.query(Criteria.where("signTime").is(now).and("openid").is(openid));
        try {
            List<Sign> signList = mongoTemplate.find(query, Sign.class);
            if (signList.size() != 0) {
                success = 2;
                throw new AllException(EmAllException.BAD_REQUEST, "今日已签到");
            }
        } catch (AllException e) {
            return Result.success(success);
        }
        // 今日没有签到，则签到
        Sign sign = new Sign();

        sign.setOpenid(openid);
        sign.setSignTime(TimeTool.DateToString(TimeTool.todayCreate().getTime()));
        mongoTemplate.save(sign, "Sign");
        success = 1;
        return Result.success(success);
    }

    /*
     * @Description: 获取签到记录
     * @Method: [openid]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 22:17
     */
    @Override
    public Result getSignRecord(String openid) {
//        Query query = Query.query(Criteria.where("openid").is(openid));
//
//        Long count = mongoTemplate.count(query, Sign.class);
        String now = TimeTool.DateToString(TimeTool.todayCreate().getTime());
        Query query = Query.query(Criteria.where("signTime").is(now).and("openid").is(openid));
        List<Sign> signList = mongoTemplate.find(query, Sign.class);
        Integer success = 0;
        if (signList.size() != 0) {
            success = 1;
        }
        return Result.success(success);
    }

    /*
     * @Description: 增加心灵鸡汤
     * @Method: [sentence]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 23:02
     */
    @Override
    public Result insertSentence(String sentence) {
        SoulSoup soulSoup = new SoulSoup();

        soulSoup.setSentence(sentence);
        mongoTemplate.save(soulSoup, "SoulSoup");
        return Result.success("插入成功");
    }

    /*
     * @Description: 获取心灵鸡汤
     * @Method: []
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 23:16
     */
    @Override
    public Result getSentence() {
        Query query = Query.query(Criteria.where("id").exists(true));
        List<SoulSoup> soups = mongoTemplate.findAll(SoulSoup.class);
        long count = mongoTemplate.count(query, SoulSoup.class);
        Random random = new Random();

        int rand = random.nextInt((int)count);
        String sentence = soups.get(rand).getSentence();
        return Result.success((Object) sentence);
    }

    /*
     * @Description: 选择植物
     * @Param: [plantId, openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/4
     * @Version: V1.0
     **/
    @Override
    public Result choosePlant(Integer plantId, String openid) {
        Query query = Query.query(Criteria.where("openid").is(openid)
                .and("createTime").is(TimeTool.getNowStrTimeOnlyYMD()));
        List<Plant> plants = mongoTemplate.find(query, Plant.class);
        if (!ObjectUtils.isEmpty(plants)) {
            return Result.success("已选择过植物");
        }

        Plant plant = new Plant();

        plant.setPlantId(plantId);
        plant.setOpenid(openid);
        plant.setCreateTime(TimeTool.getNowStrTimeOnlyYMD());

        mongoTemplate.save(plant, "Plant");
        return Result.success("选择成功");
    }

    /*
     * @Description: 获取当天设定的植物和任务完成百分比
     * @Param: [openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/4
     * @Version: V1.0
     **/
    @Override
    public Result getPlantAndPercent(String openid) {
        PlantResponse response = new PlantResponse();
        Query query = Query.query(Criteria.where("openid").is(openid))
                .with(
                        Sort.by(
                                Sort.Order.desc("createTime")
                        )
                );
        Plant plant = mongoTemplate.findOne(query, Plant.class);
        if (ObjectUtils.isEmpty(plant) || !plant.getCreateTime().equals(TimeTool.getNowStrTimeOnlyYMD())) {
            return Result.success(response);
        }

//        response.setPlant(PlantEnum.getPlant(plant.getPlantId()));
        response.setPlant(plant.getPlantId());

        Date now = TimeTool.todayCreate().getTime();
        log.info(now.toString());
        Query query1 = Query.query(
                Criteria.where("wechatId").is(openid)
                        .and("startTime").lte(now)
                        .and("endTime").gte(now)
//                        .and("parentId").ne("0")
        )
                .with(Sort.by(Sort.Order.desc("createTime")));

        List<TaskItem> taskItemList = mongoTemplate.find(query1, TaskItem.class);
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
            String per = new DecimalFormat("#.00").format(percent);
            response.setPercent(Double.parseDouble(per));
        }
        response.setIsSetup(1);

        return Result.success(response);
    }
}
