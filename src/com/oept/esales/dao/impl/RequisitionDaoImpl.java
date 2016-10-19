package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.RequisitionDao;
import com.oept.esales.model.OrderReqProdCounts;
import com.oept.esales.model.RequisitionFlat;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/8
 * Description: Requisition DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("requisitionDao")
public class RequisitionDaoImpl implements RequisitionDao {
	
	private static final Logger logger = Logger.getLogger(RequisitionDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private final static String _INSERT_STRING_REQ="insert into osa_requisition (osa_req_number,osa_req_man_number,osa_req_type_cd,"
			+ "osa_req_type_val,osa_req_subtype_cd,osa_req_subtype_val,osa_req_date,osa_req_owner_id,"
			+ "osa_currency_cd,osa_currency_val,"
			+ "osa_req_delivery_date,osa_req_receive_date,"
			+ "osa_created,osa_created_by,osa_update,osa_update_by,osa_req_status_cd,osa_req_status_val,"
			+ "osa_deliver_wh_id,osa_receive_wh_id,osa_req_comm,osa_req_order_id,osa_req_accnt_id) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String _SELECT_REQ_ID="select osa_requisition_id from osa_requisition where osa_req_number=?";
	@Override
	public String addRequisition(RequisitionFlat requisition) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					requisition.getRequisition_number(),
					requisition.getRequisition_manual_number(),
					requisition.getRequisition_type_cd(),
					requisition.getRequisition_type(),
					requisition.getRequisition_subtype_cd(),
					requisition.getRequisition_subtype_val(),
					requisition.getRequisition_date(),
					requisition.getOwner_id(),
					requisition.getCurrency_cd(),
					requisition.getCurrency_val(),
					requisition.getDelivery_date(),
					requisition.getReceive_date(),
					requisition.getCreated_date(),
					requisition.getCreated_by_id(),
					requisition.getUpdate_date(),
					requisition.getUpdate_by_id(),
					requisition.getStatus_code(),
					requisition.getStatus_value(),
					requisition.getDelivery_wh_id(),
					requisition.getReceive_wh_id(),
					requisition.getRequisition_comment(),
					requisition.getOrder_id(),
					requisition.getAccount_id()
					};
			jdbcTemplate.update(_INSERT_STRING_REQ,params);
			
