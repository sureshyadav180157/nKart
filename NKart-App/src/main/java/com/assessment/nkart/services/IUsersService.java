package com.assessment.nkart.services;

import java.util.List;

import com.assessment.nkart.models.Address;
import com.assessment.nkart.models.UserInfo;

public interface IUsersService {
	
	UserInfo authenticateUser(String userName, String password);
	boolean registerUser(UserInfo userInfo);
	List<Address> getUserAddress(int userId);

}
