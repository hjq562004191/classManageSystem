package com.example.demo.domain;

import java.io.Serializable;
/**
 * @Author JQiang
 * @create 2021/3/2 21:30
 */
public class Teacher implements Serializable {
    private int id;

    private String teacherName;  //姓名

    private String phoneNumber;  //电话

    private String teacherPassWord;  //密码

    private int classHour; //学时总长


    public int getTeacherId() {
        return id;
    }

    public void setTeacherId(int id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTeacherPassWord() {
        return teacherPassWord;
    }

    public void setTeacherPassWord(String teacherPassWord) {
        this.teacherPassWord = teacherPassWord;
    }

    public int getClassHour() {
        return classHour;
    }

    public void setClassHour(int classHour) {
        this.classHour = classHour;
    }
}
