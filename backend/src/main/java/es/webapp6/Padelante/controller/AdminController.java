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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.service.MatchService;
import es.webapp6.Padelante.service.TournamentService;
import es.webapp6.Padelante.service.UserService;

@Controller
public class AdminController {
    @Autowired
	private TournamentService tournamentService;	

	@Autowired
	private MatchService matchService;

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

    @GetMapping("/admin")
    public String admin(Model model, HttpServletRequest request, @RequestParam(required = false) Integer page) {      
		Principal principal = request.getUserPrincipal();
		if (principal != null) {				
			String userName = principal.getName();
			Optional<User> user = userService.findByName(userName);
			List<Match> matches = matchService.getUserMatches(user.get());
			model.addAttribute("matches", matches);
			model.addAttribute("numMatches", matches.size());
		}
		
		int pageInt = page == null? 0: page; 
		Page<Tournament> adminTourns = tournamentService.getTournaments(pageInt);
		model.addAttribute("adminTourns", adminTourns);
		model.addAttribute("numAdminTourns", adminTourns.getTotalPages()>1);

		Page<User> adminUsers = userService.getUsersNoAdmin(pageInt);
		model.addAttribute("adminUsers", adminUsers);
		model.addAttribute("numAdminUsers", adminUsers.getTotalPages()>1);
		model.addAttribute("adminnextpage", pageInt+1);
		return "admin";
    }

    @GetMapping("/removeUser/{id}")
	public String removeUser(Model model, @PathVariable long id) {

		Optional<User> user = userService.findById(id);
		if (user.isPresent() && user.get().getStatus()) {
			user.get().setStatus(false);
			userService.save(user.get());
		}
		return "redirect:/admin";
	}

    @GetMapping("/removeTournament/{id}")
	public String removeTournament(Model model, @PathVariable long id) {

		Optional<Tournament> tourn = tournamentService.findById(id);
		if (tourn.isPresent()) {
			model.addAttribute("removedTournament", tourn.get());
			tournamentService.delete(id);
			
		}
		return "redirect:/admin";
	}
}
