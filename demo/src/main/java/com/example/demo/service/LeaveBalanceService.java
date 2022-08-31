package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LeaveBalance;
import com.example.demo.repositories.LeaveBalanceRepository;



@Service
public class LeaveBalanceService {
	@Autowired
	private LeaveBalanceRepository lbrepo;
	
	public List<LeaveBalance> listAll()
	{
		return lbrepo.findAll();	
	}
	public void save(LeaveBalance leavetype)
	{
		lbrepo.save(leavetype);
	}
	public LeaveBalance get(Integer userleaveid)
	{
		return lbrepo.findById(userleaveid).get();
	}
	public void delete(Integer userleaveid)
	{
		lbrepo.deleteById(userleaveid);
	}
	public LeaveBalance changeUser(LeaveBalance leavebalance) {
		return lbrepo.saveAndFlush(leavebalance);
	}
}
