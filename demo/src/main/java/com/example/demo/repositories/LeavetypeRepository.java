package com.example.demo.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.LeaveType;

public interface LeavetypeRepository extends JpaRepository<LeaveType,String>{

//	@Query("SELECT t.type from type AS t where t.type != 'Compensation Leave'")
//	ArrayList<String> findAllLeaveTypeNamesExCL();
}
