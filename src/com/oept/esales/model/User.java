package com.oept.esales.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/13
 * Description: Categories data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class User {
	private String userName; // 用户名
	private String password; // 密码
	private String userId; // 用户id
	private String system; // 身份
	private String firstName; // 名字
	private String lastName; // 姓氏
	private boolean active; // 状态
	private boolean delete; // 是否删除
	private String lastLogin; // 最后一次登录时间
	private String email; // 邮箱
	private String address; // 地址
	private String createdTime; // 创建时间
	private String createdId; // 创建人
	private String updated;		//更新时间
	private String updatedBy;	//更新人id
	private String primaryPositionId;	//主职位id
	private String primaryAddressId;	//主地址id
	private Position position;  	//职位对象
	private Address addressObject;		//地址对象
	
	
	
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Address getAddressObject() {
		return addressObject;
	}
	public void setAddressObject(Address addressObject) {
		this.addressObject = addressObject;
	}
	public String getPrimaryAddressId() {
		return primaryAddressId;
	}
	public void setPrimaryAddressId(String primaryAddressId) {
		this.primaryAddressId = primaryAddressId;
	}
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getPrimaryPositionId() {
		return primaryPositionId;
	}
	public void setPrimaryPositionId(String primaryPositionId) {
		this.primaryPositionId = primaryPositionId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatedId() {
		return createdId;
	}
	public void setCreatedId(String createdId) {
		this.createdId = createdId;
	}

	

}
