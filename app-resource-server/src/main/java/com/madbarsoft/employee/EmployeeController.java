package com.madbarsoft.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@ResponseBody
	@GetMapping({ "/list" })
	public List<Employee> getEmployeesList() {
		List<Employee> employees = new ArrayList<>();
		
		Employee emp = new Employee();
		
		emp.setEmpId("imranmadbar@gmail.com");
		emp.setEmpName("MD IMRAN HOSSAIN");
		
		employees.add(emp);
		
		return employees;

	}

}
