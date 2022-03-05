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

import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.service.TournamentService;
import es.webapp6.Padelante.service.UserService;

@Controller
public class UserController {
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
}
