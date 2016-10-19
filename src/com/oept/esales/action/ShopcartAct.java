package com.oept.esales.action;

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

import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.Shopcart;
import com.oept.esales.service.OrderService;
import com.oept.esales.service.PurchaseService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/26
 * Description: Shoppint cart center actions.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Controller
@RequestMapping(value="/shopcart")
public class ShopcartAct {
	
	private static final Log logger = LogFactory.getLog(ShopcartAct.class);
	@Qualifier("purchaseService")
	@Autowired
	private PurchaseService purchaseService;
	@Qualifier("orderService")
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/list.do",method = RequestMethod.GET)
	public String portfolioList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			
			model.addAttribute("shopcart_items", purchaseService.getAvailShopcartItems(user_id));
			return "shopcart_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/add.do",method = RequestMethod.GET)
	@ResponseBody
	public String loadAvalilProducts(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			String product_id = request.getParameter("productId");
			
			purchaseService.addProdToShopcart(product_id, user_id);
			return "sucess";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delItemsById(HttpServletRequest request, HttpSession session) throws Exception{
		
		String ids = request.getParameter("ids");
		
		try {				
				String id[] = ids.split(",");
				for(int i=0;i<id.length;i++){
					purchaseService.delShopcartItemById(id[i]);
				}			
				return "{'objname':'购物车项目'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/update.do",method = RequestMethod.POST)
	@ResponseBody
	public String updateCurrentItem(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double amount = Double.parseDouble(request.getParameter("amount"));
			String item_id = request.getParameter("item_id");
			double price = Double.parseDouble(request.getParameter("price"));
			
			Shopcart item = new Shopcart();
			item.setId(item_id);
			item.setUpdate_by_id(user_id);
			item.setQuantity(quantity);
			item.setPrice(price);
			item.setAmount(amount);
			
			purchaseService.updateItemById(item);
			return "sucess";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/generateOrder.do", method = RequestMethod.GET)
	@ResponseBody
	public String generateOrder(HttpServletRequest request, HttpSession session) throws Exception{
		
		String item_ids = request.getParameter("item_ids");
		
		try {				
				OrderFlat order = new OrderFlat();
				OrderFlat orderItem = new OrderFlat();
				String order_id = orderService.addOrder(order,session.getAttribute("userid").toString());
				String item_id[] = item_ids.split(",");
				for(int i=0;i<item_id.length;i++){
					Shopcart shop_item = purchaseService.getShopcartItemById(item_id[i]);
					orderItem.setOrder_id(order_id);
					orderItem.setItem_prod_id(shop_item.getProduct_id());
					orderItem.setItem_base_price(shop_item.getPrice());
					//orderItem.setItem_purchase_price(Double.parseDouble(request.getParameter("purchase_price")));
					orderItem.setItem_base_amount(shop_item.getPrice() * shop_item.getQuantity());
					//orderItem.setItem_discount_ratio(Float.parseFloat(request.getParameter("discount_percent")));
					//orderItem.setItem_discount_amount(Double.parseDouble(request.getParameter("discount_amount")));
					orderItem.setItem_quantity(shop_item.getQuantity());
					//orderItem.setItem_tax_ratio(Float.parseFloat(request.getParameter("tax_percent")));
					//orderItem.setItem_tax_amount(Double.parseDouble(request.getParameter("tax_amount")));
					orderItem.setItem_due_amount(shop_item.getAmount());
					orderItem.setCreated_by_id(session.getAttribute("userid").toString());
					
					orderService.addOrderItem(orderItem);
					purchaseService.delShopcartItemById(item_id[i]);
				}			
				return "{'order_id':'"+order_id+"'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
}
