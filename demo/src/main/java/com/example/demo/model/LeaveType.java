package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leavetype")
@Data
@NoArgsConstructor
public class LeaveType {
	
	@Id
	@NotEmpty
	@Column(name = "typeid")
	private String typeId;
	@NotEmpty
	@Column(name = "type")
	private String type;
	@NotEmpty
	@Column(name = "description")
	private String description;

	
	public LeaveType(String typeId, @NotEmpty String type, @NotEmpty String description) {

		this.typeId = typeId;
		this.type = type;
		this.description = description;

	}
}
