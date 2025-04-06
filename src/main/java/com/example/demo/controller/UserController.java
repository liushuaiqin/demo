package com.example.demo.controller;

import com.example.demo.common.PageBean;
import com.example.demo.common.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    // 查询所有用户
    @GetMapping("/getAll")
    public Result<List<User>> findAll() {
        return Result.success(userService.findAll());
    }

    // 修改用户
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    // 根据id删除用户
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
        return Result.success();
    }

    @GetMapping("/search")
    public Result<List<User>> search(@RequestParam String name) {
        return userService.search(name);
    }

    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "5") Integer pageSize,
                         Long id, String name, Integer age, String status, String sex, String address, String phone) {

        //调用业务层分页查询功能
        PageBean pageBean = userService.page(page, pageSize, id, name, age, status, sex, address, phone);
        //响应
        return Result.success(pageBean);
    }

}

