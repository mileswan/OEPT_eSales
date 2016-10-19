package com.oept.esales.utils;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;

import com.oept.esales.dao.SystemPrefDao;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/18
 * Description:Email utility.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
public class EmailManager {
	@Autowired
	private SystemPrefDao systemPrefDao; //系统参数DAO
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
	public static StringBuffer buildApprovalEmailContent(String objectId, String objectName,String userName)
			throws Exception {
		StringBuffer content = new StringBuffer();
		String internalUrl = "";
		String externalUrl = "";
		content.append(userName+" 您好：\n\n");
		switch(objectName){
		case "Sales Order":
			objectName = "销售订单";
			internalUrl = "http://192.168.1.162/:8080/OEPT_eSales/order/so_approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86/:8080/OEPT_eSales/order/so_approval_details.do?id="+objectId;
			break;
		case "Purchase Order":
			objectName = "采购订单";
			internalUrl = "http://192.168.1.162/:8080/OEPT_eSales/order/po_approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86/:8080/OEPT_eSales/order/po_approval_details.do?id="+objectId;
			break;
		case "Stock In Requisition":
			objectName = "入库单";
			internalUrl = "http://192.168.1.162/:8080/OEPT_eSales/inventory/stock_in_approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86/:8080/OEPT_eSales/inventory/stock_in_approval_details.do?id="+objectId;
			break;
		case "Stock Out Requisition":
			objectName = "出库单";
			internalUrl = "http://192.168.1.162/:8080/OEPT_eSales/inventory/stock_out_approval_details.do?id="+objectId;
			externalUrl = "http://112.64.124.86/:8080/OEPT_eSales/inventory/stock_out_approval_details.do?id="+objectId;
			break;
		}
		content.append("以下"+objectName+"需要您的审核，请点击以下链接进入OEPT PSS系统进行审核操作：\n");
		content.append("内网地址："+internalUrl+"\n");
		content.append("外网地址："+externalUrl+"\n");
		content.append("此封邮件由系统发出，请勿回复！");
		return content;
	}
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
            throws Exception {
    	
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
    /**
    * @Method: createSimpleMail
    * @Description: 创建一封只包含文本的邮件
    * @Author:mwan
    *
    * @param session
    * @return
    * @throws Exception
    */ 
    public static MimeMessage createSimpleMailOld(Session session)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("oept_eims@oept.com.cn"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("jingchuan.wan@oept.com.cn"));
        //邮件的标题
        message.setSubject("只包含文本的简单邮件");
        //邮件的文本内容
        message.setContent("你好啊！", "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
    /**
    * @Method: createImageMail
    * @Description: 生成一封邮件正文带图片的邮件
    * @Author:mwan
    *
    * @param session
    * @return
    * @throws Exception
    */ 
    public static MimeMessage createImageMail(Session session) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("gacl@sohu.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("xdp_gacl@sina.cn"));
        //邮件标题
        message.setSubject("带图片的邮件");

        // 准备邮件数据
        // 准备邮件正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是一封邮件正文带图片<img src='cid:xxx.jpg'>的邮件", "text/html;charset=UTF-8");
        // 准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("src\\1.jpg"));
        image.setDataHandler(dh);
        image.setContentID("xxx.jpg");
        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("related");

        message.setContent(mm);
        message.saveChanges();
        //将创建好的邮件写入到E盘以文件的形式进行保存
        message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
        //返回创建好的邮件
        return message;
    }
    /**
     * @Method: createAttachMail
     * @Description: 创建一封带附件的邮件
     * @Author:mwan
     *
     * @param session
     * @return
     * @throws Exception
     */ 
     public static MimeMessage createAttachMail(Session session) throws Exception{
         MimeMessage message = new MimeMessage(session);
         
         //设置邮件的基本信息
         //发件人
         message.setFrom(new InternetAddress("gacl@sohu.com"));
         //收件人
         message.setRecipient(Message.RecipientType.TO, new InternetAddress("xdp_gacl@sina.cn"));
         //邮件标题
         message.setSubject("JavaMail邮件发送测试");
         
         //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
         MimeBodyPart text = new MimeBodyPart();
         text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");
         
         //创建邮件附件
         MimeBodyPart attach = new MimeBodyPart();
         DataHandler dh = new DataHandler(new FileDataSource("src\\2.jpg"));
         attach.setDataHandler(dh);
         attach.setFileName(dh.getName());  //
         
         //创建容器描述数据关系
         MimeMultipart mp = new MimeMultipart();
         mp.addBodyPart(text);
         mp.addBodyPart(attach);
         mp.setSubType("mixed");
         
         message.setContent(mp);
         message.saveChanges();
         //将创建的Email写入到E盘存储
         message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
         //返回生成的邮件
         return message;
     }
     /**
      * @Method: createMixedMail
      * @Description: 生成一封带附件和带图片的邮件
      * @Author:mwan
      *
      * @param session
      * @return
      * @throws Exception
      */ 
      public static MimeMessage createMixedMail(Session session) throws Exception {
          //创建邮件
          MimeMessage message = new MimeMessage(session);
          
          //设置邮件的基本信息
          message.setFrom(new InternetAddress("gacl@sohu.com"));
          message.setRecipient(Message.RecipientType.TO, new InternetAddress("xdp_gacl@sina.cn"));
          message.setSubject("带附件和带图片的的邮件");
          
          //正文
          MimeBodyPart text = new MimeBodyPart();
          text.setContent("xxx这是女的xxxx<br/><img src='cid:aaa.jpg'>","text/html;charset=UTF-8");
          
          //图片
          MimeBodyPart image = new MimeBodyPart();
          image.setDataHandler(new DataHandler(new FileDataSource("src\\3.jpg")));
          image.setContentID("aaa.jpg");
          
          //附件1
          MimeBodyPart attach = new MimeBodyPart();
          DataHandler dh = new DataHandler(new FileDataSource("src\\4.zip"));
          attach.setDataHandler(dh);
          attach.setFileName(dh.getName());
          
          //附件2
          MimeBodyPart attach2 = new MimeBodyPart();
          DataHandler dh2 = new DataHandler(new FileDataSource("src\\波子.zip"));
          attach2.setDataHandler(dh2);
          attach2.setFileName(MimeUtility.encodeText(dh2.getName()));
          
          //描述关系:正文和图片
          MimeMultipart mp1 = new MimeMultipart();
          mp1.addBodyPart(text);
          mp1.addBodyPart(image);
          mp1.setSubType("related");
          
          //描述关系:正文和附件
          MimeMultipart mp2 = new MimeMultipart();
          mp2.addBodyPart(attach);
          mp2.addBodyPart(attach2);
          
          //代表正文的bodypart
          MimeBodyPart content = new MimeBodyPart();
          content.setContent(mp1);
          mp2.addBodyPart(content);
          mp2.setSubType("mixed");
          
          message.setContent(mp2);
          message.saveChanges();
          
          message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
          //返回创建好的的邮件
          return message;
      }

}
