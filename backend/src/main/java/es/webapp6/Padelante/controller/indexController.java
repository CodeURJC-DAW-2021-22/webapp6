package es.webapp6.Padelante.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.service.TournamentService;

@Controller
public class indexController {
    
    @Autowired
	private TournamentService tournamentService;	

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
        List<Tournament> tourns = tournamentService.getTournaments();      
        model.addAttribute("tourns",tourns);// tournamentRepository.findByTournamentName("Dani").get(0).getTournamentName()
        return "main";
    }

    @GetMapping("/create_tournament")
    public String createTournamentPage(Model model) {
        return "create_tournament";
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

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/match")
    public String match(Model model) {
        return "match";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
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
   
}
