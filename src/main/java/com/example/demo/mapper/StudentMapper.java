package com.example.demo.mapper;

import com.example.demo.domain.Student;
import org.apache.ibatis.annotations.*;

/**
 * @Author JQiang
 * @create 2021/3/2 22:16
 */
@Mapper
public interface StudentMapper {

    @Insert("insert into student(student_name, class_num, student_num, phone_number,student_pass_word)" +
            " values(#{studentName}, #{classNum}, #{studentNum}, #{phoneNumber}, #{studentPassWord})")
    int addStudent(Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    boolean deleteStudent(int id);

    @Select("select * from student where id = #{id}")
    @ResultMap("studentMap")
    Student findStudentById(int id);

    @Select("SELECT student_name from student where id = #{id}")
    String findStudentNameById(int id);

    @Select("select * from student where phone_number = #{phone}")
    @Results(id = "studentMap",value = {@Result(column = "id" ,property = "id"),
            @Result(column = "student_name" ,property = "studentName"),
            @Result(column = "class_num" ,property = "classNum"),
            @Result(column = "student_num" ,property = "studentNum"),
            @Result(column = "phone_number" ,property = "phoneNumber"),
            @Result(column = "student_pass_word" ,property = "studentPassWord")}
    )
    Student findStudentByPhone(String phone);
}
