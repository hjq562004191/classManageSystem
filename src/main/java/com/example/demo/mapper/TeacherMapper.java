package com.example.demo.mapper;

import com.example.demo.domain.Teacher;
import org.apache.ibatis.annotations.*;

/**
 * @Author JQiang
 * @create 2021/3/2 22:16
 */
@Mapper
public interface TeacherMapper {
    @Insert("insert into teacher(teacher_name, phone_number,teacher_pass_word)" +
            " values(#{teacherName}, #{phoneNumber}, #{teacherPassWord})")
    int addTeacher(Teacher teacher);

    @Delete("DELETE FROM teacher WHERE id = #{id}")
    boolean deleteTeacher(int id);

    @Select("select * from teacher where id = #{id}")
    @ResultMap("teacherMap")
    Teacher findTeacherById(int id);

    @Select("SELECT teacher_name from teacher where id = #{id}")
    String findTeacherNameById(int id);
    
    @Select("select * from teacher where phone_number = #{phone}")
    @Results(id = "teacherMap",value = {@Result(column = "id" ,property = "id"),
            @Result(column = "teacher_name" ,property = "teacherName"),
            @Result(column = "phone_number" ,property = "phoneNumber"),
            @Result(column = "teacher_pass_word" ,property = "teacherPassWord")}
    )
    Teacher findTeacherByPhone(String phone);
}
