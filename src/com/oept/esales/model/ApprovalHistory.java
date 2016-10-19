package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/02/03
 * Description: Approval history data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class ApprovalHistory {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String order_id;
	private String order_number;
	private String order_type_code;
	private String order_type_val;
	private String requisition_id;
	private String requisition_number;
	private String requisition_type_code;
	private String requisition_type_val;
	private String contract_id;
	private String contract_number;
	private String contract_type_code;
	private String contract_type_val;
	private String product_id;
	private String product_name;
	private String account_id;
	private String account_name;
	private String description;
	private String created_date;
	private String created_by_user_id;
	private String created_by_user_name;
	//for filter
	private String created_date_from;
	private String created_date_to;
	
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
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getContract_number() {
		return contract_number;
	}
	public void setContract_number(String contract_number) {
		this.contract_number = contract_number;
	}
	public String getContract_type_code() {
		return contract_type_code;
	}
	public void setContract_type_code(String contract_type_code) {
		this.contract_type_code = contract_type_code;
	}
	public String getContract_type_val() {
		return contract_type_val;
	}
	public void setContract_type_val(String contract_type_val) {
		this.contract_type_val = contract_type_val;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
	
}
