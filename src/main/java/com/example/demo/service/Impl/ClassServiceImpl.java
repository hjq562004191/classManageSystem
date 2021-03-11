package com.example.demo.service.Impl;

import com.example.demo.domain.ClassList;
import com.example.demo.domain.ClassPOJO;
import com.example.demo.mapper.ClassMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.ClassService;
import com.example.demo.utils.JedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author JQiang
 * @create 2021/3/9 21:00
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Resource
    ClassMapper classMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    StudentMapper studentMapper;

    @Override
    public ResultModel addClass(ClassPOJO classPOJO) {
        ClassPOJO clazz2 = classMapper.getClassByName(classPOJO.getClassName());
        if (clazz2 == null){
            int result = classMapper.addClass(classPOJO);
            if (result == 1){
                return ResultBuilder.getSuccess("创建班级成功");
            }
            return ResultBuilder.getFailure(-1,"创建班级失败");
        }else {
            return ResultBuilder.getFailure(-1,"班级已存在");
        }
    }

    @Override
    public ResultModel allowClass(int classId) {

        return null;
    }

    @Override
    public ResultModel getClassList() {
        List<ClassPOJO> list = classMapper.getClassList();
        return ResultBuilder.getSuccess(list);
    }

    @Override
    public ResultModel getClassAndTeacherList(int page,int pageSize) {
        List<ClassPOJO> list = classMapper.getClassAndTeacherList(pageSize, (page - 1) * pageSize);
        List<ClassList> result = new LinkedList<>();
        for (ClassPOJO c:
                list) {
            String teacherList = c.getTeacherId();
            if (teacherList.contains(";")){
                String teachers = "";
                String[] strings = teacherList.split(";");
                for (String s :
                        strings) {
                    String temp = teacherMapper.findTeacherNameById(Integer.parseInt(s));
                    teachers += (temp+" ");
                }
                ClassList classList = new ClassList();
                classList.setClassPOJO(c);
                classList.setTeachers(teachers);
                result.add(classList);
            }else {
                ClassList classList = new ClassList();
                classList.setClassPOJO(c);
                String teacher = teacherMapper.findTeacherNameById(Integer.parseInt(teacherList));
                classList.setTeachers(teacher);
                result.add(classList);
            }
        }
        return ResultBuilder.getSuccess(result.size(),result,"获取班级列表成功");
    }

    @Override
    public ResultModel addTotal(int classId) {
        int result = classMapper.addTotal(classId);
        if (result == 1){
            return ResultBuilder.getSuccess("total+1成功");
        }
        return ResultBuilder.getFailure(-1,"total+1失败");
    }

    @Override
    public ResultModel joinClass(String classCode,int stuId) {
        if (!JedisUtils.isExists(classCode)){
            return ResultBuilder.getFailure(-1,"班级码错误");
        }else {
            String result = JedisUtils.getToken(classCode);
            int classId = Integer.parseInt(result);
            String className = classMapper.getClassNameById(classId);
            int res = studentMapper.joinClass(stuId, className);
            if (res == 1) {
                classMapper.addTotal(classId);
                return ResultBuilder.getSuccess("加入班级成功");
            }
            return ResultBuilder.getFailure(-1, "加入班级失败");
        }
    }

    @Override
    public ResultModel allowJoinClass(int id) {
        if (JedisUtils.isExists("class_"+String.valueOf(id))){
            String code = JedisUtils.getToken("class_"+String.valueOf(id));
            return ResultBuilder.getSuccess(code,"班级已开放，验证码为"+code);
        }
        int uuid = (int)((Math.random()*9+1)*1000);

        JedisUtils.setToken("class_"+String.valueOf(id),String.valueOf(uuid),1);
        JedisUtils.setToken(String.valueOf(uuid),String.valueOf(id),1);

        return ResultBuilder.getSuccess(uuid);
    }

}
