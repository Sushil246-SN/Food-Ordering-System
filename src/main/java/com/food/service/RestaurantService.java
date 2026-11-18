package com.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.entity.Restaurant;
import com.food.exception.RestaurantNotFoundException;
import com.food.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository repository;
	
	public Restaurant createRestaurant(Restaurant restaurant)
	{
		return repository.save(restaurant);
	}
	
	public List<Restaurant> getAllRestaurant()
	{
		return repository.findAll();
	}
	
	public Restaurant getRestaurantById(Long id)
	{
		return repository.findById(id).orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found with this id:" + id));
	}
	
	public Restaurant updateRestaurant(Long id, Restaurant restaurant)
	{
		Restaurant existing=repository.findById(id).orElseThrow(()->new RestaurantNotFoundException("restaurant is not found with this:"+ id));
		
		existing.setName(restaurant.getName());
		existing.setAddress(restaurant.getAddress());
		
		return repository.save(existing);
	}
	public void deleteRestaurant(Long id)
	{
		Restaurant restaurant=repository.findById(id).orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found with id:" + id));
		
		repository.delete(restaurant);
	}
}
