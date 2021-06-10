package com.example.demo.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeData;
import com.example.demo.dto.EmployeeDataDTO;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * Create new employee's data in DB
	 * 
	 * @return Employee's data
	 */
	@PostMapping(consumes = { "application/json" })
	public EmployeeDataDTO addEmployee(@Valid @RequestBody EmployeeData data) {

		return employeeService.addEmployee(data);
	}

	/**
	 * Save or update the particular employee's data in DB
	 * 
	 * @return Employee's data
	 */
	@PutMapping(value = "/{id}", consumes = { "application/json" })
	public EmployeeDataDTO saveorUpdateEmployee(@Valid @RequestBody EmployeeData data, @PathVariable int id) {

		return employeeService.saveorUpdateEmployee(data, id);

	}

	/**
	 * Get all employees from DB
	 * 
	 * @return list of employees
	 */
	@GetMapping
	public List<EmployeeDataDTO> getEmployees() {

		return employeeService.getAllEmployees();

	}

	/**
	 * Delete particular employee's data from DB
	 * 
	 * @return Employee's data
	 */
	@DeleteMapping(value = "/{id}")
	public EmployeeDataDTO deleteEmployee(@PathVariable int id) {

		return employeeService.deleteEmployee(id);

	}

	/**
	 * Delete all employees data from DB
	 * 
	 * @return String
	 */
	@DeleteMapping
	public String deleteAllEmployees() {

		return employeeService.deleteAllEmployees();

	}

	/**
	 * Get particular employee's data based on Id and Name from DB
	 * 
	 * @return Employee's data
	 */
	@GetMapping(value = "/getEmployee/{id}")
	public EmployeeDataDTO getEmployeeByIdAndName(@PathVariable("id") int id, @RequestParam("name") String name) {

		return employeeService.getEmployeeByIdAndName(id, name);

	}

	/**
	 * Get particular employee's data based on Id from DB
	 * 
	 * @return Employee's data
	 */
	@GetMapping(value = "/{id}")
	public EmployeeDataDTO getEmployee(@PathVariable("id") int id) {

		return employeeService.getEmployee(id);

	}

}
