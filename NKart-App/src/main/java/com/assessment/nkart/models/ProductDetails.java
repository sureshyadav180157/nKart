package com.assessment.nkart.models;

import java.util.List;

public class ProductDetails {
	
	private int productId;
	private String title;
	private String description;
	private int quantity;
	private int imageId;
	private double price;
	private double discount;
	private ItemSize selectedProductSize;
	private ItemColor selectedProductColour;
	private List<ItemSize> productSize;
	private List<ItemColor> productColour;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public List<ItemSize> getProductSize() {
		return productSize;
	}
	public void setProductSize(List<ItemSize> productSize) {
		this.productSize = productSize;
	}
	public List<ItemColor> getProductColour() {
		return productColour;
	}
	public void setProductColour(List<ItemColor> productColour) {
		this.productColour = productColour;
	}
	public ItemSize getSelectedProductSize() {
		return selectedProductSize;
	}
	public void setSelectedProductSize(ItemSize selectedProductSize) {
		this.selectedProductSize = selectedProductSize;
	}
	public ItemColor getSelectedProductColour() {
		return selectedProductColour;
	}
	public void setSelectedProductColour(ItemColor selectedProductColour) {
		this.selectedProductColour = selectedProductColour;
	}
	
}
