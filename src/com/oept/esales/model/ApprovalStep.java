package com.oept.esales.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2016/01/26
 * Description: Approval data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class ApprovalStep {
	private String id;
	private String par_id;
	private String requisition_id;
	private String contract_id;
	private String order_id;
	private String status_cd;
	private String status_val;
	private String method_cd;
	private String method_name;
	private String rollback_type_cd;
	private String rollback_type_name;
	private boolean process_flg;
	private String created;
	private String updated;
	
	List<ApprovalDetail> stepDetails = new ArrayList<ApprovalDetail>();
	
	//2016-2-22
	private String account_id;
	private String product_id;
	
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public List<ApprovalDetail> getStepDetails() {
		return stepDetails;
	}
	public void setStepDetails(List<ApprovalDetail> stepDetails) {
		this.stepDetails = stepDetails;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPar_id() {
		return par_id;
	}
	public void setPar_id(String par_id) {
		this.par_id = par_id;
	}
	public String getRequisition_id() {
		return requisition_id;
	}
	public void setRequisition_id(String requisition_id) {
		this.requisition_id = requisition_id;
	}
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getStatus_cd() {
		return status_cd;
	}
	public void setStatus_cd(String status_cd) {
		this.status_cd = status_cd;
	}
	public String getStatus_val() {
		return status_val;
	}
	public void setStatus_val(String status_val) {
		this.status_val = status_val;
	}
	public String getMethod_cd() {
		return method_cd;
	}
	public void setMethod_cd(String method_cd) {
		this.method_cd = method_cd;
	}
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	public String getRollback_type_cd() {
		return rollback_type_cd;
	}
	public void setRollback_type_cd(String rollback_type_cd) {
		this.rollback_type_cd = rollback_type_cd;
	}
	public String getRollback_type_name() {
		return rollback_type_name;
	}
	public void setRollback_type_name(String rollback_type_name) {
		this.rollback_type_name = rollback_type_name;
	}
	
	public boolean isProcess_flg() {
		return process_flg;
	}
	public void setProcess_flg(boolean process_flg) {
		this.process_flg = process_flg;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	
}
