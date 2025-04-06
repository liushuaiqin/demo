package com.example.demo.controller;

import com.example.demo.common.PageBean;
import com.example.demo.common.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ユーザーコントローラ
 * @author liusq
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 全ユーザー検索
     */
    @GetMapping("/getAll")
    public Result<List<User>> findAll() {
        return Result.success(userService.findAll());
    }

    /**
     * ユーザー更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    /**
     * IDによるユーザー削除
     */
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
        return Result.success();
    }

    /**
     * 名前でユーザー検索
     */
    @GetMapping("/search")
    public Result<List<User>> search(@RequestParam String name) {
        return userService.search(name);
    }

    /**
     * ページ作成
     */
    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "5") Integer pageSize,
                         Long id, String name, Integer age, String status, String sex, String address, String phone) {

        PageBean pageBean = userService.page(page, pageSize, id, name, age, status, sex, address, phone);

        return Result.success(pageBean);
    }

}

