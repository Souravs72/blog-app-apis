package com.sourav.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourav.blog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
