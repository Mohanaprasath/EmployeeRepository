package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.EmployeeData;
import com.example.demo.dto.EmployeeDataDTO;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Mock
	EmployeeRepository repository;

	@Mock
	ModelMapper modelMapper;

	@InjectMocks
	EmployeeService service;


	@Test
	public void addEmployeeTest() {

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(repository.save(ArgumentMatchers.any(EmployeeData.class))).thenReturn(user);

		when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(getEmployeeDto());

		EmployeeDataDTO created = service.addEmployee(user);

		assertThat(created.getName()).isSameAs(user.getName());

		verify(repository).save(user);

	}

	@Test
	public void saveOrUpdateEmployeeTest() {

		int id = 1;

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(repository.save(ArgumentMatchers.any(EmployeeData.class))).thenReturn(user);

		when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(getEmployeeDto());

		EmployeeDataDTO created = service.saveorUpdateEmployee(user, id);

		assertThat(created.getName()).isSameAs(user.getName());

		verify(repository).save(user);

	}

	@Test
	public void getAllEmployeesTest() {

		List<EmployeeData> list = new ArrayList<EmployeeData>();
		
		EmployeeData empOne = new EmployeeData(1, "mohan", 23, "male", "Trainee", "it");
		
		EmployeeData empTwo = new EmployeeData(10, "mohan", 23, "male", "Trainee", "it");

		list.add(empOne);
		
		list.add(empTwo);	

		when(repository.findAll()).thenReturn(list);

		when(modelMapper.map(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(getAllEmployeeDto());

		List<EmployeeDataDTO> created = service.getAllEmployees();

		assertEquals(created.size(), service.getAllEmployees().size());

	}

	@Test
	public void getEmployeeTest() throws Exception {

		int id = 1;

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(repository.save(ArgumentMatchers.any(EmployeeData.class))).thenReturn(user);

		when(repository.findById(id)).thenReturn(Optional.of(user));

		when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(getEmployeeDto());

		service.addEmployee(user);

		EmployeeDataDTO search = service.getEmployee(id);

		assertThat(search.getName()).isSameAs(user.getName());

	}

	@Test
	public void getEmployeeByIdAndNameTest() {

		int id = 1;

		String name = "m";

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(repository.save(ArgumentMatchers.any(EmployeeData.class))).thenReturn(user);

		when(repository.findByIdAndName(id, name)).thenReturn(Optional.of(user));

		when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(getEmployeeDto());

		service.addEmployee(user);

		EmployeeDataDTO search = service.getEmployeeByIdAndName(id, name);

		assertThat(search.getName()).isSameAs(user.getName());

		assertThat(search.getAge()).isSameAs(user.getAge());

	}

	@Test
	public void deleteEmployeeTest() throws Exception {

		int id = 1;

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(repository.save(ArgumentMatchers.any(EmployeeData.class))).thenReturn(user);

		when(repository.findById(id)).thenReturn(Optional.of(user));

		assertEquals(id, user.getId());

		service.deleteEmployee(id);

		Mockito.verify(repository, times(1)).deleteById(id);

	}

	@Test
	public void deleteAllEmployeesTest() throws Exception {

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(repository.save(ArgumentMatchers.any(EmployeeData.class))).thenReturn(user);

		service.deleteAllEmployees();

		Mockito.verify(repository, times(1)).deleteAll();

	}

	private EmployeeDataDTO getEmployeeDto() {

		EmployeeDataDTO employeeDto = new EmployeeDataDTO(1, "m", 23);

		return employeeDto;
	}
	
	private EmployeeDataDTO[] getAllEmployeeDto() {

		EmployeeDataDTO[ ] employeeObjects = new EmployeeDataDTO[2];
		
		employeeObjects[0]=new EmployeeDataDTO(1, "m", 23);
		
		employeeObjects[1]=new EmployeeDataDTO(10, "m", 23);

		return employeeObjects;
	}
}