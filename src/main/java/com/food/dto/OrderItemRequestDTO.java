package com.food.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemRequestDTO {

	@NotNull(message="Food id is required")
	@Positive(message="Food id must be greater than zero")
	private Long foodId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
	private Integer quantity;
    
    public OrderItemRequestDTO()
    {
    	
    }

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
}
