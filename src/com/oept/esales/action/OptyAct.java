package com.oept.esales.action;

import java.util.List;

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

import com.oept.esales.model.Account;
import com.oept.esales.model.Address;
import com.oept.esales.service.AccountService;
import com.oept.esales.service.ContactService;
import com.oept.esales.service.UserService;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/5/21
 * Description: Opportunity Management Action
 * Copyright (c) 2016 OEPT inc. All rights reserved.
 */
@Controller
@RequestMapping(value="/opty")
public class OptyAct {
	private static final Log logger = LogFactory.getLog(AccountAct.class);
	
	@Qualifier("userService")
	@Autowired
	private UserService userService;
	@Qualifier("accountService")
	@Autowired
	private AccountService accountService;
	@Qualifier("contactService")
	@Autowired
	private ContactService contactService;
	
	/**
	 * 查询联系人集合
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list.do")
	public String contactList(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		try {
			//调用service 查询联系人集合
			model.addAttribute("contactList", contactService.queryContactList());
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return "opty/opty_list";
	}
	
	/**
	 * 查看联系人详情
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/opty_details.do")
	public String contactDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{
//		String contactId = request.getParameter("id");
//		try {
//			Object[] params = new Object[]{contactId};
//			model.addAttribute("contactDetails", contactService.queryContactDetails(params));
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.info(e.getMessage());
//			throw e;
//		}
		return "opty/opty_view";
	}
	
	/**
	 * 跳转添加页面
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contact_add.do")
	public String contactAdd(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		return "contact/contact_add";
	}
	
	/**
	 * 加载地址
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loadAvailAddr.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Address> loadAvailAddresses(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		try{
			return userService.selectAddressLists();
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 加载单位
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loadAvailAccount.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Account> loadAvailAccount(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		try{
			Account account = new Account();
			return accountService.selectAllAtDe(account);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 添加联系人信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addContact.do")
	@ResponseBody
	public int addContact(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String firstname = request.getParameter("addFirstname");
		String lastname = request.getParameter("addLastname");
		String birthday = request.getParameter("addBirthday");
		String age = request.getParameter("addAge");
		String fax = request.getParameter("addFax");
		String email = request.getParameter("addEmail");
		String cellphone = request.getParameter("addCellphone");
		String workphone = request.getParameter("addWorkphone");
		String title = request.getParameter("addTitle");
		String department = request.getParameter("addDepartment");
		String genderType = request.getParameter("addGender");
		String addressId = request.getParameter("addAddressId");
		String accountId = request.getParameter("addAccountId");
		String comment = request.getParameter("addComment");
		String userId = String.valueOf(session.getAttribute("userid"));
		String contactName = firstname + lastname;
		String gender = "";
		if(genderType.equals("true")){
			gender = "1";
		}else if(genderType.equals("false")){
			gender = "0";
		}
		try{
			Object[] params = new Object[]{
				contactName,firstname,lastname,birthday,age,fax,email,cellphone,workphone,
				title,department,gender,addressId,accountId,comment,userId,userId
			};
			return contactService.addContact(params);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 更新联系人信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateContact.do")
	@ResponseBody
	public int updateContact(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String id = request.getParameter("updateId");
		String firstname = request.getParameter("updateFirstname");
		String lastname = request.getParameter("updateLastname");
		String birthday = request.getParameter("updateBirthday");
		String age = request.getParameter("updateAge");
		String fax = request.getParameter("updateFax");
		String email = request.getParameter("updateEmail");
		String cellphone = request.getParameter("updateCellphone");
		String workphone = request.getParameter("updateWorkphone");
		String title = request.getParameter("updateTitle");
		String department = request.getParameter("updateDepartment");
		String genderType = request.getParameter("updateGender");
		String addressId = request.getParameter("updateAddressId");
		String accountId = request.getParameter("updateAccountId");
		String comment = request.getParameter("updateComment");
		String userId = String.valueOf(session.getAttribute("userid"));
		String contactName = firstname + lastname;
		String gender = "";
		if(genderType.equals("true")){
			gender = "1";
		}else if(genderType.equals("false")){
			gender = "0";
		}
		try{
			Object[] params = new Object[]{
				contactName,firstname,lastname,birthday,age,fax,email,cellphone,workphone,
				title,department,gender,addressId,accountId,comment,userId,id
			};
			return contactService.updateContact(params);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 删除联系人信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delContact.do")
	@ResponseBody
	public int delContact(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String ids = request.getParameter("ids");
		try{
			String id[] = ids.split(",");
			int res = 0;
			for(int i=0;i<id.length;i++){
				Object[] params = new Object[]{id[i]};
				res = contactService.delContact(params);
			}
			
			return res;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
}
