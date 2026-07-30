package com.example.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.Dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
	        ResourceNotFoundException exception) {

	    ErrorResponse response = new ErrorResponse(
	            LocalDateTime.now(),404,exception.getMessage(),null);
	           
	    return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<?> handleValidationException(
	         MethodArgumentNotValidException exception) {

	     Map<String, String> errors = new HashMap<>();

	     exception.getBindingResult()
	             .getFieldErrors()
	             .forEach(error -> {errors.put( error.getField(),error.getDefaultMessage());
	             });
	     ErrorResponse response = new ErrorResponse(LocalDateTime.now(),400,"Validation Failed", errors);
	 	    	   return new ResponseEntity<>(
	    	        response,
	    	        HttpStatus.BAD_REQUEST);
	}

	 @ExceptionHandler(DuplicateResourceException.class)
	 public ResponseEntity<ErrorResponse> handleDuplicateResource(
	         DuplicateResourceException exception) {

	     ErrorResponse response = new ErrorResponse(LocalDateTime.now(),409,exception.getMessage(),null);
	     return new ResponseEntity<>(response,HttpStatus.CONFLICT);
	 }
	 
}