package com.sourav.blog.repositories; 

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourav.blog.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}
