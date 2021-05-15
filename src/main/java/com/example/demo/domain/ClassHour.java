package com.example.demo.domain;

import java.io.Serializable;

/**
 * @Author JQiang
 * @create 2021/3/13 17:06
 */
public class ClassHour implements Serializable {
    private int id;
    private int teacherId;
    private String signTitle;
    private int classHour;
    private String className;
    private int createTime;
    private int endTime;
    private String islock;
    private String teacherSign;
    private int signTime;
    private int realHour;
    private String Switch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getSignTitle() {
        return signTitle;
    }

    public void setSignTitle(String signTitle) {
        this.signTitle = signTitle;
    }

    public int getClassHour() {
        return classHour;
    }

    public void setClassHour(int classHour) {
        this.classHour = classHour;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int creatTime) {
        this.createTime = creatTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getIslock() {
        return islock;
    }

    public void setIslock(String islock) {
        this.islock = islock;
    }

    public String getTeacherSign() {
        return teacherSign;
    }

    public void setTeacherSign(String teacherSign) {
        this.teacherSign = teacherSign;
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

    @Override
    public String toString() {
        return "ClassHour{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", signTitle='" + signTitle + '\'' +
                ", classHour=" + classHour +
                ", className='" + className + '\'' +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                ", islock='" + islock + '\'' +
                ", teacherSign='" + teacherSign + '\'' +
                ", signTime=" + signTime +
                ", realHour=" + realHour +
                ", Switch='" + Switch + '\'' +
                '}';
    }
}
