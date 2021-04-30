package com.pongshy.assistant.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.mongodb.User;
import com.pongshy.assistant.service.LoginService;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
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
            //请求
            HttpGet httpGet = new HttpGet(url);

            //Get请求内容
            CloseableHttpResponse response = httpClient.execute(httpGet);
            log.info(response.toString());

            HttpEntity httpEntity = response.getEntity();
            JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(httpEntity));
            log.info(jsonObject.toJSONString());
            String errcode = jsonObject.getString("errcode");
            log.info("errcode = " + errcode);
            if (errcode == null) {
                String openid = jsonObject.getString("openid");
                log.info(openid);
                Query query = Query.query(Criteria.where("openId").is(openid));
                List<User> userList = mongoTemplate.find(query, User.class);
//                UserDO userDO = userDOMapper.selectByPrimaryKey(openid);
                if (userList.size() == 0) {
                    User record = new User();

                    record.setOpenId(openid);
                    mongoTemplate.save(record, User.class.getName());
//                    if (userDOMapper.insertSelective(record) == 0) {
//                        throw new AllException(EmAllException.DATABASE_ERROR, "数据库操作异常");
//                    }
                }
                return Result.success((Object) openid);
            } else if (errcode.equals("-1")) {
                throw new AllException(EmAllException.ErrorCode, "系统繁忙，此时请开发者稍候再试");
            } else if (errcode.equals("40029")) {
                throw new AllException(EmAllException.ErrorCode, "code无效");
            } else if (errcode.equals("45011")) {
                throw new AllException(EmAllException.ErrorCode, "频率限制，每个用户每分钟100次");
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
//                    new AllException(EmAllException.BAD_REQUEST, "调用登录凭证校验接口失败"), "login"),
//                    HttpStatus.OK);
            return Result.error(new AllException(EmAllException.BAD_REQUEST, "调用登录凭证校验接口失败"));
        } catch (IOException e) {
            e.printStackTrace();
//            return new ResponseEntity<>(new ErrorResult(
//                    new AllException(EmAllException.INTERNAL_ERROR), "login"),
//                    HttpStatus.OK);
            return Result.error(new AllException(EmAllException.INTERNAL_ERROR));
        }
    }
}
