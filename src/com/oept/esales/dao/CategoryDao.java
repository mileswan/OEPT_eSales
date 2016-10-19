package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.Category;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/13
 * Description: Categories DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface CategoryDao {

	/**
	 * Add new category
	 * @param category category's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addCategory(Category category) throws Exception;
	/**
	 * Delete category by id
	 * @param id category's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delCategoryById(String id) throws Exception;
	/**
	 * Update category by id
	 * @param category category's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateCategory(Category category) throws Exception;
	/**
	 * Query all categories data
	 * 
	 * @return return list object of all categories data
	 * @throws Exception 
	 */
	public List<Category> getAllCategories() throws Exception;
	/**
	 * Query category by id
	 * @param id category's id
	 * 
	 * @return return list object of queried category data
	 * @throws Exception 
	 */
	public Category getCategoryById(String id) throws Exception;
	/**
	 * Query parent category by id
	 * @param id category's id
	 * 
	 * @return return list object of queried category data
	 * @throws Exception 
	 */
	public Category getParentCatById(String id) throws Exception;
	/**
	 * Query specified number of categories
	 * @param category category's object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * 
	 * @return return list object of queried category data
	 * @throws Exception 
	 */
	public List<Category> getCategories(Category category,String start,String limit) throws Exception;
	void set_jdbc(JdbcTemplate jdbcTemplate);
	
	
	/**
	 * Conditions Query Category
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public List<Category> queryCategories(Category category) throws Exception;
	
	/**
	 * Query category subclass
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public boolean queryCategoriesSubclass(Category category) throws Exception;
}
