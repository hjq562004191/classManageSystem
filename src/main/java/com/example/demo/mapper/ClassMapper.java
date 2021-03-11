package com.example.demo.mapper;

import com.example.demo.domain.ClassPOJO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author JQiang
 * @create 2021/3/9 20:45
 */
@Mapper
public interface ClassMapper {

    @Select("select * from classes")
    @Results(id = "classMap",value = {@Result(column = "id" ,property = "id"),
            @Result(column = "class_name" ,property = "className"),
            @Result(column = "teacher_id" ,property = "teacherId"),
            @Result(column = "total" ,property = "total")}
    )
    List<ClassPOJO> getClassList();

    @Select("select * from classes limit #{page} offset #{pageSize}")
    @ResultMap("classMap")
    List<ClassPOJO> getClassAndTeacherList(@Param("page") int page,@Param("pageSize")int pageSize);

    @Insert("insert into classes ( class_name, teacher_id, total ) values (#{className}, #{teacherId}, #{total})")
    @ResultMap("classMap")
    int addClass(ClassPOJO clazz);

    @Select("select * from classes where class_name = #{className}")
    @ResultMap("classMap")
    ClassPOJO getClassByName(String className);

    @Select("select class_name from classes where id = #{id}")
    String getClassNameById(int id);

    @Update("UPDATE classes SET total= (total+1) WHERE id = #{classId}")
    int addTotal(int classId);
}
