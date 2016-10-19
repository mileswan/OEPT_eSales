package com.oept.esales.service;

import java.util.List;

import com.oept.esales.model.Favorite;
import com.oept.esales.model.Product;
import com.oept.esales.model.Shopcart;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/11/25
 * Description: Purchase business service interface.
 * Copyright (c) 2015 OEPT. All rights reserved.
 */
public interface PurchaseService {
	/**
	 * Query all available products' distinct categories data
	 * 
	 * @return return list object of all distinct categories data
	 * @throws Exception 
	 */
	public List<Product> getProdDistinctCat() throws Exception;
	/**
	 * Query all available products' data
	 * 
	 * @return return list object of all available products data
	 * @throws Exception 
	 */
	public List<Product> getAvailProducts() throws Exception;
	/**
	 * Add product to shopping cart
	 * @param productId product's id
	 * @param userId current user's id
	 * 
	 * @return return true if insert succeeds
	 * @throws Exception 
	 */
	public boolean addProdToShopcart(String productId, String userId) throws Exception;
	/**
	 * Query all available shopping cart items data
	 * @param userId current user's id
	 * 
	 * @return return list object of all shopping cart items data
	 * @throws Exception 
	 */
	public List<Shopcart> getAvailShopcartItems(String userId) throws Exception;
	/**
	 * Query shopping cart item by item id
	 * @param itemid shopping cart item's id
	 * 
	 * @return return object of shopping cart item data
	 * @throws Exception 
	 */
	public Shopcart getShopcartItemById(String itemid) throws Exception;
	/**
	 * Delete shopping cart item by id
	 * @param id shopping cart item id
	 * 
	 * @return return true if delete succeeds
	 * @throws Exception 
	 */
	public boolean delShopcartItemById(String id) throws Exception;
	/**
	 * Update shopping cart item by id
	 * @param item Shopping cart item object
	 * 
	 * @return return true if update succeeds
	 * @throws Exception 
	 */
	public boolean updateItemById(Shopcart item) throws Exception;
	/**
	 * Check if the user has already collected this product
	 * @param prod_id selected product's id
	 * @param user_id current user's id
	 * 
	 * @return return true if the user has already collected this product
	 * @throws Exception 
	 */
	public boolean checkFavItem(String prod_id,String user_id) throws Exception;
	/**
	 * Collect selected product to user's favorite list
	 * @param item New favorite item object
	 * 
	 * @return return true if the user collected this product successfully
	 * @throws Exception 
	 */
	public boolean addFavItem(Favorite item) throws Exception;
	/**
	 * Delete favorite item by id
	 * @param item_id selected favorite item's id
	 * 
	 * @return return true if the user has already canceled this collected product
	 * @throws Exception 
	 */
	public boolean delFavItemById(String item_id) throws Exception;
	/**
	 * Query all available favorite items data
	 * @param userId current user's id
	 * 
	 * @return return list object of all favoriteitems data
	 * @throws Exception 
	 */
	public List<Favorite> getAvailFavItemsByUserId(String userId) throws Exception;
}
