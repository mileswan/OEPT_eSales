package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Shopcart {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//For shopping cart and item columns
	private String id;
	private String shopcart_id;
	private String user_id;
	private double price;
	private double amount;
	private int quantity;
	private String created_by_id;
	private String created_by_name;
	private String created;
	private String update_by_id;
	private String update_by_name;
	private String update;
	//For product columns
	private String product_id;
	private String product_number;
	private String product_name;
	private String product_desc;
	private String product_spec;
	private String product_sku;
	private String product_category_id;
	private String product_category_name;
	private double product_price;
	private String product_currency_code;
	private String product_currency_label;
	//For product image columns
	private String product_image_original_name;
	private String product_image_context_path;
	
	
	public String getProduct_image_original_name() {
		return product_image_original_name;
	}
	public void setProduct_image_original_name(String product_image_original_name) {
		this.product_image_original_name = product_image_original_name;
	}
	public String getProduct_image_context_path() {
		return product_image_context_path;
	}
	public void setProduct_image_context_path(String product_image_context_path) {
		this.product_image_context_path = product_image_context_path;
	}
	public String getProduct_sku() {
		return product_sku;
	}
	public void setProduct_sku(String product_sku) {
		this.product_sku = product_sku;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShopcart_id() {
		return shopcart_id;
	}
	public void setShopcart_id(String shopcart_id) {
		this.shopcart_id = shopcart_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_number() {
		return product_number;
	}
	public void setProduct_number(String product_number) {
		this.product_number = product_number;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public String getProduct_spec() {
		return product_spec;
	}
	public void setProduct_spec(String product_spec) {
		this.product_spec = product_spec;
	}
	public String getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(String product_category_id) {
		this.product_category_id = product_category_id;
	}
	public String getProduct_category_name() {
		return product_category_name;
	}
	public void setProduct_category_name(String product_category_name) {
		this.product_category_name = product_category_name;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProduct_currency_code() {
		return product_currency_code;
	}
	public void setProduct_currency_code(String product_currency_code) {
		this.product_currency_code = product_currency_code;
	}
	public String getProduct_currency_label() {
		return product_currency_label;
	}
	public void setProduct_currency_label(String product_currency_label) {
		this.product_currency_label = product_currency_label;
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
	public String getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = dateFormat.format(created);;
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
	public String getUpdate() {
		return update;
	}
	public void setUpdate(Timestamp update) {
		this.update = dateFormat.format(update);;
	}
	
}
