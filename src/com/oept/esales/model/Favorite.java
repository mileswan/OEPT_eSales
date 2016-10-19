package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Favorite {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//Favorite item properties
	private String item_id;
	private String item_prod_id;
	private String item_user_id;
	private String item_created_date;
	private String item_created_by_user_id;
	private String item_update_date;
	private String item_update_by_user_id;
	//Product properties
	private String item_prod_number;
	private String item_prod_name;
	private String item_prod_category_id;
	private String item_prod_category_name;
	private double item_prod_price;
	private int item_prod_stock;
	private String item_prod_sku;
	//for product's images
	private String image_filename;
	private String image_original_filename;
	private String image_path;
	private String image_context_path;
	private String image_type;
	//Additional properties
	private boolean item_collected;
	
	
	public String getImage_filename() {
		return image_filename;
	}
	public void setImage_filename(String image_filename) {
		this.image_filename = image_filename;
	}
	public String getImage_original_filename() {
		return image_original_filename;
	}
	public void setImage_original_filename(String image_original_filename) {
		this.image_original_filename = image_original_filename;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getImage_context_path() {
		return image_context_path;
	}
	public void setImage_context_path(String image_context_path) {
		this.image_context_path = image_context_path;
	}
	public String getImage_type() {
		return image_type;
	}
	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}
	public boolean isItem_collected() {
		return item_collected;
	}
	public void setItem_collected(boolean item_collected) {
		this.item_collected = item_collected;
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
	public String getItem_user_id() {
		return item_user_id;
	}
	public void setItem_user_id(String item_user_id) {
		this.item_user_id = item_user_id;
	}
	public String getItem_created_date() {
		return item_created_date;
	}
	public void setItem_created_date(Timestamp item_created_date) {
		this.item_created_date = dateFormat.format(item_created_date);
	}
	public String getItem_created_by_user_id() {
		return item_created_by_user_id;
	}
	public void setItem_created_by_user_id(String item_created_by_user_id) {
		this.item_created_by_user_id = item_created_by_user_id;
	}
	public String getItem_update_date() {
		return item_update_date;
	}
	public void setItem_update_date(Timestamp item_update_date) {
		this.item_update_date = dateFormat.format(item_update_date);
	}
	public String getItem_update_by_user_id() {
		return item_update_by_user_id;
	}
	public void setItem_update_by_user_id(String item_update_by_user_id) {
		this.item_update_by_user_id = item_update_by_user_id;
	}
	public String getItem_prod_number() {
		return item_prod_number;
	}
	public void setItem_prod_number(String item_prod_number) {
		this.item_prod_number = item_prod_number;
	}
	public String getItem_prod_name() {
		return item_prod_name;
	}
	public void setItem_prod_name(String item_prod_name) {
		this.item_prod_name = item_prod_name;
	}
	public String getItem_prod_category_id() {
		return item_prod_category_id;
	}
	public void setItem_prod_category_id(String item_prod_category_id) {
		this.item_prod_category_id = item_prod_category_id;
	}
	public String getItem_prod_category_name() {
		return item_prod_category_name;
	}
	public void setItem_prod_category_name(String item_prod_category_name) {
		this.item_prod_category_name = item_prod_category_name;
	}
	public double getItem_prod_price() {
		return item_prod_price;
	}
	public void setItem_prod_price(double item_prod_price) {
		this.item_prod_price = item_prod_price;
	}
	public int getItem_prod_stock() {
		return item_prod_stock;
	}
	public void setItem_prod_stock(int item_prod_stock) {
		this.item_prod_stock = item_prod_stock;
	}
	public String getItem_prod_sku() {
		return item_prod_sku;
	}
	public void setItem_prod_sku(String item_prod_sku) {
		this.item_prod_sku = item_prod_sku;
	}
	
	
	
}
