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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oept.esales.model.Category;
import com.oept.esales.service.ProductService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/13
 * Description: Categories administration actions.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Controller
@RequestMapping(value="/category")
public class CategoryAct {
	
	private static final Log logger = LogFactory.getLog(CategoryAct.class);
	@Qualifier("productService")
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/list.do",method = RequestMethod.GET)
	public String categoryList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Category> categoryList = productService.getAllCategories();
			model.addAttribute("categoryList", categoryList);
			return "product_category_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/details.do",method = RequestMethod.GET)
	public String categoryDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String categoryId = request.getParameter("categoryId");
			
			Category category = productService.getCategoryById(categoryId);
			List<Category> categoryList = productService.getAllCategories();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("categoryDetails", category);
			return "product_category_details";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createCategory(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String name = request.getParameter("name");
			String parentCatId = request.getParameter("parentCatId");
			String description = request.getParameter("description");
			Category newCategory = new Category();
			newCategory.setName(name);
			newCategory.setDesc(description);
			newCategory.setParentCatId(parentCatId);
			newCategory.setCreatedById(session.getAttribute("userid").toString());
			newCategory.setUpdateById(session.getAttribute("userid").toString());
			
			if(productService.addCategory(newCategory)){
				return "{'id':'success'}";
			}		
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadcat.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String loadCategory(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Category> categoryList = productService.getAllCategories();
			
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(categoryList);	
			
			return responseStr;
			
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
					productService.delCategoryById(id[i]);
				}			
				return "{'objname':'产品/服务类别'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateCategoryById(HttpServletRequest request, HttpSession session) throws Exception{
		
		String cat_id = request.getParameter("cat_id");
		String par_id = request.getParameter("par_cat_id");
		String desc = request.getParameter("description");
		String status = request.getParameter("status");
		String catname = request.getParameter("catname");
		String update_by = session.getAttribute("userid").toString();
		
		try{			
			Category category = new Category();
			category.setId(cat_id);
			category.setParentCatId(par_id);
			category.setActive(Boolean.parseBoolean(status));
			category.setDesc(desc);
			category.setName(catname);
			category.setUpdateById(update_by);
			productService.updateCategory(category);
			return "{'objname':'产品/服务类别'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
}
