package com.blogapp.apis.services;

import java.util.List;

import com.blogapp.apis.payloads.UserDto;

public interface UserService {
  
	UserDto registerNewUser(UserDto user);
	
	//CRUD operation
  UserDto createUser(UserDto user);
  UserDto updateUser(UserDto user,Integer userId);
  UserDto getUserById(Integer userId);
  List<UserDto> getAllUsers();
  void deleteUser(Integer userId);
}
