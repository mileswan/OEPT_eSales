package com.oept.esales.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.oept.esales.model.Attachment;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/24
 * Description: File DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface FileDao {

	/**
	 * Add new file
	 * @param file file's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addFile(Attachment file) throws Exception;
	/**
	 * Delete category by id
	 * @param id category's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delFileById(String id) throws Exception;
	/**
	 * Update file by id
	 * @param file product file's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateFile(Attachment file) throws Exception;
	/**
	 * Query all files data
	 * 
	 * @return return list object of all files data
	 * @throws Exception 
	 */
	public List<Attachment> getAllFiles() throws Exception;
	/**
	 * Query files data with parameters
	 * @param file
	 * 
	 * @return return list object of all files data
	 * @throws Exception 
	 */
	public List<Attachment> getFiles(Attachment file, String start,
			String limit, String sortColumn, String sortDir) throws Exception;
	/**
	 * Query file by id
	 * @param id file's id
	 * 
	 * @return return list object of queried file data
	 * @throws Exception 
	 */
	public Attachment getFileById(String id) throws Exception;
	/**
	 * Query files by product id
	 * @param id product's id
	 * 
	 * @return return list object of queried file data
	 * @throws Exception 
	 */
	public List<Attachment> getFileByProductId(String id) throws Exception;
	/**
	 * Query files by order id
	 * @param id order's id
	 * 
	 * @return return list object of queried file data
	 * @throws Exception 
	 */
	public List<Attachment> getFileByOrderId(String id) throws Exception;
	/**
	 * Query files by requisition id
	 * @param id requisition's id
	 * 
	 * @return return list object of queried file data
	 * @throws Exception 
	 */
	public List<Attachment> getFileByRequisitionId(String id) throws Exception;
	/**
	 * Query files by contract id
	 * @param id contract's id
	 * 
	 * @return return list object of queried file data
	 * @throws Exception 
	 */
	public List<Attachment> getFileByContractId(String id) throws Exception;
	void set_jdbc(JdbcTemplate jdbcTemplate);
}
