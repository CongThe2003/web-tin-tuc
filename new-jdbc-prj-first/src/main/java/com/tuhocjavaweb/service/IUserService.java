package com.tuhocjavaweb.service;

import com.tuhocjavaweb.model.UserModel;

public interface IUserService {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
