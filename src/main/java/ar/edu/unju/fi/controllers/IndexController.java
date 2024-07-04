package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/principal")
public class IndexController {

	@GetMapping({"/index", "/home", "/", "/inicio","/main"})
	
	public String getIndex() {
		return "index";
	}
}
