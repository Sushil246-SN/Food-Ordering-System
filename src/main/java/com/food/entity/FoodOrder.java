package com.food.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Food_Orders")
public class FoodOrder {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;
	
	@Column(name="Order_Date", nullable=false)
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=20)
	private OrderStatus status;	
	
	@Column(name="Total_Amount",precision=10, scale=2)
	private BigDecimal totalAmount;
	
	@OneToMany(
			mappedBy="order",
			cascade= CascadeType.ALL,
			orphanRemoval=true)
	private List<OrderItem> orderItems=new ArrayList<>();
	
	public FoodOrder()
	{
		
	}

	public FoodOrder( Customer customer, LocalDateTime orderDate, OrderStatus status,
			BigDecimal totalAmount) {
		this.customer = customer;
		this.orderDate = orderDate;
		this.status = status;
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<OrderItem> getOrderItems() {
	    return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
	    this.orderItems = orderItems;
	}
	
	
	
}
