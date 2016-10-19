package com.oept.esales.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.ServiceItem;
import com.oept.esales.model.Services;
import com.oept.esales.model.User;
import com.oept.esales.service.ServicesService;

/**
 * @author zhujj 
 * Version: 1.0 
 * Date: 2015/12/2 
 * Description: Service management operation. 
 * Copyright (c) 2015 上海基辕科技有限公司. 
 * All rights reserved.
 */
@Controller
@RequestMapping(value = "/service")
public class ServiceAct {

	@Qualifier("servicesService")
	@Autowired
	private ServicesService servicesService;

	private static final Log logger = LogFactory.getLog(UserAct.class);

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/list.do")
	public String serviceList(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String userId = String.valueOf(session.getAttribute("userid"));
		try {
			String type = request.getParameter("type");
			Object[] params = new Object[] { userId };
			if (type.equals("consult")) {
				try {
					// 查询当前用户是否为服务咨询ID
					int consult = servicesService
							.selectUserPositionConsult(params);
					if (consult == 1) {
						// 当前用户为服务咨询ID
						// 咨询服务进行中列表
						List<Services> services = servicesService
								.selectServicesListSys(params);
						// 咨询服务已完成列表
						List<Services> services2 = servicesService
								.selectServicesListSys2(params);
						model.addAttribute("services", services);
						model.addAttribute("services2", services2);
						// 是否为服务咨询ID
						model.addAttribute("userType", consult);
					} else {
						// 当前用户
						// 咨询服务进行中列表
						List<Services> services = servicesService
								.selectServicesList(params);
						// 咨询服务已完成列表
						List<Services> services2 = servicesService
								.selectServicesList2(params);
						model.addAttribute("services", services);
						model.addAttribute("services2", services2);
						// 是否为服务咨询ID
						model.addAttribute("userType", consult);
					}
				} catch (Exception e) {
					// TODO: handle exception
					logger.info(e.getMessage());
					throw e;
				}
				return "services/serConsult";
			} else if (type.equals("complaint")) {
				try {
					// 查询当前用户是否为服务投诉ID
					int id = servicesService
							.selectUserPositionComplaint(params);
					// 调用servicesService根据id查询投诉服务的集合
					List<Services> sList = servicesService.selectComplaintList(
							params, id);
					model.addAttribute("scList", sList);
					//查询投诉服务历史集合
					List<Services> hsList = servicesService.selectComplaintListHis(params, id);
					model.addAttribute("hsList", hsList);
					// 调用servicesService查询id下所有订单编号
					List<OrderFlat> oList = servicesService
							.selectUserOrderId(params);
					model.addAttribute("oList", oList);
					model.addAttribute("userType", id);
					return "services/services_complaint";
				} catch (Exception e) {
					// TODO: handle exception
					logger.info(e.getMessage());
					throw e;
				}

			}else if (type.equals("return")) {
				try {
					//查询当前用户是否为售后服务人员
					int res = servicesService.selectUserIdAss(params);
					if(res == 0){
						//不为客服人员
						//退货列表
						model.addAttribute("rtList",servicesService.selectReturnList(params));
						//返修列表
						model.addAttribute("rpList",servicesService.selectRepairList(params));
						//历史订单
						model.addAttribute("oraList",servicesService.selectOverReturnAllList(params));
						//历史退货订单
						model.addAttribute("ortList", servicesService.selectOverReturnList(params));
						//历史返修订单
						model.addAttribute("orpList", servicesService.selectOverRepairList(params));
						return "services/services_return";
					}else{
						//为客服人员
						//退货列表
						model.addAttribute("rtsList",servicesService.selectReturnListSys(params));
						//返修列表
						model.addAttribute("rpsList",servicesService.selectRepairListSys(params));
						//历史订单
						model.addAttribute("orasList",servicesService.selectOverReturnAllListSys(params));
						//历史退货订单
						model.addAttribute("ortsList", servicesService.selectOverReturnListSys(params));
						//历史返修订单
						model.addAttribute("orpsList", servicesService.selectOverRepairListSys(params));
						return "services/services_return_dispose";
					}
				} catch (Exception e) {
					// TODO: handle exception
					logger.info(e.getMessage());
					throw e;
				}
				
			}else if (type.equals("123")) {
				// 调用servicesService查询id下所有订单编号
				List<OrderFlat> oList = servicesService
						.selectUserOrderId(params);
				model.addAttribute("oList", oList);
				return "services/services_return_apply";
			} else {
				return "";
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}

	}

	/**
	 * 申请咨询服务
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/createConsultSer.do")
	@ResponseBody
	public int createConsultSer(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serName = request.getParameter("serName");
		String serDesc = request.getParameter("serDesc");
		String serType = request.getParameter("serType");
		String serDetail = request.getParameter("serDetail");
		String userId = String.valueOf(session.getAttribute("userid"));
		int res = 0;
		try {
			// 查询业务量最少的咨询服务业务员
			User user = servicesService.allotConSerUser();
			Object[] params = new Object[] { serName, serDesc, serType,
					user.getUserId(), userId, serDetail };
			// 添加咨询服务订单
			res = servicesService.addConSerOrder(params);

		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return res;
	}

	/**
	 * 查询咨询服务订单详情
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/consultDetails.do")
	public String selectConsultDetails(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serviceId = request.getParameter("serviceId");
		String userId = String.valueOf(session.getAttribute("userid"));
		int res = 0;
		try {
			Object[] params = new Object[] { userId };
			Object[] params2 = new Object[] { serviceId };
			// 查询当前用户是否为服务咨询ID
			res = servicesService.selectUserPositionConsult(params);
			if (res == 1) {
				// 返回咨询服务订单详细信息
				List<ServiceItem> serviceItem = servicesService
						.selectServiceDetail(params2);
				Services services = servicesService
						.selectConsultDetail2(params2);
				model.addAttribute("consultDetail", serviceItem);
				model.addAttribute("services", services);
				model.addAttribute("userType", res);
				// 查询用户名称
				return "services/serConsultItem";
			} else {
				// 返回咨询服务订单详细信息
				List<ServiceItem> serviceItem = servicesService
						.selectServiceDetail(params2);
				Services services = servicesService
						.selectConsultDetail2(params2);
				model.addAttribute("consultDetail", serviceItem);
				model.addAttribute("services", services);
				model.addAttribute("userType", res);
				return "services/serConsultItem";
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}

	/**
	 * 更改咨询服务状态
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/createConSerItem.do")
	@ResponseBody
	public int createConSerItem(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serviceId = request.getParameter("serviceId");
		int res = 0;
		try {
			String status = "2";
			Object[] params3 = new Object[] { status, serviceId };
			// 更改咨询服务状态
			res = servicesService.updateConSerStatus(params3);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return res;
	}

	/**
	 * 发表回复
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/replyContent.do")
	@ResponseBody
	public int replyContent(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serviceId = request.getParameter("serviceId");
		String replyContent = request.getParameter("replyContent");
		String userId = String.valueOf(session.getAttribute("userid"));

		try {
			Object[] params = new Object[] { userId };
			Object[] params2 = new Object[] { serviceId };
			// 获取咨询订单信息
			Services services = servicesService.selectConsultDetail2(params2);
			Object[] params3 = new Object[] { services.getServiceName(),
					services.getServiceDesc(), replyContent, serviceId, userId };
			// 查询当前用户是否为服务咨询ID
			int id = servicesService.selectUserPositionConsult(params);
			return servicesService.addReplyContent(params3, id);

		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}

	/**
	 * 咨询结束更改订单状态
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateServiceStatus.do")
	@ResponseBody
	public int updateServiceStatus(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serviceId = request.getParameter("serviceId");

		try {
			String status = "3";
			Object[] params = new Object[] { status, serviceId };
			// 调用修改咨询订单状态
			return servicesService.updateConSerStatus(params);

		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}

	/**
	 * 申请投诉
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/createComplaint.do")
	@ResponseBody
	public int createComplaint(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {

		String orderId = request.getParameter("orderId");
		String compType = request.getParameter("compType");
		String serDesc = request.getParameter("serDesc");
		String serDetail = request.getParameter("serDetail");
		String userId = String.valueOf(session.getAttribute("userid"));
		String levelVal = request.getParameter("select_level");
		String serName = "投诉";
		String serType = "投诉";
		String serStatus = "1";
		String levelName;
		if (levelVal.equals("1")) {
			levelName = "一般投诉";
		} else if (levelVal.equals("2")) {
			levelName = "紧急投诉";
		} else {
			levelName = "严重投诉";
		}
		try {
			// 查询业务量最少的投诉服务业务员
			User user = servicesService.allotCompUser();
			Object[] params = new Object[] { serName, serDesc, serType,
					compType, serStatus, user.getUserId(), userId, serDetail,
					orderId, levelVal, levelName };
			return servicesService.addComplaintOrder(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}

	/**
	 * 查看投诉详细信息
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/complaintDetail.do")
	public String complaintOrderItem(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		int stype = Integer.valueOf(request.getParameter("stype"));
		String serviceId = request.getParameter("serviceId");
		String userId = String.valueOf(session.getAttribute("userid"));
		Object[] params = new Object[] { serviceId };
		Object[] params4 = new Object[] { userId };
		// 查询当前用户是否为投诉服务ID
		int uType = servicesService.selectUserPositionComplaint(params4);
		model.addAttribute("uType", uType);
		try {
			if (stype == 1) {
				// 用户类型为客服

				// 根据服务订单id查询该订单详情
				Services services = servicesService.selectUserServices(params);
				model.addAttribute("services", services);
				Object[] params2 = new Object[] { services.getServiceName(),
						services.getServiceDesc(),
						services.getServiceComment(), services.getServiceId(),
						services.getServiceCreated(),
						services.getServiceCreatedBy() };
				Object[] params6 = new Object[] { services.getServiceId() };
				// 根据订单id查询如果子订单中有数据就不添加
				int count = servicesService.selectSrvIdCompItem(params6);
				if (count == 0) {
					// 受理后创建投诉子订单
					int res = servicesService.addComplaintOrderItem(params2);
					if (res == 1) {
						String status = "2";
						Object[] params3 = new Object[] { status, serviceId };
						// 更改投诉订单状态
						int res2 = servicesService.updateConSerStatus(params3);
					}
				}
				// 查询该投诉订单下所有子订单信息
				List<ServiceItem> srvItem = servicesService
						.selectServiceDetail(params);
				model.addAttribute("siList", srvItem);

			} else {
				// 用户类型为会员
				// 根据服务订单id查询该订单详情
				Services services = servicesService.selectUserServices(params);
				model.addAttribute("services", services);
				// 查询该投诉订单下所有子订单信息
				List<ServiceItem> srvItem = servicesService
						.selectServiceDetail(params);
				model.addAttribute("siList", srvItem);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}

		return "services/services_complaint_item";
	}

	/**
	 * 回复投诉
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/replyComplaint.do")
	@ResponseBody
	public int replyComplaint(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serviceId = request.getParameter("serviceId");
		String replyComplaint = request.getParameter("replyComplaint");
		String userId = String.valueOf(session.getAttribute("userid"));
		Object[] params = new Object[] { serviceId };
		Object[] params2 = new Object[] { userId };
		try {
			// 根据服务订单id查询该订单详情
			Services services = servicesService.selectUserServices(params);
			Object[] params3 = new Object[] { services.getServiceName(),
					services.getServiceDesc(), replyComplaint,
					services.getServiceId(), userId };
			// 查询当前用户是否为投诉服务ID
			int id = servicesService.selectUserPositionComplaint(params2);
			return servicesService.addReplyComplaintOrderItem(params3, id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;

		}
	}

	/**
	 * 咨询结束更改订单状态(投诉)
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateServiceStatusComp.do")
	@ResponseBody
	public int updateServiceStatusComp(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serviceId = request.getParameter("serviceId");

		try {
			String status = "3";
			Object[] params = new Object[] { status, serviceId };
			// 调用修改咨询订单状态
			return servicesService.updateConSerStatus(params);

		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}

	/**
	 * 根据投诉级别不同查询不同数据
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/selectQuery.do")
	public String selectQuery(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String level = request.getParameter("level");
		String userId = String.valueOf(session.getAttribute("userid"));
		try {
			Object[] params = new Object[] { userId };
			Object[] params2 = new Object[] { userId, level };
			// 查询当前用户是否为服务投诉ID
			int id = servicesService.selectUserPositionComplaint(params);
			// 调用servicesService根据id查询投诉服务的集合
			List<Services> sList = servicesService.selectComplaintListLevel(
					params2, id);
			model.addAttribute("scList", sList);
			// 调用servicesService查询id下所有订单编号
			List<OrderFlat> oList = servicesService.selectUserOrderId(params);
			//查询投诉服务历史集合
			List<Services> hsList = servicesService.selectComplaintListHis(params, id);
			model.addAttribute("hsList", hsList);
			model.addAttribute("level", level);
			model.addAttribute("oList", oList);
			model.addAttribute("userType", id);
			return "services/services_complaint";
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 根据orderId查询订单详情
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectOrderDetail.do")
	public String selectOrderDetail(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String orderId = request.getParameter("orderId");
		String type = request.getParameter("type");
		try {
			Object[] params = new Object[]{
					orderId	
				};
			//调用service查询orderId订单详细信息获取订单金额
			OrderFlat order = servicesService.selectOrderDetail(params);
			model.addAttribute("od", order);
			model.addAttribute("type",type);
			return "services/services_return_apply";
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 创建退货服务订单
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/returnApply.do")
	@ResponseBody
	public int returnApply(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String orderId = request.getParameter("orderId");
		String returnDesc = request.getParameter("returnDesc");
		String returnComm = request.getParameter("returnComm");
		String userId = String.valueOf(session.getAttribute("userid"));
		String serName = "退返货";
		String serType = "退货";
		String serStatus = "1";
		try {
			//调用service查询最少工作量的售后客服人员id
			User user = servicesService.selectUserIdAss();
			Object[] params = new Object[]{
					serName,returnDesc,serType,serStatus,user.getUserId(),userId,returnComm,orderId
			};
			//调用service创建退货服务订单
			return servicesService.createReturnOrder(params); 
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 创建返修服务订单
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/repairApply.do")
	@ResponseBody
	public int repairApply(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String orderId = request.getParameter("orderId");
		String repairDesc = request.getParameter("repairDesc");
		String repairComm = request.getParameter("repairComm");
		String userId = String.valueOf(session.getAttribute("userid"));
		String serName = "退返货";
		String serType = "返修";
		String serStatus = "1";
		try {
			//调用service查询最少工作量的售后客服人员id
			User user = servicesService.selectUserIdAss();
			Object[] params = new Object[]{
					serName,repairDesc,serType,serStatus,user.getUserId(),userId,repairComm,orderId
			};
			//调用service创建退货服务订单
			return servicesService.createRepairOrder(params); 
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 查询服务订单详情
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectServDetail.do")
	public String selectServDetail(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serviceId = request.getParameter("id");
		Object[] params = new Object[]{
				serviceId
		};
		try {
			//调用service查询服务订单详情
			model.addAttribute("sd", servicesService.selectServDetail(params));
			return "services/services_return_details";
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 处理退返修申请
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/disposeReturn.do")
	@ResponseBody
	public int disposeReturn(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serviceId = request.getParameter("serviceId");
		String type = request.getParameter("type");
		try {
			Object[] params = new Object[]{
					type,serviceId
			};
			return servicesService.updateRtSerStatus(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 处理退返修申请
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/disposeReturn2.do")
	@ResponseBody
	public int disposeReturn2(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String serviceId = request.getParameter("serviceId");
		String type = request.getParameter("type");
		String userId = String.valueOf(session.getAttribute("userid"));
		try {
			Object[] params = new Object[]{
					type,userId,serviceId
			};
			return servicesService.updateRtSerStatus2(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
}
