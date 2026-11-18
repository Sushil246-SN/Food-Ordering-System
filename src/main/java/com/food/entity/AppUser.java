package com.food.entity;
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
@Table(name="App_User")
public class AppUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(nullable= false, unique=true, length=100)
	private String email;
	
	@Column(nullable =false)
	private String password;
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=20)
	private Role role;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Customer_Id", unique=true)
	private Customer customer;
	
	public AppUser()
	{
		
	}
	public AppUser(String email, String password,
            Role role, Customer customer) {
 this.email = email;
 this.password = password;
 this.role = role;
 this.customer = customer;

}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}