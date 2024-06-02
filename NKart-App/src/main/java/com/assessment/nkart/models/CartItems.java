package com.assessment.nkart.models;

public class CartItems {
	
	private int cart_id;
	private int productId;
	private int userId;
	private int colorId;
	private int sizeId;
	private int quantity;
	
	public CartItems() {}
	
	public CartItems(int productId, int userId, int colorId, 
			int sizeId, int quantity) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.colorId = colorId;
		this.sizeId = sizeId;
		this.quantity = quantity;
	}
	
	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public int getSizeId() {
		return sizeId;
	}
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
