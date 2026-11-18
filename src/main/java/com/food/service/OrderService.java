package com.food.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.dto.OrderResponseDTO;
import com.food.entity.Customer;
import com.food.entity.FoodOrder;
import com.food.entity.OrderStatus;
import com.food.exception.CustomerNotFoundException;
import com.food.exception.OrderNotFoundException;
import com.food.repository.CustomerRepository;
import com.food.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	
	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	
	public OrderService(OrderRepository orderRepository,CustomerRepository customerRepository)
	{
		this.orderRepository=orderRepository;
		this.customerRepository=customerRepository;
	}
	
	public OrderResponseDTO createOrder(String email)
	{
		Customer customer=customerRepository.findByEmail(email).orElseThrow(()->new CustomerNotFoundException("customer not found with this id:" + email));
		
		FoodOrder order=new FoodOrder(customer, LocalDateTime.now(),OrderStatus.CREATE,BigDecimal.ZERO);
		
		FoodOrder saveOrder=orderRepository.save(order);
		
		return convertToResponse(saveOrder);
	}
	
	@Transactional(readOnly=true)
	public List<OrderResponseDTO> getMyOrder(String email)
	{
		Customer customer=customerRepository.findByEmail(email).orElseThrow(()-> new CustomerNotFoundException("Customer not found with email: \" + email"));
		
		return orderRepository.findByCustomerIdOrderByOrderDateDesc(customer.getId())
								.stream()
								.map(this::convertToResponse)
								.toList();
	}
	@Transactional(readOnly=true)
	public OrderResponseDTO getOrderById(Long id,String email)
	{
		FoodOrder order=orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order not found with this id;"+ id));
		
		if(!order.getCustomer().getEmail().equalsIgnoreCase(email))
		{
			throw new OrderNotFoundException("Order not found with ID: " + id);
		}
		return convertToResponse(order);

	}
	
	//Admin 
	@Transactional(readOnly=true)
	public List<OrderResponseDTO> getAllOrders()
	{
		return orderRepository.findAll().stream()
										.map(this::convertToResponse)
										.toList();
	}
	@Transactional(readOnly=true)
	public OrderResponseDTO getOrderByIdforAdmin(Long id)
	{
		FoodOrder order=orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException( "Order not found with ID: " + id));
		
		return convertToResponse(order);
	}
	private OrderResponseDTO convertToResponse(FoodOrder order)
	{
		return new OrderResponseDTO(
				order.getId(),
				order.getCustomer().getId(),
				order.getCustomer().getName(),
				order.getOrderDate(),
				order.getStatus(),
				order.getTotalAmount());
	}
	
}
