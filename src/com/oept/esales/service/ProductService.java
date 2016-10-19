package com.oept.esales.service;

import java.util.List;

import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.Category;
import com.oept.esales.model.Attachment;
import com.oept.esales.model.Product;
import com.oept.esales.model.ProductCategory;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/16
 * Description: Products business service interface.
 * Copyright (c) 2015 mwan. All rights reserved.
 */
public interface ProductService {
	/**
	 * Add new category service
	 * @param category category's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addCategory(Category category) throws Exception;
	/**
	 * Update category service
	 * @param category category's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateCategory(Category category) throws Exception;
	/**
	 * Delete category by id
	 * @param id category's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delCategoryById(String id) throws Exception;
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
	 * Query all categories data
	 * 
	 * @return return list object of all categories data
	 * @throws Exception 
	 */
	public List<Category> getAllCategories() throws Exception;
	/**
	 * Add new product service
	 * @param product product's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addProduct(Product product) throws Exception;
	/**
	 * Query all products data
	 * 
	 * @return return list object of all products data
	 * @throws Exception 
	 */
	public List<Product> getAllProducts() throws Exception;
	/**
	 * Query available products data
	 * 
	 * @return return list object of all products data
	 * @throws Exception 
	 */
	public List<Product> getAvailProducts() throws Exception;
	/**
	 * Delete product by id
	 * @param product product's object
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delProductById(Product product) throws Exception;
	/**
	 * Publish or unpublish product by id
	 * @param product product's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean pubProductById(Product product) throws Exception;
	/**
	 * Query product by id
	 * @param id product's id
	 * 
	 * @return return object of queried product data
	 * @throws Exception 
	 */
	public Product getProductById(String id) throws Exception;
	/**
	 * Update product service
	 * @param product product's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateProductById(Product product) throws Exception;
	/**
	 * Add new product file
	 * @param file product's file object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addProductFile(Attachment file) throws Exception;
	/**
	 * Query images data by product id
	 * @param id product's id
	 * 
	 * @return return list object of queried images data
	 * @throws Exception 
	 */
	public List<Attachment> getImagesByProdId(String id) throws Exception;
	/**
	 * Delete image by id
	 * @param id image's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	boolean delImageById(String id) throws Exception;
	/**
	 * Update product's images by id
	 * @param file image's file
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	boolean updateProductFile(Attachment file) throws Exception;
	/**
	 * Get product's total count
	 * 
	 * @return return total count
	 * @throws Exception 
	 */
	int getProductsCount() throws Exception;
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
	List<Product> getProducts(Product product, String start, String limit,
			String sortColumn, String sortDir) throws Exception;
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
	 * 读取产品目录最大层级数
	 * @return
	 * @throws Exception
	 */
	public int getProdCatMaxLvl() throws Exception;
	
	
	/**
	 * 根据lvl查询产品类型
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> getProdCatLvlId(Object[] params) throws Exception;
	/**
	 * Add new approval_history
	 * @param approval_history approval history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addApprovalHistory(ApprovalHistory approval_history) throws Exception;
	/**
	 * Query specified number of approval histories
	 * @param approval_history approval history's object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * @param sortColumn column to sort
	 * @param sortDir asc or desc
	 * 
	 * @return return list object of queried approval histories data
	 * @throws Exception 
	 */
	public List<ApprovalHistory> getApprovalHistories(ApprovalHistory approval_history,String start,String limit,String sortColumn, String sortDir) throws Exception;
	/**
	 * Update Product's status service
	 * @param product product's object
	 * 
	 * @return return update message including errors
	 * @throws Exception 
	 */
	public String updateProdStatusById(Product product) throws Exception;
	/**
	 * Update Product's status facade service
	 * @param product product's object
	 * 
	 * @return return update message including errors
	 * @throws Exception 
	 */
	public String updateProdStatusByIdFacade(Product product) throws Exception;
	
	/**
	 * query Add Repetition
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean queryAddRepetition(Product product) throws Exception;
}
