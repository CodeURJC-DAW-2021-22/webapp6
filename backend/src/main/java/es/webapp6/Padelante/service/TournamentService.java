package es.webapp6.Padelante.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		return tournaments.findAll(PageRequest.of(0, 3));
	}


	public Page<Tournament> getTournaments(int page) {
		return tournaments.findAll(PageRequest.of(page, 3));
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

	public boolean isAnyUserOfTeamInTournament(Tournament tournament, Team team){
		List<Match> auxMatches = matches.getAuxMatches(tournament);
		User member1 = team.getPlayers().get(0);
		User member2 = team.getPlayers().get(1);

		for (int i = 0; i < auxMatches.size(); i++){
			List<User> playersTeamOne = auxMatches.get(i).getTeamOne().getPlayers();
			List<User> playersTeamTwo = auxMatches.get(i).getTeamTwo().getPlayers();
			if (playersTeamOne.get(0) == member1 || playersTeamOne.get(0) == member2 ||
			playersTeamTwo.get(1) == member1 || playersTeamTwo.get(1) == member2){
				return true;
			}
		}
		return false;
	}

	public void addParticipant(Tournament tournament, Team team){
		List<Match> auxMatches = matches.getAuxMatches(tournament);

		if (!isAnyUserOfTeamInTournament(tournament, team)){
			if (auxMatches.isEmpty() || !auxMatches.get(auxMatches.size()-1).getTeamTwo().isTbd()){
				Team teamTwo = new Team(true);
				teams.save(teamTwo);
				Match match = new Match(0, team, teamTwo, tournament);
				matches.save(match);
			} else {
				teams.delete(auxMatches.get(auxMatches.size()-1).getTeamTwo());
				auxMatches.get(auxMatches.size()-1).setTeamTwo(team);
				matches.save(auxMatches.get(auxMatches.size()-1));
			}
			tournament.setNumSignedUp(tournament.getNumSignedUp()+1);
			tournaments.save(tournament);
		}
		
	}

	public void deleteParticipant(Tournament tournament, Team team){
		List<Match> auxMatches = matches.getTeamAuxMatches(tournament, team);
		Team teamAux = new Team(true);
		for (int i = 0; i < auxMatches.size(); i++){
			if (auxMatches.get(i).getTeamOne() == team) {
				teams.save(teamAux);
				auxMatches.get(i).setTeamOne(teamAux);
				tournament.setNumSignedUp(tournament.getNumSignedUp()-1);
				matches.save(auxMatches.get(i));
				tournaments.save(tournament);
			} else{
				if (auxMatches.get(i).getTeamTwo() == team) {
					teams.save(teamAux);
					auxMatches.get(i).setTeamTwo(teamAux);
					tournament.setNumSignedUp(tournament.getNumSignedUp()-1);
					matches.save(auxMatches.get(i));
					tournaments.save(tournament);
				}
			}
		}
	}

	private static double log(double num, int base) {
		return (Math.log10(num) / Math.log10(base));
	}

	public static int calcSpotsRoundOne (Tournament tournament) {
		int exponentN = (int) log(tournament.getNumSignedUp(), 2);
		int exponentK = (int) log(tournament.getNumParticipants(), 2);
			
		while (exponentN < exponentK - 1) {
			exponentK --;
		}
		return (int) Math.pow(2, exponentK);
	}

	public void generateEmptyBracket(Tournament tournament){
		int rounds = (int) log(calcSpotsRoundOne(tournament), 2);
		tournament.setRounds(rounds);

		int power = 0;
		for (int i = rounds; i >= 1; i--) {
			power = (int) Math.pow(2, i);
			for (int j = 1; j <= power; j++) {
				Team teamOne = new Team(true);
				Team teamTwo = new Team(true);
				teams.save(teamOne);
				teams.save(teamTwo);
				Match match = new Match(i, teamOne, teamTwo, tournament);
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
			teamTwo = auxMatches.get(i).getTeamOne();
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
			teams.delete(startRound.get(i).getTeamOne());
			startRound.get(i).setTeamOne(teamsSignedUp.get(0));
			teamsSignedUp.remove(0);
			matches.save(startRound.get(i));
		}

		List<Team> teamsUp = teamsSignedUp.subList(0, (int) (teamsSignedUp.size()/2)+(1/2));
		List<Team> teamsDown = teamsSignedUp.subList
			((int) (teamsSignedUp.size()/2)+(1/2), (int) teamsSignedUp.size());

		int i = 0;
		while (!teamsUp.isEmpty()){
			teams.delete(startRound.get(i).getTeamTwo());
			startRound.get(i).setTeamTwo(teamsUp.get(0));
			teamsUp.remove(0);
			matches.save(startRound.get(i));
			i++;
		}
		i = startRound.size()/2;
		while (!teamsDown.isEmpty()){
			teams.delete(startRound.get(i).getTeamTwo());
			startRound.get(i).setTeamTwo(teamsDown.get(0));
			teamsDown.remove(0);
			matches.save(startRound.get(i));
			i++;
		}
	}

	public void moveNextRound(Tournament tournament, Match match, Team team){
		List<Match> matchRound = matches.getRoundMatches(tournament, match.getRound());
		List<Match> nextRound = matches.getRoundMatches(tournament, match.getRound()-1);
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
