package com.sourav.blog.payloads;
 
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sourav.blog.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	
	private int postId;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	private Date addedDate;
	
	private String imageName;
	
	private CategoryDTO category;
	private UserDTO user;
	
	private Set<CommentDTO> comments = new HashSet<>();
}
