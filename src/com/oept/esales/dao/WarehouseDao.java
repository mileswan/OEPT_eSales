package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.WarehouseFlat;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Warehouse DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface WarehouseDao {

	/**
	 * Add new warehouse
	 * @param warehouse Warehouse's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addWarehouse(WarehouseFlat warehouse) throws Exception;
	/**
	 * Delete warehouse by id
	 * @param id warehouse's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delWarehouseById(String id) throws Exception;
	/**
	 * Update warehouse by id
	 * @param warehouse warehouse's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateWarehouse(WarehouseFlat warehouse) throws Exception;
	/**
	 * Query all warehouses data
	 * 
	 * @return return list object of all warehouses data
	 * @throws Exception 
	 */
	public List<WarehouseFlat> getAllWarehouses() throws Exception;
	/**
	 * Query warehouses by id
	 * @param id warehouses's id
	 * 
	 * @return return list object of queried warehouses data
	 * @throws Exception 
	 */
	public WarehouseFlat getWarehouseById(String id) throws Exception;
	/**
	 * Query specified number of warehouses
	 * @param warehouses warehouses' object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * @param sortColumn column to sort
	 * @param sortDir asc or desc
	 * 
	 * @return return list object of queried warehouses data
	 * @throws Exception 
	 */
	public List<WarehouseFlat> getWarehouses(WarehouseFlat warehouses,String start,String limit,String sortColumn, String sortDir) throws Exception;
	/**
	 * Query warehouse items by warehouse id
	 * @param warehouseId warehouse's id
	 * 
	 * @return return list object of queried warehouse items data
	 * @throws Exception 
	 */
	public List<WarehouseFlat> getItemsByWarehouseId(String warehouseId) throws Exception;
	void set_jdbc(JdbcTemplate jdbcTemplate);
}
