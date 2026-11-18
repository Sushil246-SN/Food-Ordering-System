package com.food.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.dto.CustomerResponseDTO;
import com.food.dto.RegisterRequestDTO;
import com.food.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;
	
	public AuthController(AuthService authService)
	{
		this.authService=authService;
	}
	@PostMapping("/register")
	public ResponseEntity<CustomerResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request)
	{
		CustomerResponseDTO response=authService.register(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
