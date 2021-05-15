package com.example.demo.service;

import com.example.demo.domain.ClassHour;
import com.example.demo.model.ResultModel;


/**
 * @Author JQiang
 * @create 2021/3/13 20:52
 */

public interface HourService {
    ResultModel createHourSign(ClassHour classHour);
    ResultModel getClassHourList(String phoneNumber,int page,int pageSize);
    ResultModel getTeacherClassHourList(String phoneNumber,int page,int pageSize);
    ResultModel deleteSign(int id);
    ResultModel getStudentSign(int hourId);
    ResultModel getAllClassHourList(int page,int pageSize);
    ResultModel signStudent(int signId,int userId);
    ResultModel changeHourSwitch(int hourId, String aSwitch);
    ResultModel deleteHourById(int id);
}
