package com.oept.esales.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oept.esales.dao.SystemPrefDao;
import com.oept.esales.service.EmailService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/2/14
 * Description: Email manager service implements.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private SystemPrefDao systemPrefDao; //系统参数DAO
	
	@Override
	public StringBuffer buildApprovalEmailContent(String objectId,
			String objectName, String userName) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer content = new StringBuffer();
		String internalUrl = "";
		String externalUrl = "";
		content.append("尊敬的"+userName+" 您好：");
		content.append("<br/>");
		content.append("<br/>");
		switch(objectName){
		case "Sales Order":
			objectName = "销售订单";
			internalUrl = "http://192.168.1.162:8088/OEPT_eSales/order/so_approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86:8088/OEPT_eSales/order/so_approval_details.do?id="+objectId;
			break;
		case "Purchase Order":
			objectName = "采购订单";
			internalUrl = "http://192.168.1.162:8088/OEPT_eSales/order/po_approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86:8088/OEPT_eSales/order/po_approval_details.do?id="+objectId;
			break;
		case "Stock In Requisition":
			objectName = "入库单";
			internalUrl = "http://192.168.1.162:8088/OEPT_eSales/inventory/stock_in_approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86:8088/OEPT_eSales/inventory/stock_in_approval_details.do?id="+objectId;
			break;
		case "Stock Out Requisition":
			objectName = "出库单";
			internalUrl = "http://192.168.1.162:8088/OEPT_eSales/inventory/stock_out_approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86:8088/OEPT_eSales/inventory/stock_out_approval_details.do?id="+objectId;
			break;
		case "Product":
			objectName = "产品/服务";
			internalUrl = "http://192.168.1.162:8088/OEPT_eSales/prodadmin/approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86:8088/OEPT_eSales/prodadmin/approval_details.do?id="+objectId;
			break;
		case "Account":
			objectName = "客户/供应商";
			internalUrl = "http://192.168.1.162:8088/OEPT_eSales/account/approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86:8088/OEPT_eSales/account/approval_details.do?id="+objectId;
			break;
		}
		content.append("以下"+objectName+"需要您的审核，请点击以下链接进入OEPT PSS系统进行审核操作：");
		content.append("<br/>");
		content.append("内网地址："+internalUrl);
		content.append("<br/>");
		content.append("外网地址："+externalUrl);
		content.append("<br/>");
		content.append("<br/>");
		content.append("********************");
		content.append("此封邮件由系统发出，请勿回复！");
		content.append("********************");
		return content;
	}

	@Override
	public boolean createSimpleMail(String toAddress, String subject,
			String content) throws Exception {
		// TODO Auto-generated method stub
	   	if( !(systemPrefDao.getPrefByCode("mail_available").getSystem_preference_value().equals("on")) ){
    		return false;
    	}
    	Properties prop = new Properties();
    	String mail_host = systemPrefDao.getPrefByCode("mail_host").getSystem_preference_value();
    	String mail_transport_protocal = systemPrefDao.getPrefByCode("mail_transport_protocol").getSystem_preference_value();
        prop.setProperty("mail.host", mail_host);
        prop.setProperty("mail.transport.protocol", mail_transport_protocal);
        String mail_smtp_auth = "";
        if(systemPrefDao.getPrefByCode("mail_smtp_auth").getSystem_preference_value().equals("on")){
        	mail_smtp_auth = "true";
        }else{
        	mail_smtp_auth = "false";
        }
        prop.setProperty("mail.smtp.auth", mail_smtp_auth);
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        String mail_username = systemPrefDao.getPrefByCode("mail_username").getSystem_preference_value();
        String mail_password = systemPrefDao.getPrefByCode("mail_password").getSystem_preference_value();
        ts.connect(mail_host, mail_username, mail_password);
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(mail_username));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        //邮件的标题
        message.setSubject(subject);
        //邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");
        //发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
        
        return true;
	}

}
