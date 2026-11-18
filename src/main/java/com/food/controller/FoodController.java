package com.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.food.dto.FoodRequestDTO;
import com.food.dto.FoodResponseDTO;
import com.food.service.FoodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class FoodController {
	
	@Autowired
	public FoodService foodService;
	
	@PostMapping("restaurants/{restaurantId}/foods")
	@ResponseStatus(HttpStatus.CREATED)
	public FoodResponseDTO createFood(@PathVariable Long restaurantId,@Valid @RequestBody FoodRequestDTO request)
	{
		return foodService.createFood(restaurantId, request);
	}
	
	@GetMapping("/foods")
	public List<FoodResponseDTO> getAllFood()
	{
		return foodService.getAllFoods();
	}
	
	@GetMapping("foods/{id}")
	public FoodResponseDTO getFoodById(@PathVariable Long id)
	{
		return foodService.getFoodById(id);
	}
	
	@GetMapping("restaurants/{restaurantId}/foods")
	public List<FoodResponseDTO> getFoodsByRestaurantId(@PathVariable Long restaurantId)
	{
		
		return foodService.getFoodRestaurant(restaurantId);
	}
	@DeleteMapping("/food/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFood(@PathVariable Long id)
	{
		foodService.deleteFood(id);
	}
}
