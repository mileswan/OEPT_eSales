package com.oept.esales.dao;

import com.oept.esales.model.User;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/06
 * Description: Categories DAO interface.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
public interface LoginDao {

	/**
	 * 登录验证接口
	 */
	public User login2(User user) throws Exception;
	
	/**
	 * 登录记录用户登录时间
	 */
	public int lastLogin(User user) throws Exception;
	
	
}
