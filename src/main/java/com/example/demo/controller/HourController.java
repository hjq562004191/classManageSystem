package com.example.demo.controller;

import com.example.demo.domain.ClassHour;
import com.example.demo.model.ResultModel;
import com.example.demo.service.HourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResultModel getClassHourList(String phoneNumber, int page, int pageSize) {
        return hourService.getClassHourList(phoneNumber, page, pageSize);
    }

    @RequestMapping(value = "/hour/getTeacherClassHourList", method = POST)
    public ResultModel getTeacherClassHourList(String phoneNumber, int page, int pageSize) {
        return hourService.getTeacherClassHourList(phoneNumber, page, pageSize);
    }

    @RequestMapping(value = "/hour/deleteSign", method = POST)
    public ResultModel deleteSign(int id) {
        return hourService.deleteSign(id);
    }

    @RequestMapping(value = "/hour/getStudentSign", method = POST)
    public ResultModel getStudentSign(int hourId) {
        return hourService.getStudentSign(hourId);
    }

    @RequestMapping(value = "/hour/signStudent", method = POST)
    public ResultModel signStudent(int signId,int userId) {
        return hourService.signStudent(signId,userId);
    }

    @RequestMapping(value = "/hour/getAllClassHourList", method = POST)
    public ResultModel getAllClassHourList(int page, int pageSize) {
        return hourService.getAllClassHourList(page, pageSize);
    }

    @RequestMapping(value = "/hour/changeHourSwitch", method = POST)
    public ResultModel changeHourSwitch(int hourId, String Switch) {
        return hourService.changeHourSwitch(hourId,Switch);
    }

    @RequestMapping(value = "/hour/deleteHourById", method = POST)
    public ResultModel deleteHourById(int id) {
        return hourService.deleteHourById(id);
    }

}
