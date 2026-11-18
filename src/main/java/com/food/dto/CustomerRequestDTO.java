package com.food.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerRequestDTO {

	@NotBlank(message="Customer name is required")
	@Size(max=100, message="Name cannot exceed 100 charactors")
	private String name;
	
	@NotBlank(message="Customer email is required")
	@Email(message="Please provide a valid email")
	@Size(max=100, message="Name cannot exceed 100 charactors")
	private String email;
	
	@Pattern
	(regexp="^[0-9]{10,15}$",
	message="Phone number must contain 10 to 15 digits")
	private String phone;
	
	public CustomerRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
	
}
