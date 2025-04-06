package com.example.demo.service.impl;

import com.example.demo.common.PageBean;
import com.example.demo.common.Result;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ASUS
 */
@Service
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        log.info("Executing findAll users operation");
        List<User> result = userMapper.findAll();
        log.info("Found {} users in total", result.size());
        return result;
    }


    @Override
    public void update(User user) {
        log.info("Updating user with ID: {}", user.getId());
        user.setUpdateTime(LocalDateTime.now());
        int affectedRows = userMapper.update(user);
        log.info("Updated {} rows for user ID: {}", affectedRows, user.getId());
    }

    @Override
    public void delete(Long id) {
        log.warn("Deleting user with ID: {}", id);
        int affectedRows = userMapper.deleteById(id);
        log.info("Deleted {} rows for user ID: {}", affectedRows, id);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, Long id, String name, Integer age, String status, String sex,
                         String address, String phone) {
        log.info("Starting pagination query, page: {}, size: {}", page, pageSize);
        PageHelper.startPage(page, pageSize);
        Long count = userMapper.count();

        Integer start = (page - 1) * pageSize;
        List<User> userList = userMapper.page(start, pageSize);

        List<User> empList = userMapper.list(id, name, age, status, sex, address, phone);
        PageInfo<User> p = new PageInfo<>(empList);
        PageBean pageBean = new PageBean(count, userList);

        log.info("Pagination completed, total: {}, current page: {}", count, userList.size());
        return pageBean;
    }

    @Override
    public Result<List<User>> search(String name) {
        log.debug("Starting user search with keyword: {}", name);
        List<User> users = userMapper.search(name);
        log.info("Search completed, found {} matches", users.size());
        return Result.success(users);
    }


}
