package com.example.demo.common;

import lombok.Data;

import java.io.Serializable;


/**
 * @author liusq
 */
@Data
public class Result<T> implements Serializable {

    // コード：1は成功、0および他の数字は失敗
    private Integer code;
    // エラーメッセージ
    private String msg;
    // データ
    private T data;

    // 成功結果を作成します
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }

    // データを含む成功結果を作成します
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    // エラーメッセージを含む失敗結果を作成します
    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}