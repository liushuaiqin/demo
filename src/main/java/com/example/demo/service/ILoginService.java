package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.pojo.User;

/**
 * @author ASUS
 */
public interface ILoginService {

    Result<User> login(String name, String password);

    void add(User user);
}
