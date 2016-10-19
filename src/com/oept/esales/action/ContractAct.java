package com.oept.esales.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oept.esales.model.Account;
import com.oept.esales.model.Attachment;
import com.oept.esales.model.Contract;
import com.oept.esales.model.ListOfValue;
import com.oept.esales.service.AccountService;
import com.oept.esales.service.ContractService;
import com.oept.esales.service.SystemService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/11
 * Description: Contract administration actions.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
@Controller
@RequestMapping(value="/contract")
public class ContractAct {
	
	private static final Log logger = LogFactory.getLog(ContractAct.class);
	@Qualifier("contractService")
	@Autowired
	private ContractService contractService;
	@Qualifier("accountService")
	@Autowired
	private AccountService accountService;
	@Qualifier("systemService")
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value="/my_list.do",method = RequestMethod.GET)
	public String contractMyList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			Contract contract = new Contract();
			contract.setOwner_id(session.getAttribute("userid").toString());
			List<Contract> contractList = contractService.getContracts(contract, null, null, null, null);
			model.addAttribute("contractList", contractList);
			return "contract/my_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/all_list.do",method = RequestMethod.GET)
	public String contractAllList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Contract> contractList = contractService.getAllContractList();
			model.addAttribute("contractList", contractList);
			
			ListOfValue list_of_value = new ListOfValue();
			list_of_value.setList_code("Contract Type");
			List<ListOfValue> contract_type_list = systemService.getListOfValues(list_of_value, null, null, null, null);
			model.addAttribute("contract_type_list", contract_type_list);
			return "contract/contract_all_list";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/all_details.do",method = RequestMethod.GET)
	public String contractAllDetails(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String contract_id = request.getParameter("id");
			Contract contractDetails = contractService.getContractById(contract_id);
			model.addAttribute("contractDetails", contractDetails);
			
			ListOfValue list_of_value_search = new ListOfValue();
			list_of_value_search.setList_code("Contract Type");
			List<ListOfValue> contract_type_list = systemService.getListOfValues(list_of_value_search, null, null, null, null);
			model.addAttribute("contract_type_list", contract_type_list);
			return "contract/contract_all_details";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/create.do",method = RequestMethod.POST)
	@ResponseBody
	public String createContract(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String name = request.getParameter("name");
			String number = request.getParameter("number");
			String supplier_id = request.getParameter("supplier_id");
			String customer_id = request.getParameter("customer_id");
			String description = request.getParameter("desc");
			String contract_date = request.getParameter("contract_date");
			String type_code = request.getParameter("type_code");
			String type_value = request.getParameter("type_value");
			double amount = Double.valueOf(request.getParameter("amount"));
			Contract contract = new Contract();
			contract.setName(name);
			contract.setNumber(number);
			contract.setType_code(type_code);
			contract.setType_value(type_value);
			contract.setSupplier_id(supplier_id);
			contract.setAccount_id(customer_id);
			contract.setDescription(description);
			contract.setAmount(amount);
			contract.setContract_date(Date.valueOf(contract_date));
			contract.setOwner_id(session.getAttribute("userid").toString());
			contract.setCreated_by_user_id(session.getAttribute("userid").toString());
			contract.setUpdated_by_user_id(session.getAttribute("userid").toString());
			
			if(contractService.addContract(contract)){
				return "{'id':'success'}";
			}		
			return "{'noid':'failed'}";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delContractById(HttpServletRequest request, HttpSession session) throws Exception{
		
		String id = request.getParameter("id");
		
		try {				
				
			if(contractService.delContractById(id)){
				return "{'objname':'合同'}";
			}
				return null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateContractById(HttpServletRequest request, HttpSession session) throws Exception{
		
		try{			
			String id = request.getParameter("contract[id]");
			String name = request.getParameter("contract[name]");
			String number = request.getParameter("contract[number]");
			String supplier_id = request.getParameter("contract[supplier_id]");
			String customer_id = request.getParameter("contract[customer_id]");
			String description = request.getParameter("contract[description]");
			String contract_date = request.getParameter("contract[date]");
			double amount = Double.valueOf(request.getParameter("contract[amount]"));
			String type_code = request.getParameter("contract[type]");
			String type_value = systemService.getValueByCodeName("Contract Type", type_code);
			Contract contract = contractService.getContractById(id);
			contract.setName(name);
			contract.setNumber(number);
			contract.setType_code(type_code);
			contract.setType_value(type_value);
			contract.setAmount(amount);
			contract.setSupplier_id(supplier_id);
			contract.setAccount_id(customer_id);
			contract.setDescription(description);
			contract.setContract_date(Date.valueOf(contract_date));
			contract.setUpdated_by_user_id(session.getAttribute("userid").toString());
			
			contractService.updateContract(contract);
			return "{'objname':'合同'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	//file upload action
	@RequestMapping(value="/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpSession session) throws Exception{
		String errMsg = "";
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String contractId = request.getParameter("contractId").toString();

		String fileName = file.getOriginalFilename();
		String fileUniqueName = request.getParameter("name").toString();
		String defaultContextPath = "/files/contracts";
		String defaultRealPath = request.getSession().getServletContext().getRealPath(defaultContextPath);

		File dirFile = new File(defaultRealPath);
		if(!dirFile.exists())
		{
			logger.info("The folder "+defaultRealPath+" does not exist,now trying to create a one...");
			if(dirFile.mkdir()){
				logger.info("Create folder "+defaultRealPath+" successfully!");
			}else{
				logger.info("Create folder "+defaultRealPath+" failed!");
				responseMap.put("result", "failed");
				responseMap.put("id", fileName);
				return responseMap;
			}
		}

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(defaultRealPath+"\\"+fileName)));
				stream.write(bytes);
				stream.close();
				
				Attachment contractFile = new Attachment();
				contractFile.setFilename(fileName);
				contractFile.setUnique_filename(fileUniqueName);
				contractFile.setOriginal_filename(fileName);
				contractFile.setPath(defaultRealPath);
				contractFile.setContextpath(defaultContextPath);
				contractFile.setFilesize(file.getSize());
				contractFile.setUploaded_by_user_id(session.getAttribute("userid").toString());
				contractFile.setUpdate_by_user_id(session.getAttribute("userid").toString());
				contractFile.setImage_label(file.getContentType());
				contractFile.setContract_id(contractId);
				contractService.addAttachment(contractFile);
				
			} catch (Exception e) {
				logger.info(e);
				errMsg = "You failed to upload " + fileName + " => " + e.getMessage();
				logger.info(errMsg);
				responseMap.put("result", "failed");
				responseMap.put("id", fileName);
				return responseMap;
			}
		} else {
			errMsg = "You failed to upload " + fileName + " because the file was empty.";
			logger.info(errMsg); 
			responseMap.put("result", "failed");
			responseMap.put("id", fileName);
			return responseMap;
		}

		responseMap.put("result", "OK");
		responseMap.put("id", fileName);
		return responseMap;
	}
	
	@RequestMapping(value="/loadattach.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String loadAttachments(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String contractId = request.getParameter("contractId").toString();
			List<Attachment> attachmentList = contractService.getAttachmentsByContractId(contractId);
			
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(attachmentList);	
			
			return responseStr;
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadAvailCustomer.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Account> loadAvailCustomer(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			Account customer = new Account();
			customer.setAccountType("客户");
			customer.setActive(true);
			
			return accountService.getAccount(customer);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadAvailSupplier.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Account> loadAvailSupplier(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			Account customer = new Account();
			customer.setAccountType("供应商");
			customer.setActive(true);
			
			return accountService.getAccount(customer);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/delAttach.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delAttachById(HttpServletRequest request, HttpSession session) throws Exception{

		try {
			String id = request.getParameter("image_id");
			contractService.delAttachmentById(id);
			
			return "{'objname':'合同附件'}";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw e;
		}
	}
	
}
