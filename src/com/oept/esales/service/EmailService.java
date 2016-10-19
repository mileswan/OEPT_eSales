package com.oept.esales.service;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/2/14
 * Description: Email manager service interface.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public interface EmailService {

	/**
	 * @Method: buildApprovalEmailContent
	 * @Description: 生成待审核邮件内容
	 * @Author:mwan
	 *
	 * @param objectId
	 * @param objectName
	 * @param userId
	 * 
	 * @return email content
	 * @throws Exception
	 */ 
	public StringBuffer buildApprovalEmailContent(String objectId, String objectName,String userName)
			throws Exception;
	
	 /**
	  * @Method: createSimpleMail
	  * @Description: 创建一封只包含文本的邮件
	  * @Author:mwan
	  *
	  * @param toAddress
	  * @param subject
	  * @param content
	  * 
	  * @return
	  * @throws Exception
	  */ 
	  public boolean createSimpleMail(String toAddress,String subject,String content)
	            throws Exception;
}
