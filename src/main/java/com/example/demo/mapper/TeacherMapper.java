package com.example.demo.mapper;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
            @Result(column = "college" ,property = "college"),
            @Result(column = "phone_number" ,property = "phoneNumber"),
            @Result(column = "class_name" ,property = "className"),
            @Result(column = "class_hour" ,property = "classHour"),
            @Result(column = "author_lock" ,property = "authorLock"),
            @Result(column = "teacher_pass_word" ,property = "teacherPassWord")}
    )
    Teacher findTeacherByPhone(String phone);

    @Update("UPDATE teacher SET teacher_name = #{teacherName}," +
            "college = #{college},phone_number = #{phoneNumber} WHERE id = #{id}")
    @ResultMap("teacherMap")
    int changeTeacher(Teacher teacher);

    @Update("UPDATE teacher SET class_hour = 0 WHERE phone_Number = #{phone}")
    int clearClassHour(String phone);

    @Select("SELECT * from teacher limit #{page} offset #{pageSize}")
    @ResultMap("teacherMap")
    List<Teacher> getTeacherList(@Param("page") int page, @Param("pageSize")int pageSize);

    @Select("SELECT * from teacher where class_hour > 0 limit #{page} offset #{pageSize}")
    @ResultMap("teacherMap")
    List<Teacher> getClassHourList(@Param("page") int page, @Param("pageSize")int pageSize);

    @Update("UPDATE teacher SET author_lock = #{authorLock}  WHERE  phone_number = #{phoneNumber} ")
    int changeTeacherAuthor(@Param("authorLock")String authorLock,@Param("phoneNumber")String phoneNumber);

    @Update("UPDATE teacher SET class_hour = (class_hour + #{classHour}) WHERE  id=#{id}")
    int addClassHour(@Param("id")int id,@Param("classHour")int classHour);

    @Update("UPDATE teacher SET class_hour = (class_hour - #{classHour}) WHERE  id=#{id}")
    int jianClassHour(@Param("id")int id,@Param("classHour")int classHour);
}
