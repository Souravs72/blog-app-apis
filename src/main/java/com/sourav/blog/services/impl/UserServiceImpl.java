package com.sourav.blog.services.impl;

import java.util.List; 
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sourav.blog.entities.User;
import com.sourav.blog.payloads.UserDTO;
import com.sourav.blog.repositories.UserRepository;
import com.sourav.blog.services.UserService;
import com.sourav.blog.exceptions.*;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User",
								"id", userId));

		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setAbout(userDTO.getAbout());
		user.setPassword(userDTO.getPassword());

		User updateUser = this.userRepository.save(user);
		return this.userToDto(updateUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> users = this.userRepository.findAll();
		List<UserDTO> userDTOs = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDTOs;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);
	}
	
	private User dtoToUser(UserDTO userDTO) {
		
		User user = this.modelMapper.map(userDTO, User.class);
		return user;
	}
	private UserDTO userToDto(User user) {
		
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
}
