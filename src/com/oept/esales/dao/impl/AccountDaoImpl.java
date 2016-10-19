package com.oept.esales.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.AccountDao;
import com.oept.esales.model.Account;
import com.oept.esales.model.Address;
import com.oept.esales.model.Product;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Categories DAO implements.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Repository("AccountDao")
public class AccountDaoImpl implements AccountDao{

	private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate get_jdbc11() {
		return jdbcTemplate;
	}
	public void set_jdbc11(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 根据节点层查询该层下信息集合
	 */
	@Override
	public List<Account> selectTreeLvl(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_account_cat where osa_accnt_cat_lvl = ?";
		return jdbcTemplate.query(sql, params, new RowMapper<Account>(){

			@Override
			public Account mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Account account = new Account();
				account.setCatId(rs.getString("osa_accnt_cat_id"));
				account.setCatName(rs.getString("osa_accnt_cat_name"));
				account.setCatActive(rs.getString("osa_accnt_cat_active"));
				account.setCatParId(rs.getString("osa_accnt_cat_par_id"));
				account.setCatLvl(rs.getString("osa_accnt_cat_lvl"));
				account.setCatCreated(rs.getString("osa_created"));
				account.setCatCreatedBy(rs.getString("osa_created_by"));
				account.setCatUpdate(rs.getString("osa_updated"));
				account.setCatUpdateBy(rs.getString("osa_updated_by"));
				account.setCatDesc(rs.getString("osa_accnt_cat_desc"));
				return account;
			}
			
		});
	}
	
