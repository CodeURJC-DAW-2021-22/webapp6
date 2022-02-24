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



    // @GetMapping("/tournament/{id}/image")
	// public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

	// 	Optional<Tournament> tournament = tournamentService.findById(id);
	// 	if (tournament.isPresent() && tournament.get().getImageFile() != null) {

	// 		Resource file = new InputStreamResource(tournament.get().getImageFile().getBinaryStream());
            
	// 		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
	// 				.contentLength(tournament.get().getImageFile().length()).body(file);

	// 	} else {
	// 		return ResponseEntity.notFound().build();
	// 	}
	// }

    // @GetMapping("/removeTournament/{id}")
	// public String removeTournament(Model model, @PathVariable long id) {

	// 	Optional<Tournament> tourn = tournamentService.findById(id);
	// 	if (tourn.isPresent()) {
	// 		tournamentService.delete(id);
	// 		model.addAttribute("book", tourn.get());
	// 	}
	// 	return "removedbook";
	// }

    // @PostMapping("/newTournament")
	// public String newTournaemntProcess(Model model, Book book, MultipartFile imageField, @RequestParam List<Long> selectedShops) throws IOException {

	// 	if (!imageField.isEmpty()) {
	// 		book.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
	// 		book.setImage(true);
	// 	}

	// 	book.setShops(shopService.findById(selectedShops));

	// 	bookService.save(book);

	// 	model.addAttribute("bookId", book.getId());

	// 	return "redirect:/books/"+book.getId();
	// }

    // @GetMapping("/editTournament/{id}")
	// public String editTournament(Model model, @PathVariable long id) {

	// 	Optional<Tournament> tourn = tournamentService.findById(id);
	// 	if (tourn.isPresent()) {
	// 		model.addAttribute("book", tourn.get());
	// 		return "editBookPage";
	// 	} else {
	// 		return "books";
	// 	}
	// }

	// @PostMapping("/editTournament")
	// public String editTournamentProcess(Model model, Tournament tourn, boolean removeImage, MultipartFile imageField)
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
