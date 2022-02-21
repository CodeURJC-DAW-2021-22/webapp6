package es.webapp6.Padelante.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.repositories.TournamentRepository;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {
    
    @Autowired
    private TournamentRepository tournaments;

    // @PostConstruct
    // public void init(){
    //     tournaments.save(new Tournament("T",5));
    // }

    @GetMapping("/")
	public Collection<Tournament> getTournament() {
		return tournaments.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tournament> getTournament(@PathVariable long id) {

		Optional<Tournament> tournament = tournaments.findById(id);

		if (tournament.isPresent()) {
			return ResponseEntity.ok(tournament.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {

		tournaments.save(tournament);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(tournament.getId()).toUri();

		return ResponseEntity.created(location).body(tournament);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tournament> replaceTournament(@PathVariable long id, @RequestBody Tournament newTournament) {

		if (tournaments.existsById(id)) {

			newTournament.setId(id);
			tournaments.save(newTournament);

			return ResponseEntity.ok(newTournament);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tournament> deletePost(@PathVariable long id) {

		Optional<Tournament> tournament = tournaments.findById(id);

		if (tournament.isPresent()) {
			tournaments.deleteById(id);
			return ResponseEntity.ok(tournament.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
