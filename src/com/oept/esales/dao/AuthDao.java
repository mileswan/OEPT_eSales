package com.oept.esales.dao;

import java.util.List;

import com.oept.esales.model.Approval;
import com.oept.esales.model.ApprovalDetail;
import com.oept.esales.model.ApprovalItem;
import com.oept.esales.model.ApprovalItemPer;
import com.oept.esales.model.ApprovalStep;
import com.oept.esales.model.Auth;




/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/30
 * Description: Categories DAO interface.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
public interface AuthDao {
	
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
	 * 查询职位可用权限（返回List）
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
	 * 审核规则设定方法----------------------------
	 */
	/**
	 * 保存审核规则
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int saveApprovalRule(Object[] params) throws Exception;
	
	/**
	 * 更新审核规则
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateApprovalRule(Object[] params) throws Exception;
	
	/**
	 * 查询审批规则
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Approval> queryApprovalRule() throws Exception;
	
	/**
	 * 查询审核规则是否已存在
	 * @return
	 * @throws Exception
	 */
	public boolean queryApprovalRuleBoolean(Object[] params) throws Exception;

	
	/**
	 * 查询审核规则(条件查询)(name属性)
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Approval> queryApprovalRuleCond(Approval approval) throws Exception;
	
	/**
	 * 查询审批规则信息（返回Approval对象）(id属性)
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Approval queryApprovalRuleDetail(Object[] params) throws Exception;
	
	/**
	 * 保存审核流程（流程第一步）
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int saveApprovalRuleItemOne (Object[] params) throws Exception;
	
	/**
	 * 保存审核流程（流程第一步）
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int saveApprovalRuleItemOne2 (Object[] params) throws Exception;
	/**
	 * 保存审核流程
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int saveApprovalRuleItem (Object[] params) throws Exception;
	
	/**
	 * 保存审核流程
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int saveApprovalRuleItem2 (Object[] params) throws Exception;
	
	/**
	 * 查询审核流程信息（条件查询）（id属性）
	 * @param approval
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalItem> queryApprovalRuleItem(ApprovalItem approvalItem) throws Exception;
	
	/**
	 * 添加审核流程的审核人
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int saveApprovalItemUser(Object[] params) throws Exception;
	
	/**
	 * 查询审批规则流程审核人信息
	 * @param approvalItem
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalItemPer> queryApprovalItemPer(ApprovalItemPer approvalItemPer) throws Exception;
	
	/**
	 * 删除审核人信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int delApprovalItemPer(Object[] params) throws Exception;
	
	/**
	 * 删除审核流程
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int delApprovalItem(Object[] params) throws Exception;
	
	/**
	 * 删除审核规则
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int delApproval(Object[] params) throws Exception;
	
	//BEGIN 审核流程执行接口----------------------------------------------
	/**
	 * 根据ObjectName判断是否存在有效的审核规则（code）
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int queryAvailApprovalRule(Object[] params) throws Exception;
	
	/**
	 * 根据ObjectId查询approval Step表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalStep> queryApprovalStepId(ApprovalStep approvalStep) throws Exception;
	
	/**
	 * 根据ObjectId查询approval Step表用来删除查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalStep> queryApprovalStepIdDel(ApprovalStep approvalStep) throws Exception;
	
	/**
	 * 删除审核详情表数据方法
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int delApprovalDetailStepId(ApprovalDetail approvalDetail)throws Exception;
	
	/**
	 * 删除审核步骤表数据方法
	 * @param approvalStep
	 * @return
	 * @throws Exception
	 */
	public int delApprovalStepId(ApprovalStep approvalStep) throws Exception;
	
	/**
	 * 查询ObjectId step表有效数据
	 * true为存在数据，false为不存在数据
	 * @param approvalStep
	 * @return
	 * @throws Exception
	 */
	public boolean queryApprovalStepValidData(ApprovalStep approvalStep) throws Exception;
	
	/**
	 * 查询ObjectId Detail表有效数据
	 * true为存在数据，false为不存在数据
	 * @param approvalStep
	 * @return
	 * @throws Exception
	 */
	public boolean queryApprovalDetailValidData(ApprovalDetail approvalDetail) throws Exception;
	
	/**
	 * 条件查询，查询审核规则
	 * @param approval
	 * @return
	 * @throws Exception
	 */
	public Approval queryCondApprovalRule(Approval approval) throws Exception;
	
	/**
	 * 添加审核执行步骤
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int addApprovalStep(Object[] params,boolean firstStep,String idType) throws Exception;
	
	/**
	 * 添加审核执行步骤详细信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int addApprovalDetail(Object[] params) throws Exception;
	
	/**
	 * 查询objectId所有的审核步骤
	 * @param approvalStep
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalStep> queryApprovalSteps(ApprovalStep approvalStep) throws Exception; 
	
	/**
	 * 查询是否存在有效步骤
	 * @param approvalStep
	 * @return
	 * @throws Exception
	 */
	public boolean queryApprovalStepValidFlag(ApprovalStep approvalStep) throws Exception;
	
	/**
	 * 查询ObjectId正在执行的审核步骤
	 * @param approvalStep
	 * @return
	 * @throws Exception
	 */
	public ApprovalStep queryApprovalStepFlag(ApprovalStep approvalStep) throws Exception;
	
	/**
	 * 查询步骤信息方法
	 * @param approvalStep
	 * @return
	 * @throws Exception
	 */
	public ApprovalStep queryApprovalStep(ApprovalStep approvalStep) throws Exception;
	
	/**
	 * 查询审核步骤下的详细信息
	 * @param approvalDetial
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalDetail> queryApprovalDetailCond(ApprovalDetail approvalDetail) throws Exception;
	
	/**
	 * 更改审核详细信息
	 * @param approvalDetail
	 * @return
	 * @throws Exception
	 */
	public int updateApprovalDetailToApprove(Object[] params) throws Exception;
	
	/**
	 * 更改审核步骤进行状态
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateApprovalStepFlag(Object[] params) throws Exception;
	
	/**
	 * 查询步骤是否有下一步
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean queryApprovalStepNext(Object[] params) throws Exception;
	
	/**
	 * 更改下一步审核步骤进行状态
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateNextApprovalStepFlag(Object[] params) throws Exception;
	
	/**
	 * 查询步骤是否为第一步
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean queryApprovalStepFirst(Object[] params) throws Exception;
	
	/**
	 * 初始化步骤详细记录数据
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateApprovalDetailInit(Object[] params) throws Exception;
	
	/**
	 * 查询该用户需要操作的审核流程状态
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ApprovalDetail queryApprovalDetailUserId(Object[] params) throws Exception;
	
	
	//END 审核流程执行接口----------------------------------------------
}
