package com.sourav.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourav.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
