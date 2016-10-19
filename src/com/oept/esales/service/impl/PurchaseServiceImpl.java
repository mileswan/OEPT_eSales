package com.oept.esales.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oept.esales.dao.FavoriteDao;
import com.oept.esales.dao.ProductDao;
import com.oept.esales.dao.ShopcartDao;
import com.oept.esales.model.Favorite;
import com.oept.esales.model.Product;
import com.oept.esales.model.Shopcart;
import com.oept.esales.service.PurchaseService;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/25
 * Description: Purchase business service implementor.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private ProductDao productDao; //产品信息DAO
	@Autowired
	private ShopcartDao shopcartDao; //购物车DAO
	@Autowired
	private FavoriteDao favoriteDao; //收藏夹DAO
	
	@Override
	public List<Product> getProdDistinctCat()
			throws Exception {
		// TODO Auto-generated method stub
		Product availProduct = new Product();
		availProduct.setStatus("已发布");
		availProduct.setCheck_valid_date(true);
		availProduct.setImage_type("Thumbnail image");
		return productDao.getProdDistinctCat(availProduct);
	}

	@Override
	public List<Product> getAvailProducts()
			throws Exception {
		// TODO Auto-generated method stub
		Product availProduct = new Product();
		availProduct.setCheck_valid_date(true);
		availProduct.setStatus("Published");
		//availProduct.setImage_type("Thumbnail image");
		return productDao.loadAvailProducts(availProduct);
	}

	@Override
	public boolean addProdToShopcart(String productId, String userId) throws Exception {
		// TODO Auto-generated method stub
		Shopcart item = new Shopcart();
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		
		List<Shopcart> existItemList = shopcartDao.getItemsByUserProdId(userId, productId);
		
		if(existItemList.size()==0){
			item.setCreated(nowdate);
			item.setUpdate(nowdate);
			item.setQuantity(1);  //Set default quantity here
			item.setCreated_by_id(userId);
			item.setUpdate_by_id(userId);
			item.setUser_id(userId);
			item.setProduct_id(productId);
			
			Product shopProduct = productDao.getProductById(productId);
			item.setPrice(shopProduct.getPrice());
			item.setAmount(shopProduct.getPrice() * 1);
			return shopcartDao.addItem(item);
		}else{
			existItemList.get(0).setUpdate(nowdate);
			existItemList.get(0).setUpdate_by_id(userId);
			existItemList.get(0).setPrice(existItemList.get(0).getProduct_price());
			existItemList.get(0).setQuantity(existItemList.get(0).getQuantity() + 1);
			existItemList.get(0).setAmount(existItemList.get(0).getPrice() * existItemList.get(0).getQuantity());
			return shopcartDao.updateItem(existItemList.get(0));
		}
		
	}

	@Override
	public List<Shopcart> getAvailShopcartItems(String userId) throws Exception {
		// TODO Auto-generated method stub
		return shopcartDao.getItemsByUserId(userId);
	}

	@Override
	public boolean delShopcartItemById(String id) throws Exception {
		// TODO Auto-generated method stub
		return shopcartDao.delItemById(id);
	}

	@Override
	public boolean updateItemById(Shopcart item) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		item.setUpdate(nowdate);
		return shopcartDao.updateItem(item);
	}

	@Override
	public Shopcart getShopcartItemById(String itemid) throws Exception {
		// TODO Auto-generated method stub
		return shopcartDao.getItemById(itemid);
	}

	@Override
	public boolean checkFavItem(String prod_id, String user_id)
			throws Exception {
		// TODO Auto-generated method stub
		return favoriteDao.checkFavItem(prod_id, user_id);
	}

	@Override
	public boolean addFavItem(Favorite item) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();       
		Timestamp nowdate = new Timestamp(date.getTime());
		item.setItem_created_date(nowdate);
		item.setItem_update_date(nowdate);
		return favoriteDao.addFavItem(item);
	}

	@Override
	public boolean delFavItemById(String item_id) throws Exception {
		// TODO Auto-generated method stub
		return favoriteDao.delFavItemById(item_id);
	}

	@Override
	public List<Favorite> getAvailFavItemsByUserId(String userId)
			throws Exception {
		// TODO Auto-generated method stub
		return favoriteDao.getFavItemsByUserId(userId);
	}

}
