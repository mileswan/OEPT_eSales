package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/30
 * Description: Orders data model.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public class Order {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//For order columns
	private String order_id;
	private String order_number;
	private String order_manual_number;
	private String purchase_date;
	private String delivery_date;
	private String order_comment;
	private String order_type_cd;
	private String order_type;
	private String owner_id;
	private String owner_name;
	private String created_date;
	private String created_by_id;
	private String created_by_name;
	private String update_date;
	private String update_by_id;
	private String update_by_name;
	private String account_id;
	private String account_name;
	private String supplier_id;
	private String supplier_name;
	private String delivery_wh_id;
	private String delivery_wh_name;
	private String receive_wh_id;
	private String receive_wh_name;
	private String ship_addr_id;
	private String ship_addr_name;
	private String bill_addr_id;
	private String bill_addr_name;
	private String status_code;
	private String status_value;
	private double base_amount;
	private double delivery_amount;
	private double due_amount;
	private boolean owner_viewed;
	//For filter columns
	private String order_base_price_from;
	private String order_base_price_to;
	private String order_purchase_price_from;
	private String order_purchase_price_to;
	private String order_date_from;
	private String order_date_to;
	//For some statistics
	private int records_count;
	
	public double getDelivery_amount() {
		return delivery_amount;
	}
	public void setDelivery_amount(double delivery_amount) {
		this.delivery_amount = delivery_amount;
	}
	public String getOrder_type_cd() {
		return order_type_cd;
	}
	public void setOrder_type_cd(String order_type_cd) {
		this.order_type_cd = order_type_cd;
	}
	public String getOrder_comment() {
		return order_comment;
	}
	public void setOrder_comment(String order_comment) {
		this.order_comment = order_comment;
	}
	public String getOrder_manual_number() {
		return order_manual_number;
	}
	public void setOrder_manual_number(String order_manual_number) {
		this.order_manual_number = order_manual_number;
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
	public String getDelivery_wh_id() {
		return delivery_wh_id;
	}
	public void setDelivery_wh_id(String delivery_wh_id) {
		this.delivery_wh_id = delivery_wh_id;
	}
	public String getDelivery_wh_name() {
		return delivery_wh_name;
	}
	public void setDelivery_wh_name(String delivery_wh_name) {
		this.delivery_wh_name = delivery_wh_name;
	}
	public String getReceive_wh_id() {
		return receive_wh_id;
	}
	public void setReceive_wh_id(String receive_wh_id) {
		this.receive_wh_id = receive_wh_id;
	}
	public String getReceive_wh_name() {
		return receive_wh_name;
	}
	public void setReceive_wh_name(String receive_wh_name) {
		this.receive_wh_name = receive_wh_name;
	}
	public int getRecords_count() {
		return records_count;
	}
	public void setRecords_count(int records_count) {
		this.records_count = records_count;
	}
	public String getOrder_base_price_from() {
		return order_base_price_from;
	}
	public void setOrder_base_price_from(String order_base_price_from) {
		this.order_base_price_from = order_base_price_from;
	}
	public String getOrder_base_price_to() {
		return order_base_price_to;
	}
	public void setOrder_base_price_to(String order_base_price_to) {
		this.order_base_price_to = order_base_price_to;
	}
	public String getOrder_purchase_price_from() {
		return order_purchase_price_from;
	}
	public void setOrder_purchase_price_from(String order_purchase_price_from) {
		this.order_purchase_price_from = order_purchase_price_from;
	}
	public String getOrder_purchase_price_to() {
		return order_purchase_price_to;
	}
	public void setOrder_purchase_price_to(String order_purchase_price_to) {
		this.order_purchase_price_to = order_purchase_price_to;
	}
	public String getOrder_date_from() {
		return order_date_from;
	}
	public void setOrder_date_from(String order_date_from) {
		this.order_date_from = order_date_from;
	}
	public String getOrder_date_to() {
		return order_date_to;
	}
	public void setOrder_date_to(String order_date_to) {
		this.order_date_to = order_date_to;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Timestamp delivery_date) {
		if(delivery_date!=null){
			this.delivery_date = dateFormat.format(delivery_date);
		}else{
			this.delivery_date = null;
		}
		
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Timestamp purchase_date) {
		this.purchase_date = dateFormat.format(purchase_date);
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
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
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = dateFormat.format(created_date);
	}
	public String getCreated_by_id() {
		return created_by_id;
	}
	public void setCreated_by_id(String created_by_id) {
		this.created_by_id = created_by_id;
	}
	public String getCreated_by_name() {
		return created_by_name;
	}
	public void setCreated_by_name(String created_by_name) {
		this.created_by_name = created_by_name;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = dateFormat.format(update_date);
	}
	public String getUpdate_by_id() {
		return update_by_id;
	}
	public void setUpdate_by_id(String update_by_id) {
		this.update_by_id = update_by_id;
	}
	public String getUpdate_by_name() {
		return update_by_name;
	}
	public void setUpdate_by_name(String update_by_name) {
		this.update_by_name = update_by_name;
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
	public String getShip_addr_id() {
		return ship_addr_id;
	}
	public void setShip_addr_id(String ship_addr_id) {
		this.ship_addr_id = ship_addr_id;
	}
	public String getShip_addr_name() {
		return ship_addr_name;
	}
	public void setShip_addr_name(String ship_addr_name) {
		this.ship_addr_name = ship_addr_name;
	}
	public String getBill_addr_id() {
		return bill_addr_id;
	}
	public void setBill_addr_id(String bill_addr_id) {
		this.bill_addr_id = bill_addr_id;
	}
	public String getBill_addr_name() {
		return bill_addr_name;
	}
	public void setBill_addr_name(String bill_addr_name) {
		this.bill_addr_name = bill_addr_name;
	}
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
	public double getBase_amount() {
		return base_amount;
	}
	public void setBase_amount(double base_amount) {
		this.base_amount = base_amount;
	}
	public double getDue_amount() {
		return due_amount;
	}
	public void setDue_amount(double due_amount) {
		this.due_amount = due_amount;
	}
	public boolean isOwner_viewed() {
		return owner_viewed;
	}
	public void setOwner_viewed(boolean owner_viewed) {
		this.owner_viewed = owner_viewed;
	}
	
	
}
