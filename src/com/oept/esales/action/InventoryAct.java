package com.oept.esales.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oept.esales.model.Account;
import com.oept.esales.model.Address;
import com.oept.esales.model.ApprovalDetail;
import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.ApprovalStep;
import com.oept.esales.model.Contract;
import com.oept.esales.model.ListOfValue;
import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.Product;
import com.oept.esales.model.RequisitionFlat;
import com.oept.esales.model.RequisitionHistory;
import com.oept.esales.model.StockHistory;
import com.oept.esales.model.User;
import com.oept.esales.model.WarehouseFlat;
import com.oept.esales.model.WarehouseStock;
import com.oept.esales.service.AccountService;
import com.oept.esales.service.AuthService;
import com.oept.esales.service.ContractService;
import com.oept.esales.service.InventoryService;
import com.oept.esales.service.OrderService;
import com.oept.esales.service.ProductService;
import com.oept.esales.service.SystemService;
import com.oept.esales.service.UserService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Inventory administration actions.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Controller
@RequestMapping(value="/inventory")
public class InventoryAct {
	
	private static final Log logger = LogFactory.getLog(InventoryAct.class);
	@Qualifier("inventoryService")
	@Autowired
	private InventoryService inventoryService;
	@Qualifier("userService")
	@Autowired
	private UserService userService;
	@Qualifier("productService")
	@Autowired
	private ProductService productService;
	@Qualifier("accountService")
	@Autowired
	private AccountService accountService;
	@Qualifier("systemService")
	@Autowired
	private SystemService systemService;
	@Qualifier("contractService")
	@Autowired
	private ContractService contractService;
	@Qualifier("orderService")
	@Autowired
	private OrderService orderService;
	@Qualifier("authService")
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/warehouse_list.do",method = RequestMethod.GET)
	public String warehouseList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<WarehouseFlat> warehouseList = inventoryService.getAllWarehouses();
			model.addAttribute("warehouseList", warehouseList);
			return "inventory/warehouse_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/warehouse_details.do",method = RequestMethod.GET)
	public String warehouseDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String warehouseId = request.getParameter("id");
			WarehouseFlat warehouse = inventoryService.getWarehouseById(warehouseId);
			List<WarehouseFlat> items_list = inventoryService.getItemsByWarehouseId(warehouseId);
			model.addAttribute("items_list", items_list);
			model.addAttribute("warehouseDetails", warehouse);
			return "inventory/warehouse_details";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/warehouse_details_readonly.do",method = RequestMethod.GET)
	public String warehouseDetailsReadonly(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String warehouseId = request.getParameter("id");
			WarehouseFlat warehouse = inventoryService.getWarehouseById(warehouseId);
			List<WarehouseFlat> items_list = inventoryService.getItemsByWarehouseId(warehouseId);
			model.addAttribute("items_list", items_list);
			model.addAttribute("warehouseDetails", warehouse);
			return "inventory/warehouse_details_readonly";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/warehouse_stock_list.do",method = RequestMethod.GET)
	public String warehouseStockList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String productId = request.getParameter("productId");
			WarehouseStock warehouse_stock_search = new WarehouseStock();
			warehouse_stock_search.setProduct_id(productId);
			List<WarehouseStock> items_list = inventoryService.getStockInfos(warehouse_stock_search, null, null, null, null);
			model.addAttribute("items_list", items_list);
			return "inventory/warehouse_stock_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/warehouse_create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createWarehouse(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String name = request.getParameter("name");
			String number = request.getParameter("number");
			String comment = request.getParameter("comment");
			String addressId = request.getParameter("addressId");
			String addressName = request.getParameter("addressName");
			String contactName = request.getParameter("contactName");
			String contactPhone = request.getParameter("contactPhone");
			WarehouseFlat warehouse = new WarehouseFlat();
			warehouse.setName(name);
			warehouse.setNumber(number);
			warehouse.setComment(comment);
			warehouse.setPrimary_addr_id(addressId);
			warehouse.setAddress_name(addressName);
			warehouse.setPrimary_contact_name(contactName);
			warehouse.setPrimary_contact_cellphone(contactPhone);
			warehouse.setCreated_by_user_id(session.getAttribute("userid").toString());
			warehouse.setUpdated_by_user_id(session.getAttribute("userid").toString());
			
			if(inventoryService.addWarehouse(warehouse)){
				return "{'id':'success'}";
			}		
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/warehouse_delete.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delWarehouseById(HttpServletRequest request, HttpSession session) throws Exception{
		
		String ids = request.getParameter("ids");
		
		try {				
				String id[] = ids.split(",");
				for(int i=0;i<id.length;i++){
					inventoryService.delWarehouseById(id[i]);
				}			
				return "{'objname':'仓库'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/warehouse_update.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateWarehouseById(HttpServletRequest request, HttpSession session) throws Exception{
		
		String warehouse_id = request.getParameter("warehouse_id");
		String warehouse_number = request.getParameter("warehouse[number]");
		String warehouse_name = request.getParameter("warehouse[name]");
		String contact_name = request.getParameter("warehouse[contact_name]");
		String contact_phone = request.getParameter("warehouse[contact_phone]");
		String address_id = request.getParameter("warehouse[address_id]");
		String address_name = request.getParameter("warehouse[address_name]");
		boolean active = Boolean.parseBoolean(request.getParameter("warehouse[acitve]"));
		String desc = request.getParameter("description");
		String update_by = session.getAttribute("userid").toString();
		
		try{			
			WarehouseFlat warehouse = new WarehouseFlat();
			warehouse.setId(warehouse_id);
			warehouse.setName(warehouse_name);
			warehouse.setNumber(warehouse_number);
			warehouse.setPrimary_addr_id(address_id);
			warehouse.setPrimary_contact_name(contact_name);
			warehouse.setPrimary_contact_cellphone(contact_phone);
			warehouse.setAddress_name(address_name);
			warehouse.setComment(desc);
			warehouse.setUpdated_by_user_id(update_by);
			warehouse.setActive(active);
			
			inventoryService.updateWarehouse(warehouse);
			return "{'objname':'仓库'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	@RequestMapping(value="/loadAvailArea.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Address> loadAvailAreas(HttpServletRequest request, HttpSession session) throws Exception{

		try{
				
			return userService.selectAddressLists();
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/loadAllProd.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Product> loadAllProducts(HttpServletRequest request, HttpSession session) throws Exception{

		try{
				
			return productService.getAllProducts();
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/loadAvailProd.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Product> loadAvailProducts(HttpServletRequest request, HttpSession session) throws Exception{

		try{
				
			return productService.getAvailProducts();
			
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
	
	@RequestMapping(value="/loadAvailContracts.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Contract> loadAvailContracts(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			
			return contractService.getAllContractList();
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/add_stock_info.do",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String addStockInfo(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String warehouse_id = request.getParameter("warehouse[id]");
			String product_id = request.getParameter("stock[product-id]");
			int stock = Integer.parseInt(request.getParameter("stock_quantity"));
			String sku = request.getParameter("stock[sku]");
			WarehouseStock warehouse_stock = new WarehouseStock();
			warehouse_stock.setWarehouse_id(warehouse_id);
			warehouse_stock.setProduct_id(product_id);
			warehouse_stock.setStock(stock);
			warehouse_stock.setSku(sku);
			warehouse_stock.setOrdered_stock_in(0);
			warehouse_stock.setOrdered_stock_out(0);
			warehouse_stock.setCreated_by_user_id(session.getAttribute("userid").toString());
			warehouse_stock.setUpdated_by_user_id(session.getAttribute("userid").toString());
			
			if(inventoryService.addStockInfo(warehouse_stock)){
				return "{'objname':'库存产品'}";
			}		
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/del_stock_info.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delStockInfoById(HttpServletRequest request, HttpSession session) throws Exception{
		
		String id = request.getParameter("item_id");
		
		try {	
				inventoryService.delStockInfoById(id,session.getAttribute("userid").toString());
				return "{'objname':'库存产品'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/edit_stock_info.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String editStockInfoById(HttpServletRequest request, HttpSession session) throws Exception{
		
		try {	
			String id = request.getParameter("stockInfo[id]");
			int stock = Integer.parseInt(request.getParameter("stock_quantity"));
			String sku = request.getParameter("stock[sku]");
			
			WarehouseStock warehouse_stock = new WarehouseStock();
			warehouse_stock.setId(id);
			warehouse_stock.setSku(sku);
			warehouse_stock.setStock(stock);
			warehouse_stock.setOrdered_stock_in(-1);
			warehouse_stock.setOrdered_stock_out(-1);
			warehouse_stock.setUpdated_by_user_id(session.getAttribute("userid").toString());
			inventoryService.updateStockInfoById(warehouse_stock);
			return "{'objname':'库存产品'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
//			String[] ids = request.getParameterValues("id[]");
//			String customActionType = request.getParameter("customActionType");
//			String customActionName = request.getParameter("customActionName");
			String action = request.getParameter("action");
			String warehouse_id = request.getParameter("warehouse_id");
			StockHistory stockHistorySearch = new StockHistory();
			
			stockHistorySearch.setWarehouse_id(warehouse_id);
			int iTotalRecords = inventoryService.getHistories(stockHistorySearch, null, null, null, null).size();
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<StockHistory> stockHistoryList = new ArrayList<StockHistory>();
			
			if(action!=null && action.equals("filter")){
				stockHistorySearch.setCreated_date_from(request.getParameter("stock_history_date_from"));
				stockHistorySearch.setCreated_date_to(request.getParameter("stock_history_date_to"));
				stockHistorySearch.setProduct_name(request.getParameter("stock_history_product_name"));
				stockHistorySearch.setStock_type_code(request.getParameter("stock_history_type"));
				stockHistorySearch.setStock_quantity_from(request.getParameter("stock_history_quantity_from"));
				stockHistorySearch.setStock_quantity_to(request.getParameter("stock_history_quantity_to"));
				stockHistorySearch.setRequisition_number(request.getParameter("stock_history_req_number"));
//				stockHistorySearch.setStock_price_from(request.getParameter("stock_history_price_from"));
//				stockHistorySearch.setStock_price_to(request.getParameter("stock_history_price_to"));
//				stockHistorySearch.setStock_amount_from(request.getParameter("stock_history_amount_from"));
//				stockHistorySearch.setStock_amount_to(request.getParameter("stock_history_amount_to"));
				stockHistorySearch.setDescription(request.getParameter("stock_history_desc"));
				stockHistorySearch.setOriginal_stock_from(request.getParameter("stock_history_original_from"));
				stockHistorySearch.setOriginal_stock_to(request.getParameter("stock_history_original_to"));
				
				stockHistoryList = inventoryService.getHistories(stockHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", inventoryService.getHistories(stockHistorySearch, null, 
						null, null, null).size());
			}else{
				stockHistoryList = inventoryService.getHistories(stockHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < stockHistoryList.size(); i++)
	        {  
				StockHistory stockHistory = stockHistoryList.get(i); 
				List<String> dataRow = new ArrayList<String>();
				dataRow.add(stockHistory.getCreated_date());
				dataRow.add(stockHistory.getProduct_name());
				dataRow.add(stockHistory.getStock_type_val());
				dataRow.add(String.valueOf(stockHistory.getOriginal_stock()));
				dataRow.add(String.valueOf(stockHistory.getStock_quantity()));
//				dataRow.add(String.valueOf(stockHistory.getStock_price()));
//				dataRow.add(String.valueOf(stockHistory.getStock_amount()));
				dataRow.add(stockHistory.getDescription());
				if(stockHistory.getStock_type_code().equals("Stock In")){
					dataRow.add("<a href=\"../inventory/stock_in_details.do?id="+stockHistory.getRequisition_id()+"\" >"+stockHistory.getRequisition_number()+"</a>");
				}else if(stockHistory.getStock_type_code().equals("Stock Out")){
					dataRow.add("<a href=\"../inventory/stock_out_details.do?id="+stockHistory.getRequisition_id()+"\" >"+stockHistory.getRequisition_number()+"</a>");
				}
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
	
	@RequestMapping(value="/loadItems.do",method = RequestMethod.GET)
	@ResponseBody
	public List<RequisitionFlat> loadOrderItems(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String reqId = request.getParameter("reqId");
				
			return inventoryService.getItemsByReqId(reqId);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadAvailPO.do",method = RequestMethod.GET)
	@ResponseBody
	public List<OrderFlat> loadAvailPO(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Purchase Order");
			orderSearch.setStatus_code("processing");
				
			return orderService.getOrders(orderSearch, null, null, null, null);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/loadAvailSO.do",method = RequestMethod.GET)
	@ResponseBody
	public List<OrderFlat> loadAvailSO(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			OrderFlat orderSearch = new OrderFlat();
			orderSearch.setOrder_type_cd("Sales Order");
			orderSearch.setStatus_code("processing");
				
			return orderService.getOrders(orderSearch, null, null, null, null);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/editReqAmounts.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateReqAmounts(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat req = inventoryService.getRequisitionById(request.getParameter("reqId"));
			req.setBase_amount(Double.parseDouble(request.getParameter("base_total")));
			req.setDue_amount(Double.parseDouble(request.getParameter("due_total")));
			req.setUpdate_by_id(session.getAttribute("userid").toString());
			req.setUpdate_by_name(session.getAttribute("username").toString());
			inventoryService.updateRequisition(req);
			
			return "{'objname':'单据总金额'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/req_history_listdata.do",method = RequestMethod.POST)
	@ResponseBody
	public String reqHistoryListData(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Object> data = new ArrayList<Object>();
			Map<String, Object> response = new HashMap<String, Object>();
			int length = Integer.parseInt(request.getParameter("length"));
			int start = Integer.parseInt(request.getParameter("start"));
			int sEcho = Integer.parseInt(request.getParameter("draw"));
			int sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			String sortDir = request.getParameter("order[0][dir]");
			String action = request.getParameter("action");
			String reqId = request.getParameter("reqId");
			RequisitionHistory reqHistorySearch = new RequisitionHistory();
			
			reqHistorySearch.setRequisition_id(reqId);
			int iTotalRecords = inventoryService.getReqHistories(reqHistorySearch, null, null, null, null).size();
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<RequisitionHistory> reqHistoryList = new ArrayList<RequisitionHistory>();
			
			if(action!=null && action.equals("filter")){
				reqHistorySearch.setCreated_date_from(request.getParameter("order_history_date_from"));
				reqHistorySearch.setCreated_date_to(request.getParameter("order_history_date_to"));
				reqHistorySearch.setDescription(request.getParameter("order_history_desc"));
				reqHistorySearch.setCreated_by_user_name(request.getParameter("order_created_by_username"));
				
				reqHistoryList = inventoryService.getReqHistories(reqHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", inventoryService.getReqHistories(reqHistorySearch, null, 
						null, null, null).size());
			}else{
				reqHistoryList = inventoryService.getReqHistories(reqHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < reqHistoryList.size(); i++)
	        {  
				RequisitionHistory reqHistory = reqHistoryList.get(i); 
				List<String> dataRow = new ArrayList<String>();
				dataRow.add(reqHistory.getCreated_date());
				dataRow.add(reqHistory.getDescription());
				dataRow.add(reqHistory.getCreated_by_user_name());
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
	@RequestMapping(value="/req_approval_history_listdata.do",method = RequestMethod.POST)
	@ResponseBody
	public String reqApprovalHistoryListData(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Object> data = new ArrayList<Object>();
			Map<String, Object> response = new HashMap<String, Object>();
			int length = Integer.parseInt(request.getParameter("length"));
			int start = Integer.parseInt(request.getParameter("start"));
			int sEcho = Integer.parseInt(request.getParameter("draw"));
			int sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			String sortDir = request.getParameter("order[0][dir]");
			String action = request.getParameter("action");
			String reqId = request.getParameter("reqId");
			ApprovalHistory approvalHistorySearch = new ApprovalHistory();
			
			approvalHistorySearch.setRequisition_id(reqId);
			int iTotalRecords = inventoryService.getApprovalHistories(approvalHistorySearch, null, null, null, null).size();
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<ApprovalHistory> reqApprovalHistoryList = new ArrayList<ApprovalHistory>();
			
			if(action!=null && action.equals("filter")){
				approvalHistorySearch.setCreated_date_from(request.getParameter("order_history_date_from"));
				approvalHistorySearch.setCreated_date_to(request.getParameter("order_history_date_to"));
				approvalHistorySearch.setDescription(request.getParameter("order_history_desc"));
				approvalHistorySearch.setCreated_by_user_name(request.getParameter("order_created_by_username"));
				
				reqApprovalHistoryList = inventoryService.getApprovalHistories(approvalHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", inventoryService.getApprovalHistories(approvalHistorySearch, null, 
						null, null, null).size());
			}else{
				reqApprovalHistoryList = inventoryService.getApprovalHistories(approvalHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < reqApprovalHistoryList.size(); i++)
	        {  
				ApprovalHistory reqApprovalHistory = reqApprovalHistoryList.get(i); 
				List<String> dataRow = new ArrayList<String>();
				dataRow.add(reqApprovalHistory.getCreated_date());
				dataRow.add(reqApprovalHistory.getDescription());
				dataRow.add(reqApprovalHistory.getCreated_by_user_name());
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
	
	@RequestMapping(value="/stock_in_list.do",method = RequestMethod.GET)
	public String stockInList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setRequisition_type_cd("Stock In Requisition");
			requisitionSearch.setOwner_id(session.getAttribute("userid").toString());
			List<RequisitionFlat> requisitionList = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			
			ListOfValue list_of_value = new ListOfValue();
			list_of_value.setList_code("Stock In Type");
			List<ListOfValue> stock_in_type_list = systemService.getListOfValues(list_of_value, null, null, null, null);
			model.addAttribute("requisitionList", requisitionList);
			model.addAttribute("stock_in_type_list", stock_in_type_list);
			return "inventory/stock_in_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/stock_in_details.do",method = RequestMethod.GET)
	public String stockInDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String reqId = request.getParameter("id");
			RequisitionFlat requisition = inventoryService.getRequisitionById(reqId);
			List<RequisitionFlat> items_list = inventoryService.getItemsByReqId(reqId);
			User owner = userService.selectUserDetail(requisition.getOwner_id());
			if( !"".equals(requisition.getReceive_wh_id()) && requisition.getReceive_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(requisition.getReceive_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Stock In Type");
			List<ListOfValue> stock_in_type_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			boolean readOnlyFlag = false;
			switch(requisition.getStatus_code()){
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
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("stock_in_type_list",stock_in_type_list);
			model.addAttribute("owner", owner);
			model.addAttribute("items_list", items_list);
			model.addAttribute("requisitionDetails", requisition);
			
			if(requisition.getOrder_id()!=null && !"".equals(requisition.getOrder_id())){
				OrderFlat related_order = orderService.getOrderById(requisition.getOrder_id());
				model.addAttribute("related_order", related_order);
			}
			
			return "inventory/stock_in_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_in_all_details.do",method = RequestMethod.GET)
	public String stockInAllDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String reqId = request.getParameter("id");
			RequisitionFlat requisition = inventoryService.getRequisitionById(reqId);
			List<RequisitionFlat> items_list = inventoryService.getItemsByReqId(reqId);
			User owner = userService.selectUserDetail(requisition.getOwner_id());
			if( !"".equals(requisition.getReceive_wh_id()) && requisition.getReceive_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(requisition.getReceive_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Stock In Type");
			List<ListOfValue> stock_in_type_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			boolean readOnlyFlag = false;
			switch(requisition.getStatus_code()){
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
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("stock_in_type_list",stock_in_type_list);
			model.addAttribute("owner", owner);
			model.addAttribute("items_list", items_list);
			model.addAttribute("requisitionDetails", requisition);
			
			if(requisition.getOrder_id()!=null && !"".equals(requisition.getOrder_id())){
				OrderFlat related_order = orderService.getOrderById(requisition.getOrder_id());
				model.addAttribute("related_order", related_order);
			}
			
			return "inventory/stock_in_all_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_in_approval_details.do",method = RequestMethod.GET)
	public String stockInApprovalDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String reqId = request.getParameter("id");
			RequisitionFlat requisition = inventoryService.getRequisitionById(reqId);
			List<RequisitionFlat> items_list = inventoryService.getItemsByReqId(reqId);
			User owner = userService.selectUserDetail(requisition.getOwner_id());
			if( !"".equals(requisition.getReceive_wh_id()) && requisition.getReceive_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(requisition.getReceive_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Stock In Type");
			List<ListOfValue> stock_in_type_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			boolean readOnlyFlag = false;
			switch(requisition.getStatus_code()){
			case "submit - approved":
				readOnlyFlag = true;
				break;
			case "submit - rejected":
				readOnlyFlag = true;
				break;
			case "submit - pending":
				ApprovalDetail userApprovalDetail = authService.queryApprovalDetailStatus(reqId, "Stock In Requisition", session.getAttribute("userid").toString());
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
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("stock_in_type_list",stock_in_type_list);
			model.addAttribute("owner", owner);
			model.addAttribute("items_list", items_list);
			model.addAttribute("requisitionDetails", requisition);
			
			if(requisition.getOrder_id()!=null && !"".equals(requisition.getOrder_id())){
				OrderFlat related_order = orderService.getOrderById(requisition.getOrder_id());
				model.addAttribute("related_order", related_order);
			}
			
			return "inventory/stock_in_approval_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/stock_in_all_list.do",method = RequestMethod.GET)
	public String stockInAllList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setRequisition_type_cd("Stock In Requisition");
			List<RequisitionFlat> requisitionList = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			
			ListOfValue list_of_value = new ListOfValue();
			list_of_value.setList_code("Stock In Type");
			List<ListOfValue> stock_in_type_list = systemService.getListOfValues(list_of_value, null, null, null, null);
			model.addAttribute("stock_in_type_list", stock_in_type_list);
			model.addAttribute("requisitionList", requisitionList);
			return "inventory/stock_in_all_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_in_approval_list.do",method = RequestMethod.GET)
	public String stockInApprovalList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setRequisition_type_cd("Stock In Requisition");
			List<RequisitionFlat> requisitionList = inventoryService.getRequisitionsForApprover(requisitionSearch, 
					session.getAttribute("userid").toString(),null, null, null, null);
			model.addAttribute("requisitionList", requisitionList);
			return "inventory/stock_in_approval_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/stock_out_list.do",method = RequestMethod.GET)
	public String stockOutList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setRequisition_type_cd("Stock Out Requisition");
			requisitionSearch.setOwner_id(session.getAttribute("userid").toString());
			List<RequisitionFlat> requisitionList = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			
			ListOfValue list_of_value = new ListOfValue();
			list_of_value.setList_code("Stock Out Type");
			List<ListOfValue> stock_out_type_list = systemService.getListOfValues(list_of_value, null, null, null, null);
			model.addAttribute("stock_out_type_list", stock_out_type_list);
			model.addAttribute("requisitionList", requisitionList);
			return "inventory/stock_out_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_out_all_list.do",method = RequestMethod.GET)
	public String stockOutAllList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setRequisition_type_cd("Stock Out Requisition");
			List<RequisitionFlat> requisitionList = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			
			ListOfValue list_of_value = new ListOfValue();
			list_of_value.setList_code("Stock Out Type");
			List<ListOfValue> stock_out_type_list = systemService.getListOfValues(list_of_value, null, null, null, null);
			model.addAttribute("stock_out_type_list", stock_out_type_list);
			model.addAttribute("requisitionList", requisitionList);
			return "inventory/stock_out_all_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_out_approval_list.do",method = RequestMethod.GET)
	public String stockOutApprovalList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setRequisition_type_cd("Stock Out Requisition");
			List<RequisitionFlat> requisitionList = inventoryService.getRequisitionsForApprover(requisitionSearch, 
					session.getAttribute("userid").toString(),null, null, null, null);
			model.addAttribute("requisitionList", requisitionList);
			return "inventory/stock_out_approval_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_out_details.do",method = RequestMethod.GET)
	public String stockOutDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String reqId = request.getParameter("id");
			RequisitionFlat requisition = inventoryService.getRequisitionById(reqId);
			List<RequisitionFlat> items_list = inventoryService.getItemsByReqId(reqId);
			User owner = userService.selectUserDetail(requisition.getOwner_id());
			if( !"".equals(requisition.getDelivery_wh_id()) && requisition.getDelivery_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(requisition.getDelivery_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			if( !"".equals(requisition.getAccount_id()) && requisition.getAccount_id()!=null ){
				Account customer = accountService.selectAtDe(new Object[]{requisition.getAccount_id()});
				model.addAttribute("customer", customer);
			}
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Stock Out Type");
			List<ListOfValue> stock_out_type_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			boolean readOnlyFlag = false;
			switch(requisition.getStatus_code()){
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
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("stock_out_type_list",stock_out_type_list);
			model.addAttribute("owner", owner);
			model.addAttribute("items_list", items_list);
			model.addAttribute("requisitionDetails", requisition);
			
			if(requisition.getOrder_id()!=null && !"".equals(requisition.getOrder_id())){
				OrderFlat related_order = orderService.getOrderById(requisition.getOrder_id());
				model.addAttribute("related_order", related_order);
			}
			
			return "inventory/stock_out_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_out_all_details.do",method = RequestMethod.GET)
	public String stockOutAllDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String reqId = request.getParameter("id");
			RequisitionFlat requisition = inventoryService.getRequisitionById(reqId);
			List<RequisitionFlat> items_list = inventoryService.getItemsByReqId(reqId);
			User owner = userService.selectUserDetail(requisition.getOwner_id());
			if( !"".equals(requisition.getDelivery_wh_id()) && requisition.getDelivery_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(requisition.getDelivery_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			if( !"".equals(requisition.getAccount_id()) && requisition.getAccount_id()!=null ){
				Account customer = accountService.selectAtDe(new Object[]{requisition.getAccount_id()});
				model.addAttribute("customer", customer);
			}
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Stock Out Type");
			List<ListOfValue> stock_out_type_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			boolean readOnlyFlag = false;
			switch(requisition.getStatus_code()){
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
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("stock_out_type_list",stock_out_type_list);
			model.addAttribute("owner", owner);
			model.addAttribute("items_list", items_list);
			model.addAttribute("requisitionDetails", requisition);
			
			if(requisition.getOrder_id()!=null && !"".equals(requisition.getOrder_id())){
				OrderFlat related_order = orderService.getOrderById(requisition.getOrder_id());
				model.addAttribute("related_order", related_order);
			}
			
			return "inventory/stock_out_all_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_out_approval_details.do",method = RequestMethod.GET)
	public String stockOutApprovalDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String reqId = request.getParameter("id");
			RequisitionFlat requisition = inventoryService.getRequisitionById(reqId);
			List<RequisitionFlat> items_list = inventoryService.getItemsByReqId(reqId);
			User owner = userService.selectUserDetail(requisition.getOwner_id());
			if( !"".equals(requisition.getDelivery_wh_id()) && requisition.getDelivery_wh_id()!=null ){
				WarehouseFlat warehouse = inventoryService.getWarehouseById(requisition.getDelivery_wh_id());
				model.addAttribute("warehouse", warehouse);
			}
			if( !"".equals(requisition.getAccount_id()) && requisition.getAccount_id()!=null ){
				Account customer = accountService.selectAtDe(new Object[]{requisition.getAccount_id()});
				model.addAttribute("customer", customer);
			}
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Stock Out Type");
			List<ListOfValue> stock_out_type_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			boolean readOnlyFlag = false;
			switch(requisition.getStatus_code()){
			case "submit - approved":
				readOnlyFlag = true;
				break;
			case "submit - rejected":
				readOnlyFlag = true;
				break;
			case "submit - pending":
				ApprovalDetail userApprovalDetail = authService.queryApprovalDetailStatus(reqId, "Stock Out Requisition", session.getAttribute("userid").toString());
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
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			model.addAttribute("stock_out_type_list",stock_out_type_list);
			model.addAttribute("owner", owner);
			model.addAttribute("items_list", items_list);
			model.addAttribute("requisitionDetails", requisition);
			
			if(requisition.getOrder_id()!=null && !"".equals(requisition.getOrder_id())){
				OrderFlat related_order = orderService.getOrderById(requisition.getOrder_id());
				model.addAttribute("related_order", related_order);
			}
			
			return "inventory/stock_out_approval_view";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/stock_transfer_list.do",method = RequestMethod.GET)
	public String stockTransferList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setRequisition_type_cd("Stock Transfer Requisition");
			requisitionSearch.setOwner_id(session.getAttribute("userid").toString());
			List<RequisitionFlat> requisitionList = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			model.addAttribute("requisitionList", requisitionList);
			return "inventory/stock_transfer_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_transfer_all_list.do",method = RequestMethod.GET)
	public String stockTransferAllList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisitionSearch = new RequisitionFlat();
			requisitionSearch.setRequisition_type_cd("Stock Transfer Requisition");
			List<RequisitionFlat> requisitionList = inventoryService.getRequisitions(requisitionSearch, null, null, null, null);
			model.addAttribute("requisitionList", requisitionList);
			return "inventory/stock_transfer_all_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/stock_transfer_approval_list.do",method = RequestMethod.GET)
	public String stockTransferApprovalList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<WarehouseFlat> warehouseList = inventoryService.getAllWarehouses();
			model.addAttribute("warehouseList", warehouseList);
			return "inventory/stock_transfer_approval_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/stock_in_create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createStockInReq(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisition = new RequisitionFlat();
			requisition.setCreated_by_id(session.getAttribute("userid").toString());
			requisition.setCreated_by_name(session.getAttribute("username").toString());
			requisition.setUpdate_by_id(session.getAttribute("userid").toString());
			requisition.setOwner_id(session.getAttribute("userid").toString());
			requisition.setRequisition_manual_number(request.getParameter("man_number"));
			requisition.setRequisition_comment(request.getParameter("desc"));
			requisition.setReceive_wh_id(request.getParameter("warehouse_id"));
			requisition.setReceive_date(Date.valueOf(request.getParameter("receive_date")));
			String sub_type_code = request.getParameter("sub_type");
			requisition.setRequisition_subtype_cd(sub_type_code);
			requisition.setRequisition_subtype_val(systemService.getValueByCodeName("Stock In Type", sub_type_code));
			
			if(inventoryService.addStockInReq(requisition)!=null){
				return "{'id':'success'}";
			}
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/stock_out_create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createStockOutReq(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisition = new RequisitionFlat();
			requisition.setCreated_by_id(session.getAttribute("userid").toString());
			requisition.setCreated_by_name(session.getAttribute("username").toString());
			requisition.setUpdate_by_id(session.getAttribute("userid").toString());
			requisition.setOwner_id(session.getAttribute("userid").toString());
			requisition.setRequisition_manual_number(request.getParameter("man_number"));
			requisition.setRequisition_comment(request.getParameter("desc"));
			requisition.setDelivery_wh_id(request.getParameter("warehouse_id"));
			requisition.setAccount_id(request.getParameter("customer_id"));
			requisition.setDelivery_date(Date.valueOf(request.getParameter("delivery_date")));
			String sub_type_code = request.getParameter("sub_type");
			requisition.setRequisition_subtype_cd(sub_type_code);
			requisition.setRequisition_subtype_val(systemService.getValueByCodeName("Stock Out Type", sub_type_code));
			
			if(inventoryService.addStockOutReq(requisition)!=null){
				return "{'id':'success'}";
			}
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/addItem.do",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String addReqItem(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisition = new RequisitionFlat();
			requisition.setRequisition_id(request.getParameter("req[id]"));
			requisition.setItem_prod_id(request.getParameter("product[id]"));
			requisition.setItem_prod_name(request.getParameter("product[name]"));
			//requisition.setItem_base_price(Double.parseDouble(request.getParameter("product[price]")));
			//requisition.setItem_due_price(Double.parseDouble(request.getParameter("purchase_price")));
			//requisition.setItem_base_amount(Double.parseDouble(request.getParameter("base_amount")));
			requisition.setItem_quantity(Integer.parseInt(request.getParameter("purchase_quantity")));
			//requisition.setItem_due_amount(Double.parseDouble(request.getParameter("purchase_amount")));
			//requisition.setItem_contract_id(request.getParameter("contract[id]"));
			requisition.setItem_comment(request.getParameter("item_comment"));
			requisition.setItem_created_by_id(session.getAttribute("userid").toString());
			requisition.setItem_created_by_name(session.getAttribute("username").toString());
			requisition.setItem_update_by_id(session.getAttribute("userid").toString());
			requisition.setItem_update_by_name(session.getAttribute("username").toString());
			
			//test
			String requisition_type_value = request.getParameter("requisition[type_value]");//入库单
			requisition.setRequisition_type(requisition_type_value);
			
			String user_id = session.getAttribute("userid").toString();
			
			
			inventoryService.addRequisitionItem(requisition);
			
			return "{'objname':'入库项'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/editItem.do",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String editReqItem(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			RequisitionFlat requisition = inventoryService.getItemsById(request.getParameter("order_item[id]"));
			//test
			requisition.setRequisition_id(request.getParameter("req[id]"));
			String requisition_type_value = request.getParameter("requisition[type_value]");//入库单
			requisition.setRequisition_type(requisition_type_value);
			
			requisition.setItem_prod_id(request.getParameter("product[id]"));
			requisition.setItem_prod_name(request.getParameter("product[name]"));
			//requisition.setItem_base_price(Double.parseDouble(request.getParameter("product[price]")));
			//requisition.setItem_due_price(Double.parseDouble(request.getParameter("purchase_price")));
			//requisition.setItem_base_amount(Double.parseDouble(request.getParameter("base_amount")));
			requisition.setItem_quantity(Integer.parseInt(request.getParameter("purchase_quantity")));
			//requisition.setItem_due_amount(Double.parseDouble(request.getParameter("purchase_amount")));
			//requisition.setItem_contract_id(request.getParameter("contract[id]"));
			requisition.setItem_comment(request.getParameter("item_comment"));
			requisition.setItem_update_by_id(session.getAttribute("userid").toString());
			requisition.setItem_update_by_name(session.getAttribute("username").toString());
			inventoryService.updateRequisitionItem(requisition);
			
			return "{'objname':'入库项'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/delItem.do",method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delReqItem(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String item_id = request.getParameter("item_id");
			String requisition_id = request.getParameter("requisition_id");
			
			//test
			String requisition_type = new String(request.getParameter("requisiton_type").getBytes("ISO8859-1"),"UTF-8");
			String product_name = new String(request.getParameter("product_name").getBytes("ISO8859-1"), "UTF-8");
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userid").toString();
			Object[] params = new Object[]{requisition_type, requisition_id, product_name, username, userid};
			inventoryService.delRequisitionItemById(item_id, params);
			return "{'objname':'出入库项'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updateReqInfo.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateReqInfo(HttpServletRequest request, HttpSession session) throws Exception{
		
		String supplier_id = request.getParameter("supplier_id");
		String receive_wh_id = request.getParameter("receive_wh_id");
		String delivery_wh_id = request.getParameter("delivery_wh_id");
		String subtype_code = request.getParameter("subtype_code");
		String customer_id = request.getParameter("customer_id");
		String owner_id = request.getParameter("owner_id");
		String comment = request.getParameter("comment");
		String reqId = request.getParameter("reqId");
		String update_by = session.getAttribute("userid").toString();
		String man_number = request.getParameter("man_number");
		String related_order_id = request.getParameter("related_order_id");
		Date receive_date = null;
		Date delivery_date = null;
		if(request.getParameter("receive_date")!=null && !"".equals(request.getParameter("receive_date"))){
			receive_date = Date.valueOf(request.getParameter("receive_date"));
		}
		if(request.getParameter("delivery_date")!=null && !"".equals(request.getParameter("delivery_date"))){
			delivery_date = Date.valueOf(request.getParameter("delivery_date"));
		}
		
		try{
			StringBuffer history_desc = new StringBuffer();
			RequisitionFlat requisition = inventoryService.getRequisitionById(reqId);
			if( !"".equals(supplier_id) && supplier_id!=null ){
				requisition.setSupplier_id(supplier_id);
				history_desc.append("更新了供应商！");
			}
			if( !"".equals(receive_wh_id) && receive_wh_id!=null ){
				requisition.setReceive_wh_id(receive_wh_id);
				history_desc.append("更新了收货仓库！");
			}
			if( !"".equals(delivery_wh_id) && delivery_wh_id!=null ){
				requisition.setDelivery_wh_id(delivery_wh_id);
				history_desc.append("更新了发货仓库！");
			}
			if( !"".equals(customer_id) && customer_id!=null ){
				requisition.setAccount_id(customer_id);
				history_desc.append("更新了集成商！");
			}
			if( !"".equals(subtype_code) && subtype_code!=null ){
				if(!(requisition.getRequisition_subtype_cd().equals(subtype_code))){
					history_desc.append("更新了单据出入库类型！");
				}
				requisition.setRequisition_subtype_cd(subtype_code);
				if(requisition.getRequisition_type_cd().equals("Stock In Requisition")){
					requisition.setRequisition_subtype_val(systemService.getValueByCodeName("Stock In Type", subtype_code));
				}else{
					requisition.setRequisition_subtype_val(systemService.getValueByCodeName("Stock Out Type", subtype_code));
				}
			}
			if( !"".equals(owner_id) && owner_id!=null ){
				requisition.setOwner_id(owner_id);
				history_desc.append("更新了经手人！");
			}
			if( !"".equals(comment) && comment!=null ){
				requisition.setRequisition_comment(comment);
				history_desc.append("更新了备注！");
			}
			if( !"".equals(man_number) && man_number!=null ){
				if(requisition.getRequisition_manual_number()==null){
					history_desc.append("更新了人工单据号！");
				}else if(!(requisition.getRequisition_manual_number().equals(man_number))){
					history_desc.append("更新了人工单据号！");
				}
				requisition.setRequisition_manual_number(man_number);
			}
			if( !"".equals(related_order_id) && related_order_id!=null ){
				if(requisition.getOrder_id()==null){
					history_desc.append("更新了关联订单！");
				}else if(!(requisition.getOrder_id().equals(related_order_id))){
					history_desc.append("更新了关联订单！");
				}
				requisition.setOrder_id(related_order_id);
			}
			if( !"".equals(receive_date) && receive_date!=null ){
				if(!( (Date.valueOf(requisition.getReceive_date())).equals(receive_date) )){
					history_desc.append("更新了入库日期！");
				}
				requisition.setReceive_date(receive_date);
			}
			if( !"".equals(delivery_date) && delivery_date!=null ){
				if(!( (Date.valueOf(requisition.getDelivery_date())).equals(delivery_date) )){
					history_desc.append("更新了出库日期！");
				}
				requisition.setDelivery_date(delivery_date);
			}
			requisition.setHistory_desc(history_desc.toString());
			requisition.setUpdate_by_id(update_by);
			requisition.setUpdate_by_name(session.getAttribute("username").toString());
			inventoryService.updateRequisition(requisition);
			
			return "{'objname':'单据'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updateReqStatus.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateReqStatus(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String reqId = request.getParameter("reqId");
			RequisitionFlat requisition = inventoryService.getRequisitionById(reqId);
			requisition.setUpdate_by_id(session.getAttribute("userid").toString());
			requisition.setUpdate_by_name(session.getAttribute("username").toString());
			requisition.setStatus_code(request.getParameter("status_code"));
			requisition.setRequisition_type_cd(request.getParameter("type_code"));
			
			return inventoryService.updateRequisitionStatusFacade(requisition);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			return "{'errmsg':'"+e.getMessage()+"'}";
		}
	}
	
	@RequestMapping(value="/sio_import_view.do", method = RequestMethod.GET)
	public String sioImportView(HttpServletRequest request, HttpSession session) throws Exception{
		
		try{
			
			return "inventory/sio_import";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/soo_import_view.do", method = RequestMethod.GET)
	public String sooImportView(HttpServletRequest request, HttpSession session) throws Exception{
		
		try{
			
			return "inventory/soo_import";
			
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
			String reqId = request.getParameter("reqId");
			String type = request.getParameter("type");
			return authService.queryApproval(reqId, type);
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
			inventoryService.delAttachmentById(id);
			
			return "{'objname':'单据附件'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
}
