package com.example.demo.domain;

import java.io.Serializable;

/**
 * @Author JQiang
 * @create 2021/3/13 18:23
 */
public class HourList implements Serializable {
    private int id;
    private int hourId;
    private int studentId;
    private String sign;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHourId() {
        return hourId;
    }

    public void setHourId(int hourId) {
        this.hourId = hourId;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "HourList{" +
                "id=" + id +
                ", hourId=" + hourId +
                ", studentId=" + studentId +
                ", sign='" + sign + '\'' +
                '}';
    }
}