	/**
	 * 查询Account tree最大层数
	 */
	@Override
	public int selectTreeMaxLvl() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select max(osa_accnt_cat_lvl) from osa_account_cat";
		return jdbcTemplate.queryForInt(sql);
	}
	
	/**
	 * 查询所有父类名称
	 */
	@Override
	public List<Account> selectAllParentName() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_account_cat order by osa_accnt_cat_lvl asc";
		return jdbcTemplate.query(sql, new RowMapper<Account>(){
			@Override
			public Account mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Account account = new Account();
				account.setCatId(rs.getString("osa_accnt_cat_id"));
				account.setCatName(rs.getString("osa_accnt_cat_name"));
				account.setCatActive(rs.getString("osa_accnt_cat_active"));
				account.setCatParId(rs.getString("osa_accnt_cat_par_id"));
				account.setCatLvl(rs.getString("osa_accnt_cat_lvl"));
				account.setCatCreated(rs.getString("osa_created"));
				account.setCatCreatedBy(rs.getString("osa_created_by"));
				account.setCatUpdate(rs.getString("osa_updated"));
				account.setCatUpdateBy(rs.getString("osa_updated_by"));
				account.setCatDesc(rs.getString("osa_accnt_cat_desc"));
				return account;
			}
		});
	}
	
	/**
	 * 根据id查询单位节点信息
	 */
	@Override
	public Account selectAcDeId(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_account_cat where osa_accnt_cat_id = ?";
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<Account>(){
			@Override
			public Account mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Account account = new Account();
				account.setCatId(rs.getString("osa_accnt_cat_id"));
				account.setCatName(rs.getString("osa_accnt_cat_name"));
				account.setCatActive(rs.getString("osa_accnt_cat_active"));
				account.setCatParId(rs.getString("osa_accnt_cat_par_id"));
				account.setCatLvl(rs.getString("osa_accnt_cat_lvl"));
				account.setCatCreated(rs.getString("osa_created"));
				account.setCatCreatedBy(rs.getString("osa_created_by"));
				account.setCatUpdate(rs.getString("osa_updated"));
				account.setCatUpdateBy(rs.getString("osa_updated_by"));
				account.setCatDesc(rs.getString("osa_accnt_cat_desc"));
				return account;
			}
		});
	}
	/**
	 * 添加单位tree节点
	 */
	@Override
	public int addAccountCat(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_account_cat(osa_accnt_cat_name,osa_accnt_cat_active,"
				+ "osa_accnt_cat_par_id,osa_accnt_cat_lvl,osa_created,osa_created_by) values(?,1,?,?,now(),?)";
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更改单位tree节点
	 */
	@Override
	public int updateAccountCat(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_account_cat set osa_accnt_cat_name = ?,osa_accnt_cat_par_id = ?,osa_accnt_cat_lvl = ?,"
				+ "osa_updated = now(),osa_updated_by = ? where osa_accnt_cat_id = ?";
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 根据id删除节点
	 */
	@Override
	public int deleteAcNode(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from osa_account_cat where osa_accnt_cat_id = ?";
		return jdbcTemplate.update(sql, params);
	}
	
	
	/**
	 * 根据catId查询所有单位信息(客户)
	 */
	@Override
	public List<Account> selectAllAtDe(Account account) throws Exception {
		// TODO Auto-generated method stub
		//String sql = "select * from (select * from osa_account,osa_address where osa_primary_addr_id = osa_addr_id) a where  osa_active_flg = true";
		String sql = "select *,c.osa_username created_by_name,d.osa_username updated_by_name "
				+ "from osa_account a INNER JOIN osa_address b on a.osa_primary_addr_id = b.osa_addr_id "
				+ "LEFT JOIN osa_user c on a.osa_created_by=c.osa_user_id "
				+ "LEFT JOIN osa_user d on a.osa_updated_by=d.osa_user_id WHERE a.osa_active_flg = TRUE";
		
		if(account.getaCatId()!=null&&!account.getaCatId().equals("")){
			sql = sql + " and  osa_accnt_cat_id = '" + account.getaCatId() + "'";
		}
		if(account.getAccountType()!=null&&!account.getAccountType().equals("")){
			sql = sql + " and osa_accnt_type = '" + account.getAccountType() + "'";
		}
		return jdbcTemplate.query(sql, new RowMapper<Account>(){
			@Override
			public Account mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Account account = new Account();
				account.setAccountId(rs.getString("osa_account_id"));
				account.setAccountName(rs.getString("osa_accnt_name"));
				account.setAccountNumber(rs.getString("osa_accnt_number"));
				account.setAccountType(rs.getString("osa_accnt_type"));
				account.setaCatId(rs.getString("osa_accnt_cat_id"));
				account.setWorkphone(rs.getString("osa_workphone"));
				account.setPrimaryAddrId(rs.getString("osa_primary_addr_id"));
				account.setAddrName(rs.getString("osa_primary_addr_name"));
				account.setFax(rs.getString("osa_fax"));
				account.setEmail(rs.getString("osa_email"));
				account.setCreated(rs.getString("osa_created"));
				account.setCreatedById(rs.getString("osa_created_by"));
				account.setCreatedBy(rs.getString("created_by_name"));
				account.setUpdate(rs.getString("osa_updated"));
				account.setUpdateById(rs.getString("osa_updated_by"));
				account.setUpdateBy(rs.getString("updated_by_name"));
				account.setAccountComm(rs.getString("osa_account_comm"));
				account.setPrimaryShipaddrId(rs.getString("osa_primary_shipaddr_id"));
				account.setPrimarybilladdrId(rs.getString("osa_primary_billaddr_id"));
				account.setAccountStatus(rs.getString("osa_account_status"));
				account.setAccountStatusVal(rs.getString("osa_account_status_val"));
				account.setActive(rs.getBoolean("osa_active_flg"));
//				account.setAddressName(rs.getString("osa_primary_addr_name"));
//				account.setShipAddressName(rs.getString("osa_primary_shipaddr_name"));
//				account.setBillAddressName(rs.getString("osa_primary_billaddr_name"));
				Address address = new Address();
				address.setAddressId(rs.getString("osa_addr_id"));
				address.setAllAddress(rs.getString("osa_addr_name"));
				address.setProvince(rs.getString("osa_addr_province"));
				address.setCity(rs.getString("osa_addr_city"));
				address.setCounty(rs.getString("osa_addr_county"));
				address.setZipcode(rs.getString("osa_addr_zipcode"));
				address.setContactName(rs.getString("osa_addr_contact_name"));
				address.setContactCell(rs.getString("osa_addr_contact_cell"));
				address.setStreet(rs.getString("osa_addr_street"));
				address.setCreated(rs.getString("osa_addr_created"));
				address.setUpdate(rs.getString("osa_addr_update"));
				address.setCreatedBy(rs.getString("osa_addr_created_by"));
				address.setUpdateBy(rs.getString("osa_addr_update_by"));
				address.setCountry(rs.getString("osa_addr_country"));
				account.setAddress(address);
				return account;
			}
		});
	}
	
	
	/**
	 * 根据aId查询单位信息详情
	 */
	@Override
	public Account selectAtDe(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select *,c.osa_username created_by_name,d.osa_username updated_by_name "
				+ "from osa_account a INNER JOIN osa_address b "
				+ "ON a.osa_primary_addr_id = b.osa_addr_id "
				+ "LEFT JOIN osa_user c on a.osa_created_by=c.osa_user_id "
				+ "LEFT JOIN osa_user d on a.osa_updated_by=d.osa_user_id where osa_account_id = ?";
		//String sql = "select * from osa_account,osa_address where osa_primary_addr_id = osa_addr_id and osa_account_id = ?";
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<Account>(){

			@Override
			public Account mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Account account = new Account();
				account.setAccountId(rs.getString("osa_account_id"));
				account.setAccountName(rs.getString("osa_accnt_name"));
				account.setAccountNumber(rs.getString("osa_accnt_number"));
				account.setAccountType(rs.getString("osa_accnt_type"));
				account.setaCatId(rs.getString("osa_accnt_cat_id"));
				account.setWorkphone(rs.getString("osa_workphone"));
				account.setPrimaryAddrId(rs.getString("osa_primary_addr_id"));
				account.setFax(rs.getString("osa_fax"));
				account.setEmail(rs.getString("osa_email"));
				account.setCreated(rs.getString("osa_created"));
				account.setCreatedById(rs.getString("osa_created_by"));
				account.setCreatedBy(rs.getString("created_by_name"));
				account.setUpdate(rs.getString("osa_updated"));
				account.setUpdateById(rs.getString("osa_updated_by"));
				account.setUpdateBy(rs.getString("updated_by_name"));
				account.setAccountComm(rs.getString("osa_account_comm"));
				account.setPrimaryShipaddrId(rs.getString("osa_primary_shipaddr_id"));
				account.setPrimarybilladdrId(rs.getString("osa_primary_billaddr_id"));
				account.setAccountStatus(rs.getString("osa_account_status"));
				account.setActive(rs.getBoolean("osa_active_flg"));
				account.setOtherAddress1(rs.getString("osa_other_addr_name1"));
				account.setOtherAddress2(rs.getString("osa_other_addr_name2"));
				account.setAddrName(rs.getString("osa_primary_addr_name"));
				account.setZipcode(rs.getString("osa_zipcode"));
				account.setAccountDesc(rs.getString("osa_account_desc"));
//				account.setAddressName(rs.getString("osa_primary_addr_name"));
//				account.setShipAddressName(rs.getString("osa_primary_shipaddr_name"));
//				account.setBillAddressName(rs.getString("osa_primary_billaddr_name"));
				Address address = new Address();
				address.setAddressId(rs.getString("osa_addr_id"));
				address.setAllAddress(rs.getString("osa_addr_name"));
				address.setProvince(rs.getString("osa_addr_province"));
				address.setCity(rs.getString("osa_addr_city"));
				address.setCounty(rs.getString("osa_addr_county"));
				address.setZipcode(rs.getString("osa_addr_zipcode"));
				address.setContactName(rs.getString("osa_addr_contact_name"));
				address.setContactCell(rs.getString("osa_addr_contact_cell"));
				address.setStreet(rs.getString("osa_addr_street"));
				address.setCreated(rs.getString("osa_addr_created"));
				address.setUpdate(rs.getString("osa_addr_update"));
				address.setCreatedBy(rs.getString("osa_addr_created_by"));
				address.setUpdateBy(rs.getString("osa_addr_update_by"));
				address.setCountry(rs.getString("osa_addr_country"));
				account.setAddress(address);
				return account;
			}
			
		});
	}
	
	/**
	 * 添加单位
	 */
	@Override
	public int addAt(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_account(osa_accnt_name,osa_accnt_number,osa_accnt_type,"
				+ "osa_accnt_cat_id,osa_workphone,osa_primary_addr_id,osa_other_addr_name1,osa_other_addr_name2,osa_fax,osa_email,osa_created,"
				+ "osa_created_by,osa_account_desc,osa_primary_addr_name,osa_zipcode,osa_account_status,osa_account_status_val,"
				+ "osa_active_flg) values(?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,true)";
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更改单位
	 */
	@Override
	public int editAt(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_account set osa_accnt_name=?,osa_accnt_number=?,osa_accnt_type=?,osa_accnt_cat_id=?,"
				+ "osa_workphone=?,osa_primary_addr_id=?,osa_primary_addr_name=?,osa_fax=?,osa_email=?,osa_zipcode=?,"
				+ "osa_updated=now(),osa_updated_by=?,osa_account_desc=?,osa_account_comm=?,osa_other_addr_name1=?,"
				+ "osa_other_addr_name2=? where osa_account_id =?";
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 根据aId冻结该信息
	 */
	@Override
	public int deleteAt(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_account set osa_active_flg=false,osa_accnt_cat_id = null where osa_account_id=?";
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询指定条件的单位信息
	 */
	@Override
	public List<Account> getAccount(Account account)
			throws Exception {
		// TODO Auto-generated method stub
//		String sql = "select * from (select c.osa_account_id,c.osa_accnt_name,c.osa_accnt_number,c.osa_accnt_type,"
//				+ "c.osa_accnt_cat_id,c.osa_workphone,c.osa_primary_addr_id,c.osa_fax,c.osa_email,c.osa_created,"
//				+ "c.osa_created_by,c.osa_updated,c.osa_updated_by,c.osa_account_comm,c.osa_primary_shipaddr_id,"
//				+ "c.osa_primary_billaddr_id,c.osa_account_status,c.osa_active_flg,c.osa_primary_addr_name,"
//				+ "c.osa_primary_billaddr_name,d.osa_addr_name osa_primary_shipaddr_name  from  (select "
//				+ "c.osa_account_id,c.osa_accnt_name,c.osa_accnt_number,c.osa_accnt_type,c.osa_accnt_cat_id,"
//				+ "c.osa_workphone,c.osa_primary_addr_id,c.osa_fax,c.osa_email,c.osa_created,c.osa_created_by,"
//				+ "c.osa_updated,c.osa_updated_by,c.osa_account_comm,c.osa_primary_shipaddr_id,"
//				+ "c.osa_primary_billaddr_id,c.osa_account_status,c.osa_active_flg,c.osa_primary_addr_name,"
//				+ "d.osa_addr_name osa_primary_billaddr_name from  (select c.osa_account_id,c.osa_accnt_name,"
//				+ "c.osa_accnt_number,c.osa_accnt_type,c.osa_accnt_cat_id,c.osa_workphone,c.osa_primary_addr_id,"
//				+ "c.osa_fax,c.osa_email,c.osa_created,c.osa_created_by,c.osa_updated,c.osa_updated_by,"
//				+ "c.osa_account_comm,c.osa_primary_shipaddr_id,c.osa_primary_billaddr_id,c.osa_account_status,"
//				+ "c.osa_active_flg,d.osa_addr_name osa_primary_addr_name from osa_account c,osa_address d where "
//				+ "c.osa_primary_addr_id = d.osa_addr_id) c,osa_address d where c.osa_primary_billaddr_id = "
//				+ "d.osa_addr_id ) c,osa_address d where c.osa_primary_shipaddr_id = d.osa_addr_id) c ";
		String sql = "select c.osa_account_id,c.osa_accnt_name,c.osa_accnt_number,c.osa_accnt_type,"
				+ "c.osa_accnt_cat_id,c.osa_workphone,c.osa_primary_addr_id,c.osa_fax,c.osa_email,c.osa_created,"
				+ "c.osa_created_by,c.osa_updated,c.osa_updated_by,c.osa_account_comm,c.osa_primary_shipaddr_id,"
				+ "c.osa_primary_billaddr_id,c.osa_account_status,c.osa_active_flg,c.osa_primary_addr_name,"
				+ "shipaddr.osa_addr_name osa_primary_shipaddr_name,billaddr.osa_addr_name osa_primary_billaddr_name,"
				+ "d.osa_addr_country country,d.osa_addr_province province,d.osa_addr_city city,d.osa_addr_county county" 
				+ " from osa_account c inner join osa_address d on c.osa_primary_addr_id=d.osa_addr_id"
				+ " LEFT JOIN osa_address shipaddr on c.osa_primary_shipaddr_id=shipaddr.osa_addr_id "
				+ "LEFT JOIN osa_address billaddr on c.osa_primary_billaddr_id=billaddr.osa_addr_id where 1=1";
		if(!"".equals(account.getAccountType())&&account.getAccountType() != null&&!"".equals(account.isActive()) ){
			sql = sql + " and c.osa_accnt_type = '" + account.getAccountType() + "' and c.osa_active_flg = " + account.isActive();
		}else if(!"".equals(account.isActive())){
			sql = sql + " and c.osa_active_flg = " + account.isActive();
		}else if(!"".equals(account.getAccountType())&&account.getAccountType() != null ){
			sql = sql + " and c.osa_accnt_type = '" + account.getAccountType() + "'";
		}
		
		if(!"".equals(account.getAccountStatus())&&account.getAccountStatus() != null ){
			sql = sql + " and c.osa_account_status = '" + account.getAccountStatus() + "'";
		}
		
		return jdbcTemplate.query(sql, new RowMapper<Account>(){
			@Override
			public Account mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Account account = new Account();
				Address address = new Address();
				address.setCountry(rs.getString("country"));
				address.setProvince(rs.getString("province"));
				address.setCity(rs.getString("city"));
				address.setCounty(rs.getString("county"));
				account.setAddress(address);
				
				account.setAccountId(rs.getString("osa_account_id"));
				account.setAccountName(rs.getString("osa_accnt_name"));
				account.setAccountNumber(rs.getString("osa_accnt_number"));
				account.setAccountType(rs.getString("osa_accnt_type"));
				account.setaCatId(rs.getString("osa_accnt_cat_id"));
				account.setWorkphone(rs.getString("osa_workphone"));
				account.setPrimaryAddrId(rs.getString("osa_primary_addr_id"));
				account.setFax(rs.getString("osa_fax"));
				account.setEmail(rs.getString("osa_email"));
				account.setCreated(rs.getString("osa_created"));
				account.setCreatedBy(rs.getString("osa_created_by"));
				account.setUpdate(rs.getString("osa_updated"));
				account.setUpdateBy(rs.getString("osa_updated_by"));
				account.setAccountComm(rs.getString("osa_account_comm"));
				account.setPrimaryShipaddrId(rs.getString("osa_primary_shipaddr_id"));
				account.setPrimarybilladdrId(rs.getString("osa_primary_billaddr_id"));
				account.setAccountStatus(rs.getString("osa_account_status"));
				account.setActive(rs.getBoolean("osa_active_flg"));
				account.setAddressName(rs.getString("osa_primary_addr_name"));
				account.setShipAddressName(rs.getString("osa_primary_shipaddr_name"));
				account.setBillAddressName(rs.getString("osa_primary_billaddr_name"));
				
				return account;
			}
		});
	}
	
	/**
	 * 根据catId查询该节点下的子节点
	 */
	@Override
	public List<Account> setAccountCatCnode(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_account_cat where osa_accnt_cat_par_id = ?";
		return jdbcTemplate.query(sql, params, new RowMapper<Account>(){
			@Override
			public Account mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Account account = new Account();
				account.setCatId(rs.getString("osa_accnt_cat_id"));
				account.setCatName(rs.getString("osa_accnt_cat_name"));
				account.setCatActive(rs.getString("osa_accnt_cat_active"));
				account.setCatParId(rs.getString("osa_accnt_cat_par_id"));
				account.setCatLvl(rs.getString("osa_accnt_cat_lvl"));
				account.setCatCreated(rs.getString("osa_created"));
				account.setCatCreatedBy(rs.getString("osa_created_by"));
				account.setCatUpdate(rs.getString("osa_updated"));
				account.setCatUpdateBy(rs.getString("osa_updated_by"));
				account.setCatDesc(rs.getString("osa_accnt_cat_desc"));
				return account;
			}
		});
	}
	
	/**
	 * 查询该节点下子节点数目
	 */
	@Override
	public int setAccountCatCnodeCount(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_account_cat where osa_accnt_cat_par_id = ?";
		return jdbcTemplate.queryForInt(sql, params);
	}
	
	/**
	 * 更改单位信息关联字段cId
	 */
	@Override
	public int delectAtc(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_account set osa_accnt_cat_id = null where osa_account_id = ?";
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更改单位
	 */
	@Override
	public boolean updateAccount(Account account) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("update osa_account set ");
			List<Object> params = new ArrayList<Object>();
			if(!account.getAccountStatus().equals("") && account.getAccountStatus()!=null){
				queryString.append("osa_account_status = ?,");
				params.add(account.getAccountStatus());
			}
			if(!account.getAccountStatusVal().equals("") && account.getAccountStatusVal()!=null){
				queryString.append("osa_account_status_val = ?,");
				params.add(account.getAccountStatusVal());
			}
			queryString.append("osa_updated = now(), osa_updated_by = ? where osa_account_id = ?");
			params.add(account.getUpdateBy());
			params.add(account.getAccountId());
			Object[] param = (Object[])params.toArray(new Object[params.size()]);
			jdbcTemplate.update(queryString.toString(),param);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 读取单位总数
	 */
	@Override
	public int getAccountCount() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) as count from osa_account";
		return jdbcTemplate.queryForInt(sql);
	}
	
	/**
	 * 读取需要审核的单位
	 */
	@Override
	public List<Account> getAccountsForApprover(Account account,
			String approver_id, String start, String limit, String sortColumn,
			String sortDir) throws Exception {
		// TODO Auto-generated method stub
//		String sql = "select a.osa_account_id,a.osa_accnt_name,a.osa_accnt_number,a.osa_accnt_type,a.osa_accnt_cat_id,"
//				+ "a.osa_workphone,a.osa_primary_addr_id,a.osa_fax,a.osa_email,a.osa_created,a.osa_created_by,"
//				+ "a.osa_updated,a.osa_updated_by,a.osa_account_desc,a.osa_primary_addr_name,a.osa_zipcode,"
//				+ "a.osa_account_status,a.osa_account_status_val,c.osa_accnt_cat_name categoryName,a.osa_active_flg,"
//				+ "u1.osa_username createdByName,u2.osa_username updateByName from osa_account a inner join "
//				+ "osa_account_cat c on a.osa_accnt_cat_id = c.osa_accnt_cat_id inner join osa_user "
//				+ "u1 on a.osa_created_by = u1.osa_user_id left outer join osa_user u2 on a.osa_updated_by = "
//				+ "u2.osa_user_id inner join osa_approval_step r1 on r1.osa_account_id = a.osa_account_id "
//				+ "inner join osa_approval_detail r2 on r1.osa_approval_step_id = r2.osa_approval_step_id where "
//				+ "r1.osa_process_flg = true and r2.osa_approval_status_cd = 'pending' and r2.osa_to_approve= '"+approver_id+"' ";
		
		String sql = "select a.osa_account_id,a.osa_accnt_number,a.osa_accnt_name,a.osa_accnt_type,c.osa_accnt_cat_name categoryName,a.osa_updated,a.osa_account_status,a.osa_accnt_cat_id,"
				+ "a.osa_workphone,a.osa_primary_addr_id,a.osa_fax,a.osa_email,a.osa_created,a.osa_created_by,"
				+ "a.osa_updated_by,a.osa_account_desc,a.osa_primary_addr_name,a.osa_zipcode,"
				+ "a.osa_account_status_val,a.osa_active_flg,"
				+ "u1.osa_username createdByName,u2.osa_username updateByName from osa_account a inner join "
				+ "osa_account_cat c on a.osa_accnt_cat_id = c.osa_accnt_cat_id inner join osa_user "
				+ "u1 on a.osa_created_by = u1.osa_user_id left outer join osa_user u2 on a.osa_updated_by = "
				+ "u2.osa_user_id inner join osa_approval_step r1 on r1.osa_account_id = a.osa_account_id "
				+ "inner join osa_approval_detail r2 on r1.osa_approval_step_id = r2.osa_approval_step_id where "
				+ "r1.osa_process_flg = true and r2.osa_approval_status_cd = 'pending' and r2.osa_to_approve=? ";
		Object[] params = new Object[] {approver_id};
		StringBuffer queryString = new StringBuffer();
		queryString.append(sql);
		
		if( !"".equals(account.getAccountNumber())&&account.getAccountNumber()!=null  ){
			queryString.append("and osa_accnt_number ='"+account.getAccountNumber()+"' ");
		}
		
		
		if ((!"".equals(sortColumn) && sortColumn != null)) {
			queryString.append(" order by " + sortColumn + " " + sortDir);
		}
		if ((!"".equals(start) && start != null)) {
			if ("".equals(limit) || limit == null) {
				queryString.append(" limit " + start + ",-1");
			} else {
				queryString.append(" limit " + start + "," + limit);
			}
		}
		return jdbcTemplate.query(queryString.toString(),params, new RowMapper<Account>(){

			@Override
			public Account mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Account account = new Account();
				account.setAccountId(rs.getString("osa_account_id"));
				account.setAccountName(rs.getString("osa_accnt_name"));
				account.setAccountNumber(rs.getString("osa_accnt_number"));
				account.setAccountType(rs.getString("osa_accnt_type"));
				account.setaCatId(rs.getString("osa_accnt_cat_id"));
				account.setWorkphone(rs.getString("osa_workphone"));
				account.setPrimaryAddrId(rs.getString("osa_primary_addr_id"));
				account.setFax(rs.getString("osa_fax"));
				account.setEmail(rs.getString("osa_email"));
				account.setCreated(rs.getString("osa_created"));
				account.setCreatedBy(rs.getString("osa_created_by"));
				account.setUpdate(rs.getString("osa_updated"));
				account.setUpdateBy(rs.getString("osa_updated_by"));
				account.setAccountStatus(rs.getString("osa_account_status"));
				account.setAccountStatusVal(rs.getString("osa_account_status_val"));
				account.setZipcode(rs.getString("osa_zipcode"));
				account.setActive(rs.getBoolean("osa_active_flg"));
				account.setAddressName(rs.getString("osa_primary_addr_name"));
				account.setCatName(rs.getString("categoryName"));
				return account;
			}
			
		});
	}
	
	@Override
	public boolean queryAccountExist(Account account) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_account where 1=1 ";
		if(!"".equals(account.getAccountName())&&account.getAccountName()!=null){
			sql = sql + " and osa_accnt_name = '" + account.getAccountName() + "'";
		}
		if(!"".equals(account.getAccountType())&&account.getAccountType()!=null){
			sql = sql + " and osa_accnt_type = '" + account.getAccountType() + "'";
		}
		int res = jdbcTemplate.queryForInt(sql);
		if(res != 0)
			return true;
		else 
			return false;
	}
	
}
