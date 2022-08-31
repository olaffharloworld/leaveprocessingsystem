package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leavebalance")
@Data
@NoArgsConstructor
public class LeaveBalance {
    @Id
    @Column(name = "userleaveid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userleaveid; 

    @NotEmpty
    @Column(name = "name")
    private String name;
    
    @NotEmpty
    @Column(name = "type")
    private String type;
    
    @NotNull
    @Column(name = "balance")
    private double balance;

	public LeaveBalance(String name, String type, double balance) {
		super();
		this.name = name;
		this.type = type;
		this.balance = balance;
	}


}
