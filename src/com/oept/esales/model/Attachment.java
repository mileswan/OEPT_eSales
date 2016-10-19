package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author mwan
 * Version: 2.0
 * Date: 2016/1/11
 * Description: Attachment file data model.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public class Attachment {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String filename;
	private String original_filename;
	private String unique_filename;
	private String path;
	private String context_path;
	private long filesize;
	private String product_id;
	private String product_number;
	private String product_name;
	private String order_id;
	private String order_number;
	private String requisition_id;
	private String requisition_number;
	private String contract_id;
	private String contract_number;
	private String uploaded_by_user_id;
	private String uploaded_by_user;
	private String uploaded_date;
	private String uploadip;
	private String upload_source;
	private String type;
	private String image_type;
	private String image_label;
	private int sort_order;
	private String update_date;
	private String update_by_user_id;
	private String update_by_user;
	
	
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
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getContract_number() {
		return contract_number;
	}
	public void setContract_number(String contract_number) {
		this.contract_number = contract_number;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = dateFormat.format(update_date);
	}
	public String getUpdate_by_user_id() {
		return update_by_user_id;
	}
	public void setUpdate_by_user_id(String update_by_user_id) {
		this.update_by_user_id = update_by_user_id;
	}
	public String getUpdate_by_user() {
		return update_by_user;
	}
	public void setUpdate_by_user(String update_by_user) {
		this.update_by_user = update_by_user;
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
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getUnique_filename() {
		return unique_filename;
	}
	public void setUnique_filename(String unique_filename) {
		this.unique_filename = unique_filename;
	}
	public void setImage_label(String image_label) {
		this.image_label = image_label;
	}
	public String getImage_label() {
		return image_label;
	}
	public String getImage_type() {
		return image_type;
	}
	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}
	public int getSort_order() {
		return sort_order;
	}
	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOriginal_filename() {
		return original_filename;
	}
	public void setOriginal_filename(String original_filename) {
		this.original_filename = original_filename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getContextpath() {
		return context_path;
	}
	public void setContextpath(String context_path) {
		this.context_path = context_path;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getUploaded_by_user_id() {
		return uploaded_by_user_id;
	}
	public void setUploaded_by_user_id(String uploaded_by_user_id) {
		this.uploaded_by_user_id = uploaded_by_user_id;
	}
	public String getUploaded_by_user() {
		return uploaded_by_user;
	}
	public void setUploaded_by_user(String uploaded_by_user) {
		this.uploaded_by_user = uploaded_by_user;
	}
	public String getUploaded_date() {
		return uploaded_date;
	}
	public void setUploaded_date(Timestamp uploaded_date) {
		this.uploaded_date =  dateFormat.format(uploaded_date);
	}
	public String getUploadip() {
		return uploadip;
	}
	public void setUploadip(String uploadip) {
		this.uploadip = uploadip;
	}
	public String getUpload_source() {
		return upload_source;
	}
	public void setUpload_source(String upload_source) {
		this.upload_source = upload_source;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
