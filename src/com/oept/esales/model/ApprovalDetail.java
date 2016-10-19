package com.oept.esales.model;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2016/01/26
 * Description: Approval data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class ApprovalDetail {
	private String id;
	private String step_id;
	private String status_cd;
	private String status_val;
	private String to_approve;
	private String method_cd;
	private String method_name;
	private String rollback_type_cd;
	private String rollback_type_name;
	private String created;
	
	private boolean process_flg;
	
	private User user;
	
	
	public boolean isProcess_flg() {
		return process_flg;
	}
	public void setProcess_flg(boolean process_flg) {
		this.process_flg = process_flg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStep_id() {
		return step_id;
	}
	public void setStep_id(String step_id) {
		this.step_id = step_id;
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
	public String getTo_approve() {
		return to_approve;
	}
	public void setTo_approve(String to_approve) {
		this.to_approve = to_approve;
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
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
}
