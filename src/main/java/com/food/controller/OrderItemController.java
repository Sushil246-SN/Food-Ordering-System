package com.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.dto.OrderItemRequestDTO;
import com.food.dto.OrderItemResponseDTO;
import com.food.service.OrderItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders/{orderId}/items")
public class OrderItemController {

	@Autowired
	public OrderItemService orderItemService;
	
	@PostMapping
	public ResponseEntity<OrderItemResponseDTO> addItem(@PathVariable Long orderId, @Valid @RequestBody OrderItemRequestDTO request,Authentication  authentication)
	{
		String email=authentication.getName();
		boolean isAdmin=authentication.getAuthorities().stream()
														.anyMatch(authority->authority.getAuthority().equals("ROLE_ADMIN"));
		OrderItemResponseDTO response=orderItemService.addItemToOrder(orderId, request, email, isAdmin);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping
	public ResponseEntity<List<OrderItemResponseDTO>> getItems(@PathVariable Long orderId,Authentication authentication)
	{
		String email=authentication.getName();
		boolean isAdmin=authentication.getAuthorities().stream().anyMatch(authority->authority.getAuthority().equals("ROLE_ADMIN"));
		
		
		return ResponseEntity.ok(orderItemService.getItemByOrder(orderId, email, isAdmin));
	}
}
