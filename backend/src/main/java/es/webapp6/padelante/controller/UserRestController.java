package es.webapp6.padelante.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import es.webapp6.padelante.model.Match;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.model.Views;
import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.TournamentService;
import es.webapp6.padelante.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;



import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import com.fasterxml.jackson.annotation.JsonView;


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
	private PasswordEncoder passwordEncoder;
<<<<<<< HEAD
	
	//who is conected
	@JsonView(Views.Mostrar.class)
=======

	@Operation(summary = "Get the user conected")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "User conected not found", content = @Content) })

	// who is conected
>>>>>>> 20494030577c9f2e0e263d51668ee21dc4fc7ece
	@GetMapping("/me")
	public ResponseEntity<User> getActiveUser(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			return ResponseEntity.ok(userService.findByName(principal.getName()).get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

<<<<<<< HEAD
	//get all users
	@JsonView(Views.Mostrar.class)
=======
	// get all users
>>>>>>> 20494030577c9f2e0e263d51668ee21dc4fc7ece
	@GetMapping("")
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam int page) {
		return ResponseEntity.ok(userService.getUsers(page));
	}

<<<<<<< HEAD
	//get user by id
	@JsonView(Views.Mostrar.class)
=======
	// get user by id
>>>>>>> 20494030577c9f2e0e263d51668ee21dc4fc7ece
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) {

		if (userService.exist(id)) {
			User user = userService.findById(id).get();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// Register new user
	@PostMapping("")
	public ResponseEntity<User> registerNewUser(@RequestBody User user) {
		user.setStatus(true);
		if (user.getName().isBlank() || userService.findByName(user.getName()).isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		} else {
			URI location = fromCurrentRequest().build().toUri();
			User userNew = new User(user.getName(), passwordEncoder.encode(user.getEncodedPassword()), user.getEmail(),
					user.getRealName(), "USER");

			userService.save(userNew);
			return ResponseEntity.created(location).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id, @RequestBody User user) {

		if (userService.exist(id) && !user.getStatus()) {
			userService.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(Views.Mostrar.class)
	@GetMapping("/me/pairs")
	public ResponseEntity<Page<User>> getUserPairs(@RequestParam int page, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Page<User> userPairs = userService.findPairsOf(page, userService.findByName(principal.getName()).get());
			return new ResponseEntity<>(userPairs, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/me/tournaments")
	public ResponseEntity<Page<Tournament>> getUserTournaments(@RequestParam int page, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Page<Tournament> userTourns = tournamentService.findUserTournaments(page,
					userService.findByName(principal.getName()).get());
			return new ResponseEntity<>(userTourns, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(Views.Mostrar.class)
	@GetMapping("/me/matches")
	public ResponseEntity<List<Match>> getUserMatches(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			List<Match> matches = matchService.getUserMatches(userService.findByName(principal.getName()).get());
			return new ResponseEntity<>(matches, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// To update user.
	@PutMapping("")
	public ResponseEntity<User> updateUser(@RequestBody User updatedUser, HttpServletRequest request)
			throws SQLException {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			User dbUser = userService.findByName(principal.getName()).get();

			// Data that must not change because user could be inconsistent
			updatedUser.setId(dbUser.getId());
			updatedUser.setName(dbUser.getName());
			updatedUser.setEmail(dbUser.getEmail());
			updatedUser.setNumWins(dbUser.getNumWins());
			updatedUser.setNumLoses(dbUser.getNumLoses());
			updatedUser.setNumMatchesPlayed(dbUser.getNumMatchesPlayed());
			updatedUser.setHistoricalKarma(dbUser.getHistoricalKarma());
			updatedUser.setStatus(dbUser.getStatus());
			updatedUser.setEncodedPassword(dbUser.getEncodedPassword());
			updatedUser.setRoles(dbUser.getRoles());
			if (dbUser.getImage()) {
				updatedUser.setImageFile(BlobProxy.generateProxy(dbUser.getImageFile().getBinaryStream(),
						dbUser.getImageFile().length()));
				updatedUser.setImage(true);
			}

			userService.save(updatedUser);

			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/image")
	public ResponseEntity<Object> uploadImage(@RequestParam MultipartFile imageFile, HttpServletRequest request)
			throws IOException {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			User user = userService.findByName(principal.getName()).get();
			URI location = fromCurrentRequest().build().toUri();

			user.setImage(true);
			user.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
			userService.save(user);

			return ResponseEntity.created(location).build();
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		if (userService.exist(id)) {
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

		if (principal != null) {
			User user = userService.findByName(principal.getName()).get();
			if (user.getImage()) {
				user.setImageFile(null);
				user.setImage(false);
				userService.save(user);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
