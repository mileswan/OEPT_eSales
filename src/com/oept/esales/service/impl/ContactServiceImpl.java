package com.oept.esales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oept.esales.dao.ContactDao;
import com.oept.esales.model.Contact;
import com.oept.esales.service.ContactService;

/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Account info service implements.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;

	/**
	 * 添加联系人信息
	 */
	@Override
	public int addContact(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return contactDao.addContact(params);
	}

	/**
	 * 查询联系人集合
	 */
	@Override
	public List<Contact> queryContactList() throws Exception {
		// TODO Auto-generated method stub
		return contactDao.queryContactList();
	}

	/**
	 * 查询联系人详细信息
	 */
	@Override
	public Contact queryContactDetails(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return contactDao.queryContactDetails(params);
	}

	/**
	 * 更新联系人信息
	 */
	@Override
	public int updateContact(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return contactDao.updateContact(params);
	}

	/**
	 * 删除联系人
	 */
	@Override
	public int delContact(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		return contactDao.delContact(params);
	}
	
	

}
