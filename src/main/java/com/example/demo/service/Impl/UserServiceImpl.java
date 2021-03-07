package com.example.demo.service.Impl;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.JedisUtils;
import com.example.demo.utils.PhoneUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author JQiang
 * @create 2021/3/3 9:24
 */
@Service
@CrossOrigin
public class UserServiceImpl implements UserService {

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    AdminMapper adminMapper;

    @Override
    public ResultModel studentRegister(Student student) {
        Student stu = studentMapper.findStudentByPhone(student.getPhoneNumber());
        //检测账号是否存在
        if (stu == null){
            int result = studentMapper.addStudent(student);
            if (result == 0){
                return ResultBuilder.getFailure(-1,"添加学生失败");
            }
        }else {
            return ResultBuilder.getFailure(-1,"账号已存在");
        }
        return ResultBuilder.getSuccess(0,"注册成功");
    }

    @Override
    public ResultModel teacherRegister(Teacher teacher, String code) {
        Teacher tea = teacherMapper.findTeacherByPhone(teacher.getPhoneNumber());
        //检测账号是否存在
        if (tea == null){
            try {
                if (PhoneUtil.judgeCodeIsTrue(code,teacher.getPhoneNumber())) {
                    int result = teacherMapper.addTeacher(teacher);
                    if (result == 0){
                        return ResultBuilder.getFailure(-1,"添加教师失败");
                    }
                }else {
                    return ResultBuilder.getFailure(-1, "手机验证码错误");
                }
            } catch (Exception e) {
                return ResultBuilder.getFailure(-1, "抛出异常");
            }
        }else {
            return ResultBuilder.getFailure(-1,"账号已存在");
        }
        return ResultBuilder.getSuccess("注册成功");
    }

    @Override
    public ResultModel adminRegister(Admin admin, String code) {
        Admin adm = adminMapper.findAdminByPhone(admin.getPhoneNumber());
        //检测账号是否存在
        if (adm == null){
            try {
                if (PhoneUtil.judgeCodeIsTrue(code,admin.getPhoneNumber())) {
                    int result = adminMapper.addAdmin(admin);
                    if (result == 0){
                        return ResultBuilder.getFailure(-1,"注册管理员失败");
                    }
                }else {
                    return ResultBuilder.getFailure(-1, "手机验证码错误");
                }
            } catch (Exception e) {
                return ResultBuilder.getFailure(-1, "抛出异常");
            }
        }else {
            return ResultBuilder.getFailure(-1,"账号已存在");
        }
        return ResultBuilder.getSuccess("注册成功");
    }

    @Override
    public ResultModel studentLogin(String phone, String passWord) {
        Student stu = studentMapper.findStudentByPhone(phone);
        if (stu == null) {
            return ResultBuilder.getFailure(-1, "用户不存在");
        }
        System.out.println(stu.toString());
        String mPassword = stu.getStudentPassWord();
        if (mPassword.equals(passWord)) {
            String token = "";
            try {
                token = JWTUtils.createToken(stu.getId(), stu.getStudentName(), stu.getPhoneNumber());
                JedisUtils.setToken(String.valueOf(stu.getId()), token, 7);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBuilder.getFailure(-1, "创建token错误");
            }
            return ResultBuilder.getSuccess(token, "登录成功");
        } else {
            return ResultBuilder.getFailure(-1, "密码错误");
        }
    }

    @Override
    public ResultModel teacherLogin(String phone, String passWord) {
        Teacher teacher = teacherMapper.findTeacherByPhone(phone);
        if (teacher == null) {
            return ResultBuilder.getFailure(-1, "用户不存在");
        }

        String mPassword = teacher.getTeacherPassWord();
        if (mPassword.equals(passWord)) {
            String token = "";
            try {
                token = JWTUtils.createToken(teacher.getTeacherId(), teacher.getTeacherName(), teacher.getPhoneNumber());
                JedisUtils.setToken(String.valueOf(teacher.getTeacherId()), token, 7);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBuilder.getFailure(-1, "创建token错误");
            }
            return ResultBuilder.getSuccess(token, "登录成功");
        } else {
            return ResultBuilder.getFailure(-1, "密码错误");
        }
    }

    @Override
    public ResultModel adminLogin(String phone, String passWord) {
        Admin admin = adminMapper.findAdminByPhone(phone);
        if (admin == null) {
            return ResultBuilder.getFailure(-1, "用户不存在");
        }

        String mPassword = admin.getAdminPassWord();
        if (mPassword.equals(passWord)) {
            String token = "";
            try {
                token = JWTUtils.createToken(admin.getAdminId(), admin.getAdminName(), admin.getPhoneNumber());
                JedisUtils.setToken(String.valueOf(admin.getAdminId()), token, 7);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBuilder.getFailure(-1, "创建token错误");
            }
            return ResultBuilder.getSuccess(token, "登录成功");
        } else {
            return ResultBuilder.getFailure(-1, "密码错误");
        }
    }


    @Override
    public ResultModel changePassWord(String phoneNumber, String code, String passWord, String sedPassWord) {
        return null;
    }

    @Override
    public ResultModel getStudent(String phoneNumber) {
        Student stu = studentMapper.findStudentByPhone(phoneNumber);
        System.out.println(stu.toString());
        if (stu == null) {
            return ResultBuilder.getFailure(-1, "用户不存在");
        }
        return ResultBuilder.getSuccess(stu,"获取成功");
    }

    @Override
    public ResultModel getStudentList(int page,int pageSize) {
        List<Student> stus = studentMapper.getStudentList(pageSize,(page-1)*pageSize);
        for (Student s :
                stus) {
            System.out.println(s.toString());
        }
        if (stus == null) {
            return ResultBuilder.getFailure(-1, "获取学生列表失败");
        }
        return ResultBuilder.getSuccess(stus,stus.size()+"");
    }
    @Override
    public ResultModel changeStudent(Student student) {
        int result = studentMapper.changeStudent(student);
        if (result != 1) {
            return ResultBuilder.getFailure(-1, "更新学生信息失败");
        }
        return ResultBuilder.getSuccess(0,"更新学生信息成功");
    }

    @Override
    public ResultModel deleteStudent(int id) {
        Student stu = studentMapper.findStudentById(id);
        if (stu == null) {
            return ResultBuilder.getFailure(-1, "用户不存在");
        }
        boolean result = studentMapper.deleteStudent(id);
        if (result) {
            return ResultBuilder.getFailure(-1, "删除学生信息失败");
        }
        return ResultBuilder.getSuccess(0,"删除学生信息成功");
    }

}
