package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.LoginDao;
import com.oept.esales.model.User;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/06
 * Description: Categories DAO implements.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Repository("loginDao")
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate get_jdbc11() {
		return jdbcTemplate;
	}
	public void set_jdbc11(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 实现接口登录验证
	 */
	@Override
	public User login2(User user) throws Exception {
		// TODO Auto-generated method stub
		//SQL语句
		String sql = "select * from osa_user where osa_username = ? AND osa_password = ?";
		//参数放入对象数组中
		Object[] params = new Object[] {
				user.getUserName(),
				user.getPassword() 
		};
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setUserId(rs.getString("osa_user_id"));
				user.setUserName(rs.getString("osa_username"));
				user.setPassword(rs.getString("osa_password"));
				user.setDelete(rs.getBoolean("osa_delete"));
				user.setPrimaryPositionId(rs.getString("osa_primary_position_id"));
				return user;
			}
		}, params);
	}
	
	/**
	 * 用户登录记录登录时间
	 */
//	@Override
//	public int lastLogin(String username) {
//		// TODO Auto-generated method stub
//		String sql = "update osa_user set osa_last_login = now() where osa_username = ?;";
//		return jdbcTemplate.update(sql, username);
//		
//	}
	@Override
	public int lastLogin(User user) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_user set osa_last_login = now() where osa_username = ?;";
		Object[] params = new Object[]{
				user.getUserId()
		};
		return jdbcTemplate.update(sql, params);
	}
}
