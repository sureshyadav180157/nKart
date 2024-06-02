package com.assessment.nkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.nkart.models.DashboardData;
import com.assessment.nkart.models.ProductDetails;
import com.assessment.nkart.models.Products;
import com.assessment.nkart.services.IProductService;

@RestController
@RequestMapping("/nKart")
public class ProductsListController {
	@Autowired
	private IProductService productService;

	@GetMapping(value = "/dashboardData")
	public DashboardData getDashboardData() {
		return productService.getDashboardData();
	}

	@GetMapping(value = "/products")
	public List<Products> getProducts(@RequestParam(required = false) Integer id) {
		int subCatId = id != null ? id.intValue() : 0;
		return productService.getProductsList(subCatId);
	}

	@GetMapping(value = "/productDetails")
	public ProductDetails getProduct(@RequestParam("productId") int productId) {
		return productService.getProductDetails(productId);
	}
}
