package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ログインマッパー
 * @author liusq
 */
@Mapper
public interface LoginMapper {

    /**
     * ユーザーログイン処理
     */
    @Select("select * from user where name = #{name} and password = #{password}")
    User login(String name, String password);

    /**
     * ユーザー追加
     */
    @Insert("insert into user (id,name,age,status,sex,address,phone,create_time,update_Time,password) values " +
            "(#{id},#{name},#{age},#{status},#{sex},#{address},#{phone},#{createTime},#{updateTime},#{password})")
    void add(User user);
}
