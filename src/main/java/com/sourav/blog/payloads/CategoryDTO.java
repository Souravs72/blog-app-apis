package com.sourav.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4, message = "title must be greater than 4 letter")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10, message = "description must be greater than 10 letters")
	private String categoryDescription;
	
	
}
