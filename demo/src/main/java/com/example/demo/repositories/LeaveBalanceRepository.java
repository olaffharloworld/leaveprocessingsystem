package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LeaveBalance;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Integer>{

}
