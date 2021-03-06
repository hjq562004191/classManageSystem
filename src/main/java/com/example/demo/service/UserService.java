package com.example.demo.service;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.model.ResultModel;

/**
 * @Author JQiang
 * @create 2021/3/3 9:08
 */

public interface UserService {
    //code是短信验证码
    ResultModel studentRegister(Student student);

    ResultModel teacherRegister(Teacher teacher,String code);

    ResultModel adminRegister(Admin admin, String code);

    ResultModel studentLogin(String phone,String passWord);

    ResultModel teacherLogin(String phone,String passWord);

    ResultModel adminLogin(String phone,String passWord);

    ResultModel changePassWord(String phoneNumber,String code,String passWord,String sedPassWord);
}
