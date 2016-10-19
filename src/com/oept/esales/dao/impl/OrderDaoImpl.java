package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.OrderDao;
import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.OrderReqProdCounts;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/30
 * Description: Orders DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

	private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
	private final static String _INSERT_STRING_ORDER="insert into osa_order (osa_order_number,osa_order_man_number,osa_order_type_cd,"
			+ "osa_order_type,osa_order_date,osa_order_owner_id,"
			+ "osa_created,osa_created_by,osa_update,osa_update_by,osa_order_owner_viewed,osa_order_status_cd,osa_order_status_val,"
			+ "osa_currency_cd,osa_currency_val,osa_order_shipaddr_id,osa_order_billaddr_id,"
			+ "osa_order_supplier_id,osa_order_accnt_id,osa_deliver_wh_id,osa_receive_wh_id,osa_order_comm) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String _INSERT_STRING_ITEM="insert into osa_order_item (osa_order_id,osa_order_prod_id,osa_item_status_code,osa_item_status_val,"
			+ "osa_prod_quantity,osa_prod_base_price,osa_prod_due_price,osa_order_due_amt,osa_order_discount_amt,osa_order_tax_amt,"
			+ "osa_order_base_amt,osa_order_tax_ratio,osa_order_discount_ratio,osa_order_wh_id,"
			+ "osa_currency_cd,osa_currency_val,osa_desc,"
			+ "osa_item_created,osa_item_created_by,osa_item_update,osa_item_update_by) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String _SELECT_COUNT="select count(*) as count from osa_order";
	private final static String _SELECT_ORDER_ID="select osa_order_id from osa_order where osa_order_number=?";
	private final static String _SELECT_STRING_ORDER_DETAIL="select a.*,c.osa_addr_name ship_address,u.osa_username owner_name, "
			+ "u1.osa_username createdBy, u2.osa_username updateBy from osa_order a inner join "
			+ "osa_user u on a.osa_order_owner_id=u.osa_user_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_update_by=u2.osa_user_id left outer join "
			+ "osa_address c on a.osa_order_shipaddr_id=c.osa_addr_id where a.osa_order_id=?";
	private final static String _SELECT_STRING_ORDER_ITEMS="select a.*, p.osa_prod_name,p.osa_prod_model,c.osa_wh_name,"
			+ "u1.osa_username createdBy, u2.osa_username updateBy from osa_order_item a inner join "
			+ "osa_order b on a.osa_order_id=b.osa_order_id left outer join "
			+ "osa_warehouse c on a.osa_order_wh_id=c.osa_warehouse_id left outer join "
			+ "osa_product p on a.osa_order_prod_id=p.osa_prod_id inner join "
			+ "osa_user u1 on a.osa_item_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_item_update_by=u2.osa_user_id where a.osa_order_id=?";
	private final static String _SELECT_STRING_ORDER_LIST="select a.osa_order_id,a.osa_order_number,a.osa_order_date,"
			+ "a.osa_order_man_number,a.osa_created,u1.osa_username createdBy,"
			+ "c.osa_addr_name ship_address,a.osa_order_base_amt,a.osa_order_due_amt,a.osa_order_status_val,a.osa_order_status_cd,"
			+ "a.osa_order_delivery_date,u.osa_username owner_name,a.osa_order_owner_viewed,a.osa_order_shipaddr_id,"
			+ "a.osa_order_supplier_id,d2.osa_accnt_name supplier_name,"
			+ "a.osa_order_accnt_id,d1.osa_accnt_name account_name,"
			+ "a.osa_receive_wh_id,w1.osa_wh_name receive_warehouse_name,"
			+ "a.osa_currency_cd,a.osa_currency_val,"
			+ "a.osa_deliver_wh_id,w2.osa_wh_name delivery_warehouse_name,"
			+ "u2.osa_username updateBy from osa_order a inner join "
			+ "osa_user u on a.osa_order_owner_id=u.osa_user_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_update_by=u2.osa_user_id left outer join "
			+ "osa_address c on a.osa_order_shipaddr_id=c.osa_addr_id left outer join "
			+ "osa_account d1 on a.osa_order_accnt_id = d1.osa_account_id left outer join "
			+ "osa_account d2 on a.osa_order_supplier_id = d2.osa_account_id left outer join "
			+ "osa_warehouse w1 on a.osa_receive_wh_id = w1.osa_warehouse_id left outer join "
			+ "osa_warehouse w2 on a.osa_deliver_wh_id = w2.osa_warehouse_id";
	private final static String _UPDATE_STRING_ORDER="UPDATE osa_order SET osa_order_status_cd=?, osa_order_status_val=?, "
			+ "osa_update=?,osa_update_by=?,osa_order_owner_id=?,osa_order_shipaddr_id=?,osa_order_billaddr_id=?,"
			+ "osa_order_base_amt=?,osa_order_due_amt=?,osa_order_accnt_id=?,osa_order_lv2_accnt_id=?,osa_order_owner_viewed=?,"
			+ "osa_order_comm=?,osa_order_supplier_id=?,osa_order_lv2_supplier_id=?,osa_deliver_wh_id=?,osa_receive_wh_id=?,"
			+ "osa_order_type=?,osa_order_type_cd=?,osa_order_man_number=?,"
			+ "osa_payment_status_cd=?,osa_payment_status_val=?,osa_payment_date=?,osa_payment_ratio=?,"
			+ "osa_receipt_status_cd=?,osa_receipt_status_val=?,osa_receipt_due_date=?,osa_receipt_ratio=?,"
			+ "osa_order_shipaddr_name=?,osa_order_billaddr_name=? "
			+ "WHERE osa_order_id = ?";
	private final static String _UPDATE_STRING_STATUS="UPDATE osa_order SET osa_order_status_cd=?, osa_order_status_val=?,"
			+ "osa_update=?,osa_update_by=? "
			+ "WHERE osa_order_id = ?";
	private final static String _UPDATE_ORDER_AMOUNTS="UPDATE osa_order SET osa_order_base_amt=?, osa_order_due_amt=?,"
			+ "osa_update=?,osa_update_by=? "
			+ "WHERE osa_order_id = ?";
	private final static String _UPDATE_STRING_ORDER_ITEM="UPDATE osa_order_item SET osa_order_prod_id=?, osa_prod_quantity=?, "
			+ "osa_prod_base_price=?,osa_prod_due_price=?,osa_order_base_amt=?,osa_order_discount_ratio=?,osa_order_discount_amt=?,"
			+ "osa_order_tax_ratio=?,osa_order_tax_amt=?,osa_order_due_amt=?,osa_order_wh_id=?,osa_desc=?,osa_item_update=?,osa_item_update_by=? "
			+ "WHERE osa_order_item_id = ?";
	private final static String _UPDATE_ORDER_SHIP_ADDR="UPDATE osa_order SET osa_order_shipaddr_id=?,"
			+ "osa_update=?,osa_update_by=? "
			+ "WHERE osa_order_id = ?";
	private final static String _UPDATE_ORDER_BILL_ADDR="UPDATE osa_order SET osa_order_billaddr_id=?,"
			+ "osa_update=?,osa_update_by=? "
			+ "WHERE osa_order_id = ?";
	private final static String _DELETE_ORDER_ITEM="delete from osa_order_item where osa_order_item_id=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public String addOrder(OrderFlat order) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order.getOrder_number(),
					order.getOrder_manual_number(),
					order.getOrder_type_cd(),
					order.getOrder_type(),
					order.getPurchase_date(),
					order.getOwner_id(),
					order.getCreated_date(),
					order.getCreated_by_id(),
					order.getUpdate_date(),
					order.getUpdate_by_id(),
					order.isOwner_viewed(),
					order.getStatus_code(),
					order.getStatus_value(),
					order.getCurrency_cd(),
					order.getCurrency_val(),
					order.getShip_addr_id(),
					order.getBill_addr_id(),
					order.getSupplier_id(),
					order.getAccount_id(),
					order.getDelivery_wh_id(),
					order.getReceive_wh_id(),
					order.getOrder_comment()
					};
			jdbcTemplate.update(_INSERT_STRING_ORDER,params);
			
			params = new Object[]{order.getOrder_number()};
			OrderFlat new_order = jdbcTemplate.queryForObject(_SELECT_ORDER_ID, params,
					new RowMapper<OrderFlat>(){         
	        		@Override  
	        		public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
	        			OrderFlat order  = new OrderFlat(); 
	        			order.setOrder_id(rs.getString("osa_order_id"));
	        			return order;
	        		}
			});
			return new_order.getOrder_id();
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delOrderById(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrder(OrderFlat order) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order.getStatus_code(),
					order.getStatus_value(),
					order.getUpdate_date(),
					order.getUpdate_by_id(),
					order.getOwner_id(),
					order.getShip_addr_id(),
					order.getBill_addr_id(),
					order.getBase_amount(),
					order.getDue_amount(),
					order.getAccount_id(),
					order.getAccount_lv2_id(),
					order.isOwner_viewed(),
					order.getOrder_comment(),
					order.getSupplier_id(),
					order.getSupplier_lv2_id(),
					order.getDelivery_wh_id(),
					order.getReceive_wh_id(),
					order.getOrder_type(),
					order.getOrder_type_cd(),
					order.getOrder_manual_number(),
					order.getPayment_status_code(),
					order.getPayment_status_val(),
					order.getPayment_date(),
					order.getPayment_ratio(),
					order.getReceipt_status_code(),
					order.getReceipt_status_val(),
					order.getReceipt_due_date(),
					order.getReceipt_ratio(),
					order.getShip_addr_name(),
					order.getBill_addr_name(),
					order.getOrder_id() };
			jdbcTemplate.update(_UPDATE_STRING_ORDER,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<OrderFlat> getAllOrders() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_STRING_ORDER_LIST, 
                new RowMapper<OrderFlat>(){         
                    @Override  
                    public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderFlat order  = new OrderFlat();  
                    	order.setOrder_id(rs.getString("osa_order_id"));
                    	order.setOrder_number(rs.getString("osa_order_number"));
                    	order.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	order.setPurchase_date(rs.getTimestamp("osa_order_date"));
                    	order.setDelivery_date(rs.getTimestamp("osa_order_delivery_date"));
                    	order.setOrder_type_cd(rs.getString("osa_order_type_cd"));
                    	order.setOrder_type(rs.getString("osa_order_type"));
                    	order.setShip_addr_id(rs.getString("osa_order_shipaddr_id"));
                    	order.setShip_addr_name(rs.getString("ship_address"));
                    	order.setBill_addr_id(rs.getString("osa_order_billaddr_id"));
                    	order.setOwner_name(rs.getString("owner_name"));
                    	order.setCreated_by_id(rs.getString("osa_created_by"));
                    	order.setCreated_by_name(rs.getString("createdBy"));
                    	order.setStatus_code(rs.getString("osa_order_status_cd"));
                    	order.setStatus_value(rs.getString("osa_order_status_val"));
                    	order.setPayment_status_code(rs.getString("osa_payment_status_cd"));
                    	order.setPayment_status_val(rs.getString("osa_payment_status_val"));
                    	order.setPayment_ratio(rs.getDouble("osa_payment_ratio"));
                    	order.setBase_amount(rs.getDouble("osa_order_base_amt"));
                    	order.setDue_amount(rs.getDouble("osa_order_due_amt"));
                    	order.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	order.setCurrency_val(rs.getString("osa_currency_val"));
                    	order.setOwner_viewed(rs.getBoolean("osa_order_owner_viewed"));
                    	order.setSupplier_id(rs.getString("osa_order_supplier_id"));
                    	order.setAccount_id(rs.getString("osa_order_accnt_id"));
                    	order.setOrder_comment(rs.getString("osa_order_comm"));
                    	order.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	order.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	order.setOwner_id(rs.getString("osa_order_owner_id"));
                        return order;  
                    }  
        });
	}
	
	@Override
	public List<OrderFlat> getAllOrders(String userid) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_ORDER_LIST);
		queryString.append(" where a.osa_order_owner_id="+userid);
		
		return jdbcTemplate.query(queryString.toString(), 
                new RowMapper<OrderFlat>(){         
                    @Override  
                    public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderFlat order  = new OrderFlat();  
                    	order.setOrder_id(rs.getString("osa_order_id"));
                    	order.setOrder_number(rs.getString("osa_order_number"));
                    	order.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	order.setPurchase_date(rs.getTimestamp("osa_order_date"));
                    	order.setDelivery_date(rs.getTimestamp("osa_order_delivery_date"));
                    	order.setOrder_type_cd(rs.getString("osa_order_type_cd"));
                    	order.setOrder_type(rs.getString("osa_order_type"));
                    	order.setShip_addr_id(rs.getString("osa_order_shipaddr_id"));
                    	order.setShip_addr_name(rs.getString("ship_address"));
                    	order.setBill_addr_id(rs.getString("osa_order_billaddr_id"));
                    	order.setOwner_name(rs.getString("owner_name"));
                    	order.setCreated_by_id(rs.getString("osa_created_by"));
                    	order.setCreated_by_name(rs.getString("createdBy"));
                    	order.setStatus_code(rs.getString("osa_order_status_cd"));
                    	order.setStatus_value(rs.getString("osa_order_status_val"));
                    	order.setPayment_status_code(rs.getString("osa_payment_status_cd"));
                    	order.setPayment_status_val(rs.getString("osa_payment_status_val"));
                    	order.setPayment_ratio(rs.getDouble("osa_payment_ratio"));
                    	order.setBase_amount(rs.getDouble("osa_order_base_amt"));
                    	order.setDue_amount(rs.getDouble("osa_order_due_amt"));
                    	order.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	order.setCurrency_val(rs.getString("osa_currency_val"));
                    	order.setOwner_viewed(rs.getBoolean("osa_order_owner_viewed"));
                    	order.setSupplier_id(rs.getString("osa_order_supplier_id"));
                    	order.setAccount_id(rs.getString("osa_order_accnt_id"));
                    	order.setOrder_comment(rs.getString("osa_order_comm"));
                    	order.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	order.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	order.setOwner_id(rs.getString("osa_order_owner_id"));
                        return order;  
                    }  
        });
	}

	@Override
	public OrderFlat getOrderById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject(_SELECT_STRING_ORDER_DETAIL, params,
                new RowMapper<OrderFlat>(){         
                    @Override  
                    public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderFlat order  = new OrderFlat();  
                    	order.setOrder_id(rs.getString("osa_order_id"));
                    	order.setOrder_number(rs.getString("osa_order_number"));
                    	order.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	order.setPurchase_date(rs.getTimestamp("osa_order_date"));
                    	order.setDelivery_date(rs.getTimestamp("osa_order_delivery_date"));
                    	order.setOrder_type_cd(rs.getString("osa_order_type_cd"));
                    	order.setOrder_type(rs.getString("osa_order_type"));
                    	order.setShip_addr_id(rs.getString("osa_order_shipaddr_id"));
                    	order.setShip_addr_name(rs.getString("osa_order_shipaddr_name"));
                    	order.setBill_addr_id(rs.getString("osa_order_billaddr_id"));
                    	order.setBill_addr_name(rs.getString("osa_order_billaddr_name"));
                    	order.setArea_id(rs.getString("osa_order_area_id"));
                    	order.setOwner_name(rs.getString("owner_name"));
                    	order.setCreated_date(rs.getTimestamp("osa_created"));
                    	order.setCreated_by_id(rs.getString("osa_created_by"));
                    	order.setCreated_by_name(rs.getString("createdBy"));
                    	order.setStatus_code(rs.getString("osa_order_status_cd"));
                    	order.setStatus_value(rs.getString("osa_order_status_val"));
                    	order.setPayment_status_code(rs.getString("osa_payment_status_cd"));
                    	order.setPayment_status_val(rs.getString("osa_payment_status_val"));
                    	order.setPayment_ratio(rs.getDouble("osa_payment_ratio"));
                    	order.setReceipt_due_date(rs.getDate("osa_receipt_due_date"));
                    	order.setReceipt_ratio(rs.getDouble("osa_receipt_ratio"));
                    	order.setReceipt_status_code(rs.getString("osa_receipt_status_cd"));
                    	order.setReceipt_status_val(rs.getString("osa_receipt_status_val"));
                    	order.setBase_amount(rs.getDouble("osa_order_base_amt"));
                    	order.setDue_amount(rs.getDouble("osa_order_due_amt"));
                    	order.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	order.setCurrency_val(rs.getString("osa_currency_val"));
                    	order.setOwner_viewed(rs.getBoolean("osa_order_owner_viewed"));
                    	order.setSupplier_id(rs.getString("osa_order_supplier_id"));
                    	order.setSupplier_lv2_id(rs.getString("osa_order_lv2_supplier_id"));
                    	order.setAccount_id(rs.getString("osa_order_accnt_id"));
                    	order.setAccount_lv2_id(rs.getString("osa_order_lv2_accnt_id"));
                    	order.setOrder_comment(rs.getString("osa_order_comm"));
                    	order.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	order.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	order.setOwner_id(rs.getString("osa_order_owner_id"));
                        return order;  
                    }  
        });
	}

	@Override
	public int getOrdersCount() throws Exception {
		// TODO Auto-generated method stub
		OrderFlat order = jdbcTemplate.queryForObject(_SELECT_COUNT, 
				new RowMapper<OrderFlat>(){         
        		@Override  
        		public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
        			OrderFlat order  = new OrderFlat(); 
        			order.setRecords_count(rs.getInt("count"));
        			return order;
        		}
		});
		return order.getRecords_count();
	}
	
	@Override
	public int getOrdersCount(String userid) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_COUNT);
		queryString.append(" where osa_order_owner_id="+userid);
		OrderFlat order = jdbcTemplate.queryForObject(queryString.toString(), 
				new RowMapper<OrderFlat>(){         
        		@Override  
        		public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
        			OrderFlat order  = new OrderFlat(); 
        			order.setRecords_count(rs.getInt("count"));
        			return order;
        		}
		});
		return order.getRecords_count();
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
	public List<OrderFlat> getOrders(OrderFlat order, String start, String limit,
			String sortColumn, String sortDir) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_ORDER_LIST);
		queryString.append(" where 1=1");
		
		String date_from = order.getOrder_date_from();
		String date_to = order.getOrder_date_to();
		
		//Filter combination
		if( !"".equals(order.getOrder_number())&&order.getOrder_number()!=null  ){
			queryString.append(" and osa_order_number='"+order.getOrder_number()+"' ");
		}
		if( !"".equals(order.getOrder_type())&&order.getOrder_type()!=null  ){
			queryString.append(" and osa_order_type='"+order.getOrder_type()+"' ");
		}
		if( !"".equals(order.getOrder_type_cd())&&order.getOrder_type_cd()!=null  ){
			queryString.append(" and osa_order_type_cd='"+order.getOrder_type_cd()+"' ");
		}
		if( !"".equals(order.getOwner_id())&&order.getOwner_id()!=null ){
			queryString.append(" and osa_order_owner_id="+order.getOwner_id()+" ");
		}
		if( !"".equals(order.getStatus_code())&&order.getStatus_code()!=null ){
			queryString.append(" and osa_order_status_cd='"+order.getStatus_code()+"' ");
		}
		if( !"".equals(order.getShip_addr_name())&&order.getShip_addr_name()!=null ){
			queryString.append(" and ship_address='"+order.getShip_addr_name()+"' ");
		}
		if( !"".equals(order.getCreated_by_name())&&order.getCreated_by_name()!=null ){
			queryString.append(" and createdBy='"+order.getCreated_by_name()+"' ");
		}
		if( !"".equals(order.getOrder_date_from())&&order.getOrder_date_from()!=null ){
			if( !"".equals(order.getOrder_date_to())&&order.getOrder_date_to()!=null ){
				queryString.append(" and osa_order_date>='"+dateFormatMethod(date_from)+"' and osa_order_date<='"+dateFormatMethod(date_to)+"' ");
			}else{
				queryString.append(" and osa_order_date>='"+dateFormatMethod(date_from)+"' ");
			}
		}else if( !"".equals(order.getOrder_date_to())&&order.getOrder_date_to()!=null ){
			queryString.append(" and osa_order_date<='"+dateFormatMethod(date_to)+"' ");
		}
		if( !"".equals(order.getOrder_base_price_from())&&order.getOrder_base_price_from()!=null ){
			if( !"".equals(order.getOrder_base_price_to())&&order.getOrder_base_price_to()!=null ){
				queryString.append(" and osa_order_base_amt>="+order.getOrder_base_price_from()+" and osa_order_base_amt<="+order.getOrder_base_price_to()+" ");
			}else{
				queryString.append(" and osa_order_base_amt>="+order.getOrder_base_price_from()+" ");
			}
		}else if( !"".equals(order.getOrder_base_price_to())&&order.getOrder_base_price_to()!=null ){
			queryString.append(" and osa_order_base_amt<="+order.getOrder_base_price_to()+" ");
		}
		if( !"".equals(order.getOrder_purchase_price_from())&&order.getOrder_purchase_price_from()!=null ){
			if( !"".equals(order.getOrder_purchase_price_to())&&order.getOrder_purchase_price_to()!=null ){
				queryString.append(" and osa_order_due_amt>="+order.getOrder_purchase_price_from()+" and osa_order_due_amt<="+order.getOrder_purchase_price_to()+" ");
			}else{
				queryString.append(" and osa_order_due_amt>="+order.getOrder_purchase_price_from()+" ");
			}
		}else if( !"".equals(order.getOrder_base_price_to())&&order.getOrder_base_price_to()!=null ){
			queryString.append(" and osa_order_due_amt<="+order.getOrder_purchase_price_to()+" ");
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
                new RowMapper<OrderFlat>(){         
                    @Override  
                    public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderFlat order  = new OrderFlat();  
                    	order.setOrder_id(rs.getString("osa_order_id"));
                    	order.setOrder_number(rs.getString("osa_order_number"));
                    	order.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	order.setPurchase_date(rs.getTimestamp("osa_order_date"));
                    	order.setDelivery_date(rs.getTimestamp("osa_order_delivery_date"));
                    	order.setShip_addr_id(rs.getString("osa_order_shipaddr_id"));
                    	order.setShip_addr_name(rs.getString("ship_address"));
                    	order.setOwner_name(rs.getString("owner_name"));
                    	order.setCreated_date(rs.getTimestamp("osa_created"));
                    	order.setCreated_by_name(rs.getString("createdBy"));
                    	order.setStatus_code(rs.getString("osa_order_status_cd"));
                    	order.setStatus_value(rs.getString("osa_order_status_val"));
                    	order.setBase_amount(rs.getDouble("osa_order_base_amt"));
                    	order.setDue_amount(rs.getDouble("osa_order_due_amt"));
                    	order.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	order.setCurrency_val(rs.getString("osa_currency_val"));
                    	order.setOwner_viewed(rs.getBoolean("osa_order_owner_viewed"));
                    	order.setSupplier_id(rs.getString("osa_order_supplier_id"));
                    	order.setSupplier_name(rs.getString("supplier_name"));
                    	order.setAccount_id(rs.getString("osa_order_accnt_id"));
                    	order.setAccount_name(rs.getString("account_name"));
                    	order.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	order.setReceive_wh_name(rs.getString("receive_warehouse_name"));
                    	order.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	order.setDelivery_wh_name(rs.getString("delivery_warehouse_name"));
                        return order;  
                    }  
        });
	}

	@Override
	public boolean addOrderItem(OrderFlat order) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order.getOrder_id(),
					order.getItem_prod_id(),
					order.getItem_status_code(),
					order.getItem_status_value(),
					order.getItem_quantity(),
					order.getItem_base_price(),
					order.getItem_due_price(),
					order.getItem_due_amount(),
					order.getItem_discount_amount(),
					order.getItem_tax_amount(),
					order.getItem_base_amount(),
					order.getItem_tax_ratio(),
					order.getItem_discount_ratio(),
					order.getItem_warehouse_id(),
					order.getCurrency_cd(),
					order.getCurrency_val(),
					order.getItem_comment(),
					order.getItem_created_date(),
					order.getItem_created_by_id(),
					order.getItem_update_date(),
					order.getItem_update_by_id()
					};
			jdbcTemplate.update(_INSERT_STRING_ITEM,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delOrderItemById(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {id};
			jdbcTemplate.update(_DELETE_ORDER_ITEM,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean updateOrderItem(OrderFlat order) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order.getItem_prod_id(),
					order.getItem_quantity(),
					order.getItem_base_price(),
					order.getItem_due_price(),
					order.getItem_base_amount(),
					order.getItem_discount_ratio(),
					order.getItem_discount_amount(),
					order.getItem_tax_ratio(),
					order.getItem_tax_amount(),
					order.getItem_due_amount(),
					order.getItem_warehouse_id(),
					order.getItem_comment(),
					order.getItem_update_date(),
					order.getItem_update_by_id(),
					order.getItem_id()};
			jdbcTemplate.update(_UPDATE_STRING_ORDER_ITEM,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<OrderFlat> getItemsByOrderId(String orderId) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {orderId};
		return jdbcTemplate.query(_SELECT_STRING_ORDER_ITEMS, params,
                new RowMapper<OrderFlat>(){         
                    @Override  
                    public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderFlat order  = new OrderFlat();  
                    	order.setItem_id(rs.getString("osa_order_item_id"));
                    	order.setOrder_id(rs.getString("osa_order_id"));
                    	order.setItem_prod_id(rs.getString("osa_order_prod_id"));
                    	order.setItem_prod_name(rs.getString("osa_prod_name"));
                    	order.setItem_prod_model(rs.getString("osa_prod_model"));
                    	order.setItem_status_code(rs.getString("osa_item_status_code"));
                    	order.setItem_status_value(rs.getString("osa_item_status_val"));
                    	order.setItem_base_price(rs.getDouble("osa_prod_base_price"));
                    	order.setItem_quantity(rs.getInt("osa_prod_quantity"));
                    	order.setItem_discount_amount(rs.getDouble("osa_order_discount_amt"));
                    	order.setItem_tax_amount(rs.getDouble("osa_order_tax_amt"));
                    	order.setItem_due_price(rs.getDouble("osa_prod_due_price"));
                    	order.setItem_due_amount(rs.getDouble("osa_order_due_amt"));
                    	order.setItem_created_date(rs.getTimestamp("osa_item_created"));
                    	order.setItem_update_date(rs.getTimestamp("osa_item_update"));
                    	order.setItem_created_by_id(rs.getString("osa_item_created_by"));
                    	order.setItem_created_by_name(rs.getString("createdBy"));
                    	order.setItem_update_by_id(rs.getString("osa_item_update_by"));
                    	order.setItem_update_by_name(rs.getString("updateBy"));
                    	order.setItem_base_amount(rs.getDouble("osa_order_base_amt"));
                    	order.setItem_discount_ratio(rs.getFloat("osa_order_discount_ratio"));
                    	order.setItem_tax_ratio(rs.getFloat("osa_order_tax_ratio"));
                    	order.setItem_comment(rs.getString("osa_desc"));
                    	order.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	order.setCurrency_val(rs.getString("osa_currency_val"));
                    	order.setItem_warehouse_name(rs.getString("osa_wh_name"));
                    	order.setItem_warehouse_id(rs.getString("osa_order_wh_id"));
                    	
                        return order;  
                    }  
        });
	}
		  

	@Override
	public boolean updateOrderStatus(OrderFlat order) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order.getStatus_code(),
					order.getStatus_value(),
					order.getUpdate_date(),
					order.getUpdate_by_id(),
					order.getOrder_id() };
			jdbcTemplate.update(_UPDATE_STRING_STATUS,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public boolean updateOrderAmounts(OrderFlat order) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order.getBase_amount(),
					order.getDue_amount(),
					order.getUpdate_date(),
					order.getUpdate_by_id(),
					order.getOrder_id() };
			jdbcTemplate.update(_UPDATE_ORDER_AMOUNTS,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean updateOrderShipAddr(OrderFlat order) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order.getShip_addr_id(),
					order.getUpdate_date(),
					order.getUpdate_by_id(),
					order.getOrder_id() };
			jdbcTemplate.update(_UPDATE_ORDER_SHIP_ADDR,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	private final static String _UPDATE_ORDER_AREA="UPDATE osa_order SET osa_order_area_id=?,"
			+ "osa_update=?,osa_update_by=? "
			+ "WHERE osa_order_id = ?";
	@Override
	public boolean updateOrderArea(OrderFlat order) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order.getArea_id(),
					order.getUpdate_date(),
					order.getUpdate_by_id(),
					order.getOrder_id() };
			jdbcTemplate.update(_UPDATE_ORDER_AREA,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean updateOrderBillAddr(OrderFlat order) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					order.getBill_addr_id(),
					order.getUpdate_date(),
					order.getUpdate_by_id(),
					order.getOrder_id() };
			jdbcTemplate.update(_UPDATE_ORDER_BILL_ADDR,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _SELECT_STRING_ORDER_LIST_FOR_APPROVER="select a.osa_order_id,a.osa_order_number,a.osa_order_date,"
			+ "a.osa_order_man_number,a.osa_created,u1.osa_username createdBy,"
			+ "c.osa_addr_name ship_address,a.osa_order_base_amt,a.osa_order_due_amt,a.osa_order_status_val,a.osa_order_status_cd,"
			+ "a.osa_order_delivery_date,u.osa_username owner_name,a.osa_order_owner_viewed,a.osa_order_shipaddr_id,"
			+ "a.osa_order_supplier_id,d2.osa_accnt_name supplier_name,"
			+ "a.osa_order_accnt_id,d1.osa_accnt_name account_name,"
			+ "a.osa_currency_cd,a.osa_currency_val,"
			+ "a.osa_receive_wh_id,w1.osa_wh_name receive_warehouse_name,"
			+ "a.osa_deliver_wh_id,w2.osa_wh_name delivery_warehouse_name,"
			+ "u2.osa_username updateBy from osa_order a inner join "
			+ "osa_user u on a.osa_order_owner_id=u.osa_user_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_update_by=u2.osa_user_id left outer join "
			+ "osa_address c on a.osa_order_shipaddr_id=c.osa_addr_id left outer join "
			+ "osa_account d1 on a.osa_order_accnt_id = d1.osa_account_id left outer join "
			+ "osa_account d2 on a.osa_order_supplier_id = d2.osa_account_id left outer join "
			+ "osa_warehouse w1 on a.osa_receive_wh_id = w1.osa_warehouse_id left outer join "
			+ "osa_warehouse w2 on a.osa_deliver_wh_id = w2.osa_warehouse_id inner join "
			+ "osa_approval_step r1 on a.osa_order_id = r1.osa_order_id inner join "
			+ "osa_approval_detail r2 on r1.osa_approval_step_id = r2.osa_approval_step_id "
			+ "where r1.osa_process_flg=TRUE and r2.osa_approval_status_cd='pending' and r2.osa_to_approve=?";
	@Override
	public List<OrderFlat> getOrdersForApprover(OrderFlat order,String approver_id,
			String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {approver_id};
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_ORDER_LIST_FOR_APPROVER);
		
		if( !"".equals(order.getOrder_type_cd())&&order.getOrder_type_cd()!=null  ){
			queryString.append(" and osa_order_type_cd='"+order.getOrder_type_cd()+"' ");
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
                new RowMapper<OrderFlat>(){         
                    @Override  
                    public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderFlat order  = new OrderFlat();  
                    	order.setOrder_id(rs.getString("osa_order_id"));
                    	order.setOrder_number(rs.getString("osa_order_number"));
                    	order.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	order.setPurchase_date(rs.getTimestamp("osa_order_date"));
                    	order.setDelivery_date(rs.getTimestamp("osa_order_delivery_date"));
                    	order.setShip_addr_id(rs.getString("osa_order_shipaddr_id"));
                    	order.setShip_addr_name(rs.getString("ship_address"));
                    	order.setOwner_name(rs.getString("owner_name"));
                    	order.setCreated_date(rs.getTimestamp("osa_created"));
                    	order.setCreated_by_name(rs.getString("createdBy"));
                    	order.setStatus_code(rs.getString("osa_order_status_cd"));
                    	order.setStatus_value(rs.getString("osa_order_status_val"));
                    	order.setBase_amount(rs.getDouble("osa_order_base_amt"));
                    	order.setDue_amount(rs.getDouble("osa_order_due_amt"));
                    	order.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	order.setCurrency_val(rs.getString("osa_currency_val"));
                    	order.setOwner_viewed(rs.getBoolean("osa_order_owner_viewed"));
                    	order.setSupplier_id(rs.getString("osa_order_supplier_id"));
                    	order.setSupplier_name(rs.getString("supplier_name"));
                    	order.setAccount_id(rs.getString("osa_order_accnt_id"));
                    	order.setAccount_name(rs.getString("account_name"));
                    	order.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	order.setReceive_wh_name(rs.getString("receive_warehouse_name"));
                    	order.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	order.setDelivery_wh_name(rs.getString("delivery_warehouse_name"));
                        return order;  
                    }  
        });
	}

	private final static String _SELECT_UNIQUE_WH_IDS="select osa_order_wh_id from osa_order_item"
			+ " where osa_order_id=? group by osa_order_wh_id";
	@Override
	public List<String> getItemWarehouseIds(String orderId) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {orderId};
		
		return jdbcTemplate.query(_SELECT_UNIQUE_WH_IDS.toString(), params,
                new RowMapper<String>(){         
                    @Override  
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	
                        return rs.getString("osa_order_wh_id"); 
                    }  
        });
	}

	private final static String _SELECT_RECENT_ORDERS="select a.created,b.* from (select osa_order_id,max(osa_created) created "
			+ "from osa_order_history where osa_created_by=? GROUP BY osa_order_id order by created desc) a "
			+ "INNER JOIN osa_order b on a.osa_order_id=b.osa_order_id LIMIT 15";
	@Override
	public List<OrderFlat> getRecentOrders(String userid) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {userid};
		
		return jdbcTemplate.query(_SELECT_RECENT_ORDERS, params,
                new RowMapper<OrderFlat>(){         
                    @Override  
                    public OrderFlat mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderFlat order  = new OrderFlat();  
                    	order.setOrder_id(rs.getString("osa_order_id"));
                    	order.setOrder_number(rs.getString("osa_order_number"));
                    	order.setOrder_manual_number(rs.getString("osa_order_man_number"));
                    	order.setPurchase_date(rs.getTimestamp("osa_order_date"));
                    	order.setDelivery_date(rs.getTimestamp("osa_order_delivery_date"));
                    	order.setShip_addr_id(rs.getString("osa_order_shipaddr_id"));
                    	order.setCreated_date(rs.getTimestamp("osa_created"));
                    	order.setUpdate_date(rs.getTimestamp("created"));
                    	order.setStatus_code(rs.getString("osa_order_status_cd"));
                    	order.setStatus_value(rs.getString("osa_order_status_val"));
                    	order.setBase_amount(rs.getDouble("osa_order_base_amt"));
                    	order.setDue_amount(rs.getDouble("osa_order_due_amt"));
                    	order.setCurrency_cd(rs.getString("osa_currency_cd"));
                    	order.setCurrency_val(rs.getString("osa_currency_val"));
                    	order.setOwner_viewed(rs.getBoolean("osa_order_owner_viewed"));
                    	order.setSupplier_id(rs.getString("osa_order_supplier_id"));
                    	order.setAccount_id(rs.getString("osa_order_accnt_id"));
                    	order.setReceive_wh_id(rs.getString("osa_receive_wh_id"));
                    	order.setDelivery_wh_id(rs.getString("osa_deliver_wh_id"));
                    	order.setOrder_type_cd(rs.getString("osa_order_type_cd"));
                    	order.setOrder_type(rs.getString("osa_order_type"));
                        return order;  
                    }  
        });
	}

	private final static String _SELECT_PROD_COUNTS="select osa_order_id,osa_order_prod_id,sum(osa_prod_quantity) osa_prod_quantity "
			+ "from osa_order_item group by osa_order_id,osa_order_prod_id having osa_order_id=? ";
	@Override
	public List<OrderReqProdCounts> getOrderProdCounts(String order_id)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {order_id};
		
		return jdbcTemplate.query(_SELECT_PROD_COUNTS, params,
                new RowMapper<OrderReqProdCounts>(){         
                    @Override  
                    public OrderReqProdCounts mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderReqProdCounts prod_counts  = new OrderReqProdCounts();  
                    	prod_counts.setOrder_id(rs.getString("osa_order_id"));
                    	prod_counts.setProduct_id(rs.getString("osa_order_prod_id"));
                    	prod_counts.setQuantity(rs.getInt("osa_prod_quantity"));
                        return prod_counts;  
                    }  
        });
	}

	@Override
	public List<OrderReqProdCounts> getOrderProdCounts(String order_id,
			String prod_id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {order_id,prod_id};
		String queryString = _SELECT_PROD_COUNTS + "and osa_order_prod_id=?";
		
		return jdbcTemplate.query(queryString, params,
                new RowMapper<OrderReqProdCounts>(){         
                    @Override  
                    public OrderReqProdCounts mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	OrderReqProdCounts prod_counts  = new OrderReqProdCounts();  
                    	prod_counts.setOrder_id(rs.getString("osa_order_id"));
                    	prod_counts.setProduct_id(rs.getString("osa_order_prod_id"));
                    	prod_counts.setQuantity(rs.getInt("osa_prod_quantity"));
                        return prod_counts;  
                    }  
        });
	}

}
