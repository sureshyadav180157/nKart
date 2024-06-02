package com.assessment.nkart.services;

import java.util.List;

import com.assessment.nkart.models.Products;

public interface IWishlistService {

	boolean addProductToWishlist(int productId, int userId);

	List<Products> getWishListProducts(int userId);
	
	boolean deleteProductFromWishlist(int productId, int userId);
}
