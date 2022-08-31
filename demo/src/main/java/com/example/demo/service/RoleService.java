package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repositories.RoleReposity;

@Service
public class RoleService {

	@Autowired
	private RoleReposity rorepo;
	
	public List<Role> listAll()
	{
		return rorepo.findAll();	
	}
	public void save(Role role)
	{
		rorepo.save(role);
	}
	public Role get(String roleid)
	{
		return rorepo.findById(roleid).get();
	}
	public void delete(String roleid)
	{
		rorepo.deleteById(roleid);
	}
	public Role changeUser(Role role) {
		return rorepo.saveAndFlush(role);
	}
}
