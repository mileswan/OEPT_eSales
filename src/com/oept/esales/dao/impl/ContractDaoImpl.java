package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.ContractDao;
import com.oept.esales.model.Contract;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/11
 * Description: Contract DAO implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Repository("contractDao")
public class ContractDaoImpl implements ContractDao {

	private static final Logger logger = Logger.getLogger(ContractDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private final static String _INSERT_STRING_CONTRACT="insert into osa_contract (osa_contract_name,osa_contract_number,"
			+ "osa_type_cd,osa_type_val,osa_status_cd,osa_status_val,osa_contract_date,"
			+ "osa_account_id,osa_supplier_id,osa_owner_id,osa_project_id,osa_contract_amt,osa_desc,"
			+ "osa_created,osa_created_by,osa_updated,osa_updated_by) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	@Override
	public boolean addContract(Contract contract) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					contract.getName(),
					contract.getNumber(),
					contract.getType_code(),
					contract.getType_value(),
					contract.getStatus_code(),
					contract.getStatus_value(),
					contract.getContract_date(),
					contract.getAccount_id(),
					contract.getSupplier_id(),
					contract.getOwner_id(),
					contract.getProject_id(),
					contract.getAmount(),
					contract.getDescription(),
					contract.getCreated_date(),
					contract.getCreated_by_user_id(),
					contract.getUpdated_date(),
					contract.getUpdated_by_user_id()
					};
			jdbcTemplate.update(_INSERT_STRING_CONTRACT,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	private final static String _SELECT_STRING_ALL="select a.*,b.osa_accnt_name account_name, c.osa_accnt_name supplier_name,"
			+ "d.osa_project_name project_name,e.osa_username owner_name,"
			+ "u1.osa_username createdBy, u2.osa_username updatedBy from osa_contract a left outer join "
			+ "osa_account b on a.osa_account_id=b.osa_account_id left outer join "
			+ "osa_account c on a.osa_supplier_id=c.osa_account_id left outer join "
			+ "osa_project d on a.osa_project_id=d.osa_project_id left outer join "
			+ "osa_user e on a.osa_owner_id=e.osa_user_id inner join "
			+ "osa_user u1 on a.osa_created_by=u1.osa_user_id left outer join "
			+ "osa_user u2 on a.osa_updated_by=u2.osa_user_id";
	@Override
	public List<Contract> getAllContractList() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_STRING_ALL, 
                new RowMapper<Contract>(){         
                    @Override  
                    public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	Contract contract  = new Contract();
                    	contract.setId(rs.getString("osa_contract_id"));
                    	contract.setName(rs.getString("osa_contract_name"));
                    	contract.setNumber(rs.getString("osa_contract_number"));
                    	contract.setType_code(rs.getString("osa_type_cd"));
                    	contract.setType_value(rs.getString("osa_type_val"));
                    	contract.setStatus_code(rs.getString("osa_status_cd"));
                    	contract.setStatus_value(rs.getString("osa_status_val"));
                    	contract.setContract_date(rs.getDate("osa_contract_date"));
                    	contract.setOwner_id(rs.getString("osa_owner_id"));
                    	contract.setOwner_name(rs.getString("owner_name"));
                    	contract.setAccount_id(rs.getString("osa_account_id"));
                    	contract.setSupplier_id(rs.getString("osa_supplier_id"));
                    	contract.setAccount_name(rs.getString("account_name"));
                    	contract.setSupplier_name(rs.getString("supplier_name"));
                    	contract.setDescription(rs.getString("osa_desc"));
                    	contract.setAmount(rs.getDouble("osa_contract_amt"));
                    	contract.setProject_id(rs.getString("osa_project_id"));
                    	contract.setProject_name(rs.getString("osa_project_name"));
                    	contract.setCreated_date(rs.getTimestamp("osa_created"));
                    	contract.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	contract.setCreated_by_user_name(rs.getString("createdBy"));
                    	contract.setUpdated_date(rs.getTimestamp("osa_updated"));
                    	contract.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	contract.setUpdated_by_user_name(rs.getString("updatedBy"));
                        return contract;  
                    }  
        });
	}

	private final static String _SELECT_STRING_ONE = _SELECT_STRING_ALL + " where a.osa_contract_id=?";
	@Override
	public Contract getContractById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject(_SELECT_STRING_ONE, params,
                new RowMapper<Contract>(){         
                    @Override  
                    public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	Contract contract  = new Contract();
                    	contract.setId(rs.getString("osa_contract_id"));
                    	contract.setName(rs.getString("osa_contract_name"));
                    	contract.setNumber(rs.getString("osa_contract_number"));
                    	contract.setType_code(rs.getString("osa_type_cd"));
                    	contract.setType_value(rs.getString("osa_type_val"));
                    	contract.setStatus_code(rs.getString("osa_status_cd"));
                    	contract.setStatus_value(rs.getString("osa_status_val"));
                    	contract.setContract_date(rs.getDate("osa_contract_date"));
                    	contract.setOwner_id(rs.getString("osa_owner_id"));
                    	contract.setOwner_name(rs.getString("owner_name"));
                    	contract.setAccount_id(rs.getString("osa_account_id"));
                    	contract.setSupplier_id(rs.getString("osa_supplier_id"));
                    	contract.setAccount_name(rs.getString("account_name"));
                    	contract.setSupplier_name(rs.getString("supplier_name"));
                    	contract.setDescription(rs.getString("osa_desc"));
                    	contract.setAmount(rs.getDouble("osa_contract_amt"));
                    	contract.setProject_id(rs.getString("osa_project_id"));
                    	contract.setProject_name(rs.getString("osa_project_name"));
                    	contract.setCreated_date(rs.getTimestamp("osa_created"));
                    	contract.setCreated_by_user_id(rs.getString("osa_created_by"));
                    	contract.setCreated_by_user_name(rs.getString("createdBy"));
                    	contract.setUpdated_date(rs.getTimestamp("osa_updated"));
                    	contract.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	contract.setUpdated_by_user_name(rs.getString("updatedBy"));
                        return contract; 
                    }  
        });
	}

	private final static String _UPDATE_STRING_ONE = "UPDATE osa_contract SET osa_contract_name = ?, osa_contract_number = ?,"
			+ "osa_type_cd = ?,osa_type_val=?,osa_contract_date=?,osa_desc=?,osa_owner_id=?,"
			+ "osa_account_id=?,osa_supplier_id=?,osa_project_id=?,osa_contract_amt=?,"
			+ "osa_updated=?,osa_updated_by=? "
			+ "where osa_contract_id=?";
	@Override
	public boolean updateContract(Contract contract) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					contract.getName(),
					contract.getNumber(),
					contract.getType_code(),
					contract.getType_value(),
					contract.getContract_date(),
					contract.getDescription(),
					contract.getOwner_id(),
					contract.getAccount_id(),
					contract.getSupplier_id(),
					contract.getProject_id(),
					contract.getAmount(),
					contract.getUpdated_date(),
					contract.getUpdated_by_user_id(),
					contract.getId()};
			jdbcTemplate.update(_UPDATE_STRING_ONE,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	private final static String _DELETE_STRING_ONE = "DELETE from osa_contract where a.osa_contract_id=?";
	@Override
	public boolean delContractById(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {id };
			jdbcTemplate.update(_DELETE_STRING_ONE,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Contract> getContracts(Contract contract, String start,
			String limit, String sortColumn, String sortDir) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_ALL);
		queryString.append(" where 1=1 ");
		
		//Filter combination
		if( !"".equals(contract.getNumber())&&contract.getNumber()!=null  ){
			queryString.append("and osa_contract_number='"+contract.getNumber()+"' ");
		}
		if( !"".equals(contract.getName())&&contract.getName()!=null ){
			queryString.append("and osa_contract_name='"+contract.getName()+"' ");
		}
		if( !"".equals(contract.getType_code())&&contract.getType_code()!=null ){
			queryString.append("and osa_type_cd="+contract.getType_code()+" ");
		}
		if( !"".equals(contract.getOwner_id())&&contract.getOwner_id()!=null ){
			queryString.append("and osa_owner_id='"+contract.getOwner_id()+"' ");
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
		                new RowMapper<Contract>(){         
		                    @Override  
		                    public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {  
		                    	Contract contract  = new Contract();
		                    	contract.setId(rs.getString("osa_contract_id"));
		                    	contract.setName(rs.getString("osa_contract_name"));
		                    	contract.setNumber(rs.getString("osa_contract_number"));
		                    	contract.setType_code(rs.getString("osa_type_cd"));
		                    	contract.setType_value(rs.getString("osa_type_val"));
		                    	contract.setStatus_code(rs.getString("osa_status_cd"));
		                    	contract.setStatus_value(rs.getString("osa_status_val"));
		                    	contract.setContract_date(rs.getDate("osa_contract_date"));
		                    	contract.setOwner_id(rs.getString("osa_owner_id"));
		                    	contract.setOwner_name(rs.getString("owner_name"));
		                    	contract.setAccount_id(rs.getString("osa_account_id"));
		                    	contract.setSupplier_id(rs.getString("osa_supplier_id"));
		                    	contract.setAccount_name(rs.getString("account_name"));
		                    	contract.setSupplier_name(rs.getString("supplier_name"));
		                    	contract.setDescription(rs.getString("osa_desc"));
		                    	contract.setAmount(rs.getDouble("osa_contract_amt"));
		                    	contract.setProject_id(rs.getString("osa_project_id"));
		                    	contract.setProject_name(rs.getString("osa_project_name"));
		                    	contract.setCreated_date(rs.getTimestamp("osa_created"));
		                    	contract.setCreated_by_user_id(rs.getString("osa_created_by"));
		                    	contract.setCreated_by_user_name(rs.getString("createdBy"));
		                    	contract.setUpdated_date(rs.getTimestamp("osa_updated"));
		                    	contract.setUpdated_by_user_id(rs.getString("osa_updated_by"));
		                    	contract.setUpdated_by_user_name(rs.getString("updatedBy"));
		                        return contract; 
		                    }  
		        });
	}

	private final static String _UPDATE_STRING_STATUS = "UPDATE osa_contract SET osa_status_cd = ?, osa_status_val = ?,"
			+ "osa_updated=?,osa_updated_by=? "
			+ "where osa_contract_id=?";
	@Override
	public boolean updateContractStatus(Contract contract) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					contract.getStatus_code(),
					contract.getStatus_value(),
					contract.getUpdated_date(),
					contract.getUpdated_by_user_id(),
					contract.getId()};
			jdbcTemplate.update(_UPDATE_STRING_STATUS,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

}
