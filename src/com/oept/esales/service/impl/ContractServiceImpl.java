package com.oept.esales.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oept.esales.dao.ContractDao;
import com.oept.esales.dao.FileDao;
import com.oept.esales.dao.ListOfValueDao;
import com.oept.esales.model.Attachment;
import com.oept.esales.model.Contract;
import com.oept.esales.service.ContractService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/11
 * Description: Contracts business service implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Service("contractService")
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractDao contractDao; //合同DAO
	@Autowired
	private FileDao fileDao; //合同附件DAO
	@Autowired
	private ListOfValueDao listOfValueDao; //值列表DAO
	
	@Override
	public boolean addContract(Contract contract) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		contract.setCreated_date(nowdate);
		contract.setUpdated_date(nowdate);
		contract.setStatus_code("new");
		contract.setStatus_value(listOfValueDao.getValueByCodeName("Contract Status", "new"));
		return contractDao.addContract(contract);
	}

	@Override
	public List<Contract> getAllContractList() throws Exception {
		// TODO Auto-generated method stub
		return contractDao.getAllContractList();
	}

	@Override
	public Contract getContractById(String id) throws Exception {
		// TODO Auto-generated method stub
		return contractDao.getContractById(id);
	}

	@Override
	public boolean updateContract(Contract contract) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		contract.setUpdated_date(nowdate);
		return contractDao.updateContract(contract);
	}

	@Override
	@Transactional
	public boolean delContractById(String id) throws Exception {
		// TODO Auto-generated method stub
		//删除合同附件
		List<Attachment> attach_list = fileDao.getFileByContractId(id);
		for(int i=0;i<attach_list.size();i++){
			fileDao.delFileById(attach_list.get(i).getId());
			String fileName = attach_list.get(i).getOriginal_filename();
			String realPath = attach_list.get(i).getPath();
			File image = new File(realPath+fileName);
			image.delete();
		}
		return contractDao.delContractById(id);
	}

	@Override
	public List<Contract> getContracts(Contract contract, String start,
			String limit, String sortColumn, String sortDir) throws Exception {
		// TODO Auto-generated method stub
		return contractDao.getContracts(contract, start, limit, sortColumn, sortDir);
	}

	@Override
	public boolean addAttachment(Attachment file) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		file.setUploaded_date(nowdate);
		file.setUpload_source("Contract Attachment Uploader");
		file.setUpdate_date(nowdate);
		file.setType("Contract Attachment");
		return fileDao.addFile(file);
	}

	@Override
	public List<Attachment> getAttachmentsByContractId(String id)
			throws Exception {
		// TODO Auto-generated method stub
		return fileDao.getFileByContractId(id);
	}

	@Override
	@Transactional
	public boolean delAttachmentById(String id) throws Exception {
		// TODO Auto-generated method stub
		Attachment attachment = fileDao.getFileById(id);
		String realPath = attachment.getPath();
		String fileName = attachment.getOriginal_filename();
		File image = new File(realPath+"\\"+fileName);
		image.delete();
		
		return fileDao.delFileById(id);
	}

}
