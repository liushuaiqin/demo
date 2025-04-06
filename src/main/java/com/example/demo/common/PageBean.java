package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liusq
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {

	// 総レコード数
	private Long total;

	// 現在のページのデータリスト
	private List rows;

}