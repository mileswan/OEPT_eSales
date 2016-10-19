package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/31
 * Description: Warehouse stock data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class WarehouseStock {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String warehouse_id;
	private String warehouse_name;
	private String product_id;
	private String product_name;
	private String product_number;
	private String product_model;
	private String product_category_id;
	private String product_category;
	private String sku;
	private int stock;
	private int ordered_stock_in;
	private int ordered_stock_out;
	private String created_date;
	private String created_by_user_id;
	private String created_by_user_name;
	private String updated_date;
	private String updated_by_user_id;
	private String updated_by_user_name;
	
	public String getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(String product_category_id) {
		this.product_category_id = product_category_id;
	}
	public String getProduct_number() {
		return product_number;
	}
	public void setProduct_number(String product_number) {
		this.product_number = product_number;
	}
	public String getProduct_model() {
		return product_model;
	}
	public void setProduct_model(String product_model) {
		this.product_model = product_model;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getWarehouse_name() {
		return warehouse_name;
	}
	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getOrdered_stock_in() {
		return ordered_stock_in;
	}
	public void setOrdered_stock_in(int ordered_stock_in) {
		this.ordered_stock_in = ordered_stock_in;
	}
	public int getOrdered_stock_out() {
		return ordered_stock_out;
	}
	public void setOrdered_stock_out(int ordered_stock_out) {
		this.ordered_stock_out = ordered_stock_out;
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
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Timestamp updated_date) {
		this.updated_date = dateFormat.format(updated_date);
	}
	public String getUpdated_by_user_id() {
		return updated_by_user_id;
	}
	public void setUpdated_by_user_id(String updated_by_user_id) {
		this.updated_by_user_id = updated_by_user_id;
	}
	public String getUpdated_by_user_name() {
		return updated_by_user_name;
	}
	public void setUpdated_by_user_name(String updated_by_user_name) {
		this.updated_by_user_name = updated_by_user_name;
	}

	
}
