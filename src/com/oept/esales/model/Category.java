package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/13
 * Description: Categories data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class Category {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String name;
	private Boolean active;
	private String desc;
	private String parentCat;
	private String parentCatId;
	private String createdBy;
	private String createdById;
	private String created;
	private String update;
	private String updateBy;
	private String updateById;
	private int hierlvl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getParentCat() {
		return parentCat;
	}
	public void setParentCat(String parentCat) {
		this.parentCat = parentCat;
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
	public String getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(String parentCatId) {
		this.parentCatId = parentCatId;
	}
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
	public int getHierlvl() {
		return hierlvl;
	}
	public void setHierlvl(int hierlvl) {
		this.hierlvl = hierlvl;
	}
	
}
