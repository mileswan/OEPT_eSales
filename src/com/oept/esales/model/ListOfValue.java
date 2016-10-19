package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/12
 * Description: List of values data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class ListOfValue {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String list_code;
	private String list_name;
	private String list_value;
	private String list_desc;
	private String created_date;
	private String created_by_user_id;
	private String created_by_user_name;
	private String updated_date;
	private String updated_by_user_id;
	private String updated_by_user_name;
	//for filter
	private String created_date_from;
	private String created_date_to;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getList_code() {
		return list_code;
	}
	public void setList_code(String list_code) {
		this.list_code = list_code;
	}
	public String getList_name() {
		return list_name;
	}
	public void setList_name(String list_name) {
		this.list_name = list_name;
	}
	public String getList_value() {
		return list_value;
	}
	public void setList_value(String list_value) {
		this.list_value = list_value;
	}
	public String getList_desc() {
		return list_desc;
	}
	public void setList_desc(String list_desc) {
		this.list_desc = list_desc;
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
