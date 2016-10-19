package com.oept.esales.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oept.esales.dao.UserDao;
import com.oept.esales.model.Address;
import com.oept.esales.model.Position;
import com.oept.esales.model.User;
import com.oept.esales.service.UserService;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/16
 * Description: User management operation service implements.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
@Service("userService")
public class UserServiceImpl implements UserService{


	@Autowired
	private UserDao userDao;
	/**
	 * 用户快速注册功能实现业务接口
	 */
	@Override
	public int signin(User user) throws Exception {
		// TODO Auto-generated method stub
		//调用userDao signin方法并返回结果
		return userDao.signin(user);
	}
	
	/**
	 * 注册验证用户名是否已存在
	 */
	@Override
	public Integer testingUser(String username) {
		// TODO Auto-generated method stub
		//调用userDao testingUser方法并返回结果
		return userDao.testingUser(username);
	}

	/**
	 * 查询用户列表
	 */
	@Override
	public List<User> selectUserList() throws Exception {
		// TODO Auto-generated method stub
		//调用userDao selectUserList方法并返回结果
		return userDao.selectUserList();
	}
	
	/**
	 * 查询单个用户信息
	 */
	@Override
	public User selectUserDetail(String userId) throws Exception{
		// TODO Auto-generated method stub
		//调用userDao 查询用户详细信息方法并返回结果
		return userDao.selectUserDetail(userId);
	}

	/**
	 * 更新用户信息，参数带密码
	 */
	@Override
	public int updateUserDetailAndPwd(Object[] params) {
		// TODO Auto-generated method stub
		//调用userDao 更新用户信息方法（无密码）并返回结果
		return userDao.updateUserDetailAndPwd(params);
	}

	/**
	 * 更新用户信息，参数不带密码
	 */
	@Override
	public int updateUserDetailNoPwd(Object[] params){
		// TODO Auto-generated method stub
		//调用userDao 更新用户信息方法（有密码）并返回结果
		return userDao.updateUserDetailNoPwd(params);
	}

	/**
	 * 根据id删除user
	 */
	@Override
	public Integer deleteUser(String userId) {
		// TODO Auto-generated method stub
		//调用userDao 删除用户方法并返回结果
		return userDao.deleteUser(userId);
	}

	/**
	 * 创建新用户
	 */
	@Override
	public int newUser(Object[] params) {
		// TODO Auto-generated method stub
		//调用userDao 创建新用户方法并返回结果
		return userDao.newUser(params);
	}

	/**
	 * 保存收货地址
	 */
	@Override
	public int newAddress(Address address) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			//查询新建区域是否重复
			boolean res = userDao.queryAddressExist(address);
			if(res){
				result = 2;
			}else{
				result = userDao.newAddress(address);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//调用userDao 保存收货地址方法并返回结果
		return result;
	}

	/**
	 * 删除地址
	 */
	@Override
	public int deleteAddress(String addressId) {
		// TODO Auto-generated method stub
		//调用userDao 删除方法并返回结果
		return userDao.deleteAddress(addressId);
		
	}

	/**
	 * 更新收货地址
	 */
	@Override
	public int updateAddress(Object[] params) {
		// TODO Auto-generated method stub
		return userDao.updateAddress(params);
	}

	/**
	 * 根据id查询地址信息
	 */
	@Override
	public Address selectAddressDetail(String addressId) {
		// TODO Auto-generated method stub
		return userDao.selectAddressDetail(addressId);
	}

	/**
	 * 根据id查询地址信息
	 */
	@Override
	public Map<String,Object> selectAddressDetail2(String addressId) {
		// TODO Auto-generated method stub
		return userDao.selectAddressDetail2(addressId);
	}
	
