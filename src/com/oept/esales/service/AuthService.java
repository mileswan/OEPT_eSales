package com.oept.esales.service;

import java.util.List;
import java.util.Map;

import com.oept.esales.model.Account;
import com.oept.esales.model.Approval;
import com.oept.esales.model.ApprovalDetail;
import com.oept.esales.model.ApprovalStep;
import com.oept.esales.model.Auth;


/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/30
 * Description: Authorization service interface.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public interface AuthService {

	
	/**
	 * 以层级为条件查询权限
	 * @return
	 * @throws Exception
	 */
	public List<Auth> queryAuthLvl(Object[] params) throws Exception;
	
	/**
	 * 查询权限最大层级
	 * @return
	 * @throws Exception
	 */
	public int queryAuthMaxLvl() throws Exception;
	
	/**
	 * 查询职位可用权限
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Auth> queryAuthItem(Object[] params) throws Exception;
	
	/**
	 * 查询该职位是否已经拥有该权限（1为拥有，0为未拥有）
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int queryThisAuthYoN(Object[] params) throws Exception;
	
	/**
	 * 为职位添加权限
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int addAuthItem(Object[] params) throws Exception;
	
	/**
	 * 删除职位的该权限（删除中间表）
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int delAuthItem(Object[] params) throws Exception;
	
	/**
	 * 查询该权限
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Auth queryAuth(Auth auth) throws Exception;
	
	/**
	 * 查询该权限下所有权限
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Auth> queryAuthPar(Object[] params) throws Exception;
	
	
	/**
	 * 查询权限(boolean)(position,code)
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean queryPermissions(Object[] params) throws Exception;
	
	/**
	 * 提交审批规则
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int submitApproval(Map<String,Object> params) throws Exception;
	
	/**
	 * 提交更新后的审批规则
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int submitUpdateApproval(Map<String,Object> params) throws Exception;
	
	/**
	 * 查询审批规则集合
	 * @return
	 * @throws Exception
	 */
	public List<Approval> queryApprovalRuleList() throws Exception;
	
	/**
	 * 查询审批规则信息
	 * @return
	 * @throws Exception
	 */
	public Approval queryApprovalRuleDetail(String ruleId) throws Exception;
	
	/**
	 * 删除审批规则
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int deleteApproval(String id) throws Exception;
	

	/**
	 * 审核流程执行接口
	 * @param objectId
	 * @param objectName
	 * @param ActionType (Submit,Approve,Reject)
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String ApprovalExecute(String objectId,String objectName,String ActionType,String userId) throws Exception;
	
	/**
	 * 查询审核流程中审核人当前的审核状态
	 * @param objectId
	 * @param objectName
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ApprovalDetail queryApprovalDetailStatus(String objectId,String objectName,String userId) throws Exception;
	
	/**
	 * 查询审核详细信息接口
	 * @param id
	 * @param type (Order/Requisition/Contract)
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalStep> queryApproval(String id,String type) throws Exception;
}
