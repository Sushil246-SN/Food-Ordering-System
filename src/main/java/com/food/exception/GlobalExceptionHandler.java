package com.food.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RestaurantNotFoundException.class)
	public ResponseEntity<Map<String,String>> handelRestaurantNotFound(RestaurantNotFoundException exception)
	{
		Map<String, String> error=new HashMap<>();
		
		error.put("error", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
				
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handelerValidationError(MethodArgumentNotValidException exception)
	{
		Map<String, String> error=new HashMap<>();
		
		exception.getBindingResult()
				.getFieldErrors()
				.forEach(fielderror-> error.put(fielderror.getField(), fielderror.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(error);
		
	}
	
	@ExceptionHandler(FoodNotFoundException.class)
	public ResponseEntity<Map<String,String>> handlerFoodNotFound(FoodNotFoundException exception)
	{
		
		Map<String, String> error=new HashMap<>();
		
		error.put("error", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler(DuplicateCustomerEmailException.class)
	public ResponseEntity<Map<String, String >> handlerDuplicateCustomerEmail(DuplicateCustomerEmailException exception)
	{
		Map<String, String > error=new HashMap<>();
		error.put("error", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Map<String, String>> handlerCustomerNotFoundException(CustomerNotFoundException exception)
	{
		Map<String, String> error=new HashMap<>();
		error.put("error", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Map<String, String >> handlerOrderNotFound(OrderNotFoundException exception)
	{
		Map<String, String>	error=new HashMap<>();
		error.put("error", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(InvalidOrderStatusException.class)
	public ResponseEntity<Map<String, String >> handlerInvalidOrderStatus(InvalidOrderStatusException exception)
	{
		Map<String , String > error=new HashMap<>();
		error.put("error", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<Map<String, String>> handlerPaymentNotFound(PaymentNotFoundException exception)
	{
		Map<String,String> error=new HashMap<>();
		error.put("error", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(PaymentAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handlerPaymentNotFound(PaymentAlreadyExistsException exception)
	{
		Map<String,String> error=new HashMap<>();
		error.put("error", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handlerIllegalArgument(IllegalArgumentException exception)
	{
		Map<String,String> error=new HashMap<>();
		error.put("error", exception.getMessage());
		
		return ResponseEntity.badRequest().body(error);
	}
}
