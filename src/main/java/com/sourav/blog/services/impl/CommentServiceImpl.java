package com.sourav.blog.services.impl;

import java.util.Date;  

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourav.blog.entities.Comment;
import com.sourav.blog.entities.Post;
import com.sourav.blog.entities.User;
import com.sourav.blog.exceptions.ResourceNotFoundException;
import com.sourav.blog.payloads.CommentDTO;
import com.sourav.blog.repositories.CommentRepository;
import com.sourav.blog.repositories.PostRepository;
import com.sourav.blog.repositories.UserRepository;
import com.sourav.blog.services.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer userId, Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Comment comment =  this.modelMapper.map(commentDTO, Comment.class);
		comment.setAddedDate(new Date());
		comment.setPost(post);
		comment.setUser(user);
		
		Comment createdComment = this.commentRepository.save(comment);
		
		return this.modelMapper.map(createdComment, CommentDTO.class);
	}

	@Override
	public CommentDTO updateComment(CommentDTO CommentDTO, Integer commentId) {
				
		
		return null;
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new 
				ResourceNotFoundException("Comment", "comment id", commentId));
		
		this.commentRepository.delete(comment);
		
	}

	@Override
	public CommentDTO getCommentById(Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDTO getCommentsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy,
			String SortDir) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
