package com.oept.esales.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/30
 * Description: Orders data flat model.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public class OrderFlat {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	//For order columns
	private String order_id;
	private String order_number;
	private String order_manual_number;
	private String purchase_date;
	private String delivery_date;
	private String order_comment;
	private String order_type_cd;
	private String order_type;
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
	private String account_id;
	private String account_name;
	private String account_lv2_id;
	private String account_lv2_name;
	private String supplier_id;
	private String supplier_name;
	private String supplier_lv2_id;
	private String supplier_lv2_name;
	private String delivery_wh_id;
	private String delivery_wh_name;
	private String receive_wh_id;
	private String receive_wh_name;
	private String ship_addr_id;
	private String ship_addr_name;
	private String bill_addr_id;
	private String bill_addr_name;
	private String area_id;
	private String status_code;
	private String status_value;
	private String payment_status_code;
	private String payment_status_val;
	private String payment_date;
	private double payment_ratio;
	private String receipt_status_code;
	private String receipt_status_val;
	private String receipt_due_date;
	private double receipt_ratio;
	private double base_amount;
	private double delivery_amount;
	private double due_amount;
	private boolean owner_viewed;
	//For order item columns
	private String item_id;
	private String item_prod_id;
	private String item_prod_name;
	private String item_prod_model;
	private String item_status_code;
	private String item_status_value;
	private int item_quantity;
	private String item_warehouse_id;
	private String item_warehouse_name;
	private String item_contract_id;
	private String item_contract_number;
	private String item_comment;
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
	private String order_base_price_from;
	private String order_base_price_to;
	private String order_purchase_price_from;
	private String order_purchase_price_to;
	private String order_date_from;
	private String order_date_to;
	//For history records
	private String history_desc;
	//For some statistics
	private int records_count;
	
	public String getItem_prod_model() {
		return item_prod_model;
	}
	public void setItem_prod_model(String item_prod_model) {
		this.item_prod_model = item_prod_model;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getAccount_lv2_id() {
		return account_lv2_id;
	}
	public void setAccount_lv2_id(String account_lv2_id) {
		this.account_lv2_id = account_lv2_id;
	}
	public String getAccount_lv2_name() {
		return account_lv2_name;
	}
	public void setAccount_lv2_name(String account_lv2_name) {
		this.account_lv2_name = account_lv2_name;
	}
	public String getSupplier_lv2_id() {
		return supplier_lv2_id;
	}
	public void setSupplier_lv2_id(String supplier_lv2_id) {
		this.supplier_lv2_id = supplier_lv2_id;
	}
	public String getSupplier_lv2_name() {
		return supplier_lv2_name;
	}
	public void setSupplier_lv2_name(String supplier_lv2_name) {
		this.supplier_lv2_name = supplier_lv2_name;
	}
	public String getPayment_status_code() {
		return payment_status_code;
	}
	public void setPayment_status_code(String payment_status_code) {
		this.payment_status_code = payment_status_code;
	}
	public String getPayment_status_val() {
		return payment_status_val;
	}
	public void setPayment_status_val(String payment_status_val) {
		this.payment_status_val = payment_status_val;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		if(payment_date!=null){
			this.payment_date = dateFormat1.format(payment_date);
		}else{
			this.payment_date = null;
		}
	}
	public double getPayment_ratio() {
		return payment_ratio;
	}
	public void setPayment_ratio(double payment_ratio) {
		this.payment_ratio = payment_ratio;
	}
	public String getReceipt_status_code() {
		return receipt_status_code;
	}
	public void setReceipt_status_code(String receipt_status_code) {
		this.receipt_status_code = receipt_status_code;
	}
	public String getReceipt_status_val() {
		return receipt_status_val;
	}
	public void setReceipt_status_val(String receipt_status_val) {
		this.receipt_status_val = receipt_status_val;
	}
	public String getReceipt_due_date() {
		return receipt_due_date;
	}
	public void setReceipt_due_date(Date receipt_due_date) {
		if(receipt_due_date!=null){
			this.receipt_due_date = dateFormat1.format(receipt_due_date);
		}else{
			this.receipt_due_date = null;
		}
	}
	public double getReceipt_ratio() {
		return receipt_ratio;
	}
	public void setReceipt_ratio(double receipt_ratio) {
		this.receipt_ratio = receipt_ratio;
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
