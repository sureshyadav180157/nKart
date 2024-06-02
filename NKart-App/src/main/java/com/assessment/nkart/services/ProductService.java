package com.assessment.nkart.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.nkart.dbmanager.ProductsDatabaseManager;
import com.assessment.nkart.models.DashboardData;
import com.assessment.nkart.models.ProductDetails;
import com.assessment.nkart.models.Products;

@Service
public class ProductService implements IProductService {

	@Override
	public List<Products> getProductsList(int subCategoryId) {
		ProductsDatabaseManager productDatabaseManager = new ProductsDatabaseManager();
		return productDatabaseManager.getProductsList(subCategoryId);
	}

	@Override
	public ProductDetails getProductDetails(int productId) {
		ProductsDatabaseManager productDatabaseManager = new ProductsDatabaseManager();
		return productDatabaseManager.getProductDetails(productId);
	}

	@Override
	public DashboardData getDashboardData() {
		ProductsDatabaseManager productDatabaseManager = new ProductsDatabaseManager();
		return productDatabaseManager.getDashboardData();
	}
}
