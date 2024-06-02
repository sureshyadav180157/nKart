package com.assessment.nkart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.nkart.models.Address;
import com.assessment.nkart.models.UserInfo;
import com.assessment.nkart.services.IUsersService;

@RestController
@RequestMapping("/nKart")
public class UserDetailController {

	@Autowired
	private IUsersService usersService;

	@PostMapping(value = "/authenticateUser")
	public UserInfo authenticateUser(@RequestBody Map<String, String> userData) {
		String username = userData.get("username");
		String password = userData.get("password");
		return usersService.authenticateUser(username, password);
	}
	
	@PostMapping(value = "/registerUser")
	public boolean registerUser(@RequestBody Map<String, String> userData) {
		String username = userData.get("username");
		String password = userData.get("password");
		String salutation = userData.get("salutation");
		String firstName = userData.get("firstName");
		String lastName = userData.get("lastName");
		String email = userData.get("email");
		String mobile = userData.get("mobile");
		UserInfo userInfo = new UserInfo(username, salutation, firstName, 
				lastName, email, mobile, password);
		return usersService.registerUser(userInfo);
	}
	
	@GetMapping(value = "/getAddress")
	public List<Address> getUserAddress(@RequestParam("userId") int userId) {
		return usersService.getUserAddress(userId);
	}
}
