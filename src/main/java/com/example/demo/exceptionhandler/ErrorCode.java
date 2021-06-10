package com.example.demo.exceptionhandler;

public enum ErrorCode {

	EMPLOYEE_NOT_FOUND("E001", "Employee not found"),

	NO_EMPLOYEE_FOUND("E002", "No employee found"),

	EMPLOYEE_MATCH_NOT_FOUND("E003", "Employee for this id and name not found");

	private String errorCode;

	private String errorMessage;

	ErrorCode(String errorCode, String errorMessage) {

		this.errorCode = errorCode;

		this.errorMessage = errorMessage;

	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
