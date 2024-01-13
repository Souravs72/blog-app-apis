package com.sourav.blog.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRequest {
	
	private String username;
	private String password;
}
