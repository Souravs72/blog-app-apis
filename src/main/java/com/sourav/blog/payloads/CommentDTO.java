package com.sourav.blog.payloads;


import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

	private Integer commentId;
	private String content;
	private Date addedDate;
	
	private UserDTO user;
	
}
