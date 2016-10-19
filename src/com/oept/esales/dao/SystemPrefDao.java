package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.SystemPreference;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/7
 * Description: System preference DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface SystemPrefDao {

	/**
	 * Update preference by code
	 * @param systemPref system preference's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updatePrefByCode(SystemPreference systemPref) throws Exception;
	/**
	 * Get system preference by code
	 * @param code system preference's code
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public SystemPreference getPrefByCode(String code) throws Exception;
	/**
	 * Get current data source info
	 * 
	 * @return return data source name
	 * @throws Exception 
	 */
	public String getDataSourceName() throws Exception;
	/**
	 * Query all system preferences data
	 * 
	 * @return return list object of all system preferences data
	 * @throws Exception 
	 */
	public List<SystemPreference> getAllPreferences() throws Exception;
	void set_jdbc(JdbcTemplate jdbcTemplate);
}
