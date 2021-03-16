package com.example.demo.service.Impl;

import com.example.demo.domain.*;
import com.example.demo.mapper.HourMapper;
import com.example.demo.mapper.SignMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.HourService;
import com.example.demo.utils.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author JQiang
 * @create 2021/3/13 20:54
 */
@Service
@SuppressWarnings("all")
public class HourServiceImpl implements HourService {

    @Resource
    HourMapper hourMapper;
    @Resource
    StudentMapper studentMapper;
    @Resource
    TeacherMapper teacherMapper;
    @Resource
    SignMapper signMapper;

    @Override
    public ResultModel createHourSign(ClassHour classHour) {
        int time = (int) (System.currentTimeMillis()/1000);
        classHour.setCreateTime(time);

        int res = hourMapper.createClassSign(classHour);
        if (res == 1){
            teacherMapper.addClassHour(classHour.getTeacherId(),classHour.getClassHour());
            int hourId = hourMapper.getIdByTime(time);
            List<Student> studentList = studentMapper.getStudentListByClassName(classHour.getClassName());
            for (Student s :
                    studentList) {
                HourList hourList = new HourList();
                hourList.setHourId(hourId);
                hourList.setStudentId(s.getId());
                hourList.setSign("0");
                signMapper.insertStudentSign(hourList);
            }
            return ResultBuilder.getSuccess("创建签到成功");
        }
        return ResultBuilder.getFailure(-1,"创建签到失败");
    }

    @Override
    public ResultModel getClassHourList(String phoneNumber) {
        Student student = studentMapper.findStudentByPhone(phoneNumber);
        List<ClassHour> list = hourMapper.getClassHourList(student.getClassName());
        int now = (int) (System.currentTimeMillis()/1000);

        List<ClassHour> first = new LinkedList<>();
        for (ClassHour c :
                list) {
            int sed = TimeUtil.minToSecond(c.getEndTime());
            if (c.getCreateTime() + sed > now){
                first.add(c);
            }else {
                if (c.getIslock().equals("0") ){
                    c.setIslock("1");
                    hourMapper.setLock(c.getId());
                }
            }
        }
        return ResultBuilder.getSuccess(first.size(),first,"获取学生签到列表成功");
    }

    @Override
    public ResultModel getTeacherClassHourList(String phoneNumber) {
        Teacher teacher = teacherMapper.findTeacherByPhone(phoneNumber);
        String[] strings = teacher.getClassName().split(";");
        List<ClassHour> list = new LinkedList<>();
        for (String s :
                strings) {
            List<ClassHour> temp = hourMapper.getClassHourList(s);
            list.addAll(temp);
        }

        int now = (int) (System.currentTimeMillis()/1000);

        List<ClassHour> first = new LinkedList<>();
        for (ClassHour c:
                list) {
            int sed = TimeUtil.minToSecond(c.getEndTime());
            if (c.getCreateTime()+sed > now){
                first.add(c);
            }else {
                if (c.getIslock().equals("0") ){
                    c.setIslock("1");
                    hourMapper.setLock(c.getId());
                }
            }
        }
        return ResultBuilder.getSuccess(first.size(),first,"获取学生签到列表成功");
    }

    @Override
    public ResultModel deleteSign(int id) {
        ClassHour hour = hourMapper.getClassHourById(id);
        boolean f = hourMapper.deleteSign(id);
        if (f){
            teacherMapper.jianClassHour(hour.getTeacherId(),hour.getClassHour());
            return ResultBuilder.getSuccess("删除签到成功");
        }
        return ResultBuilder.getFailure(-1,"删除签到失败");
    }

    @Override
    public ResultModel getStudentSign(int hourId) {
        List<HourList> lists = signMapper.getStuSignList(hourId);
        List<StudentSign> first = new LinkedList<>();
        List<StudentSign> second = new LinkedList<>();
        String no = "未签到";
        String yes = "已签到";
        for (HourList h :
                lists) {
            String name = studentMapper.findStudentNameById(h.getStudentId());
            if (h.getSign().equals("1")){
                StudentSign studentSign = new StudentSign(name,yes);
                first.add(studentSign);
            }else {
                StudentSign studentSign = new StudentSign(name,no);
                second.add(studentSign);
            }
        }
        second.addAll(first);
        return ResultBuilder.getSuccess(second,"获取学生签到列表成功");
    }

    @Override
    public ResultModel getTeacherClassHour(int id) {

        return null;
    }
}
