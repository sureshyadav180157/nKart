package com.assessment.nkart.services;

import java.util.List;

import com.assessment.nkart.models.DashboardData;
import com.assessment.nkart.models.ProductDetails;
import com.assessment.nkart.models.Products;

public interface IProductService {
	List<Products> getProductsList(int subCategoryId);
	ProductDetails getProductDetails(int productId);
	DashboardData getDashboardData();
}
