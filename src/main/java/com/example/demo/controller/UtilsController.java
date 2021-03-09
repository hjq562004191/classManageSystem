package com.example.demo.controller;


import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.UtilsService;
import com.example.demo.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Qiang
 */
@RestController
@RequestMapping("/utils")
@CrossOrigin
public class UtilsController {
    @Autowired
    UtilsService utilsService;
    @Resource
    StudentMapper studentMapper;
    @Resource
    TeacherMapper teacherMapper;

    @RequestMapping(value = "/getPhoneCode", method = POST)
    public ResultModel getphonecode(String phoneNumber, int radio) {
        if (radio == 1) {
            Student stu = studentMapper.findStudentByPhone(phoneNumber);
            //检测账号是否存在
            if (stu != null) {
                return ResultBuilder.getFailure(-1, "账号已存在");
            }
        }else if (radio == 2) {
            Teacher teacher = teacherMapper.findTeacherByPhone(phoneNumber);
            //检测账号是否存在
            if (teacher != null) {
                return ResultBuilder.getFailure(-1, "账号已存在");
            }
        }


        String code = null;
        code = PhoneUtil.getVerificationCode(phoneNumber);
        if (code == null) {
            return ResultBuilder.getFailure(-1, "验证码发送失败!");
        }
        return ResultBuilder.getSuccess(code, "获取成功!");
    }


    @RequestMapping(value = "/judgePhoneCode", method = POST)
    public ResultModel phoneNumberJudge(String code, String phoneNumber) {
        if (PhoneUtil.judgeCodeIsTrue(code, phoneNumber)) {
            return ResultBuilder.getSuccess("验证成功!");
        }
        return ResultBuilder.getFailure(-1, "验证失败!");
    }


    @RequestMapping("/notLoginIn")
    public ResultModel notLoginIn() {
        return utilsService.notLoginIn();
    }

    @RequestMapping("/adminNotLoginIn")
    public ResultModel adminNotLoginIn() {
        return utilsService.adminNotLoginIn();
    }

    @RequestMapping("/logonExpires")
    public ResultModel logonExpires() {
        return utilsService.logonExpires();
    }

    @RequestMapping("/loginException")
    public ResultModel loginException() {
        return utilsService.loginException();
    }

    @RequestMapping("/fileNotAllow")
    public ResultModel fileNotNull() {
        return utilsService.fileNotAllow();
    }

    @RequestMapping("/noJurisdiction")
    public ResultModel noJurisdiction() {
        return utilsService.noJurisdiction();
    }
}
