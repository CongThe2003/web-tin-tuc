package com.tuhocjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.tuhocjavaweb.dao.ICategoryDAO;
import com.tuhocjavaweb.model.CategoryModel;
import com.tuhocjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService {

// Gọi đến findAll() bên CategoryDAO
	
	/*Cách cũ nhược điểm: nếu như có nhiều class implements 
	1 interface thì ta sẽ phải khai báo nhiều lần new tênClass()*/
	/*
	 * private ICategoryDAO categoryDAO;
	 * 
	 * public public CategoryService() { categoryDAO = new CategoryDAO(); }
	 */

	/*Cách mới: Khi sử dụng Inject thì ta ko cần khai báo class
	ta sử dụng phương thức ở class nào thì nó sẽ tự tìm*/
	@Inject
	private ICategoryDAO categoryDAO;

	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

}
