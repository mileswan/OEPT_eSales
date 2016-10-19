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

import com.oept.esales.dao.WarehouseStockDao;
import com.oept.esales.model.WarehouseStock;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/31
 * Description: Warehouse stock DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("warehouseStockDao")
public class WarehouseStockDaoImpl implements WarehouseStockDao {

	private static final Logger logger = Logger.getLogger(WarehouseStockDaoImpl.class);
	
	private final static String _INSERT_WAREHOUSE_STOCK="insert into osa_warehouse_prod (osa_warehouse_id,osa_product_id,"
			+ "osa_sku,osa_stock,osa_ordered_stock_in,osa_ordered_stock_out,"
			+ "osa_created,osa_created_by,osa_updated,osa_updated_by) "
			+ "values (?,?,?,?,?,?,?,?,?,?)";
//	private final static String _UPDATE_WAREHOUSE_STOCK="UPDATE osa_warehouse_prod SET osa_warehouse_id = ?, osa_product_id = ?,"
//			+ "osa_sku=?,osa_stock = ?,osa_ordered_stock_in=?,osa_ordered_stock_out=?,"
//			+ "osa_updated=?,osa_updated_by=? "
//			+ "WHERE osa_wh_prod_id = ?";
	private final static String _SELECT_ALL_WAREHOUSE_STOCK="select a.*, b.osa_wh_name, u1.osa_username created_by_username, "
			+ "c.osa_prod_name,u2.osa_username updated_by_username,"
			+ "c.osa_prod_num,c.osa_prod_model,c.osa_prod_category_id,d.osa_prod_cat_name "
			+ "from osa_warehouse_prod a left outer join osa_warehouse b on a.osa_warehouse_id=b.osa_warehouse_id left outer join "
			+ "osa_product c on a.osa_product_id=c.osa_prod_id inner join "
			+ "osa_prod_cat d on c.osa_prod_category_id=d.osa_prod_cat_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_updated_by=u2.osa_user_id where 1=1";
	private final static String _SELECT_WAREHOUSE_STOCK=_SELECT_ALL_WAREHOUSE_STOCK + " and a.osa_wh_prod_id=?";
	private final static String _DELETE_WAREHOUSE_STOCK=  "delete from osa_warehouse_prod where osa_wh_prod_id=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean addStockInfo(WarehouseStock warehouse_stock)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					warehouse_stock.getWarehouse_id(),
					warehouse_stock.getProduct_id(),
					warehouse_stock.getSku(),
					warehouse_stock.getStock(),
					warehouse_stock.getOrdered_stock_in(),
					warehouse_stock.getOrdered_stock_out(),
					warehouse_stock.getCreated_date(),
					warehouse_stock.getCreated_by_user_id(),
					warehouse_stock.getUpdated_date(),
					warehouse_stock.getUpdated_by_user_id()
					};
			jdbcTemplate.update(_INSERT_WAREHOUSE_STOCK,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean updateStockInfoById(WarehouseStock warehouse_stock)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("UPDATE osa_warehouse_prod SET ");
			
			List<Object> parameters = new ArrayList<Object>();
			//Filter combination
			if( !"".equals(warehouse_stock.getWarehouse_id()) && warehouse_stock.getWarehouse_id()!=null ){
				queryString.append("osa_warehouse_id=?,");
				parameters.add(warehouse_stock.getWarehouse_id());
			}
			if( !"".equals(warehouse_stock.getProduct_id()) && warehouse_stock.getProduct_id()!=null ){
				queryString.append("osa_product_id=?,");
				parameters.add(warehouse_stock.getProduct_id());
			}
			if( !"".equals(warehouse_stock.getSku()) && warehouse_stock.getSku()!=null ){
				queryString.append("osa_sku=?,");
				parameters.add(warehouse_stock.getSku());
			}
			if( warehouse_stock.getStock() != -1 ){
				queryString.append("osa_stock=?,");
				parameters.add(warehouse_stock.getStock());
			}
			if( warehouse_stock.getOrdered_stock_in() != -1 ){
				queryString.append("osa_ordered_stock_in=?,");
				parameters.add(warehouse_stock.getOrdered_stock_in());
			}
			if( warehouse_stock.getOrdered_stock_out() != -1 ){
				queryString.append("osa_ordered_stock_out=?,");
				parameters.add(warehouse_stock.getOrdered_stock_out());
			}
			
			queryString.append("osa_updated=?,osa_updated_by=? WHERE osa_wh_prod_id = ?");
			parameters.add(warehouse_stock.getUpdated_date());
			parameters.add(warehouse_stock.getUpdated_by_user_id());
			parameters.add(warehouse_stock.getId());
			
			Object[] params = (Object[])parameters.toArray(new Object[parameters.size()]);
					
			jdbcTemplate.update(queryString.toString(),params);
			return true;
			}catch(Exception e){
				logger.info(e.getMessage());
				throw e;
			}
	}

	@Override
	public List<WarehouseStock> getAllStockInfo() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_ALL_WAREHOUSE_STOCK, 
                new RowMapper<WarehouseStock>(){         
                    @Override  
                    public WarehouseStock mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	WarehouseStock warehouse_stock  = new WarehouseStock();
                    	warehouse_stock.setId(rs.getString("osa_wh_prod_id"));
                    	warehouse_stock.setWarehouse_id(rs.getString("osa_warehouse_id"));
                    	warehouse_stock.setWarehouse_name(rs.getString("osa_wh_name"));
                    	warehouse_stock.setProduct_id(rs.getString("osa_product_id"));
                    	warehouse_stock.setProduct_name(rs.getString("osa_prod_name"));
                    	warehouse_stock.setProduct_number(rs.getString("osa_prod_num"));
                    	warehouse_stock.setProduct_model(rs.getString("osa_prod_model"));
                    	warehouse_stock.setProduct_category(rs.getString("osa_prod_cat_name"));
                    	warehouse_stock.setProduct_category_id(rs.getString("osa_prod_category_id"));
                    	warehouse_stock.setSku(rs.getString("osa_sku"));
                    	warehouse_stock.setStock(rs.getInt("osa_stock"));
                    	warehouse_stock.setOrdered_stock_in(rs.getInt("osa_ordered_stock_in"));
                    	warehouse_stock.setOrdered_stock_out(rs.getInt("osa_ordered_stock_out"));
                    	warehouse_stock.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	warehouse_stock.setCreated_by_user_name(rs.getString("created_by_username"));
                    	warehouse_stock.setCreated_date(rs.getTimestamp("osa_created"));
                    	warehouse_stock.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	warehouse_stock.setUpdated_by_user_name(rs.getString("updated_by_username"));
                    	warehouse_stock.setUpdated_date(rs.getTimestamp("osa_updated"));
                        return warehouse_stock;  
                    }
        });
	}

	@Override
	public WarehouseStock getStockInfoById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
        
        return (WarehouseStock) jdbcTemplate.queryForObject(_SELECT_WAREHOUSE_STOCK, params, new RowMapper<Object>(){  
            @Override  
            public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	WarehouseStock warehouse_stock  = new WarehouseStock();  
            	warehouse_stock.setId(rs.getString("osa_wh_prod_id"));
            	warehouse_stock.setWarehouse_id(rs.getString("osa_warehouse_id"));
            	warehouse_stock.setWarehouse_name(rs.getString("osa_wh_name"));
            	warehouse_stock.setProduct_id(rs.getString("osa_product_id"));
            	warehouse_stock.setProduct_name(rs.getString("osa_prod_name"));
            	warehouse_stock.setProduct_number(rs.getString("osa_prod_num"));
            	warehouse_stock.setProduct_model(rs.getString("osa_prod_model"));
            	warehouse_stock.setProduct_category(rs.getString("osa_prod_cat_name"));
            	warehouse_stock.setProduct_category_id(rs.getString("osa_prod_category_id"));
            	warehouse_stock.setSku(rs.getString("osa_sku"));
            	warehouse_stock.setStock(rs.getInt("osa_stock"));
            	warehouse_stock.setOrdered_stock_in(rs.getInt("osa_ordered_stock_in"));
            	warehouse_stock.setOrdered_stock_out(rs.getInt("osa_ordered_stock_out"));
            	warehouse_stock.setCreated_by_user_id(rs.getString("osa_created_by"));
            	warehouse_stock.setCreated_by_user_name(rs.getString("created_by_username"));
            	warehouse_stock.setCreated_date(rs.getTimestamp("osa_created"));
            	warehouse_stock.setUpdated_by_user_id(rs.getString("osa_updated_by"));
            	warehouse_stock.setUpdated_by_user_name(rs.getString("updated_by_username"));
            	warehouse_stock.setUpdated_date(rs.getTimestamp("osa_updated"));
                return warehouse_stock;  
            } 
        });
	}

	@Override
	public List<WarehouseStock> getStockInfos(WarehouseStock warehouse_stock,
			String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_ALL_WAREHOUSE_STOCK);
		
		//Filter combination
		if( !"".equals(warehouse_stock.getProduct_id()) && warehouse_stock.getProduct_id()!=null ){
			queryString.append(" and a.osa_product_id='"+warehouse_stock.getProduct_id()+"' ");
		}
		if( !"".equals(warehouse_stock.getWarehouse_id()) && warehouse_stock.getWarehouse_id()!=null ){
			queryString.append(" and a.osa_warehouse_id='"+warehouse_stock.getWarehouse_id()+"' ");
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
                new RowMapper<WarehouseStock>(){         
                    @Override  
                    public WarehouseStock mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	WarehouseStock warehouse_stock  = new WarehouseStock();
                    	warehouse_stock.setId(rs.getString("osa_wh_prod_id"));
                    	warehouse_stock.setWarehouse_id(rs.getString("osa_warehouse_id"));
                    	warehouse_stock.setWarehouse_name(rs.getString("osa_wh_name"));
                    	warehouse_stock.setProduct_id(rs.getString("osa_product_id"));
                    	warehouse_stock.setProduct_name(rs.getString("osa_prod_name"));
                    	warehouse_stock.setProduct_number(rs.getString("osa_prod_num"));
                    	warehouse_stock.setProduct_model(rs.getString("osa_prod_model"));
                    	warehouse_stock.setProduct_category(rs.getString("osa_prod_cat_name"));
                    	warehouse_stock.setProduct_category_id(rs.getString("osa_prod_category_id"));
                    	warehouse_stock.setSku(rs.getString("osa_sku"));
                    	warehouse_stock.setStock(rs.getInt("osa_stock"));
                    	warehouse_stock.setOrdered_stock_in(rs.getInt("osa_ordered_stock_in"));
                    	warehouse_stock.setOrdered_stock_out(rs.getInt("osa_ordered_stock_out"));
                    	warehouse_stock.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	warehouse_stock.setCreated_by_user_name(rs.getString("created_by_username"));
                    	warehouse_stock.setCreated_date(rs.getTimestamp("osa_created"));
                    	warehouse_stock.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	warehouse_stock.setUpdated_by_user_name(rs.getString("updated_by_username"));
                    	warehouse_stock.setUpdated_date(rs.getTimestamp("osa_updated"));
                        return warehouse_stock;  
                    }
        });
	}

	@Override
	public boolean delStockInfoById(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					id };
			jdbcTemplate.update(_DELETE_WAREHOUSE_STOCK,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

}
