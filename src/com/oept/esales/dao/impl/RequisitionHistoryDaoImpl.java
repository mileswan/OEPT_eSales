package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.RequisitionHistoryDao;
import com.oept.esales.model.RequisitionHistory;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/12
 * Description: Requisition history DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("requisitionHistoryDao")
public class RequisitionHistoryDaoImpl implements RequisitionHistoryDao {

	private static final Logger logger = Logger.getLogger(RequisitionHistoryDaoImpl.class);
	
	private final static String _INSERT_STRING_REQ_HISTORY="insert into osa_requisition_history ("
			+ "osa_req_id,osa_history_desc,"
			+ "osa_created,osa_created_by) "
			+ "values (?,?,?,?)";
	private final static String _SELECT_ALL_REQ_HISTORY="select a.*, u1.osa_username created_by_username, "
			+ "d.osa_req_number,d.osa_req_man_number,"
			+ "d.osa_req_type_cd,d.osa_req_type_val "
			+ "from osa_requisition_history a left outer join "
			+ "osa_requisition d on a.osa_req_id=d.osa_requisition_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id where 1=1 ";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean addHistory(RequisitionHistory requisition_history)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					requisition_history.getRequisition_id(),
					requisition_history.getDescription(),
					requisition_history.getCreated_date(),
					requisition_history.getCreated_by_user_id()
					};
			jdbcTemplate.update(_INSERT_STRING_REQ_HISTORY,params);
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
	public List<RequisitionHistory> getAllHistories() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_ALL_REQ_HISTORY, 
                new RowMapper<RequisitionHistory>(){         
                    @Override  
                    public RequisitionHistory mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	RequisitionHistory requisition_history  = new RequisitionHistory();
                    	requisition_history.setId(rs.getString("osa_order_history_id"));
                    	requisition_history.setRequisition_id(rs.getString("osa_req_id"));
                    	requisition_history.setRequisition_number(rs.getString("osa_req_number"));
                    	requisition_history.setRequisition_man_number(rs.getString("osa_req_man_number"));
                    	requisition_history.setRequisition_type_code(rs.getString("osa_req_type_cd"));
                    	requisition_history.setRequisition_type_val(rs.getString("osa_req_type_val"));
                    	requisition_history.setDescription(rs.getString("osa_history_desc"));
                    	requisition_history.setCreated_date(rs.getTimestamp("osa_created"));
                    	requisition_history.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	requisition_history.setCreated_by_user_name(rs.getString("created_by_username"));
                        return requisition_history;  
                    }
        });
	}

	@Override
	public RequisitionHistory getHistoryById(String id) throws Exception {
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
	public List<RequisitionHistory> getHistories(
			RequisitionHistory requisition_history, String start, String limit,
			String sortColumn, String sortDir) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_ALL_REQ_HISTORY);
		
		String date_from = requisition_history.getCreated_date_from();
		String date_to = requisition_history.getCreated_date_to();
		
		
		//Filter combination
		if( !"".equals(requisition_history.getRequisition_id()) && requisition_history.getRequisition_id()!=null ){
			queryString.append("and a.osa_req_id='"+requisition_history.getRequisition_id()+"' ");
		}
		if( !"".equals(requisition_history.getRequisition_type_code()) && requisition_history.getRequisition_type_code()!=null ){
			queryString.append("and d.osa_req_type_cd='"+requisition_history.getRequisition_type_code()+"' ");
		}
		if( !"".equals(requisition_history.getDescription()) && requisition_history.getDescription()!=null ){
			queryString.append("and a.osa_history_desc like '%"+requisition_history.getDescription()+"%' ");
		}
		if( !"".equals(requisition_history.getCreated_by_user_name()) && requisition_history.getCreated_by_user_name()!=null ){
			queryString.append("and u1.osa_username='"+requisition_history.getCreated_by_user_name()+"' ");
		}
		if( !"".equals(requisition_history.getCreated_date_from())&&requisition_history.getCreated_date_from()!=null ){
			if( !"".equals(requisition_history.getCreated_date_to())&&requisition_history.getCreated_date_to()!=null ){
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_from)+"' and a.osa_created<='"+dateFormatMethod(date_to)+"' ");
			}else{
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_to)+"' ");
			}
		}else if( !"".equals(requisition_history.getCreated_date_to())&&requisition_history.getCreated_date_to()!=null ){
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
                new RowMapper<RequisitionHistory>(){         
                    @Override  
                    public RequisitionHistory mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	RequisitionHistory requisition_history  = new RequisitionHistory();
                    	requisition_history.setId(rs.getString("osa_req_history_id"));
                    	requisition_history.setRequisition_id(rs.getString("osa_req_id"));
                    	requisition_history.setRequisition_number(rs.getString("osa_req_number"));
                    	requisition_history.setRequisition_man_number(rs.getString("osa_req_man_number"));
                    	requisition_history.setRequisition_type_code(rs.getString("osa_req_type_cd"));
                    	requisition_history.setRequisition_type_val(rs.getString("osa_req_type_val"));
                    	requisition_history.setDescription(rs.getString("osa_history_desc"));
                    	requisition_history.setCreated_date(rs.getTimestamp("osa_created"));
                    	requisition_history.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	requisition_history.setCreated_by_user_name(rs.getString("created_by_username"));
                        return requisition_history; 
                    }
        });
	}

}
