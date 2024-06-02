package com.assessment.nkart.dbmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assessment.nkart.models.DashboardData;
import com.assessment.nkart.models.ItemColor;
import com.assessment.nkart.models.ItemSize;
import com.assessment.nkart.models.ProductCategory;
import com.assessment.nkart.models.ProductDetails;
import com.assessment.nkart.models.Products;
import com.assessment.nkart.models.SubCategory;
import com.assessment.nkart.models.TopImage;

public class ProductsDatabaseManager {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public List<Products> getProductsList(int subCategoryId) {
		if(subCategoryId > 0) {
			return getProducts(subCategoryId);
		} else {
			return getProducts();
		}
	}
	
	private List<Products> getProducts(){
		List<Products> products = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				statement = connection.createStatement();
				// Result set get the result of the SQL query
				resultSet = statement.executeQuery("select * from nKart.products");
				products = getProductsFromResultSet(resultSet);
			}
			return products;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return products;
		} finally {
			DatabaseConnectionManager.closeDatabaseConnection();
			closeStatementsAndResultset();
		}
	}
	
	private List<Products> getProducts(int subCategoryId){
		List<Products> products = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				preparedStatement = connection.prepareStatement("select * from nKart.products where subCategoryId = ?");
				preparedStatement.setInt(1, subCategoryId);
				// Result set get the result of the SQL query
				resultSet = preparedStatement.executeQuery();
				products = getProductsFromResultSet(resultSet);
			}
			return products;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return products;
		} finally {
			DatabaseConnectionManager.closeDatabaseConnection();
			closeStatementsAndResultset();
		}
	}

	public ProductDetails getProductDetails(int productId) {
		ProductDetails productDetails = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				// PreparedStatements can use variables and are more efficient
				preparedStatement = connection
						.prepareStatement("select * from nKart.product_details where productId = ?");
				preparedStatement.setInt(1, productId);
				resultSet = preparedStatement.executeQuery();
				productDetails = getProductDetailsFromResultSet(resultSet);
			}
			return productDetails;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return productDetails;
		} finally {
			DatabaseConnectionManager.closeDatabaseConnection();
			closeStatementsAndResultset();
		}
	}

	public DashboardData getDashboardData() {
		DashboardData dashboardData = new DashboardData();
		List<TopImage> topImages = null;
		List<Products> newCollection = null;
		List<Products> bestSelling = null;
		List<Products> recentView = null;
		List<Products> dealOfTheDay = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				statement = connection.createStatement();
				// Result set get the result of the SQL query
				resultSet = statement.executeQuery("select * from nKart.product_image");
				topImages = getTopImageFromResultSet(resultSet);
				dashboardData.setTopImages(topImages);

				resultSet = statement.executeQuery("SELECT * FROM nKart.products ORDER BY rand() limit 7");
				newCollection = getProductsFromResultSet(resultSet);
				dashboardData.setNewCollection(newCollection);

				resultSet = statement.executeQuery("SELECT * FROM nKart.products ORDER BY rand() limit 5");
				bestSelling = getProductsFromResultSet(resultSet);
				dashboardData.setNewCollection(bestSelling);

				resultSet = statement.executeQuery("SELECT * FROM nKart.products ORDER BY rand() limit 3");
				recentView = getProductsFromResultSet(resultSet);
				dashboardData.setBestSelling(recentView);

				resultSet = statement.executeQuery("SELECT * FROM nKart.products ORDER BY rand() limit 2");
				dealOfTheDay = getProductsFromResultSet(resultSet);
				dashboardData.setDealOfTheDay(dealOfTheDay);

			}
			return dashboardData;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return dashboardData;
		} finally {
			DatabaseConnectionManager.closeDatabaseConnection();
			closeStatementsAndResultset();
		}
	}

	private List<Products> getProductsFromResultSet(ResultSet resultSet) throws SQLException {
		List<Products> products = new ArrayList<Products>();
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name also possible to get the columns
			// via the column number which starts at 1 e.g. resultSet.getSTring(2);
			Products product = new Products();
			product.setProductId(resultSet.getInt("productId"));
			product.setTitle(resultSet.getString("title"));
			product.setShortDesc(resultSet.getString("shortDesc"));
			product.setCategoryId(resultSet.getInt("categoryId"));
			product.setSubCategoryId(resultSet.getInt("subCategoryId"));
			product.setQuantity(resultSet.getInt("quantity"));
			product.setPrice(resultSet.getDouble("price"));
			product.setDiscount(resultSet.getDouble("discount"));
			product.setCreatedDate(resultSet.getDate("createdDate"));
			product.setLastUpdated(resultSet.getDate("lastUpdated"));
			product.setThumbnailUrl(resultSet.getString("thumbnailUrl"));
			products.add(product);
		}
		return products;
	}

	private ProductDetails getProductDetailsFromResultSet(ResultSet resultSet) throws SQLException {
		ProductDetails product = null;
		// ResultSet is initially before the first data set
		resultSet.next();
		product = new ProductDetails();
		product.setProductId(resultSet.getInt("productId"));
		product.setTitle(resultSet.getString("title"));
		product.setDescription(resultSet.getString("description"));
		product.setQuantity(resultSet.getInt("quantity"));
		product.setPrice(resultSet.getDouble("price"));
		product.setDiscount(resultSet.getDouble("discount"));
		product.setProductSize(getProductSize(resultSet.getInt("productSize")));
		product.setProductColour(getProductColor(resultSet.getInt("productColour")));
		product.setImageId(resultSet.getInt("imageId"));
		return product;
	}

	private List<ItemSize> getProductSize(int sizeId) {
		List<ItemSize> itemSizes = new ArrayList<ItemSize>();
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from nKart.item_size");
				while (resultSet.next()) {
					ItemSize itemSize = new ItemSize();
					itemSize.setSizeId(resultSet.getInt("SizeId"));
					itemSize.setSizeName(resultSet.getString("SizeName"));
					itemSize.setSizeValue(resultSet.getString("SizeValue"));
					itemSizes.add(itemSize);
				}
			}
			return itemSizes;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return itemSizes;
		}
	}

	private List<ItemColor> getProductColor(int colorId) {
		List<ItemColor> itemColors = new ArrayList<ItemColor>();
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				// PreparedStatements can use variables and are more efficient
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from nKart.item_color");
				while (resultSet.next()) {
					ItemColor itemColor = new ItemColor();
					itemColor.setColourId(resultSet.getInt("ColorId"));
					itemColor.setColourName(resultSet.getString("ColorName"));
					itemColor.setColourCode(resultSet.getString("ColorCode"));
					itemColors.add(itemColor);
				}
			}
			return itemColors;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return itemColors;
		}
	}
	
	public List<ProductCategory> getProductCategories() {
		List<ProductCategory> productsCategories = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				statement = connection.createStatement();
				// Result set get the result of the SQL query
				resultSet = statement.executeQuery("select * from nKart.product_category");
				productsCategories = getCategoriesFromResultSet(resultSet);
			}
			return productsCategories;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return productsCategories;
		} finally {
			DatabaseConnectionManager.closeDatabaseConnection();
			closeStatementsAndResultset();
		}
	}

	private List<ProductCategory> getCategoriesFromResultSet(ResultSet resultSet) throws SQLException {
		List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			ProductCategory product = new ProductCategory();
			product.setCategoryId(resultSet.getInt("categoryID"));
			product.setCategoryName(resultSet.getString("categoryName"));
			product.setSubCategories(getProductSubCategories(resultSet.getInt("categoryID")));
			productCategories.add(product);
		}
		return productCategories;
	}

	private List<SubCategory> getProductSubCategories(int productId) {
		List<SubCategory> productSubCategory = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement("select * from nKart.sub_category where categoryID = ?");
				preparedStatement.setInt(1, productId);
				// Result set get the result of the SQL query
				resultSet = preparedStatement.executeQuery();
				productSubCategory = getSubCategoriesFromResultSet(resultSet);
			}
			return productSubCategory;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return productSubCategory;
		}
	}

	private List<SubCategory> getSubCategoriesFromResultSet(ResultSet resultSet) throws SQLException {
		List<SubCategory> productCategories = new ArrayList<SubCategory>();
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			SubCategory product = new SubCategory();
			product.setCategoryId(resultSet.getInt("categoryID"));
			product.setSubCategoryId(resultSet.getInt("subCategory"));
			product.setSubCategoryName(resultSet.getString("categoryName"));
			productCategories.add(product);
		}
		return productCategories;
	}

	private List<TopImage> getTopImageFromResultSet(ResultSet resultSet) throws SQLException {
		List<TopImage> topImages = new ArrayList<TopImage>();
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name also possible to get the columns
			// via the column number which starts at 1 e.g. resultSet.getSTring(2);
			TopImage topImage = new TopImage();
			topImage.setProductId(resultSet.getInt("productId"));
			topImage.setSubCategoryId(resultSet.getInt("imageId"));
			topImage.setUrl(resultSet.getString("imageUrl"));
			topImage.setIsProductListing(true);
			topImages.add(topImage);
		}
		return topImages;
	}

	// You need to close the resultSet
	private void closeStatementsAndResultset() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				statement.close();
			}
			if (statement != null) {
				statement.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
