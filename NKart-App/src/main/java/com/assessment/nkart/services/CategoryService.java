package com.assessment.nkart.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.nkart.dbmanager.ProductsDatabaseManager;
import com.assessment.nkart.models.ProductCategory;
@Service
public class CategoryService implements ICategoryService {

	@Override
	public List<ProductCategory> getProductCategories() {
		ProductsDatabaseManager getProducts = new ProductsDatabaseManager();
		return getProducts.getProductCategories();
	}
}
