package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee {
	@Id
	@Column(name = "employeeId")
	private String employeeId;
	@Column(name = "name")
	private String name;
	@Column(name = "managerId")
	private String managerId;

	public Employee(String employeeId, String name, String managerId) {

		this.employeeId = employeeId;
		this.name = name;
		this.managerId = managerId;

	}
}
