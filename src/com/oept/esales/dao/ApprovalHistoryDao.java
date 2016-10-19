package com.oept.esales.dao;

import java.util.List;

import com.oept.esales.model.ApprovalHistory;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/2/3
 * Description: Approval History DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface ApprovalHistoryDao {

	/**
	 * Add new approval history
	 * @param approval_history approval history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addHistory(ApprovalHistory approval_history) throws Exception;
	/**
	 * Delete approval history by id
	 * @param id approval history's id
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
	public List<ApprovalHistory> getAllHistories() throws Exception;
	/**
	 * Query approval history by id
	 * @param id approval history's id
	 * 
	 * @return return list object of queried approval history data
	 * @throws Exception 
	 */
	public ApprovalHistory getHistoryById(String id) throws Exception;
	/**
	 * Query specified number of order histories
	 * @param approval_history approval history's object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * @param sortColumn column to sort
	 * @param sortDir asc or desc
	 * 
	 * @return return list object of queried approval histories data
	 * @throws Exception 
	 */
	public List<ApprovalHistory> getHistories(ApprovalHistory approval_history,String start,String limit,String sortColumn, String sortDir) throws Exception;
}
