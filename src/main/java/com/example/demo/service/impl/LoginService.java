package com.example.demo.service.impl;

import com.example.demo.common.Result;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.ILoginService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @author ASUS
 */
@Service
public class LoginService implements ILoginService{

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    @Resource
    private LoginMapper loginMapper;

    @Override
    public Result<User> login(String name, String password) {
        log.info("ユーザーログイン試行: ユーザー名=[{}], パスワード=[***]", name);
        User user = loginMapper.login(name, password);
        if (user == null) {
            log.warn("ログイン失敗: ユーザー名またはパスワードが間違っています");
            return Result.error("お名前またはパスワードが正しくありません。ご確認後、また利用してください。");
        }

        // 新增用户状态校验
        log.info("ログイン成功: ユーザーID=[{}], ユーザー名=[{}]", user.getId(), user.getName());
        if (user.getStatus() != 1) {
            log.warn("ユーザー状態異常: ユーザーID=[{}], 状態=[{}]", user.getId(), user.getStatus());
            return Result.error("ご登録した会員が利用できません。管理員へご連絡ください。");
        }
        return Result.success(user);
    }

    @Override
    public void add(User user) {
        // ユーザー追加操作を記録
        log.info("ユーザー追加: ユーザー名=[{}]", user.getName());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        loginMapper.add(user);
        log.info("ユーザー追加完了: ユーザーID=[{}]", user.getId());
    }

}
