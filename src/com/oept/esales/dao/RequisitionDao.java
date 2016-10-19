package com.oept.esales.dao;

import java.util.List;

import com.oept.esales.model.OrderReqProdCounts;
import com.oept.esales.model.RequisitionFlat;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/8
 * Description: Requisition and requisition items DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface RequisitionDao {

	/**
	 * Add new requisition
	 * @param requisition requisition's object
	 * 
	 * @return return new requisition's id
	 * @throws Exception 
	 */
	public String addRequisition(RequisitionFlat requisition) throws Exception;
	/**
	 * Update requisition by requisition object
	 * @param requisition requisition's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateRequisition(RequisitionFlat requisition) throws Exception;
	/**
	 * Update requisition's status by requisition object
	 * @param requisition requisition's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateRequisitionStatus(RequisitionFlat requisition) throws Exception;
	/**
	 * Query all Requisitions data
	 * 
	 * @return return list object of all Requisitions data
	 * @throws Exception 
	 */
	public List<RequisitionFlat> getAllRequisitions() throws Exception;
	/**
	 * Query requisition by id
	 * @param id requisition's id
	 * 
	 * @return return list object of queried requisition data
	 * @throws Exception 
	 */
	public RequisitionFlat getRequisitionById(String id) throws Exception;
	/**
	 * Query recent requisitions updated by user
	 * @param userid user's id
	 * 
	 * @return return list object of recent requisition data
	 * @throws Exception 
	 */
	public List<RequisitionFlat> getRecentRequisitions(String userid) throws Exception;
	/**
	 * Query data
	 * @param req_id req's id
	 * 
	 * @return return list object of all req item data under specified user
	 * @throws Exception 
	 */
	public List<OrderReqProdCounts> getReqProdCounts(String req_id) throws Exception;
	/**
	 * Query data
	 * @param req_id req's id
	 * @param prod_id product's id
	 * 
	 * @return return list object of all req item data under specified user
	 * @throws Exception 
	 */
	public List<OrderReqProdCounts> getReqProdCounts(String req_id,String prod_id) throws Exception;
	/**
	 * Query data
	 * @param order_id order's id
	 * 
	 * @return return list object of all req item data under specified user
	 * @throws Exception 
	 */
	public List<OrderReqProdCounts> getReqProdCountsForOrderId(String order_id) throws Exception;
	/**
	 * Query data
	 * @param order_id order's id
	 * @param prod_id product's id
	 * 
	 * @return return list object of all req item data under specified user
	 * @throws Exception 
	 */
	public List<OrderReqProdCounts> getReqProdCountsForOrderId(String order_id,String prod_id) throws Exception;
	/**
	 * Query specified number of requisitions
	 * @param requisition requisition's object for filter parameters
	 * @param start line number of start
	 * @param limit number of data
	 * @param sortColumn column number to sort
	 * @param sortDir sort direction, asc or desc
	 * 
	 * @return return list object of queried requisition data
	 * @throws Exception 
	 */
	public List<RequisitionFlat> getRequisitions(RequisitionFlat requisition,String start,String limit,String sortColumn, String sortDir) throws Exception;
	/**
	 * Query requisitions for approver
	 * @param approver_id approver's id
	 * @param start line number of start
	 * @param limit number of data
	 * @param sortColumn column number to sort
	 * @param sortDir sort direction, asc or desc
	 * 
	 * @return return list object of queried requisition data
	 * @throws Exception 
	 */
	public List<RequisitionFlat> getRequisitionsForApprover(RequisitionFlat requisition,String approver_id,String start,String limit,String sortColumn, String sortDir) throws Exception;
	/**
	 * Add new requisition item
	 * @param requisition requisition's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addRequisitionItem(RequisitionFlat requisition) throws Exception;
	/**
	 * Delete Requisition item by id
	 * @param id Requisition's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delRequisitionItemById(String id) throws Exception;
	/**
	 * Update Requisition item by Requisition object
	 * @param requisition Requisition's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateRequisitionItem(RequisitionFlat requisition) throws Exception;
	/**
	 * Query all requisitions item data for specified order
	 * @param requisitionId Requisition's id
	 * 
	 * @return return list object of all Requisitions data
	 * @throws Exception 
	 */
	public List<RequisitionFlat> getItemsByReqId(String requisitionId) throws Exception;
	/**
	 * Query specified item data
	 * @param id item's id
	 * 
	 * @return return list object of item data
	 * @throws Exception 
	 */
	public RequisitionFlat getItemsById(String id) throws Exception;
	/**
	 * Update requisition's amounts by Requisition object
	 * @param requisition Requisition's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	boolean updateRequisitionAmounts(RequisitionFlat requisition) throws Exception;
	/**
	 * Update requisition's shipping address by Requisition object
	 * @param requisition Requisition's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	boolean updateRequisitionShipAddr(RequisitionFlat requisition) throws Exception;
	/**
	 * Update requisition's billing address by Requisition object
	 * @param requisition Requisition's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	boolean updateRequisitionBillAddr(RequisitionFlat requisition) throws Exception;
}
