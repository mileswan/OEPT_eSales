package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.ApprovalHistoryDao;
import com.oept.esales.model.ApprovalHistory;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/2/3
 * Description: Approval history DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("approvalHistoryDao")
public class ApprovalHistoryDaoImpl implements ApprovalHistoryDao {

	private static final Logger logger = Logger.getLogger(ApprovalHistoryDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private final static String _INSERT_STRING_APPROVAL_HISTORY="insert into osa_approval_history ("
			+ "osa_order_id,osa_requisition_id,osa_contract_id,"
			+ "osa_product_id,osa_account_id,osa_history_desc,"
			+ "osa_created,osa_created_by) "
			+ "values (?,?,?,?,?,?,?,?)";
	@Override
	public boolean addHistory(ApprovalHistory approval_history)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					approval_history.getOrder_id(),
					approval_history.getRequisition_id(),
					approval_history.getContract_id(),
					approval_history.getProduct_id(),
					approval_history.getAccount_id(),
					approval_history.getDescription(),
					approval_history.getCreated_date(),
					approval_history.getCreated_by_user_id()
					};
			System.out.println("sql:"+_INSERT_STRING_APPROVAL_HISTORY);
			int res = jdbcTemplate.update(_INSERT_STRING_APPROVAL_HISTORY,params);
			System.out.println("res:"+res);
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

