package com.example.demo.domain;

import java.io.Serializable;

/**
 * @Author JQiang
 * @create 2021/3/15 20:31
 */
public class StudentSign implements Serializable {
    private int id;
    private String name;
    private String sign;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentSign(int id, String name, String sign) {
        this.id = id;
        this.name = name;
        this.sign = sign;
    }
}
