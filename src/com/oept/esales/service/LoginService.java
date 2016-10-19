package com.oept.esales.service;

import java.util.Map;

import com.oept.esales.model.User;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/16
 * Description: User login, validation and logout service interface.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public interface LoginService {

	//登录验证
	public User login(User user) throws Exception;
	
	//记录登录时间
	public int lastLogin(User user) throws Exception;
}
