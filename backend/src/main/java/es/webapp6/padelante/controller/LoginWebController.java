package es.webapp6.padelante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.webapp6.padelante.service.UserService;

@Controller
public class LoginWebController {
    @Autowired
	private UserService userService;
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}

	@GetMapping("/register")
    public String register(Model model) {
		model.addAttribute("u", true);
        return "register";
    }

    @PostMapping("/register")
    public String newRegister(Model model, @RequestParam String name, @RequestParam String encodedPassword,@RequestParam String email,
	@RequestParam String realName){
		
		if(userService.findByName(name).isPresent()){
			model.addAttribute("u", false);
			return "register";
		}else{
			userService.registerNewUser(name, encodedPassword,email,realName);
			return "redirect:/";	
		}
    }
}
