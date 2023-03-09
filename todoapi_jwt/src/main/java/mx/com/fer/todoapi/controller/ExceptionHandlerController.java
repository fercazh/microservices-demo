package mx.com.fer.todoapi.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import mx.com.fer.todoapi.exception.MessageError;
import mx.com.fer.todoapi.exception.ToDoException;
import mx.com.fer.todoapi.exception.UserException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler({
		UserException.class
		, ToDoException.class
		})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public MessageError genericException(Exception ex) {
		return MessageError.builder()
				.status("ERROR")
				.date(LocalDateTime.now())
				.message(ex.getMessage())
				.build();
	}

//	@ExceptionHandler({
//		BadCredentialsException.class
//		, UsernameNotFoundException.class
//		})
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public MessageError unauthorizedException(Exception ex) {
//		return MessageError.builder()
//				.status("ERROR")
//				.date(LocalDateTime.now())
//				.message(ex.getMessage())
//				.build();
//	}
	
	@ExceptionHandler({
		MethodArgumentNotValidException.class
		})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public MessageError unauthorizedException(MethodArgumentNotValidException ex) {
		Map<String, String> errMap = new HashMap<>();
		ex.getBindingResult()
		.getFieldErrors().stream()
		.forEach(fielderror -> errMap.put(fielderror.getField(), fielderror.getDefaultMessage()));
		
		return MessageError.builder()
				.status("ERROR")
				.date(LocalDateTime.now())
				.message("Validation failed.")
				.errors(errMap)
				.build();
	}
}
