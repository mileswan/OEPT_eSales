package com.oept.esales.service;

import java.util.List;
import java.util.Map;

import com.oept.esales.model.ListOfValue;
import com.oept.esales.model.SystemPreference;


/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/7
 * Description: System settings service interface.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public interface SystemService {

	/**
	 * Update preference by code
	 * @param systemPref system preference's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updatePrefByCode(SystemPreference systemPref) throws Exception;
	/**
	 * Get system preference by code
	 * @param code system preference's code
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public SystemPreference getPrefByCode(String code) throws Exception;
	/**
	 * Query all system preferences data
	 * 
	 * @return return list object of all system preferences data
	 * @throws Exception 
	 */
	public List<SystemPreference> getAllPreferences() throws Exception;
	/**
	 * Get current data source info
	 * 
	 * @return return data source values
	 * @throws Exception 
	 */
	public Map<String,String> getDataSourceInfo() throws Exception;
	/**
	 * Add new list_of_value
	 * @param list_of_value list_of_value's object
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addValue(ListOfValue list_of_value) throws Exception;
	/**
	 * Delete list_of_value by id
	 * @param id list_of_value's id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delValueById(String id) throws Exception;
	/**
	 * Update list_of_value
	 * @param list_of_value list_of_value's object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateValue(ListOfValue list_of_value) throws Exception;
	/**
	 * Query list_of_value value by code and name
	 * @param code list_of_value's code
	 * @param name list_of_value's name
	 * 
	 * @return return value of the code and name
	 * @throws Exception 
	 */
	public String getValueByCodeName(String code, String name) throws Exception;
	/**
	 * Query all ListOfValues data
	 * 
	 * @return return list object of all ListOfValues data
	 * @throws Exception 
	 */
	public List<ListOfValue> getAllListOfValues() throws Exception;
	/**
	 * Query ListOfValue by id
	 * @param id ListOfValue's id
	 * 
	 * @return return list object of queried ListOfValue data
	 * @throws Exception 
	 */
	public ListOfValue getListOfValueById(String id) throws Exception;
	/**
	 * Query specified number of ListOfValues
	 * @param order_history ListOfValue's object for query parameters
	 * @param start line number of start
	 * @param limit line number of data
	 * @param sortColumn column to sort
	 * @param sortDir asc or desc
	 * 
	 * @return return list object of queried list_of_values data
	 * @throws Exception 
	 */
	public List<ListOfValue> getListOfValues(ListOfValue list_of_value,String start,String limit,String sortColumn, String sortDir) throws Exception;
}
