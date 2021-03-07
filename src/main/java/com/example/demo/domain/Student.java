package com.example.demo.domain;


import java.io.Serializable;
/**
 * @Author JQiang
 * @create 2021/3/2 21:02
 */
public class Student implements Serializable {

    private int id;

    private String studentName;  //姓名
    private String classNum;  //班级
    private String studentNum; //学号
    private String phoneNumber;  //电话
    private String studentPassWord;  //密码


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStudentPassWord() {
        return studentPassWord;
    }

    public void setStudentPassWord(String studentPassWord) {
        this.studentPassWord = studentPassWord;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", classNum='" + classNum + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", studentPassWord='" + studentPassWord + '\'' +
                '}';
    }
}
