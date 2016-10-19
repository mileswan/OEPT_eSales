package com.oept.esales.action;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oept.esales.model.Address;
import com.oept.esales.model.User;
import com.oept.esales.service.UserService;

/**
 * @author zhujj 
 * Version: 1.0 
 * Date: 2015/11/23 
 * Description: Personal information management. 
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Controller
@RequestMapping(value = "/personal")
public class PersonalAct {

	@Qualifier("userService")
	@Autowired
	private UserService userService;

	private static final Log logger = LogFactory.getLog(PersonalAct.class);

	/**
	 * 查询当前用户下所有的收货地址
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/address.do")
	public String addressList(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		try {
			String userId = String.valueOf(session.getAttribute("userid"));
			List<Address> aList = userService.personalAddressList(userId);
			model.addAttribute("aList", aList);
			//获取当前用户的默认地址
			User result = userService.userDefaultAddress(userId);
			model.addAttribute("aMap",result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		return "PIAddress";
	}

	/**
	 * 新建收货地址
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createAddress.do")
	@ResponseBody
	public int createAddress(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		// 接收参数
		String contactName = request.getParameter("contactName");
		String province = request.getParameter("location_p");
		String city = request.getParameter("location_c");
		String county = request.getParameter("location_a");
		String detailsAddress = request.getParameter("detailsAddress");
		String zipcode = request.getParameter("zipcode");
		String contactTel = request.getParameter("contactTel");
		String country = request.getParameter("country");
		String addressAllName = country+province + city + county + detailsAddress;
		String userId = String.valueOf(session.getAttribute("userid"));
		// 存入持久化类
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
		address.setCountry(country);
		
		int res = 0;
		try {
			// 调用userService保存收货地址方法
			int result = userService.newAddress(address);
			if(result == 1){
				//创建Object数组传递参数
				Object[] params = new Object[] {
						addressAllName,
						userId,
						userId
				};
				// 调用userService分配地址方法
				res = userService.createUserAddress(params);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
		return res;
	}
	
	/**
	 * 删除收货地址信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAddress.do")
	@ResponseBody
	public int deleteUserIdAddress(Model model, HttpServletRequest request,
			HttpSession session)throws Exception{
		//接收参数
		String addressId = request.getParameter("addressId");
		String userId = String.valueOf(session.getAttribute("userid"));
		try {
			//存入持久化类
			Address address = new Address();
			address.setAddressId(addressId);
			User user = new User();
			user.setUserId(userId);
			//调用userService删除用户收货地址
			return userService.personalDeleteAddress(address, user);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	/**
	 * 查询需要更新的地址数据
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSelectAddress.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateSelectAddress(Model model, HttpServletRequest request,
			HttpSession session)throws Exception{
		//接收参数
		String addressId = request.getParameter("addressId");
		try {
			//Map接收userService查询地址信息方法返回结果
			Map<String,Object> map = userService.selectAddressDetail2(addressId);
			//创建ObjectMapper对象
			ObjectMapper mapper = new ObjectMapper();  
			//Map转JSON 返回ajax
			return  mapper.writeValueAsString(map); 
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}

	/**
	 * 更新地址数据
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAddress.do")
	@ResponseBody
	public int updateAddress(Model model, HttpServletRequest request,
			HttpSession session)throws Exception{
		try {
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
			String addressAllName = country+location_p+location_c+location_a+detailsAddress;
			//创建Object数组传递参数
			Object[] params = new Object[] {
					addressAllName,
					location_p,
					location_c,
					location_a,
					zipcode,
					contactName,
					contactTel,
					detailsAddress,
					country,
					addressId
			};
			//调用userService更新地址方法返回结果
			return userService.updateAddress(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 更改默认地址
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/defaultAddress.do")
	@ResponseBody
	public int defaultAddress(Model model, HttpServletRequest request,
			HttpSession session)throws Exception{
		//接收参数
		String addressId = request.getParameter("addressId");
		String userId = String.valueOf(session.getAttribute("userid"));
		try {
			//调用userService更改默认地址方法并返回结果
			return userService.defaultAddress(userId, addressId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 读取用户信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/settings.do")
	public String settingsGet(Model model, HttpServletRequest request,
			HttpSession session)throws Exception{
		//接收参数
		String userId = String.valueOf(session.getAttribute("userid"));
		try {
			User user = userService.selectUserDetail(userId);
			model.addAttribute("user",user);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return "PIDetails";
	}
	
	/**
	 * 修改用户信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePersonalSetting.do")
	@ResponseBody
	public int updatePersonalSetting(Model model, HttpServletRequest request,
			HttpSession session)throws Exception{
		//接收参数
		String username = request.getParameter("username");
		String emailaddress = request.getParameter("emailaddress");
		String address = request.getParameter("address");
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String activeX = request.getParameter("active");
		String userId = request.getParameter("userId");
		Boolean active = false;
		if(activeX == "1"){
			active = true;
		}else if(activeX == "0"){
			active = false;
		}
		try {
			//创建Object数组传递参数
			Object[] params = new Object[] {
					emailaddress,address,lastname,firstname,username,active,session.getAttribute("userid"),userId
			};
			//调用userService更新用户信息方法（无密码参数）并存入res对象中
			return userService.updateUserDetailNoPwd(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping(value = "/passwordPage.do")
	public String passwordPage(Model model, HttpServletRequest request,
			HttpSession session)throws Exception{
		String userId = String.valueOf(session.getAttribute("userid"));
		System.out.println(userId);
		try {
			User user = userService.selectUserDetail(userId);
			model.addAttribute("username",user);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return "PIPassword";
	}
	
	/**
	 * 修改密码
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePassword.do")
	@ResponseBody
	public int updatePassword(Model model, HttpServletRequest request,
			HttpSession session)throws Exception{
		//接收参数
		String agoPassword = request.getParameter("agoPassword");
		String newPassword = request.getParameter("newPassword");
		String userId = String.valueOf(session.getAttribute("userid"));
		//存入Object[]对象中，用以验证参数
		Object[] params = new Object[]{
				userId
		};
		//存入Object[]对象中，修改参数
		Object[] params2 = new Object[]{
				newPassword,
				userId,
				userId
		};
		try {
			//调用userService验证方法并接收返回参数
			String password =  userService.verificationPassword(params);
			//对比两值结果
			if(agoPassword.equals(password)){
				//如果原密码正确，进行修改
				return userService.updatePassword(params2);
			}else{
				//不正确返回消息
				return 2;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
}
