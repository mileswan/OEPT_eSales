package com.oept.esales.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oept.esales.action.AuthAct;
import com.oept.esales.dao.AuthDao;
import com.oept.esales.dao.UserDao;
import com.oept.esales.model.Approval;
import com.oept.esales.model.ApprovalDetail;
import com.oept.esales.model.ApprovalItem;
import com.oept.esales.model.ApprovalItemPer;
import com.oept.esales.model.ApprovalStep;
import com.oept.esales.model.Auth;
import com.oept.esales.model.User;
import com.oept.esales.service.AuthService;
import com.oept.esales.service.EmailService;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/30
 * Description: Authorization info service implements.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

	private static final Log logger = LogFactory.getLog(AuthAct.class);
	@Autowired
	private AuthDao authDao;
	
	@Autowired
	private UserDao userDao;
	
	@Qualifier("emailService")
	@Autowired
	private EmailService emailService;

	/**
	 * 以层级为条件查询权限
	 */
	@Override
	public List<Auth> queryAuthLvl(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return authDao.queryAuthLvl(params);
	}

	/**
	 * 查询权限最大层级
	 */
	@Override
	public int queryAuthMaxLvl() throws Exception {
		// TODO Auto-generated method stub
		return authDao.queryAuthMaxLvl();
	}

	/**
	 * 查询用职位可用权限
	 */
	@Override
	public List<Auth> queryAuthItem(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return authDao.queryAuthItem(params);
	}

	/**
	 * 查询该职位是否已经拥有该权限
	 */
	@Override
	public int queryThisAuthYoN(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return authDao.queryThisAuthYoN(params);
	}

	/**
	 * 为职位添加权限
	 */
	@Override
	public int addAuthItem(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return authDao.addAuthItem(params);
	}

	/**
	 * 删除职位的该权限
	 */
	@Override
	public int delAuthItem(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return authDao.delAuthItem(params);
	}

	/**
	 * 查询该权限
	 */
	@Override
	public Auth queryAuth(Auth auth) throws Exception {
		// TODO Auto-generated method stub
		return authDao.queryAuth(auth);
	}

	/**
	 * 查询该权限下所有权限
	 */
	@Override
	public List<Auth> queryAuthPar(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return authDao.queryAuthPar(params);
	}

	/**
	 * 查询权限(boolean)(position,code)
	 */
	@Override
	public boolean queryPermissions(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return authDao.queryPermissions(params);
	}

	/**
	 * 提交审批规则
	 */
	@Override
	@Transactional
	public int submitApproval(Map<String,Object> params) throws Exception {
		//获取所有参数
		String name = String.valueOf(params.get("name"));
		String apprObject = String.valueOf(params.get("apprObject"));
		String apprObjectCd = String.valueOf(params.get("apprObjectCd"));
		String type = String.valueOf(params.get("type"));
		String typeCd = String.valueOf(params.get("typeCd"));
		String rollback = String.valueOf(params.get("rollback"));
		String rollbackCd = String.valueOf(params.get("rollbackCd"));
		boolean email = (boolean) params.get("email");
		boolean inbox = (boolean) params.get("inbox");
		String usersId = String.valueOf(params.get("usersId"));
		String method = String.valueOf(params.get("method"));
		String methodCd = String.valueOf(params.get("methodCd"));
		String userId = String.valueOf(params.get("userId"));
		
		//查询审核规则是否存在，如果已存在，则提示信息，不存在添加规则
		Object[] paramsIsTrue = new Object[]{apprObjectCd,typeCd};
		boolean trueOfalse = authDao.queryApprovalRuleBoolean(paramsIsTrue);
		if(trueOfalse == true){
			return 3;
		}else{
			Object[] paramsRule = new Object[]{
					name,apprObjectCd,apprObject,typeCd,type,rollbackCd,rollback,email,inbox,userId
				};
				//提交审批规则，存入审核规则表rule
				int ruleId = authDao.saveApprovalRule(paramsRule);
				String userIdArray[] = usersId.split("/");
				String methodArray[] = method.split("/");
				String methodCdArray[] = methodCd.split("/");
				int itemId=0;
				int res = 0;
				for(int i=0;i<methodArray.length;i++){
					if(i == 0){
						Object[] paramsItem = new Object[]{ruleId,methodCdArray[i],methodArray[i],userId};
						//存入审核规则审核流程表item（添加流程第一步，没有父类列）
						itemId = authDao.saveApprovalRuleItemOne(paramsItem);
					}else{
						Object[] paramsItem = new Object[]{ruleId,methodCdArray[i],methodArray[i],itemId,userId};
						//存入审核规则审核流程表item（添加流程第一步，没有父类列）
						itemId = authDao.saveApprovalRuleItem(paramsItem);
					}
					
//					ApprovalItem aip = new ApprovalItem();
//					aip.setRule_id(ruleId);
//					//根据参数条件查询该条数据id
//					List<ApprovalItem> approvalItems = authDao.queryApprovalRuleItem(aip);
//					//获取审核流程 id
//					String itemId = approvalItems.get(i).getItem_id();
					//创建审核流程，用户中间表数据
					String userIds[] = userIdArray[i].split(";");
					for(int k=0;k<userIds.length;k++){
						Object[] paramsItemPer = new Object[]{itemId,userIds[k],userId};
						res = authDao.saveApprovalItemUser(paramsItemPer);
					}
				}
				return res;
		}
		
		
	}
	
	/**
	 * 提交更新后的审批规则
	 */
	@Override
	@Transactional
	public int submitUpdateApproval(Map<String, Object> params)
			throws Exception {
		//获取所有参数
		String name = String.valueOf(params.get("name"));
		String apprObject = String.valueOf(params.get("apprObject"));
		String apprObjectCd = String.valueOf(params.get("apprObjectCd"));
		String type = String.valueOf(params.get("type"));
		String typeCd = String.valueOf(params.get("typeCd"));
		String rollback = String.valueOf(params.get("rollback"));
		String rollbackCd = String.valueOf(params.get("rollbackCd"));
		boolean email = (boolean) params.get("email");
		boolean inbox = (boolean) params.get("inbox");
		String usersId = String.valueOf(params.get("usersId"));
		String method = String.valueOf(params.get("method"));
		String methodCd = String.valueOf(params.get("methodCd"));
		String userId = String.valueOf(params.get("userId"));
		String ruleId = String.valueOf(params.get("id"));
		//参数
		Object[] paramsRule = new Object[]{
			name,apprObjectCd,apprObject,typeCd,type,rollbackCd,rollback,email,inbox,userId,ruleId
		};
		int res = 0;
		//根据rule id更改数据
		res = authDao.updateApprovalRule(paramsRule);
		if(res != 0){
			//参数
			ApprovalItem approvalItemParams = new ApprovalItem();
			approvalItemParams.setRule_id(ruleId);
			//查询rule id子记录
			List<ApprovalItem> approvalItem = authDao.queryApprovalRuleItem(approvalItemParams);
			//遍历删除子记录
			for(int i=approvalItem.size();i>0;i--){
				//参数
				Object paramsItemDel[] = new Object[]{approvalItem.get((i-1)).getItem_id()}; 
				//根据itemId删除子记录审核人
				int res1 = authDao.delApprovalItemPer(paramsItemDel);
				//根据itemId删除子记录
				int res2 = authDao.delApprovalItem(paramsItemDel);
			}
			String userIdArray[] = usersId.split("/");
			String methodArray[] = method.split("/");
			String methodCdArray[] = methodCd.split("/");
			int itemId=0;
			for(int i=0;i<methodArray.length;i++){
				if(i == 0){
					Object[] paramsItem = new Object[]{ruleId,methodCdArray[i],methodArray[i],userId};
					//存入审核规则审核流程表item（添加流程第一步，没有父类列）
					itemId = authDao.saveApprovalRuleItemOne2(paramsItem);
				}else{
					Object[] paramsItem = new Object[]{ruleId,methodCdArray[i],methodArray[i],itemId,userId};
					//存入审核规则审核流程表item（添加流程第一步，没有父类列）
					itemId = authDao.saveApprovalRuleItem2(paramsItem);
				}
				
				//创建审核流程，用户中间表数据
				String userIds[] = userIdArray[i].split(";");
				for(int k=0;k<userIds.length;k++){
					Object[] paramsItemPer = new Object[]{itemId,userIds[k],userId};
					res = authDao.saveApprovalItemUser(paramsItemPer);
				}
			}
		}
		return res;
	}

	/**
	 * 查询审批规则集合
	 */
	@Override
	public List<Approval> queryApprovalRuleList() throws Exception {
		// TODO Auto-generated method stub
		return authDao.queryApprovalRule();
	}

	/**
	 * 查询审批规则信息
	 */
	@Override
	public Approval queryApprovalRuleDetail(String ruleId) throws Exception {
		// TODO Auto-generated method stub
		Approval approval = new Approval();
		//根据ruleId查询该审批规则信息
		Object[] paramsApprovalRuleDetail = new Object[]{ruleId};
		approval = authDao.queryApprovalRuleDetail(paramsApprovalRuleDetail);
		//根据查询到的信息查询所有旗下的子信息
		ApprovalItem approvalItemParams = new ApprovalItem();
		approvalItemParams.setRule_id(ruleId);
		List<ApprovalItem> approvalItem = new ArrayList<ApprovalItem>();
		approvalItem = authDao.queryApprovalRuleItem(approvalItemParams);
		for(int i=0;i<approvalItem.size();i++){
			String itemId = approvalItem.get(i).getItem_id();
			//根据itemId查询旗下所有用户id
			ApprovalItemPer approvalItemPerParams = new ApprovalItemPer();
			approvalItemPerParams.setItem_id(itemId);
			List<ApprovalItemPer> approvalItemPer = authDao.queryApprovalItemPer(approvalItemPerParams);
			//根据userId查询用户信息
			for(int j=0;j<approvalItemPer.size();j++){
				String userId = approvalItemPer.get(j).getUser_id();
				approvalItemPer.get(j).setUser(userDao.selectUserDetail(userId));
			}
			approvalItem.get(i).setApprovalItemPer(approvalItemPer);
		}
		approval.setApprovalItem(approvalItem);
		return approval;
	}

	/**
	 * 删除审批规则
	 */
	@Override
	public int deleteApproval(String id) throws Exception {
		// TODO Auto-generated method stub
		//参数
		ApprovalItem approvalItemParams = new ApprovalItem();
		approvalItemParams.setRule_id(id);
		//查询rule id子记录
		List<ApprovalItem> approvalItem = authDao.queryApprovalRuleItem(approvalItemParams);
		//遍历删除子记录
		for(int i=approvalItem.size();i>0;i--){
			//参数
			Object paramsItemDel[] = new Object[]{approvalItem.get((i-1)).getItem_id()}; 
			//根据itemId删除子记录审核人
			int res1 = authDao.delApprovalItemPer(paramsItemDel);
			//根据itemId删除子记录
			int res2 = authDao.delApprovalItem(paramsItemDel);
		}
		Object[] params = new Object[]{id};
		//删除规则
		int res = authDao.delApproval(params);
		return res;
	}

	/**
	 * 审核流程执行接口
	 * @param objectId
	 * @param objectName
	 * @param ActionType (Submit,Approve,Reject)
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public String ApprovalExecute(String objectId, String objectName,
			String actionType, String userId) throws Exception {
		// TODO Auto-generated method stub
		String status_cd ;
		String status_val;
		boolean process_flg;
		//取出Object id 类型
		String objectIdType = "";
		if(objectName.equals("Account")||objectName.equals("Product")){
			objectIdType = objectName;
		}else{
			String[] Osplit = objectName.split(" ");
			for(int i=0;i<Osplit.length;i++){
				if(i == (Osplit.length-1)){
					if(Osplit[i].equals("Order")){
						objectIdType = Osplit[i];
					}
					if(Osplit[i].equals("Requisition")){
						objectIdType = Osplit[i];
					}
					if(Osplit[i].equals("Contract")){
						objectIdType = Osplit[i];
					}
				}
			}
		}
		//判断传入参数是否有值
		if(objectId!=null&&objectName!=null&&actionType!=null&&userId!=null&&!objectId.equals("")&&!objectName.equals("")&&!actionType.equals("")&&!userId.equals("")){
			//根据ObjectName判断是否存在有效的审核规则（code）
			Object[] params_qaar = new Object[]{objectName};
			int resValue = authDao.queryAvailApprovalRule(params_qaar);
			if(resValue != 0){
				//存在有效的审核规则
				//根据ObjectName查询审核规则
				Approval approval = new Approval();
				approval.setObject_cd(objectName);
				Approval resApproval = authDao.queryCondApprovalRule(approval);
				//判断ActionType的值
				if(actionType.equals("Submit")){
					status_cd = "Pending";
					status_val = "待审核";
					process_flg = true;
					int resStepId = 0;
					//actionType为submit,提交审核
					//清空ObjectId下的审核明细
					clearObjectIdApproval(objectId,objectName);
					//创建相应的步骤详细记录Apprvoal
					//根据审核规则查询流程ApprovalItem
					ApprovalItem approvalItem = new ApprovalItem();
					approvalItem.setRule_id(resApproval.getRule_id());
					List<ApprovalItem> resApprovalItem = authDao.queryApprovalRuleItem(approvalItem);
					//按步骤创建Step
					for(int i=0;i<resApprovalItem.size();i++){
						//创建step数据
						if(i==0){
							//第一步，判断Id类型创建数据
							//参数值（objectId,status_cd,status_val,method_cd,method_name,rollback_cd,rollback_name,process_flg）
							Object[] paramsNewStep = new Object[]{
									objectId,status_cd,status_val,resApprovalItem.get(i).getMethod_cd(),
									resApprovalItem.get(i).getMethod_name(),resApproval.getRollback_type_cd(),
									resApproval.getRollback_type_name(),process_flg};
							resStepId = authDao.addApprovalStep(paramsNewStep, true, objectIdType);
						}else{
							//不是第一步
							process_flg = false;
							//参数值（par_id,objectId,status_cd,status_val,method_cd,method_name,rollback_cd,rollback_name,process_flg）
							Object[] paramsNewStep = new Object[]{
									resStepId,objectId,status_cd,status_val,resApprovalItem.get(i).getMethod_cd(),
									resApprovalItem.get(i).getMethod_name(),resApproval.getRollback_type_cd(),
									resApproval.getRollback_type_name(),process_flg};
							resStepId = authDao.addApprovalStep(paramsNewStep, false, objectIdType);
						}
						//查询审批规则流程审核人信息
						ApprovalItemPer approvalItemPer = new ApprovalItemPer();
						approvalItemPer.setItem_id(resApprovalItem.get(i).getItem_id());
						List<ApprovalItemPer> resApprovalItemPer = authDao.queryApprovalItemPer(approvalItemPer);
						for(int j=0;j<resApprovalItemPer.size();j++){
							//创建detail数据 
							//参数值（objectId,status_cd,status_val,method_cd,method_name,rollback_cd,rollback_name,process_flg）
							Object[] paramsNewDetail = new Object[]{
									resStepId,status_cd,status_val,resApprovalItemPer.get(j).getUser_id(),
									resApprovalItem.get(i).getMethod_cd(),resApprovalItem.get(i).getMethod_name(),
									resApproval.getRollback_type_cd(),resApproval.getRollback_type_name()};
							authDao.addApprovalDetail(paramsNewDetail);
							try {
								//判断为第一步发送通知信息
								if(i==0){
									//判断通知信息，发送通知消息
									//邮件提醒
									if(resApproval.isEmail()==true){
										//查询审核人详细信息
										User user= userDao.selectUserDetail(resApprovalItemPer.get(j).getUser_id());
										emailService.createSimpleMail(user.getEmail(),"以下单据需要您的审核",emailService.buildApprovalEmailContent(objectId,objectName,user.getUserName()).toString());
									}
									//站内提醒
									if(resApproval.isInbox()==true){
										
									}
								}
							} catch (Exception e) {
								logger.info(e.getMessage());
								throw e;
							}
						}
					}
					return "Pending";
				}else if(actionType.equals("Approve")){
					//actionType为approve,审核中
					//根据objectId查询步骤信息
					process_flg = true;
					ApprovalStep approvalStep = new ApprovalStep();
					if(objectIdType.equals("Order")){
						approvalStep.setOrder_id(objectId);
					}else if(objectIdType.equals("Requisition")){
						approvalStep.setRequisition_id(objectId);
					}else if(objectIdType.equals("Contract")){
						approvalStep.setContract_id(objectId);
					}else if(objectIdType.equals("Account")){
						approvalStep.setAccount_id(objectId);
					}else if(objectIdType.equals("Product")){
						approvalStep.setProduct_id(objectId);
					}
					//查询是否有效步骤标记
					boolean resValidFlag = authDao.queryApprovalStepValidFlag(approvalStep);
					if(resValidFlag == true){
						ApprovalStep resApprovalStep = authDao.queryApprovalStepFlag(approvalStep);
						//更改审核人审核状态
						status_cd = "Approved";
						status_val = "已审核";
						Object[] paramsUpdateToApprove = new Object[]{status_cd,status_val,resApprovalStep.getId(),userId};
						int resUpdADVal = authDao.updateApprovalDetailToApprove(paramsUpdateToApprove);
						//判断method value
						if(resApprovalStep.getMethod_cd().equals("All Must Pass")){
							//查询当前step下其他detail的审核状态
							ApprovalDetail paramsApprovalDetails = new ApprovalDetail();
							paramsApprovalDetails.setStep_id(resApprovalStep.getId());
							List<ApprovalDetail> resApprovalDetails = authDao.queryApprovalDetailCond(paramsApprovalDetails);
							boolean allMustPass = true;
							for(int i=0;i<resApprovalDetails.size();i++){
								if(!resApprovalDetails.get(i).getStatus_cd().equals("Approved")){
									allMustPass = false;
								}
							}
							if(allMustPass == true){
								//更改当前步骤process_flg状态为false
								process_flg = false;
								status_cd = "Approved";
								status_val = "已审核";
								Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getId()};
								int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
								//查询是否有下一步
								Object[] paramsQueryNext = new Object[]{resApprovalStep.getId()};
								boolean resQueryNext = authDao.queryApprovalStepNext(paramsQueryNext);
								if(resQueryNext == true){
									//修改下一步步骤process_flg状态为true
									process_flg = true;
									Object[] paramsUpdateNextFlag = new Object[]{process_flg,resApprovalStep.getId()};
									int resUpdNASVal = authDao.updateNextApprovalStepFlag(paramsUpdateNextFlag);
									//判断通知信息，发送通知消息
									try {
										//邮件提醒
										if(resApproval.isEmail()==true){
											//查询下一步审核步骤信息
											ApprovalStep step = new ApprovalStep();
											step.setPar_id(resApprovalStep.getId());
											ApprovalStep resStep = authDao.queryApprovalStep(step);
											//查询该步骤下详细信息
											ApprovalDetail approvalDetail = new ApprovalDetail();
											approvalDetail.setStep_id(resStep.getId());
											List<ApprovalDetail> resApprovalDetail = authDao.queryApprovalDetailCond(approvalDetail);
											for(int i=0;i<resApprovalDetail.size();i++){
												User user= userDao.selectUserDetail(resApprovalDetail.get(i).getTo_approve());
												emailService.createSimpleMail(user.getEmail(),"以下单据需要您的审核",emailService.buildApprovalEmailContent(objectId,objectName,user.getUserName()).toString());
											}
										}
										//站内提醒
										if(resApproval.isInbox()==true){
											
										}
									} catch (Exception e) {
										// TODO Auto-generated catch block
										logger.info(e.getMessage());
										throw e;
									}
									return "Pending";
								}else{
									//当前步骤为最后一步
									return "Approved";
								}
							}else{
								//并未全部通过
								return "Pending";
							}
						}else{
							//更改当前步骤process_flg状态为false
							process_flg = false;
							status_cd = "Approved";
							status_val = "已审核";
							Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getId()};
							int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
							//查询是否有下一步
							Object[] paramsQueryNext = new Object[]{resApprovalStep.getId()};
							boolean resQueryNext = authDao.queryApprovalStepNext(paramsQueryNext);
							if(resQueryNext == true){
								//修改下一步步骤process_flg状态为true
								process_flg = true;
								Object[] paramsUpdateNextFlag = new Object[]{process_flg,resApprovalStep.getId()};
								int resUpdNASVal = authDao.updateNextApprovalStepFlag(paramsUpdateNextFlag);
								//判断通知信息，发送通知消息
								try {
									//邮件提醒
									if(resApproval.isEmail()==true){
										//查询下一步的审核步骤信息
										ApprovalStep step = new ApprovalStep();
										step.setPar_id(resApprovalStep.getId());
										ApprovalStep resStep = authDao.queryApprovalStep(step);
										//查询该步骤下详细信息
										ApprovalDetail approvalDetail = new ApprovalDetail();
										approvalDetail.setStep_id(resStep.getId());
										List<ApprovalDetail> resApprovalDetail = authDao.queryApprovalDetailCond(approvalDetail);
										for(int i=0;i<resApprovalDetail.size();i++){
											User user= userDao.selectUserDetail(resApprovalDetail.get(i).getTo_approve());
											emailService.createSimpleMail(user.getEmail(),"以下单据需要您的审核",emailService.buildApprovalEmailContent(objectId,objectName,user.getUserName()).toString());
										}
									}
									//站内提醒
									if(resApproval.isInbox()==true){
										
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									logger.info(e.getMessage());
									throw e;
								}
								return "Pending";
							}else{
								//当前为最后一步
								return "Approved";
							}
						}
					}else{
						//不存在有效的审核步骤
						return "ApprovalStep Not Valid";
					}
					
				}else if(actionType.equals("Reject")){
					//actionType为reject,审核拒绝           
					process_flg = true;
					ApprovalStep approvalStep = new ApprovalStep();
					if(objectIdType.equals("Order")){
						approvalStep.setOrder_id(objectId);
					}else if(objectIdType.equals("Requisition")){
						approvalStep.setRequisition_id(objectId);
					}else if(objectIdType.equals("Contract")){
						approvalStep.setContract_id(objectId);
					}else if(objectIdType.equals("Account")){
						approvalStep.setAccount_id(objectId);
					}else if(objectIdType.equals("Product")){
						approvalStep.setProduct_id(objectId);
					}
					//查询是否有效步骤标记
					boolean resValidFlag = authDao.queryApprovalStepValidFlag(approvalStep);
					if(resValidFlag == true){
						//存在有效的步骤标记
						ApprovalStep resApprovalStep = authDao.queryApprovalStepFlag(approvalStep);
						//更改审核人审核状态
						status_cd = "Rejected";
						status_val = "已拒绝";
						Object[] paramsUpdateToApprove = new Object[]{status_cd,status_val,resApprovalStep.getId(),userId};
						int resUpdADVal = authDao.updateApprovalDetailToApprove(paramsUpdateToApprove);
						//判断method value
						if(resApprovalStep.getMethod_cd().equals("Anyone Pass")){
							//method为Anyone Pass
							//判断当前step下的其他审核人的审核状态
							ApprovalDetail paramsApprovalDetail = new ApprovalDetail();
							paramsApprovalDetail.setStep_id(resApprovalStep.getId());
							List<ApprovalDetail> resApprovalDetails =  authDao.queryApprovalDetailCond(paramsApprovalDetail);
							int resReturn = 0;
							for(int i=0;i<resApprovalDetails.size();i++){
								//查询detail的审核状态
								if(resApprovalDetails.get(i).getStatus_cd().equals("Pending")){
									resReturn = 1;
								}
							}
							if(resReturn == 0){
								//判断拒绝回滚类型rollback_type_cd的值，回滚
								if(resApprovalStep.getRollback_type_cd().equals("Revert")){
									//判断当前step是否为第一步
									Object[] paramsFirstStep = new Object[]{resApprovalStep.getId()};
									boolean resFirstStep = authDao.queryApprovalStepFirst(paramsFirstStep);
									if(resFirstStep == true){
										//当期步骤为第一步
										process_flg = false;
										status_cd = "Rejected";
										status_val = "已拒绝";
										Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getId()};
										int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
										return "Rejected";
									}else{
										//当前步骤不是第一步
										//更改当前步骤flag为false
										process_flg = false;
										status_cd = "Pending";
										status_val = "待审核";
										Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getId()};
										int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
										//更改当前Step Detail记录为初始值，重新开始
										Object[] paramsInitDetail = new Object[]{status_cd,status_val,resApprovalStep.getId()};
										authDao.updateApprovalDetailInit(paramsInitDetail);
										//更改上一步步骤flag为true
										process_flg = true;
										Object[] paramsUpdateFlag2 = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getPar_id()};
										int resUpdASVal2 = authDao.updateApprovalStepFlag(paramsUpdateFlag2);
										//更改当前Step Detail记录为初始值，重新开始
										Object[] paramsInitDetail2 = new Object[]{status_cd,status_val,resApprovalStep.getPar_id()};
										authDao.updateApprovalDetailInit(paramsInitDetail2);
										//判断通知信息，发送通知消息
										try {
											//邮件提醒
											if(resApproval.isEmail()==true){
												//查询上一步的步骤信息
												ApprovalStep step = new ApprovalStep();
												step.setId(resApprovalStep.getPar_id());
												ApprovalStep resStep = authDao.queryApprovalStep(step);
												//查询该步骤下详细信息
												ApprovalDetail approvalDetail = new ApprovalDetail();
												approvalDetail.setStep_id(resStep.getId());
												List<ApprovalDetail> resApprovalDetail = authDao.queryApprovalDetailCond(approvalDetail);
												for(int i=0;i<resApprovalDetail.size();i++){
													User user= userDao.selectUserDetail(resApprovalDetail.get(i).getTo_approve());
													emailService.createSimpleMail(user.getEmail(),"以下单据需要您的审核",emailService.buildApprovalEmailContent(objectId,objectName,user.getUserName()).toString());
												}
											}
											//站内提醒
											if(resApproval.isInbox()==true){
												
											}
										} catch (Exception e) {
											// TODO Auto-generated catch block
											logger.info(e.getMessage());
											throw e;
										}
										return "Pending";
									}
								}else if(resApprovalStep.getRollback_type_cd().equals("Restart")){
									//判断当前step是否为第一步
									Object[] paramsFirstStep = new Object[]{resApprovalStep.getId()};
									boolean resFirstStep = authDao.queryApprovalStepFirst(paramsFirstStep);
									if(resFirstStep == true){
										//当期步骤为第一步
										process_flg = false;
										status_cd = "Rejected";
										status_val = "已拒绝";
										Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getId()};
										int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
										return "Rejected";
									}else{
										//更改除第一步以外 step记录flag为false
										List<ApprovalStep> resAllStep = authDao.queryApprovalSteps(approvalStep);
										for(int i=0;i<resAllStep.size();i++){
											if(i==0){
												//更改第一步骤process_flg状态为true
												process_flg = true;
												status_cd = "Pending";
												status_val = "待审核";
												Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resAllStep.get(i).getId()};
												int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
												//判断通知信息，发送通知消息
												try {
													//邮件提醒
													if(resApproval.isEmail()==true){
														//给第一步的审核人发送通知
														//查询该步骤下详细信息
														ApprovalDetail approvalDetail = new ApprovalDetail();
														approvalDetail.setStep_id(resAllStep.get(i).getId());
														List<ApprovalDetail> resApprovalDetail = authDao.queryApprovalDetailCond(approvalDetail);
														for(int j=0;j<resApprovalDetail.size();j++){
															User user= userDao.selectUserDetail(resApprovalDetail.get(j).getTo_approve());
															emailService.createSimpleMail(user.getEmail(),"以下单据需要您的审核",emailService.buildApprovalEmailContent(objectId,objectName,user.getUserName()).toString());
														}
													}
													//站内提醒
													if(resApproval.isInbox()==true){
														
													}
												} catch (Exception e) {
													// TODO Auto-generated catch block
													logger.info(e.getMessage());
													throw e;
												}
											}else{
												//更改当前步骤process_flg状态为false
												process_flg = false;
												status_cd = "Pending";
												status_val = "待审核";
												Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resAllStep.get(i).getId()};
												int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
											}
											//更改所有Step Detail记录为初始值，重新开始
											status_cd = "Pending";
											status_val = "待审核";
											Object[] paramsInitDetail = new Object[]{status_cd,status_val,resAllStep.get(i).getId()};
											authDao.updateApprovalDetailInit(paramsInitDetail);
										}
										return "Pending";
									}
								}else if(resApprovalStep.getRollback_type_cd().equals("Cancel")){
									//全部为拒绝，返回Rejected
									process_flg = false;
									status_cd = "Rejected";
									status_val = "已拒绝";
									Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getId()};
									int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
									return "Rejected";
									
								}else{
									//回滚类型错误
									return "Error:Rollback_Type";
								}
								
							}else{
								//有未审核状态，返回Pending
								return "Pending";
							}
						}else{
							//Method为Anryone Pass and Anyone Reject或All Must Pass
							//判断拒绝回滚类型rollback_type_cd的值，回滚
							if(resApprovalStep.getRollback_type_cd().equals("Revert")){
								//查询当前执行步骤是否为第一步
								Object[] params = new Object[]{resApprovalStep.getId()};
								boolean resFirstStep = authDao.queryApprovalStepFirst(params);
								//是第一步直接返回Rejected
								if(resFirstStep == true){
									//更改步骤信息为拒绝Rejected
									process_flg = false;
									status_cd = "Rejected";
									status_val = "已拒绝";
									Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getId()};
									int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
									return "Rejected";
								}else{
									//更改当前步骤flag为false
									process_flg = false;
									Object[] paramsUpdateFlag = new Object[]{process_flg,resApprovalStep.getId()};
									int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
									//更改当前Step Detail记录为初始值，重新开始
									status_cd = "Pending";
									status_val = "待审核";
									Object[] paramsInitDetail = new Object[]{status_cd,status_val,resApprovalStep.getId()};
									authDao.updateApprovalDetailInit(paramsInitDetail);
									//更改上一步步骤flag为true
									process_flg = true;
									Object[] paramsUpdateFlag2 = new Object[]{process_flg,resApprovalStep.getPar_id()};
									int resUpdASVal2 = authDao.updateApprovalStepFlag(paramsUpdateFlag2);
									//更改当前Step Detail记录为初始值，重新开始
									Object[] paramsInitDetail2 = new Object[]{status_cd,status_val,resApprovalStep.getPar_id()};
									authDao.updateApprovalDetailInit(paramsInitDetail2);
									//判断通知信息，发送通知消息
									try {
										//邮件提醒
										if(resApproval.isEmail()==true){
											//查询上一步的步骤信息
											ApprovalStep step = new ApprovalStep();
											step.setId(resApprovalStep.getPar_id());
											ApprovalStep resStep = authDao.queryApprovalStep(step);
											//查询该步骤下详细信息
											ApprovalDetail approvalDetail = new ApprovalDetail();
											approvalDetail.setStep_id(resStep.getId());
											List<ApprovalDetail> resApprovalDetail = authDao.queryApprovalDetailCond(approvalDetail);
											for(int i=0;i<resApprovalDetail.size();i++){
												User user= userDao.selectUserDetail(resApprovalDetail.get(i).getTo_approve());
												emailService.createSimpleMail(user.getEmail(),"以下单据需要您的审核",emailService.buildApprovalEmailContent(objectId,objectName,user.getUserName()).toString());
											}
										}
										//站内提醒
										if(resApproval.isInbox()==true){
											
										}
									} catch (Exception e) {
										// TODO Auto-generated catch block
										logger.info(e.getMessage());
										throw e;
									}
									return "Pending";
								}
								
							}else if(resApprovalStep.getRollback_type_cd().equals("Restart")){
								//查询当前执行步骤是否为第一步
								Object[] params = new Object[]{resApprovalStep.getId()};
								boolean resFirstStep = authDao.queryApprovalStepFirst(params);
								//是第一步直接返回Rejected
								if(resFirstStep == true){
									//更改步骤信息为拒绝Rejected
									process_flg = false;
									status_cd = "Rejected";
									status_val = "已拒绝";
									Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getId()};
									int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
									return "Rejected";
								}else{
									//更改除第一步以外 step记录flag为false
									List<ApprovalStep> resAllStep = authDao.queryApprovalSteps(approvalStep);
									for(int i=0;i<resAllStep.size();i++){
										if(i==0){
											//更改第一步骤process_flg状态为true
											process_flg = true;
											Object[] paramsUpdateFlag = new Object[]{process_flg,resAllStep.get(i).getId()};
											int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
											//判断通知信息，发送通知消息
											try {
												//邮件提醒
												if(resApproval.isEmail()==true){
													//给第一步的审核人发送通知
													//查询该步骤下详细信息
													ApprovalDetail approvalDetail = new ApprovalDetail();
													approvalDetail.setStep_id(resAllStep.get(i).getId());
													List<ApprovalDetail> resApprovalDetail = authDao.queryApprovalDetailCond(approvalDetail);
													for(int j=0;j<resApprovalDetail.size();j++){
														User user= userDao.selectUserDetail(resApprovalDetail.get(j).getTo_approve());
														emailService.createSimpleMail(user.getEmail(),"以下单据需要您的审核",emailService.buildApprovalEmailContent(objectId,objectName,user.getUserName()).toString());
													}
												}
												//站内提醒
												if(resApproval.isInbox()==true){
													
												}
											} catch (Exception e) {
												// TODO Auto-generated catch block
												logger.info(e.getMessage());
												throw e;
											}
										}else{
											//更改当前步骤process_flg状态为false
											process_flg = false;
											Object[] paramsUpdateFlag = new Object[]{process_flg,resAllStep.get(i).getId()};
											int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
										}
										//更改所有Step Detail记录为初始值，重新开始
										status_cd = "Pending";
										status_val = "待审核";
										Object[] paramsInitDetail = new Object[]{status_cd,status_val,resAllStep.get(i).getId()};
										authDao.updateApprovalDetailInit(paramsInitDetail);
									}
									return "Pending";
								}
								
							}else if(resApprovalStep.getRollback_type_cd().equals("Cancel")){
								//取消整个审核流程
								//更改步骤信息为拒绝Rejected
								process_flg = false;
								status_cd = "Rejected";
								status_val = "已拒绝";
								Object[] paramsUpdateFlag = new Object[]{process_flg,status_cd,status_val,resApprovalStep.getId()};
								int resUpdASVal = authDao.updateApprovalStepFlag(paramsUpdateFlag);
								return "Rejected";
								
							}else{
								//回滚类型错误
								return "Error:Rollback_Type";
							}
						}
					}else{
						//resValidFlag == false,不存在有效的步骤
						return "ApprovalStep Not Valid";
					}
						
				}else{
					//执行类型错误
					return "Error:AccountType";
				}
			}else{
				//不存在有效的审核规则，无需审核返回
				return "Approval Not Required";
			}
			
		}else{
			//传入参数值为空
			return "Params Value is Null";
		}
		
	}
	
	/**
	 * 清空ObjectId下的审核明细（审核流程执行接口）
	 */
	public void clearObjectIdApproval(String objectId, String objectName) throws Exception{
		try{
			//参数 --查询approval detail表参数
			ApprovalStep paramsAs = new ApprovalStep();
			//取出Object id 类型
			if(objectName.equals("Account")){
				paramsAs.setAccount_id(objectId);
			}else if(objectName.equals("Product")){
				paramsAs.setProduct_id(objectId);
			}else{
			String[] objectNameType = objectName.split(" ");
			for(int i=0;i<objectNameType.length;i++){
				if(i == (objectNameType.length-1)){
					if(objectNameType[i].equals("Order")){
						paramsAs.setOrder_id(objectId);
					}
					if(objectNameType[i].equals("Requisition")){
						paramsAs.setRequisition_id(objectId);
					}
					if(objectNameType[i].equals("Contract")){
						paramsAs.setContract_id(objectId);
					}
				}
			}
			}
			//query approval detail data is null?
			boolean validStepValue = authDao.queryApprovalStepValidData(paramsAs);
			if(validStepValue == true){
				//根据ObjectId查询approval step表id
				List<ApprovalStep> as = authDao.queryApprovalStepIdDel(paramsAs);
				for(int i=as.size();i>0;i--){
					//根据查询到的stepid删除步数详细信息
					ApprovalDetail approvalDetail = new ApprovalDetail();
					approvalDetail.setStep_id(as.get((i-1)).getId());
					//查询detail是否有有效数据
					boolean validDetailValue = authDao.queryApprovalDetailValidData(approvalDetail);
					if(validDetailValue == true){
						authDao.delApprovalDetailStepId(approvalDetail);
					}else{
					}
					//删除审核步骤表
					ApprovalStep approvalStep = new ApprovalStep();
					approvalStep.setId(as.get((i-1)).getId());
					authDao.delApprovalStepId(approvalStep);
				}
			}else{

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 查询审核流程中审核人当前的审核状态
	 */
	@Override
	public ApprovalDetail queryApprovalDetailStatus(String objectId,
			String objectName, String userId) throws Exception {
		// TODO Auto-generated method stub
		//取出Object id 类型
		String objectIdType = "";
		if(objectName.equals("Account")||objectName.equals("Product")){
			objectIdType = objectName;
		}else{
			String[] Osplit = objectName.split(" ");
			for(int i=0;i<Osplit.length;i++){
				if(i == (Osplit.length-1)){
					if(Osplit[i].equals("Order")){
						objectIdType = Osplit[i];
					}
					if(Osplit[i].equals("Requisition")){
						objectIdType = Osplit[i];
					}
					if(Osplit[i].equals("Contract")){
						objectIdType = Osplit[i];
					}
				}
			}
		}
		ApprovalDetail returnApprovalDetail = new ApprovalDetail();
		//判断传入参数是否有值
		if(objectId!=null&&objectName!=null&&userId!=null&&!objectId.equals("")&&!objectName.equals("")&&!userId.equals("")){
			//根据objectId查询步骤信息
			ApprovalStep approvalStep = new ApprovalStep();
			if(objectIdType.equals("Order")){
				approvalStep.setOrder_id(objectId);
			}else if(objectIdType.equals("Requisition")){
				approvalStep.setRequisition_id(objectId);
			}else if(objectIdType.equals("Contract")){
				approvalStep.setContract_id(objectId);
			}else if(objectIdType.equals("Account")){
				approvalStep.setAccount_id(objectId);
			}else if(objectIdType.equals("Product")){
				approvalStep.setProduct_id(objectId);
			}
			//查询该订单审核的所有步骤
			List<ApprovalStep> resApprovalSteps = authDao.queryApprovalSteps(approvalStep);
			for(int i=0;i<resApprovalSteps.size();i++){
				ApprovalDetail approvalDetail = new ApprovalDetail();
				approvalDetail.setStep_id(resApprovalSteps.get(i).getId());
				List<ApprovalDetail> resApprovalDetails = authDao.queryApprovalDetailCond(approvalDetail);
				for(int j=0;j<resApprovalDetails.size();j++){
					if(resApprovalDetails.get(j).getTo_approve().equals(userId)){
						resApprovalDetails.get(j).setProcess_flg(resApprovalSteps.get(i).isProcess_flg());
						returnApprovalDetail = resApprovalDetails.get(j);
					}
				}
			}
			return returnApprovalDetail;
		}else{
			return returnApprovalDetail;
		}
	}

	/**
	 * 查询审核详细信息接口
	 */
	@Override
	public List<ApprovalStep> queryApproval(String id,String type)
			throws Exception {
		// TODO Auto-generated method stub
		ApprovalStep params_as = new ApprovalStep();
		//判断id类型
		if(type.equals("Order")){
			params_as.setOrder_id(id);
		}else if(type.equals("Requisition")){
			params_as.setRequisition_id(id);
		}else if(type.equals("Contract")){
			params_as.setContract_id(id);
		}else if(type.equals("Account")){
			params_as.setAccount_id(id);
		}else if(type.equals("Product")){
			params_as.setProduct_id(id);
		}
		//查询是否有有效数据
		boolean res = authDao.queryApprovalStepValidData(params_as);
		if(res == true){
			//查询所有的步骤
			List<ApprovalStep> resSteps = authDao.queryApprovalSteps(params_as);
			//根据查询到的step id查询所有detail
			for(int i=0;i<resSteps.size();i++){
				ApprovalDetail params_ad = new ApprovalDetail();
				params_ad.setStep_id(resSteps.get(i).getId());
				List<ApprovalDetail> resDetails = authDao.queryApprovalDetailCond(params_ad);
				for(int k=0;k<resDetails.size();k++){
					//查询用户的详细信息
					User user = userDao.selectUserDetail(resDetails.get(k).getTo_approve());
					resDetails.get(k).setUser(user);
				}
				//查询到的步骤详情放入步骤表中
				resSteps.get(i).setStepDetails(resDetails);
			}
			return resSteps;
		}else{
			return null;
		}
	}
}
