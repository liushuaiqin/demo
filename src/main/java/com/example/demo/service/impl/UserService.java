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
 * ユーザーサービス実装クラス
 * @author liusq
 */
@Service
public class UserService implements IUserService {

    // ロガーの初期化
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    // UserMapperのインスタンスを注入
    @Resource
    private UserMapper userMapper;

    /**
     * 全てのユーザーを取得
     * @return ユーザーリスト
     */
    @Override
    public List<User> findAll() {
        // ログ出力：全てのユーザー情報を取得
        log.info("Executing findAll users operation");
        // UserMapperから全てのユーザーを取得
        List<User> result = userMapper.findAll();
        // ログ出力：取得したユーザー数
        log.info("Found {} users in total", result.size());
        return result;
    }

    /**
     * ユーザー情報を更新
     * @param user 更新するユーザーオブジェクト
     */
    @Override
    public void update(User user) {
        // ログ出力：更新するユーザーID
        log.info("Updating user with ID: {}", user.getId());
        // 更新時間を現在時刻に設定
        user.setUpdateTime(LocalDateTime.now());
        // UserMapperでユーザー情報を更新
        int affectedRows = userMapper.update(user);
        // ログ出力：更新された行数
        log.info("Updated {} rows for user ID: {}", affectedRows, user.getId());
    }

    /**
     * ユーザーを削除
     * @param id 削除するユーザーID
     */
    @Override
    public void delete(Long id) {
        // ログ出力（警告）：削除するユーザーID
        log.warn("Deleting user with ID: {}", id);
        // UserMapperでユーザーを削除
        int affectedRows = userMapper.deleteById(id);
        // ログ出力：削除された行数
        log.info("Deleted {} rows for user ID: {}", affectedRows, id);
    }

    /**
     * ユーザーのページング検索
     * @param page ページ番号
     * @param pageSize ページサイズ
     * @return ページング結果オブジェクト
     */
    @Override
    public PageBean page(Integer page, Integer pageSize, Long id, String name, Integer age, String status, String sex,
                         String address, String phone) {
        // ログ出力：ページングクエリ開始
        log.info("Starting pagination query, page: {}, size: {}", page, pageSize);
        // PageHelperでページングを開始
        PageHelper.startPage(page, pageSize);
        // UserMapperから総ユーザー数を取得
        Long count = userMapper.count();
        // ページングの開始位置を計算
        Integer start = (page - 1) * pageSize;
        // UserMapperからページングされたユーザーリストを取得
        List<User> userList = userMapper.page(start, pageSize);
        List<User> empList = userMapper.list(id, name, age, status, sex, address, phone);
        // PageInfoオブジェクトを作成
        PageInfo<User> p = new PageInfo<>(empList);
        PageBean pageBean = new PageBean(count, userList);
        // ログ出力：ページング完了
        log.info("Pagination completed, total: {}, current page: {}", count, userList.size());
        return pageBean;
    }

    /**
     * ユーザー名による検索
     * @param name 検索キーワード（ユーザー名）
     * @return 検索結果（ユーザーリスト）
     */
    @Override
    public Result<List<User>> search(String name) {
        // ログ出力（デバッグ）：検索開始
        log.debug("Starting user search with keyword: {}", name);
        // UserMapperからユーザー名で検索
        List<User> users = userMapper.search(name);
        // ログ出力：検索完了と一致数
        log.info("Search completed, found {} matches", users.size());
        // 検索結果をResultオブジェクトにセットして返却
        return Result.success(users);
    }


}
