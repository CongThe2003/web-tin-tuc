package com.tuhocjavaweb.service.impl;

import javax.inject.Inject;

import com.tuhocjavaweb.dao.IUserDAO;
import com.tuhocjavaweb.model.UserModel;
import com.tuhocjavaweb.service.IUserService;

public class UserService implements IUserService{

	@Inject
	private IUserDAO userDAO;
	
	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
