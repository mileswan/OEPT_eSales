package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.WarehouseDao;
import com.oept.esales.model.WarehouseFlat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/30
 * Description: Warehouse DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("warehouseDao")
public class WarehouseDaoImpl implements WarehouseDao {

	private static final Logger logger = Logger.getLogger(WarehouseDaoImpl.class);
	
	private final static String _INSERT_WAREHOUSE="insert into osa_warehouse (osa_wh_number,osa_wh_name,osa_wh_comm,"
			+ "osa_primary_addr_id,osa_addr_name,osa_primary_contact_id,osa_wh_contact_name,osa_wh_contact_phone,osa_active_flg,"
			+ "osa_created,osa_created_by,osa_updated,osa_updated_by) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String _SELECT_ALL_WAREHOUSE="select a.*, c.osa_addr_name primary_addr_name, u1.osa_username created_by_username, "
			+ "b.osa_contact_name primary_contact_name,b.osa_cellphone primary_contact_phone,u2.osa_username updated_by_username "
			+ "from osa_warehouse a left outer join osa_contact b on a.osa_primary_contact_id=b.osa_contact_id left outer join "
			+ "osa_address c on a.osa_primary_addr_id=c.osa_addr_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_updated_by=u2.osa_user_id where 1=1";
	private final static String _SELECT_WAREHOUSE=_SELECT_ALL_WAREHOUSE + " and a.osa_warehouse_id=?";
	
