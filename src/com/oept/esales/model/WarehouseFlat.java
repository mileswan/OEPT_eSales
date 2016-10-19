package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Warehouse data flat model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class WarehouseFlat {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String name;
	private String number;
	private String comment;
	private boolean active;
	private String primary_addr_id;
	private String primary_addr_name;
	private String address_name;
	private String primary_contact_id;
	private String primary_contact_name;
	private String primary_contact_cellphone;
	private String created_date;
	private String created_by_user_id;
	private String created_by_user_name;
	private String updated_date;
	private String updated_by_user_id;
	private String updated_by_user_name;
	//for stock items in warehouse
	private String item_id;
	private String item_product_id;
	private String item_product_number;
	private String item_product_name;
	private String item_product_status;
	private String item_product_status_val;
	private String item_product_model;
	private double item_product_price;
	private int item_stock;
	private int item_ordered_stock_in;
	private int item_ordered_stock_out;
	private String item_sku;
	private String item_created_date;
	private String item_created_by_user_id;
	private String item_created_by_user_name;
	private String item_updated_date;
	private String item_updated_by_user_id;
	private String item_updated_by_user_name;
	//For filter
	private boolean checkActive;
	
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public String getItem_product_number() {
		return item_product_number;
	}
	public void setItem_product_number(String item_product_number) {
		this.item_product_number = item_product_number;
	}
	public String getItem_updated_date() {
		return item_updated_date;
	}
	public void setItem_updated_date(Timestamp item_updated_date) {
		this.item_updated_date = dateFormat.format(item_updated_date);
	}
	public String getItem_updated_by_user_id() {
		return item_updated_by_user_id;
	}
	public void setItem_updated_by_user_id(String item_updated_by_user_id) {
		this.item_updated_by_user_id = item_updated_by_user_id;
	}
	public String getItem_updated_by_user_name() {
		return item_updated_by_user_name;
	}
	public void setItem_updated_by_user_name(String item_updated_by_user_name) {
		this.item_updated_by_user_name = item_updated_by_user_name;
	}
	public String getItem_product_status_val() {
		return item_product_status_val;
	}
	public void setItem_product_status_val(String item_product_status_val) {
		this.item_product_status_val = item_product_status_val;
	}
	public String getItem_product_model() {
		return item_product_model;
	}
	public void setItem_product_model(String item_product_model) {
		this.item_product_model = item_product_model;
	}
	public boolean isCheckActive() {
		return checkActive;
	}
	public void setCheckActive(boolean checkActive) {
		this.checkActive = checkActive;
	}
	public int getItem_ordered_stock_in() {
		return item_ordered_stock_in;
	}
	public void setItem_ordered_stock_in(int item_ordered_stock_in) {
		this.item_ordered_stock_in = item_ordered_stock_in;
	}
	public int getItem_ordered_stock_out() {
		return item_ordered_stock_out;
	}
	public void setItem_ordered_stock_out(int item_ordered_stock_out) {
		this.item_ordered_stock_out = item_ordered_stock_out;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getItem_product_status() {
		return item_product_status;
	}
	public void setItem_product_status(String item_product_status) {
		this.item_product_status = item_product_status;
	}
	public String getItem_product_name() {
		return item_product_name;
	}
	public void setItem_product_name(String item_product_name) {
		this.item_product_name = item_product_name;
	}
	public double getItem_product_price() {
		return item_product_price;
	}
	public void setItem_product_price(double item_product_price) {
		this.item_product_price = item_product_price;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_product_id() {
		return item_product_id;
	}
	public void setItem_product_id(String item_product_id) {
		this.item_product_id = item_product_id;
	}
	public int getItem_stock() {
		return item_stock;
	}
	public void setItem_stock(int item_stock) {
		this.item_stock = item_stock;
	}
	public String getItem_sku() {
		return item_sku;
	}
	public void setItem_sku(String item_sku) {
		this.item_sku = item_sku;
	}
	public String getItem_created_date() {
		return item_created_date;
	}
	public void setItem_created_date(Timestamp item_created_date) {
		this.item_created_date = dateFormat.format(item_created_date);
	}
	public String getItem_created_by_user_id() {
		return item_created_by_user_id;
	}
	public void setItem_created_by_user_id(String item_created_by_user_id) {
		this.item_created_by_user_id = item_created_by_user_id;
	}
	public String getItem_created_by_user_name() {
		return item_created_by_user_name;
	}
	public void setItem_created_by_user_name(String item_created_by_user_name) {
		this.item_created_by_user_name = item_created_by_user_name;
	}
	public String getCreated_by_user_name() {
		return created_by_user_name;
	}
	public void setCreated_by_user_name(String created_by_user_name) {
		this.created_by_user_name = created_by_user_name;
	}
	public String getUpdated_by_user_name() {
		return updated_by_user_name;
	}
	public void setUpdated_by_user_name(String updated_by_user_name) {
		this.updated_by_user_name = updated_by_user_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPrimary_addr_id() {
		return primary_addr_id;
	}
	public void setPrimary_addr_id(String primary_addr_id) {
		this.primary_addr_id = primary_addr_id;
	}
	public String getPrimary_addr_name() {
		return primary_addr_name;
	}
	public void setPrimary_addr_name(String primary_addr_name) {
		this.primary_addr_name = primary_addr_name;
	}
	public String getPrimary_contact_id() {
		return primary_contact_id;
	}
	public void setPrimary_contact_id(String primary_contact_id) {
		this.primary_contact_id = primary_contact_id;
	}
	public String getPrimary_contact_name() {
		return primary_contact_name;
	}
	public void setPrimary_contact_name(String primary_contact_name) {
		this.primary_contact_name = primary_contact_name;
	}
	public String getPrimary_contact_cellphone() {
		return primary_contact_cellphone;
	}
	public void setPrimary_contact_cellphone(String primary_contact_cellphone) {
		this.primary_contact_cellphone = primary_contact_cellphone;
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
	
	
	
}
