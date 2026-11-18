package com.food.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food.dto.CustomerResponseDTO;
import com.food.dto.RegisterRequestDTO;
import com.food.entity.AppUser;
import com.food.entity.Customer;
import com.food.entity.Role;
import com.food.exception.DuplicateCustomerEmailException;
import com.food.repository.AppUserRepository;
import com.food.repository.CustomerRepository;

@Service
public class AuthService {

	private final AppUserRepository  appUserRepository;
	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthService(AppUserRepository  appUserRepository,CustomerRepository customerRepository,PasswordEncoder passwordEncoder)
	{
		this.appUserRepository=appUserRepository;
		this.customerRepository=customerRepository;
		this.passwordEncoder=passwordEncoder;
	}
	public CustomerResponseDTO register(RegisterRequestDTO request)
	{
		String email=request.getEmail().trim().toLowerCase();
		
		if(appUserRepository.existsByEmail(email))
		{
			throw new DuplicateCustomerEmailException("Email is already registered");
		}
		
		Customer customer=customerRepository.findByEmail(email).orElseGet(()->{
			
			Customer newCustomer=new Customer();
			newCustomer.setEmail(email);
			newCustomer.setName(request.getName());
			newCustomer.setPhone(request.getPhone());
			
			return customerRepository.save(newCustomer);
		
		});
		
		String encoderPassword=passwordEncoder.encode(request.getPassword());
		
		AppUser appUser=new AppUser();
		
		appUser.setEmail(email);
		appUser.setPassword(encoderPassword);
		appUser.setRole(Role.CUSTOMER);
		appUser.setCustomer(customer);
		
		appUserRepository.save(appUser);
		
		return new CustomerResponseDTO(customer.getId(),customer.getName(),customer.getEmail(),customer.getPhone());

	}
	
}