			params = new Object[]{requisition.getRequisition_number()};
			RequisitionFlat new_req = jdbcTemplate.queryForObject(_SELECT_REQ_ID, params,
					new RowMapper<RequisitionFlat>(){         
	        		@Override  
	        		public RequisitionFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
	        			RequisitionFlat req  = new RequisitionFlat(); 
	        			req.setRequisition_id(rs.getString("osa_requisition_id"));
	        			return req;
	        		}
			});
			return new_req.getRequisition_id();
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _UPDATE_STRING_REQ="UPDATE osa_requisition SET osa_req_man_number=?,"
			+ "osa_req_status_cd=?, osa_req_status_val=?, "
			+ "osa_update=?,osa_update_by=?,osa_req_owner_id=?,"
			+ "osa_req_base_amt=?,osa_req_due_amt=?,osa_req_delivery_date=?,osa_req_receive_date=?,"
			+ "osa_req_comm=?,osa_deliver_wh_id=?,osa_receive_wh_id=?,osa_req_order_id=?,"
			+ "osa_req_type_cd=?,osa_req_type_val=?,osa_req_subtype_cd=?,osa_req_subtype_val=?,osa_req_accnt_id=? "
			+ "WHERE osa_requisition_id = ?";
	@Override
	public boolean updateRequisition(RequisitionFlat requisition)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					requisition.getRequisition_manual_number(),
					requisition.getStatus_code(),
					requisition.getStatus_value(),
					requisition.getUpdate_date(),
					requisition.getUpdate_by_id(),
					requisition.getOwner_id(),
					requisition.getBase_amount(),
					requisition.getDue_amount(),
					requisition.getDelivery_date(),
					requisition.getReceive_date(),
					requisition.getRequisition_comment(),
					requisition.getDelivery_wh_id(),
					requisition.getReceive_wh_id(),
					requisition.getOrder_id(),
					requisition.getRequisition_type_cd(),
					requisition.getRequisition_type(),
					requisition.getRequisition_subtype_cd(),
					requisition.getRequisition_subtype_val(),
					requisition.getAccount_id(),
					requisition.getRequisition_id()
					 };
			jdbcTemplate.update(_UPDATE_STRING_REQ,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _UPDATE_STRING_STATUS="UPDATE osa_requisition SET osa_req_status_cd=?, osa_req_status_val=?,"
			+ "osa_update=?,osa_update_by=? "
			+ "WHERE osa_requisition_id = ?";
	@Override
	public boolean updateRequisitionStatus(RequisitionFlat requisition)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					requisition.getStatus_code(),
					requisition.getStatus_value(),
					requisition.getUpdate_date(),
					requisition.getUpdate_by_id(),
					requisition.getRequisition_id()};
			jdbcTemplate.update(_UPDATE_STRING_STATUS,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _SELECT_STRING_REQ_LIST="select a.*,u1.osa_username createdBy,"
			+ "c.osa_addr_name ship_address,u.osa_username owner_name,e.osa_order_number,e.osa_order_man_number,"
			+ "d2.osa_accnt_name supplier_name,d1.osa_accnt_name account_name,w1.osa_wh_name receive_warehouse_name,"
			+ "w2.osa_wh_name delivery_warehouse_name,u2.osa_username updateBy from osa_requisition a inner join "
			+ "osa_user u on a.osa_req_owner_id=u.osa_user_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_update_by=u2.osa_user_id left outer join "
			+ "osa_address c on a.osa_req_shipaddr_id=c.osa_addr_id left outer join "
			+ "osa_account d1 on a.osa_req_accnt_id = d1.osa_account_id left outer join "
			+ "osa_account d2 on a.osa_req_supplier_id = d2.osa_account_id left outer join "
			+ "osa_warehouse w1 on a.osa_receive_wh_id = w1.osa_warehouse_id left outer join "
			+ "osa_warehouse w2 on a.osa_deliver_wh_id = w2.osa_warehouse_id left outer join "
			+ "osa_order e on a.osa_req_order_id = e.osa_order_id";
	@Override
	public List<RequisitionFlat> getAllRequisitions() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_STRING_REQ_LIST, 
                new RowMapper<RequisitionFlat>(){         
                    @Override  
                    public RequisitionFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	RequisitionFlat requisition  = new RequisitionFlat();
                    	requisition.setRequisition_id(rs.getString("osa_requisition_id"));
                    	requisition.setRequisition_number(rs.getString("osa_req_number"));
                    	requisition.setRequisition_manual_number(rs.getString("osa_req_man_number"));
                    	requisition.setRequisition_type_cd(rs.getString("osa_req_type_cd"));
                    	requisition.setRequisition_type(rs.getString("osa_req_type_val"));
                    	requisition.setRequisition_subtype_cd(rs.getString("osa_req_subtype_cd"));
                    	requisition.setRequisition_subtype_val(rs.getString("osa_req_subtype_val"));
                    	requisition.setStatus_code(rs.getString("osa_req_status_cd"));
                    	requisition.setStatus_value(rs.getString("osa_req_status_val"));
                    	requisition.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	requisition.setReceive_wh_name(rs.getString("receive_warehouse_name"));
                    	requisition.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	requisition.setDelivery_wh_name(rs.getString("delivery_warehouse_name"));
                    	requisition.setAccount_id(rs.getString("osa_req_accnt_id"));
                    	requisition.setAccount_name(rs.getString("account_name"));
                    	requisition.setRequisition_comment(rs.getString("osa_req_comm"));
                    	requisition.setBase_amount(rs.getDouble("osa_req_base_amt"));
                    	requisition.setDue_amount(rs.getDouble("osa_req_due_amt"));
                    	requisition.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	requisition.setCurrency_val(rs.getString("osa_currency_val"));
                    	requisition.setOwner_id(rs.getString("osa_req_owner_id"));
                    	requisition.setOwner_name(rs.getString("owner_name"));
                    	requisition.setCreated_date(rs.getTimestamp("osa_created"));
                    	requisition.setCreated_by_id(rs.getString("osa_created_by"));
                    	requisition.setCreated_by_name(rs.getString("createdBy"));
                    	requisition.setUpdate_date(rs.getTimestamp("osa_update"));
                    	requisition.setUpdate_by_id(rs.getString("osa_update_by"));
                    	requisition.setUpdate_by_name(rs.getString("updateBy"));
                    	requisition.setOwner_id(rs.getString("osa_req_owner_id"));
                    	requisition.setOwner_name(rs.getString("owner_name"));
                    	requisition.setOrder_id(rs.getString("osa_req_order_id"));
                    	requisition.setOrder_number(rs.getString("osa_order_number"));
                    	requisition.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	if(rs.getTimestamp("osa_req_date")!=null && !"".equals(rs.getTimestamp("osa_req_date"))){
                    		requisition.setRequisition_date(rs.getTimestamp("osa_req_date"));
                    	}
                    	if(rs.getDate("osa_req_delivery_date")!=null && !"".equals(rs.getDate("osa_req_delivery_date"))){
                    		requisition.setDelivery_date(rs.getDate("osa_req_delivery_date"));
                    	}
                    	if(rs.getDate("osa_req_receive_date")!=null && !"".equals(rs.getDate("osa_req_receive_date"))){
                    		requisition.setReceive_date(rs.getDate("osa_req_receive_date"));
                    	}
                        return requisition;  
                    }  
        });
	}

	@Override
	public RequisitionFlat getRequisitionById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_REQ_LIST);
		queryString.append(" where osa_requisition_id=? ");
		
		return jdbcTemplate.queryForObject(queryString.toString(), params,
                new RowMapper<RequisitionFlat>(){         
                    @Override  
                    public RequisitionFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	RequisitionFlat requisition  = new RequisitionFlat();
                    	requisition.setRequisition_id(rs.getString("osa_requisition_id"));
                    	requisition.setRequisition_number(rs.getString("osa_req_number"));
                    	requisition.setRequisition_manual_number(rs.getString("osa_req_man_number"));
                    	requisition.setRequisition_type_cd(rs.getString("osa_req_type_cd"));
                    	requisition.setRequisition_type(rs.getString("osa_req_type_val"));
                    	requisition.setRequisition_subtype_cd(rs.getString("osa_req_subtype_cd"));
                    	requisition.setRequisition_subtype_val(rs.getString("osa_req_subtype_val"));
                    	requisition.setStatus_code(rs.getString("osa_req_status_cd"));
                    	requisition.setStatus_value(rs.getString("osa_req_status_val"));
                    	requisition.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	requisition.setReceive_wh_name(rs.getString("receive_warehouse_name"));
                    	requisition.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	requisition.setDelivery_wh_name(rs.getString("delivery_warehouse_name"));
                    	requisition.setAccount_id(rs.getString("osa_req_accnt_id"));
                    	requisition.setAccount_name(rs.getString("account_name"));
                    	requisition.setRequisition_comment(rs.getString("osa_req_comm"));
                    	requisition.setBase_amount(rs.getDouble("osa_req_base_amt"));
                    	requisition.setDue_amount(rs.getDouble("osa_req_due_amt"));
                    	requisition.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	requisition.setCurrency_val(rs.getString("osa_currency_val"));
                    	requisition.setOwner_id(rs.getString("osa_req_owner_id"));
                    	requisition.setOwner_name(rs.getString("owner_name"));
                    	requisition.setCreated_date(rs.getTimestamp("osa_created"));
                    	requisition.setCreated_by_id(rs.getString("osa_created_by"));
                    	requisition.setCreated_by_name(rs.getString("createdBy"));
                    	requisition.setUpdate_date(rs.getTimestamp("osa_update"));
                    	requisition.setUpdate_by_id(rs.getString("osa_update_by"));
                    	requisition.setUpdate_by_name(rs.getString("updateBy"));
                    	requisition.setOwner_id(rs.getString("osa_req_owner_id"));
                    	requisition.setOwner_name(rs.getString("owner_name"));
                    	requisition.setOrder_id(rs.getString("osa_req_order_id"));
                    	requisition.setOrder_number(rs.getString("osa_order_number"));
                    	requisition.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	if(rs.getTimestamp("osa_req_date")!=null && !"".equals(rs.getTimestamp("osa_req_date"))){
                    		requisition.setRequisition_date(rs.getTimestamp("osa_req_date"));
                    	}
                    	if(rs.getDate("osa_req_delivery_date")!=null && !"".equals(rs.getDate("osa_req_delivery_date"))){
                    		requisition.setDelivery_date(rs.getDate("osa_req_delivery_date"));
                    	}
                    	if(rs.getDate("osa_req_receive_date")!=null && !"".equals(rs.getDate("osa_req_receive_date"))){
                    		requisition.setReceive_date(rs.getDate("osa_req_receive_date"));
                    	}
                        return requisition; 
                    }  
        });
	}

	@Override
	public List<RequisitionFlat> getRequisitions(RequisitionFlat requisition,
			String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_REQ_LIST);
		queryString.append(" where 1=1");
		
		//Filter combination
		if( !"".equals(requisition.getRequisition_number()) && requisition.getRequisition_number()!=null  ){
			queryString.append(" and osa_req_number='"+requisition.getRequisition_number()+"' ");
		}
		if( !"".equals(requisition.getOrder_id())&&requisition.getOrder_id()!=null  ){
			queryString.append(" and osa_req_order_id='"+requisition.getOrder_id()+"' ");
		}
		if( !"".equals(requisition.getRequisition_type_cd())&&requisition.getRequisition_type_cd()!=null  ){
			queryString.append(" and osa_req_type_cd='"+requisition.getRequisition_type_cd()+"' ");
		}
		if( !"".equals(requisition.getOwner_id())&&requisition.getOwner_id()!=null ){
			queryString.append(" and osa_req_owner_id="+requisition.getOwner_id()+" ");
		}
