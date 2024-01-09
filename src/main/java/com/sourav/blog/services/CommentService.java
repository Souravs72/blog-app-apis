package com.sourav.blog.services;

import com.sourav.blog.payloads.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO commentDTO, Integer postId);

	// update
	CommentDTO updateComment(CommentDTO CommentDTO, Integer commentId);

	// delete
	void deleteComment(Integer commentId);

	// get
	CommentDTO getCommentById(Integer commentId);
	
	// get all Comments by user
	CommentDTO getCommentsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String SortDir);

}
