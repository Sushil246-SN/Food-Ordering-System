package com.food.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.food.entity.AppUser;
import com.food.entity.Role;
import com.food.repository.AppUserRepository;

@Configuration
public class AdminSetup {

	//@Bean
	public CommandLineRunner createAdmin(AppUserRepository appUserRepository,PasswordEncoder passwordEncoder)
	{
		
		return args ->{
			
			String email="";
			
			if(!appUserRepository.existsByEmail(email))
			{
				AppUser admin=new AppUser();
				
				admin.setEmail(email);
				admin.setPassword(passwordEncoder.encode(""));
				admin.setRole(Role.ADMIN);
				
				appUserRepository.save(admin);
				
				System.out.println("Admin created");	
			}
			else
			{
				System.out.println("admin already exist");
			}
		};
	}

		
}
