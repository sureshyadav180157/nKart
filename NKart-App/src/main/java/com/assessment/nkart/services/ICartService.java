package com.assessment.nkart.services;

import java.util.List;

import com.assessment.nkart.models.CartItems;
import com.assessment.nkart.models.ProductDetails;

public interface ICartService {

	boolean addProductToCart(CartItems cartItems);

	List<ProductDetails> getCartItems(int userId);
	
	boolean deleteProductFromCart(int productId, int userId);

}
