package com.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.entity.FoodOrder;

@Repository
public interface OrderRepository extends JpaRepository<FoodOrder, Long> {

	List<FoodOrder> findByCustomerIdOrderByOrderDateDesc(Long customerId);
}
