package com.oept.esales.service;

import java.util.List;

import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.Attachment;
import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.OrderHistory;
import com.oept.esales.model.OrderReqProdCounts;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/30
 * Description: Orders business service interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface OrderService {
	/**
	 * Add new order item service
	 * @param order order's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addOrderItem(OrderFlat order) throws Exception;
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
	 * @param id order's id
	 * 
	 * @return return list object of queried Attachments data
	 * @throws Exception 
	 */
	public List<Attachment> getAttachmentsByOrderId(String id) throws Exception;
	/**
	 * Delete Attachment by id
	 * @param id Attachment's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	boolean delAttachmentById(String id) throws Exception;
	/**
	 * Update order service
	 * @param order order's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateOrderItem(OrderFlat order) throws Exception;
	/**
	 * Delete order item by id
	 * @param id order item's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delOrderItemById(String id, Object[] params) throws Exception;
	/**
	 * Query all order items data by order id
	 * @param orderId order's id
	 * 
	 * @return return list object of all order items data
	 * @throws Exception 
	 */
	public List<OrderFlat> getItemsByOrderId(String orderId) throws Exception;
	/**
	 * Add new order service
	 * @param order order's object
	 * @param userId current user's id
	 * 
	 * @return return new order's id
	 * @throws Exception 
	 */
	public String addOrder(OrderFlat order, String userId) throws Exception;
	/**
	 * Add new purchase order service
	 * @param order order's object
	 * 
	 * @return return new order's id
	 * @throws Exception 
	 */
	public String addPurchaseOrder(OrderFlat order) throws Exception;
	/**
	 * Add new purchase return order service
	 * @param order order's object
	 * 
	 * @return return new order's id
	 * @throws Exception 
	 */
	public String addPurchaseReturnOrder(OrderFlat order) throws Exception;
	/**
	 * Add new sales order service
	 * @param order order's object
	 * 
	 * @return return new order's id
	 * @throws Exception 
	 */
	public String addSalesOrder(OrderFlat order) throws Exception;
	/**
	 * Add new sales return order service
	 * @param order order's object
	 * 
	 * @return return new order's id
	 * @throws Exception 
	 */
	public String addSalesReturnOrder(OrderFlat order) throws Exception;
	/**
	 * Query all orders data
	 * 
	 * @return return list object of all orders data
	 * @throws Exception 
	 */
	public List<OrderFlat> getAllOrders() throws Exception;
	/**
	 * Query all orders data
	 * @param userid user's id
	 * 
	 * @return return list object of all orders data under specified user
	 * @throws Exception 
	 */
	public List<OrderFlat> getAllOrders(String userid) throws Exception;
	/**
	 * Query all orders data
	 * @param order_id order's id
	 * 
	 * @return return list object of all orders data under specified user
	 * @throws Exception 
	 */
	public List<OrderReqProdCounts> getOrderProdCounts(String order_id) throws Exception;
	/**
	 * Query recent orders updated by user
	 * @param userid user's id
	 * 
	 * @return return list object of recent orders data under specified user
	 * @throws Exception 
	 */
	public List<OrderFlat> getRecentOrders(String userid) throws Exception;
	/**
	 * Delete order by id
	 * @param order order's object
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delOrderById(OrderFlat order) throws Exception;
	/**
	 * Generate requisitions from order
	 * @param order order's object
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean generateReq(OrderFlat order) throws Exception;
	/**
	 * Query order by id
	 * @param id order's id
	 * 
	 * @return return object of queried order data
	 * @throws Exception 
	 */
	public OrderFlat getOrderById(String id) throws Exception;
	/**
	 * Update Order service
	 * @param order order's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateOrderById(OrderFlat order) throws Exception;
	/**
	 * Update Order's status service
	 * @param order order's object
	 * 
	 * @return return update message including errors
	 * @throws Exception 
	 */
	public String updateOrderStatusById(OrderFlat order) throws Exception;
	/**
	 * Update Order's status facade service
	 * @param order order's object
	 * 
	 * @return return update message including errors
	 * @throws Exception 
	 */
	public String updateOrderStatusByIdFacade(OrderFlat order) throws Exception;
	/**
	 * Update Order's address service
	 * @param order order's object
	 * @param addr_type address's type bill address or ship address
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateOrderAddrById(OrderFlat order, String addr_type) throws Exception;
	/**
	 * Get order's total count
	 * 
	 * @return return total count
	 * @throws Exception 
	 */
	int getOrdersCount() throws Exception;
	/**
	 * Get order's total count
	 * @param userid user's id
	 * 
	 * @return return total count under specified user
	 * @throws Exception 
	 */
	int getOrdersCount(String userid) throws Exception;
	/**
	 * Query specified number of orders
	 * @param order order's object for query parameters
	 * @param start line number of start
	 * @param limit number of data
	 * @param sortColumn column number to sort
	 * @param sortDir sort direction, asc or desc
	 * 
	 * @return return list object of queried order data
	 * @throws Exception 
	 */
	List<OrderFlat> getOrders(OrderFlat order, String start, String limit,
			String sortColumn, String sortDir) throws Exception;
	/**
	 * Query orders for current approver
	 * @param approver_id approver's id
	 * @param start line number of start
	 * @param limit number of data
	 * @param sortColumn column number to sort
	 * @param sortDir sort direction, asc or desc
	 * 
	 * @return return list object of queried order data
	 * @throws Exception 
	 */
	public List<OrderFlat> getOrdersForApprover(OrderFlat order,String approver_id,String start,String limit,String sortColumn, String sortDir) throws Exception;
	/**
	 * Update Order's amounts service
	 * @param order order's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	boolean updateOrderAmountsById(OrderFlat order) throws Exception;
	/**
	 * Add new order_history
	 * @param order_history order history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addHistory(OrderHistory order_history) throws Exception;
	/**
	 * Add new approval_history
	 * @param approval_history approval history's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addApprovalHistory(ApprovalHistory approval_history) throws Exception;
	/**
	 * Delete order history by id
	 * @param id order history's id
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
	public List<OrderHistory> getAllHistories() throws Exception;
	/**
	 * Query order history by id
	 * @param id order history's id
	 * 
	 * @return return list object of queried order history data
	 * @throws Exception 
	 */
	public OrderHistory getHistoryById(String id) throws Exception;
	/**
	 * Query specified number of order histories
	 * @param order_history order history's object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * @param sortColumn column to sort
	 * @param sortDir asc or desc
	 * 
	 * @return return list object of queried order histories data
	 * @throws Exception 
	 */
	public List<OrderHistory> getHistories(OrderHistory order_history,String start,String limit,String sortColumn, String sortDir) throws Exception;
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
