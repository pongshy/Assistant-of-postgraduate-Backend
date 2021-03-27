package com.pongshy.assistant.model.entity;

public class tasksDO {
    private Integer id;

    private String taskname;

    private String tag;

    private Integer lft;

    private Integer rgt;

    private Integer maintaskid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    public Integer getRgt() {
        return rgt;
    }

    public void setRgt(Integer rgt) {
        this.rgt = rgt;
    }

    public Integer getMaintaskid() {
        return maintaskid;
    }

    public void setMaintaskid(Integer maintaskid) {
        this.maintaskid = maintaskid;
    }
}