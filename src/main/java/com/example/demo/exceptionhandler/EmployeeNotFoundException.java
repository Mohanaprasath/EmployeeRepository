package com.example.demo.exceptionhandler;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends RuntimeException {

	private int employeeId;

	private String errorCode;

	private String errorMessage;

	public EmployeeNotFoundException(ErrorCode errorCode, int employeeId) {

		this.employeeId = employeeId;

		this.errorCode = errorCode.getErrorCode();

		this.errorMessage = errorCode.getErrorMessage();
	}

}
