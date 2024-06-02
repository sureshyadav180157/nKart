package com.assessment.nkart.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.nkart.dbmanager.WishlistAndCartDBManager;
import com.assessment.nkart.models.CartItems;
import com.assessment.nkart.models.ProductDetails;

@Service
public class CartService implements ICartService {

	@Override
	public boolean addProductToCart(CartItems cartItems) {
		WishlistAndCartDBManager cartDBManager = new WishlistAndCartDBManager();
		return cartDBManager.addProductToCart(cartItems);

	}

	@Override
	public List<ProductDetails> getCartItems(int userId) {
		WishlistAndCartDBManager wishlistDBManager = new WishlistAndCartDBManager();
		return wishlistDBManager.getCartItems(userId);
	}

	@Override
	public boolean deleteProductFromCart(int productId, int userId) {
		WishlistAndCartDBManager cartDBManager = new WishlistAndCartDBManager();
		return cartDBManager.deleteProductFromCart(productId, userId);
	}

}
