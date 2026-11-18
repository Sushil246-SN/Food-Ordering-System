package com.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.dto.OrderStatusRequestDTO;
import com.food.dto.OrderStatusResponseDTO;
import com.food.entity.FoodOrder;
import com.food.entity.OrderStatus;
import com.food.exception.InvalidOrderStatusException;
import com.food.exception.OrderNotFoundException;
import com.food.repository.OrderRepository;

@Service
@Transactional
public class OrderStatusService {

	@Autowired
	public OrderRepository orderRepository;
	
	public OrderStatusResponseDTO updateOrderStatus(Long orderId, OrderStatusRequestDTO request)
	{
		FoodOrder order=orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order not found with id: " + orderId));
		
		OrderStatus currentStatus=order.getStatus();
		OrderStatus newStatus=request.getStatus();
		
		if(!isValidTransition(currentStatus,newStatus))
		{
			throw new InvalidOrderStatusException("Cannot change order status from "
                    + currentStatus + " to " + newStatus);
		}
			order.setStatus(newStatus);
			
			return new OrderStatusResponseDTO(order.getId(),order.getStatus());
	}
	
	private boolean isValidTransition(OrderStatus currentStatus, OrderStatus newStatus)
	{
		if (currentStatus==null || newStatus==null)
		{
			return false;
		}
		switch(currentStatus)
		{
		case CREATE:
			return newStatus==OrderStatus.CONFIRMED
					|| newStatus==OrderStatus.CANCELLED;
		
		case CONFIRMED:
			return newStatus==OrderStatus.PREPARING
					|| newStatus==OrderStatus.CANCELLED;
			
		case PREPARING:
			return newStatus==OrderStatus.OUT_FOR_DELIVERY;
			
		case OUT_FOR_DELIVERY:
			
			return newStatus==OrderStatus.DELIVERED;
			
		case DELIVERED:
		case CANCELLED:
			
			default:
				return false;
		}	
	}
}
