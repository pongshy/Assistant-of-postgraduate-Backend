package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.mongodb.Sign;
import com.pongshy.assistant.model.mongodb.SoulSoup;
import com.pongshy.assistant.service.HomePageService;
import com.pongshy.assistant.tool.TimeTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        Query query = Query.query(Criteria.where("signTime").is(now));
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
        Query query = Query.query(Criteria.where("signTime").is(now));
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
}
