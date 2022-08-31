package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,String>{

	public List<Employee> findByManagerId(String id);
	public Employee findByEmployeeId(String employeeId);
	public Employee findByName(String name);
}
