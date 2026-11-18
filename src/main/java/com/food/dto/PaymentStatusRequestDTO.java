package com.food.dto;

import com.food.entity.PaymentStatus;

import jakarta.validation.constraints.NotNull;

public class PaymentStatusRequestDTO {
	
	@NotNull(message="Payment Status is required")
	private PaymentStatus status;
	
	public PaymentStatusRequestDTO()
	{
		
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	

}
