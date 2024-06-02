package com.assessment.nkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.nkart.models.ProductCategory;
import com.assessment.nkart.services.ICategoryService;

@RestController
@RequestMapping("/nKart")
public class ProductsCategoryController {
	@Autowired
	private ICategoryService categoryService;

	@GetMapping(value = "/getProductCategories")
	public List<ProductCategory> getProductCategories() {
		List<ProductCategory> productCategory = categoryService.getProductCategories();
		return productCategory;
	}
}
