package com.example.demo.service;

import com.example.demo.common.PageBean;
import com.example.demo.common.Result;
import com.example.demo.pojo.User;

import java.util.List;

/**
 * @author ASUS
 */
public interface IUserService {

    void update(User user);

    void delete(Long id);

    List<User> findAll();

    Result<List<User>> search(String name);


    PageBean page(Integer page, Integer pageSize, Long id, String name, Integer age, String status, String sex,
                    String address, String phone);


}
