package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.ShopcartDao;
import com.oept.esales.model.Shopcart;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/26
 * Description: Shopping cart DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("shopcartDao")
public class ShopcartDaoImpl implements ShopcartDao {
	
	private static final Logger logger = Logger.getLogger(ShopcartDaoImpl.class);
	
	private final static String _INSERT_STRING1="insert into osa_shopcart_item (osa_prod_id,osa_shop_user_id,osa_prod_shop_quantity,"
			+ "osa_prod_shop_price,osa_prod_shop_amount,"
			+ "osa_item_created,osa_item_created_by,osa_item_update,osa_item_update_by) "
			+ "values (?,?,?,?,?,?,?,?,?)";
	private final static String _SELECT_STRING1="select a.*, b.*,c.osa_prod_cat_name category_name, u1.osa_username created_by_name, "
			+ "u2.osa_username update_by_name "
			+ "from osa_shopcart_item a inner join osa_product b on a.osa_prod_id=b.osa_prod_id "
			+ "inner join osa_prod_cat c on b.osa_prod_category_id=c.osa_prod_cat_id left outer join "
			+ "osa_user u1 on a.osa_item_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_item_update_by=u2.osa_user_id "
			+ "where a.osa_shop_user_id = ? and a.osa_prod_id = ?";
	private final static String _SELECT_STRING2="select a.*, b.*,f.*,c.osa_prod_cat_name category_name, u1.osa_username created_by_name, "
			+ "u2.osa_username update_by_name "
			+ "from osa_shopcart_item a inner join osa_product b on a.osa_prod_id=b.osa_prod_id "
			+ "inner join osa_prod_cat c on b.osa_prod_category_id=c.osa_prod_cat_id left outer join "
			+ "osa_user u1 on a.osa_item_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_item_update_by=u2.osa_user_id inner join "
			+ "osa_prod_file f on a.osa_prod_id=f.osa_product_id "
			+ "where a.osa_shop_user_id = ? and f.osa_image_type='Thumbnail Image'";
	private final static String _SELECT_ITEM_DETAIL="select * from osa_shopcart_item where osa_shop_item_id=?";
	private final static String _UPDATE_STRING1="UPDATE osa_shopcart_item SET osa_prod_shop_quantity = ?, osa_prod_shop_price = ?,osa_prod_shop_amount = ?, "
			+ "osa_item_update=?,osa_item_update_by=? "
			+ "WHERE osa_shop_item_id = ?";
	private final static String _DELETE_STRING1="delete from osa_shopcart_item where osa_shop_item_id=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean addItem(Shopcart item) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					item.getProduct_id(),
					item.getUser_id(),
					item.getQuantity(),
					item.getPrice(),
					item.getAmount(),
					item.getCreated(),
					item.getCreated_by_id(),
					item.getUpdate(),
					item.getUpdate_by_id()
					};
			jdbcTemplate.update(_INSERT_STRING1,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delItemById(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					id };
			jdbcTemplate.update(_DELETE_STRING1,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean updateItem(Shopcart item) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					item.getQuantity(),
					item.getPrice(),
					item.getAmount(),
					item.getUpdate(),
					item.getUpdate_by_id(),
					item.getId()};
			jdbcTemplate.update(_UPDATE_STRING1,params);
			return true;
			}catch(Exception e){
				logger.info(e.getMessage());
				throw e;
			}
	}

	@Override
	public List<Shopcart> getAllItems() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcart> getItemsByUserId(String userid) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {userid};
        
        return jdbcTemplate.query(_SELECT_STRING2, params, new RowMapper<Shopcart>(){  
            @Override  
            public Shopcart mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Shopcart item  = new Shopcart();
            	item.setId(rs.getString("osa_shop_item_id"));
            	item.setProduct_name(rs.getString("osa_prod_name"));
            	item.setProduct_category_name(rs.getString("category_name"));
            	item.setQuantity(rs.getInt("osa_prod_shop_quantity"));
            	item.setProduct_price(rs.getDouble("osa_prod_price"));
            	item.setPrice(rs.getDouble("osa_prod_shop_price"));
            	item.setProduct_image_original_name(rs.getString("osa_original_filename"));
            	item.setProduct_image_context_path(rs.getString("osa_context_path"));
                return item;  
            } 
        });
	}

	@Override
	public List<Shopcart> getItems(Shopcart item, String start, String limit)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcart> getItemsByUserProdId(String userid, String productid)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {userid,productid};
        
        return jdbcTemplate.query(_SELECT_STRING1, params, new RowMapper<Shopcart>(){  
            @Override  
            public Shopcart mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Shopcart item  = new Shopcart();
            	item.setId(rs.getString("osa_shop_item_id"));
            	item.setProduct_name(rs.getString("osa_prod_name"));
            	item.setQuantity(rs.getInt("osa_prod_shop_quantity"));
            	item.setProduct_price(rs.getDouble("osa_prod_price"));
                return item;  
            } 
        });
	}

	@Override
	public Shopcart getItemById(String itemid) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {itemid};
		
		return jdbcTemplate.queryForObject(_SELECT_ITEM_DETAIL, params,
                new RowMapper<Shopcart>(){         
                    @Override  
                    public Shopcart mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	Shopcart item  = new Shopcart();
                    	item.setUser_id(rs.getString("osa_shop_user_id"));
                    	item.setAmount(rs.getDouble("osa_prod_shop_amount"));
                    	item.setPrice(rs.getDouble("osa_prod_shop_price"));
                    	item.setProduct_id(rs.getString("osa_prod_id"));
                    	item.setQuantity(rs.getInt("osa_prod_shop_quantity"));
                    	
                        return item;  
                    }  
        });
	}

}
