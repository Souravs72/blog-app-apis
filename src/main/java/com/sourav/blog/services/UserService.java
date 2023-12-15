package com.sourav.blog.services;

import java.util.List;

import com.sourav.blog.payloads.UserDTO;

public interface UserService {
	
	UserDTO createUser(UserDTO userDto);
	UserDTO updateUser(UserDTO user, Integer userId);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer userId);
}
