package com.food.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.dto.CustomerRequestDTO;
import com.food.dto.CustomerResponseDTO;
import com.food.entity.Customer;
import com.food.exception.CustomerNotFoundException;
import com.food.exception.DuplicateCustomerEmailException;
import com.food.repository.CustomerRepository;



@Service
@Transactional
public class CustomerService {
	

	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository)
	{
		this.customerRepository=customerRepository;
		
	}
	
	
	public CustomerResponseDTO createCustomer(CustomerRequestDTO request)
	{
		if(customerRepository.existsByEmail(request.getEmail()))
		{
			throw new DuplicateCustomerEmailException("Customer already exist with email;" + request.getEmail());

		}
		Customer customer =new Customer(request.getName(),request.getEmail(),request.getPhone()
				);
		Customer saveCustomer=customerRepository.save(customer);
		
		return convertToResponse(saveCustomer);
	}
	
	@Transactional(readOnly=true)
	public List<CustomerResponseDTO> getAllCustomer()
	{
		return customerRepository.findAll()
								.stream()
								.map(this::convertToResponse)
								.toList();
	}
	@Transactional(readOnly=true)
	public CustomerResponseDTO getCustomerById(Long id)
	{
		Customer customer=customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("customer not found with this:"+ id));
		
		return convertToResponse(customer);
	}
	
	public void deleteCustomer(Long id)
	{
		Customer customer=customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("customer not found with this:"+ id));
		
		customerRepository.delete(customer);
	}
	
	private CustomerResponseDTO convertToResponse(Customer customer)
	{
		return new CustomerResponseDTO(
		customer.getId(),
		customer.getName(),
		customer.getEmail(),
		customer.getPhone());
		
	}
	
}
