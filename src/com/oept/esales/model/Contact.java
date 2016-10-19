package com.oept.esales.model;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/22
 * Description: Categories data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class Contact {
	private String contact_id;		//联系人id
	private String contact_name;	//联系人名称
	private String first_name;		//姓氏
	private String last_name;		//名字
	private String full_name;		//姓名
	private String comment;			//说明
	private String gender;			//性别
	private String birthday;		//生日
	private String age;				//年龄
	private String department;		//部门
	private String active_flg;		//账户状态
	private String title;			//职称
	private String contact_par_id;	//职级关系(暂)
	private String email;			//邮箱
	private String cellphone;		//个人电话
	private String workphone;		//办公电话
	private String fax;				//传真
	private String primary_addr_id;	//地址id
	private String account_id;		//单位id
	private String created;			//创建时间
	private String created_by;		//创建人id
	private String updated;			//更新时间
	private String updated_by;		//更新id
	private String created_name;	//创建人名称
	private String update_name;		//更新人名称
	
	private Account account;			//单位对象
	private Address address;			//地址对象
	
	
	
	public String getCreated_name() {
		return created_name;
	}
	public void setCreated_name(String created_name) {
		this.created_name = created_name;
	}
	public String getUpdate_name() {
		return update_name;
	}
	public void setUpdate_name(String update_name) {
		this.update_name = update_name;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getActive_flg() {
		return active_flg;
	}
	public void setActive_flg(String active_flg) {
		this.active_flg = active_flg;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContact_par_id() {
		return contact_par_id;
	}
	public void setContact_par_id(String contact_par_id) {
		this.contact_par_id = contact_par_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getWorkphone() {
		return workphone;
	}
	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPrimary_addr_id() {
		return primary_addr_id;
	}
	public void setPrimary_addr_id(String primary_addr_id) {
		this.primary_addr_id = primary_addr_id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
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
