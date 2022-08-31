package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "publicholiday")
@Data
@NoArgsConstructor
public class PublicHoliday {
	@Id
	@NotEmpty
	@Column(name = "name")
	private String name;
	@NotEmpty
	@Column(name = "description")
	private String description;
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="dd-MM-yyyy")
	@Column(name = "date")
	private Date date;
	public PublicHoliday(String name, String description, Date date) {
		super();
		this.name = name;
		this.description = description;
		this.date = date;
	}

}
