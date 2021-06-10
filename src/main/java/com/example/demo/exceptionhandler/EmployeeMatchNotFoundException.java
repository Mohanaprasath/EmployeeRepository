package com.example.demo.exceptionhandler;

import lombok.Getter;

@Getter
public class EmployeeMatchNotFoundException extends RuntimeException {

	private int employeeId;

	private String employeeName;

	private String errorCode;

	private String errorMessage;

	public EmployeeMatchNotFoundException(ErrorCode errorCode, int employeeId, String employeeName) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.errorCode = errorCode.getErrorCode();
		this.errorMessage = errorCode.getErrorMessage();
	}

}
