package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.SystemPrefDao;
import com.oept.esales.model.SystemPreference;
@Repository("systemPrefDao")
public class SystemPrefDaoImpl implements SystemPrefDao {

	private static final Logger logger = Logger.getLogger(SystemPrefDaoImpl.class);
	
	private final static String _UPDATE_STRING="UPDATE osa_system_pref SET osa_pref_val = ?,"
			+ "osa_updated=?,osa_updated_by=? "
			+ "WHERE osa_pref_code = ?";
	private final static String _SELECT_STRING="select a.*, u1.osa_username updated_by_username "
			+ "from osa_system_pref a left join "
			+ "osa_user u1 on a.osa_updated_by=u1.osa_user_id "
			+ "where a.osa_pref_code = ?";
	private final static String _SELECT_STRING_ALL="select a.*, u1.osa_username updated_by_username "
			+ "from osa_system_pref a left join "
			+ "osa_user u1 on a.osa_updated_by=u1.osa_user_id";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean updatePrefByCode(SystemPreference systemPref) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					systemPref.getSystem_preference_value(),
					systemPref.getUpdated_date(),
					systemPref.getUpdated_by_user_id(),
					systemPref.getSystem_preference_code()};
			jdbcTemplate.update(_UPDATE_STRING,params);
			return true;
			}catch(Exception e){
				logger.info(e.getMessage());
				throw e;
			}
	}

	@Override
	public SystemPreference getPrefByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {code};
        
        return (SystemPreference) jdbcTemplate.queryForObject(_SELECT_STRING, params, new RowMapper<Object>(){  
            @Override  
            public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	SystemPreference systemPref  = new SystemPreference();  
            	systemPref.setId(rs.getString("osa_sys_pref_id"));
            	systemPref.setSystem_preference_name(rs.getString("osa_pref_name"));
            	systemPref.setSystem_preference_value(rs.getString("osa_pref_val"));
            	systemPref.setUpdated_date(rs.getTimestamp("osa_updated"));
            	systemPref.setUpdated_by_user_id(rs.getString("osa_updated_by"));
            	systemPref.setUpdated_by_user_name(rs.getString("updated_by_username"));
                return systemPref;  
            } 
        });
	}

	@Override
	public List<SystemPreference> getAllPreferences() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(_SELECT_STRING_ALL, 
                new RowMapper<SystemPreference>(){         
                    @Override  
                    public SystemPreference mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	SystemPreference systemPref  = new SystemPreference();  
                    	systemPref.setId(rs.getString("osa_sys_pref_id"));
                    	systemPref.setSystem_preference_code(rs.getString("osa_pref_code"));
                    	systemPref.setSystem_preference_name(rs.getString("osa_pref_name"));
                    	systemPref.setSystem_preference_value(rs.getString("osa_pref_val"));
                    	systemPref.setUpdated_date(rs.getTimestamp("osa_updated"));
                    	systemPref.setUpdated_by_user_id(rs.getString("osa_updated_by"));
                    	systemPref.setUpdated_by_user_name(rs.getString("updated_by_username"));
                        return systemPref; 
                    }  
        });
	}

	private final static String _QUERY_CURRENT_DB = "select database()";
	@Override
	public String getDataSourceName() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(_QUERY_CURRENT_DB, 
                new RowMapper<String>(){         
                    @Override  
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {  
                    	String database_name = rs.getString("database()");
                        return database_name; 
                    }  
        });
	}

}
