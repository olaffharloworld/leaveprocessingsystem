package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.PublicHoliday;
import com.example.demo.service.PublicHolidayService;
import com.example.demo.validator.LeaveTypeValidator;
import com.example.demo.validator.PublicHolidayValidator;


@Controller
public class PublicHolidayController {

	@Autowired 
	private PublicHolidayService service;
	@Autowired
	private PublicHolidayValidator phValidator;

	@InitBinder("publicholiday")
	private void initEmployeeBinder(WebDataBinder binder) {
		binder.addValidators(phValidator);
	}
	
	
	@RequestMapping("/publicholiday")
	public String Viewpublicholidaypage(Model model)
	{
		List<PublicHoliday> listPublicHolidays=service.listAll();
		model.addAttribute("listPublicHolidays", listPublicHolidays);
		
		return "publicholidaylist";
	}
	
	@RequestMapping("/newpublicholiday")
	public String showNewphForm(Model model)
	{
		PublicHoliday publicholiday= new PublicHoliday();
		model.addAttribute("publicholiday",publicholiday);
		
		return "newpublicholiday";
	}
	
	@RequestMapping(value="/savepublicholiday",method = RequestMethod.POST)
	public String savepublicholiday(@ModelAttribute("publicholiday") @Valid PublicHoliday publicholiday,
			BindingResult result)
	{
		if (result.hasErrors()) {
			return "newpublicholiday";
			}
		service.save(publicholiday);
		return "redirect:/publicholiday";
	}
	
	@RequestMapping(value="/editpublicholiday/{name}",method=RequestMethod.GET)
	public ModelAndView showEditPHForm(@PathVariable("name") String name)
	{
		ModelAndView mav = new ModelAndView("edit_publicholiday");
		PublicHoliday publicholiday= service.get(name);
		mav.addObject("publicholiday", publicholiday);		
		return mav;
	}
	@RequestMapping(value="/editpublicholiday/{name}",method=RequestMethod.POST)
	public ModelAndView editPH(@PathVariable("name") String name,@ModelAttribute @Valid PublicHoliday publicholiday, 
			BindingResult result)
	{
		if (result.hasErrors()) {
			return new ModelAndView("edit_publicholiday");
			}
		ModelAndView mav = new ModelAndView("redirect:/publicholiday");
		String message="Public Holiday was successfully updated";
		System.out.println(message);
		service.changeUser(publicholiday);
		return mav;
	}
	
	
	@RequestMapping(value="/deletepublicholiday/{name}",method=RequestMethod.GET)
	public String deletePH(@PathVariable("name") String name)
	{
		service.delete(name);
		
		return "redirect:/publicholiday";
	}

}
