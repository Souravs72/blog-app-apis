package com.sourav.blog.payloads;
 
import java.util.HashSet; 
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

	private Integer id;
	@NotEmpty
	@Size(min = 4, message = "Username must be greater than 3 chanracters")
	private String name;

	@Email(message = "Email is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be greater than 2 and less than 11 characters")
//	@JsonIgnore                 //To Hide Password, but it Will ignore for both GET(Marshalling) and POST(UnMarshalling)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)        //To hide password only for Marshalling
	private String password;

	@NotEmpty
	private String about;
	
	private Set<RoleDTO> roles = new HashSet<>();

}
