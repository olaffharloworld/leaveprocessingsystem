package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.model.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.RoleReposity;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository emrepo;
	@Autowired
	private RoleReposity rlrepo;
	
	public List<Employee> listAll()
	{
		return emrepo.findAll();	
	}
	public void save(Employee employee)
	{
		emrepo.save(employee);
	}
	public Employee get(String employeeId)
	{
		return emrepo.findById(employeeId).get();
	}
	public void delete(String employeeId)
	{
		rlrepo.deleteById(employeeId);
		emrepo.deleteById(employeeId);
	}
	public Employee changeUser(Employee employee) {
		return emrepo.saveAndFlush(employee);
	}
	public Employee GetEmloyee()
	{
		return emrepo.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}