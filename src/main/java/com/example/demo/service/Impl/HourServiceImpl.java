package com.example.demo.service.Impl;

import com.example.demo.domain.ClassHour;
import com.example.demo.domain.Student;
import com.example.demo.mapper.HourMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.HourService;
import com.example.demo.utils.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author JQiang
 * @create 2021/3/13 20:54
 */
@Service
public class HourServiceImpl implements HourService {

    @Resource
    HourMapper hourMapper;
    @Resource
    StudentMapper studentMapper;

    @Override
    public ResultModel createHourSign(ClassHour classHour) {
        Date date = new Date();
        classHour.setCreateTime((int) (date.getTime()/1000));


        return null;
    }

    @Override
    public ResultModel getClassHourList(int page, int pageSize, String phoneNumber) {
        Student student = studentMapper.findStudentByPhone(phoneNumber);
        List<ClassHour> list = hourMapper.getClassHourList(pageSize, (page - 1) * pageSize,student.getClassName());
        int now = (int) (System.currentTimeMillis()/1000);

        List<ClassHour> first = new LinkedList<>();
        List<ClassHour> sedcond = new LinkedList<>();
        for (ClassHour c :
                list) {
            int sed = TimeUtil.minToSecond(c.getEndTime());
            if (c.getIslock().equals("0")||c.getCreateTime() + sed < now){
                sedcond.add(c);
                c.setIslock("1");
                hourMapper.setLock(c.getId());
            }else {
                first.add(c);
            }
        }
        return ResultBuilder.getSuccess(first.size(),first,"获取学生签到列表成功");
    }
}
