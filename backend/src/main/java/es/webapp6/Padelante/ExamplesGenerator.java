package es.webapp6.Padelante;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.repositories.MatchRepository;
import es.webapp6.Padelante.repositories.TeamRepository;
import es.webapp6.Padelante.repositories.TournamentRepository;
import es.webapp6.Padelante.repositories.UserRepository;

@Component
public class ExamplesGenerator {

	@Autowired
	private TournamentRepository tournamentRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @PostConstruct
	public void init() {

		//
		// ------ USERS AND TEAMS ------
		//

		User user1 = new User("user", passwordEncoder.encode("pass"),"user@correo.com","User", "USER");
		User user2 = new User("admin", passwordEncoder.encode("adminpass"),"admin@correo.com","Admin", "USER", "ADMIN");

		//Creo que se pedian solo 2 usuarios, asi que estos habria que borrarlos despues si es asi
		User user3 = new User("Paco", passwordEncoder.encode("pass"),"paco@correo.com","Paco Navarro", "USER");
		User user4 = new User("KevinAnd", passwordEncoder.encode("pass"),"kevinand@correo.com","Kevin Anderson", "USER");

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		
		Team TBD = new Team(true);
		Team t1 = new Team(false);
		Team t2 = new Team(false);
		Team t3 = new Team(false);

		t1.setUserA(user1);		
		t1.setUserB(user2);

		t2.setUserA(user3);		
		t2.setUserB(user4);

		t3.setUserA(user1);		
		t3.setUserB(user4);
		
		teamRepository.save(TBD);
		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);

		List<Team> teams = teamRepository.findAll();
		System.out.println("Teams: "+teams);
		
		List<User> users1 = userRepository.findPairsOf(user1);
		System.out.println("Pairs of User1: "+users1);
		
		List<User> users2 = userRepository.findPairsOf(user2);
		System.out.println("Pairs of User2: "+users2);
		
		List<User> users3 = userRepository.findPairsOf(user3);
		System.out.println("Pairs of User3: "+users3);
		
		List<User> users4 = userRepository.findPairsOf(user4);
		System.out.println("Pairs of User4: "+users4);





		//
		// ------ TOURNAMENTS AND MATCHES ------
		//
		
		Date fecha1 = new Date("12/16/2022 16:00");
		Date fecha2 = new Date("12/20/2022 17:00");
		
		Tournament tournament = new Tournament("Torneo 11", 5,"About5","RulesSet","Madrid11",fecha1,fecha2,"Format","user");
        Tournament tournament1= new Tournament("Torneo 22", 5,"About6","RulesSet","Madrid12",fecha1,fecha2,"Format","user");
        Tournament tournament2= new Tournament("Torneo 33", 6,"About7","RulesSet","Madrid13",fecha1,fecha2,"Format","user");
        Tournament tournament3= new Tournament("Torneo 44", 7,"About8","RulesSet","Madrid14",fecha1,fecha2,"Format","user");
        Tournament tournament4= new Tournament("Torneo 55", 8,"About9","RulesSet","Madrid15",fecha1,fecha2,"Format","user");
		Tournament tournament5 = new Tournament("Torneo 66", 5,"About5","RulesSet","Madrid11",fecha1,fecha2,"Format","user");
        Tournament tournament6= new Tournament("Torneo 77", 5,"About6","RulesSet","Madrid12",fecha1,fecha2,"Format","user");
        Tournament tournament7= new Tournament("Torneo 88", 6,"About7","RulesSet","Madrid13",fecha1,fecha2,"Format","user");
        Tournament tournament8= new Tournament("Torneo 99", 7,"About8","RulesSet","Madrid14",fecha1,fecha2,"Format","user");
        Tournament tournament9= new Tournament("Torneo 100", 8,"About9","RulesSet","Madrid15",fecha1,fecha2,"Format","user");
		
		tournamentRepository.save(tournament);
        tournamentRepository.save(tournament1);
        tournamentRepository.save(tournament2);
        tournamentRepository.save(tournament3);
        tournamentRepository.save(tournament4);
		tournamentRepository.save(tournament5);
        tournamentRepository.save(tournament6);
        tournamentRepository.save(tournament7);
        tournamentRepository.save(tournament8);
        tournamentRepository.save(tournament9);
		
		Match m1 = new Match(1, t1, t2, tournament);
		
		Match m2 = new Match(1, t2, t3, tournament);
		
		Match m3 = new Match(2, t1, t3, tournament);
		
		matchRepository.save(m1);
		matchRepository.save(m2);
		matchRepository.save(m3);
				
		List<Tournament> tournaments = tournamentRepository.getTeamTournaments(t1);
		
		System.out.println("Tournaments: "+tournaments);

		// List<User> players = userRepository.getPlayerPairs(u1);

		// System.out.println("Players: "+players);
		
		// List<Match> matches = matchRepository.getMatches(tournament);
		
		// System.out.println("Matches: "+matches);
		
		// List<Team> teams = teamRepository.getTeams(tournament);
		
		// System.out.println("Teams: "+teams);
	}
    
}
