package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.ListOfValueDao;
import com.oept.esales.model.ListOfValue;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/12
 * Description: List of values DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("listOfValueDao")
public class ListOfValueDaoImpl implements ListOfValueDao {

	private static final Logger logger = Logger.getLogger(ListOfValueDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private final static String _INSERT_STRING_VALUE="insert into osa_list_of_value ("
			+ "osa_list_code,osa_list_name,osa_list_value,osa_list_desc,"
			+ "osa_created,osa_created_by,osa_updated,osa_updated_by) "
			+ "values (?,?,?,?,?,?,?,?)";
	@Override
	public boolean addValue(ListOfValue list_of_value) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					list_of_value.getList_code(),
					list_of_value.getList_name(),
					list_of_value.getList_value(),
					list_of_value.getList_desc(),
					list_of_value.getCreated_date(),
					list_of_value.getCreated_by_user_id(),
					list_of_value.getUpdated_date(),
					list_of_value.getUpdated_by_user_id()
					};
			jdbcTemplate.update(_INSERT_STRING_VALUE,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _DELETE_STRING_VALUE="delete from osa_list_of_value "
			+ "where osa_id=?";
	@Override
	public boolean delValueById(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {id};
			jdbcTemplate.update(_DELETE_STRING_VALUE,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _SELECT_ALL_VALUE="select a.*, u1.osa_username created_by_username, "
			+ "u2.osa_username updated_by_username "
			+ "from osa_list_of_value a left outer join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_updated_by=u2.osa_user_id";
	@Override
	public List<ListOfValue> getAllListOfValues() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_ALL_VALUE, 
                new RowMapper<ListOfValue>(){         
                    @Override  
                    public ListOfValue mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	ListOfValue list_of_value  = new ListOfValue();
                    	list_of_value.setId(rs.getString("osa_id"));
                    	list_of_value.setList_code(rs.getString("osa_list_code"));
                    	list_of_value.setList_name(rs.getString("osa_list_name"));
                    	list_of_value.setList_value(rs.getString("osa_list_value"));
                    	list_of_value.setList_desc(rs.getString("osa_list_desc"));
                    	list_of_value.setCreated_date(rs.getTimestamp("osa_created"));
                    	list_of_value.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	list_of_value.setCreated_by_user_name(rs.getString("created_by_username"));
                    	list_of_value.setUpdated_date(rs.getTimestamp("osa_updated"));
                    	list_of_value.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	list_of_value.setUpdated_by_user_name(rs.getString("updated_by_username"));
                        return list_of_value;  
                    }
        });
	}

