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

import com.oept.esales.model.Favorite;
import com.oept.esales.service.PurchaseService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/8
 * Description: Favorites administration actions.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Controller
@RequestMapping(value="/favorite")
public class FavoriteAct {
	
	private static final Log logger = LogFactory.getLog(FavoriteAct.class);
	@Qualifier("purchaseService")
	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping(value="/list.do",method = RequestMethod.GET)
	public String categoryList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Favorite> favoriteList = purchaseService.getAvailFavItemsByUserId(session.getAttribute("userid").toString());
			model.addAttribute("favoriteList", favoriteList);
			return "favorite_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/addItem.do",method = RequestMethod.GET)
	@ResponseBody
	public String addFavItem(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String prodId = request.getParameter("prodId");
			String userId = session.getAttribute("userid").toString();
			Favorite item = new Favorite();
			item.setItem_prod_id(prodId);
			item.setItem_user_id(userId);
			item.setItem_created_by_user_id(userId);
			item.setItem_update_by_user_id(userId);
			
			if(purchaseService.addFavItem(item)){
				return "success";
			}		
			return "failed";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delCategoryById(HttpServletRequest request, HttpSession session) throws Exception{
		
		String ids = request.getParameter("ids");
		
		try {				
				String id[] = ids.split(",");
				for(int i=0;i<id.length;i++){
					purchaseService.delFavItemById(id[i]);
				}			
				return "{'objname':'收藏项'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
}
