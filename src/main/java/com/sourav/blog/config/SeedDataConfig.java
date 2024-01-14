package com.sourav.blog.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.sourav.blog.entities.Role;
import com.sourav.blog.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
    	
    	if(roleRepository.count() == 0) {
    		try {
    			Role adminRole = new Role();
    			adminRole.setId(AppConstants.ADMIN_USER);
    			adminRole.setName("ROLE_ADMIN");
    			
    			Role userRole = new Role();
    			userRole.setId(AppConstants.NORMAL_USER);
    			userRole.setName("ROLE_NORMAL");
    			
    			Role managerRole = new Role();
    			managerRole.setId(AppConstants.MANAGER_USER);
    			managerRole.setName("ROLE_MANAGER");
    			
    			List<Role> roles = List.of(adminRole, userRole, managerRole);
    			this.roleRepository.saveAll(roles);
    			log.debug("created Roles - {}", adminRole, userRole, managerRole);
    		}
	    	catch (Exception e) {
	    		log.debug("Error while creating Roles - {}");
			}
    	}
    }
}