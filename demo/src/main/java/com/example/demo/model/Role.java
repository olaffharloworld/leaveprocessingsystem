package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role {

	@Id
	@Column(name = "roleid")
	private String roleId;
	@Column(name = "name")
	private String name;
	

	public Role(String roleId, String name) {

		this.roleId = roleId;
		this.name = name;
	}
}
