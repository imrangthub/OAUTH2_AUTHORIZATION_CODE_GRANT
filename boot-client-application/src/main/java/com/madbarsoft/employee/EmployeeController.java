package com.madbarsoft.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

	@GetMapping({ "/getEmployees" })
	public ModelAndView getEmployeeInfo() {
		return new ModelAndView("getEmployees");
	}
	
	@GetMapping({ "/showEmployees" })
	public ModelAndView showEmployees(@RequestParam("code") String code) {
		
		System.out.println("This is from Client Show Employee, Authorization code: "+code);

		
		return new ModelAndView("showEmployees");
	}

	

}