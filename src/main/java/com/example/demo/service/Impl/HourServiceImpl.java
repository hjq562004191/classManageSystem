package com.example.demo.service.Impl;

import com.example.demo.domain.*;
import com.example.demo.mapper.HourMapper;
import com.example.demo.mapper.SignMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.HourService;
import com.example.demo.utils.JedisUtils;
import com.example.demo.utils.TimeUtil;
import com.example.demo.utils.TimerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        Teacher teacher = teacherMapper.findTeacherById(classHour.getTeacherId());
        classHour.setSignTitle(classHour.getClassName()+"_"+classHour.getSignTitle());
        int res = hourMapper.createClassSign(classHour);
        if (res == 1){
//            teacherMapper.addClassHour(classHour.getTeacherId(),classHour.getClassHour());
            int hourId = hourMapper.getIdByTime(time);
            List<Student> studentList = studentMapper.getStudentListByClassName(classHour.getClassName());
            for (Student s :
                    studentList) {
                JedisUtils.setToken("stu_message_"+s.getId(),"你的老师"+teacher.getTeacherName()+
                        "刚刚发布了一个关于"+classHour.getClassName()+"的一个签到，持续时间为 "+classHour.getEndTime()+
                        "分钟 ，请及时签到！",1);
                HourList hourList = new HourList();
                hourList.setHourId(hourId);
                hourList.setStudentId(s.getId());
                hourList.setSign("0");
                signMapper.insertStudentSign(hourList);
            }
            //刚创建签到就给学生发送消息
            //定时任务，该到点的时候给老师发送一个消息提醒签退累计时长
            TimerUtil.ScheduledTask(teacher.getId(),classHour.getClassHour());
            return ResultBuilder.getSuccess("创建签到成功");
        }
        return ResultBuilder.getFailure(-1,"创建签到失败");
    }

    @Override
    public ResultModel getClassHourList(String phoneNumber,int page,int pageSize) {
        Student student = studentMapper.findStudentByPhone(phoneNumber);
        List<ClassHour> list = hourMapper.getClassHourList(student.getClassName(),pageSize,
                (page - 1) * pageSize);
        int now = (int) (System.currentTimeMillis()/1000);

        for (ClassHour c :
                list) {
            int sed = TimeUtil.minToSecond(c.getEndTime());
            if (c.getCreateTime() + sed > now){

            }else {
                if (c.getIslock().equals("0") ){
                    c.setIslock("1");
                    hourMapper.setLock(c.getId());
                }
            }
        }
        int total = hourMapper.getClassHourTotal(student.getClassName());
        return ResultBuilder.getSuccess(total,list,"获取学生签到列表成功");
    }

    @Override
    public ResultModel getTeacherClassHourList(String phoneNumber,int page,int pageSize) {
        Teacher teacher = teacherMapper.findTeacherByPhone(phoneNumber);
        List<ClassHour> list = hourMapper.getClassHourListById(teacher.getId(),pageSize,
                (page - 1) * pageSize);

        int now = (int) (System.currentTimeMillis()/1000);

        for (ClassHour c:
                list) {
            int sed = TimeUtil.minToSecond(c.getEndTime());
            if (c.getCreateTime()+sed > now){

            }else {
                if (c.getIslock().equals("0") ){
                    c.setIslock("1");
                    hourMapper.setLock(c.getId());
                }
            }
        }
        int total = hourMapper.getClassHourTotalById(teacher.getId());
        return ResultBuilder.getSuccess(total,list,"获取学生签到列表成功");
    }

    @Override
    public ResultModel deleteSign(int id) {
        ClassHour hour = hourMapper.getClassHourById(id);
        boolean f = hourMapper.deleteSign(id);
        if (f){
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
                StudentSign studentSign = new StudentSign(h.getStudentId(),name,yes);
                first.add(studentSign);
            }else {
                StudentSign studentSign = new StudentSign(h.getStudentId(),name,no);
                second.add(studentSign);
            }
        }
        second.addAll(first);
        return ResultBuilder.getSuccess(second,"获取学生签到列表成功");
    }

    @Override
    public ResultModel getAllClassHourList(int page,int pageSize) {
        List<ClassHour> list = hourMapper.getAllClassHourList(pageSize, (page - 1) * pageSize);
        int size = list.size();
        List<PassHour> list1 = new ArrayList<>(size);
        for (ClassHour c :
                list) {
            String n = teacherMapper.findTeacherNameById(c.getTeacherId());
            list1.add(new PassHour(c.getId(),n,c.getSignTitle(),c.getCreateTime(),c.getSignTime(),c.getRealHour(),
                    c.getSwitch()));
        }
        int total = hourMapper.getAllClassHourTotal();
        return ResultBuilder.getSuccess(total,list1,"获取成功");
    }

    @Override
    public ResultModel signStudent(int signId,int userId) {
        signMapper.setSign(signId,userId);
        return ResultBuilder.getSuccess("成功");
    }

    @Override
    public ResultModel changeHourSwitch(int hourId, String aSwitch) {
        ClassHour classHour = hourMapper.getClassHourById(hourId);
        int res = hourMapper.changeHourSwitch(hourId,aSwitch);
        if (aSwitch.equals("0")){
            teacherMapper.jianClassHour(classHour.getTeacherId(),classHour.getRealHour());
        }else {
            teacherMapper.addClassHour(classHour.getTeacherId(),classHour.getRealHour());
        }
        if (res == 1){
            return ResultBuilder.getSuccess("更改成功");
        }
        return ResultBuilder.getFailure(-1,"更改失败");
    }

    @Override
    public ResultModel deleteHourById(int id) {
        int res = hourMapper.deleteHourById(id);
        if (res == 1){
            return ResultBuilder.getSuccess("更改成功");
        }
        return ResultBuilder.getFailure(-1,"更改失败");
    }

}
