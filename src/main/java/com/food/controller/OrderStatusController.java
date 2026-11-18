package com.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.dto.OrderStatusRequestDTO;
import com.food.dto.OrderStatusResponseDTO;
import com.food.service.OrderStatusService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderStatusController {
	
	@Autowired
	public OrderStatusService orderStatusService;

	@PatchMapping("/{orderId}/status")
	public ResponseEntity<OrderStatusResponseDTO> updateOrderStatus(@PathVariable Long orderId,@Valid @RequestBody OrderStatusRequestDTO request)
	{
		OrderStatusResponseDTO response=orderStatusService.updateOrderStatus(orderId, request);
	
		return ResponseEntity.ok(response);
	}
}
