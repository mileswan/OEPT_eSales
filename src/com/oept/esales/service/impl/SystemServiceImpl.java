package com.oept.esales.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oept.esales.dao.ListOfValueDao;
import com.oept.esales.dao.SystemPrefDao;
import com.oept.esales.model.ListOfValue;
import com.oept.esales.model.SystemPreference;
import com.oept.esales.service.SystemService;
/**
 * @author mwan
 * Version: 2.0
 * Date: 2016/1/7
 * Description: System settings service implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {
	
	@Autowired
	private SystemPrefDao systemPrefDao; //系统参数DAO
	@Autowired
	private ListOfValueDao listOfValueDao; //值列表信息DAO
	
	@Override
	public boolean updatePrefByCode(SystemPreference systemPref)
			throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		systemPref.setUpdated_date(nowdate);
		return systemPrefDao.updatePrefByCode(systemPref);
	}

	@Override
	public SystemPreference getPrefByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		return systemPrefDao.getPrefByCode(code);
	}

	@Override
	public List<SystemPreference> getAllPreferences() throws Exception {
		// TODO Auto-generated method stub
		return systemPrefDao.getAllPreferences();
	}

	@Override
	public boolean addValue(ListOfValue list_of_value) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		list_of_value.setCreated_date(nowdate);
		list_of_value.setUpdated_date(nowdate);
		return listOfValueDao.addValue(list_of_value);
	}

	@Override
	public boolean delValueById(String id) throws Exception {
		// TODO Auto-generated method stub
		return listOfValueDao.delValueById(id);
	}

	@Override
	public List<ListOfValue> getAllListOfValues() throws Exception {
		// TODO Auto-generated method stub
		return listOfValueDao.getAllListOfValues();
	}

	@Override
	public ListOfValue getListOfValueById(String id) throws Exception {
		// TODO Auto-generated method stub
		return listOfValueDao.getListOfValueById(id);
	}

	@Override
	public List<ListOfValue> getListOfValues(ListOfValue list_of_value,
			String start, String limit, String sortColumn, String sortDir)
			throws Exception {
		// TODO Auto-generated method stub
		return listOfValueDao.getListOfValues(list_of_value, start, limit, sortColumn, sortDir);
	}

	@Override
	public boolean updateValue(ListOfValue list_of_value) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		list_of_value.setUpdated_date(nowdate);
		return listOfValueDao.updateValue(list_of_value);
	}

	@Override
	public String getValueByCodeName(String code, String name) throws Exception {
		// TODO Auto-generated method stub
		return listOfValueDao.getValueByCodeName(code, name);
	}

	@Override
	public Map<String, String> getDataSourceInfo() throws Exception {
		// TODO Auto-generated method stub
		String data_source_name = systemPrefDao.getDataSourceName();
		Map<String, String> data_source_map = new HashMap<String,String>();
		data_source_map.put("DB_Name", data_source_name);
		return data_source_map;
	}

}
