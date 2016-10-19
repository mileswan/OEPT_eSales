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
public class Approval {
	private String rule_id;
	private String rule_name;
	private String object_cd;
	private String object_name;
	private String action_cd;
	private String action_name;
	private String rollback_type_cd;
	private String rollback_type_name;
	private boolean email;
	private boolean inbox;
	private String rule_created;
	private String rule_created_by;
	private String rule_updated;
	private String rule_updated_by;
	
	List<ApprovalItem> approvalItem = new ArrayList<ApprovalItem>();
	
	
	public List<ApprovalItem> getApprovalItem() {
		return approvalItem;
	}
	public void setApprovalItem(List<ApprovalItem> approvalItem) {
		this.approvalItem = approvalItem;
	}
	public String getRule_id() {
		return rule_id;
	}
	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}
	public String getRule_name() {
		return rule_name;
	}
	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}
	public String getObject_cd() {
		return object_cd;
	}
	public void setObject_cd(String object_cd) {
		this.object_cd = object_cd;
	}
	public String getObject_name() {
		return object_name;
	}
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
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
	public boolean isEmail() {
		return email;
	}
	public void setEmail(boolean email) {
		this.email = email;
	}
	public boolean isInbox() {
		return inbox;
	}
	public void setInbox(boolean inbox) {
		this.inbox = inbox;
	}
	public String getRule_created() {
		return rule_created;
	}
	public void setRule_created(String rule_created) {
		this.rule_created = rule_created;
	}
	public String getRule_created_by() {
		return rule_created_by;
	}
	public void setRule_created_by(String rule_created_by) {
		this.rule_created_by = rule_created_by;
	}
	public String getRule_updated() {
		return rule_updated;
	}
	public void setRule_updated(String rule_updated) {
		this.rule_updated = rule_updated;
	}
	public String getRule_updated_by() {
		return rule_updated_by;
	}
	public void setRule_updated_by(String rule_updated_by) {
		this.rule_updated_by = rule_updated_by;
	}
	
	
}
