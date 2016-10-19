package com.oept.esales.service;

import java.util.List;

import com.oept.esales.model.Attachment;
import com.oept.esales.model.Contract;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/11
 * Description: Contract administration service interface.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public interface ContractService {

	/**
	 * 添加合同信息
	 * @param contract
	 * @return
	 * @throws Exception
	 */
	public boolean addContract(Contract contract) throws Exception;
	
	/**
	 * 查询所有合同集合
	 * @return
	 * @throws Exception
	 */
	public List<Contract> getAllContractList() throws Exception;
	
	/**
	 * 查询合同详细信息
	 * @param id 合同id
	 * @return
	 * @throws Exception
	 */
	public Contract getContractById(String id) throws Exception;
	
	/**
	 * 更新合同信息
	 * @param contract
	 * @return
	 * @throws Exception
	 */
	public boolean updateContract(Contract contract) throws Exception;
	
	/**
	 * 删除合同
	 * @param id 合同id
	 * @return
	 * @throws Exception
	 */
	public boolean delContractById(String id) throws Exception;
	/**
	 * Query specified number of contracts
	 * @param contract Contractn's object for filter parameters
	 * @param start line number of start
	 * @param limit number of data
	 * @param sortColumn column number to sort
	 * @param sortDir sort direction, asc or desc
	 * 
	 * @return return list object of queried contracts data
	 * @throws Exception 
	 */
	public List<Contract> getContracts(Contract contract,String start,String limit,String sortColumn, String sortDir) throws Exception;
	/**
	 * Add new attachment file
	 * @param file attachment's file object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addAttachment(Attachment file) throws Exception;
	/**
	 * Query Attachments data by product id
	 * @param id attachment's id
	 * 
	 * @return return list object of queried Attachments data
	 * @throws Exception 
	 */
	public List<Attachment> getAttachmentsByContractId(String id) throws Exception;
	/**
	 * Delete Attachment by id
	 * @param id Attachment's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	boolean delAttachmentById(String id) throws Exception;
}
