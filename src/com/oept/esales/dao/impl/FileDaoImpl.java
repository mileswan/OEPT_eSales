package com.oept.esales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oept.esales.dao.FileDao;
import com.oept.esales.model.Attachment;
@Repository("fileDao")
public class FileDaoImpl implements FileDao {
	private static final Logger logger = Logger.getLogger(FileDaoImpl.class);
	
	private final static String _INSERT_STRING1="insert into osa_attr_file (osa_filename,osa_original_filename,osa_unique_filename,osa_product_id,"
			+ "osa_order_id,osa_contract_id,osa_path,osa_context_path,osa_filesize,osa_uploaded_by_user_id,osa_upload_date,osa_uploadip,osa_upload_source,"
			+ "osa_type,osa_image_type,osa_image_label,osa_sort_order) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String _DELETE_STRING1="delete from osa_attr_file where osa_file_id=?";
	private final static String _UPDATE_STRING1="UPDATE osa_attr_file SET osa_filename = ?, osa_image_type = ?,"
			+ "osa_update = ?,osa_update_by=? "
			+ "WHERE osa_file_id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void set_jdbc(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean addFile(Attachment file) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					file.getFilename(),
					file.getOriginal_filename(),
					file.getUnique_filename(),
					file.getProduct_id(),
					file.getOrder_id(),
					file.getContract_id(),
					file.getPath(),
					file.getContextpath(),
					file.getFilesize(),
					file.getUploaded_by_user_id(),
					file.getUploaded_date(),
					file.getUploadip(),
					file.getUpload_source(),
					file.getType(),
					file.getImage_type(),
					file.getImage_label(),
					file.getSort_order()
					};
			jdbcTemplate.update(_INSERT_STRING1,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delFileById(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					id };
			jdbcTemplate.update(_DELETE_STRING1,params);
			return true;
		}catch(Exception e){
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean updateFile(Attachment file) throws Exception {
		// TODO Auto-generated method stub
		try{
			Object[] params = new Object[] {
					file.getFilename(),
					file.getImage_type(),
					file.getUpdate_date(),
					file.getUpdate_by_user_id(),
					file.getId()};
			jdbcTemplate.update(_UPDATE_STRING1,params);
			return true;
			}catch(Exception e){
				logger.info(e.getMessage());
				throw e;
			}
	}

	@Override
	public List<Attachment> getAllFiles() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private final static String _SELECT_STRING_FOR_ID="select a.*, prod.osa_prod_num product_number, prod.osa_prod_name product_name,"
			+ "o.osa_order_number order_number, u.osa_username uploaded_by_user "
			+ "from osa_attr_file a left outer join osa_product prod on a.osa_product_id=prod.osa_prod_id left outer join "
			+ "osa_order o on a.osa_order_id=o.osa_order_id left outer join "
			+ "osa_user u on a.osa_uploaded_by_user_id=u.osa_user_id where a.osa_file_id = ?";
	@Override
	public Attachment getFileById(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
        
        return jdbcTemplate.queryForObject(_SELECT_STRING_FOR_ID, params, new RowMapper<Attachment>(){  
            @Override  
            public Attachment mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Attachment file  = new Attachment();
            	file.setId(rs.getString("osa_file_id"));
            	file.setFilename(rs.getString("osa_filename"));
            	file.setUnique_filename(rs.getString("osa_unique_filename"));
            	file.setOriginal_filename(rs.getString("osa_original_filename"));
            	file.setPath(rs.getString("osa_path"));
            	file.setContextpath(rs.getString("osa_context_path"));
            	file.setFilesize(rs.getLong("osa_filesize"));
            	file.setImage_label(rs.getString("osa_image_label"));
            	file.setImage_type(rs.getString("osa_image_type"));
                return file;  
            } 
        });
	}

	private final static String _SELECT_STRING_FOR_PRODUCT="select a.*, prod.osa_prod_num product_number, prod.osa_prod_name product_name,"
			+ "o.osa_order_number order_number, u.osa_username uploaded_by_user "
			+ "from osa_attr_file a left outer join osa_product prod on a.osa_product_id=prod.osa_prod_id left outer join "
			+ "osa_order o on a.osa_order_id=o.osa_order_id left outer join "
			+ "osa_user u on a.osa_uploaded_by_user_id=u.osa_user_id where a.osa_product_id = ?";
	@Override
	public List<Attachment> getFileByProductId(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
        
        return jdbcTemplate.query(_SELECT_STRING_FOR_PRODUCT, params, new RowMapper<Attachment>(){  
            @Override  
            public Attachment mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Attachment file  = new Attachment();
            	file.setId(rs.getString("osa_file_id"));
            	file.setFilename(rs.getString("osa_filename"));
            	file.setUnique_filename(rs.getString("osa_unique_filename"));
            	file.setOriginal_filename(rs.getString("osa_original_filename"));
            	file.setPath(rs.getString("osa_path"));
            	file.setContextpath(rs.getString("osa_context_path"));
            	file.setFilesize(rs.getLong("osa_filesize"));
            	file.setImage_label(rs.getString("osa_image_label"));
            	file.setImage_type(rs.getString("osa_image_type"));
                return file;  
            } 
        });
	}

	private final static String _SELECT_STRING_FOR_ORDER="select a.*, prod.osa_prod_num product_number, prod.osa_prod_name product_name,"
			+ "o.osa_order_number order_number, u.osa_username uploaded_by_user "
			+ "from osa_attr_file a left outer join osa_product prod on a.osa_product_id=prod.osa_prod_id left outer join "
			+ "osa_order o on a.osa_order_id=o.osa_order_id left outer join "
			+ "osa_user u on a.osa_uploaded_by_user_id=u.osa_user_id where a.osa_order_id = ?";
	@Override
	public List<Attachment> getFileByOrderId(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
        
        return jdbcTemplate.query(_SELECT_STRING_FOR_ORDER, params, new RowMapper<Attachment>(){  
            @Override  
            public Attachment mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Attachment file  = new Attachment();
            	file.setId(rs.getString("osa_file_id"));
            	file.setFilename(rs.getString("osa_filename"));
            	file.setUnique_filename(rs.getString("osa_unique_filename"));
            	file.setOriginal_filename(rs.getString("osa_original_filename"));
            	file.setPath(rs.getString("osa_path"));
            	file.setContextpath(rs.getString("osa_context_path"));
            	file.setFilesize(rs.getLong("osa_filesize"));
            	file.setImage_label(rs.getString("osa_image_label"));
            	file.setImage_type(rs.getString("osa_image_type"));
            	file.setUploaded_date(rs.getTimestamp("osa_upload_date"));
            	file.setUploaded_by_user(rs.getString("uploaded_by_user"));
                return file;  
            } 
        });
	}

	private final static String _SELECT_STRING_FOR_REQ="select a.*, prod.osa_prod_num product_number, prod.osa_prod_name product_name,"
			+ "o.osa_order_number order_number, u.osa_username uploaded_by_user "
			+ "from osa_attr_file a left outer join osa_product prod on a.osa_product_id=prod.osa_prod_id left outer join "
			+ "osa_order o on a.osa_order_id=o.osa_order_id left outer join "
			+ "osa_user u on a.osa_uploaded_by_user_id=u.osa_user_id where a.osa_requisition_id = ?";
	@Override
	public List<Attachment> getFileByRequisitionId(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
        
        return jdbcTemplate.query(_SELECT_STRING_FOR_REQ, params, new RowMapper<Attachment>(){  
            @Override  
            public Attachment mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Attachment file  = new Attachment();
            	file.setId(rs.getString("osa_file_id"));
            	file.setFilename(rs.getString("osa_filename"));
            	file.setUnique_filename(rs.getString("osa_unique_filename"));
            	file.setOriginal_filename(rs.getString("osa_original_filename"));
            	file.setPath(rs.getString("osa_path"));
            	file.setContextpath(rs.getString("osa_context_path"));
            	file.setFilesize(rs.getLong("osa_filesize"));
            	file.setImage_label(rs.getString("osa_image_label"));
            	file.setImage_type(rs.getString("osa_image_type"));
            	file.setUploaded_date(rs.getTimestamp("osa_upload_date"));
            	file.setUploaded_by_user(rs.getString("uploaded_by_user"));
                return file;  
            } 
        });
	}

	private final static String _SELECT_STRING_FOR_CONTRACT="select a.*, prod.osa_prod_num product_number, prod.osa_prod_name product_name,"
			+ "o.osa_order_number order_number, u.osa_username uploaded_by_user "
			+ "from osa_attr_file a left outer join osa_product prod on a.osa_product_id=prod.osa_prod_id left outer join "
			+ "osa_order o on a.osa_order_id=o.osa_order_id left outer join "
			+ "osa_user u on a.osa_uploaded_by_user_id=u.osa_user_id where a.osa_contract_id = ?";
	@Override
	public List<Attachment> getFileByContractId(String id) throws Exception {
		// TODO Auto-generated method stub
		Object[] params = new Object[] {id};
        
        return jdbcTemplate.query(_SELECT_STRING_FOR_CONTRACT, params, new RowMapper<Attachment>(){  
            @Override  
            public Attachment mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Attachment file  = new Attachment();
            	file.setId(rs.getString("osa_file_id"));
            	file.setFilename(rs.getString("osa_filename"));
            	file.setUnique_filename(rs.getString("osa_unique_filename"));
            	file.setOriginal_filename(rs.getString("osa_original_filename"));
            	file.setPath(rs.getString("osa_path"));
            	file.setContextpath(rs.getString("osa_context_path"));
            	file.setFilesize(rs.getLong("osa_filesize"));
            	file.setImage_label(rs.getString("osa_image_label"));
            	file.setImage_type(rs.getString("osa_image_type"));
            	file.setUploaded_date(rs.getTimestamp("osa_upload_date"));
            	file.setUploaded_by_user(rs.getString("uploaded_by_user"));
                return file;  
            } 
        });
	}

	private final static String _SELECT_STRING_ALL="select a.*, prod.osa_prod_num product_number, prod.osa_prod_name product_name,"
			+ "o.osa_order_number order_number, u.osa_username uploaded_by_user "
			+ "from osa_attr_file a left outer join osa_product prod on a.osa_product_id=prod.osa_prod_id left outer join "
			+ "osa_order o on a.osa_order_id=o.osa_order_id left outer join "
			+ "osa_user u on a.osa_uploaded_by_user_id=u.osa_user_id";
	@Override
	public List<Attachment> getFiles(Attachment file, String start,
			String limit, String sortColumn, String sortDir) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer();
		queryString.append(_SELECT_STRING_ALL);
		queryString.append(" where 1=1 ");
		
		//Filter combination
		if( !"".equals(file.getOriginal_filename())&&file.getOriginal_filename()!=null  ){
			queryString.append("and osa_original_filename='"+file.getOriginal_filename()+"' ");
		}
		if( !"".equals(file.getPath())&&file.getPath()!=null ){
			queryString.append("and osa_path='"+file.getPath()+"' ");
		}
		if( !"".equals(file.getContextpath())&&file.getContextpath()!=null ){
			queryString.append("and osa_context_path='"+file.getContextpath()+"' ");
		}
		
		//Sort combination
		if( (!"".equals(sortColumn)&&sortColumn!=null ) ){
			queryString.append(" order by "+sortColumn+" "+sortDir);
		}
		if( (!"".equals(start)&&start!=null ) ){
			if("".equals(limit)||limit==null){
				queryString.append(" limit "+start+",-1");
			}else{
				queryString.append(" limit "+start+","+limit);
			}
		}
		return jdbcTemplate.query(queryString.toString(), new RowMapper<Attachment>(){  
            @Override  
            public Attachment mapRow(ResultSet rs,int rowNum)throws SQLException {  
            	Attachment file  = new Attachment();
            	file.setId(rs.getString("osa_file_id"));
            	file.setFilename(rs.getString("osa_filename"));
            	file.setUnique_filename(rs.getString("osa_unique_filename"));
            	file.setOriginal_filename(rs.getString("osa_original_filename"));
            	file.setPath(rs.getString("osa_path"));
            	file.setContextpath(rs.getString("osa_context_path"));
            	file.setFilesize(rs.getLong("osa_filesize"));
            	file.setImage_label(rs.getString("osa_image_label"));
            	file.setImage_type(rs.getString("osa_image_type"));
            	file.setUploaded_date(rs.getTimestamp("osa_upload_date"));
            	file.setUploaded_by_user(rs.getString("uploaded_by_user"));
                return file;  
            } 
        });
	}

}
