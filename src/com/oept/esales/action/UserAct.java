package com.oept.esales.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oept.esales.model.Address;
import com.oept.esales.model.Position;
import com.oept.esales.model.User;
import com.oept.esales.service.AuthService;
import com.oept.esales.service.LoginService;
import com.oept.esales.service.UserService;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/06
 * Description: User management operation.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Controller
@RequestMapping(value="/user")
public class UserAct {

	@Qualifier("userService")
	@Autowired
	private UserService userService;
	
	@Qualifier("loginService")
	@Autowired
	private LoginService loginService;
	
	@Qualifier("authService")
	@Autowired
	private AuthService authService;
	
	private static final Log logger = LogFactory.getLog(UserAct.class);
	
	
	/**
	 * 新用户快速注册方法
	 * @param model
	 * @param request
	 * @param session
	 * @return 
	 */
	@RequestMapping(value="/signin.do")
	@ResponseBody
	public int createUser(Model model, HttpServletRequest request, HttpSession session)throws Exception{
		//依次获取request数据
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		//存入持久化类
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setLastName(lastname);
		user.setFirstName(firstname);
		user.setEmail(email);
		user.setAddress(address);
			
		//调用业务层注册方法，接收返回类型为整型
		int results =  userService.signin(user);
		//如果注册成功自动登录
		if(results == 1){
			try{
				//调用loginservice登录方法，接收返回数据
				User user2 = loginService.login(user);
				if(user2 != null){
					//记录用户登录时间
					loginService.lastLogin(user);
				}
				//接收到的返回数据存入model，方便前端调用数据
				model.addAttribute("loginMap", user2);
				//用户名密码存入session
				session.setAttribute("username", user.getUserName());
				session.setAttribute("password", user.getPassword());
				session.setAttribute("userid", user2.getUserId());
				return 1;
			}catch(Exception e){
				logger.info(e.getMessage());
				throw e;
			}
		}else{
			//返回页面
			return 0;
		}
	}
	
	/**
	 * 验证用户是否存在
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testingUesr.do",method = RequestMethod.POST)
	@ResponseBody
	public String testingUser(Model model, HttpServletRequest request, HttpSession session)throws Exception{
		//获取request数据
		String username = request.getParameter("username");
		//用户名为空，不执行查询
		if(username == ""){
			String res;
			res = "3";
			return res;
		}else{
		try {
			//调用userService注册验证方法并接收返回结果
			Integer result = userService.testingUser(username);
			String res;
			//根据返回结果判断返回相应结果
			if(result == null){
				res = "2";
				return res;
			}else{
				res = "1";
				return res;
			}
		} catch (Exception e) {
			String res;
			res = "2";
			return res;
		}
		}
		
	}
	
	/**
	 * 查询所有用户
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list.do")
	public String navToUserList(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		try{
			//调用userService查询用户列表方法并存入model对象中
			model.addAttribute("userList",userService.selectUserList());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		//返回页面
		return "userList";
	}
	
	/**
	 * 用户的详细信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/details.do")
	public String navToUserDetail(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		try{
			//接收userId参数
			String userId = request.getParameter("userId");
			//调用userService查询用户详细信息方法并存入model对象中
			model.addAttribute("userDetails", userService.selectUserDetail(userId));
			//调用userService查询职位集合方法并存入model对象中
			model.addAttribute("positions", userService.selectPositions());
			Object[] params = new Object[]{
					userId
			};
			List<Map<String, Object>> personalPositions = userService.personalPositionList(params);
			//调用个人职位集合
			model.addAttribute("pPositions", personalPositions);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		//返回页面
		return "userDetails";
	}
	
	/**
	 * 更新用户信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateChannellist(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String res = "";
		try{
			//接收参数
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String emailaddress = request.getParameter("emailaddress");
			String address = request.getParameter("address");
			String lastname = request.getParameter("lastname");
			String firstname = request.getParameter("firstname");
			String activeX = request.getParameter("active");
			String userId = request.getParameter("userId");
			String position = request.getParameter("position");
			String positionId = request.getParameter("positionId");
			String loginUserId = String.valueOf(session.getAttribute("userid"));
			Boolean active = false;
			if(activeX.equals("1")){
				active = true;
			}else if(activeX.equals("0")){
				active = false;
			}

			//如果密码为空则执行没有密码参数的更新方法，不然则执行带密码参数的更新方法
			if(password.equals("")||password==null){
				//创建Object数组传递参数
				Object[] params = new Object[] {
						emailaddress,address,lastname,firstname,username,active,loginUserId,userId
				};
				//调用userService更新用户信息方法（无密码参数）并存入res对象中
				res = String.valueOf(userService.updateUserDetailNoPwd(params));
				//更新主职位
				if(res.equals("1")){
					Object[] params2 = new Object[]{
							position,
							userId
					};
					//查询用户当前需要修改的职位是否存在，不存在返回信息，存在修改
					int or = userService.selectUserThisPosition(params2);
					if(or != 0){
						//更新用户主职位
						int result = userService.updateUserPrimaryPosition(params2);
						res = String.valueOf(result);
					}else {
						res = "3";
					}
					
				}
			}else{
				Object[] params = new Object[] {
						password,emailaddress,address,lastname,firstname,username,active,loginUserId,userId
				};
				//调用userService更新用户信息方法（有密码参数）并存入res对象中
				res = String.valueOf(userService.updateUserDetailAndPwd(params));
				//更新主职位
				if(res.equals("1")){
					Object[] params2 = new Object[]{
							position,
							userId
					};
					//查询用户当前需要修改的职位是否存在，不存在返回信息，存在修改
					int or = userService.selectUserThisPosition(params2);
					if(or != 0){
						//更新用户主职位
						int result = userService.updateUserPrimaryPosition(params2);
						res = String.valueOf(result);
					}else {
						res = "3";
					}
				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		//返回ajax结果
		return res;
	}
	
	/**
	 * 根据id批量删除用户
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delect.do", method = RequestMethod.POST)
	@ResponseBody
	public String delectUser(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//获取checkbox的id字符串
		String boxs = request.getParameter("boxs");
		String[] users = null;
		//截取字符串成数组
		users = boxs.split("s");
		String result = "0";
		try {
			//遍历数组
			for(int i=0;i<users.length;i++){
				//依次根据id进行删除工作
				Integer res = userService.deleteUser(users[i]);
				//返回结果
				if(res == 1){
					result = "1";
				}else{
					result = "0";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
				logger.info(e.getMessage());
				throw e;
		}
		//返回结果
		return result;
	}
	
	/**
	 * 只进行跳转，新用户注册
	 */
	@RequestMapping(value="/newUserSkip.do")
	public String newUserSkip(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//只进行跳转
		return "userNews";
	}
	
