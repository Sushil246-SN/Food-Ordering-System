package com.food.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.food.dto.FoodRequestDTO;
import com.food.dto.FoodResponseDTO;
import com.food.entity.Food;
import com.food.entity.Restaurant;
import com.food.exception.FoodNotFoundException;
import com.food.exception.RestaurantNotFoundException;
import com.food.repository.FoodRepository;
import com.food.repository.RestaurantRepository;

@Service
@Transactional
public class FoodService {
	
	private final FoodRepository foodRepository;
	private final RestaurantRepository restaurantRepository;
	
	public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository)
	{
		this.foodRepository=foodRepository;
		this.restaurantRepository=restaurantRepository;
	}
	
public FoodResponseDTO createFood(Long RestaurantId, FoodRequestDTO request)
{
	Restaurant restaurant=restaurantRepository.findById(RestaurantId).orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found with this id:"+ RestaurantId));
	
	Food food=new Food(request.getName(),request.getPrice(),restaurant);
	
	Food saveFood=foodRepository.save(food);
	
	return convertToResponse(saveFood);
}

@Transactional(readOnly=true)
public List<FoodResponseDTO> getAllFoods()
{
return foodRepository.findAll()
					.stream()
					.map(this::convertToResponse)
					.toList();
}
@Transactional(readOnly=true)
public FoodResponseDTO getFoodById(Long id)
{
		Food food=foodRepository.findById(id).orElseThrow(()->new FoodNotFoundException("Food not found with this id: " +id));
		
		return convertToResponse(food);
}

public List<FoodResponseDTO> getFoodRestaurant(Long restaurantId)
{
	if(!restaurantRepository.existsById(restaurantId))
	{
		throw new RestaurantNotFoundException("restaurant not found with this:"+ restaurantId);
	}
	
	return foodRepository.findByRestaurant_Id(restaurantId).stream()
															.map(this::convertToResponse)
															.toList();
}


public void deleteFood(Long id)
{
	Food food=foodRepository.findById(id).orElseThrow(()-> new FoodNotFoundException("food not found with this: " +id));
	
	foodRepository.delete(food);
}

private FoodResponseDTO convertToResponse(Food food)
{
	return new FoodResponseDTO(food.getId(),
			food.getName(),
			food.getPrice(),
			food.getRestaurant().getId(),
			food.getRestaurant().getName());
}
	
}
