package com.example.demo.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "EmployeesData")
@Data
public class EmployeeData {

	

	@Id
	@GeneratedValue
	private long id;

	@NotEmpty(message = "name must not be empty")
	private String name;

	private int age;

	private String gender;

	private String designation;

	private String department;

	public EmployeeData(int id, String name, int age, String gender, String designation, String department) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.designation = designation;
		this.department = department;
	}

	public EmployeeData() {
		// TODO Auto-generated constructor stub
	}

	
	
	

}
