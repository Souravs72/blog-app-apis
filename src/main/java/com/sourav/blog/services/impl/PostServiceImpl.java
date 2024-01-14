package com.sourav.blog.services.impl;

import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sourav.blog.entities.Category;
import com.sourav.blog.entities.Post;
import com.sourav.blog.entities.User;
import com.sourav.blog.exceptions.ResourceNotFoundException;
import com.sourav.blog.payloads.PostDTO;
import com.sourav.blog.payloads.PostResponse;
import com.sourav.blog.repositories.CategoryRepository;
import com.sourav.blog.repositories.PostRepository;
import com.sourav.blog.repositories.UserRepository;
import com.sourav.blog.services.PostService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;

	String pattern = "MM-dd-yyyy HH:mm:ss";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());

	static PostResponse getPostResponse(List<PostDTO> postDTOs, Page<Post> pagePost) {
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDTOs);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setImageName("default.png");

		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post createdPost = this.postRepository.save(post);

		return this.modelMapper.map(createdPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());

		post.setAddedDate(new Date());
		post.setImageName(postDTO.getImageName());

		Post updatedPost = this.postRepository.save(post);
		return this.modelMapper.map(updatedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post posts = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));

		this.postRepository.delete(posts);

	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post posts = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
		return this.modelMapper.map(posts, PostDTO.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepository.findAll(pageable);
		List<Post> allPosts = pagePost.getContent();
		List<PostDTO> postDTOs = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postResponse = getPostResponse(postDTOs, pagePost);

		return postResponse;
	}

	@Override
	public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Page<Post> pagePost = this.postRepository.findByUser(user, pageable);
		List<Post> posts = pagePost.getContent();
		List<PostDTO> postDTOs = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		PostResponse postResponse = getPostResponse(postDTOs, pagePost);

		return postResponse;
	}

	@Override
	public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		Page<Post> pagePost = this.postRepository.findByCategory(category, pageable);
		List<Post> posts = pagePost.getContent();
		List<PostDTO> postDTOs = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postResponse = getPostResponse(postDTOs, pagePost);

		return postResponse;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {

		List<Post> posts = this.postRepository.findByTitleContaining(keyword);
		List<PostDTO> postDTOs = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		return postDTOs;
	}
}
