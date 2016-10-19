package com.oept.esales.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oept.esales.model.ApprovalDetail;
import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.ApprovalStep;
import com.oept.esales.model.Attachment;
import com.oept.esales.model.Category;
import com.oept.esales.model.Product;
import com.oept.esales.model.ProductCategory;
import com.oept.esales.service.AuthService;
import com.oept.esales.service.ProductService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/11
 * Description: Products administration actions.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Controller
@RequestMapping(value="/prodadmin")
public class ProdadminAct {
	
	private static final Log logger = LogFactory.getLog(ProdadminAct.class);
	@Qualifier("productService")
	@Autowired
	private ProductService productService;
	@Qualifier("authService")
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/list.do",method = RequestMethod.GET)
	public String productList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Category> categoryList = productService.getAllCategories();
			model.addAttribute("categoryList", categoryList);
			return "products_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/approval_list.do",method = RequestMethod.GET)
	public String productApprovalList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Category> categoryList = productService.getAllCategories();
			model.addAttribute("categoryList", categoryList);
			return "products_list_approval";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/listdata.do",method = RequestMethod.POST)
	@ResponseBody
	public String productListData(HttpServletRequest request, HttpSession session) throws Exception{

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
			Product productSearch = new Product();
			
			if(customActionType!=null && customActionType.equals("group_action")){
				if(customActionName.equals("delete")){
					Product product = new Product();
					for(int i=0;i<ids.length;i++){
						product.setId(ids[i]);
						product.setUpdateById(session.getAttribute("userid").toString());
						productService.delProductById(product);
				    }
					response.put("customActionStatus", "OK");
					response.put("customActionMessage", "产品记录已修改为删除状态！");
				}else if(customActionName.equals("publish")){
					Product product = new Product();
					for(int i=0;i<ids.length;i++){
						product.setId(ids[i]);
						product.setStatus("已发布");
						product.setUpdateById(session.getAttribute("userid").toString());
						productService.pubProductById(product);
				    }
					response.put("customActionStatus", "OK");
					response.put("customActionMessage", "产品记录已成功发布！");
				}else if(customActionName.equals("unpublished")){
					Product product = new Product();
					for(int i=0;i<ids.length;i++){
						product.setId(ids[i]);
						product.setStatus("未发布");
						product.setUpdateById(session.getAttribute("userid").toString());
						productService.pubProductById(product);
				    }
					response.put("customActionStatus", "OK");
					response.put("customActionMessage", "产品记录已取消发布！");
				}
			}
			
			int iTotalRecords = productService.getProductsCount();
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<Product> productList = new ArrayList<Product>();
			
			if(action!=null && action.equals("filter")){
				productSearch.setNumber(request.getParameter("product_number"));
				productSearch.setName(request.getParameter("product_name"));
				productSearch.setModel(request.getParameter("product_model"));
				productSearch.setCategoryId(request.getParameter("product_category"));
//				productSearch.setProduct_price_from(request.getParameter("product_price_from"));
//				productSearch.setProduct_price_to(request.getParameter("product_price_to"));
				productSearch.setProduct_quantity_from(request.getParameter("product_quantity_from"));
				productSearch.setProduct_quantity_to(request.getParameter("product_quantity_to"));
				productSearch.setProduct_created_from(request.getParameter("product_created_from"));
				productSearch.setProduct_created_to(request.getParameter("product_created_to"));
				productSearch.setStatus(request.getParameter("product_status"));
				
				productList = productService.getProducts(productSearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", productService.getProducts(productSearch, null, 
						null, null, null).size());
			}else{
				productList = productService.getProducts(productSearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < productList.size(); i++)
	        {  
				Product product = productList.get(i); 
				List<String> dataRow = new ArrayList<String>();
				dataRow.add("<input type='checkbox' name='id[]' value="+product.getId()+">");
				dataRow.add(product.getNumber());
				dataRow.add(product.getName());
				dataRow.add(product.getModel());
				dataRow.add(product.getCategoryName());
				//dataRow.add(String.valueOf(product.getPrice()));
				dataRow.add("<a href=\"../inventory/warehouse_stock_list.do?productId="+product.getId()+"\">"+String.valueOf(product.getStock())+"</a>");
				dataRow.add(product.getCreated());
				if(product.isDeleteFlg()){
					dataRow.add("<span class=\"label label-sm label-danger\">已删除</span>");
				}else if(product.getStatus().equals("Not Published")){
					dataRow.add("<span class=\"label label-sm label-info\">未发布</span>");
				}else if(product.getStatus().equals("Pending")){
					dataRow.add("<span class=\"label label-sm label-info\">待审核</span>");
				}else if(product.getStatus().equals("Rejected")){
					dataRow.add("<span class=\"label label-sm label-danger\">已拒绝</span>");
				}else{
					dataRow.add("<span class=\"label label-sm label-success\">已发布</span>");
				}
				dataRow.add("<a href=\"details.do?id="+product.getId()+"\" class=\"btn btn-xs default btn-editable\"><i class=\"fa fa-pencil\"></i> 编辑</a>");
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
	@RequestMapping(value="/listdata_approval.do",method = RequestMethod.POST)
	@ResponseBody
	public String productApprovalListData(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Object> data = new ArrayList<Object>();
			Map<String, Object> response = new HashMap<String, Object>();
			int length = Integer.parseInt(request.getParameter("length"));
			int start = Integer.parseInt(request.getParameter("start"));
			int sEcho = Integer.parseInt(request.getParameter("draw"));
			int sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			String sortDir = request.getParameter("order[0][dir]");
			String approver_id = session.getAttribute("userid").toString();
//			String[] ids = request.getParameterValues("id[]");
//			String customActionType = request.getParameter("customActionType");
//			String customActionName = request.getParameter("customActionName");
			String action = request.getParameter("action");
			Product productSearch = new Product();
			
			int iTotalRecords = productService.getProductsCount();
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<Product> productList = new ArrayList<Product>();
			
			if(action!=null && action.equals("filter")){
				productSearch.setNumber(request.getParameter("product_number"));
				productSearch.setName(request.getParameter("product_name"));
				productSearch.setCategoryId(request.getParameter("product_category"));
//				productSearch.setProduct_price_from(request.getParameter("product_price_from"));
//				productSearch.setProduct_price_to(request.getParameter("product_price_to"));
				productSearch.setProduct_quantity_from(request.getParameter("product_quantity_from"));
				productSearch.setProduct_quantity_to(request.getParameter("product_quantity_to"));
				productSearch.setProduct_created_from(request.getParameter("product_created_from"));
				productSearch.setProduct_created_to(request.getParameter("product_created_to"));
				productSearch.setStatus(request.getParameter("product_status"));
				
				productList = productService.getProductsForApprover(productSearch, approver_id,String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", productService.getProductsForApprover(productSearch, approver_id,null, 
						null, null, null).size());
			}else{
				productList = productService.getProductsForApprover(productSearch, approver_id,String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < productList.size(); i++)
	        {  
				Product product = productList.get(i); 
				List<String> dataRow = new ArrayList<String>();
				dataRow.add("<input type='checkbox' name='id[]' value="+product.getId()+">");
				dataRow.add(product.getNumber());
				dataRow.add(product.getName());
				dataRow.add(product.getCategoryName());
				//dataRow.add(String.valueOf(product.getPrice()));
				dataRow.add("<a href=\"../inventory/warehouse_stock_list.do?productId="+product.getId()+"\">"+String.valueOf(product.getStock())+"</a>");
				dataRow.add(product.getCreated());
				if(product.isDeleteFlg()){
					dataRow.add("<span class=\"label label-sm label-danger\">已删除</span>");
				}else if(product.getStatus().equals("Not Published")){
					dataRow.add("<span class=\"label label-sm label-info\">未发布</span>");
				}else if(product.getStatus().equals("Pending")){
					dataRow.add("<span class=\"label label-sm label-info\">待审核</span>");
				}else if(product.getStatus().equals("Rejected")){
					dataRow.add("<span class=\"label label-sm label-danger\">已拒绝</span>");
				}else{
					dataRow.add("<span class=\"label label-sm label-success\">已发布</span>");
				}
				dataRow.add("<a href=\"approval_details.do?id="+product.getId()+"\" class=\"btn btn-xs default btn-editable\"><i class=\"fa fa-pencil\"></i> 进行审核</a>");
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
			String productId = request.getParameter("id");
			ApprovalHistory approvalHistorySearch = new ApprovalHistory();
			
			approvalHistorySearch.setProduct_id(productId);
			int iTotalRecords = productService.getApprovalHistories(approvalHistorySearch, null, null, null, null).size();
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
				
				approvalHistoryList = productService.getApprovalHistories(approvalHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", productService.getApprovalHistories(approvalHistorySearch, null, 
						null, null, null).size());
			}else{
				approvalHistoryList = productService.getApprovalHistories(approvalHistorySearch, String.valueOf(start), 
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
	
	@RequestMapping(value="/details.do",method = RequestMethod.GET)
	public String productDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String productId = request.getParameter("id");
			
			Product product = productService.getProductById(productId);
			
			model.addAttribute("product", product);
			
			boolean readOnlyFlag = true;
			switch(product.getStatus()){
			case "Not Published":
				readOnlyFlag = false;
				break;
			case "Rejected":
				readOnlyFlag = false;
				break;
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			
			return "products_edit";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/approval_details.do",method = RequestMethod.GET)
	public String productApprovalDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String productId = request.getParameter("id");
			
			Product product = productService.getProductById(productId);
			model.addAttribute("product", product);
			
			boolean readOnlyFlag = true;
			switch(product.getStatus()){
			case "Not Published":
				readOnlyFlag = false;
				break;
			case "Pending":
				ApprovalDetail userApprovalDetail = authService.queryApprovalDetailStatus(productId, "Product", session.getAttribute("userid").toString());
				if(userApprovalDetail.getStatus_cd().equals("Pending") && userApprovalDetail.isProcess_flg()){
					readOnlyFlag = false;
				}else{
					readOnlyFlag = true;
				}
				break;
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			
			return "products_edit_approval";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/queryApprovalStatus.do",method = RequestMethod.POST)
	@ResponseBody
	public List<ApprovalStep> queryApprovalStatus(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String productId = request.getParameter("productId");
			String type = request.getParameter("type");
			return authService.queryApproval(productId, type);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createProduct(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String name = request.getParameter("name");
			String number = request.getParameter("number");
			String description = request.getParameter("description");
			String categoryId = request.getParameter("categoryId");
			String name_model = request.getParameter("model");
			String sku = request.getParameter("sku");
			Product newProduct = new Product();
			newProduct.setName(name);
			newProduct.setNumber(number);
			newProduct.setDesc(description);
			newProduct.setCategoryId(categoryId);
			newProduct.setCreateById(session.getAttribute("userid").toString());
			newProduct.setUpdateById(session.getAttribute("userid").toString());
			newProduct.setModel(name_model);
			newProduct.setSku(sku);
			
			if(productService.addProduct(newProduct)){
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
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateProdById(HttpServletRequest request, HttpSession session) throws Exception{
		
		String prod_id = request.getParameter("product[id]");
		String prod_name = request.getParameter("product[name]");
		String prod_num = request.getParameter("product[number]");
		String prod_desc = request.getParameter("product[description]");
		String prod_spec = request.getParameter("product[spec]");
		String cat_id = request.getParameter("product[category]");
		String avail_from_date = request.getParameter("product[available_from]");
		String avail_to_date = request.getParameter("product[available_to]");
		String prod_sku = request.getParameter("product[sku]");
		String prod_stock = request.getParameter("product[stock]");
		//String prod_price = request.getParameter("product[price]");
		String prod_status = request.getParameter("product[status]");
		String prod_model = request.getParameter("product[model]");
		String update_by = session.getAttribute("userid").toString();
		
		String image_filename[] = request.getParameterValues("product[images][name][]");
		String image_id[] = request.getParameterValues("product[images][id][]");
		String image_type = "";
		
		try{			
			Product product = new Product();
			product.setId(prod_id);
			product.setNumber(prod_num);
			product.setName(prod_name);
			product.setCategoryId(cat_id);
			product.setDesc(prod_desc);
			product.setSpec(prod_spec);
			product.setModel(prod_model);
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			if((!"".equals(avail_from_date)) && avail_from_date!=null){
				product.setValidstart(dateformat.parse(avail_from_date));
			}else{
				product.setValidstart(null);
			}
			if((!"".equals(avail_from_date)) && avail_to_date!=null){
				product.setValidend(dateformat.parse(avail_to_date));
			}else{
				product.setValidend(null);
			}
			product.setSku(prod_sku);
			product.setStock(Integer.parseInt(prod_stock));
			//product.setPrice(Double.parseDouble(prod_price));
			product.setStatus(prod_status);
			product.setUpdateById(update_by);
			productService.updateProductById(product);
			
			if(image_id!=null){
				for(int i=0;i<image_id.length;i++){
					Attachment image = new Attachment();
					image.setUpdate_by_user_id(update_by);
					image.setId(image_id[i]);
					image.setFilename(image_filename[i]);
					image_type=request.getParameter("product[images][image_type]["+image_id[i]+"]");
					image.setImage_type(image_type);
					productService.updateProductFile(image);
				}
			}
			
			return "{'objname':'产品'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	//file upload action
	@RequestMapping(value="/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpSession session) throws Exception{
		String errMsg = "";
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String productId = request.getParameter("productId").toString();

		String fileName = file.getOriginalFilename();
		String fileUniqueName = request.getParameter("name").toString();
		String defaultContextPath = "/img/products";
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
				
				Attachment imageFile = new Attachment();
				imageFile.setFilename(fileName);
				imageFile.setUnique_filename(fileUniqueName);
				imageFile.setOriginal_filename(fileName);
				imageFile.setPath(defaultRealPath);
				imageFile.setContextpath(defaultContextPath);
				imageFile.setFilesize(file.getSize());
				imageFile.setUploaded_by_user_id(session.getAttribute("userid").toString());
				imageFile.setImage_label(file.getContentType());
				imageFile.setProduct_id(productId);
				productService.addProductFile(imageFile);
				
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
	
	@RequestMapping(value="/loadimg.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String loadProdImages(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String productId = request.getParameter("productId").toString();
			List<Attachment> imageList = productService.getImagesByProdId(productId);
			
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(imageList);	
			
			return responseStr;
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/delImage.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delImageById(HttpServletRequest request, HttpSession session) throws Exception{

		try {
			String id = request.getParameter("image_id");
			String fileName = request.getParameter("image_filename");
			String contextPath = request.getParameter("remove_image_filepath");
			productService.delImageById(id);
			String realPath = request.getSession().getServletContext().getRealPath(contextPath);
			File image = new File(realPath+"\\"+fileName);
			image.delete();
			return "{'objname':'产品图片'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	
	@RequestMapping(value="/listTree.do")
	public String listTree(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try {
			//查询产品类型最大层级
			int maxLvl = productService.getProdCatMaxLvl();
			//查询产品类型
			for(int i=0;i<(maxLvl+1);i++){
				Object[] params = new Object[]{i};
				//查询层级为i的产品类型数据
				List<ProductCategory> pcl = productService.getProdCatLvlId(params);
				String aName = "pList"+i;
				model.addAttribute(aName,pcl);
			}
			
			return "products_list_tree";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/deleteCategory.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public int delCategoryById(HttpServletRequest request, HttpSession session) throws Exception{
		
		String id = request.getParameter("id");
		
		try {				
			boolean is = false;
			is = productService.delCategoryById(id);
			if(is == true)
				return 1;
			else
				return 0;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updateProdStatus.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateOrderStatus(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			Product product = new Product();
			product.setId(request.getParameter("productId"));
			product.setUpdateById(session.getAttribute("userid").toString());
			product.setUpdateBy(session.getAttribute("username").toString());
			product.setStatus(request.getParameter("status_code"));
			
			return productService.updateProdStatusByIdFacade(product);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			return "{'errmsg':'"+e.getMessage()+"'}";
		}
	}
	
	@RequestMapping(value="/queryAddRepetition.do")
	@ResponseBody
	public int queryAddRepetition(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			Product product = new Product();
			product.setNumber(request.getParameter("num"));
			product.setModel(request.getParameter("model"));
			boolean res = productService.queryAddRepetition(product);
			if(res)
				return 1;
			else
				return 0;
			
		}catch(Exception e){
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/new_category.do",method = RequestMethod.POST)
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
//	@RequestMapping(value="/loadProdCat.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
//	@ResponseBody
//	public List<List<ProductCategory>> loadProdCat(HttpServletRequest request, HttpSession session) throws Exception{
//
//		try {
//			//读取产品类型数据
//			System.out.println("进入读取产品类型数据");
//			return productService.getProdCatTree();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.info(e.getMessage());
//			throw e;
//		}
//	}
}
