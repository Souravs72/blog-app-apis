package com.sourav.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourav.blog.entities.Post;
import com.sourav.blog.entities.User;
import com.sourav.blog.entities.Category;


public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}
