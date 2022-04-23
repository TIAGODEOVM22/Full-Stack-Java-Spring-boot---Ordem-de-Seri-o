package com.tiago.os.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
@CrossOrigin("*")
@Controller
public class TesteController {

	@GetMapping("/home")
	public String testar() {
		
		return "home";
	}
}
