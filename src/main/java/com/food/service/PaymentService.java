package com.food.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.dto.PaymentRequestDTO;
import com.food.dto.PaymentResponseDTO;
import com.food.entity.FoodOrder;
import com.food.entity.OrderStatus;
import com.food.entity.Payment;
import com.food.entity.PaymentStatus;
import com.food.exception.OrderNotFoundException;
import com.food.exception.PaymentAlreadyExistsException;
import com.food.exception.PaymentNotFoundException;
import com.food.repository.OrderRepository;
import com.food.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {
	
	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;
	
	public PaymentService(PaymentRepository paymentRepository,OrderRepository orderRepository)
	{
		this.paymentRepository=paymentRepository;
		this.orderRepository=orderRepository;
	}
	//create payment
	public PaymentResponseDTO createPayment(Long orderId, PaymentRequestDTO request,String email, boolean isAdmin)
	{
		FoodOrder order=orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order not found with id: " + orderId));
		
		checkOrderOwnership(order, email, isAdmin);
		
		if(order.getStatus()==OrderStatus.CANCELLED)
		{
			throw new IllegalArgumentException("Payment cannot be done for a cancelled order");
					
		}
		
		if(order.getStatus()!=OrderStatus.CONFIRMED)
		{
			throw new IllegalArgumentException(
			        "Payment can only be created for confirmed orders");
		}
		
		if(paymentRepository.existsByOrder_Id(orderId))
		{
			throw new PaymentAlreadyExistsException("Payment already exists for order id: " + orderId);
		}
		
		BigDecimal amount=order.getTotalAmount();
		
		
		
		if(amount==null || amount.compareTo(BigDecimal.ZERO)<=0)
		{
			throw new IllegalArgumentException("Cannot create payment for an order with zero or invalid amount");
		}
		
		Payment payment=new Payment(order, amount,request.getPaymentMethod(),PaymentStatus.PENDING,null);
		
		Payment savePayment=paymentRepository.save(payment);
		
		return convertToResponse(savePayment);
	}
	
	//Get payment using payment_id
	@Transactional(readOnly=true)
	public PaymentResponseDTO getPaymentById(Long paymentId,String email,boolean isAdmin)
	{
		Payment payment=paymentRepository.findById(paymentId).orElseThrow(()->new PaymentNotFoundException("Payment not found with id: " + paymentId));
		
		checkOrderOwnership(payment.getOrder(),email,isAdmin);
		
		return convertToResponse(payment);
	}
	//Get payment using order_id
	@Transactional(readOnly = true)
    public PaymentResponseDTO getPaymentByOrderId(Long orderId,String email, boolean isAdmin) {

		FoodOrder order=orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException(
                "Order not found with ID: " + orderId));
		
		 checkOrderOwnership(order, email, isAdmin);
		 
        Payment payment = paymentRepository.findByOrder_Id(orderId)
                .orElseThrow(() ->
                        new PaymentNotFoundException(
                                "Payment not found for order id: " + orderId));
       

        return convertToResponse(payment);
    }
	
	
	// Demo Only for Payment simulation.
	
	public PaymentResponseDTO simulatePaymentResult(Long paymentId,PaymentStatus newStatus,String email,boolean isAdmin)
	{
		Payment payment=paymentRepository.findById(paymentId).orElseThrow(()-> new PaymentNotFoundException( "Payment not found with id: " + paymentId));
		
		checkOrderOwnership(payment.getOrder(),email, isAdmin);
		
		if(payment.getPaymentStatus()!=PaymentStatus.PENDING)
		{
			throw new IllegalArgumentException(
                    "Only pending payments can receive a result");
		}
		if(newStatus !=PaymentStatus.SUCCESS && newStatus != PaymentStatus.FAILED)
		{
			throw new IllegalArgumentException(
                    "Payment result must be SUCCESS or FAILED");
		}
		payment.setPaymentStatus(newStatus);
		
		if(newStatus==PaymentStatus.SUCCESS)
		{
			payment.setPaymentDate(LocalDateTime.now());
		}
		
		return convertToResponse(payment);
	}
	private void checkOrderOwnership(FoodOrder order,String email,boolean isAdmin)
	{
		if(isAdmin)
		{
			return;
		}
		if(order.getCustomer().getEmail().equalsIgnoreCase(email))
		{
		 throw new OrderNotFoundException(
                 "Order not found with ID: " + order.getId());
		}
	}
	private PaymentResponseDTO convertToResponse(Payment payment)
	{
		return new  PaymentResponseDTO(
				payment.getId(),
				payment.getOrder().getId(),
				payment.getAmount(),
				payment.getPaymentMethod(),
				payment.getPaymentStatus(),
				payment.getPaymentDate());
	}
	
}
