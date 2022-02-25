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

    // @PostMapping("/create_tournament")
    // public String createTournament(@RequestParam String tournamentName, @RequestParam int numParticipants){
    //     tournamentService.createTournament(tournamentName, numParticipants);    
    //     return "redirect:/";
    // }


    @PostMapping("/create_tournament")
	public String newTournamentProcess(Model model, Tournament tourna, MultipartFile imageField) throws IOException {

		if (!imageField.isEmpty()) {
			tourna.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			tourna.setImage(true);
		}
		tournamentService.save(tourna);
		return "redirect:/";
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
