package com.example.demo.domain;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
 * @Author JQiang
 * @create 2021/3/2 20:32
 */
public class Admin implements Serializable {
    private int id;

    @NotBlank(message = "姓名不能为空")
    @Length(min = 1, max = 8, message = "姓名必须在1到8为之间")
    private String adminName;  //姓名

    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号必须为11位")
    private String phoneNumber;  //电话

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度必须在6到16个之间")
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
