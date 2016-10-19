package com.oept.esales.service.impl;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oept.esales.dao.OrderDao;
import com.oept.esales.dao.RequisitionDao;
import com.oept.esales.model.OrderFlat;
import com.oept.esales.model.RequisitionFlat;
import com.oept.esales.service.MSOfficeService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2016/2/16
 * Description: MS Office export/import service implements.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
@SuppressWarnings("deprecation")
@Service("msOfficeService")
public class MSOfficeServiceImpl implements MSOfficeService {

	@Autowired
	private OrderDao orderDao; //order DAO
	@Autowired
	private RequisitionDao requisitionDao; //出入库单据DAO
	
	@Override
	public void exportPO(String objectId,ServletOutputStream outputStream) throws Exception {
		//创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("PO");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		//设置单元格内容
		cell.setCellValue("学员考试成绩一览表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);   
		//创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("姓名");
		row2.createCell(1).setCellValue("班级");   
		row2.createCell(2).setCellValue("笔试成绩");
		row2.createCell(3).setCellValue("机试成绩");   
		//在sheet里创建第三行
		HSSFRow row3=sheet.createRow(2);
		row3.createCell(0).setCellValue("李明");
		row3.createCell(1).setCellValue("As178");
		row3.createCell(2).setCellValue(87);   
		row3.createCell(3).setCellValue(78);

		//输出Excel文件    
		wb.write(outputStream);
		wb.close();
		outputStream.flush();
		outputStream.close();
	}

	@Override
	public void exportSO(String objectId,ServletOutputStream outputStream) throws Exception {

	}

	@Override
	public void exportSIO(String objectId,ServletOutputStream outputStream) throws Exception {

	}

	@Override
	public void exportSOO(String objectId,ServletOutputStream outputStream) throws Exception {

	}

	@Override
	public OrderFlat importPO(InputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		//HSSFWorkbook wb = new HSSFWorkbook(inputStream);
		return null;
	}

	@Override
	public OrderFlat importSO(InputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequisitionFlat importSIO(InputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequisitionFlat importSOO(InputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
