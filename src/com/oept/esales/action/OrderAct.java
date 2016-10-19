package com.oept.esales.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oept.esales.model.Account;
import com.oept.esales.model.Address;
import com.oept.esales.model.ApprovalDetail;
import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.ApprovalStep;
import com.oept.esales.model.Attachment;
import com.oept.esales.model.ListOfValue;
import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.OrderHistory;
import com.oept.esales.model.Product;
import com.oept.esales.model.RequisitionFlat;
import com.oept.esales.model.User;
import com.oept.esales.model.WarehouseFlat;
import com.oept.esales.service.AccountService;
import com.oept.esales.service.AuthService;
import com.oept.esales.service.InventoryService;
import com.oept.esales.service.MSOfficeService;
import com.oept.esales.service.OrderService;
import com.oept.esales.service.ProductService;
import com.oept.esales.service.PurchaseService;
import com.oept.esales.service.SystemService;
import com.oept.esales.service.UserService;
import com.oept.esales.service.EmailService;
/**
 * @author mwan
 * Version: 2.0
 * Date: 2016/2/16
 * Description: Orders administration actions.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Controller
@RequestMapping(value="/order")
public class OrderAct {
	
	private static final Log logger = LogFactory.getLog(OrderAct.class);
	@Qualifier("productService")
	@Autowired
	private ProductService productService;
	@Qualifier("orderService")
	@Autowired
	private OrderService orderService;
	@Qualifier("userService")
	@Autowired
	private UserService userService;
	@Qualifier("purchaseService")
	@Autowired
	private PurchaseService purchaseService;
	@Qualifier("inventoryService")
	@Autowired
	private InventoryService inventoryService;
	@Qualifier("accountService")
	@Autowired
	private AccountService accountService;
	@Qualifier("systemService")
	@Autowired
	private SystemService systemService;
	@Qualifier("authService")
	@Autowired
	private AuthService authService;
	@Qualifier("emailService")
	@Autowired
	private EmailService emailService;

	@Qualifier("msOfficeService")
	@Autowired
	private MSOfficeService msOfficeService;
	
	

	@RequestMapping(value="/list.do",method = RequestMethod.GET)
	public String orderList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			if(request.getParameter("filter")!=null && request.getParameter("filter").equals("all")){
				return "order/orders_list_all";
			}else{
				return "order/orders_list";
			}
			
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/listdata.do",method = RequestMethod.POST)
	@ResponseBody
	public String orderListData(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Object> data = new ArrayList<Object>();
			Map<String, Object> response = new HashMap<String, Object>();
			int length = Integer.parseInt(request.getParameter("length"));
			int start = Integer.parseInt(request.getParameter("start"));
			int sEcho = Integer.parseInt(request.getParameter("draw"));
			int sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			String sortDir = request.getParameter("order[0][dir]");
			String[] ids = request.getParameterValues("id[]");
			String customActionType = request.getParameter("customActionType");
			String customActionName = request.getParameter("customActionName");
			String action = request.getParameter("action");
			String filter_type = request.getParameter("filter");
			OrderFlat orderSearch = new OrderFlat();
			
			if(customActionType!=null && customActionType.equals("group_action")){
				if(customActionName.equals("submit")){
					OrderFlat order = new OrderFlat();
					for(int i=0;i<ids.length;i++){
						order.setOrder_id(ids[i]);
						order.setUpdate_by_id(session.getAttribute("userid").toString());
						order.setStatus_code("submit");
						order.setStatus_value("已提交");
						orderService.updateOrderStatusById(order);
				    }
					response.put("customActionStatus", "OK");
					response.put("customActionMessage", "订单已成功提交！");
				}else if(customActionName.equals("cancel")){
					OrderFlat order = new OrderFlat();
					for(int i=0;i<ids.length;i++){
						order.setOrder_id(ids[i]);
						order.setUpdate_by_id(session.getAttribute("userid").toString());
						order.setStatus_code("cancel");
						order.setStatus_value("已取消");
						orderService.updateOrderStatusById(order);
				    }
					response.put("customActionStatus", "OK");
					response.put("customActionMessage", "订单已成功取消！");
				}else if(customActionName.equals("hold")){
					OrderFlat order = new OrderFlat();
					for(int i=0;i<ids.length;i++){
						order.setOrder_id(ids[i]);
						order.setUpdate_by_id(session.getAttribute("userid").toString());
						order.setStatus_code("hold");
						order.setStatus_value("已搁置");
						orderService.updateOrderStatusById(order);
				    }
					response.put("customActionStatus", "OK");
					response.put("customActionMessage", "订单已成功搁置！");
				}else if(customActionName.equals("closed")){
					OrderFlat order = new OrderFlat();
					for(int i=0;i<ids.length;i++){
						order.setOrder_id(ids[i]);
						order.setUpdate_by_id(session.getAttribute("userid").toString());
						order.setStatus_code("closed");
						order.setStatus_value("已关闭");
						orderService.updateOrderStatusById(order);
				    }
					response.put("customActionStatus", "OK");
					response.put("customActionMessage", "订单已成功关闭！");
				}
			}
			
			int iTotalRecords = 0;
			if(filter_type==null || (!filter_type.equals("all")) ){
				iTotalRecords = orderService.getOrdersCount(session.getAttribute("userid").toString());
			}else{
				iTotalRecords = orderService.getOrdersCount();
			}
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<OrderFlat> orderList = new ArrayList<OrderFlat>();
			
			if(action!=null && action.equals("filter")){
				orderSearch.setOrder_number(request.getParameter("order_number"));
				orderSearch.setStatus_code(request.getParameter("order_status"));
				orderSearch.setOrder_date_from(request.getParameter("order_date_from"));
				orderSearch.setOrder_date_to(request.getParameter("order_date_to"));
				orderSearch.setOrder_base_price_from(request.getParameter("order_base_price_from"));
				orderSearch.setOrder_base_price_to(request.getParameter("order_base_price_to"));
				orderSearch.setOrder_purchase_price_from(request.getParameter("order_purchase_price_from"));
				orderSearch.setOrder_purchase_price_to(request.getParameter("order_purchase_price_to"));
				orderSearch.setShip_addr_name(request.getParameter("order_ship_to"));
				orderSearch.setCreated_by_name(request.getParameter("order_customer_name"));
				if(filter_type==null || (!filter_type.equals("all")) ){
					orderSearch.setOwner_id(session.getAttribute("userid").toString());
				}
				
				orderList = orderService.getOrders(orderSearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", orderService.getOrders(orderSearch, null, 
						null, null, null).size());
			}else{
				if(filter_type==null || (!filter_type.equals("all")) ){
					orderSearch.setOwner_id(session.getAttribute("userid").toString());
				}
				orderList = orderService.getOrders(orderSearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < orderList.size(); i++)
	        {  
				OrderFlat order = orderList.get(i);
				List<String> dataRow = new ArrayList<String>();
				dataRow.add("<input type='checkbox' name='id[]' value="+order.getOrder_id()+">");
				dataRow.add(order.getOrder_number());
				dataRow.add(order.getPurchase_date());
				dataRow.add(order.getCreated_by_name());
				dataRow.add(order.getShip_addr_name());
				dataRow.add(String.valueOf(order.getBase_amount()));
				dataRow.add(String.valueOf(order.getDue_amount()));
				if(order.getStatus_code().equals("closed")){
					dataRow.add("<span class=\"label label-sm label-success\">已关闭</span>");
				}else if(order.getStatus_code().equals("fraud")){
					dataRow.add("<span class=\"label label-sm label-danger\">欺诈订单</span>");
				}else{
					dataRow.add("<span class=\"label label-sm label-info\">"+order.getStatus_value()+"</span>");
				}
				if(filter_type!=null && filter_type.equals("all")){
					dataRow.add("<a href=\"details.do?id="+order.getOrder_id()+"\" class=\"btn btn-xs default btn-editable\"><i class=\"fa fa-pencil\"></i> 编辑</a>");
				}else{
					dataRow.add("<a href=\"details.do?id="+order.getOrder_id()+"\" class=\"btn btn-xs default btn-editable\"><i class=\"fa fa-pencil\"></i> 查看详情</a>");
				}
				
				data.add(dataRow);
	        }
			
			response.put("data", data);
			response.put("draw", sEcho+1);
			response.put("recordsTotal ", iTotalRecords);
			response.put("iTotalRecords", iTotalRecords);
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(response);		
			
			return responseStr;
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/details.do",method = RequestMethod.GET)
	public String orderDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("id");
			
			OrderFlat order = orderService.getOrderById(orderId);
			User user = userService.selectUserDetail(order.getCreated_by_id());
			if( !"".equals(order.getShip_addr_id()) && order.getShip_addr_id()!=null ){
				Address ship_address = userService.selectAddressDetail(order.getShip_addr_id());
				model.addAttribute("ship_address", ship_address);
			}
			if( !"".equals(order.getBill_addr_id()) && order.getBill_addr_id()!=null ){
				Address bill_address = userService.selectAddressDetail(order.getBill_addr_id());
				model.addAttribute("bill_address", bill_address);
			}
			List<OrderFlat> orderItems = orderService.getItemsByOrderId(orderId);
			
			model.addAttribute("order", order);
			model.addAttribute("user", user);
			model.addAttribute("orderItems", orderItems);
			return "order/orders_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/create.do",method = RequestMethod.GET)
	@ResponseBody
	public String createOrder(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			OrderFlat order = new OrderFlat();
			
			if(orderService.addOrder(order,user_id)!=null){
				return "{'id':'success'}";
			}		
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	//Generate order directly from portfolio
	@RequestMapping(value="/generate.do",method = RequestMethod.GET)
	@ResponseBody
	public String generateOrder(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			String prod_id = request.getParameter("prod_id");
			String prod_price = request.getParameter("prod_price");
			String prod_quantity = request.getParameter("prod_quantity");
			
			OrderFlat order = new OrderFlat();
			String order_id = orderService.addOrder(order,user_id);
			
			OrderFlat orderItem = new OrderFlat();
			orderItem.setOrder_id(order_id);
			orderItem.setItem_prod_id(prod_id);
			orderItem.setCreated_by_id(user_id);
			orderItem.setItem_base_price(Double.parseDouble(prod_price));
			//orderItem.setItem_purchase_price(Double.parseDouble(request.getParameter("purchase_price")));
			orderItem.setItem_base_amount(Double.parseDouble(prod_price) * Integer.parseInt(prod_quantity));
			//orderItem.setItem_discount_ratio(Float.parseFloat(request.getParameter("discount_percent")));
			//orderItem.setItem_discount_amount(Double.parseDouble(request.getParameter("discount_amount")));
			orderItem.setItem_quantity(Integer.parseInt(prod_quantity));
			//orderItem.setItem_tax_ratio(Float.parseFloat(request.getParameter("tax_percent")));
			//orderItem.setItem_tax_amount(Double.parseDouble(request.getParameter("tax_amount")));
			orderItem.setItem_due_amount(Double.parseDouble(prod_price) * Integer.parseInt(prod_quantity));
			orderService.addOrderItem(orderItem);
					
			return "{'order_id':'"+order_id+"'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateOrderById(HttpServletRequest request, HttpSession session) throws Exception{
		
		return null;
	}
	
	//file upload action
	@RequestMapping(value="/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpSession session) throws Exception{
		String errMsg = "";
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String orderId = request.getParameter("id").toString();

		String fileName = file.getOriginalFilename();
		String fileUniqueName = request.getParameter("name").toString();
		String defaultContextPath = "/files/orders";
		String defaultRealPath = request.getSession().getServletContext().getRealPath(defaultContextPath);

		File dirFile = new File(defaultRealPath);
		if(!dirFile.exists())
		{
			logger.info("The folder "+defaultRealPath+" does not exist,now trying to create a one...");
			if(dirFile.mkdir()){
				logger.info("Create folder "+defaultRealPath+" successfully!");
			}else{
				logger.info("Create folder "+defaultRealPath+" failed!");
				responseMap.put("result", "failed");
				responseMap.put("id", fileName);
				return responseMap;
			}
		}

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(defaultRealPath+"\\"+fileName)));
				stream.write(bytes);
				stream.close();
				
				Attachment orderFile = new Attachment();
				orderFile.setFilename(fileName);
				orderFile.setUnique_filename(fileUniqueName);
				orderFile.setOriginal_filename(fileName);
				orderFile.setPath(defaultRealPath);
				orderFile.setContextpath(defaultContextPath);
				orderFile.setFilesize(file.getSize());
				orderFile.setUploaded_by_user_id(session.getAttribute("userid").toString());
				orderFile.setUpdate_by_user_id(session.getAttribute("userid").toString());
				orderFile.setImage_label(file.getContentType());
				orderFile.setOrder_id(orderId);
				orderService.addAttachment(orderFile);
				
			} catch (Exception e) {
				logger.info(e);
				errMsg = "You failed to upload " + fileName + " => " + e.getMessage();
				logger.info(errMsg);
				responseMap.put("result", "failed");
				responseMap.put("id", fileName);
				return responseMap;
			}
		} else {
			errMsg = "You failed to upload " + fileName + " because the file was empty.";
			logger.info(errMsg); 
			responseMap.put("result", "failed");
			responseMap.put("id", fileName);
			return responseMap;
		}

		responseMap.put("result", "OK");
		responseMap.put("id", fileName);
		return responseMap;
	}
	
	@RequestMapping(value="/loadattach.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String loadAttachments(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("orderId").toString();
			List<Attachment> attachmentList = orderService.getAttachmentsByOrderId(orderId);
			
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(attachmentList);	
			
			return responseStr;
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/delAttach.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delAttachById(HttpServletRequest request, HttpSession session) throws Exception{

		try {
			String id = request.getParameter("image_id");
			orderService.delAttachmentById(id);
			
			return "{'objname':'订单附件'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadAvailProd.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Product> loadAvailProducts(HttpServletRequest request, HttpSession session) throws Exception{

		try{
				
			return purchaseService.getAvailProducts();
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadAvailAddr.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Address> loadAvailAddresses(HttpServletRequest request, HttpSession session) throws Exception{

		try{
				
			return userService.selectAddressLists();
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadItems.do",method = RequestMethod.GET)
	@ResponseBody
	public List<OrderFlat> loadOrderItems(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("orderId");
				
			return orderService.getItemsByOrderId(orderId);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/addItem.do",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String addOrderItem(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat order = new OrderFlat();
			order.setOrder_id(request.getParameter("order[id]"));
			order.setItem_prod_id(request.getParameter("product[id]"));
			order.setItem_prod_name(request.getParameter("product[name]"));
			order.setItem_comment(request.getParameter("item_comment"));
			//order.setItem_base_price(Double.parseDouble(request.getParameter("product[price]")));
			order.setItem_due_price(Double.parseDouble(request.getParameter("purchase_price")));
			order.setItem_base_amount(Double.parseDouble(request.getParameter("base_amount")));
			//order.setItem_discount_ratio(Float.parseFloat(request.getParameter("discount_percent")));
			//order.setItem_discount_amount(Double.parseDouble(request.getParameter("discount_amount")));
			order.setItem_quantity(Integer.parseInt(request.getParameter("purchase_quantity")));
			order.setItem_tax_ratio(Float.parseFloat(request.getParameter("tax_percent")));
			order.setItem_tax_amount(Double.parseDouble(request.getParameter("tax_amount")));
			order.setItem_due_amount(Double.parseDouble(request.getParameter("purchase_amount")));
			order.setItem_warehouse_id(request.getParameter("warehouse[id]"));
			order.setItem_warehouse_name(request.getParameter("warehouse[name]"));
			order.setItem_created_by_id(session.getAttribute("userid").toString());
			order.setItem_created_by_name(session.getAttribute("username").toString());
			order.setItem_update_by_id(session.getAttribute("userid").toString());
			order.setItem_update_by_name(session.getAttribute("username").toString());
			orderService.addOrderItem(order);
			
			return "{'objname':'订单项'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/editItem.do",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String editOrderItem(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat order = new OrderFlat();
			order.setOrder_id(request.getParameter("order[id]"));
			order.setItem_id(request.getParameter("order_item[id]"));
			order.setItem_prod_id(request.getParameter("product[id]"));
			order.setItem_prod_name(request.getParameter("product[name]"));
			order.setItem_comment(request.getParameter("item_comment"));
			//order.setItem_base_price(Double.parseDouble(request.getParameter("product[price]")));
			order.setItem_due_price(Double.parseDouble(request.getParameter("purchase_price")));
			order.setItem_base_amount(Double.parseDouble(request.getParameter("base_amount")));
			//order.setItem_discount_ratio(Float.parseFloat(request.getParameter("discount_percent")));
			//order.setItem_discount_amount(Double.parseDouble(request.getParameter("discount_amount")));
			order.setItem_quantity(Integer.parseInt(request.getParameter("purchase_quantity")));
			order.setItem_tax_ratio(Float.parseFloat(request.getParameter("tax_percent")));
			order.setItem_tax_amount(Double.parseDouble(request.getParameter("tax_amount")));
			order.setItem_due_amount(Double.parseDouble(request.getParameter("purchase_amount")));
			order.setItem_warehouse_id(request.getParameter("warehouse[id]"));
			order.setItem_warehouse_name(request.getParameter("warehouse[name]"));
			order.setItem_update_by_id(session.getAttribute("userid").toString());
			order.setItem_update_by_name(session.getAttribute("username").toString());
			orderService.updateOrderItem(order);
			
			return "{'objname':'订单项'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/delItem.do",method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delOrderItem(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String item_id = request.getParameter("item_id");
			String order_id = request.getParameter("orderId");
			String userid = session.getAttribute("userid").toString();
			String username = session.getAttribute("username").toString();
			
			//test
			
			String rawProductName = request.getParameter("productName");
			String productName = new String(rawProductName.getBytes("ISO8859-1"),"UTF-8");
			
			Object[] params = new Object[]{userid,username,order_id, productName};
			
			orderService.delOrderItemById(item_id, params);
			
			return "{'objname':'购买项'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/history_listdata.do",method = RequestMethod.POST)
	@ResponseBody
	public String historyListData(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Object> data = new ArrayList<Object>();
			Map<String, Object> response = new HashMap<String, Object>();
			int length = Integer.parseInt(request.getParameter("length"));
			int start = Integer.parseInt(request.getParameter("start"));
			int sEcho = Integer.parseInt(request.getParameter("draw"));
			int sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			String sortDir = request.getParameter("order[0][dir]");
			String action = request.getParameter("action");
			String orderId = request.getParameter("orderId");
			OrderHistory orderHistorySearch = new OrderHistory();
			
			orderHistorySearch.setOrder_id(orderId);
			int iTotalRecords = orderService.getHistories(orderHistorySearch, null, null, null, null).size();
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<OrderHistory> orderHistoryList = new ArrayList<OrderHistory>();
			
			if(action!=null && action.equals("filter")){
				orderHistorySearch.setCreated_date_from(request.getParameter("order_history_date_from"));
				orderHistorySearch.setCreated_date_to(request.getParameter("order_history_date_to"));
				orderHistorySearch.setDescription(request.getParameter("order_history_desc"));
				orderHistorySearch.setCreated_by_user_name(request.getParameter("order_created_by_username"));
				
				orderHistoryList = orderService.getHistories(orderHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", orderService.getHistories(orderHistorySearch, null, 
						null, null, null).size());
			}else{
				orderHistoryList = orderService.getHistories(orderHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < orderHistoryList.size(); i++)
	        {  
				OrderHistory orderHistory = orderHistoryList.get(i); 
				List<String> dataRow = new ArrayList<String>();
				dataRow.add(orderHistory.getCreated_date());
				dataRow.add(orderHistory.getDescription());
				dataRow.add(orderHistory.getCreated_by_user_name());
				dataRow.add(" ");
				data.add(dataRow);
	        }
			
			response.put("data", data);
			response.put("draw", sEcho+1);
			response.put("recordsTotal ", iTotalRecords);
			//response.put("recordsFiltered", iTotalRecords);
			response.put("iTotalRecords", iTotalRecords);
			
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(response);		
			
			return responseStr;
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/approval_history_listdata.do",method = RequestMethod.POST)
	@ResponseBody
	public String approvalHistoryListData(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Object> data = new ArrayList<Object>();
			Map<String, Object> response = new HashMap<String, Object>();
			int length = Integer.parseInt(request.getParameter("length"));
			int start = Integer.parseInt(request.getParameter("start"));
			int sEcho = Integer.parseInt(request.getParameter("draw"));
			int sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			String sortDir = request.getParameter("order[0][dir]");
			String action = request.getParameter("action");
			String orderId = request.getParameter("orderId");
			ApprovalHistory approvalHistorySearch = new ApprovalHistory();
			
			approvalHistorySearch.setOrder_id(orderId);
			int iTotalRecords = orderService.getApprovalHistories(approvalHistorySearch, null, null, null, null).size();
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<ApprovalHistory> approvalHistoryList = new ArrayList<ApprovalHistory>();
			
			if(action!=null && action.equals("filter")){
				approvalHistorySearch.setCreated_date_from(request.getParameter("order_history_date_from"));
				approvalHistorySearch.setCreated_date_to(request.getParameter("order_history_date_to"));
				approvalHistorySearch.setDescription(request.getParameter("order_history_desc"));
				approvalHistorySearch.setCreated_by_user_name(request.getParameter("order_created_by_username"));
				
				approvalHistoryList = orderService.getApprovalHistories(approvalHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", orderService.getApprovalHistories(approvalHistorySearch, null, 
						null, null, null).size());
			}else{
				approvalHistoryList = orderService.getApprovalHistories(approvalHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < approvalHistoryList.size(); i++)
	        {  
				ApprovalHistory approvalHistory = approvalHistoryList.get(i); 
				List<String> dataRow = new ArrayList<String>();
				dataRow.add(approvalHistory.getCreated_date());
				dataRow.add(approvalHistory.getDescription());
				dataRow.add(approvalHistory.getCreated_by_user_name());
				dataRow.add(" ");
				data.add(dataRow);
	        }
			
			response.put("data", data);
			response.put("draw", sEcho+1);
			response.put("recordsTotal ", iTotalRecords);
			//response.put("recordsFiltered", iTotalRecords);
			response.put("iTotalRecords", iTotalRecords);
			
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(response);		
			
			return responseStr;
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@RequestMapping(value="/editOrderAmounts.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateOrderAmounts(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat order = new OrderFlat();
			order.setOrder_id(request.getParameter("orderId"));
			order.setBase_amount(Double.parseDouble(request.getParameter("base_total")));
			order.setDue_amount(Double.parseDouble(request.getParameter("due_total")));
			order.setUpdate_by_id(session.getAttribute("userid").toString());
			order.setUpdate_by_name(session.getAttribute("username").toString());
			orderService.updateOrderAmountsById(order);
			
			return "{'objname':'购买项'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updateOrderAddr.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateOrderAddress(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat order = new OrderFlat();
			order.setOrder_id(request.getParameter("orderId"));
			order.setUpdate_by_id(session.getAttribute("userid").toString());
			order.setUpdate_by_name(session.getAttribute("username").toString());
			
			String address_type = request.getParameter("address_type");
			if(address_type.equals("bill-address")){
				order.setBill_addr_id(request.getParameter("address_id"));
				orderService.updateOrderAddrById(order, "bill-address");
			}else if(address_type.equals("ship-address")){
				order.setShip_addr_id(request.getParameter("address_id"));
				orderService.updateOrderAddrById(order, "ship-address");
			}else{
				order.setArea_id(request.getParameter("address_id"));
				orderService.updateOrderAddrById(order, "area");
			}
			
			return "{'objname':'地址/区域'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updateOrderStatus.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateOrderStatus(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat order = new OrderFlat();
			order.setOrder_id(request.getParameter("orderId"));
			order.setUpdate_by_id(session.getAttribute("userid").toString());
			order.setUpdate_by_name(session.getAttribute("username").toString());
			order.setStatus_code(request.getParameter("status_code"));
			order.setOrder_type_cd(request.getParameter("type_code"));
			
			return orderService.updateOrderStatusByIdFacade(order);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			return "{'errmsg':'"+e.getMessage()+"'}";
		}
	}
	
	@RequestMapping(value="/po_list.do",method = RequestMethod.GET)
	public String poList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Purchase Order");
			orderSearch.setOwner_id(session.getAttribute("userid").toString());
			List<OrderFlat> po_list = orderService.getOrders(orderSearch, null, null, null, null);
			model.addAttribute("po_list",po_list);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Currency");
			List<ListOfValue> currency_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("currency_list", currency_list);
			
			return "po-admin/po_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/po_all_list.do",method = RequestMethod.GET)
	public String poAllList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Purchase Order");
			List<OrderFlat> po_list = orderService.getOrders(orderSearch, null, null, null, null);
			model.addAttribute("po_list",po_list);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Currency");
			List<ListOfValue> currency_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("currency_list", currency_list);
			
			return "po-admin/po_all_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/po_approval_list.do",method = RequestMethod.GET)
	public String poApprovalList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Purchase Order");
			List<OrderFlat> po_list = orderService.getOrdersForApprover(orderSearch,
					session.getAttribute("userid").toString(), null, null, null, null);
			model.addAttribute("po_list",po_list);
			
			return "po-admin/po_approval_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/po_return_list.do",method = RequestMethod.GET)
	public String poReturnList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Purchase Return Order");
			orderSearch.setOwner_id(session.getAttribute("userid").toString());
			List<OrderFlat> po_return_list = orderService.getOrders(orderSearch, null, null, null, null);
			model.addAttribute("po_return_list",po_return_list);
			
			return "po-admin/po_return_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/po_return_all_list.do",method = RequestMethod.GET)
	public String poReturnAllList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Purchase Return Order");
			List<OrderFlat> po_return_list = orderService.getOrders(orderSearch, null, null, null, null);
			model.addAttribute("po_return_list",po_return_list);
			
			return "po-admin/po_return_all_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/so_list.do",method = RequestMethod.GET)
	public String soList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Sales Order");
			orderSearch.setOwner_id(session.getAttribute("userid").toString());
			List<OrderFlat> so_list = orderService.getOrders(orderSearch, null, null, null, null);
			model.addAttribute("so_list",so_list);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Currency");
			List<ListOfValue> currency_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("currency_list", currency_list);
			
			return "so-admin/so_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/so_all_list.do",method = RequestMethod.GET)
	public String soAllList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Sales Order");
			List<OrderFlat> so_list = orderService.getOrders(orderSearch, null, null, null, null);
			model.addAttribute("so_list",so_list);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Currency");
			List<ListOfValue> currency_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("currency_list", currency_list);
			
			return "so-admin/so_all_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/so_approval_list.do",method = RequestMethod.GET)
	public String soApprovalList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Sales Order");
			List<OrderFlat> so_list = orderService.getOrdersForApprover(orderSearch,
					session.getAttribute("userid").toString(), null, null, null, null);
			model.addAttribute("so_list",so_list);
			
			return "so-admin/so_approval_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/so_return_list.do",method = RequestMethod.GET)
	public String soReturnList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Sales Return Order");
			orderSearch.setOwner_id(session.getAttribute("userid").toString());
			List<OrderFlat> so_return_list = orderService.getOrders(orderSearch, null, null, null, null);
			model.addAttribute("so_return_list",so_return_list);
			
			return "so-admin/so_return_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/so_return_all_list.do",method = RequestMethod.GET)
	public String soReturnAllList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Sales Return Order");
			List<OrderFlat> so_return_list = orderService.getOrders(orderSearch, null, null, null, null);
			model.addAttribute("so_return_list",so_return_list);
			
			return "so-admin/so_return_all_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/po_create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createPurchaseOrder(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			OrderFlat order = new OrderFlat();
			order.setCreated_by_id(user_id);
			order.setCreated_by_name(session.getAttribute("username").toString());
			order.setUpdate_by_id(user_id);
			order.setOwner_id(user_id);
			order.setOrder_manual_number(request.getParameter("man_number"));
			order.setOrder_comment(request.getParameter("desc"));
			order.setCurrency_cd(request.getParameter("currency_code"));
			String supplier_id = request.getParameter("supplier_id");
			if(!"".equals(supplier_id)&&supplier_id!=null){
				order.setSupplier_id(supplier_id);
			}
			//order.setReceive_wh_id(request.getParameter("warehouse_id"));
			
			if(orderService.addPurchaseOrder(order)!=null){
				return "{'id':'success'}";
			}
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/por_create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createPurchaseReturnOrder(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			OrderFlat order = new OrderFlat();
			order.setCreated_by_id(user_id);
			order.setCreated_by_name(session.getAttribute("username").toString());
			order.setUpdate_by_id(user_id);
			order.setOwner_id(user_id);
			order.setOrder_manual_number(request.getParameter("man_number"));
			order.setOrder_comment(request.getParameter("desc"));
			String supplier_id = request.getParameter("supplier_id");
			if(!"".equals(supplier_id)&&supplier_id!=null){
				order.setSupplier_id(supplier_id);
			}
			order.setDelivery_wh_id(request.getParameter("warehouse_id"));
			
			if(orderService.addPurchaseReturnOrder(order)!=null){
				return "{'id':'success'}";
			}
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/so_create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createSalesOrder(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			OrderFlat order = new OrderFlat();
			order.setCreated_by_id(user_id);
			order.setCreated_by_name(session.getAttribute("username").toString());
			order.setUpdate_by_id(user_id);
			order.setOwner_id(user_id);
			order.setOrder_manual_number(request.getParameter("man_number"));
			order.setOrder_comment(request.getParameter("desc"));
			order.setCurrency_cd(request.getParameter("currency_code"));
			String customer_id = request.getParameter("customer_id");
			if(!"".equals(customer_id)&&customer_id!=null){
				order.setAccount_id(customer_id);
			}
			//order.setDelivery_wh_id(request.getParameter("warehouse_id"));
			
			if(orderService.addSalesOrder(order)!=null){
				return "{'id':'success'}";
			}
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/sor_create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createSalesReturnOrder(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			OrderFlat order = new OrderFlat();
			order.setCreated_by_id(user_id);
			order.setCreated_by_name(session.getAttribute("username").toString());
			order.setUpdate_by_id(user_id);
			order.setOwner_id(user_id);
			order.setOrder_manual_number(request.getParameter("man_number"));
			order.setOrder_comment(request.getParameter("desc"));
			String customer_id = request.getParameter("customer_id");
			if(!"".equals(customer_id)&&customer_id!=null){
				order.setAccount_id(customer_id);
			}
			order.setReceive_wh_id(request.getParameter("warehouse_id"));
			
			if(orderService.addSalesReturnOrder(order)!=null){
				return "{'id':'success'}";
			}
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadAvailWarehouse.do",method = RequestMethod.GET)
	@ResponseBody
	public List<WarehouseFlat> loadAvailWarehouse(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			WarehouseFlat warehouse = new WarehouseFlat();
			warehouse.setCheckActive(true);
			return inventoryService.getWarehouses(warehouse, null, null, null, null);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadAvailSupplier.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Account> loadAvailSupplier(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			Account supplier = new Account();
			supplier.setAccountType("供应商");
			supplier.setAccountStatus("Published");
			supplier.setActive(true);
			
			return accountService.getAccount(supplier);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadAvailCustomer.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Account> loadAvailCustomer(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			Account customer = new Account();
			customer.setAccountType("客户");
			customer.setAccountStatus("Published");
			customer.setActive(true);
			
			return accountService.getAccount(customer);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadAvailOwner.do",method = RequestMethod.GET)
	@ResponseBody
	public List<User> loadAvailOwner(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			User owner = new User();
			owner.setActive(true);
			owner.setDelete(false);
			
			return userService.getUserLists(owner);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/po_details.do",method = RequestMethod.GET)
	public String poDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("id");
			
			OrderFlat order = orderService.getOrderById(orderId);
			User owner = userService.selectUserDetail(order.getOwner_id());
			
			if( !"".equals(order.getSupplier_id()) && order.getSupplier_id()!=null ){
				Object[] params = new Object[]{order.getSupplier_id()};
				Account supplier = accountService.selectAtDe(params);
				model.addAttribute("supplier", supplier);
			}
			if( !"".equals(order.getSupplier_lv2_id()) && order.getSupplier_lv2_id()!=null ){
				Object[] params = new Object[]{order.getSupplier_lv2_id()};
				Account supplier_lv2 = accountService.selectAtDe(params);
				model.addAttribute("supplier_lv2", supplier_lv2);
			}
			if( !"".equals(order.getBill_addr_id()) && order.getBill_addr_id()!=null ){
				Address bill_address = userService.selectAddressDetail(order.getBill_addr_id());
				model.addAttribute("bill_address", bill_address);
			}
			if( !"".equals(order.getShip_addr_id()) && order.getShip_addr_id()!=null ){
				Address ship_address = userService.selectAddressDetail(order.getShip_addr_id());
				model.addAttribute("ship_address", ship_address);
			}
			if( !"".equals(order.getReceive_wh_id()) && order.getReceive_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(order.getReceive_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			List<OrderFlat> orderItems = orderService.getItemsByOrderId(orderId);
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setOrder_id(orderId);
			List<RequisitionFlat> related_req_list = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Payment Status");
			List<ListOfValue> payment_status_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("payment_status_list", payment_status_list);
			
			boolean readOnlyFlag = false;
			switch(order.getStatus_code()){
			case "submit - approved":
				readOnlyFlag = true;
				break;
			case "submit - rejected":
				readOnlyFlag = true;
				break;
			case "submit - pending":
				readOnlyFlag = true;
				break;
			case "submitted":
				readOnlyFlag = true;
				break;
			case "processing":
				readOnlyFlag = true;
				break;
			case "cancelled":
				readOnlyFlag = true;
				break;
			case "completed":
				readOnlyFlag = true;
				break;
			case "scanned":
				readOnlyFlag = true;
				break;
			case "archived":
				readOnlyFlag = true;
				break;
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("related_req_list",related_req_list);
			model.addAttribute("default_tax_ratio", systemService.getPrefByCode("default_tax_ratio").getSystem_preference_value());
			model.addAttribute("owner", owner);
			model.addAttribute("order", order);
			model.addAttribute("orderItems", orderItems);
			return "po-admin/po_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/po_all_details.do",method = RequestMethod.GET)
	public String poAllDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("id");
			
			OrderFlat order = orderService.getOrderById(orderId);
			User owner = userService.selectUserDetail(order.getOwner_id());
			
			if( !"".equals(order.getSupplier_id()) && order.getSupplier_id()!=null ){
				Object[] params = new Object[]{order.getSupplier_id()};
				Account supplier = accountService.selectAtDe(params);
				model.addAttribute("supplier", supplier);
			}
			if( !"".equals(order.getSupplier_lv2_id()) && order.getSupplier_lv2_id()!=null ){
				Object[] params = new Object[]{order.getSupplier_lv2_id()};
				Account supplier_lv2 = accountService.selectAtDe(params);
				model.addAttribute("supplier_lv2", supplier_lv2);
			}
			if( !"".equals(order.getBill_addr_id()) && order.getBill_addr_id()!=null ){
				Address bill_address = userService.selectAddressDetail(order.getBill_addr_id());
				model.addAttribute("bill_address", bill_address);
			}
			if( !"".equals(order.getShip_addr_id()) && order.getShip_addr_id()!=null ){
				Address ship_address = userService.selectAddressDetail(order.getShip_addr_id());
				model.addAttribute("ship_address", ship_address);
			}
			if( !"".equals(order.getReceive_wh_id()) && order.getReceive_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(order.getReceive_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			List<OrderFlat> orderItems = orderService.getItemsByOrderId(orderId);
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setOrder_id(orderId);
			List<RequisitionFlat> related_req_list = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Payment Status");
			List<ListOfValue> payment_status_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("payment_status_list", payment_status_list);
			
			boolean readOnlyFlag = false;
			switch(order.getStatus_code()){
			case "submit - approved":
				readOnlyFlag = true;
				break;
			case "submit - rejected":
				readOnlyFlag = true;
				break;
			case "submit - pending":
				readOnlyFlag = true;
				break;
			case "submitted":
				readOnlyFlag = true;
				break;
			case "processing":
				readOnlyFlag = true;
				break;
			case "cancelled":
				readOnlyFlag = true;
				break;
			case "completed":
				readOnlyFlag = true;
				break;
			case "scanned":
				readOnlyFlag = true;
				break;
			case "archived":
				readOnlyFlag = true;
				break;
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("related_req_list",related_req_list);
			model.addAttribute("default_tax_ratio", systemService.getPrefByCode("default_tax_ratio").getSystem_preference_value());
			model.addAttribute("owner", owner);
			model.addAttribute("order", order);
			model.addAttribute("orderItems", orderItems);
			return "po-admin/po_all_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/po_approval_details.do",method = RequestMethod.GET)
	public String poApprovalDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("id");
			
			OrderFlat order = orderService.getOrderById(orderId);
			User owner = userService.selectUserDetail(order.getOwner_id());
			
			if( !"".equals(order.getSupplier_id()) && order.getSupplier_id()!=null ){
				Object[] params = new Object[]{order.getSupplier_id()};
				Account supplier = accountService.selectAtDe(params);
				model.addAttribute("supplier", supplier);
			}
			if( !"".equals(order.getSupplier_lv2_id()) && order.getSupplier_lv2_id()!=null ){
				Object[] params = new Object[]{order.getSupplier_lv2_id()};
				Account supplier_lv2 = accountService.selectAtDe(params);
				model.addAttribute("supplier_lv2", supplier_lv2);
			}
			if( !"".equals(order.getBill_addr_id()) && order.getBill_addr_id()!=null ){
				Address bill_address = userService.selectAddressDetail(order.getBill_addr_id());
				model.addAttribute("bill_address", bill_address);
			}
			if( !"".equals(order.getShip_addr_id()) && order.getShip_addr_id()!=null ){
				Address ship_address = userService.selectAddressDetail(order.getShip_addr_id());
				model.addAttribute("ship_address", ship_address);
			}
			if( !"".equals(order.getArea_id()) && order.getArea_id()!=null ){
				Address sales_area = userService.selectAddressDetail(order.getArea_id());
				model.addAttribute("sales_area", sales_area);
			}
			if( !"".equals(order.getReceive_wh_id()) && order.getReceive_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(order.getReceive_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			List<OrderFlat> orderItems = orderService.getItemsByOrderId(orderId);
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setOrder_id(orderId);
			List<RequisitionFlat> related_req_list = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Payment Status");
			List<ListOfValue> payment_status_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("payment_status_list", payment_status_list);
			
			boolean readOnlyFlag = false;
			switch(order.getStatus_code()){
			case "submit - approved":
				readOnlyFlag = true;
				break;
			case "submit - rejected":
				readOnlyFlag = true;
				break;
			case "submit - pending":
				ApprovalDetail userApprovalDetail = authService.queryApprovalDetailStatus(orderId, "Purchase Order", session.getAttribute("userid").toString());
				if(userApprovalDetail.getStatus_cd().equals("Pending")){
					readOnlyFlag = false;
				}else{
					readOnlyFlag = true;
				}
				break;
			case "submitted":
				readOnlyFlag = true;
				break;
			case "processing":
				readOnlyFlag = true;
				break;
			case "cancelled":
				readOnlyFlag = true;
				break;
			case "completed":
				readOnlyFlag = true;
				break;
			case "scanned":
				readOnlyFlag = true;
				break;
			case "archived":
				readOnlyFlag = true;
				break;
			}
			
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("related_req_list",related_req_list);
			model.addAttribute("default_tax_ratio", systemService.getPrefByCode("default_tax_ratio").getSystem_preference_value());
			model.addAttribute("owner", owner);
			model.addAttribute("order", order);
			model.addAttribute("orderItems", orderItems);
			return "po-admin/po_approval_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updatePOInfo.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updatePOInfo(HttpServletRequest request, HttpSession session) throws Exception{
		
		String supplier_id = request.getParameter("supplier_id");
		String supplier_lv2_id = request.getParameter("supplier_lv2_id");
		String receive_wh_id = request.getParameter("receive_wh_id");
		String owner_id = request.getParameter("owner_id");
		String comment = request.getParameter("comment");
		String orderId = request.getParameter("orderId");
		String man_number = request.getParameter("man_number");
		String payment_status = request.getParameter("payment_status");
		String payment_ratio = request.getParameter("payment_ratio");
		String update_by = session.getAttribute("userid").toString();
		String update_by_name = session.getAttribute("username").toString();
		
		try{			
			OrderFlat order = orderService.getOrderById(orderId);
			StringBuffer history_desc = new StringBuffer();
			
			if( !"".equals(supplier_id) && supplier_id!=null ){
				if(!(order.getSupplier_id().equals(supplier_id))){
					order.setSupplier_id(supplier_id);
					history_desc.append("由"+update_by_name+"更新了直接供应商。");
				}
			}
			if( !"".equals(supplier_lv2_id) && supplier_lv2_id!=null ){
				if(order.getSupplier_lv2_id()==null){
					order.setSupplier_lv2_id(supplier_lv2_id);
					history_desc.append("由"+update_by_name+"更新了间接供应商。");
				}else if(!(order.getSupplier_lv2_id().equals(supplier_lv2_id))){
					order.setSupplier_lv2_id(supplier_lv2_id);
					history_desc.append("由"+update_by_name+"更新了间接供应商。");
				}
			}
			if( !"".equals(receive_wh_id) && receive_wh_id!=null ){
				if(!(order.getReceive_wh_id().equals(receive_wh_id))){
					order.setReceive_wh_id(receive_wh_id);
					history_desc.append("由"+update_by_name+"更新了收货仓库。");
				}
			}
			if( !"".equals(owner_id) && owner_id!=null ){
				if(!(order.getOwner_id().equals(owner_id))){
					order.setOwner_id(owner_id);
					history_desc.append("由"+update_by_name+"更新了经手人。");
				}
			}
			if( !"".equals(comment) && comment!=null ){
				if(!(order.getOrder_comment().equals(comment))){
					order.setOrder_comment(comment);
					history_desc.append("由"+update_by_name+"更新了订单备注。");
				}
			}
			if( !"".equals(man_number) && man_number!=null ){
				if(order.getOrder_manual_number()==null){
					history_desc.append("更新了人工单据号！");
				}else if(!(order.getOrder_manual_number().equals(man_number))){
					history_desc.append("更新了人工单据号！");
				}
				order.setOrder_manual_number(man_number);
			}
			if( !"".equals(payment_status) && payment_status!=null ){
				if(order.getPayment_status_code()==null){
					history_desc.append("更新了付款状态！");
				}else if(!(order.getPayment_status_code().equals(payment_status))){
					history_desc.append("更新了付款状态！");
				}
				order.setPayment_status_code(payment_status);
				order.setPayment_status_val(systemService.getValueByCodeName("Payment Status", payment_status));
			}
			if( !"".equals(payment_ratio) && payment_ratio!=null ){
				if(order.getPayment_ratio() != Double.valueOf(payment_ratio)){
					history_desc.append("更新了付款百分比！");
				}
				order.setPayment_ratio(Double.valueOf(payment_ratio));
			}
			
			order.setHistory_desc(history_desc.toString());
			order.setUpdate_by_id(update_by);
			order.setUpdate_by_name(session.getAttribute("username").toString());
			orderService.updateOrderById(order);
			
			return "{'objname':'采购单'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/por_details.do",method = RequestMethod.GET)
	public String porDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("id");
			
			OrderFlat order = orderService.getOrderById(orderId);
			User owner = userService.selectUserDetail(order.getOwner_id());
			
			if( !"".equals(order.getSupplier_id()) && order.getSupplier_id()!=null ){
				Object[] params = new Object[]{order.getSupplier_id()};
				Account supplier = accountService.selectAtDe(params);
				model.addAttribute("supplier", supplier);
			}
			if( !"".equals(order.getBill_addr_id()) && order.getBill_addr_id()!=null ){
				Address bill_address = userService.selectAddressDetail(order.getBill_addr_id());
				model.addAttribute("bill_address", bill_address);
			}
			if( !"".equals(order.getShip_addr_id()) && order.getShip_addr_id()!=null ){
				Address ship_address = userService.selectAddressDetail(order.getShip_addr_id());
				model.addAttribute("ship_address", ship_address);
			}
			if( !"".equals(order.getDelivery_wh_id()) && order.getDelivery_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(order.getDelivery_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			List<OrderFlat> orderItems = orderService.getItemsByOrderId(orderId);
			
			model.addAttribute("owner", owner);
			model.addAttribute("order", order);
			model.addAttribute("orderItems", orderItems);
			return "po-admin/po_return_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updatePORInfo.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updatePORInfo(HttpServletRequest request, HttpSession session) throws Exception{
		
		String supplier_id = request.getParameter("supplier_id");
		String delivery_wh_id = request.getParameter("delivery_wh_id");
		String owner_id = request.getParameter("owner_id");
		String comment = request.getParameter("comment");
		String orderId = request.getParameter("orderId");
		String update_by = session.getAttribute("userid").toString();
		
		try{			
			OrderFlat order = orderService.getOrderById(orderId);
			if( !"".equals(supplier_id) && supplier_id!=null ){
				order.setSupplier_id(supplier_id);
			}
			if( !"".equals(delivery_wh_id) && delivery_wh_id!=null ){
				order.setDelivery_wh_id(delivery_wh_id);
			}
			if( !"".equals(owner_id) && owner_id!=null ){
				order.setOwner_id(owner_id);
			}
			if( !"".equals(comment) && comment!=null ){
				order.setOrder_comment(comment);
			}
			order.setUpdate_by_id(update_by);
			order.setUpdate_by_name(session.getAttribute("username").toString());
			orderService.updateOrderById(order);
			
			return "{'objname':'采购退货单'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/so_details.do",method = RequestMethod.GET)
	public String soDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("id");
			
			OrderFlat order = orderService.getOrderById(orderId);
			User owner = userService.selectUserDetail(order.getOwner_id());
			
			if( !"".equals(order.getAccount_id()) && order.getAccount_id()!=null ){
				Object[] params = new Object[]{order.getAccount_id()};
				Account customer = accountService.selectAtDe(params);
				model.addAttribute("customer", customer);
			}
			if( !"".equals(order.getAccount_lv2_id()) && order.getAccount_lv2_id()!=null ){
				Object[] params = new Object[]{order.getAccount_lv2_id()};
				Account customer_lv2 = accountService.selectAtDe(params);
				model.addAttribute("customer_lv2", customer_lv2);
			}
			if( !"".equals(order.getBill_addr_id()) && order.getBill_addr_id()!=null ){
				Address bill_address = userService.selectAddressDetail(order.getBill_addr_id());
				model.addAttribute("bill_address", bill_address);
			}
			if( !"".equals(order.getShip_addr_id()) && order.getShip_addr_id()!=null ){
				Address ship_address = userService.selectAddressDetail(order.getShip_addr_id());
				model.addAttribute("ship_address", ship_address);
			}
			if( !"".equals(order.getArea_id()) && order.getArea_id()!=null ){
				Address sales_area = userService.selectAddressDetail(order.getArea_id());
				model.addAttribute("sales_area", sales_area);
			}
			if( !"".equals(order.getDelivery_wh_id()) && order.getDelivery_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(order.getDelivery_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			List<OrderFlat> orderItems = orderService.getItemsByOrderId(orderId);
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setOrder_id(orderId);
			List<RequisitionFlat> related_req_list = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Receipt Status");
			List<ListOfValue> receipt_status_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("receipt_status_list", receipt_status_list);
			
			boolean readOnlyFlag = false;
			switch(order.getStatus_code()){
			case "submit - approved":
				readOnlyFlag = true;
				break;
			case "submit - rejected":
				readOnlyFlag = true;
				break;
			case "submit - pending":
				readOnlyFlag = true;
				break;
			case "submitted":
				readOnlyFlag = true;
				break;
			case "processing":
				readOnlyFlag = true;
				break;
			case "cancelled":
				readOnlyFlag = true;
				break;
			case "completed":
				readOnlyFlag = true;
				break;
			case "scanned":
				readOnlyFlag = true;
				break;
			case "archived":
				readOnlyFlag = true;
				break;
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("related_req_list",related_req_list);
			model.addAttribute("default_tax_ratio", systemService.getPrefByCode("default_tax_ratio").getSystem_preference_value());
			model.addAttribute("owner", owner);
			model.addAttribute("order", order);
			model.addAttribute("orderItems", orderItems);
			return "so-admin/so_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/so_all_details.do",method = RequestMethod.GET)
	public String soAllDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("id");
			
			OrderFlat order = orderService.getOrderById(orderId);
			User owner = userService.selectUserDetail(order.getOwner_id());
			
			if( !"".equals(order.getAccount_id()) && order.getAccount_id()!=null ){
				Object[] params = new Object[]{order.getAccount_id()};
				Account customer = accountService.selectAtDe(params);
				model.addAttribute("customer", customer);
			}
			if( !"".equals(order.getAccount_lv2_id()) && order.getAccount_lv2_id()!=null ){
				Object[] params = new Object[]{order.getAccount_lv2_id()};
				Account customer_lv2 = accountService.selectAtDe(params);
				model.addAttribute("customer_lv2", customer_lv2);
			}
			if( !"".equals(order.getBill_addr_id()) && order.getBill_addr_id()!=null ){
				Address bill_address = userService.selectAddressDetail(order.getBill_addr_id());
				model.addAttribute("bill_address", bill_address);
			}
			if( !"".equals(order.getShip_addr_id()) && order.getShip_addr_id()!=null ){
				Address ship_address = userService.selectAddressDetail(order.getShip_addr_id());
				model.addAttribute("ship_address", ship_address);
			}
			if( !"".equals(order.getArea_id()) && order.getArea_id()!=null ){
				Address sales_area = userService.selectAddressDetail(order.getArea_id());
				model.addAttribute("sales_area", sales_area);
			}
			if( !"".equals(order.getDelivery_wh_id()) && order.getDelivery_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(order.getDelivery_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			List<OrderFlat> orderItems = orderService.getItemsByOrderId(orderId);
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setOrder_id(orderId);
			List<RequisitionFlat> related_req_list = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Receipt Status");
			List<ListOfValue> receipt_status_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("receipt_status_list", receipt_status_list);
			
			boolean readOnlyFlag = false;
			switch(order.getStatus_code()){
			case "submit - approved":
				readOnlyFlag = true;
				break;
			case "submit - rejected":
				readOnlyFlag = true;
				break;
			case "submit - pending":
				readOnlyFlag = true;
				break;
			case "submitted":
				readOnlyFlag = true;
				break;
			case "processing":
				readOnlyFlag = true;
				break;
			case "cancelled":
				readOnlyFlag = true;
				break;
			case "completed":
				readOnlyFlag = true;
				break;
			case "scanned":
				readOnlyFlag = true;
				break;
			case "archived":
				readOnlyFlag = true;
				break;
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("related_req_list",related_req_list);
			model.addAttribute("default_tax_ratio", systemService.getPrefByCode("default_tax_ratio").getSystem_preference_value());
			model.addAttribute("owner", owner);
			model.addAttribute("order", order);
			model.addAttribute("orderItems", orderItems);
			return "so-admin/so_all_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/so_approval_details.do",method = RequestMethod.GET)
	public String soApprovalDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("id");
			
			OrderFlat order = orderService.getOrderById(orderId);
			User owner = userService.selectUserDetail(order.getOwner_id());
			
			if( !"".equals(order.getAccount_id()) && order.getAccount_id()!=null ){
				Object[] params = new Object[]{order.getAccount_id()};
				Account customer = accountService.selectAtDe(params);
				model.addAttribute("customer", customer);
			}
			if( !"".equals(order.getAccount_lv2_id()) && order.getAccount_lv2_id()!=null ){
				Object[] params = new Object[]{order.getAccount_lv2_id()};
				Account customer_lv2 = accountService.selectAtDe(params);
				model.addAttribute("customer_lv2", customer_lv2);
			}
			if( !"".equals(order.getBill_addr_id()) && order.getBill_addr_id()!=null ){
				Address bill_address = userService.selectAddressDetail(order.getBill_addr_id());
				model.addAttribute("bill_address", bill_address);
			}
			if( !"".equals(order.getShip_addr_id()) && order.getShip_addr_id()!=null ){
				Address ship_address = userService.selectAddressDetail(order.getShip_addr_id());
				model.addAttribute("ship_address", ship_address);
			}
			if( !"".equals(order.getArea_id()) && order.getArea_id()!=null ){
				Address sales_area = userService.selectAddressDetail(order.getArea_id());
				model.addAttribute("sales_area", sales_area);
			}
			if( !"".equals(order.getDelivery_wh_id()) && order.getDelivery_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(order.getDelivery_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			List<OrderFlat> orderItems = orderService.getItemsByOrderId(orderId);
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setOrder_id(orderId);
			List<RequisitionFlat> related_req_list = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);		
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Receipt Status");
			List<ListOfValue> receipt_status_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("receipt_status_list", receipt_status_list);
			
			boolean readOnlyFlag = false;
			switch(order.getStatus_code()){
			case "submit - approved":
				readOnlyFlag = true;
				break;
			case "submit - rejected":
				readOnlyFlag = true;
				break;
			case "submit - pending":
				ApprovalDetail userApprovalDetail = authService.queryApprovalDetailStatus(orderId, "Sales Order", session.getAttribute("userid").toString());
				if(userApprovalDetail.getStatus_cd().equals("Pending")){
					readOnlyFlag = false;
				}else{
					readOnlyFlag = true;
				}
				break;
			case "submitted":
				readOnlyFlag = true;
				break;
			case "processing":
				readOnlyFlag = true;
				break;
			case "cancelled":
				readOnlyFlag = true;
				break;
			case "completed":
				readOnlyFlag = true;
				break;
			case "scanned":
				readOnlyFlag = true;
				break;
			case "archived":
				readOnlyFlag = true;
				break;
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("related_req_list",related_req_list);
			model.addAttribute("default_tax_ratio", systemService.getPrefByCode("default_tax_ratio").getSystem_preference_value());
			model.addAttribute("owner", owner);
			model.addAttribute("order", order);
			model.addAttribute("orderItems", orderItems);
			return "so-admin/so_approval_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updateSOInfo.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateSOInfo(HttpServletRequest request, HttpSession session) throws Exception{
		
		String customer_id = request.getParameter("customer_id");
		String customer_lv2_id = request.getParameter("customer_lv2_id");
		String delivery_wh_id = request.getParameter("delivery_wh_id");
		String owner_id = request.getParameter("owner_id");
		String comment = request.getParameter("comment");
		String orderId = request.getParameter("orderId");
		String ship_addr_id = request.getParameter("ship_addr_id");
		String bill_addr_id = request.getParameter("bill_addr_id");
		String ship_addr_name = request.getParameter("ship_address");
		String bill_addr_name = request.getParameter("bill_address");
		String man_number = request.getParameter("man_number");
		String receipt_status = request.getParameter("receipt_status");
		String receipt_ratio = request.getParameter("receipt_ratio");
		String receipt_due_date = request.getParameter("receipt_due_date");
		String update_by = session.getAttribute("userid").toString();
		String update_by_name = session.getAttribute("username").toString();
		
		try{			
			OrderFlat order = orderService.getOrderById(orderId);
			StringBuffer history_desc = new StringBuffer();
			
			if( !"".equals(customer_id) && customer_id!=null ){
				if(!(order.getAccount_id().equals(customer_id))){
					order.setAccount_id(customer_id);
					history_desc.append("由"+update_by_name+"更新了直接客户。");
				}
			}
			if( !"".equals(customer_lv2_id) && customer_lv2_id!=null ){
				if(order.getAccount_lv2_id()==null){
					history_desc.append("由"+update_by_name+"更新了间接客户。");
				}else if(!(order.getAccount_lv2_id().equals(customer_lv2_id))){
					history_desc.append("由"+update_by_name+"更新了间接客户。");
				}
				order.setAccount_lv2_id(customer_lv2_id);
			}
			if( !"".equals(delivery_wh_id) && delivery_wh_id!=null ){
				if(!(order.getDelivery_wh_id().equals(delivery_wh_id))){
					order.setDelivery_wh_id(delivery_wh_id);
					history_desc.append("由"+update_by_name+"更新了发货仓库。");
				}
			}
			if( !"".equals(owner_id) && owner_id!=null ){
				if(!(order.getOwner_id().equals(owner_id))){
					order.setOwner_id(owner_id);
					history_desc.append("由"+update_by_name+"更新了经手人。");
				}
			}
			if( !"".equals(comment) && comment!=null ){
				if(!(order.getOrder_comment().equals(comment))){
					order.setOrder_comment(comment);
					history_desc.append("由"+update_by_name+"更新了订单备注。");
				}
			}
			if( !"".equals(ship_addr_id) && ship_addr_id!=null ){
				if(!(order.getShip_addr_id().equals(ship_addr_id))){
					order.setShip_addr_id(ship_addr_id);
					history_desc.append("由"+update_by_name+"更新了收货地址。");
				}
			}
			if( !"".equals(bill_addr_id) && bill_addr_id!=null ){
				if(!(order.getBill_addr_id().equals(bill_addr_id))){
					order.setBill_addr_id(bill_addr_id);
					history_desc.append("由"+update_by_name+"更新了账单地址。");
				}
			}
			if( !"".equals(man_number) && man_number!=null ){
				if(order.getOrder_manual_number()==null){
					history_desc.append("由"+update_by_name+"更新了人工单据号！");
				}else if(!(order.getOrder_manual_number().equals(man_number))){
					history_desc.append("由"+update_by_name+"更新了人工单据号！");
				}
				order.setOrder_manual_number(man_number);
			}
			if( !"".equals(ship_addr_name) && ship_addr_name!=null ){
				if(order.getShip_addr_name()==null){
					history_desc.append("由"+update_by_name+"更新了收货地址！");
				}else if(!(order.getShip_addr_name().equals(ship_addr_name))){
					history_desc.append("由"+update_by_name+"更新了收货地址！");
				}
				order.setShip_addr_name(ship_addr_name);
			}
			if( !"".equals(bill_addr_name) && bill_addr_name!=null ){
				if(order.getBill_addr_name()==null){
					history_desc.append("由"+update_by_name+"更新了账单地址！");
				}else if(!(order.getBill_addr_name().equals(bill_addr_name))){
					history_desc.append("由"+update_by_name+"更新了账单地址！");
				}
				order.setBill_addr_name(bill_addr_name);
			}
			if( !"".equals(receipt_status) && receipt_status!=null ){
				if(order.getReceipt_status_code()==null){
					history_desc.append("由"+update_by_name+"更新了收款状态。");
				}else if(!(order.getReceipt_status_code().equals(receipt_status))){
					history_desc.append("由"+update_by_name+"更新了收款状态。");
				}
				order.setReceipt_status_code(receipt_status);
				order.setReceipt_status_val(systemService.getValueByCodeName("Receipt Status", receipt_status));
			}
			if( !"".equals(receipt_ratio) && receipt_ratio!=null ){
				if(order.getReceipt_ratio() != Double.valueOf(receipt_ratio)){
					order.setReceipt_ratio(Double.valueOf(receipt_ratio));
					history_desc.append("由"+update_by_name+"更新了收款百分比。");
				}
			}
			if( !"".equals(receipt_due_date) && receipt_due_date!=null ){
				if(order.getReceipt_due_date()==null){
					history_desc.append("由"+update_by_name+"更新了逾期日期。");
				}else if(!(order.getReceipt_due_date().equals(receipt_due_date))){
					history_desc.append("由"+update_by_name+"更新了逾期日期。");
				}
				order.setReceipt_due_date(Date.valueOf(receipt_due_date));
			}
			order.setHistory_desc(history_desc.toString());
			order.setUpdate_by_id(update_by);
			order.setUpdate_by_name(update_by_name);
			orderService.updateOrderById(order);
			
			return "{'objname':'销售订单'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/sor_details.do",method = RequestMethod.GET)
	public String sorDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("id");
			
			OrderFlat order = orderService.getOrderById(orderId);
			User owner = userService.selectUserDetail(order.getOwner_id());
			
			if( !"".equals(order.getAccount_id()) && order.getAccount_id()!=null ){
				Object[] params = new Object[]{order.getAccount_id()};
				Account customer = accountService.selectAtDe(params);
				model.addAttribute("customer", customer);
			}
			if( !"".equals(order.getBill_addr_id()) && order.getBill_addr_id()!=null ){
				Address bill_address = userService.selectAddressDetail(order.getBill_addr_id());
				model.addAttribute("bill_address", bill_address);
			}
			if( !"".equals(order.getShip_addr_id()) && order.getShip_addr_id()!=null ){
				Address ship_address = userService.selectAddressDetail(order.getShip_addr_id());
				model.addAttribute("ship_address", ship_address);
			}
			if( !"".equals(order.getReceive_wh_id()) && order.getReceive_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(order.getReceive_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			List<OrderFlat> orderItems = orderService.getItemsByOrderId(orderId);
			
			model.addAttribute("owner", owner);
			model.addAttribute("order", order);
			model.addAttribute("orderItems", orderItems);
			return "so-admin/so_return_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updateSORInfo.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateSORInfo(HttpServletRequest request, HttpSession session) throws Exception{
		
		String customer_id = request.getParameter("customer_id");
		String receive_wh_id = request.getParameter("receive_wh_id");
		String owner_id = request.getParameter("owner_id");
		String comment = request.getParameter("comment");
		String orderId = request.getParameter("orderId");
		String ship_addr_id = request.getParameter("ship_addr_id");
		String bill_addr_id = request.getParameter("bill_addr_id");
		String update_by = session.getAttribute("userid").toString();
		
		try{			
			OrderFlat order = orderService.getOrderById(orderId);
			if( !"".equals(customer_id) && customer_id!=null ){
				order.setAccount_id(customer_id);
			}
			if( !"".equals(receive_wh_id) && receive_wh_id!=null ){
				order.setReceive_wh_id(receive_wh_id);
			}
			if( !"".equals(owner_id) && owner_id!=null ){
				order.setOwner_id(owner_id);
			}
			if( !"".equals(comment) && comment!=null ){
				order.setOrder_comment(comment);
			}
			if( !"".equals(ship_addr_id) && ship_addr_id!=null ){
				order.setShip_addr_id(ship_addr_id);
			}
			if( !"".equals(bill_addr_id) && bill_addr_id!=null ){
				order.setBill_addr_id(bill_addr_id);
			}
			order.setUpdate_by_id(update_by);
			order.setUpdate_by_name(session.getAttribute("username").toString());
			orderService.updateOrderById(order);
			
			return "{'objname':'销售退货订单'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/generateReq.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String generateReq(HttpServletRequest request, HttpSession session) throws Exception{
		
		try{
			String orderId = request.getParameter("orderId");
			OrderFlat order = new OrderFlat();
			
			order.setOrder_id(orderId);
			order.setUpdate_by_id(session.getAttribute("userid").toString());
			order.setUpdate_by_name(session.getAttribute("username").toString());
			orderService.generateReq(order);
			
			return "{'objname':'出入库单'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}

	
	@RequestMapping(value="/queryApprovalStatus.do",method = RequestMethod.POST)
	@ResponseBody
	public List<ApprovalStep> queryApprovalStatus(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String orderId = request.getParameter("orderId");
			String type = request.getParameter("type");
			return authService.queryApproval(orderId, type);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	
	@RequestMapping(value="/exportPO.do", method = RequestMethod.GET)
	public void exportPO(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			String orderId = request.getParameter("orderId");
			String orderNumber = request.getParameter("orderNumber");
			
			//输出Excel文件
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=PO"+orderNumber+".xlsx");
			response.setContentType("application/ms-excel");
			msOfficeService.exportPO(orderId,response.getOutputStream());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/exportSO.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public void exportSO(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			String orderId = request.getParameter("orderId");
			String orderNumber = request.getParameter("orderNumber");
			
			//输出Excel文件
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=SO"+orderNumber+".xlsx");
			response.setContentType("application/ms-excel");
			msOfficeService.exportSO(orderId,response.getOutputStream());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/po_import_view.do", method = RequestMethod.GET)
	public String poImportView(HttpServletRequest request, HttpSession session) throws Exception{
		
		try{
			
			return "po-admin/po_import";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/po_all_import_view.do", method = RequestMethod.GET)
	public String poAllImportView(HttpServletRequest request, HttpSession session) throws Exception{
		
		try{
			
			return "po-admin/po_all_import";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/so_import_view.do", method = RequestMethod.GET)
	public String soImportView(HttpServletRequest request, HttpSession session) throws Exception{
		
		try{
			
			return "so-admin/so_import";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/so_all_import_view.do", method = RequestMethod.GET)
	public String soAllImportView(HttpServletRequest request, HttpSession session) throws Exception{
		
		try{
			
			return "so-admin/so_all_import";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	//采购订单导入
	@RequestMapping(value="/po_import.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> poImport(@RequestParam("files[]") List<MultipartFile> files,HttpServletRequest request, HttpSession session) throws Exception{
		String errMsg = "";
		Map<String, Object> fileMap = new HashMap<String, Object>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		List<Map<String, Object>> filesList = new ArrayList<Map<String, Object>>();

		for (int i =0; i< files.size(); i++) {
			MultipartFile file = files.get(i);
			String fileName = file.getOriginalFilename();

			if (!file.isEmpty()) {
				try {
					OrderFlat newPO = msOfficeService.importPO(file.getInputStream());
					fileMap.put("name", newPO.getOrder_number());
					fileMap.put("url", "po_details.do?id="+newPO.getOrder_id());
					filesList.add(fileMap);
				} catch (Exception e) {
					logger.info(e);
					errMsg = "You failed to import " + fileName + " => " + e.getMessage();
					logger.info(errMsg);
					fileMap.clear();
					fileMap.put("error", errMsg); 
					filesList.add(fileMap);
				}
			} else {
				errMsg = "You failed to import " + fileName + " because the file was empty.";
				logger.info(errMsg); 
				fileMap.clear();
				fileMap.put("error", errMsg);
				filesList.add(fileMap);
			}
		}
		responseMap.put("files", filesList);
		return responseMap;
		
    }
}
