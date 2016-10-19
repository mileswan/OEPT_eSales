package com.oept.esales.service;

import java.util.List;

import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.ServiceItem;
import com.oept.esales.model.Services;
import com.oept.esales.model.User;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/3
 * Description: Services management operation service interface.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public interface ServicesService {

	/**
	 * 根据userId查询当前用户咨询服务订单请求(客户)(进行中)
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectServicesList(Object[] params) throws Exception;
	
	/**
	 * 根据userId查询当前用户咨询服务订单请求(客服)(进行中)
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectServicesListSys(Object[] params) throws Exception;
	
	/**
	 * 根据userId查询当前用户咨询服务订单请求(客户)(已完成)
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectServicesList2(Object[] params) throws Exception;
	
	/**
	 * 根据userId查询当前用户咨询服务订单请求(客服)(已完成)
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectServicesListSys2(Object[] params) throws Exception;
	
	/**
	 * 选择咨询服务人员
	 * @return
	 * @throws Exception
	 */
	public User allotConSerUser() throws Exception;
	
	/**
	 * 选择投诉服务人员
	 * @return
	 * @throws Exception
	 */
	public User allotCompUser() throws Exception;
	
	/**
	 * 添加咨询服务订单
	 * @return
	 * @throws Exception
	 */
	public int addConSerOrder(Object[] params) throws Exception;
	
	/**
	 * 查询当前用户是否为咨询服务人员
	 * @return
	 * @throws Exception
	 */
	public int  selectUserPositionConsult(Object[] params) throws Exception;
	
	/**
	 * 查询当前用户是否为投诉服务人员
	 * @return
	 * @throws Exception
	 */
	public int  selectUserPositionComplaint(Object[] params) throws Exception;
	
	/**
	 * 查询咨询服务订单详情子单列表
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<ServiceItem> selectServiceDetail(Object[] params) throws Exception;
	
	/**
	 * 查询咨询服务订单详情
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Services selectConsultDetail2(Object[] params) throws Exception;
	
	/**
	 * 创建咨询服务子订单
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int createConSerItem(Object[] params) throws Exception;
	
	/**
	 * 更改咨询服务订单状态
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateConSerStatus(Object[] params) throws Exception;
	
	/**
	 * 添加咨询回复
	 * @param params
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int addReplyContent(Object[] params,int id) throws Exception;
	
	/**
	 * 根据id查询投诉服务的集合
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectComplaintList(Object[] params, int id) throws Exception;
	
	/**
	 * 根据id查询投诉服务的集合（历史订单）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectComplaintListHis(Object[] params, int id) throws Exception;
	
	/**
	 * 查询id下所有订单编号
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<OrderFlat> selectUserOrderId(Object[] params) throws Exception;
	
	/**
	 * 查询orderId下订单详情
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public OrderFlat selectOrderDetail(Object[] params) throws Exception;
	
	/**
	 * 添加投诉订单
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int addComplaintOrder(Object[] params) throws Exception;
	
	/**
	 * 根据id查询投诉服务订单信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Services selectUserServices(Object[] params) throws Exception;
	
	/**
	 * 创建投诉子订单
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int addComplaintOrderItem(Object[] params) throws Exception;
	
	/**
	 * 添加投诉子订单
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int addReplyComplaintOrderItem(Object[] params,int id) throws Exception;
	
	/**
	 * 根据id查询投诉服务的集合（投诉级别）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectComplaintListLevel(Object[] params, int id) throws Exception;
	
	/**
	 * 根据服务id查询该订单下是否有子订单
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int selectSrvIdCompItem(Object[] params) throws Exception;
	
	/**
	 * 查找工作量最少的售后客服人员
	 * @return
	 * @throws Exception
	 */
	public User selectUserIdAss() throws Exception;
	
	/**
	 * 创建退货服务订单
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int createReturnOrder(Object[] params) throws Exception;
	
	/**
	 * 创建返修服务订单
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int createRepairOrder(Object[] params) throws Exception;
	
	/**
	 * 查询当前UserId是否为售后服务人员
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int selectUserIdAss(Object[] params) throws Exception;
	
	/**
	 * 查询退货服务列表（会员）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectReturnList(Object[] params) throws Exception;
	
	/**
	 * 查询退货服务列表（客服）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectReturnListSys(Object[] params) throws Exception;
	
	/**
	 * 查询返修服务列表（会员）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectRepairList(Object[] params) throws Exception;
	
	/**
	 * 查询返修服务列表（客服）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectRepairListSys(Object[] params) throws Exception;
	
	/**
	 * 查询所有已完成退返修服务列表（会员）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectOverReturnAllList(Object[] params) throws Exception;
	
	/**
	 * 查询所有已完成退返修服务列表（客服）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectOverReturnAllListSys(Object[] params) throws Exception;
	
	/**
	 * 查询已完成退货服务列表（会员）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectOverReturnList(Object[] params) throws Exception;
	
	/**
	 * 查询已完成返修服务列表（会员）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectOverRepairList(Object[] params) throws Exception;

	/**
	 * 查询已完成退货服务列表（客服）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectOverReturnListSys(Object[] params) throws Exception;
	
	/**
	 * 查询已完成返修服务列表（客服）
	 * @return
	 * @throws Exception
	 */
	public List<Services> selectOverRepairListSys(Object[] params) throws Exception;
	
	/**
	 * 查询服务订单详情
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Services selectServDetail(Object[] params) throws Exception;
	
	/**
	 * 更改退返货订单状态
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateRtSerStatus(Object[] params) throws Exception;
	
	/**
	 * 更改退返货订单状态
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateRtSerStatus2(Object[] params) throws Exception;
}
