package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.ServicesDao;
import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.ServiceItem;
import com.oept.esales.model.Services;
import com.oept.esales.model.User;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/3
 * Description: Services DAO implements.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Repository("servicesDao")
public class ServicesDaoImpl implements ServicesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate get_jdbc11() {
		return jdbcTemplate;
	}

	public void set_jdbc11(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 根据userId查询当前用户服务订单请求(客户)(进行中)
	 */
	@Override
	public List<Services> selectServicesList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select sq.*,u.osa_username from osa_srv_req sq,osa_user u where "
				+ "osa_sr_created_by = ? and sq.osa_sr_owner = u.osa_user_id and sq.osa_sr_status != 3";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceOwnerName(rs.getString("osa_username"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				return services;
			}
			
		});
	}
	
	/**
	 * 根据userId查询当前用户服务订单请求(客服)(进行中)
	 */
	@Override
	public List<Services> selectServicesListSys(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select sq.*,u.osa_username from osa_srv_req sq,osa_user u "
				+ "where osa_sr_owner = ? and sq.osa_sr_owner = u.osa_user_id and sq.osa_sr_status != 3";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceOwnerName(rs.getString("osa_username"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				return services;
			}
			
		});
	}
	
	/**
	 * 根据userId查询当前用户服务订单请求(客户)(已完成)
	 */
	@Override
	public List<Services> selectServicesList2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select sq.*,u.osa_username from osa_srv_req sq,osa_user u where "
				+ "osa_sr_created_by = ? and sq.osa_sr_owner = u.osa_user_id and sq.osa_sr_status = 3";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceOwnerName(rs.getString("osa_username"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				return services;
			}
			
		});
	}
	
	/**
	 * 根据userId查询当前用户服务订单请求(客服)(已完成)
	 */
	@Override
	public List<Services> selectServicesListSys2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select sq.*,u.osa_username from osa_srv_req sq,osa_user u "
				+ "where osa_sr_owner = ? and sq.osa_sr_owner = u.osa_user_id and sq.osa_sr_status = 3";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceOwnerName(rs.getString("osa_username"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				return services;
			}
			
		});
	}

	/**
	 * 查询从事咨询服务职位下服务订单数量最少的职员
	 */
	@Override
	public User selectConSerUser() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select osa_sr_owner from osa_srv_req where osa_sr_owner in "
				+ "(select osa_user_id from  osa_post_per  where osa_position_id="
				+ "(select osa_position_id from osa_position where osa_position_name = '咨询服务'))"
				+ " and osa_sr_type = '咨询' and osa_sr_status != '3' GROUP BY osa_sr_owner ORDER BY count(*) asc limit 1";
		
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setUserId(rs.getString("osa_sr_owner"));
				return user;
			}
			
		});
	}
	
	/**
	 * 查询从事投诉服务职位下服务订单数量最少的职员
	 */
	@Override
	public User selectCompUser() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select osa_sr_owner from osa_srv_req where osa_sr_owner in "
				+ "(select osa_user_id from  osa_post_per  where osa_position_id="
				+ "(select osa_position_id from osa_position where osa_position_name = '投诉服务'))"
				+ " and osa_sr_type = '投诉' and osa_sr_status != '3' GROUP BY osa_sr_owner ORDER BY count(*) asc limit 1";
		
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setUserId(rs.getString("osa_sr_owner"));
				return user;
			}
			
		});
	}
	/**
	 * 添加咨询服务订单
	 */
	@Override
	public int addConSerOrder(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_srv_req (osa_sr_name,osa_sr_desc,osa_sr_type,osa_sr_status,osa_sr_owner,osa_sr_created,osa_sr_created_by,osa_sr_comment) values(?,?,?,1,?,now(),?,?) ";
		
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 查询当前用户是否为咨询服务人员
	 */
	@Override
	public int selectUserPositionConsult(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_post_per where osa_user_id = ? and osa_position_id = 6";
		return jdbcTemplate.queryForInt(sql, params);
	}
	
	/**
	 * 查询当前用户是否为投诉服务人员
	 */
	@Override
	public int selectUserPositionComplaint(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_post_per where osa_user_id = ? and osa_position_id = 10";
		return jdbcTemplate.queryForInt(sql, params);
	}

	/**
	 * 查询咨询服务订单详情子单列表
	 */
	@Override
	public List<ServiceItem> selectServiceDetail(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select oo.*,uo.osa_username updatename from (select o.osa_sr_item_id,o.osa_sr_item_name,"
				+ "o.osa_sr_item_desc,o.osa_sr_item_comm,o.osa_sr_id,o.osa_created,o.osa_created_by,o.osa_update,"
				+ "o.osa_update_by,u.osa_username createdname from (select osa_sr_item_id,osa_sr_item_name,osa_sr_item_desc,"
				+ "osa_sr_item_comm,osa_sr_id,osa_created,osa_created_by,osa_update,osa_update_by from osa_srv_req_item "
				+ " where osa_sr_id = ?) o left join osa_user u on o.osa_created_by = u.osa_user_id)oo left join osa_user uo"
				+ " on oo.osa_update_by = uo.osa_user_id";
		return jdbcTemplate.query(sql, params, new RowMapper<ServiceItem>(){
			@Override
			public ServiceItem mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ServiceItem s = new ServiceItem();
				s.setServiceItemId(rs.getString("osa_sr_item_id"));
				s.setServiceItemName(rs.getString("osa_sr_item_name"));
				s.setServiceItemDesc(rs.getString("osa_sr_item_desc"));
				s.setServiceItemComment(rs.getString("osa_sr_item_comm"));
				s.setServiceId(rs.getString("osa_sr_id"));
				s.setServiceItemCreated(rs.getString("osa_created"));
				s.setServiceItemCreatedBy(rs.getString("osa_created_by"));
				s.setServiceItemUpdate(rs.getString("osa_update"));
				s.setServiceItemUpdateBy(rs.getString("osa_update_by"));
				s.setCreatedName(rs.getString("createdname"));
				s.setUpdateName(rs.getString("updatename"));
				return s;
			}
		});
	}
	
	/**
	 * 查询咨询服务订单详情
	 */
	@Override
	public Services selectConsultDetail2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_srv_req  where osa_sr_id = ?";
		return jdbcTemplate.queryForObject(sql ,params, new RowMapper<Services>(){
			
					@Override
					public Services mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Services services = new Services();
						services.setServiceId(rs.getString("osa_sr_id"));
						services.setServiceName(rs.getString("osa_sr_name"));
						services.setServiceDesc(rs.getString("osa_sr_desc"));
						services.setServiceType(rs.getString("osa_sr_type"));
						services.setServiceStatus(rs.getString("osa_sr_status"));
						services.setServiceOwner(rs.getString("osa_sr_owner"));
						services.setServiceCreated(rs.getString("osa_sr_created"));
						services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
						services.setServiceComment(rs.getString("osa_sr_comment"));
						return services;
					}
				});
	}
	

	/**
	 * 创建咨询服务子订单(客服)
	 */
	@Override
	public int createConSerItem(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_srv_req_item(osa_sr_item_name,osa_sr_item_desc,osa_sr_item_comm,osa_sr_id,osa_created,osa_created_by) values(?,?,?,?,now(),?)";
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 创建咨询服务子订单
	 */
	@Override
	public int createConSerItem2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_srv_req_item(osa_sr_item_name,osa_sr_item_desc,osa_sr_item_comm,osa_sr_id,osa_update,osa_update_by) values(?,?,?,?,now(),?)";
		
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 更改咨询服务订单状态
	 */
	@Override
	public int updateConSerStatus(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_srv_req set osa_sr_status = ? where osa_sr_id = ?";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 根据id查询投诉服务的集合（会员）（未完成）
	 */
	@Override
	public List<Services> selectComplaintList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select srv.* ,o.osa_order_number from (select osa_sr_id,osa_sr_name,"
				+ "osa_sr_desc,osa_sr_type,osa_sr_subtype,osa_sr_status,osa_sr_owner,osa_sr_created,"
				+ "osa_sr_created_by,osa_sr_update,osa_sr_update_by,osa_sr_comment,osa_sr_order_id,"
				+ "osa_sr_level_val,osa_sr_level_name from osa_srv_req where osa_sr_created_by = ?"
				+ " and osa_sr_type = '投诉' and osa_sr_status !=3) srv, osa_order o where "
				+ "srv.osa_sr_order_id = o.osa_order_id ";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setServiceSubtype(rs.getString("osa_sr_subtype"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				services.setLevelVal(rs.getString("osa_sr_level_val"));
				services.setLevelName(rs.getString("osa_sr_level_name"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				services.setOrder(order);
				return services;
			}
			
		});
	}
	
	/**
	 * 根据id查询投诉服务的集合（客服）（未完成）
	 */
	@Override
	public List<Services> selectComplaintList2(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "select srv.* ,o.osa_order_number from (select osa_sr_id,osa_sr_name,osa_sr_desc,"
				+ "osa_sr_type,osa_sr_subtype,osa_sr_status,osa_sr_owner,osa_sr_created,osa_sr_created_by,"
				+ "osa_sr_update,osa_sr_update_by,osa_sr_comment,osa_sr_order_id,osa_sr_level_val,"
				+ "osa_sr_level_name from osa_srv_req where osa_sr_owner = ? and osa_sr_type = '投诉'"
				+ " and osa_sr_status !=3) srv, osa_order o where srv.osa_sr_order_id = o.osa_order_id ";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setServiceSubtype(rs.getString("osa_sr_subtype"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				services.setLevelVal(rs.getString("osa_sr_level_val"));
				services.setLevelName(rs.getString("osa_sr_level_name"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				services.setOrder(order);
				return services;
			}
			
		});
	}
	
	/**
	 * 根据id查询投诉服务的集合（会员）（历史订单）
	 */
	@Override
	public List<Services> selectComplaintListHis(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select srv.* ,o.osa_order_number from (select osa_sr_id,osa_sr_name,osa_sr_desc,"
				+ "osa_sr_type,osa_sr_subtype,osa_sr_status,osa_sr_owner,osa_sr_created,osa_sr_created_by,"
				+ "osa_sr_update,osa_sr_update_by,osa_sr_comment,osa_sr_order_id,osa_sr_level_val,"
				+ "osa_sr_level_name from osa_srv_req where osa_sr_created_by = ? and osa_sr_type = '投诉'"
				+ " and osa_sr_status =3) srv, osa_order o where srv.osa_sr_order_id = o.osa_order_id ";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setServiceSubtype(rs.getString("osa_sr_subtype"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				services.setLevelVal(rs.getString("osa_sr_level_val"));
				services.setLevelName(rs.getString("osa_sr_level_name"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				services.setOrder(order);
				return services;
			}
			
		});
	}
	
	/**
	 * 根据id查询投诉服务的集合（客服）（历史订单）
	 */
	@Override
	public List<Services> selectComplaintListHis2(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "select srv.* ,o.osa_order_number from (select osa_sr_id,osa_sr_name,osa_sr_desc,"
				+ "osa_sr_type,osa_sr_subtype,osa_sr_status,osa_sr_owner,osa_sr_created,osa_sr_created_by,"
				+ "osa_sr_update,osa_sr_update_by,osa_sr_comment,osa_sr_order_id,osa_sr_level_val,"
				+ "osa_sr_level_name from osa_srv_req where osa_sr_owner = ? and osa_sr_type = '投诉' "
				+ "and osa_sr_status =3) srv, osa_order o where srv.osa_sr_order_id = o.osa_order_id ";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setServiceSubtype(rs.getString("osa_sr_subtype"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				services.setLevelVal(rs.getString("osa_sr_level_val"));
				services.setLevelName(rs.getString("osa_sr_level_name"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				services.setOrder(order);
				return services;
			}
			
		});
	}


	/**
	 * 查询id下所有订单编号
	 */
	@Override
	public List<OrderFlat> selectUserOrderId(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_order where osa_created_by = ?";
		
		return jdbcTemplate.query(sql, params, new RowMapper<OrderFlat>(){

			@Override
			public OrderFlat mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				return order;
			}
			
		});
	}

	/**
	 * 添加投诉订单
	 */
	@Override
	public int addComplaintOrder(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_srv_req (osa_sr_name,osa_sr_desc,osa_sr_type,osa_sr_subtype,osa_sr_status,osa_sr_owner,osa_sr_created,osa_sr_created_by,osa_sr_comment,osa_sr_order_id,osa_sr_level_val,osa_sr_level_name) values(?,?,?,?,?,?,now(),?,?,?,?,?)";
		
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 创建投诉子订单
	 */
	@Override
	public int addComplaintOrderItem(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_srv_req_item (osa_sr_item_name, osa_sr_item_desc, osa_sr_item_comm, osa_sr_id, osa_created, osa_created_by) values (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 根据id查询投诉服务订单信息
	 */
	@Override
	public Services selectUserServices(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_srv_req where osa_sr_id = ?";
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceSubtype(rs.getString("osa_sr_subtype"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				services.setLevelVal(rs.getString("osa_sr_level_val"));
				services.setLevelName(rs.getString("osa_sr_level_name"));
				return services;
			}
			
		});
	}

	/**
	 * 添加投诉子订单（会员）
	 */
	@Override
	public int addReplyComplaintOrderItem(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_srv_req_item (osa_sr_item_name, osa_sr_item_desc, osa_sr_item_comm, osa_sr_id, osa_created, osa_created_by) values (?,?,?,?,now(),?)";
		
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 添加投诉子订单（客服）
	 */
	@Override
	public int addReplyComplaintOrderItem2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_srv_req_item (osa_sr_item_name, osa_sr_item_desc, osa_sr_item_comm, osa_sr_id, osa_update, osa_update_by) values (?,?,?,?,now(),?)";
		
		return  jdbcTemplate.update(sql, params);
	}

	/**
	 * 根据id查询投诉服务的集合（投诉级别）（会员）
	 */
	@Override
	public List<Services> selectComplaintListLevel(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select srv.* ,o.osa_order_number from (select osa_sr_id,osa_sr_name,osa_sr_desc,"
				+ "osa_sr_type,osa_sr_subtype,osa_sr_status,osa_sr_owner,osa_sr_created,osa_sr_created_by,"
				+ "osa_sr_update,osa_sr_update_by,osa_sr_comment,osa_sr_order_id,osa_sr_level_val,"
				+ "osa_sr_level_name from osa_srv_req where osa_sr_created_by = ? and osa_sr_type = '投诉'"
				+ " and osa_sr_status !=3 and osa_sr_level_val = ?) srv, osa_order o where"
				+ " srv.osa_sr_order_id = o.osa_order_id ";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setServiceSubtype(rs.getString("osa_sr_subtype"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				services.setLevelVal(rs.getString("osa_sr_level_val"));
				services.setLevelName(rs.getString("osa_sr_level_name"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				services.setOrder(order);
				return services;
			}
			
		});
	}
	
	/**
	 * 根据id查询投诉服务的集合（投诉级别）（客服）
	 */
	@Override
	public List<Services> selectComplaintListLevel2(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "select srv.* ,o.osa_order_number from (select osa_sr_id,osa_sr_name,osa_sr_desc,"
				+ "osa_sr_type,osa_sr_subtype,osa_sr_status,osa_sr_owner,osa_sr_created,osa_sr_created_by,"
				+ "osa_sr_update,osa_sr_update_by,osa_sr_comment,osa_sr_order_id,osa_sr_level_val,"
				+ "osa_sr_level_name from osa_srv_req where osa_sr_owner = ? and osa_sr_type = '投诉'"
				+ " and osa_sr_status !=3 and osa_sr_level_val = ?) srv, osa_order o where"
				+ " srv.osa_sr_order_id = o.osa_order_id ";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){

			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setServiceSubtype(rs.getString("osa_sr_subtype"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				services.setLevelVal(rs.getString("osa_sr_level_val"));
				services.setLevelName(rs.getString("osa_sr_level_name"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				services.setOrder(order);
				return services;
			}
			
		});
	}

	/**
	 * 根据服务id查询该订单下是否有子订单
	 */
	@Override
	public int selectSrvIdCompItem(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_srv_req_item where osa_sr_id = ?";
		return jdbcTemplate.queryForInt(sql, params);
	}

	/**
	 * 查询orderId下订单详情
	 */
	@Override
	public OrderFlat selectOrderDetail(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_order where osa_order_id = ?";
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<OrderFlat>(){
			@Override
			public OrderFlat mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				order.setDue_amount(Double.valueOf(rs.getString("osa_order_due_amt")));
				return order;
			}
		});
	}

	/**
	 * 创建退货服务订单
	 */
	@Override
	public int createReturnOrder(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_srv_req(osa_sr_name,osa_sr_desc,osa_sr_type,osa_sr_status,osa_sr_owner,osa_sr_created,osa_sr_created_by,osa_sr_comment,osa_sr_order_id) values (?,?,?,?,?,now(),?,?,?)";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 创建返修服务订单
	 */
	@Override
	public int createRepairOrder(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into osa_srv_req(osa_sr_name,osa_sr_desc,osa_sr_type,osa_sr_status,osa_sr_owner,osa_sr_created,osa_sr_created_by,osa_sr_comment,osa_sr_order_id) values (?,?,?,?,?,now(),?,?,?)";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 查找工作量最少的售后客服人员
	 */
	@Override
	public User selectUserIdAss() throws Exception {
		// TODO Auto-generated method stub==
		String sql = "select osa_user_id from  osa_post_per  where osa_position_id= (select osa_position_id from osa_position where osa_position_name = '售后服务')";
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setUserId(rs.getString("osa_user_id"));
				return user;
			}
		});
	}

	/**
	 * 查询退货服务列表（会员）
	 */
	@Override
	public List<Services> selectReturnList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select s.*,o.* from osa_srv_req s,osa_order o where s.osa_sr_order_id = o.osa_order_id and "
				+ " s.osa_sr_created_by = ? and s.osa_sr_status != 3 and s.osa_sr_type = '退货' and s.osa_sr_status != 7 and s.osa_sr_status != 8";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				order.setDue_amount(Double.valueOf(rs.getString("osa_order_due_amt")));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询退货服务列表（客服）
	 */
	@Override
	public List<Services> selectReturnListSys(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select s.*,o.* from osa_srv_req s,osa_order o where s.osa_sr_order_id = o.osa_order_id and "
				+ " s.osa_sr_owner = ? and s.osa_sr_status != 3 and s.osa_sr_type = '退货' and s.osa_sr_status != 7 and s.osa_sr_status != 8";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				order.setDue_amount(Double.valueOf(rs.getString("osa_order_due_amt")));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询返修服务列表（会员）
	 */
	@Override
	public List<Services> selectRepairList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select s.*,o.* from osa_srv_req s,osa_order o where s.osa_sr_order_id = o.osa_order_id and "
				+ " s.osa_sr_created_by = ? and s.osa_sr_status != 3 and s.osa_sr_type = '返修' and s.osa_sr_status != 7 and s.osa_sr_status != 8";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询返修服务列表（客服）
	 */
	@Override
	public List<Services> selectRepairListSys(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select s.*,o.* from osa_srv_req s,osa_order o where s.osa_sr_order_id = o.osa_order_id and "
				+ " s.osa_sr_owner = ? and s.osa_sr_status != 3 and s.osa_sr_type = '返修' and s.osa_sr_status != 7 and s.osa_sr_status != 8";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询所有已完成退返修服务列表（会员）
	 */
	@Override
	public List<Services> selectOverReturnAllList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select a.* from (select s.*,o.* from osa_srv_req s,osa_order o where "
				+ "s.osa_sr_order_id = o.osa_order_id and s.osa_sr_created_by = ? and s.osa_sr_name = '退返货')"
				+ " a where a.osa_sr_status = 3 or a.osa_sr_status = 8 or a.osa_sr_status = 7";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				order.setDue_amount(Double.valueOf(rs.getString("osa_order_due_amt")));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询所有已完成退返修服务列表（客服）
	 */
	@Override
	public List<Services> selectOverReturnAllListSys(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select a.* from (select s.*,o.* from osa_srv_req s,osa_order o where "
				+ "s.osa_sr_order_id = o.osa_order_id and s.osa_sr_owner = ? and s.osa_sr_name = '退返货')"
				+ " a where a.osa_sr_status = 3 or a.osa_sr_status = 8  or a.osa_sr_status = 7";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				order.setDue_amount(Double.valueOf(rs.getString("osa_order_due_amt")));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询已完成退货服务列表（会员）
	 */
	@Override
	public List<Services> selectOverReturnList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_srv_req where osa_sr_created_by = ? and osa_sr_status = 3 and osa_sr_type = '退货'";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询已完成返修服务列表（会员）
	 */
	@Override
	public List<Services> selectOverRepairList(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_srv_req where osa_sr_created_by = ? and osa_sr_status = 3 and osa_sr_type = '返修'";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询已完成退货服务列表（客服）
	 */
	@Override
	public List<Services> selectOverReturnListSys(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_srv_req where osa_sr_owner = ? and osa_sr_status = 3 and osa_sr_type = '退货'";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询已完成返修服务列表（客服）
	 */
	@Override
	public List<Services> selectOverRepairListSys(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from osa_srv_req where osa_sr_owner = ? and osa_sr_status = 3 and osa_sr_type = '返修'";
		return jdbcTemplate.query(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 查询当前UserId是否为售后服务人员
	 */
	@Override
	public int selectUserIdAss(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from osa_post_per where osa_user_id = ? and osa_position_id = 4";
		return jdbcTemplate.queryForInt(sql, params);
	}

	/**
	 * 查询服务订单详情
	 */
	@Override
	public Services selectServDetail(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select s.*,o.* from osa_srv_req s,osa_order o where s.osa_sr_order_id = o.osa_order_id and s.osa_sr_id = ?";
		return jdbcTemplate.queryForObject(sql, params, new RowMapper<Services>(){
			@Override
			public Services mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Services services = new Services();
				services.setServiceId(rs.getString("osa_sr_id"));
				services.setServiceName(rs.getString("osa_sr_name"));
				services.setServiceDesc(rs.getString("osa_sr_desc"));
				services.setServiceType(rs.getString("osa_sr_type"));
				services.setServiceStatus(rs.getString("osa_sr_status"));
				services.setServiceOwner(rs.getString("osa_sr_owner"));
				services.setServiceCreated(rs.getString("osa_sr_created"));
				services.setServiceCreatedBy(rs.getString("osa_sr_created_by"));
				services.setServiceComment(rs.getString("osa_sr_comment"));
				services.setOrderId(rs.getString("osa_sr_order_id"));
				OrderFlat order = new OrderFlat();
				order.setOrder_id(rs.getString("osa_sr_order_id"));
				order.setOrder_number(rs.getString("osa_order_number"));
				order.setDue_amount(Double.valueOf(rs.getString("osa_order_due_amt")));
				services.setOrder(order);
				return services;
			}
		});
	}

	/**
	 * 更改退返货订单状态
	 */
	@Override
	public int updateRtSerStatus(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_srv_req set osa_sr_status = ? where osa_sr_id = ?";
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 更改退返货订单状态
	 */
	@Override
	public int updateRtSerStatus2(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update osa_srv_req set osa_sr_status = ?,osa_sr_update = now(), osa_sr_update_by =?  where osa_sr_id = ?";
		return jdbcTemplate.update(sql, params);
	}
}
