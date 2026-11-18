package com.food.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Order_Items")
public class OrderItem {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "ORDER_ID", nullable = false)
	 private FoodOrder order;
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "FOOD_ID", nullable = false)
	 private Food food;
	 
	 @Column(nullable=false)
	 private Integer quantity;
	 
	 @Column(name = "UNIT_PRICE", nullable = false, precision = 10, scale = 2)
	 private BigDecimal unitPrice;
	 
	 public OrderItem()
	 {
		 
	 }
	 
	 public OrderItem(FoodOrder order, Food food,
             Integer quantity, BigDecimal unitPrice) {
		 this.order = order;
		 this.food = food;
		 this.quantity = quantity;
		 this.unitPrice = unitPrice;
		 
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

	 public Food getFood() {
		 return food;
	 }

	 public void setFood(Food food) {
		 this.food = food;
	 }

	 public Integer getQuantity() {
		 return quantity;
	 }

	 public void setQuantity(Integer quantity) {
		 this.quantity = quantity;
	 }

	 public BigDecimal getUnitPrice() {
		 return unitPrice;
	 }

	 public void setUnitPrice(BigDecimal unitPrice) {
		 this.unitPrice = unitPrice;
	 }
	 public BigDecimal getSubtotal() {
	        return unitPrice.multiply(BigDecimal.valueOf(quantity));
	    }
	 
	}
	
