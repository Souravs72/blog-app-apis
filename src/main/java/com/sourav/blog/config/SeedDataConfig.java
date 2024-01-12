package com.sourav.blog.config;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sourav.blog.entities.Role;
import com.sourav.blog.entities.User;
import com.sourav.blog.payloads.UserDTO;
import com.sourav.blog.repositories.UserRepository;
import com.sourav.blog.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
    	Set<Role> roles = new HashSet<>();
    	Role role = new Role();
    	role.setName("ROLE_ADMIN");
    	roles.add(role);
    	
        if (userRepository.count() == 0) {

            User admin = new User();
            admin.setName("Sourav Singh");
            admin.setEmail("sourav@gmail.com");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setAbout("Admin User");
            admin.setRoles(roles);
                    
            this.userRepository.save(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }

}