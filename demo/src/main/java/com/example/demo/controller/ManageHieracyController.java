package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.model.LeaveBalance;
import com.example.demo.model.LeaveType;
import com.example.demo.model.User;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserService;

@Controller
public class ManageHieracyController {
	
	@Autowired 
	private EmployeeService service;
	@Autowired
	private UserService uservice;
	
	@RequestMapping("/hieracy")
	public String ViewHieracyPage(Model model)
	{
		List<Employee> listEmployees=service.listAll();
		model.addAttribute("listEmployees", listEmployees);
		
		return "employeelist";
	}
	
	@RequestMapping("/newemployee")
	public String showNewEmployeeForm(Model model)
	{
		Employee employee= new Employee();
		model.addAttribute("employee",employee);
		List<User> listUsers=uservice.listAll();
		model.addAttribute("listUsers", listUsers);
		return "newemployee";
		
	}
	
	@RequestMapping(value="/saveemployee",method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") Employee employee)
	{
		service.save(employee);
		return "forward:/hieracy";
	}
	
	@RequestMapping(value="/editemployee/{employeeId}",method=RequestMethod.GET)
	public ModelAndView showEditUserForm(@PathVariable("employeeId") String employeeId)
	{
		ModelAndView mav = new ModelAndView("edit_employee");
		Employee employee = service.get(employeeId);
		mav.addObject("employee", employee);	
		List<User> listUsers=uservice.listAll();
		mav.addObject("listUsers", listUsers);
		return mav;
		
	}
	@RequestMapping(value="/editemployee/{employeeId}",method=RequestMethod.POST)
	public ModelAndView editUser(@PathVariable("employeeId") String userId,@ModelAttribute @Valid Employee employee)
	{
		ModelAndView mav = new ModelAndView("forward:/hieracy");
		String message="employee was successfully updated";
		System.out.println(message);
		service.changeUser(employee);
		return mav;
	}
	
	
	@RequestMapping(value="/deleteemployee/{employeeId}",method=RequestMethod.GET)
	public String deleteUser(@PathVariable("employeeId") String employeeId)
	{
		service.delete(employeeId);
		
		return "redirect:/hieracy";
	}
	@RequestMapping(value="/about")
	public String viewAboutPage()
	{
		return "about";
	}
	
}