	@Override
	public ListOfValue getListOfValueById(String id) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_ALL_VALUE);
		queryString.append(" where osa_id=? ");
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject(queryString.toString(), params,
                new RowMapper<ListOfValue>(){         
                    @Override  
                    public ListOfValue mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	ListOfValue list_of_value  = new ListOfValue();
                    	list_of_value.setId(rs.getString("osa_id"));
                    	list_of_value.setList_code(rs.getString("osa_list_code"));
                    	list_of_value.setList_name(rs.getString("osa_list_name"));
                    	list_of_value.setList_value(rs.getString("osa_list_value"));
                    	list_of_value.setList_desc(rs.getString("osa_list_desc"));
                    	list_of_value.setCreated_date(rs.getTimestamp("osa_created"));
                    	list_of_value.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	list_of_value.setCreated_by_user_name(rs.getString("created_by_username"));
                    	list_of_value.setUpdated_date(rs.getTimestamp("osa_updated"));
                    	list_of_value.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	list_of_value.setUpdated_by_user_name(rs.getString("updated_by_username"));
                        return list_of_value;    
                    }
        });
	}
	
	/**
	 * 日期转换:将"16/05/2015 16:35"格式转为"2015-05-16 16:35"格式
	 * @param date
	 * @return
	 */
	public static String dateFormatMethod(String date){
		String[] s = date.split(" ");
		String[] year = s[0].split("/");
		String finalYear = "";
		for(int i = year.length-1; i>=0; i--){
			if(i != 0){
				finalYear += (year[i] + "-");
			}else{
				finalYear += year[i];
			}
		}
		finalYear += (" " + s[1]);
		return finalYear;
	}

	@Override
	public List<ListOfValue> getListOfValues(ListOfValue list_of_value,
			String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_ALL_VALUE);
		queryString.append(" where 1=1 ");
		
		String date_from = list_of_value.getCreated_date_from();
		String date_to = list_of_value.getCreated_date_to();
		
		
		//Filter combination
		if( !"".equals(list_of_value.getList_code()) && list_of_value.getList_code()!=null ){
			queryString.append("and a.osa_list_code='"+list_of_value.getList_code()+"' ");
		}
		if( !"".equals(list_of_value.getList_name()) && list_of_value.getList_name()!=null ){
			queryString.append("and a.osa_list_name='"+list_of_value.getList_name()+"' ");
		}
		if( !"".equals(list_of_value.getList_value()) && list_of_value.getList_value()!=null ){
			queryString.append("and a.osa_list_value ='"+list_of_value.getList_value()+"' ");
		}
		if( !"".equals(list_of_value.getList_desc()) && list_of_value.getList_desc()!=null ){
			queryString.append("and a.osa_list_desc like '"+list_of_value.getList_desc()+"' ");
		}
		if( !"".equals(list_of_value.getCreated_date_from())&&list_of_value.getCreated_date_from()!=null ){
			if( !"".equals(list_of_value.getCreated_date_to())&&list_of_value.getCreated_date_to()!=null ){
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_from)+"' and a.osa_created<='"+dateFormatMethod(date_to)+"' ");
			}else{
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_to)+"' ");
			}
		}else if( !"".equals(list_of_value.getCreated_date_to())&&list_of_value.getCreated_date_to()!=null ){
			queryString.append("and a.osa_created<='"+dateFormatMethod(date_to)+"' ");
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
                new RowMapper<ListOfValue>(){         
                    @Override  
                    public ListOfValue mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	ListOfValue list_of_value  = new ListOfValue();
                    	list_of_value.setId(rs.getString("osa_id"));
                    	list_of_value.setList_code(rs.getString("osa_list_code"));
                    	list_of_value.setList_name(rs.getString("osa_list_name"));
                    	list_of_value.setList_value(rs.getString("osa_list_value"));
                    	list_of_value.setList_desc(rs.getString("osa_list_desc"));
                    	list_of_value.setCreated_date(rs.getTimestamp("osa_created"));
                    	list_of_value.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	list_of_value.setCreated_by_user_name(rs.getString("created_by_username"));
                    	list_of_value.setUpdated_date(rs.getTimestamp("osa_updated"));
                    	list_of_value.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	list_of_value.setUpdated_by_user_name(rs.getString("updated_by_username"));
                        return list_of_value;    
                    }
        });
	}

	private final static String _UPDATE_STRING_VALUE="UPDATE osa_list_of_value SET osa_list_code=?,"
			+ "osa_list_name=?, osa_list_value=?, osa_list_desc=?, "
			+ "osa_updated=?,osa_updated_by=? "
			+ "WHERE osa_id = ?";
	@Override
	public boolean updateValue(ListOfValue list_of_value) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					list_of_value.getList_code(),
					list_of_value.getList_name(),
					list_of_value.getList_value(),
					list_of_value.getList_desc(),
					list_of_value.getUpdated_date(),
					list_of_value.getUpdated_by_user_id(),
					list_of_value.getId()
					 };
			jdbcTemplate.update(_UPDATE_STRING_VALUE,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _SELECT_ONE_VALUE="select osa_list_value "
			+ "from osa_list_of_value where osa_list_code=? and osa_list_name=?";
	@Override
	public String getValueByCodeName(String code, String name) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {code,name};
		return jdbcTemplate.queryForObject(_SELECT_ONE_VALUE, params,
                new RowMapper<String>(){         
                    @Override  
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("osa_list_value"); 
                    }
        });
	}

}
