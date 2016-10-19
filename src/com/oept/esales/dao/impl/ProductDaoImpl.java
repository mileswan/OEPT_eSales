package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.ProductDao;
import com.oept.esales.model.Product;
import com.oept.esales.model.ProductCategory;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/13
 * Description: Products DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

	private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
	
	private final static String _INSERT_STRING1="insert into osa_product (osa_prod_num,osa_prod_name,osa_prod_desc,osa_prod_category_id,"
			+ "osa_prod_created,osa_prod_created_by,osa_prod_update,osa_prod_update_by,osa_prod_status,osa_prod_status_val,osa_prod_model,osa_prod_delete_flg,osa_prod_sku) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String _SELECT_COUNT="select count(*) as count from osa_product";
	private final static String _SELECT_STRING1="select a.*,b.osa_prod_cat_name categoryName, "
			+ "u1.osa_username createdBy, u2.osa_username updateBy from osa_product a inner join "
			+ "osa_prod_cat b on a.osa_prod_category_id=b.osa_prod_cat_id inner join "
			+ "osa_user u1 on a.osa_prod_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_prod_update_by=u2.osa_user_id";
	private final static String _SELECT_STRING_FOR_LIST="select a.osa_prod_id,a.osa_prod_num,a.osa_prod_name,a.osa_prod_model,b.osa_prod_cat_name categoryName,"
			+ "a.osa_prod_stock,a.osa_prod_update,a.osa_prod_status,"
			+ "a.osa_prod_delete_flg,a.osa_prod_sku,u1.osa_username createdBy, u2.osa_username updateBy from osa_product a inner join "
			+ "osa_prod_cat b on a.osa_prod_category_id=b.osa_prod_cat_id inner join "
			+ "osa_user u1 on a.osa_prod_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_prod_update_by=u2.osa_user_id";
	private final static String _SELECT_STRING2="select a.*,b.osa_prod_cat_name categoryName, "
			+ "u1.osa_username createdBy, u2.osa_username updateBy from osa_product a inner join "
			+ "osa_prod_cat b on a.osa_prod_category_id=b.osa_prod_cat_id inner join "
			+ "osa_user u1 on a.osa_prod_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_prod_update_by=u2.osa_user_id "
			+ "WHERE osa_prod_id = ?";
	private final static String _SELECT_STRING_FOR_DISTINCT_CAT="select DISTINCT c.osa_prod_cat_id,c.osa_prod_cat_name from osa_product a INNER JOIN osa_prod_cat c "
			+ "on a.osa_prod_category_id=c.osa_prod_cat_id left outer join osa_attr_file f "
			+ "on f.osa_product_id=a.osa_prod_id";
	private final static String _SELECT_STRING_FOR_PORTFOLIO="select a.*,c.osa_prod_cat_name"
			+ " from osa_product a INNER JOIN osa_prod_cat c "
			+ "on a.osa_prod_category_id=c.osa_prod_cat_id";
			
//	private final static String _DELETE_STRING1="UPDATE osa_product SET osa_prod_delete_flg = ?, osa_prod_status=?, "
//			+ "osa_prod_update=?,osa_prod_update_by=? "
//			+ "WHERE osa_prod_id = ?";
	private final static String _UPDATE_STRING1="UPDATE osa_product SET osa_prod_delete_flg = ?, osa_prod_status=?, "
			+ "osa_prod_update=?,osa_prod_update_by=?,osa_prod_valid_start=? "
			+ "WHERE osa_prod_id = ?";
