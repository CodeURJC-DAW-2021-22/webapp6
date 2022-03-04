package es.webapp6.Padelante.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;
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

	public Page<Tournament> listTournamentPageable(){
		return tournaments.findAllTournaments(PageRequest.of(0, 3));
	}


	public Page<Tournament> getTournaments(int page) {
		return tournaments.findAllTournaments(PageRequest.of(page, 3));
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

	public List<Tournament> getUserTournaments(User user){
		return tournaments.getUserTournaments(user);
	}


	public boolean isAnyUserOfTeamInTournament(Tournament tournament, Team team){
		List<Match> auxMatches = matches.getAuxMatches(tournament);
		User member1 = team.getUserA();
		User member2 = team.getUserB();

		for (int i = 0; i < auxMatches.size(); i++){
			Team teamOne = auxMatches.get(i).getTeamOne();
			Team teamTwo = auxMatches.get(i).getTeamTwo();
			if (teamOne.getUserA().getId() == member1.getId() || teamOne.getUserA().getId() == member2.getId() ||
				teamOne.getUserB().getId() == member1.getId() || teamOne.getUserB().getId() == member2.getId()){
				return true;
			} else {
				if (teamTwo.getUserA().getId() == member1.getId() || teamTwo.getUserA().getId() == member2.getId() ||
					teamTwo.getUserB().getId() == member1.getId() || teamTwo.getUserB().getId() == member2.getId()){
					return true;
				}
			}
		}
		return false;
	}

	public void addParticipant(Tournament tournament, Team team){
		List<Match> auxMatches = matches.getAuxMatches(tournament);

		if (tournament.getNumSignedUp() < tournament.getNumParticipants() && !isAnyUserOfTeamInTournament(tournament, team)){
			if (auxMatches.isEmpty() || !auxMatches.get(auxMatches.size()-1).getTeamTwo().isTbd()){
				Team teamTBD = teams.getTBDTeam().get(0);
				Match match = new Match(0, team, teamTBD, tournament);
				matches.save(match);
			} else {
				auxMatches.get(auxMatches.size()-1).setTeamTwo(team);
				matches.save(auxMatches.get(auxMatches.size()-1));
			}
			tournament.setNumSignedUp(tournament.getNumSignedUp()+1);
			tournaments.save(tournament);
		}
		
	}

	public void deleteParticipant(Tournament tournament, Team team){
		List<Match> auxMatches = matches.getTeamAuxMatches(tournament, team);
		Team teamTBD = teams.getTBDTeam().get(0);
		for (int i = 0; i < auxMatches.size(); i++){
			if (auxMatches.get(i).getTeamOne().getId() == team.getId()) {
				auxMatches.get(i).setTeamOne(teamTBD);
				tournament.setNumSignedUp(tournament.getNumSignedUp()-1);
				matches.save(auxMatches.get(i));
				tournaments.save(tournament);
			} else{
				if (auxMatches.get(i).getTeamTwo().getId() == team.getId()) {
					auxMatches.get(i).setTeamTwo(teamTBD);
					tournament.setNumSignedUp(tournament.getNumSignedUp()-1);
					matches.save(auxMatches.get(i));
					tournaments.save(tournament);
				}
			}
		}
	}

	private static double log(double num, int base) {
		if (num != 0){
			return (Math.log10(num) / Math.log10(base));
		} else {
			return 0;
		}
	}

	public static int calcSpotsRoundOne (Tournament tournament) {
		int exponentN = (int) log(tournament.getNumSignedUp() -1, 2);
		int exponentK = (int) log(tournament.getNumParticipants(), 2);
			
		while (exponentN < exponentK -1) {
			exponentK --;
		}
		return (int) Math.pow(2, exponentK);
	}

	public void generateEmptyBracket(Tournament tournament){
		int rounds = (int) log(calcSpotsRoundOne(tournament), 2);
		tournament.setRounds(rounds);

		int power = 0;
		for (int i = rounds; i >= 1; i--) {
			power = (int) Math.pow(2, i-1);
			for (int j = 1; j <= power; j++) {
				Team teamTBD = teams.getTBDTeam().get(0);
				Match match = new Match(i, teamTBD, teamTBD, tournament);
				matches.save(match);
			}
		}
		tournaments.save(tournament);
	}

	//Revisar saves

	public List<Team> getTeamsSignedUp(Tournament tournament){
		List<Match> auxMatches = matches.getAuxMatches(tournament);
		List<Team> teamsSignedUp = new ArrayList<Team>();
		Team teamOne = null;
		Team teamTwo = null;
		for (int i = 0; i < auxMatches.size(); i++){
			teamOne = auxMatches.get(i).getTeamOne();
			if (!teamOne.isTbd()){
				teamsSignedUp.add(teamOne);
			}
			teamTwo = auxMatches.get(i).getTeamTwo();
			if (!teamTwo.isTbd()){
				teamsSignedUp.add(teamTwo);
			}
		}
		return teamsSignedUp;
	}

	public void assignTeamsStart(Tournament tournament){
		List<Match> startRound = matches.getRoundMatches(tournament, tournament.getRounds());
		List<Team> teamsSignedUp = getTeamsSignedUp(tournament);
		Collections.shuffle(teamsSignedUp);

		for (int i = 0; i < startRound.size(); i++){
			startRound.get(i).setTeamOne(teamsSignedUp.get(0));
			teamsSignedUp.remove(0);
			matches.save(startRound.get(i));
		}

		int i = 0;
		int limit = (teamsSignedUp.size()+1)/2; 
		while (i < limit){
			startRound.get(i).setTeamTwo(teamsSignedUp.get(0));
			teamsSignedUp.remove(0);
			matches.save(startRound.get(i));
			i++;
		}
		i = startRound.size()/2;
		limit = teamsSignedUp.size() + i;
		while (i < limit){
			startRound.get(i).setTeamTwo(teamsSignedUp.get(0));
			teamsSignedUp.remove(0);
			matches.save(startRound.get(i));
			i++;
		}
	}

	public void moveNextRound(Tournament tournament, Match match, Team winner){
		List<Match> matchRound = matches.getRoundMatches(tournament, match.getRound());
		List<Match> nextRound = matches.getRoundMatches(tournament, match.getRound()-1);

		int i=0;
		while (!(matchRound.get(i).getId() == match.getId()) && i<matchRound.size()){
			i++;
		}
		int numMatch = (int) i/2;
		if (i%2 == 0) {
			nextRound.get(numMatch).setTeamOne(winner);
			matches.save(nextRound.get(numMatch));
		} else {
			nextRound.get(numMatch).setTeamTwo(winner);
			matches.save(nextRound.get(numMatch));
		}

	}

	public void setFreeWins(Tournament tournament){
		List<Match> startRound = matches.getRoundMatches(tournament, tournament.getRounds());
		Team teamOne = null;
		Team teamTwo = null;
		for (int i = 0; i < startRound.size(); i++){
			teamOne = startRound.get(i).getTeamOne();
			teamTwo = startRound.get(i).getTeamTwo();
			if (teamOne.isTbd()){
				moveNextRound(tournament, startRound.get(i), teamTwo);
			} else {
				if (teamTwo.isTbd()){
					moveNextRound(tournament, startRound.get(i), teamOne);
				}
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
