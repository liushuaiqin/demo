package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * ユーザーエンティティクラス
 * @author liusq
 */
@Data
public class User {

    /** ユーザーID */
    private Long id;

    /** ユーザー名 */
    private String name;

    /** 年齢 */
    private Integer age;

    /** ステータス */
    private Integer status;

    /** 性別 */
    private String sex;

    /** 住所 */
    private String address;

    /** 電話番号 */
    private String phone;

    /** パスワード */
    private String password;

    /**
     * 作成時間（フォーマット: yyyy-MM-dd HH:mm:ss）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新時間（フォーマット: yyyy-MM-dd HH:mm:ss）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
