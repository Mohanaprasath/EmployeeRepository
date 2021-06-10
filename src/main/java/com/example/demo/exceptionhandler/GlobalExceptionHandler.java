package com.example.demo.exceptionhandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("code", ex.getErrorCode());
		body.put("message", ex.getErrorMessage());
		body.put("empid", ex.getEmployeeId());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(NoEmployeeFoundException.class)
	public ResponseEntity<Object> handleNoEmployeeFoundException(NoEmployeeFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("code", ex.getErrorCode());
		body.put("message", ex.getErrorMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(EmployeeMatchNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeMatchNotFoundException(EmployeeMatchNotFoundException ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("code", ex.getErrorCode());
		body.put("message", ex.getErrorMessage());
		body.put("id", ex.getEmployeeId());
		body.put("name", ex.getEmployeeName());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		return new ResponseEntity(details, HttpStatus.BAD_REQUEST);
	}

}
