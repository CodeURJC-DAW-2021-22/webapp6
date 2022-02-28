package es.webapp6.Padelante.service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.repositories.MatchRepository;
import es.webapp6.Padelante.repositories.TeamRepository;
import es.webapp6.Padelante.repositories.TournamentRepository;


@Service
public class TournamentService {

    @Autowired
	private TournamentRepository tournaments;

	@Autowired
	private MatchRepository matches;
	
	@Autowired
	private TeamRepository teams;


	public List<Tournament> getTournaments() {
		return tournaments.findAll();
	}

	public List<Tournament> getTeamTournaments(Team team){
		return tournaments.getTeamTournaments(team);
	}

	public List<Tournament> getTournamentByName(String tournamentName){
		return tournaments.findByTournamentName(tournamentName);
	}

	public Optional<Tournament> findById(long id) {
		return tournaments.findById(id);
	}
	
	public boolean exist(long id) {
		return tournaments.existsById(id);
	}

	public void save(Tournament tourn) {
		tournaments.save(tourn);
	}

	public void delete(long id) {
		tournaments.deleteById(id);
	}

    public void createTournament(String tournamentName, int numParticipants,
    String about,String ruleset,String location,Date inscriptionDate,
        Date startDate,String format,String owner){
        Tournament t1 = new Tournament(tournamentName, numParticipants,about,ruleset,location,inscriptionDate,startDate,format,owner);
        tournaments.save(t1);
    }

	private static double log(double num, int base) {
		return (Math.log10(num) / Math.log10(base));
	}

	public static int calcSpotsRoundOne (int numEquipos, int numParticipants) {
		int exponenteN = (int) log(numEquipos, 2);
		int exponenteK = (int) log(numParticipants, 2);
			
		while (exponenteN < exponenteK - 1) {
			exponenteK --;
		}
		return (int) Math.pow(2, exponenteK);
	}

	public void generateEmptyBracket(Tournament tournament){
		int rondas = (int) log(calcSpotsRoundOne(teams.getTeams(tournament).size(),
		tournament.getNumParticipants()), 2);

		int potencia = 0;
		for (int i = rondas; i >= 1; i = i - 1) {
			potencia = (int) Math.pow(2, i);
			for (int j = 1; j <= potencia; j = j + 1) {
				Team teamOne = new Team(true);
				Team teamTwo = new Team(true);
				Match match = new Match(i, teamOne, teamTwo, tournament);
				matches.save(match);
			}
		}

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
