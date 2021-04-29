package com.pongshy.assistant.model.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName: User
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/29 10:44
 **/
@Document(collection = "User")
public class User {


    @Id
    private String openId;

    private String wname;

    private String wimage;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getWimage() {
        return wimage;
    }

    public void setWimage(String wimage) {
        this.wimage = wimage;
    }
}