//		if( !"".equals(order.getStatus_code())&&order.getStatus_code()!=null ){
//			queryString.append(" and osa_order_status_cd='"+order.getStatus_code()+"' ");
//		}
//		if( !"".equals(order.getShip_addr_name())&&order.getShip_addr_name()!=null ){
//			queryString.append(" and ship_address='"+order.getShip_addr_name()+"' ");
//		}
//		if( !"".equals(order.getCreated_by_name())&&order.getCreated_by_name()!=null ){
//			queryString.append(" and createdBy='"+order.getCreated_by_name()+"' ");
//		}
//		if( !"".equals(order.getOrder_date_from())&&order.getOrder_date_from()!=null ){
//			if( !"".equals(order.getOrder_date_to())&&order.getOrder_date_to()!=null ){
//				queryString.append(" and osa_order_date>='"+order.getOrder_date_from()+"' and osa_order_date<='"+order.getOrder_date_to()+"' ");
//			}else{
//				queryString.append(" and osa_order_date>='"+order.getOrder_date_from()+"' ");
//			}
//		}else if( !"".equals(order.getOrder_date_to())&&order.getOrder_date_to()!=null ){
//			queryString.append(" and osa_order_date<='"+order.getOrder_date_to()+"' ");
//		}
//		if( !"".equals(order.getOrder_base_price_from())&&order.getOrder_base_price_from()!=null ){
//			if( !"".equals(order.getOrder_base_price_to())&&order.getOrder_base_price_to()!=null ){
//				queryString.append(" and osa_order_base_amt>="+order.getOrder_base_price_from()+" and osa_order_base_amt<="+order.getOrder_base_price_to()+" ");
//			}else{
//				queryString.append(" and osa_order_base_amt>="+order.getOrder_base_price_from()+" ");
//			}
//		}else if( !"".equals(order.getOrder_base_price_to())&&order.getOrder_base_price_to()!=null ){
//			queryString.append(" and osa_order_base_amt<="+order.getOrder_base_price_to()+" ");
//		}
//		if( !"".equals(order.getOrder_purchase_price_from())&&order.getOrder_purchase_price_from()!=null ){
//			if( !"".equals(order.getOrder_purchase_price_to())&&order.getOrder_purchase_price_to()!=null ){
//				queryString.append(" and osa_order_due_amt>="+order.getOrder_purchase_price_from()+" and osa_order_due_amt<="+order.getOrder_purchase_price_to()+" ");
//			}else{
//				queryString.append(" and osa_order_due_amt>="+order.getOrder_purchase_price_from()+" ");
//			}
//		}else if( !"".equals(order.getOrder_base_price_to())&&order.getOrder_base_price_to()!=null ){
//			queryString.append(" and osa_order_due_amt<="+order.getOrder_purchase_price_to()+" ");
//		}
		
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
                new RowMapper<RequisitionFlat>(){         
                    @Override  
                    public RequisitionFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	RequisitionFlat requisition  = new RequisitionFlat();
                    	requisition.setRequisition_id(rs.getString("osa_requisition_id"));
                    	requisition.setRequisition_number(rs.getString("osa_req_number"));
                    	requisition.setRequisition_manual_number(rs.getString("osa_req_man_number"));
                    	requisition.setRequisition_type_cd(rs.getString("osa_req_type_cd"));
                    	requisition.setRequisition_type(rs.getString("osa_req_type_val"));
                    	requisition.setRequisition_subtype_cd(rs.getString("osa_req_subtype_cd"));
                    	requisition.setRequisition_subtype_val(rs.getString("osa_req_subtype_val"));
                    	requisition.setStatus_code(rs.getString("osa_req_status_cd"));
                    	requisition.setStatus_value(rs.getString("osa_req_status_val"));
                    	requisition.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	requisition.setReceive_wh_name(rs.getString("receive_warehouse_name"));
                    	requisition.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	requisition.setDelivery_wh_name(rs.getString("delivery_warehouse_name"));
                    	requisition.setAccount_id(rs.getString("osa_req_accnt_id"));
                    	requisition.setAccount_name(rs.getString("account_name"));
                    	requisition.setRequisition_comment(rs.getString("osa_req_comm"));
                    	requisition.setBase_amount(rs.getDouble("osa_req_base_amt"));
                    	requisition.setDue_amount(rs.getDouble("osa_req_due_amt"));
                    	requisition.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	requisition.setCurrency_val(rs.getString("osa_currency_val"));
                    	requisition.setOwner_id(rs.getString("osa_req_owner_id"));
                    	requisition.setOwner_name(rs.getString("owner_name"));
                    	requisition.setCreated_date(rs.getTimestamp("osa_created"));
                    	requisition.setCreated_by_id(rs.getString("osa_created_by"));
                    	requisition.setCreated_by_name(rs.getString("createdBy"));
                    	requisition.setUpdate_date(rs.getTimestamp("osa_update"));
                    	requisition.setUpdate_by_id(rs.getString("osa_update_by"));
                    	requisition.setUpdate_by_name(rs.getString("updateBy"));
                    	requisition.setOwner_id(rs.getString("osa_req_owner_id"));
                    	requisition.setOwner_name(rs.getString("owner_name"));
                    	requisition.setOrder_id(rs.getString("osa_req_order_id"));
                    	requisition.setOrder_number(rs.getString("osa_order_number"));
                    	requisition.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	if(rs.getTimestamp("osa_req_date")!=null && !"".equals(rs.getTimestamp("osa_req_date"))){
                    		requisition.setRequisition_date(rs.getTimestamp("osa_req_date"));
                    	}
                    	if(rs.getDate("osa_req_delivery_date")!=null && !"".equals(rs.getDate("osa_req_delivery_date"))){
                    		requisition.setDelivery_date(rs.getDate("osa_req_delivery_date"));
                    	}
                    	if(rs.getDate("osa_req_receive_date")!=null && !"".equals(rs.getDate("osa_req_receive_date"))){
                    		requisition.setReceive_date(rs.getDate("osa_req_receive_date"));
                    	}
                        return requisition;  
                    }  
        });
	}

	private final static String _INSERT_STRING_ITEM="insert into osa_requisition_item (osa_req_id,osa_req_prod_id,osa_req_prod_addr_id,"
			+ "osa_item_status_code,osa_item_status_val,osa_req_contract_id,"
			+ "osa_prod_quantity,osa_prod_base_price,osa_prod_due_price,osa_req_due_amt,osa_req_discount_amt,osa_req_tax_amt,"
			+ "osa_req_base_amt,osa_req_tax_ratio,osa_req_discount_ratio,osa_req_wh_id,"
			+ "osa_currency_cd,osa_currency_val,osa_desc,"
			+ "osa_item_created,osa_item_created_by,osa_item_update,osa_item_update_by) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	@Override
	public boolean addRequisitionItem(RequisitionFlat requisition)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					requisition.getRequisition_id(),
					requisition.getItem_prod_id(),
					requisition.getItem_prod_address_id(),
					requisition.getItem_status_code(),
					requisition.getItem_status_value(),
					requisition.getItem_contract_id(),
					requisition.getItem_quantity(),
					requisition.getItem_base_price(),
					requisition.getItem_due_price(),
					requisition.getItem_due_amount(),
					requisition.getItem_discount_amount(),
					requisition.getItem_tax_amount(),
					requisition.getItem_base_amount(),
					requisition.getItem_tax_ratio(),
					requisition.getItem_discount_ratio(),
					requisition.getItem_warehouse_id(),
					requisition.getCurrency_cd(),
					requisition.getCurrency_val(),
					requisition.getItem_comment(),
					requisition.getItem_created_date(),
					requisition.getItem_created_by_id(),
					requisition.getItem_update_date(),
					requisition.getItem_update_by_id()
					};
			jdbcTemplate.update(_INSERT_STRING_ITEM,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _DELETE_REQ_ITEM="delete from osa_requisition_item where osa_req_item_id=?";
	@Override
	public boolean delRequisitionItemById(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {id};
			jdbcTemplate.update(_DELETE_REQ_ITEM,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _UPDATE_STRING_REQ_ITEM="UPDATE osa_requisition_item SET osa_req_prod_id=?, osa_prod_quantity=?, osa_req_contract_id=?,"
			+ "osa_desc=?,"
			+ "osa_prod_base_price=?,osa_req_base_amt=?,osa_prod_due_price=?,osa_req_due_amt=?,osa_req_wh_id=?,osa_item_update=?,osa_item_update_by=? "
			+ "WHERE osa_req_item_id = ?";
	@Override
	public boolean updateRequisitionItem(RequisitionFlat requisition)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					requisition.getItem_prod_id(),
					requisition.getItem_quantity(),
					requisition.getItem_contract_id(),
					requisition.getItem_comment(),
					requisition.getItem_base_price(),
					requisition.getItem_base_amount(),
					requisition.getItem_due_price(),
					requisition.getItem_due_amount(),
					requisition.getItem_warehouse_id(),
					requisition.getItem_update_date(),
					requisition.getItem_update_by_id(),
					requisition.getItem_id()
					};
			jdbcTemplate.update(_UPDATE_STRING_REQ_ITEM,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	private final static String _SELECT_STRING_REQ_ITEMS="select a.*, p.osa_prod_num,p.osa_prod_name,p.osa_prod_model,c.osa_wh_name,c2.osa_contract_number,"
			+ "u1.osa_username createdBy, u2.osa_username updateBy from osa_requisition_item a inner join "
			+ "osa_requisition b on a.osa_req_id=b.osa_requisition_id left outer join "
			+ "osa_warehouse c on a.osa_req_wh_id=c.osa_warehouse_id left outer join "
			+ "osa_contract c2 on a.osa_req_contract_id = c2.osa_contract_id left outer join "
			+ "osa_product p on a.osa_req_prod_id=p.osa_prod_id inner join "
			+ "osa_user u1 on a.osa_item_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_item_update_by=u2.osa_user_id where a.osa_req_id=?";

	@Override
	public List<RequisitionFlat> getItemsByReqId(String requisitionId)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {requisitionId};
		return jdbcTemplate.query(_SELECT_STRING_REQ_ITEMS, params,
                new RowMapper<RequisitionFlat>(){         
                    @Override  
                    public RequisitionFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	RequisitionFlat requisition  = new RequisitionFlat();  
                    	requisition.setItem_id(rs.getString("osa_req_item_id"));
                    	requisition.setItem_prod_id(rs.getString("osa_req_prod_id"));
                    	requisition.setItem_prod_number(rs.getString("osa_prod_num"));
                    	requisition.setItem_prod_name(rs.getString("osa_prod_name"));
                    	requisition.setItem_prod_model(rs.getString("osa_prod_model"));
                    	requisition.setItem_warehouse_id(rs.getString("osa_req_wh_id"));
                    	requisition.setItem_warehouse_name(rs.getString("osa_wh_name"));
                    	requisition.setItem_contract_id(rs.getString("osa_req_contract_id"));
                    	requisition.setItem_contract_number(rs.getString("osa_contract_number"));
                    	requisition.setItem_status_code(rs.getString("osa_item_status_code"));
                    	requisition.setItem_status_value(rs.getString("osa_item_status_val"));
                    	requisition.setItem_quantity(rs.getInt("osa_prod_quantity"));
                    	requisition.setItem_comment(rs.getString("osa_desc"));
                    	requisition.setItem_base_price(rs.getDouble("osa_prod_base_price"));
                    	requisition.setItem_due_price(rs.getDouble("osa_prod_due_price"));
                    	requisition.setItem_base_amount(rs.getDouble("osa_req_base_amt"));
                    	requisition.setItem_due_amount(rs.getDouble("osa_req_due_amt"));
                    	requisition.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	requisition.setCurrency_val(rs.getString("osa_currency_val"));
                    	requisition.setItem_created_by_id(rs.getString("osa_item_created_by"));
                    	requisition.setItem_created_date(rs.getTimestamp("osa_item_created"));
                    	requisition.setItem_created_by_name(rs.getString("createdBy"));
                    	requisition.setItem_update_by_id(rs.getString("osa_item_update_by"));
                    	requisition.setItem_update_date(rs.getTimestamp("osa_item_update"));
                    	requisition.setItem_update_by_name(rs.getString("updateBy"));
                        return requisition;  
                    }  
        });
	}

	@Override
	public boolean updateRequisitionAmounts(RequisitionFlat requisition)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRequisitionShipAddr(RequisitionFlat requisition)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRequisitionBillAddr(RequisitionFlat requisition)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	private final static String _SELECT_STRING_REQ_LIST_FOR_APPROVER="select a.*,u1.osa_username createdBy,"
			+ "c.osa_addr_name ship_address,u.osa_username owner_name,e.osa_order_number,e.osa_order_man_number,"
			+ "d2.osa_accnt_name supplier_name,d1.osa_accnt_name account_name,w1.osa_wh_name receive_warehouse_name,"
			+ "w2.osa_wh_name delivery_warehouse_name,u2.osa_username updateBy from osa_requisition a inner join "
			+ "osa_user u on a.osa_req_owner_id=u.osa_user_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_update_by=u2.osa_user_id left outer join "
			+ "osa_address c on a.osa_req_shipaddr_id=c.osa_addr_id left outer join "
			+ "osa_account d1 on a.osa_req_accnt_id = d1.osa_account_id left outer join "
			+ "osa_account d2 on a.osa_req_supplier_id = d2.osa_account_id left outer join "
			+ "osa_warehouse w1 on a.osa_receive_wh_id = w1.osa_warehouse_id left outer join "
			+ "osa_warehouse w2 on a.osa_deliver_wh_id = w2.osa_warehouse_id left outer join "
			+ "osa_order e on a.osa_req_order_id = e.osa_order_id inner join "
			+ "osa_approval_step r1 on a.osa_requisition_id = r1.osa_requisition_id inner join "
			+ "osa_approval_detail r2 on r1.osa_approval_step_id = r2.osa_approval_step_id "
			+ "where r1.osa_process_flg=TRUE and r2.osa_approval_status_cd='pending' and r2.osa_to_approve=?";
	@Override
	public List<RequisitionFlat> getRequisitionsForApprover(RequisitionFlat requisition,String approver_id,
			String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {approver_id};
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_REQ_LIST_FOR_APPROVER);
		
		if( !"".equals(requisition.getRequisition_type_cd())&&requisition.getRequisition_type_cd()!=null  ){
			queryString.append(" and osa_req_type_cd='"+requisition.getRequisition_type_cd()+"' ");
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
                new RowMapper<RequisitionFlat>(){         
                    @Override  
                    public RequisitionFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	RequisitionFlat requisition  = new RequisitionFlat();
                    	requisition.setRequisition_id(rs.getString("osa_requisition_id"));
                    	requisition.setRequisition_number(rs.getString("osa_req_number"));
                    	requisition.setRequisition_manual_number(rs.getString("osa_req_man_number"));
                    	requisition.setRequisition_type_cd(rs.getString("osa_req_type_cd"));
                    	requisition.setRequisition_type(rs.getString("osa_req_type_val"));
                    	requisition.setRequisition_subtype_cd(rs.getString("osa_req_subtype_cd"));
                    	requisition.setRequisition_subtype_val(rs.getString("osa_req_subtype_val"));
                    	requisition.setStatus_code(rs.getString("osa_req_status_cd"));
                    	requisition.setStatus_value(rs.getString("osa_req_status_val"));
                    	requisition.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	requisition.setReceive_wh_name(rs.getString("receive_warehouse_name"));
                    	requisition.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	requisition.setDelivery_wh_name(rs.getString("delivery_warehouse_name"));
                    	requisition.setAccount_id(rs.getString("osa_req_accnt_id"));
                    	requisition.setAccount_name(rs.getString("account_name"));
                    	requisition.setRequisition_comment(rs.getString("osa_req_comm"));
                    	requisition.setBase_amount(rs.getDouble("osa_req_base_amt"));
                    	requisition.setDue_amount(rs.getDouble("osa_req_due_amt"));
                    	requisition.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	requisition.setCurrency_val(rs.getString("osa_currency_val"));
                    	requisition.setOwner_id(rs.getString("osa_req_owner_id"));
                    	requisition.setOwner_name(rs.getString("owner_name"));
                    	requisition.setCreated_date(rs.getTimestamp("osa_created"));
                    	requisition.setCreated_by_id(rs.getString("osa_created_by"));
                    	requisition.setCreated_by_name(rs.getString("createdBy"));
                    	requisition.setUpdate_date(rs.getTimestamp("osa_update"));
                    	requisition.setUpdate_by_id(rs.getString("osa_update_by"));
                    	requisition.setUpdate_by_name(rs.getString("updateBy"));
                    	requisition.setOwner_id(rs.getString("osa_req_owner_id"));
                    	requisition.setOwner_name(rs.getString("owner_name"));
                    	requisition.setOrder_id(rs.getString("osa_req_order_id"));
                    	requisition.setOrder_number(rs.getString("osa_order_number"));
                    	requisition.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	if(rs.getTimestamp("osa_req_date")!=null && !"".equals(rs.getTimestamp("osa_req_date"))){
                    		requisition.setRequisition_date(rs.getTimestamp("osa_req_date"));
                    	}
                    	if(rs.getDate("osa_req_delivery_date")!=null && !"".equals(rs.getDate("osa_req_delivery_date"))){
                    		requisition.setDelivery_date(rs.getDate("osa_req_delivery_date"));
                    	}
                    	if(rs.getDate("osa_req_receive_date")!=null && !"".equals(rs.getDate("osa_req_receive_date"))){
                    		requisition.setReceive_date(rs.getDate("osa_req_receive_date"));
                    	}
                        return requisition;  
                    }  
        });
	}

	private final static String _SELECT_STRING_REQ_ITEM="select a.*, p.osa_prod_name,c.osa_wh_name,c2.osa_contract_number,"
			+ "u1.osa_username createdBy, u2.osa_username updateBy from osa_requisition_item a inner join "
			+ "osa_requisition b on a.osa_req_id=b.osa_requisition_id left outer join "
			+ "osa_warehouse c on a.osa_req_wh_id=c.osa_warehouse_id left outer join "
			+ "osa_contract c2 on a.osa_req_contract_id = c2.osa_contract_id left outer join "
			+ "osa_product p on a.osa_req_prod_id=p.osa_prod_id inner join "
			+ "osa_user u1 on a.osa_item_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_item_update_by=u2.osa_user_id where a.osa_req_item_id=?";
	@Override
	public RequisitionFlat getItemsById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
		return jdbcTemplate.queryForObject(_SELECT_STRING_REQ_ITEM, params,
                new RowMapper<RequisitionFlat>(){         
                    @Override  
                    public RequisitionFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	RequisitionFlat requisition  = new RequisitionFlat();  
                    	requisition.setItem_id(rs.getString("osa_req_item_id"));
                    	requisition.setItem_prod_id(rs.getString("osa_req_prod_id"));
                    	requisition.setItem_prod_name(rs.getString("osa_prod_name"));
                    	requisition.setItem_warehouse_id(rs.getString("osa_req_wh_id"));
                    	requisition.setItem_warehouse_name(rs.getString("osa_wh_name"));
                    	requisition.setItem_contract_id(rs.getString("osa_req_contract_id"));
                    	requisition.setItem_contract_number(rs.getString("osa_contract_number"));
                    	requisition.setItem_status_code(rs.getString("osa_item_status_code"));
                    	requisition.setItem_status_value(rs.getString("osa_item_status_val"));
                    	requisition.setItem_quantity(rs.getInt("osa_prod_quantity"));
                    	requisition.setItem_base_price(rs.getDouble("osa_prod_base_price"));
                    	requisition.setItem_due_price(rs.getDouble("osa_prod_due_price"));
                    	requisition.setItem_base_amount(rs.getDouble("osa_req_base_amt"));
                    	requisition.setItem_due_amount(rs.getDouble("osa_req_due_amt"));
                    	requisition.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	requisition.setCurrency_val(rs.getString("osa_currency_val"));
                    	requisition.setItem_created_by_id(rs.getString("osa_item_created_by"));
                    	requisition.setItem_created_date(rs.getTimestamp("osa_item_created"));
                    	requisition.setItem_created_by_name(rs.getString("createdBy"));
                    	requisition.setItem_update_by_id(rs.getString("osa_item_update_by"));
                    	requisition.setItem_update_date(rs.getTimestamp("osa_item_update"));
                    	requisition.setItem_update_by_name(rs.getString("updateBy"));
                        return requisition;  
                    }  
        });
	}

	private final static String _SELECT_RECENT_REQS="select a.created,b.* from (select osa_req_id,max(osa_created) created "
			+ "from osa_requisition_history where osa_created_by=? GROUP BY osa_req_id order by created desc) a "
			+ "INNER JOIN osa_requisition b on a.osa_req_id=b.osa_requisition_id LIMIT 15";
	@Override
	public List<RequisitionFlat> getRecentRequisitions(String userid)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {userid};
		return jdbcTemplate.query(_SELECT_RECENT_REQS, params,
                new RowMapper<RequisitionFlat>(){         
                    @Override  
                    public RequisitionFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	RequisitionFlat requisition  = new RequisitionFlat();  
                    	requisition.setRequisition_id(rs.getString("osa_requisition_id"));
                    	requisition.setRequisition_number(rs.getString("osa_req_number"));
                    	requisition.setRequisition_manual_number(rs.getString("osa_req_man_number"));
                    	requisition.setRequisition_type_cd(rs.getString("osa_req_type_cd"));
                    	requisition.setRequisition_type(rs.getString("osa_req_type_val"));
                    	requisition.setRequisition_subtype_cd(rs.getString("osa_req_subtype_cd"));
                    	requisition.setRequisition_subtype_val(rs.getString("osa_req_subtype_val"));
                    	requisition.setUpdate_date(rs.getTimestamp("created"));
                        return requisition;  
                    }  
        });
	}

	private final static String _SELECT_PROD_COUNTS="select osa_req_id,osa_req_prod_id,sum(osa_prod_quantity) osa_prod_quantity "
			+ "from osa_requisition_item group by osa_req_id,osa_req_prod_id having osa_req_id=? ";
	@Override
	public List<OrderReqProdCounts> getReqProdCounts(String req_id)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {req_id};
		
		return jdbcTemplate.query(_SELECT_PROD_COUNTS, params,
                new RowMapper<OrderReqProdCounts>(){         
                    @Override  
                    public OrderReqProdCounts mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderReqProdCounts prod_counts  = new OrderReqProdCounts();  
                    	prod_counts.setRequisition_id(rs.getString("osa_req_id"));
                    	prod_counts.setProduct_id(rs.getString("osa_req_prod_id"));
                    	prod_counts.setQuantity(rs.getInt("osa_prod_quantity"));
                        return prod_counts;  
                    }  
        });
	}

	@Override
	public List<OrderReqProdCounts> getReqProdCounts(String req_id,
			String prod_id) throws Exception {
		// TODO Auto-generated method stub
		String queryString = _SELECT_PROD_COUNTS + "and osa_req_prod_id=?";
		Object[] params = new Object[] {req_id,prod_id};
		
		return jdbcTemplate.query(queryString, params,
                new RowMapper<OrderReqProdCounts>(){         
                    @Override  
                    public OrderReqProdCounts mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderReqProdCounts prod_counts  = new OrderReqProdCounts();  
                    	prod_counts.setRequisition_id(rs.getString("osa_req_id"));
                    	prod_counts.setProduct_id(rs.getString("osa_req_prod_id"));
                    	prod_counts.setQuantity(rs.getInt("osa_prod_quantity"));
                        return prod_counts;  
                    }  
        });
	}

	private final static String _SELECT_PROD_COUNTS_FOR_ORDER="select b.osa_req_order_id,a.osa_req_prod_id,sum(a.osa_prod_quantity) osa_prod_quantity "
			+ "from osa_requisition_item a inner join osa_requisition b on a.osa_req_id=b.osa_requisition_id "
			+ "group by b.osa_req_order_id,a.osa_req_prod_id having b.osa_req_order_id=? ";
	@Override
	public List<OrderReqProdCounts> getReqProdCountsForOrderId(String order_id,
			String prod_id) throws Exception {
		// TODO Auto-generated method stub
		String queryString = _SELECT_PROD_COUNTS_FOR_ORDER + "and a.osa_req_prod_id=?";
		Object[] params = new Object[] {order_id,prod_id};
		
		return jdbcTemplate.query(queryString, params,
                new RowMapper<OrderReqProdCounts>(){         
                    @Override  
                    public OrderReqProdCounts mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderReqProdCounts prod_counts  = new OrderReqProdCounts();
                    	prod_counts.setOrder_id(rs.getString("osa_req_order_id"));
                    	prod_counts.setProduct_id(rs.getString("osa_req_prod_id"));
                    	prod_counts.setQuantity(rs.getInt("osa_prod_quantity"));
                        return prod_counts;  
                    }  
        });
	}

	@Override
	public List<OrderReqProdCounts> getReqProdCountsForOrderId(String order_id)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {order_id};
		
		return jdbcTemplate.query(_SELECT_PROD_COUNTS_FOR_ORDER, params,
                new RowMapper<OrderReqProdCounts>(){         
                    @Override  
                    public OrderReqProdCounts mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderReqProdCounts prod_counts  = new OrderReqProdCounts();
                    	prod_counts.setOrder_id(rs.getString("osa_req_order_id"));
                    	prod_counts.setProduct_id(rs.getString("osa_req_prod_id"));
                    	prod_counts.setQuantity(rs.getInt("osa_prod_quantity"));
                        return prod_counts;  
                    }  
        });
	}

}
