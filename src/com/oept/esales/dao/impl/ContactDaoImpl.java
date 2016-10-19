package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.ContactDao;
import com.oept.esales.model.Account;
import com.oept.esales.model.Address;
import com.oept.esales.model.Contact;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Categories DAO implements.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate get_jdbc11() {
		return jdbcTemplate;
	}
	public void set_jdbc11(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 添加联系人信息
	 */
	@Override
	public int addContact(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_contact(osa_contact_name,osa_fst_name,osa_last_name,osa_birthday,osa_age,"
				+ "osa_fax,osa_email,osa_cellphone,osa_workphone,osa_title,osa_department,osa_gender,"
				+ "osa_primary_addr_id,osa_account_id,osa_comm,osa_created,osa_created_by,osa_updated,"
				+ "osa_updated_by,osa_active_flg) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,now(),?,true)";
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询联系人集合
	 */
	@Override
	public List<Contact> queryContactList() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_contact c,osa_address a,osa_account ac where c.osa_primary_addr_id = a.osa_addr_id and c.osa_account_id = ac.osa_account_id and c.osa_active_flg = true";
		return jdbcTemplate.query(sql, new RowMapper<Contact>(){
			
			@Override
			public Contact mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Contact con = new Contact();
				con.setContact_id(rs.getString("osa_contact_id"));
				con.setContact_name(rs.getString("osa_contact_name"));
				con.setFirst_name(rs.getString("osa_fst_name"));
				con.setLast_name(rs.getString("osa_last_name"));
				con.setComment(rs.getString("osa_comm"));
				con.setGender(rs.getString("osa_gender"));
				con.setBirthday(rs.getString("osa_birthday"));
				con.setAge(rs.getString("osa_age"));
				con.setDepartment(rs.getString("osa_department"));
				con.setActive_flg(rs.getString("osa_active_flg"));
				con.setTitle(rs.getString("osa_title"));
				con.setEmail(rs.getString("osa_email"));
				con.setCellphone(rs.getString("osa_cellphone"));
				con.setWorkphone(rs.getString("osa_workphone"));
				con.setFax(rs.getString("osa_fax"));
				con.setPrimary_addr_id(rs.getString("osa_primary_addr_id"));
				con.setAccount_id(rs.getString("osa_account_id"));
				con.setCreated(rs.getString("osa_created"));
				con.setCreated_by(rs.getString("osa_created_by"));
				con.setUpdated(rs.getString("osa_updated"));
				con.setUpdated_by(rs.getString("osa_updated_by"));
				Account acc = new Account();
				acc.setAccountId(rs.getString("osa_account_id"));
				acc.setAccountName(rs.getString("osa_accnt_name"));
				con.setAccount(acc);
				Address add = new Address();
				add.setAddressId(rs.getString("osa_primary_addr_id"));
				add.setAllAddress(rs.getString("osa_addr_name"));
				con.setAddress(add);
				return con;
			}
			
		});
	}
	
	/**
	 * 查询联系人详细信息
	 */
	@Override
	public Contact queryContactDetails(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select c.*,a.*,ac.*,u1.osa_username created_name,u2.osa_username update_name from osa_contact c,osa_address a,osa_account ac,osa_user u1,osa_user u2 where c.osa_primary_addr_id = a.osa_addr_id and c.osa_account_id = ac.osa_account_id and c.osa_contact_id = ? and c.osa_active_flg = true and c.osa_created_by = u1.osa_user_id and c.osa_updated_by = u2.osa_user_id";
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<Contact>(){

			@Override
			public Contact mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Contact con = new Contact();
				con.setContact_id(rs.getString("osa_contact_id"));
				con.setContact_name(rs.getString("osa_contact_name"));
				con.setFirst_name(rs.getString("osa_fst_name"));
				con.setLast_name(rs.getString("osa_last_name"));
				con.setComment(rs.getString("osa_comm"));
				con.setGender(rs.getString("osa_gender"));
				con.setBirthday(rs.getString("osa_birthday"));
				con.setAge(rs.getString("osa_age"));
				con.setDepartment(rs.getString("osa_department"));
				con.setActive_flg(rs.getString("osa_active_flg"));
				con.setTitle(rs.getString("osa_title"));
				con.setEmail(rs.getString("osa_email"));
				con.setCellphone(rs.getString("osa_cellphone"));
				con.setWorkphone(rs.getString("osa_workphone"));
				con.setFax(rs.getString("osa_fax"));
				con.setPrimary_addr_id(rs.getString("osa_primary_addr_id"));
				con.setAccount_id(rs.getString("osa_account_id"));
				con.setCreated(rs.getString("osa_created"));
				con.setCreated_by(rs.getString("osa_created_by"));
				con.setUpdated(rs.getString("osa_updated"));
				con.setUpdated_by(rs.getString("osa_updated_by"));
				con.setCreated_name(rs.getString("created_name"));
				con.setUpdate_name(rs.getString("update_name"));
				Account acc = new Account();
				acc.setAccountId(rs.getString("osa_account_id"));
				acc.setAccountName(rs.getString("osa_accnt_name"));
				con.setAccount(acc);
				Address add = new Address();
				add.setAddressId(rs.getString("osa_primary_addr_id"));
				add.setAllAddress(rs.getString("osa_addr_name"));
				con.setAddress(add);
				return con;
			}
			
		});
	}
	
	/**
	 * 更新联系人信息
	 */
	@Override
	public int updateContact(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_contact set osa_contact_name = ?,osa_fst_name = ?,osa_last_name = ?,osa_birthday = ?,osa_age = ?," +
			"osa_fax = ?,osa_email = ?,osa_cellphone = ?,osa_workphone = ?,osa_title = ?,osa_department = ?,osa_gender = ?," +
			"osa_primary_addr_id = ?,osa_account_id = ?,osa_comm = ?,osa_updated = now(),osa_updated_by = ? where osa_contact_id = ?";
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除联系人
	 */
	@Override
	public int delContact(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_contact set osa_active_flg = false where osa_contact_id = ?";
		return jdbcTemplate.update(sql, params);
	}
	
}
