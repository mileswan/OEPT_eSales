package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.Favorite;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/8
 * Description: Favorites DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface FavoriteDao {

	/**
	 * Add new favorite item
	 * @param item favorite's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addFavItem(Favorite item) throws Exception;
	/**
	 * Delete favorite item by id
	 * @param id favorite item's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delFavItemById(String id) throws Exception;
	/**
	 * Delete favorite item by id
	 * @param prod_id selected product's id
	 * @param user_id current user's id
	 * 
	 * @return return true if the user has collected this product
	 * @throws Exception 
	 */
	public boolean checkFavItem(String prod_id, String user_id) throws Exception;
	/**
	 * Query all favorite item data under specified user
	 * @param userid user's id
	 * 
	 * @return return list object of all favorite item  data under specified user
	 * @throws Exception 
	 */
	public List<Favorite> getFavItemsByUserId(String userid) throws Exception;
	/**
	 * Query favorite item by id
	 * @param id favorite item's id
	 * 
	 * @return return list object of queried favorite item data
	 * @throws Exception 
	 */
	public Favorite getFavItemsById(String id) throws Exception;
	void set_jdbc(JdbcTemplate jdbcTemplate);
}
