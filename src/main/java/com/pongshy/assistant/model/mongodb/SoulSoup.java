package com.pongshy.assistant.model.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName: SoulSoup
 * @Description: 心灵鸡汤
 * @Author: pongshy
 * @Date: 2021/4/30 23:01
 **/
@Document(collection = "SoulSoup")
public class SoulSoup {

    @Id
    private String id;

    private Integer sentiment;

    private String sentence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSentiment() {
        return sentiment;
    }

    public void setSentiment(Integer sentiment) {
        this.sentiment = sentiment;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
