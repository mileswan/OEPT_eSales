package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.Shopcart;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/26
 * Description: Shopping cart DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface ShopcartDao {
	/**
	 * Add new shopping cart item
	 * @param item Shopcart's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addItem(Shopcart item) throws Exception;
	/**
	 * Delete shopping cart item by id
	 * @param id shopping cart item's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delItemById(String id) throws Exception;
	/**
	 * Update shopping cart item  by id
	 * @param item shopping cart item 's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateItem(Shopcart item) throws Exception;
	/**
	 * Query all shopping cart items data
	 * 
	 * @return return list object of all shopping cart items data
	 * @throws Exception 
	 */
	public List<Shopcart> getAllItems() throws Exception;
	/**
	 * Query shopping cart items by user id
	 * @param userid user's id
	 * 
	 * @return return list object of queried shopping cart items data
	 * @throws Exception 
	 */
	public List<Shopcart> getItemsByUserId(String userid) throws Exception;
	/**
	 * Query shopping cart items by user id
	 * @param userid user's id
	 * @param productid product id
	 * 
	 * @return return list object of queried shopping cart items data
	 * @throws Exception 
	 */
	public List<Shopcart> getItemsByUserProdId(String userid,String productid) throws Exception;
	/**
	 * Query shopping cart item by item id
	 * @param itemid shopping cart item's id
	 * 
	 * @return return list object of queried shopping cart item data
	 * @throws Exception 
	 */
	public Shopcart getItemById(String itemid) throws Exception;
	/**
	 * Query specified number of shopping cart items
	 * @param category category's object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * 
	 * @return return list object of queried shopping cart items data
	 * @throws Exception 
	 */
	public List<Shopcart> getItems(Shopcart item,String start,String limit) throws Exception;
	void set_jdbc(JdbcTemplate jdbcTemplate);
}
