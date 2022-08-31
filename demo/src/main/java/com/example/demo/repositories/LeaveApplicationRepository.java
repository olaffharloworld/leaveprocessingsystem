package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;
import com.example.demo.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,	Integer>{


	LeaveApplication findById(int id);
	
	@Query(nativeQuery = true, value = "select distinct manager_name from leaveapplication where active=true ")
	String findManagerName();


	@Query(nativeQuery = true, value = "select * from leaveapplication where active=true ")
    public List<LeaveApplication> getAllActiveLeaves();
	@Query(nativeQuery = true, value = "select * from leaveapplication where employee_name=? order by id desc")
    public List<LeaveApplication> getAllLeavesOfUser(String username);
	List<LeaveApplication> findByEmployeeName(String id);
	
	@Query(nativeQuery=true,value="select * from leaveapplication where active=false and manager_name=?")
	public List<LeaveApplication> getAllNonActiveLeavesViaManager(String managername);
	
	@Query(nativeQuery=true,value="select * from leaveapplication where active=true and manager_name=?")
	public List<LeaveApplication> getAllActiveLeavesViaManager(String managername);
	
	
}
