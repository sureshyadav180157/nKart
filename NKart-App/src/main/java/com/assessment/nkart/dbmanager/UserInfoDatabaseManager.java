package com.assessment.nkart.dbmanager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assessment.nkart.models.Address;
import com.assessment.nkart.models.UserInfo;

public class UserInfoDatabaseManager {
	
	public UserInfo authenticateUser(String userName, String password) {
		UserInfo userInfo = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				// PreparedStatements can use variables and are more efficient
				preparedStatement = connection
						.prepareStatement("select * from nKart.users where email = ? and passwordHash = ?");
				preparedStatement.setString(1, userName);
				preparedStatement.setString(2, password);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				userInfo = new UserInfo();
				userInfo.setUserId(resultSet.getInt("userId"));
				userInfo.setUserName(resultSet.getString("userName"));
				userInfo.setSalutation(resultSet.getString("salutation"));
				userInfo.setFirstName(resultSet.getString("firstName"));
				userInfo.setLastName(resultSet.getString("lastName"));
				userInfo.setEmail(resultSet.getString("email"));
				userInfo.setMobile(resultSet.getString("mobile"));
				userInfo.setCreatedDate(resultSet.getDate("createdDate"));
				userInfo.setLastUpdated(resultSet.getDate("lastUpdated"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return userInfo;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return userInfo;
			}
		}
		return userInfo;
	}

	public List<Address> getUserAddress(int userId) {
		List<Address> addresses = new ArrayList<Address>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				// PreparedStatements can use variables and are more efficient
				preparedStatement = connection.prepareStatement("select * from nKart.address where userId = ?");
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					Address address = new Address();
					address.setUserId(resultSet.getInt("userId"));
					address.setAddressId(resultSet.getInt("addressId"));
					address.setAddress(resultSet.getString("address"));
					address.setCity(resultSet.getString("city"));
					address.setState(resultSet.getString("state"));
					address.setCountry(resultSet.getString("country"));
					address.setPinCode(resultSet.getString("pinCode"));
					address.setDefault(resultSet.getBoolean("isDefault"));
					address.setDeleted(resultSet.getBoolean("isDeleted"));
					address.setName(resultSet.getString("contactPerson"));
					address.setMobile(resultSet.getString("mobile"));
					addresses.add(address);
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return addresses;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return addresses;
			}
		}
		return addresses;
	}

	public boolean registerUser(UserInfo userInfo) {
		boolean isUpdated = false;
		PreparedStatement preparedStatement = null;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				// PreparedStatements can use variables and are more efficient
				preparedStatement = connection
						.prepareStatement("insert into nKart.users values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, getLastUserId());
				preparedStatement.setString(2, userInfo.getSalutation());
				preparedStatement.setString(3, userInfo.getFirstName());
				preparedStatement.setString(4, userInfo.getLastName());
				preparedStatement.setString(5, userInfo.getEmail());
				preparedStatement.setString(6, userInfo.getMobile());
				preparedStatement.setDate(7, new Date(System.currentTimeMillis()));
				preparedStatement.setDate(8, new Date(System.currentTimeMillis()));
				preparedStatement.setDate(9, new Date(System.currentTimeMillis()));
				preparedStatement.setString(10, userInfo.getPasswordHash());
				preparedStatement.setString(11, userInfo.getUserName());
				isUpdated = preparedStatement.executeUpdate() > 0 ? true : false;				
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return isUpdated;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return isUpdated;
			}
		}
		return isUpdated;
	}
	
	public int getLastUserId() {
		Statement statement = null;
		ResultSet resultSet = null;
		int userid = 0;
		try {
			Connection connection = DatabaseConnectionManager.getDatabaseConnection();
			if (connection != null) {
				statement = connection.createStatement();
				// Result set get the result of the SQL query
				resultSet = statement.executeQuery("SELECT userId FROM nKart.users ORDER BY userId DESC LIMIT 1");
				if(resultSet.next()) {
					userid = resultSet.getInt(1);
				}
			}
			return ++userid;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return userid;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return userid;
			}
		}
	}
}
