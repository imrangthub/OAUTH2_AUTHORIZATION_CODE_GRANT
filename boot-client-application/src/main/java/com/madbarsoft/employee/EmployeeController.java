package com.madbarsoft.employee;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmployeeController {

	@GetMapping({ "/getEmployees" })
	public ModelAndView getEmployeeInfo() {
		return new ModelAndView("getEmployees");
	}

	@GetMapping({ "/showEmployees" })
	public ModelAndView showEmployees(@RequestParam("code") String code) throws JsonProcessingException, IOException {

		System.out.println("This is from Client Show Employee, Authorization code: " + code);

		ResponseEntity<String> response = null;
		RestTemplate restTemplate = new RestTemplate();

		// According OAuth documentation we need to send the client id and
		// secret key in the header for authentication
		String credentials = "madbarsoft:secret";
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		String access_token_url = "http://localhost:8090/oauth/token";
		access_token_url += "?code=" + code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=http://localhost:8080/showEmployees";

		response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
		
		System.out.println("Access Token Response body: " + response.getBody());
		

		// Get the Access Token From the recieved JSON response
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody());
		String token = node.path("access_token").asText();

		
		String url = "http://localhost:8090/employee/list";
		
		// Use the access token for authentication
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		headers1.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<>(headers1);

		ResponseEntity<Employee[]> employees = restTemplate.exchange(url, HttpMethod.GET, entity, Employee[].class);
		System.out.println("Response Emp: "+employees);
		Employee[] employeeArray = employees.getBody();

		ModelAndView model = new ModelAndView("showEmployees");
		model.addObject("employeeList", Arrays.asList(employeeArray));
		return model;

		// return new ModelAndView("showEmployees");
	}

}