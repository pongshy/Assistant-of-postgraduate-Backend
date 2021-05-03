package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.mongodb.Feel;
import com.pongshy.assistant.model.request.FeelModifyRequest;
import com.pongshy.assistant.model.request.FeelRequest;
import com.pongshy.assistant.model.response.FeelResponse;
import com.pongshy.assistant.service.FeelService;
import com.pongshy.assistant.tool.TimeTool;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Queue;

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

        mongoTemplate.save(record, "Feel");
        return Result.success("添加成功");
    }

    /*
     * @Description: 获取用户心情图片和文字
     * @Param: [openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/2
     * @Version: V1.0
     **/
    @Override
    public Result getImageAndWord(String openid) {
        Query query = Query.query(Criteria.where("openid").is(openid))
                .with(Sort.by(
                        Sort.Order.desc("createTime")
                ));

        Feel feel = mongoTemplate.findOne(query, Feel.class);
        if (ObjectUtils.isEmpty(feel)) {
            return Result.success(feel);
        }
        FeelResponse response = new FeelResponse();
        BeanUtils.copyProperties(feel, response);
        return Result.success(response);
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
