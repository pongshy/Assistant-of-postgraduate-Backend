package com.pongshy.assistant.model;

/**
 * @ClassName: MsgFeeling
 * @Description: 个人心情语句
 * @Author: pongshy
 * @Date: 2021/4/28-15:51
 * @Version: V1.0
 **/
public class MsgFeeling {


    private String q;

    private String mode = "6class";

    public MsgFeeling(String msg) {
        this.q = msg;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
