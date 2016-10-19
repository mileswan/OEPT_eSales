package com.oept.esales.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.oept.esales.model.Approval;
import com.oept.esales.model.ListOfValue;
import com.oept.esales.model.SystemPreference;
import com.oept.esales.model.User;
import com.oept.esales.service.AuthService;
import com.oept.esales.service.SystemService;
import com.oept.esales.service.UserService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/15
 * Description: System administration actions.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Controller
@RequestMapping(value="/system")
public class SystemAct {
	
	@Qualifier("userService")
	@Autowired
	private UserService userService;
	@Qualifier("systemService")
	@Autowired
	private SystemService systemService;
	@Qualifier("authService")
	@Autowired
	private AuthService authService;
	
	private static final Log logger = LogFactory.getLog(SystemAct.class);
	
	@RequestMapping(value="/settings.do",method = RequestMethod.GET)
	public String systemSettings(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<SystemPreference> allSystemPrefList = systemService.getAllPreferences();
			Map<String,String> allSystemPrefMap = new HashMap<String,String>();
			for(int i=0;i<allSystemPrefList.size();i++){
				SystemPreference systemPref = allSystemPrefList.get(i);
				allSystemPrefMap.put(systemPref.getSystem_preference_code(), systemPref.getSystem_preference_value());
			}
			model.addAttribute("allSystemPrefMap",allSystemPrefMap);
			
			Map<String,String> allDataSourceMap = systemService.getDataSourceInfo();
			model.addAttribute("allDataSourceMap",allDataSourceMap);
			return "system/system_settings";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updateEmailSettings.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateEmailSettings(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		try{			
			SystemPreference systemPref = new SystemPreference();
			systemPref.setUpdated_by_user_id(session.getAttribute("userid").toString());
			
			systemPref.setSystem_preference_code("mail_host");
			systemPref.setSystem_preference_value(request.getParameter("mail_host"));
			systemService.updatePrefByCode(systemPref);
			
			systemPref.setSystem_preference_code("mail_transport_protocol");
			systemPref.setSystem_preference_value(request.getParameter("mail_transport_protocol"));
			systemService.updatePrefByCode(systemPref);
			
			systemPref.setSystem_preference_code("mail_username");
			systemPref.setSystem_preference_value(request.getParameter("mail_username"));
			systemService.updatePrefByCode(systemPref);
			
			systemPref.setSystem_preference_code("mail_password");
			systemPref.setSystem_preference_value(request.getParameter("mail_password"));
			systemService.updatePrefByCode(systemPref);
			
			systemPref.setSystem_preference_code("mail_available");
			systemPref.setSystem_preference_value(request.getParameter("mail_available"));
			systemService.updatePrefByCode(systemPref);
			
			systemPref.setSystem_preference_code("mail_smtp_auth");
			systemPref.setSystem_preference_value(request.getParameter("mail_smtp_auth"));
			systemService.updatePrefByCode(systemPref);
			
			return "{'objname':'Email系统参数'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}

	@RequestMapping(value="/updateDefaultSettings.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateOtherSettings(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		try{			
			SystemPreference systemPref = new SystemPreference();
			systemPref.setUpdated_by_user_id(session.getAttribute("userid").toString());
			
			systemPref.setSystem_preference_code("default_tax_ratio");
			systemPref.setSystem_preference_value(request.getParameter("default_tax_ratio"));
			systemService.updatePrefByCode(systemPref);
			
			systemPref.setSystem_preference_code("default_contract_path");
			systemPref.setSystem_preference_value(request.getParameter("default_contract_path"));
			systemService.updatePrefByCode(systemPref);
			
			return "{'objname':'系统默认参数'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/approval_rule_list.do",method = RequestMethod.GET)
	public String approvalRuleList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			model.addAttribute("approvalList", authService.queryApprovalRuleList());
			return "system/approval_rule_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/approval_rule_add.do",method = RequestMethod.GET)
	public String addApprovalRule(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			return "system/approval_rule_add";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 提交审核流程
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/submitApproval.do",method = RequestMethod.GET)
	@ResponseBody
	public int  submitApproval(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String userId = String.valueOf(session.getAttribute("userid"));
		String name = request.getParameter("name");
		String apprObject = request.getParameter("apprObject");
		String apprObjectCd = request.getParameter("apprObjectCd");
		String type = request.getParameter("type");
		String typeCd = request.getParameter("typeCd");
		String rollback = request.getParameter("rollback");
		String rollbackCd = request.getParameter("rollbackCd");
		String email = request.getParameter("email");
		String inbox = request.getParameter("inbox");
		String count = request.getParameter("count");
		String users = request.getParameter("users");
		String usersId = request.getParameter("usersId");
		String method = request.getParameter("method");
		String methodCd = request.getParameter("methodCd");
		String id = request.getParameter("id");
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("apprObject", apprObject);
		params.put("apprObjectCd", apprObjectCd);
		params.put("type", type);
		params.put("typeCd", typeCd);
		params.put("rollback", rollback);
		params.put("rollbackCd", rollbackCd);
		if(email != null){
			params.put("email", true);
		}else{
			params.put("email", false);
		}
		if(inbox != null){
			params.put("inbox", true);
		}else{
			params.put("inbox", false);
		}
		params.put("count", count);
		params.put("users", users);
		params.put("usersId", usersId);
		params.put("method", method);
		params.put("methodCd", methodCd);
		params.put("userId", userId);
		params.put("id", id);
		try{
			int res = 0;
			//保存新建审核流程
			res = authService.submitApproval(params);
			return res;
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 提交修改后的审核流程
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/submitUpdateApproval.do",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitUpdateApproval(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse reponse) throws Exception{
		String userId = String.valueOf(session.getAttribute("userid"));
		String name = request.getParameter("name");
		String apprObject = request.getParameter("apprObject");
		String apprObjectCd = request.getParameter("apprObjectCd");
		String type = request.getParameter("type");
		String typeCd = request.getParameter("typeCd");
		String rollback = request.getParameter("rollback");
		String rollbackCd = request.getParameter("rollbackCd");
		String email = request.getParameter("email");
		String inbox = request.getParameter("inbox");
		String count = request.getParameter("count");
		String users = request.getParameter("users");
		String usersId = request.getParameter("usersId");
		String method = request.getParameter("method");
		String methodCd = request.getParameter("methodCd");
		String id = request.getParameter("id");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("apprObject", apprObject);
		params.put("apprObjectCd", apprObjectCd);
		params.put("type", type);
		params.put("typeCd", typeCd);
		params.put("rollback", rollback);
		params.put("rollbackCd", rollbackCd);
		if(email != null){
			params.put("email", true);
		}else{
			params.put("email", false);
		}
		if(inbox != null){
			params.put("inbox", true);
		}else{
			params.put("inbox", false);
		}
		params.put("count", count);
		params.put("users", users);
		params.put("usersId", usersId);
		params.put("method", method);
		params.put("methodCd", methodCd);
		params.put("userId", userId);
		params.put("id", id);
		try{
			int res = 0;
			//保存更新后的审核流程
			res = authService.submitUpdateApproval(params);
//			return String.valueOf(res);
			Map<String,Object> m = new HashMap<String, Object>();
			m.put("res", res);
			return m;
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/submitUpdateApproval1.do",method = RequestMethod.POST)
	@ResponseBody
	public String  submitUpdateApprovalTest(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		try{
			int res = 1;
			//保存更新后的审核流程
			//res = authService.submitUpdateApproval(params);
			return String.valueOf(res);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	/**
	 * 加载审核用户
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loadAvailUser.do",method = RequestMethod.GET)
	@ResponseBody
	public List<User> loadAvailUser(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		try{
			return userService.selectUserList();
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 删除审核规则
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteApproval.do",method = RequestMethod.GET)
	@ResponseBody
	public int deleteApproval(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String ids = request.getParameter("ids");
		String[] id = ids.split(";");
		int res = 0;
		try{
			for(int i=0;i<id.length;i++){
				//删除审核规则
				res = authService.deleteApproval(id[i]);
			}
			return res;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/list_of_values.do",method = RequestMethod.GET)
	public String listOfValues(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<ListOfValue> list_of_values_list = systemService.getAllListOfValues();
			model.addAttribute("list_of_values_list", list_of_values_list);
			return "system/list_of_value";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/list_of_value_upsert.do",method = RequestMethod.POST)
	@ResponseBody
	public String createListOfValue(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			ListOfValue list_of_value = new ListOfValue();
			list_of_value.setCreated_by_user_id(session.getAttribute("userid").toString());
			list_of_value.setUpdated_by_user_id(session.getAttribute("userid").toString());
			list_of_value.setList_code(request.getParameter("list_code"));
			list_of_value.setList_name(request.getParameter("list_name"));
			list_of_value.setList_value(request.getParameter("list_value"));
			list_of_value.setList_desc(request.getParameter("list_desc"));
			if(request.getParameter("row_id")==null || request.getParameter("row_id").equals("")){
				if(systemService.addValue(list_of_value)){
					return "{'id':'success'}";
				}		
				return "{'noid':'failed'}";
			}else{
				list_of_value.setId(request.getParameter("row_id"));
				if(systemService.updateValue(list_of_value)){
					return "{'id':'success'}";
				}		
				return "{'noid':'failed'}";
			}
			
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/list_of_value_delete.do",method = RequestMethod.POST)
	@ResponseBody
	public String deleteListOfValue(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			if(systemService.delValueById(request.getParameter("row_id"))){
				return "{'id':'success'}";
			}		
			return "{'noid':'failed'}";
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 跳转详细信息页面
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/approvalDetailPage.do",method = RequestMethod.GET)
	public String approvalDetailPage(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String id = request.getParameter("ruleId");
		try{
			model.addAttribute("ruleId",id);
			return  "system/approval_rule_detail";
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/approvalDetail.do",method = RequestMethod.GET)
	@ResponseBody
	public Approval approvalDetail(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String ruleId = request.getParameter("id");
		try{
			//根据ruleId查询审核信息
			
			Approval approval = new Approval();
			approval = authService.queryApprovalRuleDetail(ruleId);
			return approval;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	//test--------------------------------------
//	@RequestMapping(value="/test.do",method = RequestMethod.GET)
//	@ResponseBody
//	public String test(Model model, HttpServletRequest request, HttpSession session) throws Exception{
//		String t1 = request.getParameter("test1");
//		String t2 = request.getParameter("test2");
//		String t3 = request.getParameter("test3");
//		String t4 = request.getParameter("test4");
//		try{
//			//test接口
//			return authService.ApprovalExecute(t1, t2, t3, t4);
//		}catch(Exception e){
//			logger.info(e.getMessage());
//			throw e;
//		}
//	}
}
