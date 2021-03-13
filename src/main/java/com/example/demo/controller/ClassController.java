package com.example.demo.controller;

import com.example.demo.domain.ClassPOJO;
import com.example.demo.model.ResultModel;
import com.example.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author JQiang
 * @create 2021/3/9 23:19
 */
@RestController
@CrossOrigin
@SuppressWarnings("all")
public class ClassController {
    @Autowired
    ClassService classService;

    @RequestMapping(value = "/class/creatClass", method = POST)
    public ResultModel creatClass(String className,String phoneNumber) {
        return classService.addClass(className,phoneNumber);
    }

    @RequestMapping(value = "/class/getClassList", method = POST)
    public ResultModel getClassList() {
        return classService.getClassList();
    }

    @RequestMapping(value = "/class/getClassAndTeacherList", method = POST)
    public ResultModel getClassAndTeacherList(int page,int pageSize) {
        return classService.getClassAndTeacherList(page,pageSize);
    }

    @RequestMapping(value = "/class/joinClass", method = POST)
    public ResultModel joinClass(String classCode,int id)  {
        return classService.joinClass(classCode,id);
    }

    @RequestMapping(value = "/class/allowJoinClass", method = POST)
    public ResultModel allowJoinClass(int classId,String phoneNumber)  {
        return classService.allowJoinClass(classId,phoneNumber);
    }

    @RequestMapping(value = "/class/deleteClass", method = POST)
    public ResultModel deleteClass(int id,String className){
        return classService.deleteClass(id,className);
    }

}
