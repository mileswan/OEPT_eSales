package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.OrderHistoryDao;
import com.oept.esales.model.OrderHistory;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/9
 * Description: Order history DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("orderHistoryDao")
public class OrderHistoryDaoImpl implements OrderHistoryDao {

	private static final Logger logger = Logger.getLogger(OrderHistoryDaoImpl.class);
	private final static String _INSERT_STRING_ORDER_HISTORY="insert into osa_order_history ("
			+ "osa_order_id,osa_history_desc,"
			+ "osa_created,osa_created_by) "
			+ "values (?,?,?,?)";
	private final static String _SELECT_ALL_ORDER_HISTORY="select a.*, u1.osa_username created_by_username, "
			+ "d.osa_order_number order_number,"
			+ "d.osa_order_type_cd order_type_cd,d.osa_order_type order_type "
			+ "from osa_order_history a left outer join "
			+ "osa_order d on a.osa_order_id=d.osa_order_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id where 1=1 ";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean addHistory(OrderHistory order_history) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order_history.getOrder_id(),
					order_history.getDescription(),
					order_history.getCreated_date(),
					order_history.getCreated_by_user_id()
					};
			jdbcTemplate.update(_INSERT_STRING_ORDER_HISTORY,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delHistoryById(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<OrderHistory> getAllHistories() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_ALL_ORDER_HISTORY, 
                new RowMapper<OrderHistory>(){         
                    @Override  
                    public OrderHistory mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderHistory order_history  = new OrderHistory();
                    	order_history.setId(rs.getString("osa_order_history_id"));
                    	order_history.setOrder_id(rs.getString("osa_order_id"));
                    	order_history.setOrder_number(rs.getString("order_number"));
                    	order_history.setOrder_type_code(rs.getString("order_type_cd"));
                    	order_history.setOrder_type_val(rs.getString("order_type"));
                    	order_history.setDescription(rs.getString("osa_history_desc"));
                    	order_history.setCreated_date(rs.getTimestamp("osa_created"));
                    	order_history.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	order_history.setCreated_by_user_name(rs.getString("created_by_username"));
                        return order_history;  
                    }
        });
	}

	@Override
	public OrderHistory getHistoryById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	public List<OrderHistory> getHistories(OrderHistory order_history,
			String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_ALL_ORDER_HISTORY);
		
		String date_from = order_history.getCreated_date_from();
		String date_to = order_history.getCreated_date_to();
		
		//Filter combination
		if( !"".equals(order_history.getOrder_id()) && order_history.getOrder_id()!=null ){
			queryString.append("and a.osa_order_id='"+order_history.getOrder_id()+"' ");
		}
		if( !"".equals(order_history.getOrder_type_code()) && order_history.getOrder_type_code()!=null ){
			queryString.append("and d.osa_order_type_cd='"+order_history.getOrder_type_code()+"' ");
		}
		if( !"".equals(order_history.getDescription()) && order_history.getDescription()!=null ){
			queryString.append("and a.osa_history_desc like '%"+order_history.getDescription()+"%' ");
		}
		if( !"".equals(order_history.getCreated_by_user_name()) && order_history.getCreated_by_user_name()!=null ){
			queryString.append("and u1.osa_username='"+order_history.getCreated_by_user_name()+"' ");
		}
		if( !"".equals(order_history.getCreated_date_from())&&order_history.getCreated_date_from()!=null ){
			if( !"".equals(order_history.getCreated_date_to())&&order_history.getCreated_date_to()!=null ){
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_from)+"' and a.osa_created<='"+dateFormatMethod(date_to)+"' ");
			}else{
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_to)+"' ");
			}
		}else if( !"".equals(order_history.getCreated_date_to())&&order_history.getCreated_date_to()!=null ){
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
                new RowMapper<OrderHistory>(){         
                    @Override  
                    public OrderHistory mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderHistory order_history  = new OrderHistory();
                    	order_history.setId(rs.getString("osa_order_history_id"));
                    	order_history.setOrder_id(rs.getString("osa_order_id"));
                    	order_history.setOrder_number(rs.getString("order_number"));
                    	order_history.setOrder_type_code(rs.getString("order_type_cd"));
                    	order_history.setOrder_type_val(rs.getString("order_type"));
                    	order_history.setDescription(rs.getString("osa_history_desc"));
                    	order_history.setCreated_date(rs.getTimestamp("osa_created"));
                    	order_history.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	order_history.setCreated_by_user_name(rs.getString("created_by_username"));
                        return order_history;   
                    }
        });
	}

}
