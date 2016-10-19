package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/8
 * Description: Order history data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class OrderHistory {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String order_id;
	private String order_number;
	private String order_type_code;
	private String order_type_val;
	private String description;
	private String created_date;
	private String created_by_user_id;
	private String created_by_user_name;
	//for filter
	private String created_date_from;
	private String created_date_to;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
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
