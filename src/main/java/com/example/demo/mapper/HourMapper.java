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

    @Select("select count(id) from hour" )
    int getAllClassHourTotal();

    @Select("select count(*) from hour where class_name=#{className}" )
    int getClassHourTotal(String className);

    @Select("select count(*) from hour where teacher_id=#{teacherId}" )
    int getClassHourTotalById(int teacherId);

    @Select("select * from hour where class_name=#{className} order by id desc limit #{page} offset #{pageSize}")
    @ResultMap("hourMap")
    List<ClassHour> getClassHourList(@Param("className")String className,@Param("page")int page,
                                     @Param("pageSize")int pageSize);

    @Select("select * from hour where teacher_id=#{teacherId} order by id desc limit #{page} offset #{pageSize}")
    @Results(id = "hourMap",value = {@Result(column = "id" ,property = "id"),
            @Result(column = "teacher_id" ,property = "teacherId"),
            @Result(column = "sign_title" ,property = "signTitle"),
            @Result(column = "class_name" ,property = "className"),
            @Result(column = "create_time" ,property = "createTime"),
            @Result(column = "end_time" ,property = "endTime"),
            @Result(column = "islock" ,property = "islock"),
            @Result(column = "teacher_sign" ,property = "teacherSign"),
            @Result(column = "sign_time" ,property = "signTime"),
            @Result(column = "real_class_hour" ,property = "realHour"),
            @Result(column = "switch" ,property = "Switch"),
            @Result(column = "class_hour" ,property = "classHour")}
    )
    List<ClassHour> getClassHourListById(@Param("teacherId")int teacherId,@Param("page")int page,
                                         @Param("pageSize")int pageSize);

    @Insert("insert into hour ( teacher_id, sign_title, class_name ,create_time,end_time,class_hour) " +
            "values (#{teacherId}, #{signTitle}, #{className},#{createTime}, #{endTime}, #{classHour})")
    @ResultMap("hourMap")
    int createClassSign(ClassHour clazz);

    @Select("select * from hour where id = #{id}")
    @ResultMap("hourMap")
    ClassHour getClassHourById(int id);

    @Update("UPDATE hour SET islock = '1' WHERE id = #{id}")
    @ResultMap("hourMap")
    int setLock(int id);

    @Select("select id from hour where create_time = #{time}")
    int getIdByTime(int time);

    @Delete("DELETE from hour WHERE id = #{id}")
    boolean deleteSign(int id);

    @Update("UPDATE hour SET teacher_sign = '1',sign_time=#{signTime},real_class_hour=#{hour} WHERE id = #{id}")
    @ResultMap("hourMap")
    int setTeacherSign(@Param("id")int id,@Param("signTime")int signTime, @Param("hour")int hour);

    @Select("select * from hour order by id desc limit #{page} offset #{pageSize}")
    @ResultMap("hourMap")
    List<ClassHour> getAllClassHourList(@Param("page")int page, @Param("pageSize")int pageSize);

    @Update("UPDATE hour SET switch = #{Switch}  WHERE  id = #{id} ")
    @ResultMap("hourMap")
    int changeHourSwitch(@Param("id")int id,@Param("Switch")String Switch);

    @Delete("DELETE from hour WHERE id = #{id}")
    int deleteHourById(int id);
}
