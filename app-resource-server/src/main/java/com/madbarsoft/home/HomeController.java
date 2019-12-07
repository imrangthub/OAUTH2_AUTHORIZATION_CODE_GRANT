package com.madbarsoft.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	
	@GetMapping({"/test"})
	public String testMsg(){
		
		System.out.println("Hello from Resource Server test");
		return "Hello from Resource server Test";
	}
	
	

}