	/**
	 * 查询所有用户名
	 */
	@Override
	public String[] selectAllUserName() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> users = userDao.selectAllUserName();
		String[] userNames = new String[users.size()];
		for(int i=0;i<users.size();i++){
			userNames[i] = String.valueOf(users.get(i).get("osa_username"));
		}
		return userNames;
	}

	/**
	 * 分配地址
	 */
	@Override
	public int allotAddress(Object[] params) {
		// TODO Auto-generated method stub
		return userDao.allotAddress(params);
	}

	/**
	 * 查询地址列表
	 */
	@Override
	public List<Address> selectAddressLists() throws Exception {
		// TODO Auto-generated method stub
		List<Address> addressList = userDao.selectAddressLists();
		for(int i=0;i<addressList.size();i++){
			String aId = addressList.get(i).getAddressId();
			addressList.get(i).setUser(userDao.addrIdSelectUsername(aId));
		}
		return addressList;
	}

	/**
	 * 根据地址id查询所属用户
	 */
	@Override
	public List<User> addrIdSelectUsername(String aId) throws Exception {
		// TODO Auto-generated method stub
		return userDao.addrIdSelectUsername(aId);
	}

	/**
	 * 删除用户收货地址
	 */
	@Override
	public int deleteUserAddress(Address address, User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.deleteUserAddress(address, user);
	}
	
	/**
	 * 根据id查询当前用户下所有的收货地址
	 */
	@Override
	public List<Address> personalAddressList(String userId) throws Exception{
		// TODO Auto-generated method stub
		return userDao.personalAddressList(userId);
	}

	/**
	 * 创建用户地址中间表数据
	 */
	@Override
	public int createUserAddress(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.createUesrAddress(params);
	}

	/**
	 * 个人用户删除个人收货地址（若当前要删除地址没有其他人使用就删除）
	 */
	@Override
	public int personalDeleteAddress(Address address, User user)
			throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		//根据地址用户id删除中间表数据
		int res = userDao.deleteUserAddress(address, user);
		//如果返回成功
		if(res == 1){
			//根据地址id查询中间表所有该id信息
			int count = userDao.selectCountAddressUserId(address);
			//如果返回值为0,（该id没有其他用户使用）
			if(count == 0){
				//根据地址id删除地址表数据
				result = userDao.deleteAddressPersonal(address);
			}
		}
		//返回结果
		return result;
	}

	/**
	 * 根据id获取用户默认地址
	 */
	@Override
	public User userDefaultAddress(String userId)
			throws Exception {
		// TODO Auto-generated method stub
		return userDao.userDefaultAddress(userId);
	}

	/**
	 * 更改默认地址
	 */
	@Override
	public int defaultAddress(String userId, String addressId) throws Exception {
		// TODO Auto-generated method stub
		return userDao.defaultAddress(userId, addressId);
	}

	/**
	 * 获取密码（验证）
	 */
	@Override
	public String verificationPassword(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		User user = userDao.verificationPassword(params);
		String password = user.getPassword();
		return password;
	}

	/**
	 * 修改密码
	 */
	@Override
	public int updatePassword(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.updatePersonalPassword(params);
	}

	/**
	 * 查询职位集合
	 */
	@Override
	public List<Position> selectPositions() throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectPositions();
	}

	/**
	 * 跟新用户主职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateUserPrimaryPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.updateUserPrimaryPosition(params);
	}

	/**
	 * 个人职位集合
	 */
	@Override
	public List<Map<String, Object>> personalPositionList(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		return userDao.personalPositionList(params);
	}

	/**
	 * 更新用户职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateUserPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.updateUserPosition(params);
	}

	/**
	 * 添加职位
	 */
	@Override
	public int newUserPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.newUserPosition(params);
	}

	/**
	 * 删除用户职位
	 */
	@Override
	public int deleteUserPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.deleteUserPosition(params);
	}

	/**
	 *  查询当前用户职位是否存在
	 */
	@Override
	public int selectUserThisPosition(Object[] params)
			throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectUserThisPosition(params);
	}

	/**
	 * 创建新职位
	 */
	@Override
	public int createPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.createPosition(params);
	}

	/**
	 * 查询该职位
	 */
	@Override
	public Map<String,Object> thisPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.thisPosition(params);
	}

	/**
	 * 查询该职位,返回Position对象
	 */
	@Override
	public Position getPosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getPosition(params);
	}
	
	/**
	 * 修改该职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updatePosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.updatePosition(params);
	}

	/**
	 * 删除该职位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deletePosition(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return userDao.deletePosition(params);
	}

	/**
	 * 条件查询用户集合
	 */
	@Override
	public List<User> getUserLists(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getUserLists(user);
	}

	
}