	/**
	 * 创建新用户
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/newUser.do")
	@ResponseBody
	public String newUser(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String res = "";
		try{
			//接收参数
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String emailaddress = request.getParameter("emailaddress");
			String address = request.getParameter("address");
			String lastname = request.getParameter("lastname");
			String firstname = request.getParameter("firstname");

			//创建Object数组传递参数
			Object[] params = new Object[] {
					firstname,lastname,username,password,emailaddress,address
			};
			//调用userService新建用户方法并存入res对象中
			res = String.valueOf(userService.newUser(params));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		//返回ajax结果
		return res;
	}
	
	/**
	 * 查询所有地址
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/address.do")
	public String navToAddressList(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		try{
			//调用userService查询所有地址
			List<Address> addressLists = userService.selectAddressLists();
			//存入model对象
			model.addAttribute("addressLists",addressLists);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		//返回页面
		return "area_list";
	}
	
	
	/**
	 * 保存收货地址
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/newAddress.do")
	@ResponseBody
	public String newAddress(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//接收参数
		String contactName = request.getParameter("contactName");
		String province = request.getParameter("location_p");
		String city = request.getParameter("location_c");
		String county = request.getParameter("location_a");
		String detailsAddress = request.getParameter("detailsAddress");
		String zipcode = request.getParameter("zipcode");
		String contactTel = request.getParameter("contactTel");
		String country = request.getParameter("country");
//		String addressAllName = country+province+city+county+detailsAddress;
		String addressAllName = country+province+city+county;
		String userId = String.valueOf(session.getAttribute("userid"));
		//存入持久化类
		Address address = new Address();
		address.setAllAddress(addressAllName);
		address.setProvince(province);
		address.setCity(city);
		address.setCounty(county);
		address.setStreet(detailsAddress);
		address.setZipcode(zipcode);
		address.setContactName(contactName);
		address.setContactCell(contactTel);
		address.setCreatedBy(userId);
		address.setUpdateBy(userId);
		address.setCountry(country);
		
		String res = "";
		try{
			//调用userService保存收货地址方法并存入res对象中
			res = String.valueOf(userService.newAddress(address));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		//返回ajax结果
		return res;
	}
	
	/**
	 * 批量删除收货地址
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delectAddress.do", method = RequestMethod.POST)
	@ResponseBody
	public String delectAddress(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//获取checkbox的id字符串
		String boxs = request.getParameter("boxs");
		String[] address = null;
		//截取字符串成数组
		address = boxs.split("s");
		String result = "0";
		try {
			//遍历数组
			for(int i=0;i<address.length;i++){
				//依次根据id进行删除工作
				result = String.valueOf(userService.deleteAddress(address[i]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
				logger.info(e.getMessage());
				throw e;
		}
		//返回结果
		return result;
	}
	
	/**
	 * 根据id查询地址信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectAddressId.do")
	public String selectAddressDetail(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		int readonly = Integer.valueOf(request.getParameter("readonly"));
		try{
			//接收addressId参数
			String addressId = request.getParameter("addressId");
			//调用userService查询地址详细信息方法并存入model对象中
			model.addAttribute("addressDetail", userService.selectAddressDetail(addressId));
			//调用userService根据地址查询所属用户
			model.addAttribute("aUsers",userService.addrIdSelectUsername(addressId));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		if(readonly == 1){
			return "allotAddress";
		}else{
			//返回页面
			return "area_details";
		}
	}
	
	/**
	 * 更新收货地址
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAddress.do")
	@ResponseBody
	public String updateAddress(Model model, HttpServletRequest request, HttpSession session) throws Exception{
	
		String res = "";
		try{
			//接收参数
			String contactName = request.getParameter("contactName");
			String location_p = request.getParameter("location_p");
			String location_c = request.getParameter("location_c");
			String location_a = request.getParameter("location_a");
			String detailsAddress = request.getParameter("detailsAddress");
			String zipcode = request.getParameter("zipcode");
			String contactTel = request.getParameter("contactTel");
			String addressId = request.getParameter("addressId");
			String country = request.getParameter("country");
			String userid = session.getAttribute("userid").toString();
			String addressAllName = country+location_p+location_c+location_a+detailsAddress;
			//创建Object数组传递参数
			Object[] params = new Object[] {
					addressAllName,location_p,location_c,location_a,zipcode,contactName,contactTel,detailsAddress,country,userid,addressId
			};
			res = String.valueOf(userService.updateAddress(params));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		//返回ajax结果
		return res;
	}
	
	/**
	 * 获取用户名集合
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxUserNameList.do")
	@ResponseBody
	public String[] ajaxUserNameList(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		String res = "1";
		String[] usernames;
		try {
			//调用userService获取用户名集合
			usernames = userService.selectAllUserName();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		//返回数组
		return usernames;
	}
	
	/**
	 * 分配地址
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/allotAddress.do")
	@ResponseBody
	public int allotAddress(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		int res ;
		try {
			//接收参数
			String addressId = request.getParameter("addressId");
			String username = request.getParameter("username");
			String userId = String.valueOf(session.getAttribute("userid"));
			//创建Object数组传递参数
			Object[] params = new Object[] {
					addressId,username,userId
			};
			//调用userService分配用户收货地址
			res = userService.allotAddress(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		//返回结果
		return res;
	}

	/**
	 * 删除地址
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteUserAddr.do")
	@ResponseBody
	public int deleteUserAddress(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		int res;
		//接收参数
		String addressId = request.getParameter("addressId");
		String userId = request.getParameter("userId");
		try {
			//存入持久化类
			Address address = new Address();
			address.setAddressId(addressId);
			User user = new User();
			user.setUserId(userId);
			//调用userService删除用户收货地址
			res = userService.deleteUserAddress(address, user);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		//返回结果
		return res;
	}
	
	/**
	 * 添加用户职位
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/newUserPostion.do")
	@ResponseBody
	public int newUserPostion(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		int res;
		//接收参数
		String userId = request.getParameter("userId");
		String position = request.getParameter("position");
		String sysUserId = String.valueOf(session.getAttribute("userid"));
		try {
			Object[] params = new Object[]{
					userId,
					position,
					sysUserId
			};
			//调用userService删除用户收货地址
			res = userService.newUserPosition(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		//返回结果
		return res;
	}
	
	/**
	 * 删除用户职位
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteUserPostion.do")
	@ResponseBody
	public int deleteUserPostion(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		int result = 0;
		//接收参数
		String oppid = request.getParameter("oppId");
		String userId = request.getParameter("userId");
		int param = Integer.valueOf(request.getParameter("param"));
		try {
			if(param == 2){
				//如果删除不为主职位
				Object[] params = new Object[]{
						oppid
				};
				//调用userService删除用户收货地址
				result =  userService.deleteUserPosition(params);
			}else if(param == 1){
				//如果删除为主职业
				Object[] params = new Object[]{
						oppid
				};
				//调用userService删除用户收货地址
				int res =  userService.deleteUserPosition(params);
				if(res == 1){
					String positionNull = null;
					Object[] params2 = new Object[]{
							positionNull,
							userId
					};
					//更新用户主职位
					result = userService.updateUserPrimaryPosition(params2);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return result;
	}
	
	/**
	 * 设置主职位
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updatePrimaryPosition.do")
	@ResponseBody
	public int updatePrimaryPosition(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		int result = 0;
		//接收参数
		String oppid = request.getParameter("oppId");
		String userId = request.getParameter("userId");
		try {
			Object[] params = new Object[]{
					oppid,
					userId
			};
			//调用设置主职位方法
			result = userService.updateUserPrimaryPosition(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return result;
	}
	
	/**
	 * 职位管理----查询职位列表
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/position.do")
	public String navToPositionList(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		try{
			//调用userService查询职位集合方法并存入model对象中
			model.addAttribute("positionList", userService.selectPositions());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		//返回页面
		return "position/positionList";
	}
	
	/**
	 * 职位管理----添加职位
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/createPosition.do")
	@ResponseBody
	public int createPosition(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//接收参数
		String positionName = request.getParameter("positionName");
		String parentPosition = request.getParameter("parentPosition");
		String sysUserId = String.valueOf(session.getAttribute("userid"));
		try{
			Object[] params = new Object[]{
					positionName,
					parentPosition,
					sysUserId
			};
			//调用userService创建新职位方法
			return userService.createPosition(params);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 职位管理----查询该职位
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/thisPosition.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String thisPosition(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//接收参数
		String positionId = request.getParameter("positionId");
		try{
			Object[] params = new Object[]{
					positionId
			};
			//调用查询该职位信息方法
			Map<String,Object> thisPosition = userService.thisPosition(params);
			//创建ObjectMapper对象
			ObjectMapper mapper = new ObjectMapper();  
			//Map转JSON 返回ajax
			return  mapper.writeValueAsString(thisPosition); 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 职位管理----查看职位详情
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPosition.do")
	public String getPosition(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//接收参数
		String positionId = request.getParameter("id");
		try{
			Object[] params = new Object[]{
					positionId
			};
			//调用查询该职位信息方法
			model.addAttribute("position", userService.getPosition(params));
			//调用userService查询职位集合方法并存入model对象中
			model.addAttribute("positionList", userService.selectPositions());
			//查询所有权限类型
			Object[] params1 = new Object[]{1};
			Object[] params2 = new Object[]{2};
			Object[] params3 = new Object[]{3};
			Object[] params4 = new Object[]{4};
			model.addAttribute("auth1", authService.queryAuthLvl(params1));
			model.addAttribute("auth2", authService.queryAuthLvl(params2));
			model.addAttribute("auth3", authService.queryAuthLvl(params3));
			model.addAttribute("auth4", authService.queryAuthLvl(params4));
			
			return "position/position_details";
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 职位管理----修改该职位
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updatePosition.do")
	@ResponseBody
	public int updatePosition(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//接收参数
		String positionName = request.getParameter("positionName");
		String parentPosition = request.getParameter("parentPosition");
		String sysUserId = String.valueOf(session.getAttribute("userid"));
		String positionId = request.getParameter("positionId");
		try{
			//存入Object对象中
			Object[] params = new Object[]{
					positionName,
					parentPosition,
					sysUserId,
					positionId
			};
			//调用userService更新该职位方法并返回结果
			return  userService.updatePosition(params);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 职位管理----删除该职位
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deletePosition.do")
	@ResponseBody
	public int deletePosition(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//接收参数
		String positionId = request.getParameter("positionId");
		try{
			//存入Object对象中
			Object[] params = new Object[]{
					positionId
			};
			//调用userService删除该职位方法并返回结果
			return userService.deletePosition(params);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
}
