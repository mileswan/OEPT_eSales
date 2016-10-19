package com.oept.esales.action;

import java.net.URLDecoder;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oept.esales.model.Account;
import com.oept.esales.model.Address;
import com.oept.esales.model.ApprovalDetail;
import com.oept.esales.model.ApprovalHistory;
import com.oept.esales.model.ApprovalStep;
import com.oept.esales.model.Category;
import com.oept.esales.model.Product;
import com.oept.esales.model.User;
import com.oept.esales.service.AccountService;
import com.oept.esales.service.AuthService;
import com.oept.esales.service.UserService;
/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Sales Account Info Manage
 * Copyright (c) 2015 OEPT inc. All rights reserved.
 */
@Controller
@RequestMapping(value="/account")
public class AccountAct {
	private static final Log logger = LogFactory.getLog(AccountAct.class);
	
	@Qualifier("accountService")
	@Autowired
	private AccountService accountService;
	@Qualifier("userService")
	@Autowired
	private UserService userService;
	@Qualifier("authService")
	@Autowired
	private AuthService authService;
	
	public Map<String,Object> aComm = new HashMap<String,Object>();
	public String catIdComm = "";
	
	/**
	 * 供应商模块
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/SupplierList.do")
	public String supplierInfoList(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String catId = request.getParameter("catId");
		try {
			//取出tree最大层数
			int length = accountService.selectTreeMaxLvl();
			//循环取出每层数据
			for(int i=1;i<length+1;i++){
				Object[] params = new Object[]{i};
				List<Account> aList = accountService.selectTreeLvl(params);
				String aName = "aList"+i;
				model.addAttribute(aName,aList);
			}
			//查询所有父类名称
			model.addAttribute("aCatName",accountService.selectAllParentName());
			String type = "供应商";
			Account account = new Account();
			account.setaCatId(catId);
			account.setAccountType(type);
			//根据catId查询所有单位信息
			model.addAttribute("atDe", accountService.selectAllAtDe(account));
			
			//Page type 
			model.addAttribute("typeFlag",type);
			
			User user = new User();
			user.setActive(true);
			user.setDelete(false);
			List<User> u = userService.getUserLists(user);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return "account/accountInfo";
	}
	
	@RequestMapping(value="/ClientList.do")
	public String accountInfoList(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String catId = request.getParameter("catId");
		try {
			//取出tree最大层数
			int length = accountService.selectTreeMaxLvl();
			//循环取出每层数据
			for(int i=1;i<length+1;i++){
				Object[] params = new Object[]{i};
				List<Account> aList = accountService.selectTreeLvl(params);
				String aName = "aList"+i;
				model.addAttribute(aName,aList);
			}
			//查询所有父类名称
			model.addAttribute("aCatName",accountService.selectAllParentName());
			String type = "客户";
			Account account = new Account();
			account.setaCatId(catId);
			account.setAccountType(type);
			//根据catId查询所有单位信息
			model.addAttribute("atDe", accountService.selectAllAtDe(account));
			
			//Page type 
			model.addAttribute("typeFlag",type);
			
			User user = new User();
			user.setActive(true);
			user.setDelete(false);
			List<User> u = userService.getUserLists(user);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return "account/accountInfo";
	}
	
	/**
	 * 根据单位tree id查询节点信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectAcDe.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectAcDe(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String catId = catIdComm;
		try {
			Object[] params = new Object[]{catId};
			//根据id查询单位节点信息
			Account account = accountService.selectAcDeId(params);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("catParNameId", account.getCatParId());
			map.put("catName", account.getCatName());
			map.put("catId",account.getCatId());
			//创建ObjectMapper对象
			ObjectMapper mapper = new ObjectMapper();  
			//Map转JSON 返回ajax
			return  mapper.writeValueAsString(map); 
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * 添加单位tree节点
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addAccountCat.do")
	@ResponseBody
	public int addAccountCat(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String catId = request.getParameter("catId");
		String catName = request.getParameter("catName");
		String userId = String.valueOf(session.getAttribute("userid"));
		String catLvl;
		try {
			//根据父类id查询父类层级
			Object[] params = new Object[]{catId};
			Account account = accountService.selectAcDeId(params);
			catLvl = String.valueOf(Integer.valueOf(account.getCatLvl())+1);
			Object[] params2 = new Object[]{
					catName,catId,catLvl,userId	
			};
			//添加单位tree节点
			return accountService.addAccountCat(params2); 
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * 更改单位tree节点
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAccountCat.do")
	@ResponseBody
	public int updateAccountCat(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String catId = request.getParameter("catId");
		String catName = request.getParameter("catName");
		String userId = String.valueOf(session.getAttribute("userid"));
		String id = request.getParameter("id");
		String catLvl;
		try {
			//根据父类id查询父类层级
			Object[] params = new Object[]{catId};
			Account account = accountService.selectAcDeId(params);
			catLvl = String.valueOf(Integer.valueOf(account.getCatLvl())+1);
			Object[] params2 = new Object[]{
					catName,catId,catLvl,userId,id
			};
			//更改单位tree节点
			return accountService.updateAccountCat(params2); 
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * 根据单位tree id删除节点信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAcNode.do")
	@ResponseBody
	public int deleteAcNode(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String catId = catIdComm;
		int delRes = 0;
		try {
			Object[] params = new Object[]{catId};
			
				//查询该节点是否为最终节点
				int count = accountService.setAccountCatCnodeCount(params);
				if(count == 0){
					Account accountParams = new Account();
					accountParams.setaCatId(catId);
					//查询出该节点下的所有单位信息
					List<Account> account = accountService.selectAllAtDe(accountParams);
					//删除所有该节点下的单位信息
					
					for(int i=0;i<account.size();i++){
						Object[] params2 = new Object[]{account.get(i).getAccountId()};
						delRes = accountService.deleteAt(params2);
					}
					if(delRes == 1){
						//根据id删除节点
						delRes = accountService.deleteAcNode(params);
					}
					
				}else if(count != 0){
					
					//查询该节点下的所有子节点
					List<Account> ac = accountService.setAccountCatCnode(params);
					//删除所有该节点下的单位信息

					for(int i=0;i<ac.size();i++){
						Object[] params3 = new Object[]{ac.get(i).getCatId()};
						Account accountParams = new Account();
						accountParams.setaCatId(ac.get(i).getCatId());
						//查询出该节点下的所有单位信息
						List<Account> account = accountService.selectAllAtDe(accountParams);
						//删除所有该节点下的单位信息
						for(int j=0;j<account.size();j++){
							Object[] params2 = new Object[]{account.get(i).getAccountId()};
							delRes = accountService.deleteAt(params2);
						}
						if(delRes == 1){
							//根据id删除节点
							delRes = accountService.deleteAcNode(params3);
						}
					}
					if(delRes == 1){
						//根据id删除节点
						delRes = accountService.deleteAcNode(params);
					}
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
		return delRes;
	}
	
	/**
	 * 根据类catId查询所有单位信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail.do")
	@ResponseBody
	public int detail(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String catId = request.getParameter("catId");
		try {
			Account account = new Account();
			account.setaCatId(catId);
			//根据catId查询所有单位信息
			model.addAttribute("atDe", accountService.selectAllAtDe(account));
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 根据aId查询单位信息详情
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editAt.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editAt(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String aId = request.getParameter("aId");
		try {
			Object[] params = new Object[]{aId};
			//根据aId查询单位信息详情
			Account account = accountService.selectAtDe(params);
			//根据地址id查询地址详细信息
			Address pAddr = userService.selectAddressDetail(account.getPrimaryAddrId());
			Address pShipAddr = userService.selectAddressDetail(account.getPrimaryShipaddrId());
			Address pBillAddr = userService.selectAddressDetail(account.getPrimarybilladdrId());
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("accountId", account.getAccountId());
			map.put("accountName", account.getAccountName() );
			map.put("accountNumber", account.getAccountNumber());
			map.put("accountType", account.getAccountType());
			map.put("aTel", account.getWorkphone());
			map.put("pAddr", pAddr.getAllAddress());
			map.put("pAddrId", pAddr.getAddressId());
			map.put("aFax", account.getFax());
			map.put("aEmail", account.getEmail());
			map.put("aDesc", account.getAccountDesc());
			map.put("pShipAddr", pShipAddr.getAllAddress());
			map.put("pShipAddrId", pShipAddr.getAddressId());
			map.put("pBillAddr", pBillAddr.getAllAddress());
			map.put("pBillAddrId", pBillAddr.getAddressId());
			//创建ObjectMapper对象
			ObjectMapper mapper = new ObjectMapper();  
			//Map转JSON 返回ajax
			return  mapper.writeValueAsString(map); 
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 添加单位信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveAdd.do")
	@ResponseBody
	public int saveAdd(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String aName = request.getParameter("aName");
		String aNumber = request.getParameter("aNumber");
		String aType = request.getParameter("aType");
		String aTel = request.getParameter("aTel");
		String aAddress = request.getParameter("aAddress");
		String aFax = request.getParameter("aFax");
		String aEmail = request.getParameter("aEmail");
		String aDesc = request.getParameter("aDesc");
		String aAddrDet = request.getParameter("aAddrDet");
		String aZipcode = request.getParameter("aZipcode");
		String userId = String.valueOf(session.getAttribute("userid"));
		String aOtherAddr1 = request.getParameter("aOtherAddr1");
		String aOtherAddr2 = request.getParameter("aOtherAddr2");
		String status_code = "Not Published";
		String status_val = "未发布";
		String catId = catIdComm;
		
		try {
			Object[] params = new Object[]{
					aName,aNumber,aType,catId,aTel,aAddress,aOtherAddr1,aOtherAddr2,aFax,aEmail,userId,aDesc,aAddrDet,aZipcode,status_code,status_val
			};
			//添加单位
			return accountService.addAt(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 更改单位信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveEdit.do")
	@ResponseBody
	public int saveEdit(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String aName = request.getParameter("aName");
		String aNumber = request.getParameter("aNumber");
		String aType = request.getParameter("aType");
		String aTel = request.getParameter("aTel");
		String aAddrId = request.getParameter("aAddrId");
		String aFax = request.getParameter("aFax");
		String aEmail = request.getParameter("aEmail");
		String aDesc = request.getParameter("aDesc");
		String aAddrDet = request.getParameter("aAddrDet");
		String aZipcode = request.getParameter("aZipcode");
		String aCatId = request.getParameter("aCat");
		String aComm = request.getParameter("aComm");
		String userId = String.valueOf(session.getAttribute("userid"));
		String aId = request.getParameter("aId");
		String aOtherAddr1 = request.getParameter("aOtherAddr1");
		String aOtherAddr2 = request.getParameter("aOtherAddr2");
		try {
			Object[] params = new Object[]{
					aName,aNumber,aType,aCatId,aTel,aAddrId,aAddrDet,aFax,aEmail,aZipcode,userId,aDesc,aComm,aOtherAddr1,aOtherAddr2,aId
			};
			//更改单位
			return accountService.editAt(params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 添加公共信息到model
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loadAccount.do")
	@ResponseBody
	public List<Account> loadAccount(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String catId = request.getParameter("catId");
    	String type= request.getParameter("typeFlag");
		try {
			if(!catId.equals(null)&&catId!=null){
				catIdComm = catId;
			}
			if(catIdComm.equals("1")){
				Account account = new Account();
				account.setAccountType(type);
				//查询出所有单位信息
				return accountService.selectAllAtDe(account);
			}else{
				Account account = new Account();
				account.setaCatId(catIdComm);
				account.setAccountType(type);
				//根据catId查询所有单位信息
				return accountService.selectAllAtDe(account);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 加载地址
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loadAvailAddr.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Address> loadAvailAddresses(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		try{
			return userService.selectAddressLists();
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 根据aId冻结该信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAt.do")
	@ResponseBody
	public int deleteAt(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String aId = request.getParameter("aId");
		try {
			Object[] params = new Object[]{aId};
			//根据aId冻结该信息
			return accountService.deleteAt(params); 
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 获取父类id
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectCname.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectCname(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String catId = catIdComm;
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("catId", catId);
			//创建ObjectMapper对象
			ObjectMapper mapper = new ObjectMapper();  
			//Map转JSON 返回ajax
			return  mapper.writeValueAsString(map); 
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 编辑查看客户/供应商详细信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/accountDetail.do")
	public String accountDetail(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String aId = request.getParameter("accountId");
		try {
			Object[] params = new Object[]{aId};
			//根据aId查询单位信息详情
			Account account = accountService.selectAtDe(params);
			
			
			model.addAttribute("account",account);
			
			boolean readOnlyFlag = true;
			if(account.getAccountStatus()!=null&&!account.getAccountStatus().equals("")){
				switch(account.getAccountStatus()){
				case "Not Published":
					readOnlyFlag = false;
					break;
				case "Rejected":
					readOnlyFlag = false;
					break;
				}
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			return "account/account_edit";
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 审核查看客户/供应商详细信息
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/accountApproveDetail.do")
	public String accountApproveDetail(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		String aId = request.getParameter("accountId");
		try {
			Object[] params = new Object[]{aId};
			//根据aId查询单位信息详情
			Account account = accountService.selectAtDe(params);
			model.addAttribute("account",account);
			
			boolean readOnlyFlag = true;
			if(account.getAccountStatus()!=null&&!account.getAccountStatus().equals("")){
				switch(account.getAccountStatus()){
				case "Not Published":
					readOnlyFlag = false;
					break;
				case "Rejected":
					readOnlyFlag = false;
					break;
				case "Pending":
					ApprovalDetail userApprovalDetail = authService.queryApprovalDetailStatus(aId, "Account", session.getAttribute("userid").toString());
					if(userApprovalDetail.getStatus_cd().equals("Pending") && userApprovalDetail.isProcess_flg()){
						readOnlyFlag = false;
					}else{
						readOnlyFlag = true;
					}
					break;
				case "Published":
					readOnlyFlag = true;
					break;
				}
			}else{
				readOnlyFlag = false;
			}
			model.addAttribute("readOnlyFlag",readOnlyFlag);
			return "account/account_edit_approval";
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/loadcat.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String loadCategory(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Account> acatList = accountService.selectAllParentName();
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(acatList);	
			
			return responseStr;
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/updateAccountStatus.do",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateAccountStatus(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			Account account = new Account();
			account.setAccountId(request.getParameter("accountId"));
			account.setAccountStatus(request.getParameter("status_code"));
			account.setUpdateBy(session.getAttribute("userid").toString());
			account.setUpdateByName(session.getAttribute("username").toString());
			
			return accountService.updateAccountStatusByIdFacade(account);
			
		}catch(Exception e){
			logger.info(e.getMessage());
			return "{'errmsg':'"+e.getMessage()+"'}";
		}
	}
	
	@RequestMapping(value="/queryApprovalStatus.do",method = RequestMethod.POST)
	@ResponseBody
	public List<ApprovalStep> queryApprovalStatus(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			String accountId = request.getParameter("accountId");
			String type = request.getParameter("type");
			return authService.queryApproval(accountId, type);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/approval_history_listdata.do",method = RequestMethod.POST)
	@ResponseBody
	public String approvalHistoryListData(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Object> data = new ArrayList<Object>();
			Map<String, Object> response = new HashMap<String, Object>();
			int length = Integer.parseInt(request.getParameter("length"));
			int start = Integer.parseInt(request.getParameter("start"));
			int sEcho = Integer.parseInt(request.getParameter("draw"));
			int sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			String sortDir = request.getParameter("order[0][dir]");
			String action = request.getParameter("action");
			String accountId = request.getParameter("id");
			ApprovalHistory approvalHistorySearch = new ApprovalHistory();
			
			approvalHistorySearch.setAccount_id(accountId);
			int iTotalRecords = accountService.getApprovalHistories(approvalHistorySearch, null, null, null, null).size();
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<ApprovalHistory> approvalHistoryList = new ArrayList<ApprovalHistory>();
			
			if(action!=null && action.equals("filter")){
				approvalHistorySearch.setCreated_date_from(request.getParameter("order_history_date_from"));
				approvalHistorySearch.setCreated_date_to(request.getParameter("order_history_date_to"));
				approvalHistorySearch.setDescription(request.getParameter("order_history_desc"));
				approvalHistorySearch.setCreated_by_user_name(request.getParameter("order_created_by_username"));
				
				approvalHistoryList = accountService.getApprovalHistories(approvalHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", accountService.getApprovalHistories(approvalHistorySearch, null, 
						null, null, null).size());
			}else{
				approvalHistoryList = accountService.getApprovalHistories(approvalHistorySearch, String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < approvalHistoryList.size(); i++)
	        {  
				ApprovalHistory approvalHistory = approvalHistoryList.get(i); 
				List<String> dataRow = new ArrayList<String>();
				dataRow.add(approvalHistory.getCreated_date());
				dataRow.add(approvalHistory.getDescription());
				dataRow.add(approvalHistory.getCreated_by_user_name());
				dataRow.add(" ");
				data.add(dataRow);
	        }
			
			response.put("data", data);
			response.put("draw", sEcho+1);
			response.put("recordsTotal ", iTotalRecords);
			//response.put("recordsFiltered", iTotalRecords);
			response.put("iTotalRecords", iTotalRecords);
			
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(response);		
			
			return responseStr;
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/approval_list.do",method = RequestMethod.GET)
	public String productApprovalList(Model model, HttpServletRequest request, HttpSession session) throws Exception{

		try{
			return "account/account_list_approval";
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/listdata_approval.do",method = RequestMethod.POST)
	@ResponseBody
	public String productApprovalListData(HttpServletRequest request, HttpSession session) throws Exception{

		try{
			List<Object> data = new ArrayList<Object>();
			Map<String, Object> response = new HashMap<String, Object>();
			int length = Integer.parseInt(request.getParameter("length"));
			int start = Integer.parseInt(request.getParameter("start"));
			int sEcho = Integer.parseInt(request.getParameter("draw"));
			int sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			String sortDir = request.getParameter("order[0][dir]");
			String approver_id = session.getAttribute("userid").toString();
//			String[] ids = request.getParameterValues("id[]");
//			String customActionType = request.getParameter("customActionType");
//			String customActionName = request.getParameter("customActionName");
			String action = request.getParameter("action");
			Product productSearch = new Product();
			Account accountSearch = new Account();
			
			int iTotalRecords = accountService.getAccountsForApprover(accountSearch, approver_id,null, 
					null, null, null).size();
			length = length < 0 ? iTotalRecords : length; 
			int end = start + length;
			end = end > iTotalRecords ? iTotalRecords : end;
			int limit = end - start;
			List<Product> productList = new ArrayList<Product>();
			List<Account> accountList = new ArrayList<Account>();
			
			if(action!=null && action.equals("filter")){
				productSearch.setNumber(request.getParameter("product_number"));
				productSearch.setName(request.getParameter("product_name"));
				productSearch.setCategoryId(request.getParameter("product_category"));
//				productSearch.setProduct_price_from(request.getParameter("product_price_from"));
//				productSearch.setProduct_price_to(request.getParameter("product_price_to"));
				productSearch.setProduct_quantity_from(request.getParameter("product_quantity_from"));
				productSearch.setProduct_quantity_to(request.getParameter("product_quantity_to"));
				productSearch.setProduct_created_from(request.getParameter("product_created_from"));
				productSearch.setProduct_created_to(request.getParameter("product_created_to"));
				productSearch.setStatus(request.getParameter("product_status"));
				
				accountSearch.setAccountNumber(request.getParameter(""));
				accountSearch.setAccountName(request.getParameter(""));
				
				accountList = accountService.getAccountsForApprover(accountSearch, approver_id,String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", accountService.getAccountsForApprover(accountSearch, approver_id,null, 
						null, null, null).size());
			}else{
				accountList = accountService.getAccountsForApprover(accountSearch, approver_id,String.valueOf(start), 
						String.valueOf(limit), String.valueOf(sortColumn+1), sortDir);
				response.put("iTotalDisplayRecords", iTotalRecords);
			}
			
			for(int i = 0; i < accountList.size(); i++)
	        {  
				Account account = accountList.get(i); 
				List<String> dataRow = new ArrayList<String>();
				dataRow.add("<input type='checkbox' name='id[]' value="+account.getAccountId()+">");
				dataRow.add(account.getAccountNumber());
				dataRow.add(account.getAccountName());
				dataRow.add(account.getAccountType());
				//dataRow.add(String.valueOf(product.getPrice()));
				dataRow.add(account.getCatName());
				dataRow.add(account.getUpdate());
				if(!account.isActive()){
					dataRow.add("<span class=\"label label-sm label-danger\">已删除</span>");
				}else if(account.getAccountStatus().equals("Not Published")){
					dataRow.add("<span class=\"label label-sm label-info\">未发布</span>");
				}else if(account.getAccountStatus().equals("Pending")){
					dataRow.add("<span class=\"label label-sm label-info\">待审核</span>");
				}else if(account.getAccountStatus().equals("Rejected")){
					dataRow.add("<span class=\"label label-sm label-danger\">已拒绝</span>");
				}else{
					dataRow.add("<span class=\"label label-sm label-success\">已发布</span>");
				}
				dataRow.add("<a href=\"accountApproveDetail.do?accountId="+account.getAccountId()+"\" class=\"btn btn-xs default btn-editable\"><i class=\"fa fa-pencil\"></i> 进行审核</a>");
				data.add(dataRow);
	        }
			
			response.put("data", data);
			response.put("draw", sEcho+1);
			response.put("recordsTotal ", iTotalRecords);
			//response.put("recordsFiltered", iTotalRecords);
			response.put("iTotalRecords", iTotalRecords);
			
			ObjectMapper om = new ObjectMapper();
			String responseStr = om.writeValueAsString(response);		
			
			return responseStr;
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}
}
