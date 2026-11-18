package com.food.dto;

import com.food.entity.PaymentMethod;

import jakarta.validation.constraints.NotNull;

public class PaymentRequestDTO {
	
	@NotNull(message="Payment method is required")
	private PaymentMethod paymentMethod;
	
	public PaymentRequestDTO()
	{
		
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	

}