	private final static String _DELETE_WAREHOUSE="delete from osa_warehouse where osa_warehouse_id=?";
	private final static String _UPDATE_WAREHOUSE="UPDATE osa_warehouse SET osa_wh_number = ?, osa_wh_name = ?,osa_wh_comm=?,"
			+ "osa_primary_addr_id = ?,osa_addr_name=?,osa_primary_contact_id=?,osa_wh_contact_name=?,osa_wh_contact_phone=?,osa_active_flg=?,"
			+ "osa_updated=?,osa_updated_by=? "
			+ "WHERE osa_warehouse_id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean addWarehouse(WarehouseFlat warehouse) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					warehouse.getNumber(),
					warehouse.getName(),
					warehouse.getComment(),
					warehouse.getPrimary_addr_id(),
					warehouse.getAddress_name(),
					warehouse.getPrimary_contact_id(),
					warehouse.getPrimary_contact_name(),
					warehouse.getPrimary_contact_cellphone(),
					warehouse.isActive(),
					warehouse.getCreated_date(),
					warehouse.getCreated_by_user_id(),
					warehouse.getUpdated_date(),
					warehouse.getUpdated_by_user_id()
					};
			jdbcTemplate.update(_INSERT_WAREHOUSE,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delWarehouseById(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					id };
			jdbcTemplate.update(_DELETE_WAREHOUSE,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean updateWarehouse(WarehouseFlat warehouse) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					warehouse.getNumber(),
					warehouse.getName(),
					warehouse.getComment(),
					warehouse.getPrimary_addr_id(),
					warehouse.getAddress_name(),
					warehouse.getPrimary_contact_id(),
					warehouse.getPrimary_contact_name(),
					warehouse.getPrimary_contact_cellphone(),
					warehouse.isActive(),
					warehouse.getUpdated_date(),
					warehouse.getUpdated_by_user_id(),
					warehouse.getId()};
			jdbcTemplate.update(_UPDATE_WAREHOUSE,params);
			return true;
			}catch(Exception e){
				logger.info(e.getMessage());
				throw e;
			}
	}

	@Override
	public List<WarehouseFlat> getAllWarehouses() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_ALL_WAREHOUSE, 
                new RowMapper<WarehouseFlat>(){         
                    @Override  
                    public WarehouseFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	WarehouseFlat warehouse  = new WarehouseFlat();  
                    	warehouse.setId(rs.getString("osa_warehouse_id"));
                    	warehouse.setNumber(rs.getString("osa_wh_number"));
                    	warehouse.setName(rs.getString("osa_wh_name"));
                    	warehouse.setComment(rs.getString("osa_wh_comm"));
                    	warehouse.setPrimary_addr_id(rs.getString("osa_primary_addr_id"));
                    	warehouse.setPrimary_addr_name(rs.getString("primary_addr_name"));
                    	warehouse.setAddress_name(rs.getString("osa_addr_name"));
                    	warehouse.setPrimary_contact_id(rs.getString("osa_primary_contact_id"));
                    	warehouse.setPrimary_contact_name(rs.getString("osa_wh_contact_name"));
                    	warehouse.setPrimary_contact_cellphone(rs.getString("osa_wh_contact_phone"));
                    	warehouse.setCreated_date(rs.getTimestamp("osa_created"));
                    	warehouse.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	warehouse.setCreated_by_user_name(rs.getString("created_by_username"));
                    	warehouse.setUpdated_date(rs.getTimestamp("osa_updated"));
                    	warehouse.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	warehouse.setUpdated_by_user_name(rs.getString("updated_by_username"));
                    	warehouse.setActive(rs.getBoolean("osa_active_flg"));
                        return warehouse;  
                    }
        });
	}

	@Override
	public WarehouseFlat getWarehouseById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
        
        return (WarehouseFlat) jdbcTemplate.queryForObject(_SELECT_WAREHOUSE, params, new RowMapper<Object>(){
            @Override  
            public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	WarehouseFlat warehouse  = new WarehouseFlat();  
            	warehouse.setId(rs.getString("osa_warehouse_id"));
            	warehouse.setNumber(rs.getString("osa_wh_number"));
            	warehouse.setName(rs.getString("osa_wh_name"));
            	warehouse.setComment(rs.getString("osa_wh_comm"));
            	warehouse.setPrimary_addr_id(rs.getString("osa_primary_addr_id"));
            	warehouse.setPrimary_addr_name(rs.getString("primary_addr_name"));
            	warehouse.setAddress_name(rs.getString("osa_addr_name"));
            	warehouse.setPrimary_contact_id(rs.getString("osa_primary_contact_id"));
            	warehouse.setPrimary_contact_name(rs.getString("osa_wh_contact_name"));
            	warehouse.setPrimary_contact_cellphone(rs.getString("osa_wh_contact_phone"));
            	warehouse.setCreated_date(rs.getTimestamp("osa_created"));
            	warehouse.setCreated_by_user_id(rs.getString("osa_created_by"));
            	warehouse.setCreated_by_user_name(rs.getString("created_by_username"));
            	warehouse.setUpdated_date(rs.getTimestamp("osa_updated"));
            	warehouse.setUpdated_by_user_id(rs.getString("osa_updated_by"));
            	warehouse.setUpdated_by_user_name(rs.getString("updated_by_username"));
            	warehouse.setActive(rs.getBoolean("osa_active_flg"));
                return warehouse;  
            } 
        });
	}

	@Override
	public List<WarehouseFlat> getWarehouses(WarehouseFlat warehouse, String start,
			String limit,String sortColumn, String sortDir) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_ALL_WAREHOUSE);
		
		//Filter combination
		if( warehouse.isCheckActive() ){
			queryString.append(" and a.osa_active_flg is true ");
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
                new RowMapper<WarehouseFlat>(){         
                    @Override  
                    public WarehouseFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	WarehouseFlat warehouse  = new WarehouseFlat();  
                    	warehouse.setId(rs.getString("osa_warehouse_id"));
                    	warehouse.setNumber(rs.getString("osa_wh_number"));
                    	warehouse.setName(rs.getString("osa_wh_name"));
                    	warehouse.setComment(rs.getString("osa_wh_comm"));
                    	warehouse.setPrimary_addr_id(rs.getString("osa_primary_addr_id"));
                    	warehouse.setPrimary_addr_name(rs.getString("primary_addr_name"));
                    	warehouse.setAddress_name(rs.getString("osa_addr_name"));
                    	warehouse.setPrimary_contact_id(rs.getString("osa_primary_contact_id"));
                    	warehouse.setPrimary_contact_name(rs.getString("osa_wh_contact_name"));
                    	warehouse.setPrimary_contact_cellphone(rs.getString("osa_wh_contact_phone"));
                    	warehouse.setCreated_date(rs.getTimestamp("osa_created"));
                    	warehouse.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	warehouse.setCreated_by_user_name(rs.getString("created_by_username"));
                    	warehouse.setUpdated_date(rs.getTimestamp("osa_updated"));
                    	warehouse.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	warehouse.setUpdated_by_user_name(rs.getString("updated_by_username"));
                    	warehouse.setActive(rs.getBoolean("osa_active_flg"));
                        return warehouse;  
                    }
        });
	}

	private final static String _SELECT_WAREHOUSE_ITEM="select a.*, b.osa_wh_name warehouse_name, b.osa_wh_number warehouse_number,"
			+ "u1.osa_username created_by_username, "
			+ "c.osa_prod_name product_name,c.osa_prod_price product_price,c.osa_prod_status product_status,c.osa_prod_status_val product_status_val,"
			+ "c.osa_prod_model product_model,c.osa_prod_num product_number,"
			+ "u2.osa_username updated_by_username "
			+ "from osa_warehouse_prod a left outer join osa_warehouse b on a.osa_warehouse_id=b.osa_warehouse_id left outer join "
			+ "osa_product c on a.osa_product_id=c.osa_prod_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_updated_by=u2.osa_user_id where a.osa_warehouse_id=?";
	@Override
	public List<WarehouseFlat> getItemsByWarehouseId(String warehouseId)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {warehouseId};
		
		return jdbcTemplate.query(_SELECT_WAREHOUSE_ITEM, params,
                new RowMapper<WarehouseFlat>(){         
                    @Override  
                    public WarehouseFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	WarehouseFlat warehouse  = new WarehouseFlat();  
                    	warehouse.setId(rs.getString("osa_warehouse_id"));
                    	warehouse.setNumber(rs.getString("warehouse_number"));
                    	warehouse.setName(rs.getString("warehouse_name"));
                    	warehouse.setItem_id(rs.getString("osa_wh_prod_id"));
                    	warehouse.setItem_product_id(rs.getString("osa_product_id"));
                    	warehouse.setItem_product_name(rs.getString("product_name"));
                    	warehouse.setItem_product_number(rs.getString("product_number"));
                    	warehouse.setItem_product_price(rs.getDouble("product_price"));
                    	warehouse.setItem_stock(rs.getInt("osa_stock"));
                    	warehouse.setItem_ordered_stock_in(rs.getInt("osa_ordered_stock_in"));
                    	warehouse.setItem_ordered_stock_out(rs.getInt("osa_ordered_stock_out"));
                    	warehouse.setItem_sku(rs.getString("osa_sku"));
                    	warehouse.setItem_created_date(rs.getTimestamp("osa_created"));
                    	warehouse.setItem_created_by_user_id(rs.getString("osa_created_by"));
                    	warehouse.setItem_created_by_user_name(rs.getString("created_by_username"));
                    	warehouse.setItem_updated_date(rs.getTimestamp("osa_updated"));
                    	warehouse.setItem_product_status(rs.getString("product_status"));
                    	warehouse.setItem_product_status_val(rs.getString("product_status_val"));
                    	warehouse.setItem_product_model(rs.getString("product_model"));
                        return warehouse;  
                    }
        });
	}
}
