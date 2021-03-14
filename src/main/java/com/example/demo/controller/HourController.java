package com.example.demo.controller;

import com.example.demo.domain.ClassHour;
import com.example.demo.mapper.HourMapper;
import com.example.demo.model.ResultModel;
import com.example.demo.service.HourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author JQiang
 * @create 2021/3/13 19:37
 */
@RestController
@CrossOrigin
@SuppressWarnings("all")
public class HourController {
    @Autowired
    HourService hourService;

    @RequestMapping(value = "/hour/createHourSign", method = POST)
    public ResultModel createHourSign(ClassHour classHour) {
        return hourService.createHourSign(classHour);
    }

    @RequestMapping(value = "/hour/getClassHourList", method = POST)
    public ResultModel getClassHourList(int page,int pageSize,String phoneNumber) {
        return hourService.getClassHourList(page,pageSize,phoneNumber);
    }
}
