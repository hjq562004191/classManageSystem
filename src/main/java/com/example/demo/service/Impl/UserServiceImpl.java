package com.example.demo.service.Impl;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.ClassMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.ResultBuilder;
import com.example.demo.model.ResultModel;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.JedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.util.LinkedList;
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

    @Resource
    ClassMapper classMapper;

    @Override
    public ResultModel studentRegister(Student student) {
        Student stu = studentMapper.findStudentByPhone(student.getPhoneNumber());
        //检测账号是否存在
        if (stu == null) {
            int result = studentMapper.addStudent(student);
            if (result == 0) {
                return ResultBuilder.getFailure(-1, "添加学生失败");
            }
        } else {
            return ResultBuilder.getFailure(-1, "账号已存在");
        }
        return ResultBuilder.getSuccess("注册成功");
    }

    @Override
    public ResultModel teacherRegister(Teacher teacher) {
        Teacher tea = teacherMapper.findTeacherByPhone(teacher.getPhoneNumber());
        //检测账号是否存在
        if (tea == null) {
            int result = teacherMapper.addTeacher(teacher);
            if (result == 0) {
                return ResultBuilder.getFailure(-1, "添加教师失败");
            }
        } else {
            return ResultBuilder.getFailure(-1, "账号已存在");
        }
        return ResultBuilder.getSuccess("注册成功");
    }

    @Override
    public ResultModel adminRegister(Admin admin) {
        Admin adm = adminMapper.findAdminByPhone(admin.getPhoneNumber());
        //检测账号是否存在
        if (adm == null) {
            int result = adminMapper.addAdmin(admin);
            if (result == 0) {
                return ResultBuilder.getFailure(-1, "注册管理员失败");
            }
        } else {
            return ResultBuilder.getFailure(-1, "账号已存在");
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

        if (teacher.getAuthorLock().equals("0")) {
            return ResultBuilder.getFailure(1, "账号未激活");
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
        if (stu == null) {
            return ResultBuilder.getFailure(-1, "用户不存在");
        }
        return ResultBuilder.getSuccess(stu, "获取成功");
    }

    @Override
    public ResultModel getTeacher(String phoneNumber) {
        Teacher teacher = teacherMapper.findTeacherByPhone(phoneNumber);
        if (teacher == null) {
            return ResultBuilder.getFailure(-1, "用户不存在");
        }
        return ResultBuilder.getSuccess(teacher, "获取成功");
    }

    @Override
    public ResultModel getAdmin(String phone) {
        Admin admin = adminMapper.findAdminByPhone(phone);
        if (admin == null) {
            return ResultBuilder.getFailure(-1, "用户不存在");
        }
        return ResultBuilder.getSuccess(admin, "获取成功");
    }

    @Override
    public ResultModel getStudentList(int page, int pageSize) {
        List<Student> stus = studentMapper.getStudentList(pageSize, (page - 1) * pageSize);
        if (stus == null) {
            return ResultBuilder.getFailure(-1, "获取学生列表失败");
        }
        return ResultBuilder.getSuccess(stus.size(), stus, "获取学生列表成功");
    }

    @Override
    public ResultModel getTeacherList(int page, int pageSize) {
        List<Teacher> teachers = teacherMapper.getTeacherList(pageSize, (page - 1) * pageSize);
        if (teachers == null) {
            return ResultBuilder.getFailure(-1, "获取教师列表失败");
        }
        return ResultBuilder.getSuccess(teachers.size(), teachers, "获取教师列表成功");
    }

    @Override
    public ResultModel getClassHourList(int page, int pageSize) {
        List<Teacher> teachers = teacherMapper.getClassHourList(pageSize, (page - 1) * pageSize);
        if (teachers == null) {
            return ResultBuilder.getFailure(-1, "获取教师列表失败");
        }
        return ResultBuilder.getSuccess(teachers.size(), teachers, "获取教师列表成功");
    }

    @Override
    public ResultModel getStudentListClass(int page, int pageSize,String phone) {
        Student student = studentMapper.findStudentByPhone(phone);
        List<Student> stus = studentMapper.getStudentListClass(pageSize, (page - 1) * pageSize,student.getClassName());
        if (stus == null) {
            return ResultBuilder.getFailure(-1, "获取学生列表失败");
        }
        return ResultBuilder.getSuccess(stus.size(), stus, "获取学生列表成功");
    }

    @Override
    public ResultModel changeStudent(Student student) {
        System.out.println(student.toString());
        int result = studentMapper.changeStudent(student);
        if (result != 1) {
            return ResultBuilder.getFailure(-1, "更新学生信息失败");
        }
        return ResultBuilder.getSuccess(0, "更新学生信息成功");
    }

    @Override
    public ResultModel changeTeacher(Teacher teacher) {
        int result = teacherMapper.changeTeacher(teacher);
        if (result != 1) {
            return ResultBuilder.getFailure(-1, "更新学生信息失败");
        }
        return ResultBuilder.getSuccess(0, "更新学生信息成功");
    }

    @Override
    public ResultModel deleteStudent(String phoneNumber) {
        Student stu = studentMapper.findStudentByPhone(phoneNumber);
        if (stu == null) {
            return ResultBuilder.getFailure(-1, "用户不存在");
        }
        boolean result = studentMapper.deleteStudent(phoneNumber);
        if (!result) {
            return ResultBuilder.getFailure(-1, "删除学生信息失败");
        }
        return ResultBuilder.getSuccess(0, "删除学生信息成功");
    }

    @Override
    public ResultModel getTeacherClass(int page, int pageSize, String phone) {
        Teacher teacher = teacherMapper.findTeacherByPhone(phone);
        if (teacher.getClassName().equals("")){
            List<Student> result = new LinkedList<>();
            return ResultBuilder.getSuccess(result,"教师无学生");
        }
        if (teacher.getClassName().contains(";")) {
            String[] strings = teacher.getClassName().split(";");
        List<Student> result = new LinkedList<>();
        for (String className :
                strings) {
            List<Student> stus = studentMapper.getStudentListClass(pageSize, (page - 1) * pageSize,className);
            result.addAll(stus);
        }
        return ResultBuilder.getSuccess(result.size(),result,"获取教师学生成功");
        }
        List<Student> stus = studentMapper.getStudentListClass(pageSize, (page - 1) * pageSize,teacher.getClassName());
        return ResultBuilder.getSuccess(stus.size(),stus,"获取教师学生成功");
    }

    @Override
    public ResultModel clearClassHour(String phone) {
        int res = teacherMapper.clearClassHour(phone);
        if (res == 1){
            return ResultBuilder.getSuccess("课时费结算成功");
        }
        return ResultBuilder.getFailure(-1,"课时费结算失败");
    }

    @Override
    public ResultModel changeTeacherAuthor(String phone, String lock) {
        int res = teacherMapper.changeTeacherAuthor(lock,phone);
        if (res == 1){
            return ResultBuilder.getSuccess("权限更改成功");
        }
        return ResultBuilder.getFailure(-1,"权限更改失败");
    }


}
