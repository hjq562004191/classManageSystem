package com.example.demo.domain;

import java.io.Serializable;

/**
 * @Author JQiang
 * @create 2021/3/10 16:49
 */
public class ClassList implements Serializable {
    private ClassPOJO classPOJO;
    private String teachers;

    public ClassPOJO getClassPOJO() {
        return classPOJO;
    }

    public void setClassPOJO(ClassPOJO classPOJO) {
        this.classPOJO = classPOJO;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "ClassList{" +
                "classPOJO=" + classPOJO +
                ", teachers='" + teachers + '\'' +
                '}';
    }
}
