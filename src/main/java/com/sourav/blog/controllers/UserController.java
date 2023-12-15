package com.sourav.blog.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.blog.payloads.ApiResponse;
import com.sourav.blog.payloads.UserDTO;
import com.sourav.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	 
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
		
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		
		UserDTO updatedUserDTO = this.userService.createUser(userDTO);
		
		
		return new ResponseEntity<UserDTO>(updatedUserDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}") 
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer userId) {
		
		UserDTO updatedUserDTO = this.userService.updateUser(userDTO, userId);
		
		return new ResponseEntity<UserDTO>(updatedUserDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}") 
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		
		this.userService.deleteUser(userId);
		
		
//		String pattern = "MM-dd-yyyy HH:mm:ss.SSSZ";
		String pattern = "MM-dd-yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted succesfully", true, date), HttpStatus.OK);
	}

	
}