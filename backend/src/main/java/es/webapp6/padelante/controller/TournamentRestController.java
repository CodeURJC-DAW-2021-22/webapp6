package es.webapp6.padelante.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.TeamService;
import es.webapp6.padelante.service.TournamentService;
import es.webapp6.padelante.service.UserService;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
    @Autowired
	private TournamentService tournamentService;	

    @Autowired
	private UserService userService;	

	@Autowired
	private MatchService matchService;	

	@Autowired
	private TeamService teamService;


	//get all users
    //Doesnt work on Postman because of PAGEABLE
	@GetMapping("?page={page}")
	public Page<Tournament> getTournaments(@PathVariable int page) {
		return tournamentService.getTournaments(page);
	}

	//get tournament by id
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Tournament>> getTournament(@PathVariable long id) {

		Optional<Tournament> tournament = tournamentService.findById(id);

		if (tournament != null) {
			return ResponseEntity.ok(tournament);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
    

	
}