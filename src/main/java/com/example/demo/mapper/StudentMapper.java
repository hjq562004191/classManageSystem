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

    @Insert("insert into student(student_name, class_name, student_num, phone_number,student_pass_word)" +
            " values(#{studentName}, #{className}, #{studentNum}, #{phoneNumber}, #{studentPassWord})")
    @ResultMap("studentMap")
    int addStudent(Student student);

    @Delete("DELETE from student WHERE phone_number = #{phoneNumber}")
    boolean deleteStudent(String phoneNumber);

    @Select("select * from student where id = #{id}")
    @ResultMap("studentMap")
    Student findStudentById(int id);

    @Select("SELECT student_name from student where id = #{id}")
    String findStudentNameById(int id);

    @Select("SELECT * from student limit #{page} offset #{pageSize}")
    @ResultMap("studentMap")
    List<Student> getStudentList(@Param("page") int page,@Param("pageSize")int pageSize);

    @Select("SELECT * from student where class_name = #{className} limit #{page} offset #{pageSize}")
    @ResultMap("studentMap")
    List<Student> getStudentListClass(@Param("page") int page,@Param("pageSize")int pageSize,
                                      @Param("className")String className);

    @Select("select * from student where phone_number = #{phone}")
    @Results(id = "studentMap",value = {@Result(column = "id" ,property = "id"),
            @Result(column = "student_name" ,property = "studentName"),
            @Result(column = "class_name" ,property = "className"),
            @Result(column = "student_num" ,property = "studentNum"),
            @Result(column = "phone_number" ,property = "phoneNumber"),
            @Result(column = "student_pass_word" ,property = "studentPassWord")}
    )
    Student findStudentByPhone(String phone);

    @Update("UPDATE student SET student_name = #{studentName},student_num = #{studentNum}," +
            "class_name = #{className},phone_number = #{phoneNumber} WHERE id = #{id}")
    @ResultMap("studentMap")
    int changeStudent(Student student);

    //加入班级
    @Update("UPDATE student SET class_name = #{className} WHERE id = #{id}")
    @ResultMap("studentMap")
    int joinClass(@Param("id")int id,@Param("className")String className);

    //班级被删
    @Update("UPDATE student SET class_name = '' WHERE class_name = #{className}")
    @ResultMap("studentMap")
    int deleteClass(String className);
}
