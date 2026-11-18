package com.food.dto;

import com.food.entity.OrderStatus;

import jakarta.validation.constraints.NotNull;

public class OrderStatusRequestDTO {

	@NotNull(message="Order status is required")
	private OrderStatus status;
	
	public OrderStatusRequestDTO()
	{
		
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
}
