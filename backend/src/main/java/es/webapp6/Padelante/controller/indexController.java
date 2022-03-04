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

import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.service.MatchService;
import es.webapp6.Padelante.service.TeamService;
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

	@Autowired
	private TeamService teamService;	

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
		Optional<User> user = userService.findByName(userName); //By ID??
		model.addAttribute("maches", matchService.getUserMatches(user.get()));
		}

		

		model.addAttribute("tourns",tournamentService.getTournaments(pageInt).getContent());
		model.addAttribute("nextpage", pageInt+1);
        return "main";
    }
	
	@GetMapping("/admin")
    public String admin(Model model, @RequestParam(required = false) Integer page) {       
		int pageInt = page == null? 0: page;  
		model.addAttribute("adminTourns",tournamentService.getTournaments(pageInt).getContent());
		model.addAttribute("adminUsers", userService.getUsers(pageInt).getContent());
		model.addAttribute("adminnextpage", pageInt+1);
		return "admin";
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
		model.addAttribute("maches",matchService.findById(id).get());
		
        return "match";
    }

	@PostMapping("/resultMach/{id}")
    public String resultMatch(Model model, @PathVariable long id,@RequestParam String sets1,@RequestParam String sets2,@RequestParam String sets3,
	@RequestParam String sets4,@RequestParam String sets5,@RequestParam String sets6){
        Match match = matchService.findById(id).get();
		
		boolean cheked = matchService.checkResult(sets1, sets2, sets3, sets4, sets5, sets6, match);

		if(cheked){
			// model.addAttribute("isCheked", true);
			model.addAttribute("maches",matchService.findById(id).get());
			return "redirect:/match/{id}";
		}else{
			return "matchResulterror";
		}
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String newRegister(Model model, @RequestParam String name, @RequestParam String encodedPassword,@RequestParam String email,
	@RequestParam String realName){
		
		if(userService.findByName(name).isPresent()){
			model.addAttribute("u", name);
			return "registerError";
			
		}else{
			model.addAttribute("existUser", false);
			userService.registerNewUser(name, encodedPassword,email,realName);
			return "redirect:/";
		}
       
    }

    @GetMapping("/tournament")
    public String tournament(Model model, HttpServletRequest request) {
		
		
        return "tournament";
    }

    @GetMapping("/user_profile")
    public String user_profile(Model model, HttpServletRequest request,
	@RequestParam(required = false) Integer page) {
		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		Optional<User> user = userService.findByName(userName); //By ID??
		model.addAttribute("user", user.get());
		//model.addAttribute("userCompleteName", user.get().getRealName());

		int pageInt = page == null? 0: page;  
		model.addAttribute("userTourns",tournamentService.findUserTournaments(pageInt, user.get()).getContent());
		model.addAttribute("nextpage", pageInt+1);
		
        return "user_profile";
    }

	@GetMapping("/user/{id}/image")
	public ResponseEntity<Object> downloadImageUser(@PathVariable long id) throws SQLException {

		Optional<User> user = userService.findById(id);
		if (user.isPresent() && user.get().getImageFile() != null) {

			Resource file = new InputStreamResource(user.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(user.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/update_userProfile/{id}")
	public String updateProfile(Model model,@PathVariable long id ,@RequestParam String fullName,
	@RequestParam String location, @RequestParam String country,@RequestParam String phone, 
	boolean removeImage,  MultipartFile imageField)throws IOException, SQLException{

		Optional<User> user = userService.findById(id); //By ID??
		
		if (user.isPresent()) {
		updateImageProfile(user.get(), removeImage, imageField);
		user.get().setLocation(location);
		user.get().setCountry(country);
		user.get().setPhone(phone);
		user.get().setRealName(fullName);
		userService.save(user.get());
		return "redirect:/user_profile";
		}else{
			return "error";
		}
    }

	@GetMapping("/removeUser/{id}")
	public String removeUser(Model model, @PathVariable long id) {

		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			model.addAttribute("removedUser", user.get());
			userService.delete(id);
			//model.addAttribute("userDelate", user.get());
		}
		return "removeUser";
	}

	//I know is a similar method of the update image for tournament, but i dont know how to do it
	private void updateImageProfile(User user, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			user.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			user.setImage(true);
		} else {
			if (removeImage) {
				user.setImageFile(null);
				user.setImage(false);
			} else {
				User dbUser = userService.findById(user.getId()).orElseThrow();
				if (dbUser.getImage()) {
					user.setImageFile(BlobProxy.generateProxy(dbUser.getImageFile().getBinaryStream(),
					dbUser.getImageFile().length()));
						user.setImage(true);
				}
			}
		}
	}


    @GetMapping("/tourns/{id}")
	public String showTournament(Model model, @PathVariable long id,HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		Optional<Tournament> tournament = tournamentService.findById(id);

		if (tournament.isPresent()) {
			int numRound = tournament.get().getRounds();
			boolean r1=false;
			boolean r2=false;
			boolean r3=false;
			boolean r4=false;

			if(numRound==1){
				r1=true;
			}else if(numRound==2){
				r1=true;
				r2=true;
			}else if(numRound==3){
				r1=true;
				r2=true;
				r3=true;
			}else if (numRound==4){
				r1=true;
				r2=true;
				r3=true;
				r4=true;
			}
			
			model.addAttribute("hasr1", r1);
			model.addAttribute("hasr2", r2);
			model.addAttribute("hasr3", r3);
			model.addAttribute("hasr4", r4);

			model.addAttribute("roundFour", matchService.getRoundMatches(tournament.get(),4));
			model.addAttribute("roundTres", matchService.getRoundMatches(tournament.get(),3));
			model.addAttribute("roundTwo",matchService.getRoundMatches(tournament.get(),2));
			model.addAttribute("roundOne",matchService.getRoundMatches(tournament.get(),1));

			model.addAttribute("participants", teamService.getParticipantsOfTournament(tournament.get()));	

			model.addAttribute("tourns", tournament.get());
			if(principal!=null){
				String userName = principal.getName();
				String ownerTournament=tournament.get().getOwner();
				Boolean owner = ownerTournament.equals(userName);
				if(owner || userService.findByName(userName).get().getRoles().contains("ADMIN")){
					model.addAttribute("owner", true);
				}else{
					model.addAttribute("owner", false);
				}
			}else{
				model.addAttribute("owner", false);
			}

			return "tournament";
		} else {
			return "error";
		}

	} 

	@GetMapping("/removeTournament/{id}")
	public String removeTournament(Model model, @PathVariable long id) {

		Optional<Tournament> tourn = tournamentService.findById(id);
		if (tourn.isPresent()) {
			model.addAttribute("removedTournament", tourn.get());
			tournamentService.delete(id);
			
		}
		return "removeTournament";
	}

	@PostMapping("/update_tourns/{id}")
	public String updateTournament(Model model, @PathVariable long id , @RequestParam String name, @RequestParam String about,
	@RequestParam String ruleset, @RequestParam String location, boolean removeImage, 
	MultipartFile imageField) throws IOException, SQLException{
		
		Optional<Tournament> tournament = tournamentService.findById(id);

		if (tournament.isPresent()) {
			updateImage(tournament.get(), removeImage, imageField);
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

	private void updateImage(Tournament tourn, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			tourn.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			tourn.setImage(true);
		} else {
			if (removeImage) {
				tourn.setImageFile(null);
				tourn.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Tournament dbTournament = tournamentService.findById(tourn.getId()).orElseThrow();
				if (dbTournament.getImage()) {
					tourn.setImageFile(BlobProxy.generateProxy(dbTournament.getImageFile().getBinaryStream(),
						dbTournament.getImageFile().length()));
							tourn.setImage(true);
				}
			}
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
	// @GetMapping("/users/{id}/image")
	// public ResponseEntity<Object> downloadImageUser(@PathVariable long id) throws SQLException {

	// 	Optional<User> userss = userService.findById(id);
	// 	if (userss.isPresent() && userss.get().getImageFile() != null) {

	// 		Resource file = new InputStreamResource(userss.get().getImageFile().getBinaryStream());

	// 		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
	// 				.contentLength(userss.get().getImageFile().length()).body(file);

	// 	} else {
	// 		return ResponseEntity.notFound().build();
	// 	}
	// }

  

    

}
