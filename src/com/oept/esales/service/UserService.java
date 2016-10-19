package com.oept.esales.service;

import java.util.List;
import java.util.Map;

import com.oept.esales.model.Address;
import com.oept.esales.model.Category;
import com.oept.esales.model.Position;
import com.oept.esales.model.User;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/16
 * Description: User management operation service interface.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public interface UserService {

	/**
	 * 用户快速注册功能业务接口
	 * @param requestParam
	 * @return
	 */
	public int signin(User user) throws Exception;
	
	/**
	 * 注册验证用户名是否已存在
	 * @param username
	 * @return
	 */
	public Integer testingUser(String username);
	
	/**
	 * 查询用户列表
	 * @return
	 */
	public List<User> selectUserList() throws Exception;
	
	
	/**
	 * 查询单个用户信息
	 * @param userId
	 * @return
	 */
	public User selectUserDetail(String userId) throws Exception;
	
	/**
	 * 更新用户信息，参数带密码
	 * @param params
	 * @return
	 */
	public int updateUserDetailAndPwd(Object[] params);
	
	/**
	 * 更新用户信息，参数不带密码
	 * @param params
	 * @return
	 */
	public int updateUserDetailNoPwd(Object[] params);
	
	/**
	 * 根据id删除user
	 * @param userId
	 * @return
	 */
	public Integer deleteUser(String userId);
	
	/**
	 * 创建新用户
	 * @param params
	 * @return
	 */
	public int newUser(Object[] params);
	
	/**
	 * 保存收货地址
	 * @param params
	 * @return
	 */
	public int newAddress(Address address);
	
	/**
	 * 删除地址
	 * @param addressId
	 * @return
	 */
	public int deleteAddress(String addressId);
	
	/**
	 * 更新收货地址
	 * @param params
	 * @return
	 */
	public int updateAddress(Object[] params);
	
	/**
	 * 根据id查询地址信息
	 * @param userId
	 * @return
	 */
	public Address selectAddressDetail(String addressId);
	/**
	 * 根据id查询地址信息
	 * @param userId
	 * @return
	 */
	public Map<String,Object> selectAddressDetail2(String addressId);
	
	/**
	 * 查询所有用户名
	 * @return
	 */
	public String[] selectAllUserName();
	
	/**
	 * 分配地址
	 * @param params
	 * @return
	 */
	public int allotAddress(Object[] params);
	
	/**
	 * 查询地址列表
	 * @return
	 */
	public List<Address> selectAddressLists() throws Exception;
	
	/**
	 * 根据地址id查询所属用户
	 * @return
	 * @throws Exception
	 */
	public List<User> addrIdSelectUsername(String aId) throws Exception;
	
	/**
	 * 删除用户收货地址
	 * @param address
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteUserAddress(Address address,User user) throws Exception;
	
	/**
	 * 根据id查询当前用户下所有的收货地址
	 * @param userId
	 * @return
	 */
	public List<Address> personalAddressList(String userId) throws Exception;
	
	/**
	 * 创建用户地址中间表数据
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int createUserAddress(Object[] params) throws Exception;
	
	/**
	 * 个人用户删除个人收货地址（若当前要删除地址没有其他人使用就删除）
	 * @param address
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int personalDeleteAddress(Address address, User user) throws Exception;
	
	/**
	 * 根据id获取用户默认地址
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User userDefaultAddress(String userId) throws Exception;
	
	/**
	 * 更改默认地址
	 * @param userId
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public int defaultAddress(String userId,String addressId) throws Exception;
	
	/**
	 * 获取密码（验证）
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String verificationPassword(Object[] params) throws Exception;
	
	/**
	 * 修改密码
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updatePassword(Object[] params) throws Exception;
	
	/**
	 * 查询职位集合
	 * @return
	 * @throws Exception
	 */
	public List<Position> selectPositions() throws Exception;
	
	/**
	 * 跟新用户主职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateUserPrimaryPosition(Object[] params) throws Exception;
	
	/**
	 * 个人职位集合
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> personalPositionList(Object[] params) throws Exception;
	
	/**
	 * 更新用户职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateUserPosition(Object[] params) throws Exception;
	
	/**
	 * 添加职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int newUserPosition(Object[] params) throws Exception;
	
	/**
	 * 删除用户职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int deleteUserPosition(Object[] params) throws Exception;
	
	/**
	 * 查询当前用户职位是否存在
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int selectUserThisPosition(Object[] params) throws Exception;
	
	/**
	 * 创建新职位
	 * @param position
	 * @return
	 * @throws Exception
	 */
	public int createPosition(Object[] params) throws Exception;
	
	/**
	 * 查询该职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> thisPosition(Object[] params) throws Exception;
	
	/**
	 * 查询该职位,返回Position对象
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Position getPosition(Object[] params) throws Exception;
	
	/**
	 * 修改该职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updatePosition(Object[] params) throws Exception;
	
	/**
	 * 删除该职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int deletePosition(Object[] params) throws Exception;
	
	/**
	 * 条件查询用户集合
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserLists(User user) throws Exception;

}
