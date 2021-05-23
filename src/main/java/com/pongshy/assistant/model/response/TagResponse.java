package com.pongshy.assistant.model.response;

import com.pongshy.assistant.model.Tag;
import com.pongshy.assistant.model.myEnum.Color;
import lombok.Data;

/**
 * @ClassName: TagResponse
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/27 21:37
 **/
@Data
public class TagResponse {


    private String color;

    private String tag_name;

    private Integer imp;

    private String important;

    private String emergency;


    public TagResponse(TagResponse tagResponse) {
        this.color = tagResponse.getColor();
        this.tag_name = tagResponse.getTag_name();
        this.imp = Color.getImp(this.color);
        this.important = Color.getImportant(this.imp);
        this.emergency = Color.getEmergency(this.imp);
    }

    public TagResponse(Tag tag) {
        this.color = tag.getColor();
        this.tag_name = tag.getTag_name();
        this.imp = Color.getImp(this.color);
        this.important = Color.getImportant(this.imp);
        this.emergency = Color.getEmergency(this.imp);
    }

    public TagResponse() {
        this.color = "";
        this.tag_name = "";
        this.imp = null;
        this.important = "";
        this.emergency = "";
    }
}
