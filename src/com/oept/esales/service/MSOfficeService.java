package com.oept.esales.service;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;

import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.RequisitionFlat;

/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/2/16
 * Description: MS Office export/import service interface.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
public interface MSOfficeService {

	/**
	 * @Method: exportPO
	 * @Description: 导出采购订单excel表
	 * @Author:mwan
	 *
	 * @param objectId
	 * @throws Exception
	 */ 
	public void exportPO(String objectId, ServletOutputStream outputStream) throws Exception;
	/**
	 * @Method: exportSO
	 * @Description: 导出销售订单excel表
	 * @Author:mwan
	 *
	 * @param objectId
	 * @throws Exception
	 */ 
	public void exportSO(String objectId, ServletOutputStream outputStream) throws Exception;
	/**
	 * @Method: exportSIO
	 * @Description: 导出入库单excel表
	 * @Author:mwan
	 *
	 * @param objectId
	 * @throws Exception
	 */ 
	public void exportSIO(String objectId, ServletOutputStream outputStream) throws Exception;
	/**
	 * @Method: exportSOO
	 * @Description: 导出出库单excel表
	 * @Author:mwan
	 *
	 * @param objectId
	 * @throws Exception
	 */ 
	public void exportSOO(String objectId, ServletOutputStream outputStream) throws Exception;
	/**
	 * @Method: importPO
	 * @Description: 导入采购订单excel表
	 * @Author:mwan
	 *
	 * @param inputStream
	 * @throws Exception
	 */ 
	public OrderFlat importPO(InputStream inputStream) throws Exception;
	/**
	 * @Method: importSO
	 * @Description: 导入销售订单excel表
	 * @Author:mwan
	 *
	 * @param inputStream
	 * @throws Exception
	 */ 
	public OrderFlat importSO(InputStream inputStream) throws Exception;
	/**
	 * @Method: importSIO
	 * @Description: 导入入库单excel表
	 * @Author:mwan
	 *
	 * @param inputStream
	 * @throws Exception
	 */ 
	public RequisitionFlat importSIO(InputStream inputStream) throws Exception;
	/**
	 * @Method: importSOO
	 * @Description: 导入出库单excel表
	 * @Author:mwan
	 *
	 * @param inputStream
	 * @throws Exception
	 */ 
	public RequisitionFlat importSOO(InputStream inputStream) throws Exception;
}
