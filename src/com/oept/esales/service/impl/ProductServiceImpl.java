package com.oept.esales.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oept.esales.dao.ApprovalHistoryDao;
import com.oept.esales.dao.CategoryDao;
import com.oept.esales.dao.FileDao;
import com.oept.esales.dao.ListOfValueDao;
import com.oept.esales.dao.ProductDao;
import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.Category;
import com.oept.esales.model.Attachment;
import com.oept.esales.model.Product;
import com.oept.esales.model.ProductCategory;
import com.oept.esales.service.AuthService;
import com.oept.esales.service.ProductService;
/**
 * @author mwan
 * Version: 1.1
 * Date: 2015/11/18
 * Description: Products business service implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private CategoryDao categoryDao; //产品类别DAO
	@Autowired
	private ProductDao productDao; //产品信息DAO
	@Autowired
	private FileDao fileDao; //产品图片DAO
	@Autowired
	private ApprovalHistoryDao approvalHistoryDao; //approval history info DAO
	@Autowired
	private ListOfValueDao listOfValueDao; //list of values info DAO
	
	@Override
	public boolean addCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		category.setCreated(nowdate);
		category.setUpdate(nowdate);
		category.setActive(true);
		
		if("".equals(category.getParentCatId()) || category.getParentCatId()==null){
			category.setParentCatId("1");
		}
		
		Category parentCat = categoryDao.getCategoryById(category.getParentCatId());
		category.setHierlvl(parentCat.getHierlvl() + 1);
		
		return categoryDao.addCategory(category);
	}

	@Override
	public boolean delCategoryById(String id) throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.delCategoryById(id);
	}
	
	@Override
	@Transactional
	public boolean updateCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		category.setUpdate(nowdate);
		if("".equals(category.getParentCatId()) || category.getParentCatId()==null){
			category.setParentCatId("1");
		}
		Category parentCategory = categoryDao.getCategoryById(category.getParentCatId());
		category.setHierlvl(parentCategory.getHierlvl() + 1);
		boolean res = categoryDao.updateCategory(category);
		Category params1 = new Category();
		params1.setParentCatId(category.getId());
		boolean resSubclass = categoryDao.queryCategoriesSubclass(params1);
		if(resSubclass){
			Category params2 = new Category();
			params2.setParentCatId(category.getId());
			List<Category> resCategory = categoryDao.queryCategories(params2);
			//记录读取出了子值
			for(int i=0;i<resCategory.size();i++){
				Category rc = resCategory.get(i);
				Category params3 = new Category();
				params3.setId(rc.getId());
				params3.setParentCatId(rc.getParentCatId());
				params3.setActive(rc.getActive());
				params3.setDesc(rc.getDesc());
				params3.setName(rc.getName());
				params3.setUpdateById(rc.getUpdateById());
				res = updateCategory(params3);
			}
		}
		return res;
	}

	@Override
	public Category getCategoryById(String id) throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryById(id);
	}

	@Override
	public List<Category> getAllCategories() throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.getAllCategories();
	}

	@Override
	public boolean addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		product.setCreated(nowdate);
		product.setUpdate(nowdate);
		product.setStatus("Not Published");
		product.setStatus_val(listOfValueDao.getValueByCodeName("Product Status", "Not Published"));
		product.setDeleteFlg(false);
		
		return productDao.addProduct(product);
	}

	@Override
	public List<Product> getAllProducts() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getAllProducts();
	}
	
	@Override
	public List<Product> getProducts(Product product, String start, String limit, String sortColumn, String sortDir) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProducts(product, start, limit, sortColumn, sortDir);
	}
	
	@Override
	public List<Product> getAvailProducts() throws Exception {
		// TODO Auto-generated method stub
		Product availProduct = new Product();
		availProduct.setCheck_valid_date(true);
		availProduct.setStatus("Published");
		//availProduct.setImage_type("Thumbnail image");
		return productDao.loadAvailProducts(availProduct);
	}

	@Override
	public boolean delProductById(Product product) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		product.setUpdate(nowdate);
		product.setStatus("Deleted");
		product.setStatus_val(listOfValueDao.getValueByCodeName("Product Status", "Deleted"));
		product.setDeleteFlg(true);
		return productDao.delProductById(product);
	}

	@Override
	public boolean pubProductById(Product product) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		Timestamp nowdate = new Timestamp(date.getTime());
		product.setUpdate(nowdate);
		product.setValidstart(date);
		product.setDeleteFlg(false);
		return productDao.pubProductById(product);
	}

	@Override
	public Category getParentCatById(String id) throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.getParentCatById(id);
	}

	@Override
	public Product getProductById(String id) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProductById(id);
	}

	@Override
	public boolean updateProductById(Product product) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		product.setUpdate(nowdate);
		product.setStatus_val(listOfValueDao.getValueByCodeName("Product Status", product.getStatus()));
		if(product.getStatus().equals("Deleted")){
			product.setDeleteFlg(true);
		}else{
			product.setDeleteFlg(false);
		}
		return productDao.updateProduct(product);
	}

	@Override
	public boolean addProductFile(Attachment file) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		file.setUploaded_date(nowdate);
		file.setUpload_source("Product Image Uploader");
		file.setType("Product Image");
		file.setImage_type("Base Image");
		return fileDao.addFile(file);
	}

	@Override
	public List<Attachment> getImagesByProdId(String id) throws Exception {
		// TODO Auto-generated method stub
		return fileDao.getFileByProductId(id);
	}
	
	@Override
	public boolean delImageById(String id) throws Exception {
		// TODO Auto-generated method stub
		return fileDao.delFileById(id);
	}

	@Override
	public boolean updateProductFile(Attachment file) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		file.setUpdate_date(nowdate);
		return fileDao.updateFile(file);
	}
	
	@Override
	public int getProductsCount() throws Exception {
		return productDao.getProductsCount();
	}


	/**
	 * 读取产品目录最大层级数
	 */
	@Override
	public int getProdCatMaxLvl() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProdCatMaxLvl();
	}

	/**
	 * 根据lvl查询产品类型
	 */
	@Override
	public List<ProductCategory> getProdCatLvlId(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProdCatLvlId(params);
	}

	@Override
	public List<Product> getProductsForApprover(Product product,
			String approver_id, String start, String limit, String sortColumn,
			String sortDir) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProductsForApprover(product, approver_id, start, limit, sortColumn, sortDir);
	}

	@Override
	public boolean addApprovalHistory(ApprovalHistory approval_history)
			throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		approval_history.setCreated_date(nowdate);
		return approvalHistoryDao.addHistory(approval_history);
	}

	@Override
	public List<ApprovalHistory> getApprovalHistories(
			ApprovalHistory approval_history, String start, String limit,
			String sortColumn, String sortDir) throws Exception {
		// TODO Auto-generated method stub
		return approvalHistoryDao.getHistories(approval_history, start, limit, sortColumn, sortDir);
	}

	@Override
	public String updateProdStatusById(Product product) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		product.setUpdate(nowdate);
		if(productDao.updateProduct(product)){
			return "success";
		}else{
			return "更新出现异常";
		}
	}

	@Qualifier("authService")
	@Autowired
	private AuthService authService;
	@Override
	@Transactional
	public String updateProdStatusByIdFacade(Product product) throws Exception {
		// TODO Auto-generated method stub
		try{
			String status_code = product.getStatus();
			String success_msg = "";
			if(status_code.equals("submitted")){
				success_msg = "提交产品/服务";
			}else if(status_code.equals("Published")){
				success_msg = "提交审核产品/服务";
			}else if(status_code.equals("Rejected")){
				success_msg = "提交拒绝产品/服务";
			}else if(status_code.equals("Reopen")){
				success_msg = "重开产品/服务";
			}
			
			String ActionType = "";
			ApprovalHistory approvalHistory = new ApprovalHistory();
			switch(status_code){
			case "submitted":
				ActionType = "Submit";
				approvalHistory.setProduct_id(product.getId());
				approvalHistory.setCreated_by_user_id(product.getUpdateById());
				approvalHistory.setDescription("由"+product.getUpdateBy()+"提交了产品/服务信息！");
				this.addApprovalHistory(approvalHistory);
				break;
			case "Published":
				ActionType = "Approve";
				approvalHistory.setProduct_id(product.getId());
				approvalHistory.setCreated_by_user_id(product.getUpdateById());
				approvalHistory.setDescription("由"+product.getUpdateBy()+"审核通过了产品/服务信息！");
				this.addApprovalHistory(approvalHistory);
				break;
			case "Rejected":
				ActionType = "Reject";
				approvalHistory.setProduct_id(product.getId());
				approvalHistory.setCreated_by_user_id(product.getUpdateById());
				approvalHistory.setDescription("由"+product.getUpdateBy()+"拒绝了产品/服务信息！");
				this.addApprovalHistory(approvalHistory);
				break;
			case "Reopen":
				ActionType = "Reopen";
				status_code = "Not Published";
				approvalHistory.setProduct_id(product.getId());
				approvalHistory.setCreated_by_user_id(product.getUpdateById());
				approvalHistory.setDescription("由"+product.getUpdateBy()+"重开了产品/服务信息！");
				this.addApprovalHistory(approvalHistory);
				break;
			default:
				ActionType = "Not Defined";
				break;
			}
			
			if(!ActionType.equals("Reopen")){
				String objectName = "Product";
				switch(authService.ApprovalExecute(product.getId(), objectName, ActionType, product.getUpdateById())){
				case "Approval Not Required":
					break;
				case "Pending":
					status_code = "Pending";
					break;
				case "Approved":
					status_code = "Published";
					break;
				case "Rejected":
					status_code = "Rejected";
					break;
				}
			}
			
			product.setStatus(status_code);
			product.setStatus_val(listOfValueDao.getValueByCodeName("Product Status", status_code));
			String update_status = this.updateProdStatusById(product);
			if(update_status.equals("success")){
				return "{'objname':'"+success_msg+"'}";
			}else{
				//return "{'errmsg':'"+update_status+"'}";
				throw new RuntimeException(update_status);
			}
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public boolean queryAddRepetition(Product product) throws Exception {
		// TODO Auto-generated method stub
		return productDao.queryAddRepetition(product);
	}
}
