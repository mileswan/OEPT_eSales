package com.oept.esales.service;

import java.util.List;

import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.Attachment;
import com.oept.esales.model.OrderReqProdCounts;
import com.oept.esales.model.RequisitionFlat;
import com.oept.esales.model.RequisitionHistory;
import com.oept.esales.model.StockHistory;
import com.oept.esales.model.WarehouseFlat;
import com.oept.esales.model.WarehouseStock;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Inventory business service interface.
 * Copyright (c) 2015 mwan. All rights reserved.
 */
public interface InventoryService {
	/**
	 * Add new warehouse service
	 * @param warehouse warehouse's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addWarehouse(WarehouseFlat warehouse) throws Exception;
	/**
	 * Add new attachment file
	 * @param file attachment's file object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addAttachment(Attachment file) throws Exception;
	/**
	 * Query Attachments data by product id
	 * @param id req's id
	 * 
	 * @return return list object of queried Attachments data
	 * @throws Exception 
	 */
	public List<Attachment> getAttachmentsByRegId(String id) throws Exception;
	/**
	 * Delete Attachment by id
	 * @param id Attachment's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	boolean delAttachmentById(String id) throws Exception;
	/**
	 * Update warehouse service
	 * @param warehouse warehouse's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateWarehouse(WarehouseFlat warehouse) throws Exception;
	/**
	 * Delete warehouse by id
	 * @param id warehouse's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delWarehouseById(String id) throws Exception;
	/**
	 * Query warehouse by id
	 * @param id warehouse's id
	 * 
	 * @return return list object of queried warehouse data
	 * @throws Exception 
	 */
	public WarehouseFlat getWarehouseById(String id) throws Exception;
	/**
	 * Query all warehouses data
	 * 
	 * @return return list object of all warehouses data
	 * @throws Exception 
	 */
	public List<WarehouseFlat> getAllWarehouses() throws Exception;
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
	public List<WarehouseFlat> getWarehouses(WarehouseFlat warehouse,String start,String limit,String sortColumn, String sortDir) throws Exception;
	/**
	 * Query all warehouse items data by warehouse id
	 * @param id warehouse's id
	 * 
	 * @return return list object of all warehouse items data
	 * @throws Exception 
	 */
	public List<WarehouseFlat> getItemsByWarehouseId(String warehouseId) throws Exception;
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
	 * @param user_id current user id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delStockInfoById(String id, String user_id) throws Exception;
	/**
	 * Add new stock_history
	 * @param stock_history stock history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addHistory(StockHistory stock_history) throws Exception;
	/**
	 * Add new approval_history
	 * @param approval_history approval history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addApprovalHistory(ApprovalHistory approval_history) throws Exception;
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
	/**
	 * Add new requisition
	 * @param requisition requisition's object
	 * 
	 * @return return new requisition's id
	 * @throws Exception 
	 */
	public String addRequisition(RequisitionFlat requisition) throws Exception;
	/**
	 * Add new stock in requisition
	 * @param requisition requisition's object
	 * 
	 * @return return new requisition's id
	 * @throws Exception 
	 */
	public String addStockInReq(RequisitionFlat requisition) throws Exception;
	/**
	 * Add new stock out requisition
	 * @param requisition requisition's object
	 * 
	 * @return return new requisition's id
	 * @throws Exception 
	 */
	public String addStockOutReq(RequisitionFlat requisition) throws Exception;
	/**
	 * Add new stock transfer requisition
	 * @param requisition requisition's object
	 * 
	 * @return return new requisition's id
	 * @throws Exception 
	 */
	public String addStockTransferReq(RequisitionFlat requisition) throws Exception;
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
	 * @return return update status
	 * @throws Exception 
	 */
	public String updateRequisitionStatus(RequisitionFlat requisition) throws Exception;
	/**
	 * Update requisition's status by requisition object facade
	 * @param requisition requisition's object
	 * 
	 * @return return update status
	 * @throws Exception 
	 */
	public String updateRequisitionStatusFacade(RequisitionFlat requisition) throws Exception;
	/**
	 * Query all Requisitions data
	 * 
	 * @return return list object of all Requisitions data
	 * @throws Exception 
	 */
	public List<RequisitionFlat> getAllRequisitions() throws Exception;
	/**
	 * Query recent requisitions updated by user
	 * @param userid user's id
	 * 
	 * @return return list object of recent requisition data
	 * @throws Exception 
	 */
	public List<RequisitionFlat> getRecentRequisitions(String userid) throws Exception;
	/**
	 * Query requisition by id
	 * @param id requisition's id
	 * 
	 * @return return list object of queried requisition data
	 * @throws Exception 
	 */
	public RequisitionFlat getRequisitionById(String id) throws Exception;
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
	public boolean delRequisitionItemById(String id, Object[] params) throws Exception;
	/**
	 * Update Requisition item by Requisition object
	 * @param requisition Requisition's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateRequisitionItem(RequisitionFlat requisition) throws Exception;
	/**
	 * Query specified item data
	 * @param id item's id
	 * 
	 * @return return list object of item data
	 * @throws Exception 
	 */
	public RequisitionFlat getItemsById(String id) throws Exception;
	/**
	 * Query all requisitions item data for specified order
	 * @param requisitionId Requisition's id
	 * 
	 * @return return list object of all Requisitions data
	 * @throws Exception 
	 */
	public List<RequisitionFlat> getItemsByReqId(String requisitionId) throws Exception;
	/**
	 * Query data
	 * @param req_id req's id
	 * 
	 * @return return list object of all req item data under specified user
	 * @throws Exception 
	 */
	public List<OrderReqProdCounts> getReqProdCounts(String req_id) throws Exception;
	/**
	 * Add new requisition history
	 * @param order_history requisition history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addReqHistory(RequisitionHistory requisition_history) throws Exception;
	/**
	 * Delete requisition history by id
	 * @param id order requisition's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delReqHistoryById(String id) throws Exception;
	/**
	 * Query all histories data
	 * 
	 * @return return list object of all histories data
	 * @throws Exception 
	 */
	public List<RequisitionHistory> getAllReqHistories() throws Exception;
	/**
	 * Query order history by id
	 * @param id order history's id
	 * 
	 * @return return list object of queried order history data
	 * @throws Exception 
	 */
	public RequisitionHistory getReqHistoryById(String id) throws Exception;
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
	public List<RequisitionHistory> getReqHistories(RequisitionHistory requisition_history,String start,String limit,String sortColumn, String sortDir) throws Exception;
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
}
