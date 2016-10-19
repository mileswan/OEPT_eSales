package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.CategoryDao;
import com.oept.esales.model.Category;
@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

	private static final Logger logger = Logger.getLogger(CategoryDaoImpl.class);
	
	private final static String _INSERT_STRING1="insert into osa_prod_cat (osa_prod_cat_name,osa_prod_cat_desc,osa_prod_cat_par_id,"
			+ "osa_created,osa_created_by,osa_update,osa_update_by,osa_prod_cat_active,osa_prod_cat_lvl) "
			+ "values (?,?,?,?,?,?,?,?,?)";
	private final static String _SELECT_STRING1="select a.*, b.osa_prod_cat_name parentCat, u1.osa_username createdBy, u2.osa_username updateBy "
			+ "from osa_prod_cat a left join osa_prod_cat b on a.osa_prod_cat_par_id=b.osa_prod_cat_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_update_by=u2.osa_user_id where a.osa_prod_cat_lvl <> 0 order by a.osa_prod_cat_lvl asc";
	private final static String _SELECT_STRING2="select a.*, b.osa_prod_cat_name parentCat, u1.osa_username createdBy, u2.osa_username updateBy "
			+ "from osa_prod_cat a left join osa_prod_cat b on a.osa_prod_cat_par_id=b.osa_prod_cat_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_update_by=u2.osa_user_id where a.osa_prod_cat_id = ?";
	private final static String _SELECT_STRING3="select b.*, u1.osa_username createdBy, u2.osa_username updateBy "
			+ "from osa_prod_cat a left join osa_prod_cat b on a.osa_prod_cat_par_id=b.osa_prod_cat_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_update_by=u2.osa_user_id where a.osa_prod_cat_id = ?";
	private final static String _DELETE_STRING1="delete from osa_prod_cat where osa_prod_cat_id=?";
	private final static String _UPDATE_STRING1="UPDATE osa_prod_cat SET osa_prod_cat_name = ?, osa_prod_cat_desc = ?,"
			+ "osa_prod_cat_active = ?,osa_prod_cat_lvl=?,osa_prod_cat_par_id=?,osa_update=?,osa_update_by=? "
			+ "WHERE osa_prod_cat_id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean addCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					category.getName(),
					category.getDesc(),
					category.getParentCatId(),
					category.getCreated(),
					category.getCreatedById(),
					category.getUpdate(),
					category.getUpdateById(),
					category.getActive(),
					category.getHierlvl()
					};
			jdbcTemplate.update(_INSERT_STRING1,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delCategoryById(String id) throws Exception {
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
	public boolean updateCategory(Category category)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					category.getName(),
					category.getDesc(),
					category.getActive(),
					category.getHierlvl(),
					category.getParentCatId(),
					category.getUpdate(),
					category.getUpdateById(),
					category.getId()};
			jdbcTemplate.update(_UPDATE_STRING1,params);
			return true;
			}catch(Exception e){
				logger.info(e.getMessage());
				throw e;
			}
	}

	@Override
	public List<Category> getAllCategories() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_STRING1, 
                new RowMapper<Category>(){         
                    @Override  
                    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	Category category  = new Category();  
                    	category.setId(rs.getString("osa_prod_cat_id"));
                    	category.setName(rs.getString("osa_prod_cat_name"));
                    	category.setActive(rs.getBoolean("osa_prod_cat_active"));
                    	category.setDesc(rs.getString("osa_prod_cat_desc"));
                    	category.setParentCat(rs.getString("parentCat"));
                    	category.setParentCatId(rs.getString("osa_prod_cat_par_id"));
                    	category.setCreatedBy(rs.getString("createdBy"));
                    	category.setCreatedById(rs.getString("osa_created_by"));
                    	category.setUpdateBy(rs.getString("updateBy"));
                    	category.setUpdateById(rs.getString("osa_update_by"));
                    	category.setCreated(rs.getTimestamp("osa_created"));
                    	category.setUpdate(rs.getTimestamp("osa_update"));
                    	category.setHierlvl(rs.getInt("osa_prod_cat_lvl"));
                        return category;  
                    }  
        });
	}

	@Override
	public Category getCategoryById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
        
        return (Category) jdbcTemplate.queryForObject(_SELECT_STRING2, params, new RowMapper<Object>(){  
            @Override  
            public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Category category  = new Category();  
            	category.setId(rs.getString("osa_prod_cat_id"));
            	category.setName(rs.getString("osa_prod_cat_name"));
            	category.setActive(rs.getBoolean("osa_prod_cat_active"));
            	category.setDesc(rs.getString("osa_prod_cat_desc"));
            	category.setParentCat(rs.getString("parentCat"));
            	category.setParentCatId(rs.getString("osa_prod_cat_par_id"));
            	category.setCreatedBy(rs.getString("createdBy"));
            	category.setUpdateBy(rs.getString("updateBy"));
            	category.setCreated(rs.getTimestamp("osa_created"));
            	category.setUpdate(rs.getTimestamp("osa_update"));
            	category.setHierlvl(rs.getInt("osa_prod_cat_lvl"));
                return category;  
            } 
        });
	}

	@Override
	public List<Category> getCategories(Category category, String start,
			String limit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getParentCatById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
		return (Category) jdbcTemplate.queryForObject(_SELECT_STRING3, params, new RowMapper<Object>(){  
            @Override  
            public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Category category  = new Category();  
            	category.setId(rs.getString("osa_prod_cat_id"));
            	category.setName(rs.getString("osa_prod_cat_name"));
            	category.setActive(rs.getBoolean("osa_prod_cat_active"));
            	category.setDesc(rs.getString("osa_prod_cat_desc"));
            	category.setParentCatId(rs.getString("osa_prod_cat_par_id"));
            	category.setCreatedBy(rs.getString("createdBy"));
            	category.setUpdateBy(rs.getString("updateBy"));
            	category.setCreated(rs.getTimestamp("osa_created"));
            	category.setUpdate(rs.getTimestamp("osa_update"));
                return category;  
            } 
        });
	}

	
	@Override
	public List<Category> queryCategories(Category category) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_prod_cat where 1=1 ";
		
		if(!category.getParentCatId().equals("")&&category.getParentCatId()!=null){
			sql = sql + " and osa_prod_cat_par_id = '" + category.getParentCatId() + "'";
		}
		
		return jdbcTemplate.query(sql, new RowMapper<Category>(){

			@Override
			public Category mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Category category = new Category();
				category.setId(rs.getString("osa_prod_cat_id"));
            	category.setName(rs.getString("osa_prod_cat_name"));
            	category.setActive(rs.getBoolean("osa_prod_cat_active"));
            	category.setDesc(rs.getString("osa_prod_cat_desc"));
            	category.setParentCatId(rs.getString("osa_prod_cat_par_id"));
            	category.setCreatedBy(rs.getString("osa_created_by"));
            	category.setUpdateBy(rs.getString("osa_update_by"));
            	category.setCreated(rs.getTimestamp("osa_created"));
            	category.setUpdate(rs.getTimestamp("osa_update"));
				return category;
			}
			
		});
	}

	
	@Override
	public boolean queryCategoriesSubclass(Category category) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_prod_cat where 1=1 ";
		
		if(!category.getParentCatId().equals("")&&category.getParentCatId()!=null){
			sql = sql + " and osa_prod_cat_par_id = '" + category.getParentCatId() + "'";
		}
		
		int res = jdbcTemplate.queryForInt(sql);
		if(res!=0)
			return true;
		else
			return false;
	}

}
