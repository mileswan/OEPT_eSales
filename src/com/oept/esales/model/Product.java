package com.oept.esales.model;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/13
 * Description: Products data model.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public class Product {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String number;
	private String name;
	private String desc;
	private String spec;
	private String model;
	private String categoryId;
	private String categoryName;
	private double price;
	private String currency_code;
	private String currency_label;
	private String createById;
	private String createdBy;
	private String created;
	private String status;
	private String status_val;
	private String update;
	private String updateBy;
	private String updateById;
	private String sku;
	private int stock;
	private int ordered_stock_in;
	private int ordered_stock_out;
	private boolean deleteFlg;
	private Date validstart;
	private Date validend;
	//for filter in list
	private String product_price_from;
	private String product_price_to;
	private String product_quantity_from;
	private String product_quantity_to;
	private String product_created_from;
	private String product_created_to;
	//for filter in portfolio
	private boolean check_valid_date;
	private boolean check_collected;
	//for product's images
	private String image_filename;
	private String image_original_filename;
	private String image_path;
	private String image_context_path;
	private String image_type;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getStatus_val() {
		return status_val;
	}
	public void setStatus_val(String status_val) {
		this.status_val = status_val;
	}
	public int getOrdered_stock_in() {
		return ordered_stock_in;
	}
	public void setOrdered_stock_in(int ordered_stock_in) {
		this.ordered_stock_in = ordered_stock_in;
	}
	public int getOrdered_stock_out() {
		return ordered_stock_out;
	}
	public void setOrdered_stock_out(int ordered_stock_out) {
		this.ordered_stock_out = ordered_stock_out;
	}
	public boolean isCheck_collected() {
		return check_collected;
	}
	public void setCheck_collected(boolean check_collected) {
		this.check_collected = check_collected;
	}
	public String getCurrency_label() {
		return currency_label;
	}
	public void setCurrency_label(String currency_label) {
		this.currency_label = currency_label;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
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
	public boolean isCheck_valid_date() {
		return check_valid_date;
	}
	public void setCheck_valid_date(boolean check_valid_date) {
		this.check_valid_date = check_valid_date;
	}
	public String getProduct_price_from() {
		return product_price_from;
	}
	public void setProduct_price_from(String product_price_from) {
		this.product_price_from = product_price_from;
	}
	public String getProduct_price_to() {
		return product_price_to;
	}
	public void setProduct_price_to(String product_price_to) {
		this.product_price_to = product_price_to;
	}
	public String getProduct_quantity_from() {
		return product_quantity_from;
	}
	public void setProduct_quantity_from(String product_quantity_from) {
		this.product_quantity_from = product_quantity_from;
	}
	public String getProduct_quantity_to() {
		return product_quantity_to;
	}
	public void setProduct_quantity_to(String product_quantity_to) {
		this.product_quantity_to = product_quantity_to;
	}
	public String getProduct_created_from() {
		return product_created_from;
	}
	public void setProduct_created_from(String product_created_from) {
		this.product_created_from = product_created_from;
	}
	public String getProduct_created_to() {
		return product_created_to;
	}
	public void setProduct_created_to(String product_created_to) {
		this.product_created_to = product_created_to;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCreateById() {
		return createById;
	}
	public void setCreateById(String createById) {
		this.createById = createById;
	}
	public String getUpdateById() {
		return updateById;
	}
	public void setUpdateById(String updateById) {
		this.updateById = updateById;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = dateFormat.format(created);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(Timestamp update) {
		this.update = dateFormat.format(update);
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean isDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public Date getValidstart() {
		return validstart;
	}
	public void setValidstart(Date validstart) {
		this.validstart = validstart;
	}
	public Date getValidend() {
		return validend;
	}
	public void setValidend(Date validend) {
		this.validend = validend;
	}
}
