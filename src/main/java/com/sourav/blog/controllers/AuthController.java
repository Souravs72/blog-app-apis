package com.sourav.blog.controllers;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sourav.blog.exceptions.ApiException;
import com.sourav.blog.payloads.UserDTO;
import com.sourav.blog.services.UserService;
import com.sourav.blog.token.JwtAuthRequest;
import com.sourav.blog.token.JwtAuthResponse;
import com.sourav.blog.token.JwtTokenHelper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

	private final JwtTokenHelper jwtTokenHelper;
	private final UserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;
	private final UserService userService;

	private void authenticate(String userEmail, String password) throws Exception {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userEmail, password);
		try {
			this.authenticationManager.authenticate(token);
		} catch (BadCredentialsException e) {
			throw new ApiException("Invalid username or password entered");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);

		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<UserDTO> registerToken(@RequestBody UserDTO userDTO) throws Exception {

		UserDTO registeredUser = this.userService.registerNewUser(userDTO);
		return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.OK);
	}
}