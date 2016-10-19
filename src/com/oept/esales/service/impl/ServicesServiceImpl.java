package com.oept.esales.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oept.esales.action.UserAct;
import com.oept.esales.dao.ServicesDao;
import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.ServiceItem;
import com.oept.esales.model.Services;
import com.oept.esales.model.User;
import com.oept.esales.service.ServicesService;


/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/16
 * Description: User management operation service implements.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
@Service("servicesService")
public class ServicesServiceImpl implements ServicesService{
	private static final Log logger = LogFactory.getLog(UserAct.class);

	@Autowired
	private ServicesDao servicesDao;

	/**
	 * 根据userId查询当前用户服务订单请求(客户)(进行中)
	 */
	@Override
	public List<Services> selectServicesList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectServicesList(params);
	}
	
	/**
	 * 根据userId查询当前用户服务订单请求(客服)(进行中)
	 */
	@Override
	public List<Services> selectServicesListSys(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectServicesListSys(params);
	}
	
	/**
	 * 根据userId查询当前用户服务订单请求(客户)(已完成)
	 */
	@Override
	public List<Services> selectServicesList2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectServicesList2(params);
	}
	
	/**
	 * 根据userId查询当前用户服务订单请求(客服)(已完成)
	 */
	@Override
	public List<Services> selectServicesListSys2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectServicesListSys2(params);
	}
	
	/**
	 * 选择咨询服务人员
	 */
	@Override
	public User allotConSerUser() throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectConSerUser();
	}
	
	/**
	 * 选择投诉服务人员
	 */
	@Override
	public User allotCompUser() throws Exception {
		return servicesDao.selectCompUser();
	}

	/**
	 * 添加咨询服务订单
	 */
	@Override
	public int addConSerOrder(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.addConSerOrder(params);
	}

	/**
	 * 查询当前用户是否为咨询服务人员
	 */
	@Override
	public int selectUserPositionConsult(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectUserPositionConsult(params);
	}
	
	/**
	 * 查询当前用户是否为投诉服务人员
	 */
	@Override
	public int selectUserPositionComplaint(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectUserPositionComplaint(params);
	}

	/**
	 * 查询咨询服务订单详情子单列表
	 */
	@Override
	public List<ServiceItem> selectServiceDetail(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectServiceDetail(params);
	}

	/**
	 * 创建咨询服务子订单
	 */
	@Override
	public int createConSerItem(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.createConSerItem(params);
	}

	/**
	 * 更改咨询服务订单状态
	 */
	@Override
	public int updateConSerStatus(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.updateConSerStatus(params);
	}

	/**
	 * 查询咨询服务订单详情
	 */
	@Override
	public Services selectConsultDetail2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectConsultDetail2(params);
	}

	/**
	 * 添加咨询回复
	 */
	@Override
	public int addReplyContent(Object[] params, int id) throws Exception {
		// TODO Auto-generated method stub
		if(id == 0){
			//userid为顾客
			return servicesDao.createConSerItem2(params);
		}else if(id == 1){
			//userid为客服
			return servicesDao.createConSerItem(params);
		}
		return 2;
	}

	/**
	 * 根据id查询投诉服务的集合
	 */
	@Override
	public List<Services> selectComplaintList(Object[] params, int id) throws Exception {
		// TODO Auto-generated method stub
		if(id == 0){
			return servicesDao.selectComplaintList(params);
		}else if(id == 1){
			return servicesDao.selectComplaintList2(params);
		}else{
			return null;
		}
		
	}
	
	/**
	 * 根据id查询投诉服务的集合
	 */
	@Override
	public List<Services> selectComplaintListHis(Object[] params, int id) throws Exception {
		// TODO Auto-generated method stub
		if(id == 0){
			return servicesDao.selectComplaintListHis(params);
		}else if(id == 1){
			return servicesDao.selectComplaintListHis2(params);
		}else{
			return null;
		}
		
	}

	/**
	 * 查询id下所有订单编号
	 */
	@Override
	public List<OrderFlat> selectUserOrderId(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectUserOrderId(params);
	}

	/**
	 * 添加投诉订单
	 */
	@Override
	public int addComplaintOrder(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.addComplaintOrder(params);
	}

	/**
	 * 创建投诉子订单
	 */
	@Override
	public int addComplaintOrderItem(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.addComplaintOrderItem(params);
	}

	/**
	 * 根据id查询投诉服务订单信息
	 */
	@Override
	public Services selectUserServices(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectUserServices(params);
	}

	/**
	 * 添加投诉子订单
	 */
	@Override
	public int addReplyComplaintOrderItem(Object[] params,int id) throws Exception {
		// TODO Auto-generated method stub
		if(id == 1){
			return servicesDao.addReplyComplaintOrderItem2(params);
		}else{
			return servicesDao.addReplyComplaintOrderItem(params);
		}
	}

	/**
	 * 根据id查询投诉服务的集合
	 */
	@Override
	public List<Services> selectComplaintListLevel(Object[] params, int id) throws Exception {
		// TODO Auto-generated method stub
		if(id == 0){
			return servicesDao.selectComplaintListLevel(params);
		}else if(id == 1){
			return servicesDao.selectComplaintListLevel2(params);
		}else{
			return null;
		}
		
	}
	
	/**
	 * 根据服务id查询该订单下是否有子订单
	 */
	@Override
	public int selectSrvIdCompItem(Object[] params) throws Exception {
		return servicesDao.selectSrvIdCompItem(params);
	}

	/**
	 * 查询orderId下订单详情
	 */
	@Override
	public OrderFlat selectOrderDetail(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectOrderDetail(params);
	}

	/**
	 * 查找工作量最少的售后客服人员
	 */
	@Override
	public User selectUserIdAss() throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectUserIdAss();
	}

	/**
	 * 创建退货服务订单
	 */
	@Override
	public int createReturnOrder(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.createReturnOrder(params);
	}

	/**
	 * 创建返修服务订单
	 */
	@Override
	public int createRepairOrder(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.createRepairOrder(params);
	}

	/**
	 * 添加投诉子订单（会员）
	 */
	@Override
	public List<Services> selectReturnList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectReturnList(params);
	}

	/**
	 * 添加投诉子订单（客服）
	 */
	@Override
	public List<Services> selectReturnListSys(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectReturnListSys(params);
	}

	/**
	 * 根据id查询投诉服务的集合（投诉级别）（会员）
	 */
	@Override
	public List<Services> selectRepairList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectRepairList(params);
	}

	/**
	 * 根据id查询投诉服务的集合（投诉级别）（客服）
	 */
	@Override
	public List<Services> selectRepairListSys(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectRepairListSys(params);
	}

	/**
	 * 根据服务id查询该订单下是否有子订单
	 */
	@Override
	public List<Services> selectOverReturnAllList(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectOverReturnAllList(params);
	}

	/**
	 * 查询orderId下订单详情
	 */
	@Override
	public List<Services> selectOverReturnAllListSys(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectOverReturnAllListSys(params);
	}

	/**
	 * 创建退货服务订单
	 */
	@Override
	public List<Services> selectOverReturnList(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectOverReturnList(params);
	}

	/**
	 * 创建返修服务订单
	 */
	@Override
	public List<Services> selectOverRepairList(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectOverRepairList(params);
	}

	/**
	 * 创建返修服务订单
	 */
	@Override
	public List<Services> selectOverReturnListSys(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectOverReturnListSys(params);
	}

	/**
	 * 查询退货服务列表（会员）
	 */
	@Override
	public List<Services> selectOverRepairListSys(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectOverRepairListSys(params);
	}

	/**
	 *  查询当前UserId是否为售后服务人员
	 */
	@Override
	public int selectUserIdAss(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectUserIdAss(params);
	}

	/**
	 * 查询服务订单详情
	 */
	@Override
	public Services selectServDetail(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.selectServDetail(params);
	}

	/**
	 * 更改退返货订单状态
	 */
	@Override
	public int updateRtSerStatus(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.updateRtSerStatus(params);
	}
	
	/**
	 * 更改退返货订单状态
	 */
	@Override
	public int updateRtSerStatus2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return servicesDao.updateRtSerStatus2(params);
	}

}
