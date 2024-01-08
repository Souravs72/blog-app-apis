package com.sourav.blog.services;

import java.util.List;

import com.sourav.blog.entities.Post;
import com.sourav.blog.payloads.PostDTO;
import com.sourav.blog.payloads.PostResponse;

public interface PostService {
	
	//create
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
	
	//update
	PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	
	//delete
	void deletePost(Integer postId);
	
	//get
	PostDTO getPostById(Integer postId);
	
	// getAll
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize);
	
	
	//get all posts by user
	List<PostDTO> getPostsByUser(Integer userId);
	
	
	//get all posts by user
	List<PostDTO> getPostsByCategory(Integer categoryId);
	
	//search posts
	List<PostDTO> searchPosts(String keyword);
}
