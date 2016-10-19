package com.oept.esales.service;

import java.util.List;
import java.util.Map;

import com.oept.esales.model.Account;
import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.Product;


/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Account service interface.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public interface AccountService {

	/**
	 * 根据节点层查询该层下信息集合
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Account> selectTreeLvl(Object[] params)throws Exception;
	
	/**
	 * 查询Account tree最大层数
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int selectTreeMaxLvl()throws Exception;
	
	/**
	 * 查询所有父类名称
	 * @return
	 * @throws Exception
	 */
	public List<Account> selectAllParentName() throws Exception;
	
	/**
	 * 根据id查询单位节点信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Account selectAcDeId(Object[] params) throws Exception;
	
	/**
	 * 添加单位tree节点
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int addAccountCat(Object[] params) throws Exception;
	
	/**
	 * 更改单位tree节点
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateAccountCat(Object[] params) throws Exception;
	
	/**
	 * 根据id删除节点
	 * @param parmas
	 * @return
	 * @throws Exception
	 */
	public int deleteAcNode(Object[] params) throws Exception;
	
	/**
	 * 根据catId查询所有单位信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Account> selectAllAtDe(Account account) throws Exception;
	
	
	/**
	 * 根据aId查询单位信息详情
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Account selectAtDe(Object[] params) throws Exception;
	
	/**
	 * 添加单位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int addAt(Object[] params) throws Exception;
	
	/**
	 * 更改单位
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int editAt(Object[] params) throws Exception;
	
	/**
	 * 根据aId冻结该信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int deleteAt(Object[] params) throws Exception;
	
	/**
	 * 更改单位信息关联字段cId
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int delectAtc(Object[] params) throws Exception;
	
	/**
	 * 查询指定条件的单位信息
	 * @param type
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public List<Account> getAccount(Account account)throws Exception;
	
	/**
	 * 根据catId查询该节点下的子节点
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Account> setAccountCatCnode(Object[] params) throws Exception;
	
	/**
	 * 查询该节点下子节点数目
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int setAccountCatCnodeCount(Object[] params) throws Exception;
	
	public String updateAccountStatusByIdFacade(Account account) throws Exception;
	
	public boolean addApprovalHistory(ApprovalHistory approval_history) throws Exception;
	
	public String updateAccountStatusById(Account account) throws Exception;
	
	public List<ApprovalHistory> getApprovalHistories(
			ApprovalHistory approval_history, String start, String limit,
			String sortColumn, String sortDir) throws Exception;
	
	//读取单位总数
	public int getAccountCount() throws Exception;
	
	public List<Account> getAccountsForApprover(Account account,
			String approver_id, String start, String limit, String sortColumn,
			String sortDir) throws Exception;
}
