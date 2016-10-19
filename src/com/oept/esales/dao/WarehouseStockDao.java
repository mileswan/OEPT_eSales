package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.WarehouseStock;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/31
 * Description: Warehouse stock interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface WarehouseStockDao {

	/**
	 * Add new warehouse stock
	 * @param warehouse_stock warehouse stock's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addStockInfo(WarehouseStock warehouse_stock) throws Exception;
	/**
	 * Update warehouse stock by id
	 * @param warehouse_stock warehouse stock's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateStockInfoById(WarehouseStock warehouse_stock) throws Exception;
	/**
	 * Query all warehouse stock  data
	 * 
	 * @return return list object of all warehouse stock  data
	 * @throws Exception 
	 */
	public List<WarehouseStock> getAllStockInfo() throws Exception;
	/**
	 * Query warehouse stock  by id
	 * @param id warehouse stock 's id
	 * 
	 * @return return list object of queried warehouse stock  data
	 * @throws Exception 
	 */
	public WarehouseStock getStockInfoById(String id) throws Exception;
	/**
	 * Query specified number of categories
	 * @param category category's object for query parameters
	 * @param start line number of start
	 * @param limit number of data
	 * @param sortColumn column number to sort
	 * @param sortDir sort direction, asc or desc
	 * 
	 * @return return list object of queried category data
	 * @throws Exception 
	 */
	public List<WarehouseStock> getStockInfos(WarehouseStock warehouse_stock,String start,String limit,String sortColumn, String sortDir) throws Exception;
	/**
	 * Delete warehouse stock  by id
	 * @param id warehouse stock 's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delStockInfoById(String id) throws Exception;
	void set_jdbc(JdbcTemplate jdbcTemplate);
}
