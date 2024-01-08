package com.sourav.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sourav.blog.entities.Post;
import com.sourav.blog.entities.User;
import com.sourav.blog.entities.Category;


public interface PostRepository extends JpaRepository<Post, Integer>{
	Page<Post> findByUser(User user, Pageable pageable);
	Page<Post> findByCategory(Category category, Pageable pageable);
	List<Post> findByTitleContaining(String title);
}
