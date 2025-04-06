package com.example.demo.service;

import com.example.demo.common.PageBean;
import com.example.demo.common.Result;
import com.example.demo.pojo.User;

import java.util.List;

/**
 * ユーザー管理用サービスインターフェース
 * @author liusq
 */
public interface IUserService {

    /**
     * ユーザー情報更新
     * @param user 更新するユーザーオブジェクト
     */
    void update(User user);

    /**
     * ユーザー削除
     * @param id 削除するユーザーID
     */
    void delete(Long id);

    /**
     * 全ユーザー取得
     * @return ユーザーリスト
     */
    List<User> findAll();


    /**
     * 名前でユーザー検索
     * @param name 検索キーワード（ユーザー名）
     * @return 検索結果（Result<List<User>>）
     */
    Result<List<User>> search(String name);

    /**
     * ページング
     * @param page ページ番号
     * @param pageSize ページサイズ
     * @return ページング結果（PageBean）
     */
    PageBean page(Integer page, Integer pageSize, Long id, String name, Integer age, String status, String sex,
                    String address, String phone);


}
