package es.webapp6.Padelante.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.repositories.TournamentRepository;


@Service
public class TournamentService {

    @Autowired
	private TournamentRepository tournaments;


	public List<Tournament> getTournaments() {
		return tournaments.findAll();
	}


	// public ResponseEntity<Tournament> getTournament(@PathVariable long id) {

	// 	Optional<Tournament> tournament = tournaments.findById(id);

	// 	if (tournament.isPresent()) {
	// 		return ResponseEntity.ok(tournament.get());
	// 	} else {
	// 		return ResponseEntity.notFound().build();
	// 	}
	// }    


	// public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {

	// 	tournaments.save(tournament);

	// 	URI location = fromCurrentRequest().path("/{id}").buildAndExpand(tournament.getId()).toUri();

	// 	return ResponseEntity.created(location).body(tournament);
	// }


	// public ResponseEntity<Tournament> replaceTournament(@PathVariable long id, @RequestBody Tournament newTournament) {

	// 	if (tournaments.existsById(id)) {

	// 		newTournament.setId(id);
	// 		tournaments.save(newTournament);

	// 		return ResponseEntity.ok(newTournament);
	// 	} else {
	// 		return ResponseEntity.notFound().build();
	// 	}
	// }


	// public ResponseEntity<Tournament> deleteTournament(@PathVariable long id) {

	// 	Optional<Tournament> tournament = tournaments.findById(id);

	// 	if (tournament.isPresent()) {
	// 		tournaments.deleteById(id);
	// 		return ResponseEntity.ok(tournament.get());
	// 	} else {
	// 		return ResponseEntity.notFound().build();
	// 	}
	// }
    
}
