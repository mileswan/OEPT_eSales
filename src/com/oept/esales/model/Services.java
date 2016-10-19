package com.oept.esales.model;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/3
 * Description: Categories data model.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public class Services {
	private String serviceId;	//服务订单id
	private String serviceName;	//服务订单名称
	private String serviceDesc;	//服务订单描述
	private String serviceType;	//服务订单类型
	private String serviceStatus;	//服务订单状态
	private String serviceOwner; 	//服务订单负责人id
	private String serviceCreated;	//服务订单创建时间
	private String serviceCreatedBy;//服务订单创建人id
	private String serviceUpdate;	//服务订单更新时间
	private String serviceUpdateBy;	//服务订单更新人id
	private String serviceComment;	//服务订单详细内容
	private String serviceSubtype;	//服务订单投诉原因类型
	private String orderId;		//订单编号
	private String levelVal;	//投诉级别
	private String levelName;	//投诉级别名称
	
	private OrderFlat order;	//订单
	
	private String serviceOwnerName;//服务订单负责人账户名

	
	public OrderFlat getOrder() {
		return order;
	}

	public void setOrder(OrderFlat order) {
		this.order = order;
	}

	public String getServiceSubtype() {
		return serviceSubtype;
	}

	public void setServiceSubtype(String serviceSubtype) {
		this.serviceSubtype = serviceSubtype;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getLevelVal() {
		return levelVal;
	}

	public void setLevelVal(String levelVal) {
		this.levelVal = levelVal;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getServiceOwner() {
		return serviceOwner;
	}

	public void setServiceOwner(String serviceOwner) {
		this.serviceOwner = serviceOwner;
	}

	public String getServiceCreated() {
		return serviceCreated;
	}

	public void setServiceCreated(String serviceCreated) {
		this.serviceCreated = serviceCreated;
	}

	public String getServiceCreatedBy() {
		return serviceCreatedBy;
	}

	public void setServiceCreatedBy(String serviceCreatedBy) {
		this.serviceCreatedBy = serviceCreatedBy;
	}

	public String getServiceUpdate() {
		return serviceUpdate;
	}

	public void setServiceUpdate(String serviceUpdate) {
		this.serviceUpdate = serviceUpdate;
	}

	public String getServiceUpdateBy() {
		return serviceUpdateBy;
	}

	public void setServiceUpdateBy(String serviceUpdateBy) {
		this.serviceUpdateBy = serviceUpdateBy;
	}

	
	public String getServiceComment() {
		return serviceComment;
	}

	public void setServiceComment(String serviceComment) {
		this.serviceComment = serviceComment;
	}

	public String getServiceOwnerName() {
		return serviceOwnerName;
	}

	public void setServiceOwnerName(String serviceOwnerName) {
		this.serviceOwnerName = serviceOwnerName;
	}
	
	
}
