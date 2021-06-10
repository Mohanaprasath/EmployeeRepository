package com.example.demo.dto;

import lombok.Data;

@Data
public class EmployeeDataDTO {

	private int id;
	private String name;
	private int age;
	
	public EmployeeDataDTO(int id, String name, int age) {
		
		this.id=id;
		this.name=name;
		this.age=age;
		
	}

	public EmployeeDataDTO() {
		// TODO Auto-generated constructor stub
	}
	

}
