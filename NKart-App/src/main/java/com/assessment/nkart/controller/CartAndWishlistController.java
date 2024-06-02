package com.assessment.nkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.nkart.models.CartItems;
import com.assessment.nkart.models.ProductDetails;
import com.assessment.nkart.models.Products;
import com.assessment.nkart.services.ICartService;
import com.assessment.nkart.services.IWishlistService;

@RestController
@RequestMapping("/nKart")
public class CartAndWishlistController {
	@Autowired
	private IWishlistService wishListService;
	@Autowired
	private ICartService cartListService;

	@GetMapping(value = "/getWishlistProducts")
	public List<Products> getWishlistProduct(@RequestParam("userId") int userId) {
		return wishListService.getWishListProducts(userId);
	}

	@GetMapping(value = "/getCartlistProducts")
	public List<ProductDetails> getCartlistProduct(@RequestParam("userId") int userId) {
		return cartListService.getCartItems(userId);
	}
	
	@GetMapping(value = "/addWishlistProducts")
	public boolean addWishlistProduct(@RequestParam("productId") int productId, @RequestParam("userId") int userId) {
		return wishListService.addProductToWishlist(productId, userId);
	}
	
	@GetMapping(value = "/addProductToCart")
	public boolean addProductToCart(@RequestParam("productId") int productId, @RequestParam("userId") int userId, 
			@RequestParam("sizeId") int sizeId, @RequestParam("colorId") int colorId, @RequestParam("quantity") int quantity) {
		CartItems cartItems = new CartItems(productId, userId, colorId, sizeId, quantity);
		return cartListService.addProductToCart(cartItems);
	}
	
	@GetMapping(value = "/deleteCartProduct")
	public boolean deleteCartProduct(@RequestParam("productId") int productId, @RequestParam("userId") int userId) {
		return cartListService.deleteProductFromCart(productId, userId);
	}
	
	@GetMapping(value = "/deleteWishlistProduct")
	public boolean deleteWishlistProduct(@RequestParam("productId") int productId, @RequestParam("userId") int userId) {
		return wishListService.deleteProductFromWishlist(productId, userId);
	}
}
