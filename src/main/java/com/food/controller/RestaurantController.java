package com.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.entity.Restaurant;
import com.food.service.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController{

	@Autowired
	public RestaurantService service;
	
	@PostMapping
	public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant)
	{
		return service.createRestaurant(restaurant);
	}
	
	@GetMapping
	public List<Restaurant> getAllRestaurant()
	{
		return service.getAllRestaurant();
	}
	
	@GetMapping("/{id}")
	public Restaurant getRestaurantById(@PathVariable Long id)
	{
		return service.getRestaurantById(id);
	}
	
	@PutMapping("/{id}")
	public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant)
	{
		return service.updateRestaurant(id, restaurant);
	}
	@DeleteMapping("/{id}")
	public void deleteRestaurant(@PathVariable Long id)
	{
		service.deleteRestaurant(id);
	}
}
