package es.webapp6.Padelante.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.service.MatchService;
import es.webapp6.Padelante.service.TournamentService;
import es.webapp6.Padelante.service.UserService;

@Controller
public class indexController {
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
			Page<Tournament> mytourns = tournamentService.findUserTournaments(pageInt, userService.findByName(principal.getName()).get());
			model.addAttribute("mytourns", mytourns);
			model.addAttribute("nextpage2", pageInt+1);
			model.addAttribute("numMyTourns", mytourns.getTotalPages()>1);
				
			String userName = principal.getName();
			Optional<User> user = userService.findByName(userName);
			List<Match> matches = matchService.getUserMatches(user.get());
			model.addAttribute("matches", matches);
			model.addAttribute("numMatches", matches.size());
			model.addAttribute("showMatches", matches.size()>0);
		}

		Page<Tournament> tourns = tournamentService.getTournaments(pageInt);
		model.addAttribute("tourns", tourns);
		model.addAttribute("nextpage", pageInt+1);
		model.addAttribute("numTourns", tourns.getTotalPages()>1);
        return "main";
    }
}
