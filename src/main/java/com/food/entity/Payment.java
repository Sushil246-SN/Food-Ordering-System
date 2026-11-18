package com.food.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Payments")
public class Payment {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @OneToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "ORDER_ID", nullable = false, unique = true)
	 private FoodOrder order;
	 
	 @Column(nullable = false, precision = 10, scale = 2)
	 private BigDecimal amount;
	 
	 @Enumerated(EnumType.STRING)
	 @Column(nullable = false)
	  private PaymentMethod paymentMethod;
	 
	 @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private PaymentStatus paymentStatus;
	 
	 private LocalDateTime paymentDate;
	 
	 public Payment()
	 {
		 
	 }
	 
	 
	 public Payment(FoodOrder order, BigDecimal amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus,LocalDateTime paymentDate)
	 {
		 this.order=order;
		 this.amount=amount;
		 this.paymentMethod=paymentMethod;
		 this.paymentStatus=paymentStatus;
		 this.paymentDate=paymentDate;
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public FoodOrder getOrder() {
		 return order;
	 }

	 public void setOrder(FoodOrder order) {
		 this.order = order;
	 }

	 public BigDecimal getAmount() {
		 return amount;
	 }

	 public void setAmount(BigDecimal amount) {
		 this.amount = amount;
	 }

	 public PaymentMethod getPaymentMethod() {
		 return paymentMethod;
	 }

	 public void setPaymentMethod(PaymentMethod paymentMethod) {
		 this.paymentMethod = paymentMethod;
	 }

	 public PaymentStatus getPaymentStatus() {
		 return paymentStatus;
	 }

	 public void setPaymentStatus(PaymentStatus paymentStatus) {
		 this.paymentStatus = paymentStatus;
	 }

	 public LocalDateTime getPaymentDate() {
		 return paymentDate;
	 }

	 public void setPaymentDate(LocalDateTime paymentDate) {
		 this.paymentDate = paymentDate;
	 }
	 
	 
}
