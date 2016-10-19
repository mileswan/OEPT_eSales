package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/12
 * Description: Requisition history data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class RequisitionHistory {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String requisition_id;
	private String requisition_number;
	private String requisition_man_number;
	private String requisition_type_code;
	private String requisition_type_val;
	private String description;
	private String created_date;
	private String created_by_user_id;
	private String created_by_user_name;
	//for filter
	private String created_date_from;
	private String created_date_to;
	
	public String getRequisition_man_number() {
		return requisition_man_number;
	}
	public void setRequisition_man_number(String requisition_man_number) {
		this.requisition_man_number = requisition_man_number;
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
	public String getRequisition_type_code() {
		return requisition_type_code;
	}
	public void setRequisition_type_code(String requisition_type_code) {
		this.requisition_type_code = requisition_type_code;
	}
	public String getRequisition_type_val() {
		return requisition_type_val;
	}
	public void setRequisition_type_val(String requisition_type_val) {
		this.requisition_type_val = requisition_type_val;
	}
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
