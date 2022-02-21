package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TesteController {
	
	@GetMapping("/home")
	public String testar() {
		
		return "home";
	}

}
