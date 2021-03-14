package com.example.demo.service;

import com.example.demo.domain.ClassHour;
import com.example.demo.model.ResultModel;

/**
 * @Author JQiang
 * @create 2021/3/13 20:52
 */

public interface HourService {
    ResultModel createHourSign(ClassHour classHour);
    ResultModel getClassHourList(int page,int pageSize,String phoneNumber);
}
