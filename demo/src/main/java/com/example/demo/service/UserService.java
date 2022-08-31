package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userepo;
	
	 @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<User> listAll()
	{
		return userepo.findAll();	
	}
	public void save(User user)
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userepo.save(user);
	}
	public User get(String userid)
	{
		return userepo.findById(userid).get();
	}
	public void delete(String userid)
	{
		userepo.deleteById(userid);
	}
	public User changeUser(User user) {
		return userepo.saveAndFlush(user);
	}
}
