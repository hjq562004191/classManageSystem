package com.example.demo.mapper;

import com.example.demo.domain.Admin;
import org.apache.ibatis.annotations.*;

/**
 * @Author JQiang
 * @create 2021/3/2 22:15
 */
@Mapper
public interface AdminMapper {
    @Insert("insert into admin(admin_name, phone_number,admin_pass_word)" +
            " values(#{adminName}, #{phoneNumber}, #{adminPassWord})")
    int addAdmin(Admin admin);

    @Delete("DELETE FROM admin WHERE id = #{id}")
    boolean deleteAdmin(int id);

    @Select("select * from admin where id = #{id}")
    @ResultMap("adminMap")
    Admin findAdminById(int id);

    @Select("SELECT admin_name from admin where id = #{id}")
    String findAdminNameById(int id);

    @Select("select * from admin where phone_number = #{phone}")
    @Results(id = "adminMap",value = {@Result(column = "id" ,property = "id"),
            @Result(column = "admin_name" ,property = "adminName"),
            @Result(column = "phone_number" ,property = "phoneNumber"),
            @Result(column = "admin_pass_word" ,property = "adminPassWord")}
    )
    Admin findAdminByPhone(String phone);
}
