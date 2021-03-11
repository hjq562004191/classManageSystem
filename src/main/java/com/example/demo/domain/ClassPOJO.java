package com.example.demo.domain;

import java.io.Serializable;

/**
 * @Author JQiang
 * @create 2021/3/9 22:10
 */
public class ClassPOJO implements Serializable {
    private int id;
    private String className;
    private String teacherId;
    private int total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ClassPOJO{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", total=" + total +
                '}';
    }
}
