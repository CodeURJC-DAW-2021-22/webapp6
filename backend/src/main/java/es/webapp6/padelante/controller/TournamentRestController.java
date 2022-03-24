package es.webapp6.padelante.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.service.TournamentService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;


@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
    @Autowired
	private TournamentService tournamentService;

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
	
	@PostMapping("")
	@ResponseStatus (HttpStatus.CREATED)
	public Tournament createTournament(@RequestBody Tournament tournament) {

		tournamentService.save(tournament);

		return tournament;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tournament> deleteTournament(@PathVariable long id) {

		try {
			tournamentService.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}