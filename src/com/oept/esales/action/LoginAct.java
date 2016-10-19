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
import org.springframework.web.bind.annotation.ResponseBody;

import com.oept.esales.model.Auth;
import com.oept.esales.model.User;
import com.oept.esales.service.AuthService;
import com.oept.esales.service.LoginService;
/**
 * @author zhujj,mwan
 * Version: 1.0
 * Date: 2015/11/6
 * Description: User login, validation and logout actions.
 * Copyright (c) 2015 OEPT inc. All rights reserved.
 */
@Controller
@RequestMapping(value="/auth")
public class LoginAct {
	private static final Log logger = LogFactory.getLog(LoginAct.class);
	
	@Qualifier("loginService")
	@Autowired
	private LoginService loginService;
	@Qualifier("authService")
	@Autowired
	private AuthService authService;

	/**
	 * 用户登录
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login.do")
	@ResponseBody
	public int userLogin(Model model, HttpServletRequest request, HttpSession session){
		//获取用户名密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try{
			//存入持久化类
			User user = new User();
			user.setUserName(username);
			user.setPassword(password);
			User user2 = loginService.login(user);
			Boolean nullity = user2.isDelete();
			
			if(nullity == false){
				//记录用户登录时间
				loginService.lastLogin(user2);
				model.addAttribute("loginMap", user2);
				session.setAttribute("username", user2.getUserName());
				session.setAttribute("password", user2.getPassword());
				session.setAttribute("userid", user2.getUserId());
				
				List<Auth> authList = authService.queryAuthItem(new Object[]{user2.getPrimaryPositionId()});
				
				session.setAttribute("authList", authList);
				return 1;
			}else{
				return 0;
			}
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return 2;
	}
	
	//User logout
	@RequestMapping(value="/logout.do")
	public String userLogout(Model model, HttpServletRequest request, HttpSession session){
		try {				
			session.removeAttribute("username");
			session.removeAttribute("password");
			session.removeAttribute("userid");
			session.removeAttribute("positionId");

			return "redirect:/";
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}
	}
}
