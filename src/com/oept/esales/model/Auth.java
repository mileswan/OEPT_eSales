package com.oept.esales.model;

import java.io.Serializable;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/30
 * Description: Categories data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class Auth implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//osa_perm
	private String id;
	private String perm_code;
	private String perm_name;
	private String par_id;
	private String perm_lvl;
	private String perm_created;
	private String perm_updated;
	
	//osa_perm_postn
	private String post_id;
	private String perm_id;
	private String position_id;
	private String post_created;
	private String post_created_by;
	private String post_updated;
	private String post_updated_by;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPerm_code() {
		return perm_code;
	}
	public void setPerm_code(String perm_code) {
		this.perm_code = perm_code;
	}
	public String getPerm_name() {
		return perm_name;
	}
	public void setPerm_name(String perm_name) {
		this.perm_name = perm_name;
	}
	public String getPar_id() {
		return par_id;
	}
	public void setPar_id(String par_id) {
		this.par_id = par_id;
	}
	public String getPerm_lvl() {
		return perm_lvl;
	}
	public void setPerm_lvl(String perm_lvl) {
		this.perm_lvl = perm_lvl;
	}
	public String getPerm_created() {
		return perm_created;
	}
	public void setPerm_created(String perm_created) {
		this.perm_created = perm_created;
	}
	public String getPerm_updated() {
		return perm_updated;
	}
	public void setPerm_updated(String perm_updated) {
		this.perm_updated = perm_updated;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public String getPerm_id() {
		return perm_id;
	}
	public void setPerm_id(String perm_id) {
		this.perm_id = perm_id;
	}
	public String getPosition_id() {
		return position_id;
	}
	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
	public String getPost_created() {
		return post_created;
	}
	public void setPost_created(String post_created) {
		this.post_created = post_created;
	}
	public String getPost_created_by() {
		return post_created_by;
	}
	public void setPost_created_by(String post_created_by) {
		this.post_created_by = post_created_by;
	}
	public String getPost_updated() {
		return post_updated;
	}
	public void setPost_updated(String post_updated) {
		this.post_updated = post_updated;
	}
	public String getPost_updated_by() {
		return post_updated_by;
	}
	public void setPost_updated_by(String post_updated_by) {
		this.post_updated_by = post_updated_by;
	}
	
}
