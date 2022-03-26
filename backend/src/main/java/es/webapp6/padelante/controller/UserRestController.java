package es.webapp6.padelante.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import es.webapp6.padelante.model.Match;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.TeamService;
import es.webapp6.padelante.service.TournamentService;
import es.webapp6.padelante.service.UserService;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;



@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
	private TournamentService tournamentService;	

	@Autowired
	private MatchService matchService;

    @Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;
	
	//who is conected
	@GetMapping("/me")
	public ResponseEntity<User> getActiveUser(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			return ResponseEntity.ok(userService.findByName(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	//get all users
	@GetMapping("")
	public ResponseEntity<Page<User>> getTournaments(@RequestParam int page) {
		return ResponseEntity.ok(userService.getUsers(page));
	}

	//get user by id
	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable long id) {

		Optional<User> user = userService.findById(id);

		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
    

	//Register new user. Can be done better, but idk how to check user params
	
	@PostMapping("/register")
	@ResponseStatus (HttpStatus.CREATED)
	public User registerNewUser(@RequestBody User user) {

		userService.save(user);

		return user;
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id) {

		try {
			userService.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

//not done yet
//Es necesario? A lo mejor obtener los torneos, parejas y partidos del usuario por separado si, pero esto?
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

	//This should be in tournamentREST controller, not here
	@PostMapping("/inscription/{idtourn}")
	public ResponseEntity<Object> inscriptionTournament (@PathVariable long idtourn, @RequestParam long id, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		User partner = userService.findById(id).get();

		if (principal != null) {
			User user = userService.findByName(principal.getName()).get();
			Tournament tournament = tournamentService.findById(idtourn).get();
			tournamentService.addParticipant(tournament, teamService.makeTeam(user, partner));

			URI location = fromCurrentRequest().build().toUri();
			return ResponseEntity.created(location).build();
			
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	//To update user. 
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) throws SQLException {

		if (userService.exist(id)) {

			if (updatedUser.getImage()) {
				// Maintain the same image loading it before updating the user
				User dbUser = userService.findById(id).orElseThrow();
				if (dbUser.getImage()) {
					updatedUser.setImageFile(BlobProxy.generateProxy(dbUser.getImageFile().getBinaryStream(),
					dbUser.getImageFile().length()));
				}
			}

			updatedUser.setId(id);
			userService.save(updatedUser);

			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else	{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@PostMapping("/image")
	public ResponseEntity<Object> uploadImage(@RequestParam MultipartFile imageFile, HttpServletRequest request)
			throws IOException {

		Principal principal = request.getUserPrincipal();

		if (principal != null){
			User user = userService.findByName(principal.getName()).get();
			URI location = fromCurrentRequest().build().toUri();

			user.setImage(true);
			user.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
			userService.save(user);

			return ResponseEntity.created(location).build();
		}
		else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}	
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		if (userService.exist(id)){
			User user = userService.findById(id).get();
			if (user.getImage()) {

				Resource file = new InputStreamResource(user.getImageFile().getBinaryStream());
	
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(user.getImageFile().length()).body(file);
	
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/image")
	public ResponseEntity<Object> deleteImage(HttpServletRequest request) throws IOException {
		Principal principal = request.getUserPrincipal();

		if (principal != null){
            User user = userService.findByName(principal.getName()).get();
            if (user.getImage()){
                user.setImageFile(null);
                user.setImage(false);
                userService.save(user);
                return new ResponseEntity<>(HttpStatus.OK);    
            } else {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
	}
}
