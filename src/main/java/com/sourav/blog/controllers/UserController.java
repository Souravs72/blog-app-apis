package com.sourav.blog.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Handles CRUD operation with USER API")
public class UserController {

	private final UserService userService;

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {

		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/{userId}")
	@Operation(description = "Get endpoint to return particular user", summary = "This returns all the User(ADMIN, NORMAL, MANAGER)", responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),

	})
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {

		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {

		UserDTO updatedUserDTO = this.userService.createUser(userDTO);

		return new ResponseEntity<UserDTO>(updatedUserDTO, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId) {

		UserDTO updatedUserDTO = this.userService.updateUser(userDTO, userId);

		return new ResponseEntity<UserDTO>(updatedUserDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
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