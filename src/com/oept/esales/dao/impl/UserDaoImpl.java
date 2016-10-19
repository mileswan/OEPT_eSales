package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.UserDao;
import com.oept.esales.model.Address;
import com.oept.esales.model.Position;
import com.oept.esales.model.User;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/06
 * Description: Categories DAO implements.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate get_jdbc11() {
		return jdbcTemplate;
	}

	public void set_jdbc11(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 用户快速注册方法实现接口
	 */
	@Override
	public int signin(User user) throws Exception {
		// TODO Auto-generated method stub
		// SQL语句
		String sql = "insert into osa_user(osa_firstname,osa_lastname,osa_username,osa_password,osa_email_address,osa_address,osa_created,osa_active,osa_delete) values (?,?,?,?,?,?,now(),1,0)";
		// 创建Object数组接收参数
		Object[] params = new Object[] {
			user.getFirstName(),
			user.getLastName(),
			user.getUserName(),
			user.getPassword(),
			user.getEmail(),
			user.getAddress()
		};
		// 调用jdbcTemplate update方法插入数据并获得返回结果
		int results = jdbcTemplate.update(sql, params);
		// 返回结果
		return results;
	}

	/**
	 * 注册验证用户名是否已存在
	 */
	@Override
	public Integer testingUser(String username) {
		// TODO Auto-generated method stub
		String sql = "select osa_user_id from osa_user where osa_username = ?";
		// 调用jdbcTemplate query方法查询数据并获得返回结果
		Integer res = jdbcTemplate.queryForInt(sql, username);
		return res;
	}

	/**
	 * 查询用户列表
	 */
	@Override
	public List<User> selectUserList() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select a.*,b.* from osa_user a left join osa_position b "
				+ "on a.osa_primary_position_id = b.osa_position_id where a.osa_delete = 0";
		// 调用jdbcTemplate query方法查询数据并获得返回结果
		return jdbcTemplate.query(sql, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setUserId(rs.getString("osa_user_id"));
				user.setFirstName(rs.getString("osa_firstname"));
				user.setLastName(rs.getString("osa_lastname"));
				user.setUserName(rs.getString("osa_username"));
				user.setPassword(rs.getString("osa_password"));
				user.setActive(rs.getBoolean("osa_active"));
				user.setEmail(rs.getString("osa_email_address"));
				user.setAddress(rs.getString("osa_address"));
				user.setLastLogin(rs.getString("osa_last_login"));
				user.setPrimaryPositionId(rs.getString("osa_primary_position_id"));
				Position position = new Position();
				position.setPositionName(rs.getString("osa_position_name"));
				user.setPosition(position);
				return user;
			}
		
		});
	}

	
	/**
	 * 查询单个用户详细信息
	 */
	@Override
	public User selectUserDetail(String userId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select a.*,b.* from osa_user a left join osa_position b "
				+ "on a.osa_primary_position_id = b.osa_position_id where a.osa_user_id = ?";
		Object[] params = new Object[]{
				userId
		};
		// 调用jdbcTemplate query方法查询数据并获得返回结果
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setUserId(rs.getString("osa_user_id"));
				user.setFirstName(rs.getString("osa_firstname"));
				user.setLastName(rs.getString("osa_lastname"));
				user.setUserName(rs.getString("osa_username"));
				user.setPassword(rs.getString("osa_password"));
				user.setActive(rs.getBoolean("osa_active"));
				user.setEmail(rs.getString("osa_email_address"));
				user.setAddress(rs.getString("osa_address"));
				user.setLastLogin(rs.getString("osa_last_login"));
				user.setPrimaryPositionId(rs.getString("osa_primary_position_id"));
				//读取出职位信息存入user对象
				Position position = new Position();
				position.setPositionName(rs.getString("osa_position_name"));
				position.setPositionId(rs.getString("osa_position_id"));
				user.setPosition(position);
				return user;
			}
		});
	}

	/**
	 * 更新用户信息，参数有密码
	 */
	@Override
	public int updateUserDetailAndPwd(Object[] params) {
		// TODO Auto-generated method stub
		String sql = "update osa_user set osa_password=?,osa_email_address=?,osa_address=?,osa_lastname=?,osa_firstname=?,osa_username=?,osa_active=?,osa_update = now(),osa_update_by = ? where osa_user_id = ?";
		// 调用jdbcTemplate update方法更新数据并获得返回结果
		int result = jdbcTemplate.update(sql, params);
		return result;
	}

	/**
	 * 更新用户信息，参数没有密码
	 */
	@Override
	public int updateUserDetailNoPwd(Object[] params) {
		// TODO Auto-generated method stub
		String sql = "update osa_user set osa_email_address=?,osa_address=?,osa_lastname=?,osa_firstname=?,osa_username=?,osa_active=?,osa_update = now(),osa_update_by = ? where osa_user_id = ?";
		// 调用jdbcTemplate update方法更新数据并获得返回结果
		int result = jdbcTemplate.update(sql, params);
		return result;
	}

	/**
	 * 根据id删除用户
	 */
	@Override
	public Integer deleteUser(String userId) {
		// TODO Auto-generated method stub
		String sql = "update osa_user set osa_delete = 1 where osa_user_id=?";
		// 调用jdbcTemplate update方法更新数据并获得返回结果
		int result = jdbcTemplate.update(sql, userId);
		return result;
	}

	/**
	 * 创建新用户
	 */
	@Override
	public int newUser(Object[] params) {
		// TODO Auto-generated method stub
		String sql = "insert into osa_user(osa_firstname,osa_lastname,osa_username,osa_password,osa_email_address,osa_address,osa_created,osa_active,osa_delete) values (?,?,?,?,?,?,now(),1,0)";
		// 调用jdbcTemplate update方法插入数据并获得返回结果
		int result = jdbcTemplate.update(sql, params);
		return result;
	}

	/**
	 * 保存收货地址
	 */
	@Override
	public int newAddress(Address address) {
		// TODO Auto-generated method stub
		String sql = "";
		String sql1 = "insert into osa_address(osa_addr_name,osa_addr_country,osa_addr_province,osa_addr_city,osa_addr_county,osa_addr_created,osa_addr_created_by,osa_addr_update,osa_addr_update_by";
		String sql2 = ") values ('"+address.getAllAddress()+"','"+address.getCountry()+"','"+address.getProvince()+"','"+address.getCity()+"','"+address.getCounty()+"',now(),'"+address.getCreatedBy()+"',now(),'"+address.getUpdateBy()+"'";
		String sql3 = ")";
		String sql_1 = "";
		String sql_2 = "";
		if(address.getZipcode()!=null&&!address.getZipcode().equals("")){
			sql_1 = sql_1+",osa_addr_zipcode";
			sql_2 = sql_2 + ",'" + address.getZipcode() + "'";
		}
		if(address.getContactName()!=null&&!address.getContactName().equals("")){
			sql_1 = sql_1+",osa_addr_contact_name";
			sql_2 = sql_2 + ",'" + address.getContactName() + "'";
		}
		if(address.getContactCell()!=null&&!address.getContactCell().equals("")){
			sql_1 = sql_1+",osa_addr_contact_cell";
			sql_2 = sql_2 + ",'" + address.getContactCell() + "'";
		}
		if(address.getStreet()!=null&&!address.getStreet().equals("")){
			sql_1 = sql_1+",osa_addr_street";
			sql_2 = sql_2 + ",'" + address.getStreet() + "'";
		}
		
		sql = sql1 + sql_1 + sql2 + sql_2 + sql3;
		
//		String sql = "insert into osa_address(osa_addr_name,osa_addr_province,osa_addr_city,osa_addr_county,"
//				+ "osa_addr_zipcode,osa_addr_contact_name,osa_addr_contact_cell,osa_addr_street,osa_addr_created,"
//				+ "osa_addr_created_by,osa_addr_country) values ("+address.getAllAddress()+","+address.getProvince()+","+
//				address.getCity()+","+address.getCounty()+","+address.getZipcode()+","+address.getContactName()+","+
//				address.getContactCell()+","+address.getStreet()+",now(),"+address.getCreatedBy()+","+address.getCountry()+")";
		// 调用jdbcTemplate update方法插入数据并获得返回结果
		int result = jdbcTemplate.update(sql);
		return result;
	}
	
	/**
	 * 根据id删除地址（管理员状态下删除地址）
	 */
	@Override
	public int deleteAddress(String addressId) {
		// TODO Auto-generated method stub
		String sql = "delete from osa_address where osa_addr_id = ?";
		String sql2 = "delete from osa_addr_per where osa_addr_id=?";
		// 调用jdbcTemplate update方法删除数据并获得返回结果
		jdbcTemplate.update(sql2, addressId);
		return jdbcTemplate.update(sql, addressId);
	}

	/**
	 * 更新收货地址
	 */
	@Override
	public int updateAddress(Object[] params) {
		// TODO Auto-generated method stub
		String sql = "update osa_address set osa_addr_name = ?,osa_addr_province = ?,osa_addr_city = ?,osa_addr_county=?,"
				+ "osa_addr_zipcode=?,osa_addr_contact_name=?,"
				+ "osa_addr_contact_cell=?,osa_addr_street=?,osa_addr_country=?,osa_addr_update=now(),"
				+ "osa_addr_update_by=? where osa_addr_id = ?;";
		// 调用jdbcTemplate update方法更新数据并获得返回结果
		int result = jdbcTemplate.update(sql, params);
		return result;
	}
	
	/**
	 * 根据地址id查询地址信息
	 */
	@Override
	public Address selectAddressDetail(String addressId) {
		// TODO Auto-generated method stub
		String sql = "select * from osa_address where osa_addr_id=?";
		Object[] params = new Object[]{
				addressId
		};
		// 调用jdbcTemplate query方法查询数据并获得返回结果
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<Address>(){

			@Override
			public Address mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
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
				return address;
			}
			
		});
	}
	
	/**
	 * 根据地址id查询地址信息
	 */
	@Override
	public Map<String,Object> selectAddressDetail2(String addressId) {
		// TODO Auto-generated method stub
		String sql = "select * from osa_address where osa_addr_id=?";
		Object[] params = new Object[]{
				addressId
		};
		// 调用jdbcTemplate query方法查询数据并获得返回结果
		return jdbcTemplate.queryForMap(sql,params);
	}

	/**
	 * 查询所有的用户名
	 */
	@Override
	public List<Map<String, Object>> selectAllUserName() {
		// TODO Auto-generated method stub
		String sql = "select osa_username from osa_user";
		List<Map<String, Object>> users = new ArrayList<Map<String,Object>>();
		//调用jdbcTemplate query方法查询数据并获得返回结果
		users = jdbcTemplate.queryForList(sql);
		return users;
	}

	/**
	 * 更新用户地址中间表，分配地址
	 */
	@Override
	public int allotAddress(Object[] params) {
		// TODO Auto-generated method stub
		String sql = "insert into osa_addr_per (osa_addr_id,osa_per_id,osa_created,osa_created_by) values(?,(select osa_user_id from osa_user where osa_username =?),now(),?)";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 根据地址id查询用户名
	 */
	@Override
	public List<User> addrIdSelectUsername(String params) {
		// TODO Auto-generated method stub
		String sql = "select osa_username,osa_user_id from osa_user where osa_user_id in (select osa_per_id from osa_addr_per where osa_addr_id = ?)";
		return jdbcTemplate.query(sql, 
				new RowMapper<User>(){

					@Override
					public User mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						User user = new User();
						user.setUserName(rs.getString("osa_username"));
						user.setUserId(rs.getString("osa_user_id"));
						return user;
					}
			
		},params);
	}

	/**
	 * 查询地址列表(Address)
	 */
	@Override
	public List<Address> selectAddressLists() throws Exception{
		// TODO Auto-generated method stub
		String sql = "select * from osa_address where osa_addr_street is null or osa_addr_street=''";//修改为只选择没有街道信息的区域
		return jdbcTemplate.query(sql,
				new RowMapper<Address>(){
					@Override
					public Address mapRow(ResultSet rs, int rowNum) 
							throws SQLException {
						// TODO Auto-generated method stub
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
						address.setCountry(rs.getString("osa_addr_country"));
						address.setUpdate(rs.getString("osa_addr_update"));
						address.setUpdateBy(rs.getString("osa_addr_update_by"));
						return address;
					}
			
		});
	}

	/**
	 * 删除用户收货地址
	 */
	@Override
	public int deleteUserAddress(Address address,User user) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from osa_addr_per where osa_addr_id=? and osa_per_id=?";
			Object[] params = new Object[] {
					address.getAddressId(),
					user.getUserId()
					};
			return jdbcTemplate.update(sql,params);
	}

	/**
	 * 根据id查询当前用户下所有的收货地址
	 */
	@Override
	public List<Address> personalAddressList(String userId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_address where osa_addr_id in (select osa_addr_id from osa_addr_per where osa_per_id = ?)";
		
		return jdbcTemplate.query(sql, new RowMapper<Address>(){
			@Override
			public Address mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
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
				return address;
			}
		},userId);
	}
	
	/**
	 * 创建用户地址中间表数据
	 */
	@Override
	public int createUesrAddress(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_addr_per (osa_addr_id,osa_per_id,osa_created,osa_created_by) values((select osa_addr_id from osa_address where osa_addr_name = ?),?,now(),?)";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 个人用户删除地址表信息
	 */
	@Override
	public int deleteAddressPersonal(Address address) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from osa_address where osa_addr_id = ?";
		Object[] params = new Object[] {
				address.getAddressId()
				};
		return jdbcTemplate.update(sql,params);
	}

	/**
	 * 查询当前要删除的地址的条数
	 */
	@Override
	public int selectCountAddressUserId(Address address) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_addr_per where osa_addr_id = ?";
		Object[] params = new Object[] {
				address.getAddressId()
		};
		int count = jdbcTemplate.queryForInt(sql,params);
		return count;
	}

	/**
	 * 根据id获取用户默认地址
	 */
	@Override
	public User userDefaultAddress(String  userId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select osa_primary_addr_id from osa_user where osa_user_id = ?";
		Object[] params = new Object[]{
				userId
		};
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setPrimaryAddressId(rs.getString("osa_primary_addr_id"));
				return user;
			}
			
		});
	}

	/**
	 * 更改默认地址
	 */
	@Override
	public int defaultAddress(String userId,String addressId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_user set osa_primary_addr_id = ? where osa_user_id = ?";
		Object[] params = new Object[]{
				addressId,
				userId
		};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 获取密码（验证）
	 */
	@Override
	public User verificationPassword(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select osa_password from osa_user where osa_user_id=?";
		return jdbcTemplate.queryForObject(sql,new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setPassword(rs.getString("osa_password"));
				return user;
			}
		}, params);
	}
	
	/**
	 * 更新密码
	 */
	@Override
	public int updatePersonalPassword(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_user set osa_password = ?,osa_update = now(),osa_update_by = ?"
				+ " where osa_user_id = ?";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 查询职位集合
	 */
	@Override
	public List<Position> selectPositions() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select jcp.osa_position_id,jcp.osa_position_name,jcp.osa_parent_position_id,jcp.osa_created,"
				+ "jcp.osa_created_by,jcp.osa_update,jcp.osa_update_by,jcp.osa_parent_position_name,jcp.osa_created_name,"
				+ "u.osa_username osa_update_name from(select jp.osa_position_id,jp.osa_position_name,jp.osa_parent_position_id,"
				+ "jp.osa_created,jp.osa_created_by,jp.osa_update,jp.osa_update_by,jp.osa_parent_position_name,"
				+ "u.osa_username osa_created_name from (select p.osa_position_id,p.osa_position_name,p.osa_parent_position_id,"
				+ "p.osa_created,p.osa_created_by,p.osa_update,p.osa_update_by,np.osa_position_name osa_parent_position_name "
				+ "from(select osa_position_id,osa_position_name,osa_parent_position_id,osa_created,osa_created_by,osa_update,"
				+ "osa_update_by from osa_position)p,osa_position np where p.osa_parent_position_id = np.osa_position_id)jp left join "
				+ "osa_user u on jp.osa_created_by = u.osa_user_id)jcp left join osa_user u "
				+ "on jcp.osa_update_by = u.osa_user_id order by jcp.osa_position_id";
		return jdbcTemplate.query(sql,new RowMapper<Position>(){
			@Override
			public Position mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Position position = new Position();
				position.setPositionId(rs.getString("osa_position_id"));
				position.setPositionName(rs.getString("osa_position_name"));
				position.setParentPositionId(rs.getString("osa_parent_position_id"));
				position.setParentPositionName(rs.getString("osa_parent_position_name"));
				position.setCreated(rs.getString("osa_created"));
				position.setCreatedBy(rs.getString("osa_created_by"));
				position.setCreatedName(rs.getString("osa_created_name"));
				position.setUpdate(rs.getString("osa_update"));
				position.setUpdateBy(rs.getString("osa_update_by"));
				position.setUpdateName(rs.getString("osa_update_name"));
				return position;
			}
		});
	}

	/**
	 * 更新用户主职位信息
	 */
	@Override
	public int updateUserPrimaryPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_user set osa_primary_position_id = ? where osa_user_id = ?";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 个人职位集合
	 */
	@Override
	public List<Map<String,Object>> personalPositionList(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "select c.osa_post_per_id,c.osa_username,c.osa_position_name,c.osa_created,u.osa_username,c.osa_position_id "
				+ "from osa_user u,(select o.osa_post_per_id,u.osa_username,p.osa_position_name,o.osa_position_id,o.osa_created,o.osa_created_by "
				+ "from osa_user u,osa_post_per o,osa_position p where u.osa_user_id = o.osa_user_id "
				+ "and p.osa_position_id = o.osa_position_id and u.osa_user_id = ?) c where u.osa_user_id = c.osa_created_by";
		return jdbcTemplate.queryForList(sql, params);
	}

	/**
	 * 更新用户职位
	 */
	@Override
	public int updateUserPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_post_per set osa_position_id = ? where osa_user_id = ? and osa_position_id = ?";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 添加用户职位
	 */
	@Override
	public int newUserPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_post_per(osa_user_id,osa_position_id,osa_created,osa_created_by) values (?,?,now(),?)";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 删除用户职位
	 */
	@Override
	public int deleteUserPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from osa_post_per where osa_post_per_id = ?";
		return jdbcTemplate.update(sql,params);
	}

	/**
	 *  查询当前用户职位是否存在
	 */
	@Override
	public int selectUserThisPosition(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_post_per where osa_position_id = ? and osa_user_id = ?";
		return jdbcTemplate.queryForInt(sql, params);
	}

	/**
	 * 创建新职位
	 */
	@Override
	public int createPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_position (osa_position_name,osa_parent_position_id,osa_created,osa_created_by) values (?,?,now(),?)";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 查询该职位
	 */
	@Override
	public Map<String,Object> thisPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_position where osa_position_id = ?";
		return jdbcTemplate.queryForMap(sql,params);
	}

	/**
	 * 查询该职位,返回Position对象
	 */
	@Override
	public Position getPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_position where osa_position_id = ?";
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<Position>(){

			@Override
			public Position mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Position position = new Position();
				position.setPositionId(rs.getString("osa_position_id"));
				position.setPositionName(rs.getString("osa_position_name"));
				position.setParentPositionId(rs.getString("osa_parent_position_id"));
				position.setCreated(rs.getString("osa_created"));
				position.setCreatedBy(rs.getString("osa_created_by"));
				position.setUpdate(rs.getString("osa_update"));
				position.setUpdateBy(rs.getString("osa_update_by"));
				return position;
			}
			
		});
	}
	
	/**
	 * 修改该职位
	 */
	@Override
	public int updatePosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_position set osa_position_name=?,osa_parent_position_id=?,osa_update=now(),osa_update_by=? where osa_position_id=?";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 删除该职位
	 */
	@Override
	public int deletePosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from osa_position where osa_position_id=?";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 条件查询用户集合
	 */
	@Override
	public List<User> getUserLists(User user) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from (select u.*,p.osa_position_id,p.osa_position_name,p.osa_parent_position_id,"
				+ "p.osa_created osa_position_created,p.osa_created_by osa_position_created_by,p.osa_update "
				+ "osa_position_update,p.osa_update_by osa_position_update_by from (select * from osa_user u "
				+ "left join osa_address a on u.osa_primary_addr_id = a.osa_addr_id) u left join osa_position p "
				+ "on u.osa_primary_position_id = p.osa_position_id) u";
		String searchValue = "";
		if(!"".equals(user.isActive())){
			searchValue = searchValue + " u.osa_active = " + user.isActive() + " ";
		}
		if(!"".equals(user.isDelete())){
			if(!"".equals(searchValue)){
				searchValue = searchValue + " and u.osa_delete = " + user.isDelete() + " ";
			}else{
				searchValue = searchValue + " u.osa_delete = " + user.isDelete() + " ";
			}
		}
		if(!"".equals(user.getUserId())&&user.getUserId()!=null){
			if(!"".equals(searchValue)){
				searchValue = searchValue + " and u.osa_user_id = " + user.getUserId() + " ";
			}else{
				searchValue = searchValue + " u.osa_user_id = " + user.getUserId() + " ";
			}
		}
		if(!"".equals(searchValue)){
			sql = sql + " where " + searchValue;
		}
		
		return jdbcTemplate.query(sql, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setUserId(rs.getString("osa_user_id"));
				user.setFirstName(rs.getString("osa_firstname"));
				user.setLastName(rs.getString("osa_lastname"));
				user.setUserName(rs.getString("osa_username"));
				user.setPassword(rs.getString("osa_password"));
				user.setActive(rs.getBoolean("osa_active"));
				user.setEmail(rs.getString("osa_email_address"));
				user.setAddress(rs.getString("osa_address"));
				user.setLastLogin(rs.getString("osa_last_login"));
				user.setPrimaryPositionId(rs.getString("osa_primary_position_id"));
				user.setCreatedTime(rs.getString("osa_created"));
				user.setCreatedId(rs.getString("osa_created_by"));
				user.setUpdated(rs.getString("osa_update"));
				user.setUpdatedBy(rs.getString("osa_update_by"));
				user.setPrimaryAddressId("osa_primary_addr_id");
				
				//读取出职位信息存入user对象
				Position position = new Position();
				position.setPositionName(rs.getString("osa_position_name"));
				position.setPositionId(rs.getString("osa_position_id"));
				user.setPosition(position);
				Address address = new Address();
				address.setAddressId(rs.getString("osa_primary_addr_id"));
				address.setAllAddress(rs.getString("osa_addr_name"));
				user.setAddressObject(address);
				return user;
			}
			
		});
	}

	/**
	 * 查询区域是否已存在
	 */
	@Override
	public boolean queryAddressExist(Address address) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_address where 1=1 ";
		if(!address.getProvince().equals("")&&address.getProvince()!=null){
			sql = sql + " and osa_addr_province = '" + address.getProvince() + "'";
		}
		if(!address.getCity().equals("")&&address.getCity()!=null){
			sql = sql + " and osa_addr_city = '" + address.getCity() + "'";
		}
		if(!address.getCounty().equals("")&&address.getCounty()!=null){
			sql = sql + " and osa_addr_county = '" + address.getCounty() + "'";
		}
		int res = jdbcTemplate.queryForInt(sql);
		if(res != 0){
			return true;
		}else{
			return false;
		}
	}

}
