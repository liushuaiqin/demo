package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ASUS
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {

	//总记录数
	private Long total;

	//当前页数据列表
	private List rows;

}