
package com.food.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FoodRequestDTO {

    @NotBlank(message = "Food name is required")
    @Size(max = 100, message = "Food name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Food price is required")
    @DecimalMin(value = "0.01", message = "Food price must be greater than zero")
    private BigDecimal price;

    public FoodRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
