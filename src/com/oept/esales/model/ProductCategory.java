package com.oept.esales.model;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author zhujj
 * Version: 1.0
 * Date: 2016/01/20
 * Description: Products data model.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public class ProductCategory {
	private String id;
	private String name;
	private String active;
	private String desc;
	private String par_id;
	private String created;
	private String created_by;
	private String update;
	private String update_by;
	private String cat_lvl;
	
	private int maxLvl;
	
	
	
	public int getMaxLvl() {
		return maxLvl;
	}
	public void setMaxLvl(int maxLvl) {
		this.maxLvl = maxLvl;
	}
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPar_id() {
		return par_id;
	}
	public void setPar_id(String par_id) {
		this.par_id = par_id;
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
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public String getCat_lvl() {
		return cat_lvl;
	}
	public void setCat_lvl(String cat_lvl) {
		this.cat_lvl = cat_lvl;
	}
	
	
}
