package com.food.dto;

import com.food.entity.OrderStatus;

public class OrderStatusResponseDTO {
	
	private Long orderId;
	private OrderStatus status;
	
	public OrderStatusResponseDTO(Long orderId, OrderStatus status)
	{
		this.orderId=orderId;
		this.status=status;			
	}

	public Long getOrderId() {
		return orderId;
	}

	public OrderStatus getStatus() {
		return status;
	}
	
}
