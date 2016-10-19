package com.oept.esales.model;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/22
 * Description: Categories data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class Account {
	private String accountId;
	private String accountName;
	private String accountNumber;
	private String accountType;
	private String aCatId;
	private String workphone;
	private String primaryAddrId;
	private String fax;
	private String email;
	private String created;
	private String createdBy;
	private String createdById;
	private String update;
	private String updateBy;
	private String updateById;
	private String accountDesc;
	private String accountComm;
	private String primaryShipaddrId;
	private String primarybilladdrId;
	private String accountStatus;
	private boolean active;
	
	//account cat
	private String catId;
	private String catName;
	private String catDesc;
	private String catActive;
	private String catParId;
	private String catLvl;
	private String catCreated;
	private String catCreatedBy;
	private String catUpdate;
	private String catUpdateBy;
	
	private Address address;
	private String addressName;
	private String shipAddressName;
	private String billAddressName;
	
	private String zipcode;
	private String addrName;
	private String updateByName;
	private String accountStatusVal;
	private String otherAddress1;
	private String otherAddress2;
	
	public String getCreatedById() {
		return createdById;
	}
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	public String getUpdateById() {
		return updateById;
	}
	public void setUpdateById(String updateById) {
		this.updateById = updateById;
	}
	public String getOtherAddress1() {
		return otherAddress1;
	}
	public void setOtherAddress1(String otherAddress1) {
		this.otherAddress1 = otherAddress1;
	}
	public String getOtherAddress2() {
		return otherAddress2;
	}
	public void setOtherAddress2(String otherAddress2) {
		this.otherAddress2 = otherAddress2;
	}
	public String getAccountStatusVal() {
		return accountStatusVal;
	}
	public void setAccountStatusVal(String accountStatusVal) {
		this.accountStatusVal = accountStatusVal;
	}
	public String getUpdateByName() {
		return updateByName;
	}
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddrName() {
		return addrName;
	}
	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getShipAddressName() {
		return shipAddressName;
	}
	public void setShipAddressName(String shipAddressName) {
		this.shipAddressName = shipAddressName;
	}
	public String getBillAddressName() {
		return billAddressName;
	}
	public void setBillAddressName(String billAddressName) {
		this.billAddressName = billAddressName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getaCatId() {
		return aCatId;
	}
	public void setaCatId(String aCatId) {
		this.aCatId = aCatId;
	}
	public String getWorkphone() {
		return workphone;
	}
	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}
	public String getPrimaryAddrId() {
		return primaryAddrId;
	}
	public void setPrimaryAddrId(String primaryAddrId) {
		this.primaryAddrId = primaryAddrId;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getAccountDesc() {
		return accountDesc;
	}
	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}
	public String getAccountComm() {
		return accountComm;
	}
	public void setAccountComm(String accountComm) {
		this.accountComm = accountComm;
	}
	public String getPrimaryShipaddrId() {
		return primaryShipaddrId;
	}
	public void setPrimaryShipaddrId(String primaryShipaddrId) {
		this.primaryShipaddrId = primaryShipaddrId;
	}
	public String getPrimarybilladdrId() {
		return primarybilladdrId;
	}
	public void setPrimarybilladdrId(String primarybilladdrId) {
		this.primarybilladdrId = primarybilladdrId;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	public String getCatActive() {
		return catActive;
	}
	public void setCatActive(String catActive) {
		this.catActive = catActive;
	}
	public String getCatParId() {
		return catParId;
	}
	public void setCatParId(String catParId) {
		this.catParId = catParId;
	}
	public String getCatLvl() {
		return catLvl;
	}
	public void setCatLvl(String catLvl) {
		this.catLvl = catLvl;
	}
	public String getCatCreated() {
		return catCreated;
	}
	public void setCatCreated(String catCreated) {
		this.catCreated = catCreated;
	}
	public String getCatCreatedBy() {
		return catCreatedBy;
	}
	public void setCatCreatedBy(String catCreatedBy) {
		this.catCreatedBy = catCreatedBy;
	}
	public String getCatUpdate() {
		return catUpdate;
	}
	public void setCatUpdate(String catUpdate) {
		this.catUpdate = catUpdate;
	}
	public String getCatUpdateBy() {
		return catUpdateBy;
	}
	public void setCatUpdateBy(String catUpdateBy) {
		this.catUpdateBy = catUpdateBy;
	}
	
	
	
	
}
