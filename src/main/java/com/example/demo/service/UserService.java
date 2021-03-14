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

    ResultModel teacherRegister(Teacher teacher);

    ResultModel adminRegister(Admin admin);

    ResultModel studentLogin(String phone,String passWord);

    ResultModel teacherLogin(String phone,String passWord);

    ResultModel adminLogin(String phone,String passWord);

    ResultModel changePassWord(String phoneNumber,String code,String passWord,String sedPassWord);

    ResultModel getStudent(String phone);

    ResultModel getTeacher(String phone);

    ResultModel getAdmin(String phone);

    ResultModel getStudentList(int page,int pageSize);

    ResultModel getTeacherList(int page,int pageSize);

    ResultModel getClassHourList(int page,int pageSize);

    ResultModel getStudentListClass(int page,int pageSize,String phone);
    
    ResultModel changeStudent(Student student);

    ResultModel changeTeacher(Teacher teacher);
    
    ResultModel deleteStudent(String phoneNumber);

    ResultModel getTeacherClass(int page, int pageSize, String phone);

    ResultModel clearClassHour(String phone);

    ResultModel changeTeacherAuthor(String phone,String lock);
}
