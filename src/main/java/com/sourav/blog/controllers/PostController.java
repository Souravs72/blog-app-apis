package com.sourav.blog.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.blog.payloads.ApiResponse;
import com.sourav.blog.payloads.PostDTO;
import com.sourav.blog.payloads.PostResponse;
import com.sourav.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);

		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId) {

		PostDTO updatePost = this.postService.updatePost(postDTO, postId);

		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> createPost(@PathVariable Integer postId) {

		this.postService.deletePost(postId);
		
		String pattern = "MM-dd-yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("post is deleted successfully", true, date), HttpStatus.OK);
	}

	// get Posts by Id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPost(@PathVariable Integer postId) {
		PostDTO post = this.postService.getPostById(postId);

		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}

	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false)Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		
		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// get Posts By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostByUser(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false)Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
			@PathVariable Integer userId) {

		PostResponse posts = this.postService.getPostsByUser(userId, pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}

	// get posts ByCategory
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostByCategory(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false)Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
			@PathVariable Integer categoryId) {

		PostResponse posts = this.postService.getPostsByCategory(categoryId, pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}

}
