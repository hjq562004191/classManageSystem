package com.example.demo.domain;

import java.io.Serializable;

/**
 * @Author JQiang
 * @create 2021/5/15 11:10
 */
public class PassHour implements Serializable {
    private int id;
    private String name;
    private int creatTime;
    private int signTime;
    private int realHour;
    private String Switch;
    private String Title;

    public PassHour(int id,String name,  String title,int creatTime, int signTime, int realHour, String Switch) {
        this.id = id;
        this.Title = title;
        this.name = name;
        this.creatTime = creatTime;
        this.signTime = signTime;
        this.realHour = realHour;
        this.Switch = Switch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(int creatTime) {
        this.creatTime = creatTime;
    }

    public int getSignTime() {
        return signTime;
    }

    public void setSignTime(int signTime) {
        this.signTime = signTime;
    }

    public int getRealHour() {
        return realHour;
    }

    public void setRealHour(int realHour) {
        this.realHour = realHour;
    }

    public String getSwitch() {
        return Switch;
    }

    public void setSwitch(String aSwitch) {
        Switch = aSwitch;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return "PassHour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creatTime=" + creatTime +
                ", signTime=" + signTime +
                ", realHour=" + realHour +
                ", Switch='" + Switch + '\'' +
                '}';
    }
}
