package com.oept.esales.dao;

import java.util.List;
import java.util.Map;

import com.oept.esales.model.Address;
import com.oept.esales.model.Position;
import com.oept.esales.model.User;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/06
 * Description: Categories DAO interface.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
public interface UserDao {

	/**
	 * 用户快速注册方法接口
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
	 * 查询单个用户的详细信息
	 * @param userId
	 * @return
	 */
	public User selectUserDetail(String userId) throws Exception;
	
	/**
	 * 创建新用户
	 * @param params
	 * @return
	 */
	public int newUser(Object[] params);
	
	/**
	 * 更新用户信息，参数有密码
	 * @param params
	 * @return
	 */
	public int updateUserDetailAndPwd(Object[] params);
	
	/**
	 * 更新用户信息，参数没有密码
	 * @param params
	 * @return
	 */
	public int updateUserDetailNoPwd(Object[] params);
	
	/**
	 * 根据id删除用户
	 * @param userId
	 * @return
	 */
	public Integer deleteUser(String userId);
	
	/**
	 * 保存收货地址
	 * @param params
	 * @return
	 */
	public int newAddress(Address address);
	
	
	/**
	 * 根据id删除地址
	 * @param userId
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
	 * @param addressId
	 * @return
	 */
	public Address selectAddressDetail(String addressId);
	
	/**
	 * 根据id查询地址信息
	 * @param addressId
	 * @return
	 */
	public Map<String,Object> selectAddressDetail2(String addressId);
	
	/**
	 * 查询所有的用户名
	 * @return
	 */
	public List<Map<String, Object>> selectAllUserName();
	
	/**
	 * 更新用户地址中间表，分配地址
	 * @return
	 */
	public int allotAddress(Object[] params);
	
	/**
	 * 根据地址id查询用户名
	 * @param params
	 * @return
	 */
	public List<User> addrIdSelectUsername(String params) throws Exception;
	
	
	/**
	 * 查询地址列表(Address)
	 * @return
	 */
	public List<Address> selectAddressLists() throws Exception;
	
	/**
	 * 删除用户收货地址
	 * @param params
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
	 * @return
	 */
	public int createUesrAddress(Object[] params) throws Exception;
	
	/**
	 * 个人用户删除地址表信息
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public int deleteAddressPersonal(Address address) throws Exception;
	
	/**
	 * 查询当前要删除的地址的条数
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public int selectCountAddressUserId(Address address) throws Exception;
	
	/**
	 * 根据id获取用户默认地址
	 * @param params
	 * @return
	 */
	public User userDefaultAddress(String userId) throws Exception;
	
	/**
	 * 更改默认地址
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int defaultAddress(String userId, String addressId) throws Exception;
	
	/**
	 * 获取密码（验证）
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public User verificationPassword(Object[] params) throws Exception;
	
	/**
	 * 更新密码
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updatePersonalPassword(Object[] params) throws Exception;
	
	/**
	 * 查询职位集合
	 * @return
	 * @throws Exception
	 */
	public List<Position> selectPositions() throws Exception;
	
	/**
	 * 更新用户主职位
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
	 * 添加用户职位
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
	public Map<String,Object> thisPosition(Object[] params) throws Exception;
	
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
	
	/**
	 * 查询区域是否已存在
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public boolean queryAddressExist(Address address) throws Exception;
}
