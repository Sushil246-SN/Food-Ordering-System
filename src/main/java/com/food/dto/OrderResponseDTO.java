
package com.food.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.food.entity.OrderStatus;

public class OrderResponseDTO {

    private Long id;
    private Long customerId;
    private String customerName;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private BigDecimal totalAmount;

    public OrderResponseDTO(
            Long id,
            Long customerId,
            String customerName,
            LocalDateTime orderDate,
            OrderStatus status,
            BigDecimal totalAmount) {

        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
