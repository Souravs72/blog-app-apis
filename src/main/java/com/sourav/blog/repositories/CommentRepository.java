package com.sourav.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourav.blog.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
