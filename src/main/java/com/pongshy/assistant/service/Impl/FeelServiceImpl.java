package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.mongodb.Feel;
import com.pongshy.assistant.model.mongodb.SoulSoup;
import com.pongshy.assistant.model.request.FeelModifyRequest;
import com.pongshy.assistant.model.request.FeelRequest;
import com.pongshy.assistant.model.response.AllFeelResponse;
import com.pongshy.assistant.model.response.FeelResponse;
import com.pongshy.assistant.service.FeelService;
import com.pongshy.assistant.tool.ApiTool;
import com.pongshy.assistant.tool.TimeTool;
import javafx.collections.transformation.FilteredList;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName: FeelServiceImpl
 * @Description: 心情模块具体实现类
 * @Author: pongshy
 * @Date: 2021/5/2-12:18
 * @Version: V1.0
 **/
@Service
public class FeelServiceImpl implements FeelService {


    @Resource
    private MongoTemplate mongoTemplate;


    /*
     * @Description: 添加用户的心情和句子
     * @Param: [feelRequest]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/2
     * @Version: V1.0
     **/
    @Override
    public Result commitFeelAndWords(FeelRequest feelRequest) {
        Feel record = new Feel();

        record.setWords(feelRequest.getWords());
        record.setImageUrl(feelRequest.getImageUrl());
        record.setOpenid(feelRequest.getOpenid());
        record.setCreateTime(TimeTool.getNowStrTimeOnlyYMD());
        // 心情分析
        Integer sentiment = 1;
        try {
            // 情感分析
            sentiment = Integer.parseInt(
                    ApiTool.getFeeling(ApiTool.access_token, record.getWords())
            );
        } catch (IOException e) {
            return Result.error(new AllException(EmAllException.INTERNAL_ERROR, "百度Api出错"));
        }
        record.setSentiment(sentiment);
        // 获取句子
        Query query1 = Query.query(Criteria.where("id").exists(true));
        List<SoulSoup> soups = mongoTemplate.findAll(SoulSoup.class);
        long count = mongoTemplate.count(query1, SoulSoup.class);
        Random random = new Random(System.currentTimeMillis());

        int rand = random.nextInt((int) count);
        String sentence = soups.get(rand).getSentence();
        record.setSentence(sentence);

        mongoTemplate.save(record, "Feel");
        return Result.success("添加成功");
    }

    /*
     * @Description: 获取当天用户心情图片和文字
     * @Param: [openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/2
     * @Version: V1.0
     **/
    @Override
    public Result getImageAndWord(String openid) {
        AllFeelResponse allFeelResponse = new AllFeelResponse();
        List<FeelResponse> responseList = new ArrayList<>();
        String now = TimeTool.getNowStrTimeOnlyYMD();
        Query query = Query.query(
                Criteria.where("openid").is(openid)
                        .and("createTime").is(now)
        )
                .with(Sort.by(
                        Sort.Order.desc("_id")
                ));

        List<Feel> feelList = mongoTemplate.find(query, Feel.class);
        // 如果不存在
        if (ObjectUtils.isEmpty(feelList)) {
            return Result.success(allFeelResponse);
        }
        for (Feel feel : feelList) {
            FeelResponse feel_tmp = new FeelResponse();
            BeanUtils.copyProperties(feel, feel_tmp);
            responseList.add(feel_tmp);
        }
        allFeelResponse.setFeelList(responseList);
        allFeelResponse.setIsSetup(1);

        return Result.success(allFeelResponse);
    }

    /*
     * @Description: 修改心情
     * @Param: [request]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/2
     * @Version: V1.0
     **/
    @Override
    public Result modifyFeel(FeelModifyRequest request) {
        Query query = Query.query(Criteria.where("_id").is(request.getId()));
        Update update = Update.update("words", request.getWords())
                .set("imageUrl", request.getImageUrl());
        mongoTemplate.updateMulti(query, update, Feel.class);

        try {
            // 重新进行情感分析
            Integer sentiment = Integer.parseInt(
                    ApiTool.getFeeling(ApiTool.access_token, request.getWords())
            );
            // 修改句子
            Query query1 = Query.query(Criteria.where("id").exists(true));
            List<SoulSoup> soups = mongoTemplate.findAll(SoulSoup.class);
            long count = mongoTemplate.count(query1, SoulSoup.class);
            Random random = new Random(System.currentTimeMillis());

            int rand = random.nextInt((int) count);
            String sentence = soups.get(rand).getSentence();

            Update update1 = Update.update("sentiment", sentiment)
                    .set("sentence", sentence);
            mongoTemplate.updateMulti(query, update1, Feel.class);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(new AllException(EmAllException.INTERNAL_ERROR, "百度Api出错"));
        }

        return Result.success("修改成功");
    }

    /*
     * @Description: 删除指定文字和图片
     * @Param: [id]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/2
     * @Version: V1.0
     **/
    @Override
    public Result deleteFeel(String id) {
        Query query = Query.query(Criteria.where("id").is(id));

        mongoTemplate.remove(query, Feel.class);
        return Result.success("删除成功");
    }
}
