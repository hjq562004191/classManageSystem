package com.example.demo.mapper;

import com.example.demo.domain.HourList;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @Author JQiang
 * @create 2021/3/15 21:02
 */
@Mapper
public interface SignMapper {

    @Insert("insert into hourlist ( hour_id,student_id, sign ) values (#{hourId}, #{studentId}, #{sign})")
    @Results(id = "signMap",value = {@Result(column = "id" ,property = "id",jdbcType= JdbcType.INTEGER),
            @Result(column = "hour_id" ,property = "hourId",jdbcType=JdbcType.INTEGER),
            @Result(column = "student_id" ,property = "studentId",jdbcType=JdbcType.INTEGER),
            @Result(column = "sign" ,property = "sign",jdbcType=JdbcType.VARCHAR)}
    )
    int insertStudentSign(HourList hourList);


    @Select("select * from hourlist where hour_id=#{hourId}")
    @Results(id = "signMap",value = {@Result(column = "id" ,property = "id",jdbcType= JdbcType.INTEGER),
            @Result(column = "hour_id" ,property = "hourId",jdbcType=JdbcType.INTEGER),
            @Result(column = "student_id" ,property = "studentId",jdbcType=JdbcType.INTEGER),
            @Result(column = "sign" ,property = "sign",jdbcType=JdbcType.VARCHAR)}
    )
    List<HourList> getStuSignList(int hourId);

    @Update("UPDATE hourlist SET sign = '1' WHERE hour_id = #{signId} and student_id = #{userId}")
    int setSign(@Param("signId")int signId,@Param("userId")int userId);
}
