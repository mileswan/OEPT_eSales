package com.oept.esales.dao;

import java.util.List;

import com.oept.esales.model.ListOfValue;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/1/12
 * Description: List of values DAO interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface ListOfValueDao {

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
	 * @return return true if udpate succeeds
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
