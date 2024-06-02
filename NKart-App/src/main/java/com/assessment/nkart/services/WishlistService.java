package com.assessment.nkart.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.nkart.dbmanager.WishlistAndCartDBManager;
import com.assessment.nkart.models.Products;
@Service
public class WishlistService implements IWishlistService {

	@Override
	public boolean addProductToWishlist(int productId, int userId) {
		WishlistAndCartDBManager wishlistManager = new WishlistAndCartDBManager();
		return wishlistManager.addProductToWishlist(productId, userId);
	}

	@Override
	public List<Products> getWishListProducts(int userId) {
		WishlistAndCartDBManager wishlistManager = new WishlistAndCartDBManager();
		return wishlistManager.getWishlistProducts(userId);
		
	}

	@Override
	public boolean deleteProductFromWishlist(int productId, int userId) {
		WishlistAndCartDBManager wishlistManager = new WishlistAndCartDBManager();
		return wishlistManager.deleteProductFromWishlist(productId, userId);
	}

}
