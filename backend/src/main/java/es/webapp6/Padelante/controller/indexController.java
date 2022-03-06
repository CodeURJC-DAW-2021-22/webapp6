package es.webapp6.Padelante.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.service.MatchService;
import es.webapp6.Padelante.service.TournamentService;
import es.webapp6.Padelante.service.UserService;

@Controller
public class IndexController {
    @Autowired
	private TournamentService tournamentService;	

    @Autowired
	private UserService userService;	

	@Autowired
	private MatchService matchService;	

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
     public String greeting(Model model, HttpServletRequest request, @RequestParam(required = false) Integer page) { 
		Principal principal = request.getUserPrincipal();      
		int pageInt = page == null? 0: page; 
		if (principal != null) {
			model.addAttribute("mytourns", tournamentService.getUserTournaments(userService.findByName(principal.getName()).get())); 
				
			String userName = principal.getName();
			Optional<User> user = userService.findByName(userName);
			model.addAttribute("matches", matchService.getUserMatches(user.get()));
		}

		model.addAttribute("tourns",tournamentService.getTournaments(pageInt).getContent());
		model.addAttribute("nextpage", pageInt+1);
        return "main";
    }
}
