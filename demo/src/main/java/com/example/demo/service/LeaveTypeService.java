package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LeaveType;
import com.example.demo.repositories.LeavetypeRepository;



@Service
public class LeaveTypeService {
	@Autowired
	private LeavetypeRepository lerepo;
	
	public List<LeaveType> listAll()
	{
		return lerepo.findAll();	
	}
	public void save(LeaveType leavetype)
	{
		lerepo.save(leavetype);
	}
	public LeaveType get(String typeid)
	{
		return lerepo.findById(typeid).get();
	}
	public void delete(String typeid)
	{
		lerepo.deleteById(typeid);
	}
	public LeaveType changeUser(LeaveType leavetype) {
		return lerepo.saveAndFlush(leavetype);
	}
//	public ArrayList<String> findAllLeaveTypeNamesExCL() {
//		return (ArrayList<String>) lerepo.findAllLeaveTypeNamesExCL();
//	}
	
}
