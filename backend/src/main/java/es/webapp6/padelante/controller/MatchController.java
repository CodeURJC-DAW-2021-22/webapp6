package es.webapp6.padelante.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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

import es.webapp6.padelante.model.Match;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.UserService;

@Controller
public class MatchController {
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

    @GetMapping("/match/{id}")
    public String match(Model model,@PathVariable long id,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			String userName = principal.getName();
			Optional<User> user = userService.findByName(userName);		
			List<Match> matches = matchService.getUserMatches(user.get());
			model.addAttribute("matches", matches);
			model.addAttribute("numMatches", matches.size());
			model.addAttribute("showMatches", matches.size()>0);
		}

		Match match = matchService.findById(id).get();
		
		if(match.isHasWinner()){
			model.addAttribute("isCheked", true);
		}
		else{
			model.addAttribute("isCheked", false);
		}
		model.addAttribute("goodResult", true);
		model.addAttribute("actualMatch",matchService.findById(id).get());
		
        return "match";
    }

	@PostMapping("/resultMach/{id}")
    public String resultMatch(Model model, HttpServletRequest request, @PathVariable long id,@RequestParam String games1,@RequestParam String games2,@RequestParam String games3,
	@RequestParam String games4,@RequestParam String games5,@RequestParam String games6){
        Match match = matchService.findById(id).get();
		
		ArrayList<Integer> result = matchService.buildResult(games1, games2, games3, games4, games5, games6);
		boolean cheked = matchService.generateResult(result, match);

		if(cheked){
			
			model.addAttribute("isCheked", true);
			model.addAttribute("actualMatch",matchService.findById(id).get());
			model.addAttribute("goodResult", true);
			return "redirect:/match/{id}";
			
		}else{
			Principal principal = request.getUserPrincipal();

			if (principal != null) {
				String userName = principal.getName();
				Optional<User> user = userService.findByName(userName);		
				List<Match> matches = matchService.getUserMatches(user.get());
				model.addAttribute("matches", matches);
				model.addAttribute("numMatches", matches.size());
			}

			model.addAttribute("actualMatch",matchService.findById(id).get());
			model.addAttribute("isCheked", false);
			model.addAttribute("goodResult", false);
			return "match";
		}
    }
}
