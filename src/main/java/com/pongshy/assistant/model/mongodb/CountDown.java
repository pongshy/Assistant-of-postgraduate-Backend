package com.pongshy.assistant.model.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName: CountDown
 * @Description: 倒数日Mongodb类
 * @Author: pongshy
 * @Date: 2021/5/6-13:09
 * @Version: V1.0
 **/
@Document(collection = "CountDown")
public class CountDown {


    @Id
    private String id;

    private String openid;

    private String title;

    private String endDay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