//	private final static String _UPDATE_STRING2="UPDATE osa_product SET osa_prod_num=?,osa_prod_name=?,osa_prod_desc=?,osa_prod_spec=?,"
//			+ "osa_prod_category_id=?,osa_prod_price=?,osa_prod_sku=?,osa_prod_stock=?,"
//			+ "osa_prod_delete_flg = ?, osa_prod_status=?,"
//			+ "osa_prod_update=?,osa_prod_update_by=?,osa_prod_valid_start=?,osa_prod_valid_end=? "
//			+ "WHERE osa_prod_id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					product.getNumber(),
					product.getName(),
					product.getDesc(),
					product.getCategoryId(),
					product.getCreated(),
					product.getCreateById(),
					product.getUpdate(),
					product.getUpdateById(),
					product.getStatus(),
					product.getStatus_val(),
					product.getModel(),
					product.isDeleteFlg(),
					product.getSku()
					};
			jdbcTemplate.update(_INSERT_STRING1,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delProductById(Product product) throws Exception {
		// TODO Auto-generated method stub
//		try{
//			Object[] params = new Object[] {
//					product.isDeleteFlg(),
//					product.getStatus(),
//					product.getUpdate(),
//					product.getUpdateById(),
//					product.getId() };
//			jdbcTemplate.update(_DELETE_STRING1,params);
//			return true;
//		}catch(Exception e){
//			logger.info(e.getMessage());
//			throw e;
//		}
		return false;
	}

	@Override
	public boolean updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("UPDATE osa_product SET ");
			
			List<Object> parameters = new ArrayList<Object>();
			//Filter combination
			if( !"".equals(product.getNumber()) && product.getNumber()!=null ){
				queryString.append("osa_prod_num=?,");
				parameters.add(product.getNumber());
			}
			if( !"".equals(product.getName()) && product.getName()!=null ){
				queryString.append("osa_prod_name=?,");
				parameters.add(product.getName());
			}
			if( !"".equals(product.getDesc()) && product.getDesc()!=null ){
				queryString.append("osa_prod_desc=?,");
				parameters.add(product.getDesc());
			}
			if( !"".equals(product.getSpec()) && product.getSpec()!=null ){
				queryString.append("osa_prod_spec=?,");
				parameters.add(product.getSpec());
			}
			if( !"".equals(product.getCategoryId()) && product.getCategoryId()!=null ){
				queryString.append("osa_prod_category_id=?,");
				parameters.add(product.getCategoryId());
			}
			if( product.getPrice()!=-1 ){
				queryString.append("osa_prod_price=?,");
				parameters.add(product.getPrice());
			}
			if( !"".equals(product.getSku()) && product.getSku()!=null ){
				queryString.append("osa_prod_sku=?,");
				parameters.add(product.getSku());
			}
			if( product.getStock()!=-1 ){
				queryString.append("osa_prod_stock=?,");
				parameters.add(product.getStock());
			}
			if( !"".equals(product.isDeleteFlg()) ){
				queryString.append("osa_prod_delete_flg=?,");
				parameters.add(product.isDeleteFlg());
			}
			if( !"".equals(product.getStatus()) && product.getStatus()!=null ){
				queryString.append("osa_prod_status=?,");
				parameters.add(product.getStatus());
			}
			if( !"".equals(product.getStatus_val()) && product.getStatus_val()!=null ){
				queryString.append("osa_prod_status_val=?,");
				parameters.add(product.getStatus_val());
			}
			if( !"".equals(product.getModel()) && product.getModel()!=null ){
				queryString.append("osa_prod_model=?,");
				parameters.add(product.getModel());
			}
			if( !"".equals(product.getValidstart()) && product.getValidstart()!=null ){
				queryString.append("osa_prod_valid_start=?,");
				parameters.add(product.getValidstart());
			}
			if( !"".equals(product.getValidend()) && product.getValidend()!=null ){
				queryString.append("osa_prod_valid_end=?,");
				parameters.add(product.getValidend());
			}
			if( product.getOrdered_stock_in()!=-1 ){
				queryString.append("osa_prod_ordered_stock_in=?,");
				parameters.add(product.getOrdered_stock_in());
			}
			if( product.getOrdered_stock_out()!=-1 ){
				queryString.append("osa_prod_ordered_stock_out=?,");
				parameters.add(product.getOrdered_stock_out());
			}
			queryString.append("osa_prod_update=?,osa_prod_update_by=? WHERE osa_prod_id = ?");
			parameters.add(product.getUpdate());
			parameters.add(product.getUpdateById());
			parameters.add(product.getId());
			
			Object[] params = (Object[])parameters.toArray(new Object[parameters.size()]);
			jdbcTemplate.update(queryString.toString(),params);
			return true;
			}catch(Exception e){
				logger.info(e.getMessage());
				throw e;
			}
	}

	@Override
	public List<Product> getAllProducts() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_STRING1, 
                new RowMapper<Product>(){         
                    @Override  
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	Product product  = new Product();  
                    	product.setId(rs.getString("osa_prod_id"));  
                    	product.setNumber(rs.getString("osa_prod_num"));
                    	product.setName(rs.getString("osa_prod_name"));
                    	product.setSpec(rs.getString("osa_prod_spec"));
                    	product.setDesc(rs.getString("osa_prod_desc"));
                    	product.setCategoryName(rs.getString("categoryName"));
                    	product.setCreatedBy(rs.getString("createdBy"));
                    	product.setUpdateBy(rs.getString("updateBy"));
                    	product.setPrice(rs.getDouble("osa_prod_price"));
                    	product.setCreated(rs.getTimestamp("osa_prod_created"));
                    	product.setUpdate(rs.getTimestamp("osa_prod_update"));
                    	product.setStatus(rs.getString("osa_prod_status"));
                    	product.setSku(rs.getString("osa_prod_sku"));
                    	product.setStock(rs.getInt("osa_prod_stock"));
                    	product.setDeleteFlg(rs.getBoolean("osa_prod_delete_flg"));
                    	product.setValidstart(rs.getDate("osa_prod_valid_start"));
                    	product.setValidend(rs.getDate("osa_prod_valid_end"));
                        return product;  
                    }  
        });
	}
	
	@Override
	public int getProductsCount() throws Exception {
		Product prodcount = jdbcTemplate.queryForObject(_SELECT_COUNT, 
				new RowMapper<Product>(){         
        		@Override  
        		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {  
        			Product product  = new Product(); 
        			product.setStock(rs.getInt("count"));
        			return product;
        		}
		});
		return prodcount.getStock();
	}

	@Override
	public Product getProductById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject(_SELECT_STRING2, params,
                new RowMapper<Product>(){         
                    @Override  
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	Product product  = new Product();  
                    	product.setId(rs.getString("osa_prod_id"));  
                    	product.setNumber(rs.getString("osa_prod_num"));
                    	product.setName(rs.getString("osa_prod_name"));
                    	product.setSpec(rs.getString("osa_prod_spec"));
                    	product.setDesc(rs.getString("osa_prod_desc"));
                    	product.setCategoryName(rs.getString("categoryName"));
                    	product.setCategoryId(rs.getString("osa_prod_category_id"));
                    	product.setCreatedBy(rs.getString("createdBy"));
                    	product.setUpdateBy(rs.getString("updateBy"));
                    	product.setPrice(rs.getDouble("osa_prod_price"));
                    	product.setCreated(rs.getTimestamp("osa_prod_created"));
                    	product.setUpdate(rs.getTimestamp("osa_prod_update"));
                    	product.setStatus(rs.getString("osa_prod_status"));
                    	product.setStatus_val(rs.getString("osa_prod_status_val"));
                    	product.setModel(rs.getString("osa_prod_model"));
                    	product.setSku(rs.getString("osa_prod_sku"));
                    	product.setStock(rs.getInt("osa_prod_stock"));
                    	product.setDeleteFlg(rs.getBoolean("osa_prod_delete_flg"));
                    	product.setValidstart(rs.getDate("osa_prod_valid_start"));
                    	product.setValidend(rs.getDate("osa_prod_valid_end"));
                    	product.setOrdered_stock_in(rs.getInt("osa_prod_ordered_stock_in"));
                    	product.setOrdered_stock_out(rs.getInt("osa_prod_ordered_stock_out"));
                        return product;  
                    }  
        });
	}

	@Override
	public List<Product> getProducts(Product product, String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_FOR_LIST);
		queryString.append(" where 1=1 ");
		
		//Filter combination
		if( !"".equals(product.getNumber())&&product.getNumber()!=null  ){
			queryString.append("and osa_prod_num='"+product.getNumber()+"' ");
		}
		if( !"".equals(product.getName())&&product.getName()!=null ){
			queryString.append("and osa_prod_name='"+product.getName()+"' ");
		}
		if( !"".equals(product.getModel())&&product.getModel()!=null ){
			queryString.append("and osa_prod_model='"+product.getModel()+"' ");
		}
		if( !"".equals(product.getCategoryId())&&product.getCategoryId()!=null ){
			queryString.append("and osa_prod_category_id="+product.getCategoryId()+" ");
		}
		if( !"".equals(product.getStatus())&&product.getStatus()!=null ){
			queryString.append("and osa_prod_status='"+product.getStatus()+"' ");
		}
		if( !"".equals(product.getProduct_price_from())&&product.getProduct_price_from()!=null ){
			if( !"".equals(product.getProduct_price_to())&&product.getProduct_price_to()!=null ){
				queryString.append("and osa_prod_price>="+product.getProduct_price_from()+" and osa_prod_price<="+product.getProduct_price_to()+" ");
			}else{
				queryString.append("and osa_prod_price>="+product.getProduct_price_from()+" ");
			}
		}else if( !"".equals(product.getProduct_price_to())&&product.getProduct_price_to()!=null ){
			queryString.append("and osa_prod_price<="+product.getProduct_price_to()+" ");
		}
		if( !"".equals(product.getProduct_quantity_from())&&product.getProduct_quantity_from()!=null ){
			if( !"".equals(product.getProduct_quantity_to())&&product.getProduct_quantity_to()!=null ){
				queryString.append("and osa_prod_stock>="+product.getProduct_quantity_from()+" and osa_prod_stock<="+product.getProduct_quantity_to()+" ");
			}else{
				queryString.append("and osa_prod_stock>="+product.getProduct_quantity_from()+" ");
			}
		}else if( !"".equals(product.getProduct_quantity_to())&&product.getProduct_quantity_to()!=null ){
			queryString.append("and osa_prod_stock<="+product.getProduct_quantity_to()+" ");
		}
		if( !"".equals(product.getProduct_created_from())&&product.getProduct_created_from()!=null ){
			if( !"".equals(product.getProduct_created_to())&&product.getProduct_created_to()!=null ){
				queryString.append("and osa_prod_update>='"+product.getProduct_created_from()+"' and osa_prod_update<='"+product.getProduct_created_to()+"' ");
			}else{
				queryString.append("and osa_prod_update>='"+product.getProduct_created_from()+"' ");
			}
		}else if( !"".equals(product.getProduct_created_to())&&product.getProduct_created_to()!=null ){
			queryString.append("and osa_prod_update<='"+product.getProduct_created_to()+"' ");
		}
		
		//Sort combination
		if( (!"".equals(sortColumn)&&sortColumn!=null ) ){
			queryString.append(" order by "+sortColumn+" "+sortDir);
		}
		if( (!"".equals(start)&&start!=null ) ){
			if("".equals(limit)||limit==null){
				queryString.append(" limit "+start+",-1");
			}else{
				queryString.append(" limit "+start+","+limit);
			}
		}
				return jdbcTemplate.query(queryString.toString(), 
		                new RowMapper<Product>(){         
		                    @Override  
		                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {  
		                    	Product product  = new Product();  
		                    	product.setId(rs.getString("osa_prod_id"));  
		                    	product.setNumber(rs.getString("osa_prod_num"));
		                    	product.setName(rs.getString("osa_prod_name"));
		                    	product.setModel(rs.getString("osa_prod_model"));
		                    	product.setCategoryName(rs.getString("categoryName"));
		                    	//product.setPrice(rs.getDouble("osa_prod_price"));
		                    	product.setCreated(rs.getTimestamp("osa_prod_update"));
		                    	product.setStatus(rs.getString("osa_prod_status"));
		                    	product.setSku(rs.getString("osa_prod_sku"));
		                    	product.setStock(rs.getInt("osa_prod_stock"));
		                    	product.setDeleteFlg(rs.getBoolean("osa_prod_delete_flg"));
		                        return product;  
		                    }  
		        });
	}

	@Override
	public boolean pubProductById(Product product) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					product.isDeleteFlg(),
					product.getStatus(),
					product.getUpdate(),
					product.getUpdateById(),
					product.getValidstart(),
					product.getId() };
			jdbcTemplate.update(_UPDATE_STRING1,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Product> getProdDistinctCat(Product availProduct) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_FOR_DISTINCT_CAT);
		queryString.append(" where 1=1 ");
		//Filter combination
		if( !"".equals(availProduct.getStatus())&&availProduct.getStatus()!=null  ){
			queryString.append("and osa_prod_status='"+availProduct.getStatus()+"' ");
		}
		if( availProduct.isCheck_valid_date() ){
			queryString.append("and ((osa_prod_valid_start<=NOW() and osa_prod_valid_end>=NOW()) "
					+ "OR (osa_prod_valid_start<=NOW() and osa_prod_valid_end is null))");
		}
		if( !"".equals(availProduct.getImage_type())&&availProduct.getImage_type()!=null  ){
			queryString.append("and osa_image_type='"+availProduct.getImage_type()+"' ");
		}
		
		return jdbcTemplate.query(queryString.toString(), 
                new RowMapper<Product>(){         
                    @Override  
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	Product product  = new Product();
                    	product.setCategoryId(rs.getString("osa_prod_cat_id"));
                    	product.setCategoryName(rs.getString("osa_prod_cat_name"));
                        return product;  
                    }
        });
	}
	
	@Override
	public List<Product> loadAvailProducts(Product availProduct) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_FOR_PORTFOLIO);
		queryString.append(" where 1=1 ");
		//Filter combination
		if( !"".equals(availProduct.getStatus())&&availProduct.getStatus()!=null  ){
			queryString.append("and osa_prod_status='"+availProduct.getStatus()+"' ");
		}
		if( availProduct.isCheck_valid_date() ){
			queryString.append("and ((osa_prod_valid_start<=NOW() and osa_prod_valid_end>=NOW()) "
					+ "OR (osa_prod_valid_start<=NOW() and osa_prod_valid_end is null))");
		}
