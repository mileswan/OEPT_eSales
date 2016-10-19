package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.StockHistoryDao;
import com.oept.esales.model.StockHistory;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/7
 * Description: Stock history DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("stockHistoryDao")
public class StockHistoryDaoImpl implements StockHistoryDao {

	private static final Logger logger = Logger.getLogger(StockHistoryDaoImpl.class);
	private final static String _INSERT_STRING_STOCK_HISTORY="insert into osa_stock_history (osa_warehouse_id,osa_product_id,"
			+ "osa_requisition_id,osa_stock_type_cd,osa_stock_type_val,osa_original_stock,osa_stock_quantity,osa_desc,"
			+ "osa_stock_price,osa_stock_amount,"
			+ "osa_created,osa_created_by) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String _SELECT_ALL_STOCK_HISTORY="select a.*, b.osa_wh_name warehouse_name, u1.osa_username created_by_username, "
			+ "c.osa_prod_name product_name,d.osa_req_number requisition_number "
			+ "from osa_stock_history a inner join osa_warehouse b on a.osa_warehouse_id=b.osa_warehouse_id left outer join "
			+ "osa_product c on a.osa_product_id=c.osa_prod_id left outer join "
			+ "osa_requisition d on a.osa_requisition_id=d.osa_requisition_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id where 1=1 ";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean addHistory(StockHistory stock_history) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					stock_history.getWarehouse_id(),
					stock_history.getProduct_id(),
					stock_history.getRequisition_id(),
					stock_history.getStock_type_code(),
					stock_history.getStock_type_val(),
					stock_history.getOriginal_stock(),
					stock_history.getStock_quantity(),
					stock_history.getDescription(),
					stock_history.getStock_price(),
					stock_history.getStock_amount(),
					stock_history.getCreated_date(),
					stock_history.getCreated_by_user_id()
					};
			jdbcTemplate.update(_INSERT_STRING_STOCK_HISTORY,params);
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
	public List<StockHistory> getAllHistories() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_ALL_STOCK_HISTORY, 
                new RowMapper<StockHistory>(){         
                    @Override  
                    public StockHistory mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	StockHistory stock_history  = new StockHistory();
                    	stock_history.setId(rs.getString("osa_stock_history_id"));
                    	stock_history.setWarehouse_id(rs.getString("osa_warehouse_id"));
                    	stock_history.setWarehouse_name(rs.getString("warehouse_name"));
                    	stock_history.setProduct_id(rs.getString("osa_product_id"));
                    	stock_history.setProduct_name(rs.getString("product_name"));
                    	stock_history.setDescription(rs.getString("osa_desc"));
//                    	stock_history.setOrder_id(rs.getString("osa_order_id"));
//                    	stock_history.setOrder_type_code(rs.getString("order_type_cd"));
//                    	stock_history.setOrder_type_val(rs.getString("order_type"));
                    	stock_history.setOriginal_stock(rs.getInt("osa_original_stock"));
                    	stock_history.setRequisition_id(rs.getString("osa_requisition_id"));
                    	stock_history.setRequisition_number(rs.getString("requisition_number"));
                    	stock_history.setStock_quantity(rs.getInt("osa_stock_quantity"));
                    	stock_history.setStock_price(rs.getDouble("osa_stock_price"));
                    	stock_history.setStock_amount(rs.getDouble("osa_stock_amount"));
                    	stock_history.setStock_type_code(rs.getString("osa_stock_type_cd"));
                    	stock_history.setStock_type_val(rs.getString("osa_stock_type_val"));
                    	stock_history.setCreated_date(rs.getTimestamp("osa_created"));
                    	stock_history.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	stock_history.setCreated_by_user_name(rs.getString("created_by_username"));
                    	//stock_history.setOrder_number(rs.getString("order_number"));
                        return stock_history;  
                    }
        });
	}

	@Override
	public StockHistory getHistoryById(String id) throws Exception {
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
	public List<StockHistory> getHistories(StockHistory stock_history,
			String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_ALL_STOCK_HISTORY);
		
		
		String date_from = stock_history.getCreated_date_from();
		String date_to = stock_history.getCreated_date_to();
		
		
		//Filter combination
		if( !"".equals(stock_history.getProduct_id()) && stock_history.getProduct_id()!=null ){
			queryString.append("and a.osa_product_id='"+stock_history.getProduct_id()+"' ");
		}
		if( !"".equals(stock_history.getProduct_name()) && stock_history.getProduct_name()!=null ){
			queryString.append("and c.osa_prod_name like '"+stock_history.getProduct_name()+"' ");
		}
		if( !"".equals(stock_history.getWarehouse_id()) && stock_history.getWarehouse_id()!=null ){
			queryString.append("and a.osa_warehouse_id='"+stock_history.getWarehouse_id()+"' ");
		}
		if( !"".equals(stock_history.getOrder_id()) && stock_history.getOrder_id()!=null ){
			queryString.append("and a.osa_order_id='"+stock_history.getOrder_id()+"' ");
		}
		if( !"".equals(stock_history.getRequisition_number()) && stock_history.getRequisition_number()!=null ){
			queryString.append("and d.osa_req_man_number='"+stock_history.getRequisition_number()+"' ");
		}
		if( !"".equals(stock_history.getOrder_type_code()) && stock_history.getOrder_type_code()!=null ){
			queryString.append("and d.osa_order_type_cd='"+stock_history.getOrder_type_code()+"' ");
		}
		if( !"".equals(stock_history.getStock_type_code()) && stock_history.getStock_type_code()!=null ){
			queryString.append("and a.osa_stock_type_cd='"+stock_history.getStock_type_code()+"' ");
		}
		if( !"".equals(stock_history.getDescription()) && stock_history.getDescription()!=null ){
			queryString.append("and a.osa_desc like '"+stock_history.getDescription()+"' ");
		}
		if( !"".equals(stock_history.getCreated_date_from())&&stock_history.getCreated_date_from()!=null ){
			if( !"".equals(stock_history.getCreated_date_to())&&stock_history.getCreated_date_to()!=null ){
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_from)+"' and a.osa_created<='"+dateFormatMethod(date_to)+"' ");
			}else{
				queryString.append("and a.osa_created>='"+dateFormatMethod(date_to)+"' ");
			}
		}else if( !"".equals(stock_history.getCreated_date_to())&&stock_history.getCreated_date_to()!=null ){
			queryString.append("and a.osa_created<='"+dateFormatMethod(date_to)+"' ");
		}
		if( !"".equals(stock_history.getOriginal_stock_from())&&stock_history.getOriginal_stock_from()!=null ){
			if( !"".equals(stock_history.getOriginal_stock_to())&&stock_history.getOriginal_stock_to()!=null ){
				queryString.append("and osa_original_stock>="+dateFormatMethod(date_from)+" and osa_original_stock<="+dateFormatMethod(date_to)+" ");
			}else{
				queryString.append("and osa_original_stock>="+dateFormatMethod(date_from)+" ");
			}
		}else if( !"".equals(stock_history.getOriginal_stock_to())&&stock_history.getOriginal_stock_to()!=null ){
			queryString.append("and osa_original_stock<="+stock_history.getOriginal_stock_to()+" ");
		}
		if( !"".equals(stock_history.getStock_quantity_from())&&stock_history.getStock_quantity_from()!=null ){
			if( !"".equals(stock_history.getStock_quantity_to())&&stock_history.getStock_quantity_to()!=null ){
				queryString.append("and osa_stock_quantity>="+stock_history.getStock_quantity_from()+" and osa_stock_quantity<="+stock_history.getStock_quantity_to()+" ");
			}else{
				queryString.append("and osa_stock_quantity>="+stock_history.getStock_quantity_from()+" ");
			}
		}else if( !"".equals(stock_history.getStock_quantity_to())&&stock_history.getStock_quantity_to()!=null ){
			queryString.append("and osa_stock_quantity<="+stock_history.getStock_quantity_to()+" ");
		}
		if( !"".equals(stock_history.getStock_price_from())&&stock_history.getStock_price_from()!=null ){
			if( !"".equals(stock_history.getStock_price_to())&&stock_history.getStock_price_to()!=null ){
				queryString.append("and osa_stock_price>="+stock_history.getStock_price_from()+" and osa_stock_price<="+stock_history.getStock_price_to()+" ");
			}else{
				queryString.append("and osa_stock_price>="+stock_history.getStock_price_from()+" ");
			}
		}else if( !"".equals(stock_history.getStock_price_to())&&stock_history.getStock_price_to()!=null ){
			queryString.append("and osa_stock_price<="+stock_history.getStock_price_to()+" ");
		}
		if( !"".equals(stock_history.getStock_amount_from())&&stock_history.getStock_amount_from()!=null ){
			if( !"".equals(stock_history.getStock_amount_to())&&stock_history.getStock_amount_to()!=null ){
				queryString.append("and osa_stock_amount>="+stock_history.getStock_amount_from()+" and osa_stock_amount<="+stock_history.getStock_amount_to()+" ");
			}else{
				queryString.append("and osa_stock_amount>="+stock_history.getStock_amount_from()+" ");
			}
		}else if( !"".equals(stock_history.getStock_amount_to())&&stock_history.getStock_amount_to()!=null ){
			queryString.append("and osa_stock_amount<="+stock_history.getStock_amount_to()+" ");
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
                new RowMapper<StockHistory>(){         
                    @Override  
                    public StockHistory mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	StockHistory stock_history  = new StockHistory();  
                    	stock_history.setId(rs.getString("osa_stock_history_id"));
                    	stock_history.setWarehouse_id(rs.getString("osa_warehouse_id"));
                    	stock_history.setWarehouse_name(rs.getString("warehouse_name"));
                    	stock_history.setProduct_id(rs.getString("osa_product_id"));
                    	stock_history.setProduct_name(rs.getString("product_name"));
                    	stock_history.setDescription(rs.getString("osa_desc"));
                    	stock_history.setRequisition_id(rs.getString("osa_requisition_id"));
                    	stock_history.setRequisition_number(rs.getString("requisition_number"));
                    	stock_history.setOriginal_stock(rs.getInt("osa_original_stock"));
                    	stock_history.setStock_quantity(rs.getInt("osa_stock_quantity"));
                    	stock_history.setStock_price(rs.getDouble("osa_stock_price"));
                    	stock_history.setStock_amount(rs.getDouble("osa_stock_amount"));
                    	stock_history.setStock_type_code(rs.getString("osa_stock_type_cd"));
                    	stock_history.setStock_type_val(rs.getString("osa_stock_type_val"));
                    	stock_history.setCreated_date(rs.getTimestamp("osa_created"));
                    	stock_history.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	stock_history.setCreated_by_user_name(rs.getString("created_by_username"));
                    	//stock_history.setOrder_number(rs.getString("order_number"));
                        return stock_history;  
                    }
        });
	}

}
