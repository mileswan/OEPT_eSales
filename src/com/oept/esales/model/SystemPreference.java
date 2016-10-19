package com.oept.esales.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SystemPreference {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String system_preference_code;
	private String system_preference_name;
	private String system_preference_value;
	private String updated_date;
	private String updated_by_user_id;
	private String updated_by_user_name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSystem_preference_code() {
		return system_preference_code;
	}
	public void setSystem_preference_code(String system_preference_code) {
		this.system_preference_code = system_preference_code;
	}
	public String getSystem_preference_name() {
		return system_preference_name;
	}
	public void setSystem_preference_name(String system_preference_name) {
		this.system_preference_name = system_preference_name;
	}
	public String getSystem_preference_value() {
		return system_preference_value;
	}
	public void setSystem_preference_value(String system_preference_value) {
		this.system_preference_value = system_preference_value;
	}
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Timestamp updated_date) {
		this.updated_date = dateFormat.format(updated_date);
	}
	public String getUpdated_by_user_id() {
		return updated_by_user_id;
	}
	public void setUpdated_by_user_id(String updated_by_user_id) {
		this.updated_by_user_id = updated_by_user_id;
	}
	public String getUpdated_by_user_name() {
		return updated_by_user_name;
	}
	public void setUpdated_by_user_name(String updated_by_user_name) {
		this.updated_by_user_name = updated_by_user_name;
	}
	

}
