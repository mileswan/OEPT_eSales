package com.oept.esales.service.impl;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oept.esales.dao.AccountDao;
import com.oept.esales.dao.ApprovalHistoryDao;
import com.oept.esales.dao.ListOfValueDao;
import com.oept.esales.model.Account;
import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.Product;
import com.oept.esales.service.AccountService;
import com.oept.esales.service.AuthService;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Account info service implements.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private ApprovalHistoryDao approvalHistoryDao; //approval history info DAO
	@Autowired
	private ListOfValueDao listOfValueDao;

	/**
	 * 根据节点层查询该层下信息集合
	 */
	@Override
	public List<Account> selectTreeLvl(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.selectTreeLvl(params);
	}

	/**
	 * 查询Account tree最大层数
	 */
	@Override
	public int selectTreeMaxLvl() throws Exception {
		// TODO Auto-generated method stub
		return accountDao.selectTreeMaxLvl();
	}

	/**
	 * 查询所有父类名称
	 */
	@Override
	public List<Account> selectAllParentName() throws Exception {
		// TODO Auto-generated method stub
		return accountDao.selectAllParentName();
	}

	/**
	 * 根据id查询单位节点信息
	 */
	@Override
	public Account selectAcDeId(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.selectAcDeId(params);
	}

	/**
	 * 添加单位tree节点
	 */
	@Override
	public int addAccountCat(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.addAccountCat(params);
	}
	
	/**
	 * 更改单位tree节点
	 */
	@Override
	public int updateAccountCat(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.updateAccountCat(params);
	}

	/**
	 * 根据id删除节点
	 */
	@Override
	public int deleteAcNode(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.deleteAcNode(params);
	}

	/**
	 * 根据catId查询所有单位信息
	 */
	@Override
	public List<Account> selectAllAtDe(Account account) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.selectAllAtDe(account);
	}
	

	/**
	 * 根据aId查询单位信息详情
	 */
	@Override
	public Account selectAtDe(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.selectAtDe(params);
	}

	/**
	 * 添加单位
	 */
	@Override
	public int addAt(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		//添加前查询是否存在
		Account account = new Account();
		account.setAccountName(params[0].toString());
		account.setAccountType(params[2].toString());
		boolean res = accountDao.queryAccountExist(account);
		if(res)
			return 2;
		else
			return accountDao.addAt(params);
	}

	/**
	 * 更改单位
	 */
	@Override
	public int editAt(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.editAt(params);
	}

	/**
	 * 根据aId冻结该信息
	 */
	@Override
	public int deleteAt(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.deleteAt(params);
	}

	/**
	 * 查询指定条件的单位信息
	 */
	@Override
	public List<Account> getAccount(Account account)
			throws Exception {
		// TODO Auto-generated method stub
		return accountDao.getAccount(account);
	}

	/**
	 * 根据catId查询该节点下的子节点
	 */
	@Override
	public List<Account> setAccountCatCnode(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.setAccountCatCnode(params);
	}

	/**
	 * 查询该节点下子节点数目
	 */
	@Override
	public int setAccountCatCnodeCount(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.setAccountCatCnodeCount(params);
	}

	/**
	 *  更改单位信息关联字段cId
	 */
	@Override
	public int delectAtc(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.delectAtc(params);
	}
	
	@Override
	public boolean addApprovalHistory(ApprovalHistory approval_history)
			throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		approval_history.setCreated_date(nowdate);
		return approvalHistoryDao.addHistory(approval_history);
	}
	
	@Override
	public String updateAccountStatusById(Account account) throws Exception {
		// TODO Auto-generated method stub
		if(accountDao.updateAccount(account)){
			return "success";
		}else{
			return "更新出现异常";
		}
	}
	
	@Qualifier("authService")
	@Autowired
	private AuthService authService;
	@Override
	@Transactional
	public String updateAccountStatusByIdFacade(Account account) throws Exception {
		// TODO Auto-generated method stub
		try{
			String status_code = account.getAccountStatus();
			String success_msg = "";
			if(status_code.equals("submitted")){
				success_msg = "提交客户/供应商";
			}else if(status_code.equals("Published")){
				success_msg = "提交审核客户/供应商";
			}else if(status_code.equals("Rejected")){
				success_msg = "提交拒绝客户/供应商";
			}else if(status_code.equals("Reopen")){
				success_msg = "重开客户/供应商";
			}
			
			String ActionType = "";
			ApprovalHistory approvalHistory = new ApprovalHistory();
			switch(status_code){
			case "submitted":
				ActionType = "Submit";
				approvalHistory.setAccount_id(account.getAccountId());
				approvalHistory.setCreated_by_user_id(account.getUpdateBy());
				approvalHistory.setDescription("由"+account.getUpdateByName()+"提交了客户/供应商信息！");
				this.addApprovalHistory(approvalHistory);
				break;
			case "Published":
				ActionType = "Approve";
				approvalHistory.setAccount_id(account.getAccountId());
				approvalHistory.setCreated_by_user_id(account.getUpdateBy());
				approvalHistory.setDescription("由"+account.getUpdateByName()+"审核通过了客户/供应商信息！");
				this.addApprovalHistory(approvalHistory);
				break;
			case "Rejected":
				ActionType = "Reject";
				approvalHistory.setAccount_id(account.getAccountId());
				approvalHistory.setCreated_by_user_id(account.getUpdateBy());
				approvalHistory.setDescription("由"+account.getUpdateByName()+"拒绝了客户/供应商信息！");
				this.addApprovalHistory(approvalHistory);
				break;
			case "Reopen":
				ActionType = "Reopen";
				status_code = "Not Published";
				approvalHistory.setAccount_id(account.getAccountId());
				approvalHistory.setCreated_by_user_id(account.getUpdateBy());
				approvalHistory.setDescription("由"+account.getUpdateByName()+"重开了客户/供应商信息！");
				this.addApprovalHistory(approvalHistory);
				break;
			default:
				ActionType = "Not Defined";
				break;
			}
			if(!ActionType.equals("Reopen")){
				String objectName = "Account";
				switch(authService.ApprovalExecute(account.getAccountId(), objectName, ActionType, account.getUpdateBy())){
				case "Approval Not Required":
					break;
				case "Pending":
					status_code = "Pending";
					break;
				case "Approved":
					status_code = "Published";
					break;
				case "Rejected":
					status_code = "Rejected";
					break;
				}
			}
			account.setAccountStatus(status_code);
			account.setAccountStatusVal(listOfValueDao.getValueByCodeName("Account Status", status_code));
			String update_status = this.updateAccountStatusById(account);
			if(update_status.equals("success")){
				return "{'objname':'"+success_msg+"'}";
			}else{
				//return "{'errmsg':'"+update_status+"'}";
				throw new RuntimeException(update_status);
			}
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public List<ApprovalHistory> getApprovalHistories(
			ApprovalHistory approval_history, String start, String limit,
			String sortColumn, String sortDir) throws Exception {
		// TODO Auto-generated method stub
		return approvalHistoryDao.getHistories(approval_history, start, limit, sortColumn, sortDir);
	}

	/**
	 * 读取单位总数
	 */
	@Override
	public int getAccountCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public List<Account> getAccountsForApprover(Account account,
			String approver_id, String start, String limit, String sortColumn,
			String sortDir) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.getAccountsForApprover(account, approver_id, start, limit, sortColumn, sortDir);
	}
	
}
