package com.food.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.food.entity.AppUser;
import com.food.repository.AppUserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private AppUserRepository appUserRepository;
	
	public CustomUserDetailsService(AppUserRepository appUserRepository)
	{
		this.appUserRepository=appUserRepository;
	}
	
	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException
	{
		AppUser appUser=appUserRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found with email: " + email));
		
		return User.builder()
				.username(appUser.getEmail())
				.password(appUser.getPassword())
				.roles(appUser.getRole().name())
				.build();
	}

}
