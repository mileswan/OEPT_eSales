package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/7
 * Description: Stock history data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class StockHistory {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String warehouse_id;
	private String warehouse_name;
	private String product_id;
	private String product_name;
	private String order_id;
	private String order_number;
	private String requisition_id;
	private String requisition_number;
	private String order_type_code;
	private String order_type_val;
	private String stock_type_code;
	private String stock_type_val;
	private int original_stock;
	private int stock_quantity;
	private double stock_price;
	private double stock_amount;
	private String description;
	private String created_date;
	private String created_by_user_id;
	private String created_by_user_name;
	//for filter
	private String created_date_from;
	private String created_date_to;
	private String stock_quantity_from;
	private String stock_quantity_to;
	private String original_stock_from;
	private String original_stock_to;
	private String stock_price_from;
	private String stock_price_to;
	private String stock_amount_from;
	private String stock_amount_to;
	
	public String getOriginal_stock_from() {
		return original_stock_from;
	}
	public void setOriginal_stock_from(String original_stock_from) {
		this.original_stock_from = original_stock_from;
	}
	public String getOriginal_stock_to() {
		return original_stock_to;
	}
	public void setOriginal_stock_to(String original_stock_to) {
		this.original_stock_to = original_stock_to;
	}
	public int getOriginal_stock() {
		return original_stock;
	}
	public void setOriginal_stock(int original_stock) {
		this.original_stock = original_stock;
	}
	public String getRequisition_id() {
		return requisition_id;
	}
	public void setRequisition_id(String requisition_id) {
		this.requisition_id = requisition_id;
	}
	public String getRequisition_number() {
		return requisition_number;
	}
	public void setRequisition_number(String requisition_number) {
		this.requisition_number = requisition_number;
	}
	public String getStock_price_from() {
		return stock_price_from;
	}
	public void setStock_price_from(String stock_price_from) {
		this.stock_price_from = stock_price_from;
	}
	public String getStock_price_to() {
		return stock_price_to;
	}
	public void setStock_price_to(String stock_price_to) {
		this.stock_price_to = stock_price_to;
	}
	public String getStock_amount_from() {
		return stock_amount_from;
	}
	public void setStock_amount_from(String stock_amount_from) {
		this.stock_amount_from = stock_amount_from;
	}
	public String getStock_amount_to() {
		return stock_amount_to;
	}
	public void setStock_amount_to(String stock_amount_to) {
		this.stock_amount_to = stock_amount_to;
	}
	public double getStock_price() {
		return stock_price;
	}
	public void setStock_price(double stock_price) {
		this.stock_price = stock_price;
	}
	public double getStock_amount() {
		return stock_amount;
	}
	public void setStock_amount(double stock_amount) {
		this.stock_amount = stock_amount;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getCreated_date_from() {
		return created_date_from;
	}
	public void setCreated_date_from(String created_date_from) {
		this.created_date_from = created_date_from;
	}
	public String getCreated_date_to() {
		return created_date_to;
	}
	public void setCreated_date_to(String created_date_to) {
		this.created_date_to = created_date_to;
	}
	public String getStock_quantity_from() {
		return stock_quantity_from;
	}
	public void setStock_quantity_from(String stock_quantity_from) {
		this.stock_quantity_from = stock_quantity_from;
	}
	public String getStock_quantity_to() {
		return stock_quantity_to;
	}
	public void setStock_quantity_to(String stock_quantity_to) {
		this.stock_quantity_to = stock_quantity_to;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(String warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getWarehouse_name() {
		return warehouse_name;
	}
	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_type_code() {
		return order_type_code;
	}
	public void setOrder_type_code(String order_type_code) {
		this.order_type_code = order_type_code;
	}
	public String getOrder_type_val() {
		return order_type_val;
	}
	public void setOrder_type_val(String order_type_val) {
		this.order_type_val = order_type_val;
	}
	public String getStock_type_code() {
		return stock_type_code;
	}
	public void setStock_type_code(String stock_type_code) {
		this.stock_type_code = stock_type_code;
	}
	public String getStock_type_val() {
		return stock_type_val;
	}
	public void setStock_type_val(String stock_type_val) {
		this.stock_type_val = stock_type_val;
	}
	public int getStock_quantity() {
		return stock_quantity;
	}
	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = dateFormat.format(created_date);
	}
	public String getCreated_by_user_id() {
		return created_by_user_id;
	}
	public void setCreated_by_user_id(String created_by_user_id) {
		this.created_by_user_id = created_by_user_id;
	}
	public String getCreated_by_user_name() {
		return created_by_user_name;
	}
	public void setCreated_by_user_name(String created_by_user_name) {
		this.created_by_user_name = created_by_user_name;
	}

}
