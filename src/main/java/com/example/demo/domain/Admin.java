package com.example.demo.domain;


import java.io.Serializable;
/**
 * @Author JQiang
 * @create 2021/3/2 20:32
 */
public class Admin implements Serializable {
    private int id;

    private String adminName;  //姓名

    private String phoneNumber;  //电话

    private String adminPassWord;  //密码


    public int getAdminId() {
        return id;
    }

    public void setAdminId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdminPassWord() {
        return adminPassWord;
    }

    public void setAdminPassWord(String adminPassWord) {
        this.adminPassWord = adminPassWord;
    }
}