//		if( !"".equals(availProduct.getImage_type())&&availProduct.getImage_type()!=null  ){
//			queryString.append( "and osa_image_type='"+availProduct.getImage_type()+"' ");
//		}
		
		return jdbcTemplate.query(queryString.toString(), 
                new RowMapper<Product>(){         
                    @Override  
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	Product product  = new Product();
                    	product.setId(rs.getString("osa_prod_id"));
                    	product.setName(rs.getString("osa_prod_name"));
                    	product.setNumber(rs.getString("osa_prod_num"));
                    	product.setDesc(rs.getString("osa_prod_desc"));
                    	product.setSpec(rs.getString("osa_prod_spec"));
                    	product.setCategoryId(rs.getString("osa_prod_category_id"));
                    	product.setCategoryName(rs.getString("osa_prod_cat_name"));
                    	product.setPrice(rs.getDouble("osa_prod_price"));
                    	product.setStock(rs.getInt("osa_prod_stock"));
                    	product.setSku(rs.getString("osa_prod_sku"));
                    	product.setModel(rs.getString("osa_prod_model"));
//                    	product.setImage_context_path(rs.getString("osa_context_path"));
//                    	product.setImage_original_filename(rs.getString("osa_original_filename"));
//                    	product.setImage_type(rs.getString("osa_image_type"));
                    	
                        return product;  
                    }
        });
	}

	/**
	 * 读取产品目录最大层级数
	 */
	@Override
	public int getProdCatMaxLvl() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select max(osa_prod_cat_lvl) from osa_prod_cat";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 读取产品目录
	 */
	@Override
	public List<ProductCategory> getProdCat() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_prod_cat";
		return jdbcTemplate.query(sql, new RowMapper<ProductCategory>(){

			@Override
			public ProductCategory mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ProductCategory pc = new ProductCategory();
				pc.setId(rs.getString("osa_prod_cat_id"));
				pc.setActive(rs.getString("osa_prod_cat_active"));
				pc.setPar_id(rs.getString("osa_prod_cat_par_id"));
				pc.setCat_lvl(rs.getString("osa_prod_cat_lvl"));
				pc.setDesc(rs.getString("osa_prod_cat_desc"));
				pc.setName(rs.getString("osa_prod_cat_name"));
				pc.setCreated(rs.getString("osa_created"));
				pc.setCreated_by(rs.getString("osa_created_by"));
				pc.setUpdate(rs.getString("osa_update"));
				pc.setUpdate_by(rs.getString("osa_update_by"));
				return pc;
			}
			
		});
	}

	/**
	 * 根据lvl查询产品类型
	 */
	@Override
	public List<ProductCategory> getProdCatLvlId(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_prod_cat where osa_prod_cat_lvl = ?";
		return jdbcTemplate.query(sql, params, new RowMapper<ProductCategory>(){

			@Override
			public ProductCategory mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ProductCategory pc = new ProductCategory();
				pc.setId(rs.getString("osa_prod_cat_id"));
				pc.setActive(rs.getString("osa_prod_cat_active"));
				pc.setPar_id(rs.getString("osa_prod_cat_par_id"));
				pc.setCat_lvl(rs.getString("osa_prod_cat_lvl"));
				pc.setDesc(rs.getString("osa_prod_cat_desc"));
				pc.setName(rs.getString("osa_prod_cat_name"));
				pc.setCreated(rs.getString("osa_created"));
				pc.setCreated_by(rs.getString("osa_created_by"));
				pc.setUpdate(rs.getString("osa_update"));
				pc.setUpdate_by(rs.getString("osa_update_by"));
				return pc;
			}
			
		});
	}

	private final static String _SELECT_STRING_FOR_APPROVAL_LIST="select a.osa_prod_id,a.osa_prod_num,a.osa_prod_name,b.osa_prod_cat_name categoryName,"
			+ "a.osa_prod_stock,a.osa_prod_update,a.osa_prod_status,"
			+ "a.osa_prod_delete_flg,a.osa_prod_sku,u1.osa_username createdBy, u2.osa_username updateBy from osa_product a inner join "
			+ "osa_prod_cat b on a.osa_prod_category_id=b.osa_prod_cat_id inner join "
			+ "osa_user u1 on a.osa_prod_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_prod_update_by=u2.osa_user_id inner join "
			+ "osa_approval_step r1 on r1.osa_product_id = a.osa_prod_id inner join "
			+ "osa_approval_detail r2 on r1.osa_approval_step_id = r2.osa_approval_step_id "
			+ "where r1.osa_process_flg=TRUE and r2.osa_approval_status_cd='pending' and r2.osa_to_approve=? ";
	@Override
	public List<Product> getProductsForApprover(Product product,
			String approver_id, String start, String limit, String sortColumn,
			String sortDir) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {approver_id};
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_FOR_APPROVAL_LIST);
		
		//Filter combination
		if( !"".equals(product.getNumber())&&product.getNumber()!=null  ){
			queryString.append("and osa_prod_num='"+product.getNumber()+"' ");
		}
		if( !"".equals(product.getName())&&product.getName()!=null ){
			queryString.append("and osa_prod_name like '%"+product.getName()+"%' ");
		}
		if( !"".equals(product.getCategoryId())&&product.getCategoryId()!=null ){
			queryString.append("and osa_prod_category_id="+product.getCategoryId()+" ");
		}
		if( !"".equals(product.getStatus())&&product.getStatus()!=null ){
			queryString.append("and osa_prod_status='"+product.getStatus()+"' ");
		}
		if( !"".equals(product.getProduct_price_from())&&product.getProduct_price_from()!=null ){
			if( !"".equals(product.getProduct_price_to())&&product.getProduct_price_to()!=null ){
				queryString.append("and osa_prod_price>="+product.getProduct_price_from()+" and osa_prod_price<="+product.getProduct_price_to()+" ");
			}else{
				queryString.append("and osa_prod_price>="+product.getProduct_price_from()+" ");
			}
		}else if( !"".equals(product.getProduct_price_to())&&product.getProduct_price_to()!=null ){
			queryString.append("and osa_prod_price<="+product.getProduct_price_to()+" ");
		}
		if( !"".equals(product.getProduct_quantity_from())&&product.getProduct_quantity_from()!=null ){
			if( !"".equals(product.getProduct_quantity_to())&&product.getProduct_quantity_to()!=null ){
				queryString.append("and osa_prod_stock>="+product.getProduct_quantity_from()+" and osa_prod_stock<="+product.getProduct_quantity_to()+" ");
			}else{
				queryString.append("and osa_prod_stock>="+product.getProduct_quantity_from()+" ");
			}
		}else if( !"".equals(product.getProduct_quantity_to())&&product.getProduct_quantity_to()!=null ){
			queryString.append("and osa_prod_stock<="+product.getProduct_quantity_to()+" ");
		}
		if( !"".equals(product.getProduct_created_from())&&product.getProduct_created_from()!=null ){
			if( !"".equals(product.getProduct_created_to())&&product.getProduct_created_to()!=null ){
				queryString.append("and osa_prod_update>='"+product.getProduct_created_from()+"' and osa_prod_update<='"+product.getProduct_created_to()+"' ");
			}else{
				queryString.append("and osa_prod_update>='"+product.getProduct_created_from()+"' ");
			}
		}else if( !"".equals(product.getProduct_created_to())&&product.getProduct_created_to()!=null ){
			queryString.append("and osa_prod_update<='"+product.getProduct_created_to()+"' ");
		}
		
		//Sort combination
		if( (!"".equals(sortColumn)&&sortColumn!=null ) ){
			queryString.append(" order by "+sortColumn+" "+sortDir);
		}
		if( (!"".equals(start)&&start!=null ) ){
			if("".equals(limit)||limit==null){
				queryString.append(" limit "+start+",-1");
			}else{
				queryString.append(" limit "+start+","+limit);
			}
		}
				return jdbcTemplate.query(queryString.toString(), params,
		                new RowMapper<Product>(){         
		                    @Override  
		                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {  
		                    	Product product  = new Product();  
		                    	product.setId(rs.getString("osa_prod_id"));  
		                    	product.setNumber(rs.getString("osa_prod_num"));
		                    	product.setName(rs.getString("osa_prod_name"));
		                    	product.setCategoryName(rs.getString("categoryName"));
		                    	//product.setPrice(rs.getDouble("osa_prod_price"));
		                    	product.setCreated(rs.getTimestamp("osa_prod_update"));
		                    	product.setStatus(rs.getString("osa_prod_status"));
		                    	product.setSku(rs.getString("osa_prod_sku"));
		                    	product.setStock(rs.getInt("osa_prod_stock"));
		                    	product.setDeleteFlg(rs.getBoolean("osa_prod_delete_flg"));
		                        return product;  
		                    }  
		        });
	}

	@Override
	public boolean queryAddRepetition(Product product) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_product where 1=1 ";
		if(!product.getNumber().equals("")&&product.getNumber()!=null){
			sql = sql + " and osa_prod_num = '" + product.getNumber() + "'";
		}
		if(!product.getModel().equals("")&&product.getModel()!=null){
			sql = sql + " and osa_prod_model = '" + product.getModel() + "'";
		}
		int res = jdbcTemplate.queryForInt(sql);
		if(res == 0)
			return false;
		else
			return true;
	}

}
