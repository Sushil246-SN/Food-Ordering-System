package com.food.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.food.entity.PaymentMethod;
import com.food.entity.PaymentStatus;

public class PaymentResponseDTO {
	
	private Long paymentId;
	private Long orderId;
	private BigDecimal amount;
	private PaymentMethod paymentMethod;
	private PaymentStatus paymentStatus;
	private LocalDateTime paymentDate;
	
	
	public PaymentResponseDTO(
            Long paymentId,
            Long orderId,
            BigDecimal amount,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus,
            LocalDateTime paymentDate) {

        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }


	public Long getPaymentId() {
		return paymentId;
	}


	public Long getOrderId() {
		return orderId;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}


	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}


	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

}
