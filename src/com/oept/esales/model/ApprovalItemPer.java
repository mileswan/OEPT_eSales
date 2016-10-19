package com.oept.esales.model;

import java.util.List;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2016/01/12
 * Description: Approval data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class ApprovalItemPer {
	
	private String item_per_id;
	private String item_id;
	private String user_id;
	private String per_created;
	private String per_created_by;
	
	User user = new User();
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getItem_per_id() {
		return item_per_id;
	}
	public void setItem_per_id(String item_per_id) {
		this.item_per_id = item_per_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPer_created() {
		return per_created;
	}
	public void setPer_created(String per_created) {
		this.per_created = per_created;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getPer_created_by() {
		return per_created_by;
	}
	public void setPer_created_by(String per_created_by) {
		this.per_created_by = per_created_by;
	}
	
}
