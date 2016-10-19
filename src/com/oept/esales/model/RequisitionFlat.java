package com.oept.esales.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/8
 * Description: Requistion data flat model.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public class RequisitionFlat {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	//For requisition columns
	private String requisition_id;
	private String requisition_number;
	private String requisition_manual_number;
	private String requisition_date;
	private String delivery_date;
	private String receive_date;
	private String requisition_comment;
	private String requisition_type_cd;
	private String requisition_type;
	private String requisition_subtype_cd;
	private String requisition_subtype_val;
	private String currency_cd;
	private String currency_val;
	private String owner_id;
	private String owner_name;
	private String created_date;
	private String created_by_id;
	private String created_by_name;
	private String update_date;
	private String update_by_id;
	private String update_by_name;
	private String order_id;
	private String order_number;
	private String order_manual_number;
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
	//For order item columns
	private String item_id;
	private String item_prod_id;
	private String item_prod_name;
	private String item_prod_number;
	private String item_prod_model;
	private String item_prod_address_id;
	private String item_prod_address_name;
	private String item_warehouse_id;
	private String item_warehouse_name;
	private String item_contract_id;
	private String item_contract_number;
	private String item_status_code;
	private String item_status_value;
	private String item_comment;
	private int item_quantity;
	private double item_base_price;
	private double item_due_price;
	private double item_base_amount;
	private float item_tax_ratio;
	private float item_discount_ratio;
	private double item_discount_amount;
	private double item_due_amount;
	private double item_tax_amount;
	private String item_created_date;
	private String item_created_by_id;
	private String item_created_by_name;
	private String item_update_date;
	private String item_update_by_id;
	private String item_update_by_name;
	//For filter columns
	private String requisition_base_price_from;
	private String requisition_base_price_to;
	private String requisition_price_from;
	private String requisition_price_to;
	private String requisition_date_from;
	private String requisition_date_to;
	//For history description
	private String history_desc;
	
	public String getItem_prod_number() {
		return item_prod_number;
	}
	public void setItem_prod_number(String item_prod_number) {
		this.item_prod_number = item_prod_number;
	}
	public String getItem_prod_model() {
		return item_prod_model;
	}
	public void setItem_prod_model(String item_prod_model) {
		this.item_prod_model = item_prod_model;
	}
	public String getItem_comment() {
		return item_comment;
	}
	public void setItem_comment(String item_comment) {
		this.item_comment = item_comment;
	}
	public String getCurrency_cd() {
		return currency_cd;
	}
	public void setCurrency_cd(String currency_cd) {
		this.currency_cd = currency_cd;
	}
	public String getCurrency_val() {
		return currency_val;
	}
	public void setCurrency_val(String currency_val) {
		this.currency_val = currency_val;
	}
	public String getHistory_desc() {
		return history_desc;
	}
	public void setHistory_desc(String history_desc) {
		this.history_desc = history_desc;
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
	public String getOrder_manual_number() {
		return order_manual_number;
	}
	public void setOrder_manual_number(String order_manual_number) {
		this.order_manual_number = order_manual_number;
	}
	public String getItem_contract_id() {
		return item_contract_id;
	}
	public void setItem_contract_id(String item_contract_id) {
		this.item_contract_id = item_contract_id;
	}
	public String getItem_contract_number() {
		return item_contract_number;
	}
	public void setItem_contract_number(String item_contract_number) {
		this.item_contract_number = item_contract_number;
	}
	public String getItem_warehouse_id() {
		return item_warehouse_id;
	}
	public void setItem_warehouse_id(String item_warehouse_id) {
		this.item_warehouse_id = item_warehouse_id;
	}
	public String getItem_warehouse_name() {
		return item_warehouse_name;
	}
	public void setItem_warehouse_name(String item_warehouse_name) {
		this.item_warehouse_name = item_warehouse_name;
	}
	public String getItem_prod_address_id() {
		return item_prod_address_id;
	}
	public void setItem_prod_address_id(String item_prod_address_id) {
		this.item_prod_address_id = item_prod_address_id;
	}
	public String getItem_prod_address_name() {
		return item_prod_address_name;
	}
	public void setItem_prod_address_name(String item_prod_address_name) {
		this.item_prod_address_name = item_prod_address_name;
	}
	public String getRequisition_subtype_cd() {
		return requisition_subtype_cd;
	}
	public void setRequisition_subtype_cd(String requisition_subtype_cd) {
		this.requisition_subtype_cd = requisition_subtype_cd;
	}
	public String getRequisition_subtype_val() {
		return requisition_subtype_val;
	}
	public void setRequisition_subtype_val(String requisition_subtype_val) {
		this.requisition_subtype_val = requisition_subtype_val;
	}
	public double getDelivery_amount() {
		return delivery_amount;
	}
	public void setDelivery_amount(double delivery_amount) {
		this.delivery_amount = delivery_amount;
	}
	public String getRequisition_type_cd() {
		return requisition_type_cd;
	}
	public void setRequisition_type_cd(String requisition_type_cd) {
		this.requisition_type_cd = requisition_type_cd;
	}
	public String getRequisition_comment() {
		return requisition_comment;
	}
	public void setRequisition_comment(String requisition_comment) {
		this.requisition_comment = requisition_comment;
	}
	public String getRequisition_manual_number() {
		return requisition_manual_number;
	}
	public void setRequisition_manual_number(String requisition_manual_number) {
		this.requisition_manual_number = requisition_manual_number;
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
	public double getItem_base_amount() {
		return item_base_amount;
	}
	public void setItem_base_amount(double item_base_amount) {
		this.item_base_amount = item_base_amount;
	}
	public float getItem_tax_ratio() {
		return item_tax_ratio;
	}
	public void setItem_tax_ratio(float item_tax_ratio) {
		this.item_tax_ratio = item_tax_ratio;
	}
	public float getItem_discount_ratio() {
		return item_discount_ratio;
	}
	public void setItem_discount_ratio(float item_discount_ratio) {
		this.item_discount_ratio = item_discount_ratio;
	}
	public String getRequisition_base_price_from() {
		return requisition_base_price_from;
	}
	public void setRequisition_base_price_from(String requisition_base_price_from) {
		this.requisition_base_price_from = requisition_base_price_from;
	}
	public String getRequisition_base_price_to() {
		return requisition_base_price_to;
	}
	public void setRequisition_base_price_to(String requisition_base_price_to) {
		this.requisition_base_price_to = requisition_base_price_to;
	}
	public String getRequisition_price_from() {
		return requisition_price_from;
	}
	public void setRequisition_price_from(String requisition_price_from) {
		this.requisition_price_from = requisition_price_from;
	}
	public String getRequisition_price_to() {
		return requisition_price_to;
	}
	public void setRequisition_price_to(String requisition_price_to) {
		this.requisition_price_to = requisition_price_to;
	}
	public String getRequisition_date_from() {
		return requisition_date_from;
	}
	public void setRequisition_date_from(String requisition_date_from) {
		this.requisition_date_from = requisition_date_from;
	}
	public String getRequisition_date_to() {
		return requisition_date_to;
	}
	public void setRequisition_date_to(String requisition_date_to) {
		this.requisition_date_to = requisition_date_to;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		if(delivery_date!=null){
			this.delivery_date = dateFormat1.format(delivery_date);
		}else{
			this.delivery_date = null;
		}
		
	}
	public String getReceive_date() {
		return receive_date;
	}
	public void setReceive_date(Date receive_date) {
		this.receive_date = dateFormat1.format(receive_date);
	}
	public String getItem_created_date() {
		return item_created_date;
	}
	public void setItem_created_date(Timestamp item_created_date) {
		this.item_created_date = dateFormat.format(item_created_date);
	}
	public String getItem_created_by_id() {
		return item_created_by_id;
	}
	public void setItem_created_by_id(String item_created_by_id) {
		this.item_created_by_id = item_created_by_id;
	}
	public String getItem_created_by_name() {
		return item_created_by_name;
	}
	public void setItem_created_by_name(String item_created_by_name) {
		this.item_created_by_name = item_created_by_name;
	}
	public String getItem_update_date() {
		return item_update_date;
	}
	public void setItem_update_date(Timestamp item_update_date) {
		this.item_update_date = dateFormat.format(item_update_date);
	}
	public String getItem_update_by_id() {
		return item_update_by_id;
	}
	public void setItem_update_by_id(String item_update_by_id) {
		this.item_update_by_id = item_update_by_id;
	}
	public String getItem_update_by_name() {
		return item_update_by_name;
	}
	public void setItem_update_by_name(String item_update_by_name) {
		this.item_update_by_name = item_update_by_name;
	}
	public String getRequisition_id() {
		return requisition_id;
	}
	public void setRequisition_id(String requisition_id) {
		this.requisition_id = requisition_id;
	}
	public String getRequisition_number() {
		return requisition_number;
	}
	public void setRequisition_number(String requisition_number) {
		this.requisition_number = requisition_number;
	}
	public String getRequisition_date() {
		return requisition_date;
	}
	public void setRequisition_date(Timestamp requisition_date) {
		this.requisition_date = dateFormat.format(requisition_date);
	}
	public String getRequisition_type() {
		return requisition_type;
	}
	public void setRequisition_type(String requisition_type) {
		this.requisition_type = requisition_type;
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
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_prod_id() {
		return item_prod_id;
	}
	public void setItem_prod_id(String item_prod_id) {
		this.item_prod_id = item_prod_id;
	}
	public String getItem_prod_name() {
		return item_prod_name;
	}
	public void setItem_prod_name(String item_prod_name) {
		this.item_prod_name = item_prod_name;
	}
	public String getItem_status_code() {
		return item_status_code;
	}
	public void setItem_status_code(String item_status_code) {
		this.item_status_code = item_status_code;
	}
	public String getItem_status_value() {
		return item_status_value;
	}
	public void setItem_status_value(String item_status_value) {
		this.item_status_value = item_status_value;
	}
	public int getItem_quantity() {
		return item_quantity;
	}
	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}
	public double getItem_base_price() {
		return item_base_price;
	}
	public void setItem_base_price(double item_base_price) {
		this.item_base_price = item_base_price;
	}
	public double getItem_due_price() {
		return item_due_price;
	}
	public void setItem_due_price(double item_due_price) {
		this.item_due_price = item_due_price;
	}
	public double getItem_discount_amount() {
		return item_discount_amount;
	}
	public void setItem_discount_amount(double item_discount_amount) {
		this.item_discount_amount = item_discount_amount;
	}
	public double getItem_due_amount() {
		return item_due_amount;
	}
	public void setItem_due_amount(double item_due_amount) {
		this.item_due_amount = item_due_amount;
	}
	public double getItem_tax_amount() {
		return item_tax_amount;
	}
	public void setItem_tax_amount(double item_tax_amount) {
		this.item_tax_amount = item_tax_amount;
	}
	
	
}
