package com.oept.esales.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Contract {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	private String id;
	private String number;
	private String name;
	private String type_code;
	private String type_value;
	private String status_code;
	private String status_value;
	private String owner_id;
	private String owner_name;
	private String account_id;
	private String account_name;
	private String supplier_id;
	private String supplier_name;
	private String contract_date;
	private String description;
	private String project_name;
	private String project_id;
	private double amount;
	private String created_date;
	private String created_by_user_id;
	private String created_by_user_name;
	private String updated_date;
	private String updated_by_user_id;
	private String updated_by_user_name;
	
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getStatus_value() {
		return status_value;
	}
	public void setStatus_value(String status_value) {
		this.status_value = status_value;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getType_value() {
		return type_value;
	}
	public void setType_value(String type_value) {
		this.type_value = type_value;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getContract_date() {
		return contract_date;
	}
	public void setContract_date(Date contract_date) {
		this.contract_date = dateFormat1.format(contract_date);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = dateFormat.format(created_date);
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
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Timestamp updated_date) {
		this.updated_date = dateFormat.format(updated_date);
	}
	public String getUpdated_by_user_id() {
		return updated_by_user_id;
	}
	public void setUpdated_by_user_id(String updated_by_user_id) {
		this.updated_by_user_id = updated_by_user_id;
	}
	public String getUpdated_by_user_name() {
		return updated_by_user_name;
	}
	public void setUpdated_by_user_name(String updated_by_user_name) {
		this.updated_by_user_name = updated_by_user_name;
	}
	
}
