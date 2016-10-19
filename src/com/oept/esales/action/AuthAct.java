package com.oept.esales.action;

import java.util.ArrayList;
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

import com.oept.esales.model.ApprovalDetail;
import com.oept.esales.model.Auth;
import com.oept.esales.service.AuthService;
/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/30
 * Description: User rights management
 * Copyright (c) 2015 OEPT inc. All rights reserved.
 */
@Controller
@RequestMapping(value="/auth")
public class AuthAct {
	private static final Log logger = LogFactory.getLog(AuthAct.class);
	
	@Qualifier("authService")
	@Autowired
	private AuthService authService;
	
	/**
	 * 加载权限列表
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loadAvailAuth.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Auth> loadAvailAuth(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String id = request.getParameter("id");
		try{
			Object[] params = new Object[]{id};
			return authService.queryAuthItem(params);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 保存新分配的权限
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveAuth.do")
	@ResponseBody
	public int saveAuth(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String ids = request.getParameter("ids");
		String positionId = request.getParameter("id");
		String userId = String.valueOf(session.getAttribute("userid"));
		Object[] params3 = new Object[]{positionId};
		int res = 0;
		int defeated = 0;
		try {
			if(!ids.equals("")){
				String id[] = ids.split(",");
				//遍历该职位新选择的权限数组，比对数据库，没有数据的添加
				for(int i=0;i<id.length;i++){
					Object[] params1 = new Object[]{id[i],positionId};
					Object[] params2 = new Object[]{id[i],positionId,userId};
					//查询该权限是否已拥有
					int resValue = authService.queryThisAuthYoN(params1);
					//该权限未拥有，添加该权限
					if(resValue == 0){
						res = authService.addAuthItem(params2);
						if(res == 0){
							defeated = 1;
						}
					}
				}
				//读取该职位已拥有的权限
				List<Auth> as = authService.queryAuthItem(params3);
				int index = 0;
				//遍历数据库中的权限数组，比对该职位新选择的权限数组，多余的删除
				for(int i=0;i<as.size();i++){
					index = 0;
					for(int j=0;j<id.length;j++){
						if(as.get(i).getPerm_id().equals(String.valueOf(id[j]))){
							index = 1;
						}
					}
					//比对不匹配，删除该权限
					if(index == 0){
						Object[] params4 = new Object[]{as.get(i).getPost_id()};
						res = authService.delAuthItem(params4);
						if(res == 0){
							defeated = 1;
						}
					}
				}
				if(defeated == 1){
					res = 0;
				}
				return res;
			}else{
				//查询该职位已拥有权限，并删除
				List<Auth> as = authService.queryAuthItem(params3);
				//遍历数据库中的权限数组，并删除
				for(int i=0;i<as.size();i++){
						Object[] params4 = new Object[]{as.get(i).getPost_id()};
						res = authService.delAuthItem(params4);
						if(res == 0){
							defeated = 1;
						}
				}
				if(defeated == 1){
					res = 0;
				}
				return res;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 查询权限所有子权限
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkboxClick.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Auth> queryAuthSon(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String id = request.getParameter("id");
		try{
			Object[] params = new Object[]{id};
				List<Auth> auths = new ArrayList<Auth>();
				List<Auth> auths1 = authService.queryAuthPar(params);
				for(int i=0;i<auths1.size();i++){
					if(auths1.get(i).getPerm_lvl().equals("2") || auths1.get(i).getPerm_lvl().equals("3")){
						Object[] params2 = new Object[]{auths1.get(i).getId()};
						List<Auth> auths2 = authService.queryAuthPar(params2);
						for(int j=0;j<auths2.size();j++){
							auths.add(auths2.get(j));
						}
						auths.add(auths1.get(i));
					}
					if(auths1.get(i).getPerm_lvl().equals("4")){
						auths.add(auths1.get(i));
					}
				}
				return auths;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
}
