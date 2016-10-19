package com.oept.esales.model;

import java.util.List;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/13
 * Description: Categories data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class Address {

	private String addressId;		//地址id
	private String allAddress;		//地址全称
	private String province;		//省
	private String city;			//市
	private String country;			//国家
	private String county;			//区，县
	private String zipcode;			//邮政编码
	private String contactName;		//联系人
	private String contactCell;		//联系电话
	private String street;			//详细地址（街道)
	private String created;			//创建时间
	private String update;		//更新时间
	private String createdBy;		//创建人
	private String updateBy;	//更新人

	private List<User> user;		//用户集合
	
	
	
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getAllAddress() {
		return allAddress;
	}
	public void setAllAddress(String allAddress) {
		this.allAddress = allAddress;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactCell() {
		return contactCell;
	}
	public void setContactCell(String contactCell) {
		this.contactCell = contactCell;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
}
