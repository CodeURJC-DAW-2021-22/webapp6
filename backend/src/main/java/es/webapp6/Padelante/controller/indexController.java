package es.webapp6.Padelante.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;


import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;


import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;
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
        model.addAttribute("tourns",tournamentService.getTournaments());
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

    // @PostMapping("/create_tournament")
    // public String createTournament(@RequestParam String tournamentName, @RequestParam int numParticipants){
    //     tournamentService.createTournament(tournamentName, numParticipants);    
    //     return "redirect:/";
    // }


	@PostMapping("/create_tournament")
	public String newTournamentProcess(Model model, Tournament tourna, MultipartFile imageField, HttpServletRequest request) throws IOException {
		if (!imageField.isEmpty()) {
			tourna.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			tourna.setImage(true);
		}

		Principal principal = request.getUserPrincipal();
		String user = principal.getName();
		tourna.setOwner(user);
		tournamentService.save(tourna);
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
    public String newRegister(Model model, @RequestParam String name, @RequestParam String encodedPassword,@RequestParam String email,
	@RequestParam String realName){
        userService.registerNewUser(name, encodedPassword,email,realName);
        return "redirect:/";
    }

    @GetMapping("/tournament")
    public String tournament(Model model) {
        return "tournament";
    }

    @GetMapping("/user_profile")
    public String user_profile(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		Optional<User> user = userService.findByName(userName); //By ID??
		
		model.addAttribute("user", user.get());
		//model.addAttribute("userCompleteName", user.get().getRealName());
        return "user_profile";
    }

	@PostMapping("/update_userProfile")
	public String updateProfile(Model model,@RequestParam String userN,@RequestParam String fullName,@RequestParam String location,
	@RequestParam String country,@RequestParam String phone){
		Optional<User> user = userService.findByName(userN); //By ID??

		user.get().setLocation(location);
		user.get().setCountry(country);
		user.get().setPhone(phone);
		user.get().setRealName(fullName);
		userService.save(user.get());
        

        return "redirect:user_profile";
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
			return "error";
		}

	} 

//select player from player where team in 
	@PostMapping("/update_tourns/{id}")
	public String updateTournament(Model model, @PathVariable long id , @RequestParam String name, @RequestParam String about,
	@RequestParam String ruleset, @RequestParam String location){
		Optional<Tournament> tournament = tournamentService.findById(id);

		if (tournament.isPresent()) {
			tournament.get().setTournamentName(name);
			tournament.get().setAbout(about);
			tournament.get().setRuleset(ruleset);
			tournament.get().setLocation(location);
			tournamentService.save(tournament.get());
			return "redirect:/tourns/{id}";
		} else {
			return "error";
		}
		
		

		
		
    }

	@GetMapping("/tourns/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Tournament> tourna = tournamentService.findById(id);
		if (tourna.isPresent() && tourna.get().getImageFile() != null) {

			Resource file = new InputStreamResource(tourna.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(tourna.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

    // @GetMapping("/removeTourn/{id}")
	// public String removeTournament(Model model, @PathVariable long id) {

	// 	Optional<Tournament> tourn = tournamentService.findById(id);
	// 	if (tourn.isPresent()) {
	// 		tournamentService.delete(id);
			
	// 	}
	// 	return "***";
	// }

    

	// @PostMapping("/editTournament")
	// public String editBookProcess(Model model, Tournament tourn, boolean removeImage, MultipartFile imageField)
	// 		throws IOException, SQLException {

	// 	updateImage(tourn, removeImage, imageField);

	// 	tournamentService.save(tourn);

	// 	model.addAttribute("bookId", tourn.getId());

	// 	return "redirect:/books/"+tourn.getId();
	// }

	// private void updateImage(Tournament tourn, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
	// 	if (!imageField.isEmpty()) {
	// 		tourn.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
	// 		tourn.setImage(true);
	// 	} else {
	// 		if (removeImage) {
	// 			tourn.setImageFile(null);
	// 			tourn.setImage(false);
	// 		} else {
	// 			// Maintain the same image loading it before updating the book
	// 			Tournament dbTournament = tournamentService.findById(tourn.getId()).orElseThrow();
	// 			if (dbTournament.getImage()) {
	// 				tourn.setImageFile(BlobProxy.generateProxy(dbTournament.getImageFile().getBinaryStream(),
    //                 dbTournament.getImageFile().length()));
    //                         tourn.setImage(true);
	// 			}
	// 		}
	// 	}
	// }
}
