package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.OrderHistory;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/8
 * Description: Order History DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface OrderHistoryDao {

	/**
	 * Add new order_history
	 * @param order_history order history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addHistory(OrderHistory order_history) throws Exception;
	/**
	 * Delete order history by id
	 * @param id order history's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delHistoryById(String id) throws Exception;
	/**
	 * Query all histories data
	 * 
	 * @return return list object of all histories data
	 * @throws Exception 
	 */
	public List<OrderHistory> getAllHistories() throws Exception;
	/**
	 * Query order history by id
	 * @param id order history's id
	 * 
	 * @return return list object of queried order history data
	 * @throws Exception 
	 */
	public OrderHistory getHistoryById(String id) throws Exception;
	/**
	 * Query specified number of order histories
	 * @param order_history order history's object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * @param sortColumn column to sort
	 * @param sortDir asc or desc
	 * 
	 * @return return list object of queried stock histories data
	 * @throws Exception 
	 */
	public List<OrderHistory> getHistories(OrderHistory order_history,String start,String limit,String sortColumn, String sortDir) throws Exception;
	void set_jdbc(JdbcTemplate jdbcTemplate);
}
