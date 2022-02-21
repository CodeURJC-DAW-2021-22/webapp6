package es.webapp6.Padelante;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.repositories.MatchRepository;
import es.webapp6.Padelante.repositories.TeamRepository;
import es.webapp6.Padelante.repositories.TournamentRepository;

@Controller
public class ExamplesGenerator {

	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private TeamRepository teamRepository;


    @PostConstruct
	public void init() {
		
		Team t1 = new Team("Team1");
		Team t2 = new Team("Team2");
		Team t3 = new Team("Team3");
		
		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		
		Tournament tournament = new Tournament("Torneo 11",5);
        Tournament tournament1= new Tournament("Torneo 22", 5);
        Tournament tournament2= new Tournament("Torneo 33", 6);
        Tournament tournament3= new Tournament("Torneo 44", 7);
        Tournament tournament4= new Tournament("Torneo 55", 8);

		tournamentRepository.save(tournament);
        tournamentRepository.save(tournament1);
        tournamentRepository.save(tournament2);
        tournamentRepository.save(tournament3);
        tournamentRepository.save(tournament4);
		
		Match m1 = new Match("M1");
		m1.setTeamOne(t1);
		m1.setTeamTwo(t2);
		
		Match m2 = new Match("M2");
		m2.setTeamOne(t2);
		m2.setTeamTwo(t3);
		
		Match m3 = new Match("M3");
		m3.setTeamOne(t1);
		m3.setTeamTwo(t3);
		
		// m1.setTournament(tournament);
		// m2.setTournament(tournament);
		// m3.setTournament(tournament);
		
		matchRepository.save(m1);
		matchRepository.save(m2);
		matchRepository.save(m3);
				
		List<Tournament> tournaments = tournamentRepository.getTournaments(t1);
		
		System.out.println("Tournaments: "+tournaments);
		
		// List<Match> matches = matchRepository.getMatches(tournament);
		
		// System.out.println("Matches: "+matches);
		
		// List<Team> teams = teamRepository.getTeams(tournament);
		
		// System.out.println("Teams: "+teams);
	}
    
}
