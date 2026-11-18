package com.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.dto.CustomerRequestDTO;
import com.food.dto.CustomerResponseDTO;
import com.food.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	public CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid  CustomerRequestDTO request)
	{
		
		CustomerResponseDTO response= customerService.createCustomer(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@GetMapping
	public ResponseEntity<List<CustomerResponseDTO>> getAllCustomer() 
	{
		return ResponseEntity.ok(customerService.getAllCustomer());
	}
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id)
	{
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id)
	{
		customerService.deleteCustomer(id);
	
		return ResponseEntity.noContent().build();
	}
}
