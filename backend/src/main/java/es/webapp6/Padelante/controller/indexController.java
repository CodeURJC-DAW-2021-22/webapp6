package es.webapp6.Padelante.controller;

import java.security.Principal;

import java.util.Optional;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.service.TournamentService;
import es.webapp6.Padelante.service.UserService;

@Controller
public class indexController {
    
    @Autowired
	private TournamentService tournamentService;	

    @Autowired
	private UserService userService;	

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
		} else {
			model.addAttribute("logged", false);
		}
	}
	
    @GetMapping("/")
    public String greeting(Model model) {         
        model.addAttribute("tourns",tournamentService.findAll());
       return "main";
    }

    @GetMapping("/create_tournament")
    public String createTournamentPage(Model model,HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

		if (principal != null) {

			return "create_tournament";
		} else {
			return "login";
		}

        
    }

    @PostMapping("/create_tournament")
    public String createTournament(@RequestParam String tournamentName, @RequestParam int numParticipants){
        tournamentService.createTournament(tournamentName, numParticipants);    
        return "redirect:/";
    }

    @GetMapping("/errorPage")
    public String errorPage(Model model) {
        return "errorPage";
    }


    @GetMapping("/match")
    public String match(Model model) {
        return "match";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String newRegister(Model model, @RequestParam String name, @RequestParam String encodedPassword){
        userService.registerNewUser(name, encodedPassword);
        return "redirect:/";
    }

    @GetMapping("/tournament")
    public String tournament(Model model) {
        return "tournament";
    }

    @GetMapping("/user_profile")
    public String user_profile(Model model) {
        return "user_profile";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }


    @GetMapping("/tourns/{id}")
	public String showTournament(Model model, @PathVariable long id) {

		Optional<Tournament> tournament = tournamentService.findById(id);
		if (tournament.isPresent()) {
			model.addAttribute("tourns", tournament.get());
			return "tournament";
		} else {
			return "main";
		}

	} 
}
