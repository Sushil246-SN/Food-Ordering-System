package com.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.dto.OrderResponseDTO;
import com.food.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	public OrderService orderService;
	
	//Customer can create order for themselves
	@PostMapping
	public ResponseEntity<OrderResponseDTO> createOrder(Authentication autentication)
	{
		OrderResponseDTO response=orderService.createOrder(autentication.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	//Admin can viw all orders
	
	@GetMapping("/admin/all")
	public ResponseEntity<List<OrderResponseDTO>> getAllOrders()
	{
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<OrderResponseDTO> getOrderByIdForAdmin(@PathVariable Long id)
	{
		return ResponseEntity.ok(orderService.getOrderByIdforAdmin(id));
	}
	
	//Customer can see only their own orders
	@GetMapping("/my-orders")
	public ResponseEntity<List<OrderResponseDTO>> getMyOrder(Authentication authentication)
	{
		return ResponseEntity.ok(orderService.getMyOrder(authentication.getName()));
	}
	//Only Customer can see their own individual order
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id,Authentication authentication)
	{
		return ResponseEntity.ok(orderService.getOrderById(id,authentication.getName()));
	}
	
}
