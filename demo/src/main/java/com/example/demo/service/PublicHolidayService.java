package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LeaveType;
import com.example.demo.model.PublicHoliday;
import com.example.demo.repositories.LeavetypeRepository;
import com.example.demo.repositories.PublicHolidayRepository;



@Service
public class PublicHolidayService {
	@Autowired
	private PublicHolidayRepository phrepo;
	
	public List<PublicHoliday> listAll()
	{
		return phrepo.findAll();	
	}
	public void save(PublicHoliday publicholiday)
	{
		phrepo.save(publicholiday);
	}
	public PublicHoliday get(String name)
	{
		return phrepo.findById(name).get();
	}
	public void delete(String name)
	{
		phrepo.deleteById(name);
	}
	public PublicHoliday changeUser(PublicHoliday publicholiday) {
		return phrepo.saveAndFlush(publicholiday);
	}
}
