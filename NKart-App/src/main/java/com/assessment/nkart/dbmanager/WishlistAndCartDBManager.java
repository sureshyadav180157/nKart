package com.assessment.nkart.dbmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assessment.nkart.models.CartItems;
import com.assessment.nkart.models.ItemColor;
import com.assessment.nkart.models.ItemSize;
import com.assessment.nkart.models.ProductDetails;
import com.assessment.nkart.models.Products;

public class WishlistAndCartDBManager {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public List<Products> getWishlistProducts(int userId) {
		List<Products> products = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement("select productId from nKart.wish_list where userId = ?");
				preparedStatement.setInt(1, userId);
				// Result set get the result of the SQL query
				resultSet = preparedStatement.executeQuery();
				String productIds = getProductIds(resultSet);
				statement = connection.createStatement();
				resultSet = statement
						.executeQuery("select * from nKart.products where productId in (" + productIds + ")");
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

	private String getProductIds(ResultSet resultSet) {
		StringBuffer ids = new StringBuffer();
		try {
			while (resultSet.next()) {
				ids.append(resultSet.getInt(1)).append(", ");
			}
			if(ids.length() > 2)
				return ids.toString().substring(0, ids.length() - 2);
			else
				return ids.toString();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return ids.toString();
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
	
	public boolean addProductToWishlist(int productId, int userId) {
		int rowCount = 0;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement("insert into  nKart.wish_list values (?, ?)");
				preparedStatement.setInt(1, productId);
				preparedStatement.setInt(2, userId);
				rowCount = preparedStatement.executeUpdate();
				
			}
			return rowCount > 0 ? true : false;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		} finally {
			DatabaseConnectionManager.closeDatabaseConnection();
			closeStatementsAndResultset();
		}
	}
	
	public boolean addProductToCart(CartItems cartItems) {
		int rowCount = 0;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement("insert into  nKart.cart_list(productId, userId, colorId, sizeId, quantity) values (?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, cartItems.getProductId());
				preparedStatement.setInt(2, cartItems.getUserId());
				preparedStatement.setInt(3, cartItems.getColorId());
				preparedStatement.setInt(4, cartItems.getSizeId());
				preparedStatement.setInt(5, cartItems.getQuantity());
				rowCount = preparedStatement.executeUpdate();
				
			}
			return rowCount > 0 ? true : false;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		} finally {
			DatabaseConnectionManager.closeDatabaseConnection();
			closeStatementsAndResultset();
		}

	}

	public List<ProductDetails> getCartItems(int userId) {
		List<ProductDetails> products = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement("select * from nKart.cart_list where userId = ?");
				preparedStatement.setInt(1, userId);
				// Result set get the result of the SQL query
				resultSet = preparedStatement.executeQuery();
				String productIds = getProductIds(resultSet);
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from nKart.product_details where productId in (" + productIds + ")");
				products = getProductDetailsFromResultSet(resultSet);
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
	
	private List<ProductDetails> getProductDetailsFromResultSet(ResultSet resultSet) throws SQLException {
		List<ProductDetails> products = new ArrayList<ProductDetails>();
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
		ProductDetails product = null;
		// ResultSet is initially before the first data set
		product = new ProductDetails();
		product.setProductId(resultSet.getInt("productId"));
		product.setTitle(resultSet.getString("title"));
		product.setDescription(resultSet.getString("description"));
		product.setQuantity(resultSet.getInt("quantity"));
		product.setPrice(resultSet.getDouble("price"));
		product.setDiscount(resultSet.getDouble("discount"));
		product.setSelectedProductSize(getProductSize(resultSet.getInt("productSize")));
		product.setSelectedProductColour(getProductColor(resultSet.getInt("productColour")));
		product.setImageId(resultSet.getInt("imageId"));
		products.add(product);
		}
		return products;
	}
	
	private ItemSize getProductSize(int sizeId) {
		ItemSize itemSize = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				// PreparedStatements can use variables and are more efficient
				preparedStatement = connection.prepareStatement("select * from nKart.item_size where SizeId = ?");
				preparedStatement.setInt(1, sizeId);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				itemSize = new ItemSize();
				itemSize.setSizeId(resultSet.getInt("SizeId"));
				itemSize.setSizeName(resultSet.getString("SizeName"));
				itemSize.setSizeValue(resultSet.getString("SizeValue"));
			}
			return itemSize;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return itemSize;
		} 
	}

	private ItemColor getProductColor(int colorId) {
		ItemColor itemColor = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				// PreparedStatements can use variables and are more efficient
				preparedStatement = connection.prepareStatement("select * from nKart.item_color where ColorId = ?");
				preparedStatement.setInt(1, colorId);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				itemColor = new ItemColor();
				itemColor.setColourId(resultSet.getInt("ColorId"));
				itemColor.setColourName(resultSet.getString("ColorName"));
				itemColor.setColourCode(resultSet.getString("ColorCode"));
			}
			return itemColor;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return itemColor;
		} 
	}
	
	public boolean deleteProductFromWishlist(int productId, int userId) {
		int rowCount = 0;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement("delete from  nKart.wish_list where productId = ? and userId = ?");
				preparedStatement.setInt(1, productId);
				preparedStatement.setInt(2, userId);
				rowCount = preparedStatement.executeUpdate();
				
			}
			return rowCount > 0 ? true : false;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		} finally {
			DatabaseConnectionManager.closeDatabaseConnection();
			closeStatementsAndResultset();
		}
	}
	
	public boolean deleteProductFromCart(int productId, int userId) {
		int rowCount = 0;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement("delete from  nKart.cart_list where productId = ? and userId = ?");
				preparedStatement.setInt(1, productId);
				preparedStatement.setInt(2, userId);
				rowCount = preparedStatement.executeUpdate();
				
			}
			return rowCount > 0 ? true : false;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		} finally {
			DatabaseConnectionManager.closeDatabaseConnection();
			closeStatementsAndResultset();
		}
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
