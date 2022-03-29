package es.webapp6.padelante.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
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

import es.webapp6.padelante.model.Match;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.TournamentService;
import es.webapp6.padelante.service.UserService;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

@Controller
public class UserController {
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


	@GetMapping("/register")
    public String register(Model model) {
		model.addAttribute("u", true);
        return "register";
    }

    @PostMapping("/register")
    public String newRegister(Model model, @RequestParam String name, @RequestParam String encodedPassword,@RequestParam String email,
	@RequestParam String realName){
		
		if(userService.findByName(name).isPresent()){
			model.addAttribute("u", false);
			return "register";
		}else{
			userService.registerNewUser(name, encodedPassword,email,realName);
			return "redirect:/";	
		}
    }

	@GetMapping("/removeUser/{id}")
	public String removeUser(Model model, @PathVariable long id) {

		Optional<User> user = userService.findById(id);
		if (user.isPresent() && user.get().getStatus()) {
			userService.delete(user.get());
		}
		return "redirect:/admin";
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
			model.addAttribute("showMatches", matches.size()>0);
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


    @GetMapping("/user_profile")
    public String user_profile(Model model, HttpServletRequest request,
	@RequestParam(required = false) Integer page) {
		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		Optional<User> user = userService.findByName(userName);
		model.addAttribute("user", user.get());
		List<Match> matches = matchService.getUserMatches(user.get());
		model.addAttribute("matches", matches);
		model.addAttribute("numMatches", matches.size());
		model.addAttribute("showMatches", matches.size()>0);

		int pageInt = page == null? 0: page;  
		Page<Tournament> userTourns = tournamentService.findUserTournaments(pageInt, user.get());
		model.addAttribute("userTourns", userTourns);
		model.addAttribute("numUserTourns", userTourns.getTotalPages()>1);
		model.addAttribute("nextpage", pageInt+1);

		Page<User> userPairs = userService.findPairsOf(pageInt, user.get());
		model.addAttribute("userPairs", userPairs);
		model.addAttribute("numUserPairs", userPairs.getTotalPages()>1);
		model.addAttribute("nextpage2", pageInt+1);
		
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

		Optional<User> user = userService.findById(id);
		
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
}
