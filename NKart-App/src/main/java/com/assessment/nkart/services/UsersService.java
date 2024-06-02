package com.assessment.nkart.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.nkart.dbmanager.UserInfoDatabaseManager;
import com.assessment.nkart.models.Address;
import com.assessment.nkart.models.UserInfo;
@Service
public class UsersService implements IUsersService {

	@Override
	public UserInfo authenticateUser(String userName, String password) {
		UserInfoDatabaseManager databaseManager = new UserInfoDatabaseManager();
		return databaseManager.authenticateUser(userName, password);
	}

	@Override
	public List<Address> getUserAddress(int userId) {
		UserInfoDatabaseManager databaseManager = new UserInfoDatabaseManager();
		return databaseManager.getUserAddress(userId);
	}

	@Override
	public boolean registerUser(UserInfo userInfo) {
		UserInfoDatabaseManager databaseManager = new UserInfoDatabaseManager();
		return databaseManager.registerUser(userInfo);
	}
}
