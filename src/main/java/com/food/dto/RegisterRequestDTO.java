package com.food.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
	private String name;
    
    @NotBlank(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Size(max=100)
    private String email;
    
    @Pattern(
    		regexp="^[0-9]{10,15}$",
    		message="Phone must contain 10 to 15 digits")
    public String phone;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 72,
    message = "Password must contain 8 to 72 characters")

    private String password;
    
    public RegisterRequestDTO()
    {
    	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
