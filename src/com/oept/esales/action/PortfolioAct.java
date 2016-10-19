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
import org.springframework.web.bind.annotation.ResponseBody;

import com.oept.esales.model.Product;
import com.oept.esales.service.PurchaseService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/25
 * Description: Portfolio center actions.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Controller
@RequestMapping(value="/portfolio")
public class PortfolioAct {
	
	private static final Log logger = LogFactory.getLog(PortfolioAct.class);
	@Qualifier("purchaseService")
	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping(value="/list.do",method = RequestMethod.GET)
	public String portfolioList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			
			model.addAttribute("disctinct_categories", purchaseService.getProdDistinctCat());
			
			String userId = session.getAttribute("userid").toString();
			List<Product> availProds = purchaseService.getAvailProducts();
			for(int i=0;i<availProds.size();i++){
				if(purchaseService.checkFavItem(availProds.get(i).getId(), userId)){
					availProds.get(i).setCheck_collected(true);
				}else{
					availProds.get(i).setCheck_collected(false);
				}
			}
			
			model.addAttribute("available_products", availProds);
			return "products_portfolio";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/addToShopcart.do",method = RequestMethod.GET)
	@ResponseBody
	public String loadAvalilProducts(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String user_id = session.getAttribute("userid").toString();
			String product_id = request.getParameter("productId");
			
			if(purchaseService.addProdToShopcart(product_id, user_id)){
				return "success";
			}else{
				return "fail";
			}
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
}
