package com.oept.esales.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.RequisitionFlat;
import com.oept.esales.service.InventoryService;
import com.oept.esales.service.OrderService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/9
 * Description: Dashboard center actions.
 * Copyright (c) 2015 mwan. All rights reserved.
 */
@Controller
@RequestMapping(value="/dashboard")
public class DashboardAct {
	
	private static final Log logger = LogFactory.getLog(DashboardAct.class);
	
	@Qualifier("orderService")
	@Autowired
	private OrderService orderService;
	@Qualifier("inventoryService")
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(value="/list.do",method = RequestMethod.GET)
	public String dashboard(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<OrderFlat> recentOrdersList = orderService.getRecentOrders(session.getAttribute("userid").toString());
			List<RequisitionFlat> recentReqsList = inventoryService.getRecentRequisitions(session.getAttribute("userid").toString());
			
			model.addAttribute("recentOrdersList", recentOrdersList);
			model.addAttribute("recentReqsList", recentReqsList);
			model.addAttribute("username", session.getAttribute("username"));
			
			return "dashboard";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
}
