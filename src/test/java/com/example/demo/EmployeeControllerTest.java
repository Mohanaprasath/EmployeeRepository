package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.controller.EmployeeController;
import com.example.demo.dao.EmployeeData;
import com.example.demo.dto.EmployeeDataDTO;
import com.example.demo.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService service;

	@Test
	public void addEmployeeTest() {

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(service.addEmployee(ArgumentMatchers.any(EmployeeData.class))).thenReturn(getEmployeeDto());

		EmployeeDataDTO created = employeeController.addEmployee(user);

		assertThat(created.getName()).isSameAs(user.getName());

	}

	@Test
	public void saveOrUpdateEmployeeTest() {

		int id = 1;

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(service.saveorUpdateEmployee(ArgumentMatchers.any(EmployeeData.class), ArgumentMatchers.anyInt()))
				.thenReturn(getEmployeeDto());

		EmployeeDataDTO created = employeeController.saveorUpdateEmployee(user, id);

		assertThat(created.getName()).isSameAs(user.getName());

	}

	@Test
	public void getEmployeeTest() throws Exception {

		int id = 6;

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(service.getEmployee(ArgumentMatchers.anyInt())).thenReturn(getEmployeeDto());

		EmployeeDataDTO search = employeeController.getEmployee(id);

		assertThat(search.getName()).isSameAs(user.getName());
	}

	@Test
	public void getEmployeeByIdAndNameTest() {

		int id = 1;

		String name = "m";

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(service.getEmployeeByIdAndName(ArgumentMatchers.anyInt(), ArgumentMatchers.anyString()))
				.thenReturn(getEmployeeDto());

		EmployeeDataDTO search = employeeController.getEmployeeByIdAndName(id, name);

		assertThat(search.getName()).isSameAs(user.getName());

		assertThat(search.getAge()).isSameAs(user.getAge());

	}

	@Test
	public void getAllEmployeesTest() {

		List<EmployeeData> list = new ArrayList<EmployeeData>();

		EmployeeData empOne = new EmployeeData(1, "mohan", 23, "male", "Trainee", "it");

		EmployeeData empTwo = new EmployeeData(10, "mohan", 23, "male", "Trainee", "it");

		list.add(empOne);

		list.add(empTwo);

		when(service.getAllEmployees()).thenReturn(getAllEmployeeDto());

		List<EmployeeDataDTO> created = employeeController.getEmployees();

		assertEquals(created.size(), employeeController.getEmployees().size());

	}

	@Test
	public void deleteEmployeeTest() throws Exception {

		int id = 1;

		EmployeeData user = new EmployeeData(1, "m", 23, "male", "Trainee", "it");

		when(service.deleteEmployee(ArgumentMatchers.anyInt())).thenReturn(getEmployeeDto());

		when(service.getEmployee(ArgumentMatchers.anyInt())).thenReturn(getEmployeeDto());

		EmployeeDataDTO search = employeeController.deleteEmployee(id);

		Mockito.verify(service, times(1)).deleteEmployee(id);

		assertThat(search.getName()).isSameAs(user.getName());

	}

	@Test
	public void deleteAllEmployeesTest() throws Exception {

		when(service.addEmployee(ArgumentMatchers.any(EmployeeData.class))).thenReturn(getEmployeeDto());

		employeeController.deleteAllEmployees();

		Mockito.verify(service, times(1)).deleteAllEmployees();

	}

	private EmployeeDataDTO getEmployeeDto() {

		EmployeeDataDTO employeeDto = new EmployeeDataDTO(1, "m", 23);

		return employeeDto;
	}

	private List<EmployeeDataDTO> getAllEmployeeDto() {

		List<EmployeeDataDTO> employeeObjects = new ArrayList<EmployeeDataDTO>();

		EmployeeDataDTO user1 = new EmployeeDataDTO(1, "m", 23);

		EmployeeDataDTO user2 = new EmployeeDataDTO(10, "m", 23);

		employeeObjects.add(user1);

		employeeObjects.add(user2);

		return employeeObjects;
	}

}
