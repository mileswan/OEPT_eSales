package com.oept.esales.model;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/27
 * Description: Categories data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class Position {
	private String positionId;
	private String positionName;
	private String parentPositionId;
	private String created;
	private String update;
	private String createdBy;
	private String updateBy;
	
	private String parentPositionName;
	private String createdName;
	private String updateName;
	
	public String getParentPositionName() {
		return parentPositionName;
	}
	public void setParentPositionName(String parentPositionName) {
		this.parentPositionName = parentPositionName;
	}
	public String getCreatedName() {
		return createdName;
	}
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getParentPositionId() {
		return parentPositionId;
	}
	public void setParentPositionId(String parentPositionId) {
		this.parentPositionId = parentPositionId;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
}
