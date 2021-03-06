package com.example.demo.controller;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author JQiang
 * @create 2021/3/2 21:32
 */
@SuppressWarnings("all")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/regist/studentRegister", method = POST)
    public ResultModel studentRegister(@Valid Student student, BindingResult bindingResult) {
        System.out.println(student.toString());
        // 如果注册的信息有问题
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError objectError : errors) {
                FieldError fieldError = (FieldError) objectError;
                String message = fieldError.getField();
                return ResultBuilder.getFailure(-1, message + ":" + fieldError.getDefaultMessage());
            }
        }
        return userService.studentRegister(student);
    }

    @RequestMapping(value = "/login/studentLogin", method = POST)
    public ResultModel studentLogin(String phone, String passWord) {
        return userService.studentLogin(phone, passWord);
    }

    @RequestMapping(value = "/regist/teacherRegister", method = POST)
    public ResultModel teacherRegister(@Valid Teacher teacher, BindingResult bindingResult) {
        // 如果注册的信息有问题
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError objectError : errors) {
                FieldError fieldError = (FieldError) objectError;
                String message = fieldError.getField();
                return ResultBuilder.getFailure(-1, message + ":" + fieldError.getDefaultMessage());
            }
        }
        return userService.teacherRegister(teacher);
    }

    @RequestMapping(value = "/login/teacherLogin", method = POST)
    public ResultModel teacherLogin(String phone, String passWord) {
        return userService.teacherLogin(phone, passWord);
    }
    @RequestMapping(value = "/regist/adminRegister", method = POST)
    public ResultModel adminRegister(@Valid Admin admin, BindingResult bindingResult) {
        // 如果注册的信息有问题
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError objectError : errors) {
                FieldError fieldError = (FieldError) objectError;
                String message = fieldError.getField();
                return ResultBuilder.getFailure(-1, message + ":" + fieldError.getDefaultMessage());
            }
        }
        return userService.adminRegister(admin);
    }

    @RequestMapping(value = "/login/adminLogin", method = POST)
    public ResultModel adminRogin(String phone, String passWord)  {
        return userService.adminLogin(phone, passWord);
    }

    @RequestMapping(value = "/user/getStudent", method = POST)
    public ResultModel getStudent(String phoneNumber)  {
        return userService.getStudent(phoneNumber);
    }

    @RequestMapping(value = "/user/getTeacher", method = POST)
    public ResultModel getTeacher(String phoneNumber)  {
        return userService.getTeacher(phoneNumber);
    }

    @RequestMapping(value = "/user/getAdmin", method = POST)
    public ResultModel getAdmin(String phoneNumber)  {
        return userService.getAdmin(phoneNumber);
    }

    @RequestMapping(value = "/user/getTeacherList", method = POST)
    public ResultModel getTeacherList(int page,int pageSize)  {
        return userService.getTeacherList(page, pageSize);
    }

    @RequestMapping(value = "/user/getClassHourList", method = POST)
    public ResultModel getClassHourList(int page,int pageSize)  {
        return userService.getClassHourList(page, pageSize);
    }

    @RequestMapping(value = "/user/getStudentList", method = POST)
    public ResultModel getStudentList(int page,int pageSize)  {
        return userService.getStudentList(page, pageSize);
    }

    @RequestMapping(value = "/user/getStudentListClass", method = POST)
    public ResultModel getStudentListClass(int page,int pageSize,String phone)  {
        return userService.getStudentListClass(page, pageSize,phone);
    }

    @RequestMapping(value = "/user/getTeacherClass", method = POST)
    public ResultModel getTeacherClass(int page,int pageSize,String phone)  {
        return userService.getTeacherClass(page, pageSize,phone);
    }

    @RequestMapping(value = "/user/changeStudent", method = POST)
    public ResultModel changeStudent(Student student)  {
        return userService.changeStudent(student);
    }

    @RequestMapping(value = "/user/changeTeacher", method = POST)
    public ResultModel changeTeacher(Teacher teacher)  {
        return userService.changeTeacher(teacher);
    }

    @RequestMapping(value = "/user/deleteStudent", method = POST)
    public ResultModel deleteStudent(String phoneNumber)  {
        return userService.deleteStudent(phoneNumber);
    }

    @RequestMapping(value = "/user/clearClassHour", method = POST)
    public ResultModel clearClassHour(String phoneNumber)  {
        return userService.clearClassHour(phoneNumber);
    }

    @RequestMapping(value = "/user/changeAuthor", method = POST)
    public ResultModel changeTeacherAuthor(String phoneNumber,String authorLock)  {
        return userService.changeTeacherAuthor(phoneNumber,authorLock);
    }

    @RequestMapping(value = "/user/getTeacherById", method = POST)
    public ResultModel getTeacher(int id)  {
        return userService.getTeacherById(id);
    }

    @RequestMapping(value = "/user/deleteTeacherById", method = POST)
    public ResultModel deleteTeacher(int id)  {
        return userService.deleteTeacher(id);
    }
}
