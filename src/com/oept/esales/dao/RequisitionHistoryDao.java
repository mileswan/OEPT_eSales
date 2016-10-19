package com.oept.esales.dao;

import java.util.List;

import com.oept.esales.model.RequisitionHistory;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/12
 * Description: Requisition History DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface RequisitionHistoryDao {

	/**
	 * Add new requisition history
	 * @param order_history requisition history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addHistory(RequisitionHistory requisition_history) throws Exception;
	/**
	 * Delete requisition history by id
	 * @param id order requisition's id
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
	public List<RequisitionHistory> getAllHistories() throws Exception;
	/**
	 * Query order history by id
	 * @param id order history's id
	 * 
	 * @return return list object of queried order history data
	 * @throws Exception 
	 */
	public RequisitionHistory getHistoryById(String id) throws Exception;
	/**
	 * Query specified number of requisition histories
	 * @param order_history order history's object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * @param sortColumn column to sort
	 * @param sortDir asc or desc
	 * 
	 * @return return list object of queried requisition histories data
	 * @throws Exception 
	 */
	public List<RequisitionHistory> getHistories(RequisitionHistory requisition_history,String start,String limit,String sortColumn, String sortDir) throws Exception;
}