	private final static String _SELECT_ALL_APPROVAL_HISTORY="select a.*, u1.osa_username created_by_username, "
			+ "d.osa_order_number order_number,d.osa_order_type_cd order_type_cd,d.osa_order_type order_type,"
			+ "e.osa_req_number,e.osa_req_type_cd,e.osa_req_type_val,"
			+ "f.osa_contract_number,f.osa_type_cd contract_type_cd,f.osa_type_val contract_type_val "
			+ "from osa_approval_history a left outer join "
			+ "osa_order d on a.osa_order_id=d.osa_order_id left outer join "
			+ "osa_requisition e on a.osa_requisition_id=e.osa_requisition_id left outer join "
			+ "osa_contract f on a.osa_contract_id=f.osa_contract_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id where 1=1 ";
	@Override
	public List<ApprovalHistory> getAllHistories() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_ALL_APPROVAL_HISTORY, 
                new RowMapper<ApprovalHistory>(){         
                    @Override  
                    public ApprovalHistory mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	ApprovalHistory approval_history  = new ApprovalHistory();
                    	approval_history.setId(rs.getString("osa_approval_history_id"));
                    	approval_history.setOrder_id(rs.getString("osa_order_id"));
                    	approval_history.setOrder_number(rs.getString("order_number"));
                    	approval_history.setOrder_type_code(rs.getString("order_type_cd"));
                    	approval_history.setOrder_type_val(rs.getString("order_type"));
                    	approval_history.setRequisition_id(rs.getString("osa_requisition_id"));
                    	approval_history.setRequisition_number(rs.getString("osa_req_number"));
                    	approval_history.setRequisition_type_code(rs.getString("osa_req_type_cd"));
                    	approval_history.setRequisition_type_val(rs.getString("osa_req_type_val"));
                    	approval_history.setContract_id(rs.getString("osa_contract_id"));
                    	approval_history.setContract_number(rs.getString("osa_contract_number"));
                    	approval_history.setContract_type_code(rs.getString("contract_type_cd"));
                    	approval_history.setContract_type_val(rs.getString("contract_type_val"));
                    	approval_history.setDescription(rs.getString("osa_history_desc"));
                    	approval_history.setCreated_date(rs.getTimestamp("osa_created"));
                    	approval_history.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	approval_history.setCreated_by_user_name(rs.getString("created_by_username"));
                        return approval_history;  
                    }
        });
	}

	@Override
	public ApprovalHistory getHistoryById(String id) throws Exception {
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
	public List<ApprovalHistory> getHistories(ApprovalHistory approval_history,
			String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_ALL_APPROVAL_HISTORY);
		
		String date_from = approval_history.getCreated_date_from();
		String date_to = approval_history.getCreated_date_to();
		
		//Filter combination
		if( !"".equals(approval_history.getOrder_id()) && approval_history.getOrder_id()!=null ){
			queryString.append("and a.osa_order_id='"+approval_history.getOrder_id()+"' ");
		}
		if( !"".equals(approval_history.getRequisition_id()) && approval_history.getRequisition_id()!=null ){
			queryString.append("and a.osa_requisition_id='"+approval_history.getRequisition_id()+"' ");
		}
		if( !"".equals(approval_history.getContract_id()) && approval_history.getContract_id()!=null ){
			queryString.append("and a.osa_contract_id='"+approval_history.getContract_id()+"' ");
		}
		if( !"".equals(approval_history.getProduct_id()) && approval_history.getProduct_id()!=null ){
			queryString.append("and a.osa_product_id='"+approval_history.getProduct_id()+"' ");
		}
		if( !"".equals(approval_history.getAccount_id()) && approval_history.getAccount_id()!=null ){
			queryString.append("and a.osa_account_id='"+approval_history.getAccount_id()+"' ");
		}
		if( !"".equals(approval_history.getOrder_type_code()) && approval_history.getOrder_type_code()!=null ){
			queryString.append("and d.osa_order_type_cd='"+approval_history.getOrder_type_code()+"' ");
		}
		if( !"".equals(approval_history.getDescription()) && approval_history.getDescription()!=null ){
			queryString.append("and a.osa_history_desc like '%"+approval_history.getDescription()+"%' ");
		}
		if( !"".equals(approval_history.getCreated_by_user_name()) && approval_history.getCreated_by_user_name()!=null ){
			queryString.append("and u1.osa_username='"+approval_history.getCreated_by_user_name()+"' ");
		}
		if( !"".equals(approval_history.getCreated_date_from())&&approval_history.getCreated_date_from()!=null ){
			if( !"".equals(approval_history.getCreated_date_to())&&approval_history.getCreated_date_to()!=null ){
//				queryString.append("and a.osa_created>='"+approval_history.getCreated_date_from()+"' and a.osa_created<='"+approval_history.getCreated_date_to()+"' ");
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_from)+"' and a.osa_created<='"+dateFormatMethod(date_to)+"' ");

			}else{
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_to)+"' ");
			}
		}else if( !"".equals(approval_history.getCreated_date_to())&&approval_history.getCreated_date_to()!=null ){
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
                new RowMapper<ApprovalHistory>(){         
                    @Override  
                    public ApprovalHistory mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	ApprovalHistory approval_history  = new ApprovalHistory();
                    	approval_history.setId(rs.getString("osa_approval_history_id"));
                    	approval_history.setOrder_id(rs.getString("osa_order_id"));
                    	approval_history.setOrder_number(rs.getString("order_number"));
                    	approval_history.setOrder_type_code(rs.getString("order_type_cd"));
                    	approval_history.setOrder_type_val(rs.getString("order_type"));
                    	approval_history.setRequisition_id(rs.getString("osa_requisition_id"));
                    	approval_history.setRequisition_number(rs.getString("osa_req_number"));
                    	approval_history.setRequisition_type_code(rs.getString("osa_req_type_cd"));
                    	approval_history.setRequisition_type_val(rs.getString("osa_req_type_val"));
                    	approval_history.setContract_id(rs.getString("osa_contract_id"));
                    	approval_history.setContract_number(rs.getString("osa_contract_number"));
                    	approval_history.setContract_type_code(rs.getString("contract_type_cd"));
                    	approval_history.setContract_type_val(rs.getString("contract_type_val"));
                    	approval_history.setDescription(rs.getString("osa_history_desc"));
                    	approval_history.setCreated_date(rs.getTimestamp("osa_created"));
                    	approval_history.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	approval_history.setCreated_by_user_name(rs.getString("created_by_username"));
                        return approval_history;  
                    }
        });
	}

}
