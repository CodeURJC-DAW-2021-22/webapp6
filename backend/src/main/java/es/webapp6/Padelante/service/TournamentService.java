package es.webapp6.Padelante.service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

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

    public void createTournament(String tournamentName, int numParticipants,
    String about,String ruleset,String location,Date inscriptionDate,
        Date startDate,String format,String owner){
        Tournament t1 = new Tournament(tournamentName, numParticipants,about,ruleset,location,inscriptionDate,startDate,format,owner);
        tournaments.save(t1);
    }

	public Optional<Tournament> findById(long id) {
		return tournaments.findById(id);
	}
	
	public boolean exist(long id) {
		return tournaments.existsById(id);
	}

	public List<Tournament> findAll() {
		return tournaments.findAll();
	}

	public void save(Tournament tourn) {
		tournaments.save(tourn);
	}

	public void delete(long id) {
		tournaments.deleteById(id);
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
