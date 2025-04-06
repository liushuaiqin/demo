package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.pojo.User;

/**
 * ログイン用サービスインターフェース
 * @author liusq
 */
public interface ILoginService {

    /**
     * ユーザーログイン
     * @param name ユーザー名
     * @param password パスワード
     * @return ログイン結果（Result<User>）
     */
    Result<User> login(String name, String password);

    /**
     * ユーザー追加
     * @param user 追加するユーザーオブジェクト
     */
    void add(User user);
}
