package com.example.demo.service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.EmployeeData;
import com.example.demo.dto.EmployeeDataDTO;
import com.example.demo.exceptionhandler.EmployeeMatchNotFoundException;
import com.example.demo.exceptionhandler.EmployeeNotFoundException;
import com.example.demo.exceptionhandler.ErrorCode;
import com.example.demo.exceptionhandler.NoEmployeeFoundException;
import com.example.demo.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepo;

	@Autowired
	ModelMapper modelMapper;

	public EmployeeDataDTO addEmployee(EmployeeData data) {

		employeeRepo.save(data);

		return convertToDTO(data);

	}

	public EmployeeDataDTO saveorUpdateEmployee(EmployeeData data, int id) {

		employeeRepo.save(data);

		return convertToDTO(data);
	}

	public List<EmployeeDataDTO> getAllEmployees() {

		List<EmployeeData> data = employeeRepo.findAll();

		if (data.isEmpty()) {
			throw new NoEmployeeFoundException(ErrorCode.NO_EMPLOYEE_FOUND);
		}

		return convertToDTOList(data);

	}

	public EmployeeDataDTO deleteEmployee(int id) {

		EmployeeData data = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, id));

		employeeRepo.deleteById(id);

		return convertToDTO(data);

	}

	public EmployeeDataDTO getEmployee(int id) {

		EmployeeData raw = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, id));

		System.out.println(raw);

		return convertToDTO(raw);

	}

	public String deleteAllEmployees() {

		employeeRepo.deleteAll();

		return "Employees are deleted";

	}

	public EmployeeDataDTO getEmployeeByIdAndName(int id, String name) {

		EmployeeData data = ((Optional<EmployeeData>) employeeRepo.findByIdAndName(id, name))
				.orElseThrow(() -> new EmployeeMatchNotFoundException(ErrorCode.EMPLOYEE_MATCH_NOT_FOUND, id, name));

		return convertToDTO(data);

	}

	public EmployeeDataDTO convertToDTO(EmployeeData data) {

		return modelMapper.map(data, EmployeeDataDTO.class);

	}

	public List<EmployeeDataDTO> convertToDTOList(List<EmployeeData> data) {

		List<EmployeeDataDTO> DtoList = Arrays.asList(modelMapper.map(data, EmployeeDataDTO[].class));

		return DtoList;
	}

}
