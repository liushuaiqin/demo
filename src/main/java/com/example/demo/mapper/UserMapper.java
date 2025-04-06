package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ユーザーマッパー
 * @author liusq
 */
@Mapper
public interface UserMapper {

    /**
     * 全ユーザー情報取得
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * ユーザー情報更新
     */
    @Update("update user set id = #{id} ,name= #{name},age = #{age},status =#{status},sex =#{sex}," +
            "address =#{address},phone =#{phone},update_Time =#{updateTime} where id = #{id}")
    int update(User user);

    /**
     * ユーザー削除
     */
    @Delete("delete from user where id = #{id} ")
    int deleteById(Long id);

    /**
     * ユーザー検索
     */
    @Select("SELECT * FROM user WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<User> search(String name);

    /**
     * ユーザー数カウント
     */
    @Select("select count(*) from user")
    Long count();

    /**
     * ユーザーページング取得
     */
    @Select("select * from user limit #{start}, #{pageSize}")
    List<User> page(Integer start, Integer pageSize);

    /**
     * ユーザーリスト取得
     */
    List<User> list(Long id, String name, Integer age, String status, String sex, String address, String phone);


}
