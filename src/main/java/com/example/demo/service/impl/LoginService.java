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
 * ログインサービス実装クラス
 * @author liusq
 */
@Service
public class LoginService implements ILoginService{

    // ロガーの初期化
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    // UserMapperのインスタンスを注入
    @Resource
    private LoginMapper loginMapper;

    /**
     * ユーザーログイン処理
     * @param name ユーザー名
     * @param password パスワード
     * @return ログイン結果（成功時はUserオブジェクト、失敗時はエラーメッセージ）
     * 処理フロー：
     * 1. ログイン試行ログ記録
     * 2. Mapperを通じてDB認証処理実行
     * 3. 認証成功時：ユーザーステータスチェック
     * 4. ステータス正常時：成功レスポンス返却
     * 5. いずれかのチェックで失敗時：エラーレスポンス返却
     */
    @Override
    public Result<User> login(String name, String password) {
        // ログイン試行ログ（パスワードは***でマスク）
        log.info("ユーザーログイン試行: ユーザー名=[{}], パスワード=[***]", name);
        // データアクセス層で認証処理実行
        User user = loginMapper.login(name, password);
        // 認証失敗時の処理
        if (user == null) {
            log.warn("ログイン失敗: ユーザー名またはパスワードが間違っています");
            return Result.error("お名前またはパスワードが正しくありません。ご確認後、また利用してください。");
        }

        // ユーザーステータスチェック（1=有効、その他=無効）
        log.info("ログイン成功: ユーザーID=[{}], ユーザー名=[{}]", user.getId(), user.getName());
        if (user.getStatus() != 1) {
            log.warn("ユーザー状態異常: ユーザーID=[{}], 状態=[{}]", user.getId(), user.getStatus());
            return Result.error("ご登録した会員が利用できません。管理員へご連絡ください。");
        }
        // 最終的に成功レスポンスを返却
        return Result.success(user);
    }

    /**
     * ユーザー追加処理
     * @param user 追加するユーザーオブジェクト
     * 処理内容：
     * 1. ユーザーオブジェクトに自動的に作成日時/更新日時をセット
     * 2. Mapperを通じてDBにユーザー情報を登録
     * 3. 登録完了ログ記録
     */
    @Override
    public void add(User user) {
        // ユーザー追加操作を記録
        log.info("ユーザー追加: ユーザー名=[{}]", user.getName());
        // 自動的に作成日時と更新日時をセット（現在時刻）
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        // データアクセス層でユーザー登録実行
        loginMapper.add(user);
        // 登録完了ログ（自動生成されたIDを記録）
        log.info("ユーザー追加完了: ユーザーID=[{}]", user.getId());
    }

}
