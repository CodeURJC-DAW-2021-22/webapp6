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
import org.springframework.web.bind.annotation.RequestParam;

import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.service.TournamentService;
import es.webapp6.Padelante.service.UserService;

@Controller
public class AdminController {
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

    @GetMapping("/admin")
    public String admin(Model model, @RequestParam(required = false) Integer page) {       
		int pageInt = page == null? 0: page;  
		model.addAttribute("adminTourns",tournamentService.getTournaments(pageInt).getContent());
		model.addAttribute("adminUsers", userService.getUsersNoAdmin(pageInt).getContent());
		model.addAttribute("adminnextpage", pageInt+1);
		return "admin";
    }

    @GetMapping("/removeUser/{id}")
	public String removeUser(Model model, @PathVariable long id) {

		Optional<User> user = userService.findById(id);
		if (user.isPresent() && user.get().getStatus()) {
			user.get().setStatus(false);
			userService.save(user.get());
			//model.addAttribute("removedUser", user.get());
			//userService.delete(id);
			//model.addAttribute("userDelate", user.get());
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
