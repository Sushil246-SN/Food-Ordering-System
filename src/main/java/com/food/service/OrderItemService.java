package com.food.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.dto.OrderItemRequestDTO;
import com.food.dto.OrderItemResponseDTO;
import com.food.entity.Customer;
import com.food.entity.Food;
import com.food.entity.FoodOrder;
import com.food.entity.OrderItem;
import com.food.exception.CustomerNotFoundException;
import com.food.exception.FoodNotFoundException;
import com.food.exception.OrderNotFoundException;
import com.food.repository.CustomerRepository;
import com.food.repository.FoodRepository;
import com.food.repository.OrderItemRepository;
import com.food.repository.OrderRepository;

@Service
@Transactional
public class OrderItemService {

	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;
	private final FoodRepository foodRepository;
	private final CustomerRepository customerRepository;
	
	public OrderItemService(OrderItemRepository orderItemRepository,OrderRepository orderRepository,FoodRepository foodRepository,CustomerRepository customerRepository)
	{
		this.orderItemRepository=orderItemRepository;
		this.orderRepository=orderRepository;
		this.foodRepository=foodRepository;
		this.customerRepository=customerRepository;
	}
	
	public OrderItemResponseDTO addItemToOrder(Long orderId,OrderItemRequestDTO request ,String email,boolean isAdmin)
	{
		FoodOrder order=orderRepository.findById(orderId).
					orElseThrow(()->new OrderNotFoundException("Order not found with ID: " + orderId));
		
		checkOrderOwnership(order,email,isAdmin);
		
		Food food= foodRepository.findById(request.getFoodId())
								.orElseThrow(()-> new FoodNotFoundException("Food not found with ID: " + request.getFoodId()));
								
		BigDecimal currentPrice=food.getPrice();
		
		OrderItem item=new OrderItem(order,
				food,request.getQuantity(),
				currentPrice);
		
		OrderItem savedItem=orderItemRepository.save(item);
		
		order.getOrderItems().add(savedItem);
		
		BigDecimal subtotal=savedItem.getSubtotal();
		
		BigDecimal existingTotal=order.getTotalAmount()==null
				? BigDecimal.ZERO : order.getTotalAmount();
		
		order.setTotalAmount(existingTotal.add(subtotal));
		
		return convertToResponse(savedItem);
		
	}
	
	@Transactional(readOnly=true)
	public List<OrderItemResponseDTO> getItemByOrder(Long orderId,String email,boolean isAdmin)
	{
		
		FoodOrder order=orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException(
                					"Order not found with ID: " + orderId));
		
		checkOrderOwnership(order,email,isAdmin);
		
		return orderItemRepository.findByOrder_IdOrderByIdAsc(orderId)
									.stream()
									.map(this::convertToResponse)
									.toList();
	}
		private void checkOrderOwnership(FoodOrder order, String email,boolean isAdmin)
		{
			if(isAdmin)
			{
				return;
			}
			Customer customer=customerRepository.findByEmail(email).orElseThrow(()->
            						new CustomerNotFoundException( "Customer not found with email: " + email));
			
			if(!order.getCustomer().getId().equals(customer.getId()))
			{
				throw new OrderNotFoundException("Order not found with ID: " + order.getId());
			}
		}
	
	private OrderItemResponseDTO convertToResponse(OrderItem item)
	{
		return new OrderItemResponseDTO(
				item.getId(),
				item.getFood().getId(),
				item.getFood().getName(),
				item.getQuantity(),
				item.getUnitPrice(),
				item.getSubtotal());
		
		
	}

}
