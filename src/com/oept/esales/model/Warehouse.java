package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Warehouse data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class Warehouse {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String name;
	private String number;
	private String comment;
	private boolean active;
	private String primary_addr_id;
	private String primary_addr_name;
	private String primary_contact_id;
	private String primary_contact_name;
	private String primary_contact_cellphone;
	private String created_date;
	private String created_by_user_id;
	private String created_by_user_name;
	private String updated_date;
	private String updated_by_user_id;
	private String updated_by_user_name;
	List<WarehouseStock> stock_info_list;
	//For filter
	private boolean checkActive;
	
	
	public List<WarehouseStock> getStock_info_list() {
		return stock_info_list;
	}
	public void setStock_info_list(List<WarehouseStock> stock_info_list) {
		this.stock_info_list = stock_info_list;
	}
	public boolean isCheckActive() {
		return checkActive;
	}
	public void setCheckActive(boolean checkActive) {
		this.checkActive = checkActive;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
