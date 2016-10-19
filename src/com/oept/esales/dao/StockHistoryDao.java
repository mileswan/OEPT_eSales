package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.StockHistory;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/7
 * Description: Stock History DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface StockHistoryDao {

	/**
	 * Add new stock_history
	 * @param stock_history stock history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addHistory(StockHistory stock_history) throws Exception;
	/**
	 * Delete stock history by id
	 * @param id stock history's id
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
	public List<StockHistory> getAllHistories() throws Exception;
	/**
	 * Query stock history by id
	 * @param id stock history's id
	 * 
	 * @return return list object of queried stock history data
	 * @throws Exception 
	 */
	public StockHistory getHistoryById(String id) throws Exception;
	/**
	 * Query specified number of stock histories
	 * @param stock_history stock history's object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * @param sortColumn column to sort
	 * @param sortDir asc or desc
	 * 
	 * @return return list object of queried stock histories data
	 * @throws Exception 
	 */
	public List<StockHistory> getHistories(StockHistory stock_history,String start,String limit,String sortColumn, String sortDir) throws Exception;
	void set_jdbc(JdbcTemplate jdbcTemplate);
}
