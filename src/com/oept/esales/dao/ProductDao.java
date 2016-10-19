package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.Product;
import com.oept.esales.model.ProductCategory;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/13
 * Description: Products DAO interface.
 * Copyright (c) 2015 mwan. All rights reserved.
 */
public interface ProductDao {

	/**
	 * Add new product
	 * @param product product's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addProduct(Product product) throws Exception;
	/**
	 * Delete product by id
	 * @param product product's object
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delProductById(Product product) throws Exception;
	/**
	 * Update product by id
	 * @param product product's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateProduct(Product product) throws Exception;
	/**
	 * Query all products data
	 * 
	 * @return return list object of all products data
	 * @throws Exception 
	 */
	public List<Product> getAllProducts() throws Exception;
	/**
	 * Query product by id
	 * @param id product's id
	 * 
	 * @return return list object of queried product data
	 * @throws Exception 
	 */
	public Product getProductById(String id) throws Exception;
	/**
	 * Query specified number of products
	 * @param product product's object for query parameters
	 * @param start line number of start
	 * @param limit number of data
	 * @param sortColumn column number to sort
	 * @param sortDir sort direction, asc or desc
	 * 
	 * @return return list object of queried product data
	 * @throws Exception 
	 */
	public List<Product> getProducts(Product product,String start,String limit,String sortColumn, String sortDir) throws Exception;
	/**
	 * Query specified number of products
	 * @param product product's object for query parameters
	 * @param start approver's id
	 * @param start line number of start
	 * @param limit number of data
	 * @param sortColumn column number to sort
	 * @param sortDir sort direction, asc or desc
	 * 
	 * @return return list object of queried product data
	 * @throws Exception 
	 */
	List<Product> getProductsForApprover(Product product, String approver_id,String start, String limit,
			String sortColumn, String sortDir) throws Exception;
	/**
	 * Publish or unpublish product by id
	 * @param product product's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean pubProductById(Product product) throws Exception;
	
	void set_jdbc(JdbcTemplate jdbcTemplate);
	/**
	 * Count total product records
	 * 
	 * @return return count of total product records
	 * @throws Exception 
	 */
	int getProductsCount() throws Exception;
	/**
	 * Query all products' distinct categories data
	 * @param product available product's search parameters
	 * 
	 * @return return list object of all distinct categories data
	 * @throws Exception 
	 */
	List<Product> getProdDistinctCat(Product availProduct) throws Exception;
	/**
	 * Query all available products data
	 * @param product available product's search parameters
	 * 
	 * @return return list object of all available products data
	 * @throws Exception 
	 */
	List<Product> loadAvailProducts(Product availProduct) throws Exception;
	
	/**
	 * 读取产品目录最大层级数
	 * @return
	 * @throws Exception
	 */
	public int getProdCatMaxLvl() throws Exception;
	
	/**
	 * 读取产品目录
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> getProdCat() throws Exception;
	
	/**
	 * 根据lvl查询产品类型
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> getProdCatLvlId(Object[] params) throws Exception;
	
	/**
	 * query Add Repetition
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean queryAddRepetition(Product product) throws Exception;
}
