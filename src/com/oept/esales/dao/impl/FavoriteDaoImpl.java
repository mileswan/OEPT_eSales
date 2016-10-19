package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.FavoriteDao;
import com.oept.esales.model.Favorite;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/8
 * Description: Favorite items DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("favoriteDao")
public class FavoriteDaoImpl implements FavoriteDao {

	private static final Logger logger = Logger.getLogger(FavoriteDaoImpl.class);
	
	private final static String _INSERT_NEW_ITEM="insert into osa_fav_item (osa_fav_user_id,osa_fav_prod_id,"
			+ "osa_fav_created,osa_fav_created_by,osa_fav_update,osa_fav_update_by) "
			+ "values (?,?,?,?,?,?)";
	private final static String _CHECK_ITEM="select count(*) as count from osa_fav_item where osa_fav_user_id=? and osa_fav_prod_id=?";
	
	private final static String _SELECT_ITEMS="select a.*,p.*,f.*,c.osa_prod_cat_name from osa_fav_item a inner join osa_user u on a.osa_fav_user_id=u.osa_user_id "
			+ "inner join osa_product p on a.osa_fav_prod_id=p.osa_prod_id inner join osa_prod_cat c on "
			+ "p.osa_prod_category_id=c.osa_prod_cat_id inner join osa_user u1 on a.osa_fav_created_by=u1.osa_user_id "
			+ "left outer join osa_user u2 on a.osa_fav_update_by=u2.osa_user_id "
			+ "inner join osa_prod_file f on a.osa_fav_prod_id=f.osa_product_id "
			+ "where a.osa_fav_user_id=? and f.osa_image_type='Thumbnail Image'";
	private final static String _DELETE_ITEM="delete from osa_fav_item where osa_fav_item_id=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean addFavItem(Favorite item) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					item.getItem_user_id(),
					item.getItem_prod_id(),
					item.getItem_created_date(),
					item.getItem_created_by_user_id(),
					item.getItem_update_date(),
					item.getItem_update_by_user_id()
					};
			
			jdbcTemplate.update(_INSERT_NEW_ITEM,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delFavItemById(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					id };
			jdbcTemplate.update(_DELETE_ITEM,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Favorite> getFavItemsByUserId(String userid) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {
				userid
				};
		return jdbcTemplate.query(_SELECT_ITEMS, params,
                new RowMapper<Favorite>(){         
                    @Override  
                    public Favorite mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	Favorite item  = new Favorite();
                    	item.setItem_id(rs.getString("osa_fav_item_id"));
                    	item.setItem_prod_id(rs.getString("osa_fav_prod_id"));
                    	item.setItem_user_id(rs.getString("osa_fav_user_id"));
                    	item.setItem_created_date(rs.getTimestamp("osa_fav_created"));
                    	item.setItem_update_date(rs.getTimestamp("osa_fav_update"));
                    	item.setItem_prod_category_id(rs.getString("osa_prod_category_id"));
                    	item.setItem_prod_category_name(rs.getString("osa_prod_cat_name"));
                    	item.setItem_prod_number(rs.getString("osa_prod_num"));
                    	item.setItem_prod_name(rs.getString("osa_prod_name"));
                    	item.setItem_prod_price(rs.getDouble("osa_prod_price"));
                    	item.setItem_prod_stock(rs.getInt("osa_prod_stock"));
                    	item.setItem_prod_sku(rs.getString("osa_prod_sku"));
                    	item.setImage_context_path(rs.getString("osa_context_path"));
                    	item.setImage_original_filename(rs.getString("osa_original_filename"));
                        return item;  
                    }  
        });
	}

	@Override
	public Favorite getFavItemsById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkFavItem(String prod_id, String user_id)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					user_id,
					prod_id
			};
			Favorite item = jdbcTemplate.queryForObject(_CHECK_ITEM, params,
					new RowMapper<Favorite>(){         
				@Override  
				public Favorite mapRow(ResultSet rs, int rowNum) throws SQLException {  
					Favorite item  = new Favorite(); 
					if(rs.getInt("count")>0){
						item.setItem_collected(true);
					}else{
						item.setItem_collected(false);
					}
					return item;
				}
			});
			return item.isItem_collected();
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

}
