package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ASUS
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    // 修改
    @Update("update user set id = #{id} ,name= #{name},age = #{age},status =#{status},sex =#{sex}," +
            "address =#{address},phone =#{phone},update_Time =#{updateTime} where id = #{id}")
    int update(User user);

    // 删除
    @Delete("delete from user where id = #{id} ")
    int deleteById(Long id);

    @Select("SELECT * FROM user WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<User> search(String name);

    @Select("select count(*) from user")
    Long count();

    @Select("select * from user limit #{start}, #{pageSize}")
    List<User> page(Integer start, Integer pageSize);

    List<User> list(Long id, String name, Integer age, String status, String sex, String address, String phone);


}
