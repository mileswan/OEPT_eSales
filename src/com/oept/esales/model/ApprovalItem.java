package com.oept.esales.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2016/01/12
 * Description: Approval data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class ApprovalItem {
	
	private String item_id;
	private String rule_id;
	private String method_cd;
	private String method_name;
	private String par_id;
	private String created;
	private String created_by;
	private String updated;
	private String updated_by;
	
	List<ApprovalItemPer> approvalItemPer = new ArrayList<ApprovalItemPer>();
	
	
	public List<ApprovalItemPer> getApprovalItemPer() {
		return approvalItemPer;
	}
	public void setApprovalItemPer(List<ApprovalItemPer> approvalItemPer) {
		this.approvalItemPer = approvalItemPer;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getRule_id() {
		return rule_id;
	}
	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
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
	public String getPar_id() {
		return par_id;
	}
	public void setPar_id(String par_id) {
		this.par_id = par_id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	
	
	
}
