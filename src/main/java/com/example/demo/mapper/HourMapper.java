package com.example.demo.mapper;

import com.example.demo.domain.ClassHour;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author JQiang
 * @create 2021/3/13 17:08
 */
@Mapper
public interface HourMapper {
    @Select("select * from hour")
    @Results(id = "hourMap",value = {@Result(column = "id" ,property = "id"),
            @Result(column = "teacher_id" ,property = "teacherId"),
            @Result(column = "sign_title" ,property = "signTitle"),
            @Result(column = "class_name" ,property = "className"),
            @Result(column = "create_time" ,property = "createTime"),
            @Result(column = "end_time" ,property = "endTime"),
            @Result(column = "islock" ,property = "islock"),
            @Result(column = "class_hour" ,property = "classHour")}
    )
    List<ClassHour> getClassHour();

    @Select("select * from hour where class_name=#{className} limit #{page} offset #{pageSize}")
    @ResultMap("hourMap")
    List<ClassHour> getClassHourList(@Param("page") int page, @Param("pageSize")int pageSize,
                                     @Param("className")String className);

    @Insert("insert into hour ( teacher_id, sign_title, class_name ,create_time,end_time,class_hour) " +
            "values (#{teacherId}, #{signTitle}, #{className},#{createTime}, #{endTime}, #{classHour})")
    @ResultMap("hourMap")
    int createClassHour(ClassHour clazz);

    @Select("select * from hour where id = #{id}")
    @ResultMap("hourMap")
    ClassHour getClassHourById(int id);

    //班级被删
    @Update("UPDATE hour SET islock = '1' WHERE id = #{id}")
    @ResultMap("hourMap")
    int setLock(int id);

    @Select("select class_name from classes where id = #{id}")
    String getClassNameById(int id);

    @Delete("DELETE from hour WHERE id = #{id}")
    boolean deleteClassHour(int id);
}
