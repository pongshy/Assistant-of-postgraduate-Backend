package com.pongshy.assistant.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.Tag;
import com.pongshy.assistant.model.mongodb.CountDown;
import com.pongshy.assistant.model.mongodb.TaskItem;
import com.pongshy.assistant.model.mongodb.UserInfo;
import com.pongshy.assistant.model.request.WechatInforRequest;
import com.pongshy.assistant.model.response.LoginResponse;
import com.pongshy.assistant.service.LoginService;
import com.pongshy.assistant.tool.TimeTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: LoginServiceImpl
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/29 10:26
 **/
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Value("${wx.AppID}")
    public String AppId;

    @Value("${wx.AppSecret}")
    public String secret;

    @Resource
    private MongoTemplate mongoTemplate;



    @Override
    public Result loginWX(String code) {
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + AppId +
                    "&secret=" + secret +
                    "&js_code=" + code +
                    "&grant_type=authorization_code";
            log.info("code:" + code);
            log.info(url);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //??????
            HttpGet httpGet = new HttpGet(url);

            //Get????????????
            CloseableHttpResponse response = httpClient.execute(httpGet);
            log.info(response.toString());

            HttpEntity httpEntity = response.getEntity();
            JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(httpEntity));
            log.info(jsonObject.toJSONString());
            String errcode = jsonObject.getString("errcode");
            log.info("errcode = " + errcode);
            if (errcode == null) {
                String openid = jsonObject.getString("openid");
                String session_key = jsonObject.getString("session_key");
                log.info(openid);
                Query query = Query.query(Criteria.where("openId").is(openid));
                List<UserInfo> userList = mongoTemplate.find(query, UserInfo.class);
//                UserDO userDO = userDOMapper.selectByPrimaryKey(openid);
                LoginResponse loginResponse = new LoginResponse();
                if (userList.size() == 0) {
                    UserInfo record = new UserInfo();

                    record.setOpenId(openid);
                    mongoTemplate.save(record, "UserInfo");

                    Tag tag = new Tag();
                    tag.setColor("green");
                    // ????????????
                    TaskItem taskItem3 = new TaskItem();
                    taskItem3.setParentId("0");
                    taskItem3.setTaskName("????????????");
                    taskItem3.setWechatId(openid);
                    taskItem3.setCreateTime(new Date(System.currentTimeMillis()));
                    taskItem3.setIsFinish(0);
                    taskItem3.setPriority(3);
                    taskItem3.setTag(tag);
                    mongoTemplate.save(taskItem3, "TaskItem");

                    TaskItem taskItem2 = new TaskItem();
                    taskItem2.setParentId("0");
                    taskItem2.setTaskName("????????????");
                    taskItem2.setWechatId(openid);
                    taskItem2.setCreateTime(new Date(System.currentTimeMillis()));
                    taskItem2.setIsFinish(0);
                    taskItem2.setPriority(3);
                    taskItem2.setTag(tag);
                    mongoTemplate.save(taskItem2, "TaskItem");

                    TaskItem taskItem1 = new TaskItem();
                    taskItem1.setTaskName("????????????");
                    taskItem1.setParentId("0");
                    taskItem1.setWechatId(openid);
                    taskItem1.setCreateTime(new Date(System.currentTimeMillis()));
                    taskItem1.setIsFinish(0);
                    taskItem1.setPriority(3);
                    taskItem1.setTag(tag);
                    mongoTemplate.save(taskItem1, "TaskItem");

                    TaskItem taskItem = new TaskItem();
                    taskItem.setCreateTime(new Date(System.currentTimeMillis()));
                    taskItem.setParentId("0");
                    taskItem.setTaskName("????????????");
                    taskItem.setWechatId(record.getOpenId());
                    taskItem.setIsFinish(0);
                    taskItem.setPriority(3);
                    taskItem.setTag(tag);
                    mongoTemplate.save(taskItem, "TaskItem");


                    // ?????????????????????????????????
                    CountDown countDown = new CountDown();
                    countDown.setOpenid(openid);
                    countDown.setTitle("????????????");
                    countDown.setEndDay(TimeTool.getFirstExamTime());
                    mongoTemplate.save(countDown, "CountDown");

//                    if (userDOMapper.insertSelective(record) == 0) {
//                        throw new AllException(EmAllException.DATABASE_ERROR, "?????????????????????");
//                    }
                    loginResponse.setIsNew(1);
                } else {
                    UserInfo userInfo = userList.get(0);
                    if (ObjectUtils.isEmpty(userInfo.getWname())) {
                        loginResponse.setIsNew(1);
                    } else {
                        loginResponse.setIsNew(0);
                    }
                }

                loginResponse.setOpenid(openid);
                loginResponse.setSession_key(session_key);
//                return Result.success((Object) openid);
                return Result.success(loginResponse);
            } else if (errcode.equals("-1")) {
                throw new AllException(EmAllException.ErrorCode, "?????????????????????????????????????????????");
            } else if (errcode.equals("40029")) {
                throw new AllException(EmAllException.ErrorCode, "code??????");
            } else if (errcode.equals("45011")) {
                throw new AllException(EmAllException.ErrorCode, "????????????????????????????????????100???");
            } else {
                throw new AllException(EmAllException.ErrorCode, "code has been used");
            }
        }
        catch (AllException e) {
            log.info(e.getMsg());
//            return new ResponseEntity<>(new ErrorResult(e, "/login"), HttpStatus.OK);
            return Result.error(e);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            log.info(e.getMessage());
//            return new ResponseEntity<>(new ErrorResult(
//                    new AllException(EmAllException.BAD_REQUEST, "????????????????????????????????????"), "login"),
//                    HttpStatus.OK);
            return Result.error(new AllException(EmAllException.BAD_REQUEST, "????????????????????????????????????"));
        } catch (IOException e) {
            e.printStackTrace();
//            return new ResponseEntity<>(new ErrorResult(
//                    new AllException(EmAllException.INTERNAL_ERROR), "login"),
//                    HttpStatus.OK);
            return Result.error(new AllException(EmAllException.INTERNAL_ERROR));
        }
    }

    /*
     * @Description: ?????????????????????????????????
     * @Method: [wechatInforRequest]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/6 20:21
     */
    @Override
    public Result updateUserInfor(WechatInforRequest wechatInforRequest) {
        Integer i = 0;
        Query query = Query.query(Criteria.where("_id").is(wechatInforRequest.getOpenid()));
        Update update = Update.update("wname", wechatInforRequest.getWname())
                .set("wimage", wechatInforRequest.getWimage());
        mongoTemplate.updateMulti(query, update, UserInfo.class, "UserInfo");
        i = 1;

        return Result.success(i);
    }
}
