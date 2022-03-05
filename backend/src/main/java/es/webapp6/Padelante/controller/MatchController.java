package es.webapp6.Padelante.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.service.MatchService;

@Controller
public class MatchController {
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

    @GetMapping("/match/{id}")
    public String match(Model model,@PathVariable long id,HttpServletRequest request) {
		// Principal principal = request.getUserPrincipal();
		// String userName = principal.getName();
		// Optional<User> user = userService.findByName(userName); //By ID??
		Match match = matchService.findById(id).get();
		
		if(match.getResult().get(0)==0 && match.getResult().get(1)==0){
			model.addAttribute("isCheked", false);
		}
		else{
			model.addAttribute("isCheked", true);
		}
		model.addAttribute("goodResult", true);
		model.addAttribute("maches",matchService.findById(id).get());
		
        return "match";
    }

	@PostMapping("/resultMach/{id}")
    public String resultMatch(Model model, @PathVariable long id,@RequestParam String sets1,@RequestParam String sets2,@RequestParam String sets3,
	@RequestParam String sets4,@RequestParam String sets5,@RequestParam String sets6){
        Match match = matchService.findById(id).get();
		
		boolean cheked = matchService.checkResult(sets1, sets2, sets3, sets4, sets5, sets6, match);

		if(cheked){
			model.addAttribute("isCheked", true);
			model.addAttribute("maches",matchService.findById(id).get());
			model.addAttribute("goodResult", true);
			return "redirect:/match/{id}";
			
		}else{
			model.addAttribute("maches",matchService.findById(id).get());
			model.addAttribute("isCheked", false);
			model.addAttribute("goodResult", false);
			return "match";
		}
    }
}
