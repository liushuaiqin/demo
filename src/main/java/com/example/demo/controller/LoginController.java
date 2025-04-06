package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * ログインコントローラ
 * @author ASUS
 */
@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * ログイン処理
     */
    @GetMapping
    public Result<User> login(@RequestParam String name, @RequestParam String password) {
        return loginService.login(name, password);
    }

    /**
     * ユーザー追加
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        loginService.add(user);
        return Result.success();
    }
}
