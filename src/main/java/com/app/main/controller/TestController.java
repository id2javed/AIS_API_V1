package com.app.main.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
/*
* Developer    : Pallavi Dhage
* Created Date : 2019-07-25
* Updated Date : 
* Description  : API Controller For Test My First GET & POST API (Drop-Down Master)
*  */

@RestController
@CrossOrigin(origins = "${angularOrigin}")
public class TestController {
	@GetMapping(value = ApiRestURIConstants.GET_TEST)
	public String testapi() {
		System.out.println("hii");
		return "hi";
	}
	
	
	@PostMapping(value = "/testpostapi")
	public String testapipost() {
		System.out.println("hii");
		return "hi";
	}
}
