package com.example.demo.exceptionhandler;

import lombok.Getter;

@Getter
public class NoEmployeeFoundException extends RuntimeException {

	private String errorCode;

	private String errorMessage;

	public NoEmployeeFoundException(ErrorCode errorCode) {

		this.errorCode = errorCode.getErrorCode();

		this.errorMessage = errorCode.getErrorMessage();
	}

}
