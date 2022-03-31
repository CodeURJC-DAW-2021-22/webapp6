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
import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.TournamentService;
import es.webapp6.padelante.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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


	@Operation(summary = "Get all users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found a page of user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
		})
		
	//get all users
	@JsonView(User.Mostrar.class)
	@GetMapping("")
	public ResponseEntity<Page<User>> getAllUsers(
		@Parameter(description="Page number of the list of users") @RequestParam int page) {
		return ResponseEntity.ok(userService.getUsers(page));
	}

	@Operation(summary = "Get the user conected")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the connected user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "User conected not found", content = @Content) })

	// who is conected
	@JsonView(User.Mostrar.class)
	@GetMapping("/me")
	public ResponseEntity<User> getActiveUser(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			return ResponseEntity.ok(userService.findByName(principal.getName()).get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Get the user by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
	// get user by id
	@JsonView(User.Mostrar.class)
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) {

		if (userService.exist(id)) {
			User user = userService.findById(id).get();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Register a new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User registerd successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "400", description = "User couldn't register", content = @Content) })
	// Register new user
	@PostMapping("")
	public ResponseEntity<User> registerNewUser(@RequestBody User user) {
		user.setStatus(true);
		if (user.getName().isBlank() || userService.findByName(user.getName()).isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			
			User userNew = new User(user.getName(), passwordEncoder.encode(user.getEncodedPassword()), user.getEmail(),
					user.getRealName(), "USER");

			userService.save(userNew);
			String id = userService.findByName(user.getName()).get().getId().toString();
			URI location = fromCurrentRequest().path("/"+id).build().toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@Operation(summary = "Delete a user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User deleted succesfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "User wasn't found", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id, @RequestBody User user) {

		if (userService.exist(id) && !user.getStatus()) {
			userService.delete(userService.findById(id).get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Get the user pairs")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pairs founded", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "Pairs weren't found", content = @Content) })
	@JsonView(User.Mostrar.class)
	//find pairs
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

	@Operation(summary = "Get the tournaments where the user is inscribed")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the tournament", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "Tournament not found", content = @Content) })
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

	@Operation(summary = "Get the user's matches")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Match found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "Match not found", content = @Content) })
			@JsonView(User.Mostrar.class)

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

	@Operation(summary = "Update user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User updated correctly", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "401", description = "unauthorized to update the user", content = @Content) })
	// To update user.
	@JsonView(User.Mostrar.class)
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

	@Operation(summary = "upload user image")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "uploaded correctly", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "You are not authorized to upload the image", content = @Content) })
	@PostMapping("/image")
	public ResponseEntity<Object> uploadImage(@RequestParam MultipartFile imageFile, HttpServletRequest request)
			throws IOException, SQLException {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			User user = userService.findByName(principal.getName()).get();
			
			user.setImage(true);
			user.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
			userService.save(user);

			URI location = fromCurrentRequest().path("/"+user.getId()+"/image").build().toUri();
			return ResponseEntity.created(location).build();
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@Operation(summary = "Download user image")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Imaged downloaded successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "204", description = "Image field is empty", content = @Content),
			@ApiResponse(responseCode = "404", description = "Image not found", content = @Content)			
		})
			
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

	@Operation(summary = "Delete user image")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Imaged deleted successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "400", description = "Request cannot be processed", content = @Content),
			@ApiResponse(responseCode = "401", description = "You are not authorized to delete the image", content = @Content)			
		})
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
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
