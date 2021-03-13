package com.example.demo.service;

import com.example.demo.domain.ClassPOJO;
import com.example.demo.model.ResultModel;

/**
 * @Author JQiang
 * @create 2021/3/9 20:59
 */
public interface ClassService {
    ResultModel addClass(String className,String phoneNumber);

    ResultModel getClassList();
    ResultModel getClassAndTeacherList(int page,int pageSize);
    ResultModel addTotal(int classId);
    ResultModel joinClass(String classCode,int id);
    ResultModel allowJoinClass(int id,String phoneNumber);

    ResultModel deleteClass(int id ,String name);
}
