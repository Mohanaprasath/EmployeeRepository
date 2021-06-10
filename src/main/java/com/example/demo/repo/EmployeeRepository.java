package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.EmployeeData;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeData, Integer> {


	
	public Optional<EmployeeData> findByIdAndName(int id, String name);

	public List<EmployeeData> findByAgeGreaterThan(int age);

	

}