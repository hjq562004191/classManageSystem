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

    @RequestMapping(value = "/user/studentRegister", method = POST)
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

    @RequestMapping(value = "/user/studentLogin", method = POST)
    public ResultModel studentLogin(String phone, String passWord) {
        return userService.studentLogin(phone, passWord);
    }

    @RequestMapping(value = "/user/teacherRegister", method = POST)
    public ResultModel teacherRegister(@Valid Teacher teacher, String code, BindingResult bindingResult) {
        // 如果注册的信息有问题
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError objectError : errors) {
                FieldError fieldError = (FieldError) objectError;
                String message = fieldError.getField();
                return ResultBuilder.getFailure(-1, message + ":" + fieldError.getDefaultMessage());
            }
        }
        return userService.teacherRegister(teacher,code);
    }

    @RequestMapping(value = "/user/teacherLogin", method = POST)
    public ResultModel teacherLogin(String phone, String passWord) {
        return userService.teacherLogin(phone, passWord);
    }
    @RequestMapping(value = "/user/adminRegister", method = POST)
    public ResultModel adminRegister(@Valid Admin admin, String code, BindingResult bindingResult) {
        // 如果注册的信息有问题
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError objectError : errors) {
                FieldError fieldError = (FieldError) objectError;
                String message = fieldError.getField();
                return ResultBuilder.getFailure(-1, message + ":" + fieldError.getDefaultMessage());
            }
        }
        return userService.adminRegister(admin,code);
    }

    @RequestMapping(value = "/user/adminLogin", method = POST)
    public ResultModel adminRogin(String phone, String passWord)  {
        return userService.adminLogin(phone, passWord);
    }

}
