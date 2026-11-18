package com.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.dto.PaymentRequestDTO;
import com.food.dto.PaymentResponseDTO;
import com.food.dto.PaymentStatusRequestDTO;
import com.food.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class PaymentController {
	
	@Autowired
	public PaymentService paymentService;
	
	//create payment
	@PostMapping("/orders/{orderId}/payments")
	public ResponseEntity<PaymentResponseDTO> createPayment(@PathVariable Long orderId, 
			@Valid @RequestBody PaymentRequestDTO request,Authentication authentication)
	{
		
		String email=authentication.getName();
		boolean isAdmin=authentication.getAuthorities().stream()
														.anyMatch(authority->authority.getAuthority()
														.equals("ROLE_ADMIN"));
		
		PaymentResponseDTO response=paymentService.createPayment(orderId, request, email, isAdmin);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	//get payment by payment id
	@GetMapping("/payments/{paymentId}")
	public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long paymentId, Authentication authentication)
	{
		String email=authentication.getName();
		boolean isAdmin=authentication.getAuthorities().stream()
				.anyMatch(authority->authority.getAuthority()
				.equals("ROLE_ADMIN"));
		
		return ResponseEntity.ok(paymentService.getPaymentById(paymentId, email, isAdmin));
		
	}
	//Get payment by order id
	@GetMapping("/orders/{orderId}/payments")
	public ResponseEntity<PaymentResponseDTO> getPaymentByOrderId(@PathVariable Long orderId,Authentication authentication)
	{
		String email=authentication.getName();
		boolean isAdmin=authentication.getAuthorities().stream()
				.anyMatch(authority->authority.getAuthority()
				.equals("ROLE_ADMIN"));
		
		return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId, email, isAdmin));
	}
	
	//Demo of result of payment Success
	@PatchMapping("/payments/{paymentId}/demo-result")
	public ResponseEntity<PaymentResponseDTO> simulatedPaymentResult(@PathVariable Long paymentId, 
			@Valid @RequestBody PaymentStatusRequestDTO request,Authentication authentication)
	{
		String email=authentication.getName();
		boolean isAdmin=authentication.getAuthorities().stream()
				.anyMatch(authority->authority.getAuthority()
				.equals("ROLE_ADMIN"));
		
		 PaymentResponseDTO response =
		            paymentService.simulatePaymentResult(
		                    paymentId,
		                    request.getStatus(),
		                    email,
		                    isAdmin
		            );
		return ResponseEntity.ok(response);
	}

}
