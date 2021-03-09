package com.example.demo.mapper;

import com.example.demo.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author JQiang
 * @create 2021/3/2 22:16
 */
@Mapper
public interface StudentMapper {

    @Insert("insert into student(student_name, class_num, student_num, phone_number,student_pass_word)" +
            " values(#{studentName}, #{classNum}, #{studentNum}, #{phoneNumber}, #{studentPassWord})")
    @ResultMap("studentMap")
    int addStudent(Student student);

    @Delete("DELETE from student WHERE phone_number = #{phoneNumber}")
    @ResultMap("studentMap")
    boolean deleteStudent(String phoneNumber);

    @Select("select * from student where id = #{id}")
    @ResultMap("studentMap")
    Student findStudentById(int id);

    @Select("SELECT student_name from student where id = #{id}")
    @ResultMap("studentMap")
    String findStudentNameById(int id);

    @Select("SELECT * from student limit #{page} offset #{pageSize}")
    @ResultMap("studentMap")
    List<Student> getStudentList(@Param("page") int page,@Param("pageSize")int pageSize);

    @Select("select * from student where phone_number = #{phone}")
    @Results(id = "studentMap",value = {@Result(column = "id" ,property = "id"),
            @Result(column = "student_name" ,property = "studentName"),
            @Result(column = "class_num" ,property = "classNum"),
            @Result(column = "student_num" ,property = "studentNum"),
            @Result(column = "phone_number" ,property = "phoneNumber"),
            @Result(column = "student_pass_word" ,property = "studentPassWord")}
    )
    Student findStudentByPhone(String phone);

    @Update("UPDATE student SET student_name = #{studentName},student_num = #{studentNum}," +
            "class_num = #{classNum},phone_number = #{phoneNumber} WHERE id = #{id}")
    @ResultMap("studentMap")
    int changeStudent(Student student);

}
