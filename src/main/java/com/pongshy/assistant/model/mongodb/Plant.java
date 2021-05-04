package com.pongshy.assistant.model.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName: Plant
 * @Description: TODO
 * @Author: pongshy
 * @Date: 2021/5/4-22:37
 * @Version: V1.0
 **/
@Document(collection = "Plant")
public class Plant {


    @Id
    private String id;

    private String openid;

    private String createTime;

    private Integer plantId;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }
}
