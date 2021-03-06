package com.example.demo.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
 * @Author JQiang
 * @create 2021/3/2 21:30
 */
public class Teacher implements Serializable {
    private int id;

    @NotBlank(message = "姓名不能为空")
    @Length(min = 1, max = 8, message = "姓名必须在1到8为之间")
    private String teacherName;  //姓名

    private String college;  //学院

    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号必须为11位")
    private String phoneNumber;  //电话

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度必须在6到16个之间")
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

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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
