package com.madbarsoft.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@RequestMapping(value = "/list", produces = "application/json")
	@ResponseBody
	public List<Employee> getEmployeesList() {

		List<Employee> employees = new ArrayList<>();

		Employee emp = new Employee();
		
		emp.setEmpId("imranmadbar@gmail.com");
		emp.setEmpName("MD IMRAN HOSSAIN");
		employees.add(emp);
		
		Employee emp2 = new Employee();
		
		emp2.setEmpId("baby@gmail.com");
		emp2.setEmpName("MD BABU HOSSAIN");
		employees.add(emp2);

		return employees;

	}

}
