package com.oept.esales.dao;

import java.util.List;

import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.OrderReqProdCounts;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/30
 * Description: Orders and order items DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface OrderDao {

	/**
	 * Add new order
	 * @param order order's object
	 * 
	 * @return return new order's id
	 * @throws Exception 
	 */
	public String addOrder(OrderFlat order) throws Exception;
	/**
	 * Delete order by id
	 * @param id order's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delOrderById(String id) throws Exception;
	/**
	 * Update order by Order object
	 * @param order ordery's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateOrder(OrderFlat order) throws Exception;
	/**
	 * Update order's status by Order object
	 * @param order ordery's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateOrderStatus(OrderFlat order) throws Exception;
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
	 * Query order by id
	 * @param id order's id
	 * 
	 * @return return list object of queried order data
	 * @throws Exception 
	 */
	public OrderFlat getOrderById(String id) throws Exception;
	/**
	 * Count total orders records
	 * 
	 * @return return count of total orders records
	 * @throws Exception 
	 */
	int getOrdersCount() throws Exception;
	/**
	 * Count total orders records
	 * @param userid user's id
	 * 
	 * @return return count of total orders records under specified user
	 * @throws Exception 
	 */
	int getOrdersCount(String userid) throws Exception;
	/**
	 * Query all orders data
	 * @param order_id order's id
	 * 
	 * @return return list object of all orders data under specified user
	 * @throws Exception 
	 */
	public List<OrderReqProdCounts> getOrderProdCounts(String order_id) throws Exception;
	/**
	 * Query all orders data
	 * @param order_id order's id
	 * @param prod_id product's id
	 * 
	 * @return return list object of all orders data under specified user
	 * @throws Exception 
	 */
	public List<OrderReqProdCounts> getOrderProdCounts(String order_id,String prod_id) throws Exception;
	/**
	 * Query specified number of orders
	 * @param order order's object for filter parameters
	 * @param start line number of start
	 * @param limit number of data
	 * @param sortColumn column number to sort
	 * @param sortDir sort direction, asc or desc
	 * 
	 * @return return list object of queried order data
	 * @throws Exception 
	 */
	public List<OrderFlat> getOrders(OrderFlat order,String start,String limit,String sortColumn, String sortDir) throws Exception;
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
	 * Add new order item
	 * @param order order's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addOrderItem(OrderFlat order) throws Exception;
	/**
	 * Delete order item by id
	 * @param id order's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delOrderItemById(String id) throws Exception;
	/**
	 * Update order item by Order object
	 * @param order order's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateOrderItem(OrderFlat order) throws Exception;
	/**
	 * Query all orders item data for specified order
	 * @param orderId order's id
	 * 
	 * @return return list object of all orders data
	 * @throws Exception 
	 */
	public List<OrderFlat> getItemsByOrderId(String orderId) throws Exception;
	/**
	 * Update order's amounts by Order object
	 * @param order ordery's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	boolean updateOrderAmounts(OrderFlat order) throws Exception;
	/**
	 * Update order's shipping address by Order object
	 * @param order ordery's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	boolean updateOrderShipAddr(OrderFlat order) throws Exception;
	/**
	 * Update order's billing address by Order object
	 * @param order ordery's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	boolean updateOrderBillAddr(OrderFlat order) throws Exception;
	/**
	 * Update order's area by Order object
	 * @param order ordery's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	boolean updateOrderArea(OrderFlat order) throws Exception;
	/**
	 * Query unique items' warehouse ids
	 * @param orderId order's id
	 * 
	 * @return return unique items' warehouse ids
	 * @throws Exception 
	 */
	public List<String> getItemWarehouseIds(String orderId) throws Exception;
	/**
	 * Query recent orders updated by user
	 * @param userid user's id
	 * 
	 * @return return list object of recent orders data under specified user
	 * @throws Exception 
	 */
	public List<OrderFlat> getRecentOrders(String userid) throws Exception;
}
