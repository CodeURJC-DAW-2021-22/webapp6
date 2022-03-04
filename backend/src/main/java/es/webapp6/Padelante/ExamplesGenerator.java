package es.webapp6.Padelante;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.repositories.TeamRepository;
import es.webapp6.Padelante.repositories.TournamentRepository;
import es.webapp6.Padelante.repositories.UserRepository;
import es.webapp6.Padelante.service.TournamentService;

@Component
public class ExamplesGenerator {

	@Autowired
	private TournamentRepository tournamentRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private TournamentService tournamentService;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @PostConstruct
	public void init() {

		//
		// ------ USERS AND TEAMS ------
		//

		User userNone = new User("none", passwordEncoder.encode("pass"),"none@correo.com","None", "USER");

		User user1 = new User("user", passwordEncoder.encode("pass"),"user@correo.com","User", "USER");
		User user2 = new User("admin", passwordEncoder.encode("adminpass"),"admin@correo.com","Admin", "USER", "ADMIN");

		//Creo que se pedian solo 2 usuarios, asi que estos habria que borrarlos despues si es asi
		User user3 = new User("Paco", passwordEncoder.encode("pass"),"paco@correo.com","Paco Navarro", "USER");
		User user4 = new User("KevinAnd", passwordEncoder.encode("pass"),"kevinand@correo.com","Kevin Anderson", "USER");
		User user5 = new User("Dani", passwordEncoder.encode("pass"),"dani@correo.com","Daniel Haro", "USER");
		User user6 = new User("Ruben", passwordEncoder.encode("pass"),"ruben@correo.com","Ruben Catalan", "USER");
		User user7 = new User("Silvia", passwordEncoder.encode("pass"),"silvia@correo.com","Silvia Ventura", "USER");
		User user8 = new User("Alvaro", passwordEncoder.encode("pass"),"alvaro@correo.com","Alvaro Gonzalez", "USER");
		User user9 = new User("Sara", passwordEncoder.encode("pass"),"sara@correo.com","Sara Gonzalez", "USER");
		User user10 = new User("Diego", passwordEncoder.encode("pass"),"diego@correo.com","Diego del Amo", "USER");

		User user11 = new User("Paco1", passwordEncoder.encode("pass"),"paco@correo.com","Paco Navarro", "USER");
		User user12 = new User("KevinAnd1", passwordEncoder.encode("pass"),"kevinand@correo.com","Kevin Anderson", "USER");
		User user13 = new User("Dani1", passwordEncoder.encode("pass"),"dani@correo.com","Daniel Haro", "USER");
		User user14 = new User("Ruben1", passwordEncoder.encode("pass"),"ruben@correo.com","Ruben Catalan", "USER");
		User user15 = new User("Silvia1", passwordEncoder.encode("pass"),"silvia@correo.com","Silvia Ventura", "USER");
		User user16 = new User("Alvaro1", passwordEncoder.encode("pass"),"alvaro@correo.com","Alvaro Gonzalez", "USER");
		User user17 = new User("Sara1", passwordEncoder.encode("pass"),"sara@correo.com","Sara Gonzalez", "USER");
		User user18 = new User("Diego1", passwordEncoder.encode("pass"),"diego@correo.com","Diego del Amo", "USER");
		user1.setNumWins(10);
		user1.setNumLoses(5);

		userRepository.save(userNone);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		userRepository.save(user5);
		userRepository.save(user6);
		userRepository.save(user7);
		userRepository.save(user8);
		userRepository.save(user9);
		userRepository.save(user10);
		userRepository.save(user11);
		userRepository.save(user12);
		userRepository.save(user13);
		userRepository.save(user14);
		userRepository.save(user15);
		userRepository.save(user16);
		userRepository.save(user17);
		userRepository.save(user18);
		
		Team TBD = new Team(true, userNone, userNone);
		Team t1 = new Team(false, user1, user2);
		Team t2 = new Team(false, user1, user3);
		Team t3 = new Team(false, user1, user4);
		Team t4 = new Team(false, user3, user4);
		Team t5 = new Team(false, user5, user6);
		Team t6 = new Team(false, user7, user8);
		Team t7 = new Team(false, user9, user10);
		Team t8 = new Team(false, user11, user12);
		Team t9 = new Team(false, user13, user14);
		Team t10 = new Team(false, user15, user16);
		Team t11 = new Team(false, user17, user18);
		
		teamRepository.save(TBD);
		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		teamRepository.save(t4);
		teamRepository.save(t5);
		teamRepository.save(t6);
		teamRepository.save(t7);
		teamRepository.save(t8);
		teamRepository.save(t9);
		teamRepository.save(t10);
		teamRepository.save(t11);

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
		
		Tournament tournament = new Tournament("Torneo 11", 8,"About1","Ruleset1","Madrid",fecha1,fecha2,"Simple Tournament","user");
        Tournament tournament1= new Tournament("Torneo 22", 4,"About2","Ruleset2","Madrid",fecha1,fecha2,"Simple Tournament","user");
        Tournament tournament2= new Tournament("Torneo 33", 4,"About3","Ruleset3","Madrid",fecha1,fecha2,"Simple Tournament","user");
        Tournament tournament3= new Tournament("Torneo 44", 8,"About4","Ruleset4","Madrid",fecha1,fecha2,"Simple Tournament","user");
        Tournament tournament4= new Tournament("Torneo 55", 8,"About5","Ruleset5","Madrid",fecha1,fecha2,"Simple Tournament","admin");
		Tournament tournament5 = new Tournament("Torneo 66", 8,"About6","Ruleset6","Madrid",fecha1,fecha2,"Simple Tournament","admin");
        Tournament tournament6= new Tournament("Torneo 77", 16,"About7","Ruleset7","Madrid",fecha1,fecha2,"Simple Tournament","admin");
        Tournament tournament7= new Tournament("Torneo 88", 16,"About8","Ruleset8","Madrid",fecha1,fecha2,"Simple Tournament","admin");
        Tournament tournament8= new Tournament("Torneo 99", 16,"About9","Ruleset9","Madrid",fecha1,fecha2,"Simple Tournament","admin");
        Tournament tournament9= new Tournament("Torneo 100", 2,"About10","Ruleset10","Madrid",fecha1,fecha2,"Simple Tournament","admin");
		
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
				
		List<Tournament> tournaments = tournamentRepository.getTeamTournaments(t1);
		
		System.out.println("Tournaments: "+tournaments);




		Tournament tour = tournamentRepository.findByTournamentName("Torneo 11").get(0);

		//Añadimos particpantes al torneo
		tournamentService.addParticipant(tour, t1);

		//Como el user1 está en t2 y t3 y ese usuario ya está inscrito con t1, no deja incribir a t2 y t3
		tournamentService.addParticipant(tour, t2);
		tournamentService.addParticipant(tour, t3);

		//Añadimos mas particpantes al torneo
		tournamentService.addParticipant(tour, t4);
		tournamentService.addParticipant(tour, t5);
		tournamentService.addParticipant(tour, t6);
		tournamentService.addParticipant(tour, t7);
		tournamentService.addParticipant(tour, t8);
		tournamentService.addParticipant(tour, t9);
		tournamentService.addParticipant(tour, t10);

		// //Intentamos borrar un equipo no inscrito
		// tournamentService.deleteParticipant(tour, t3);

		// //Borramos un equipo inscrito
		tournamentService.deleteParticipant(tour, t1);

		// //Volvemos a inscribir al equipo que eliminado
		tournamentService.addParticipant(tour, t1);

		// //Intentamos inscribir más equipos de los que se permiten en el torneo (4)
		tournamentService.addParticipant(tour, t11);

		tournamentService.generateEmptyBracket(tour);
		tournamentService.assignTeamsStart(tour);
		tournamentService.setFreeWins(tour);
		



		

		// List<User> players = userRepository.getPlayerPairs(u1);

		// System.out.println("Players: "+players);
		
		// List<Match> matches = matchRepository.getMatches(tournament);
		
		// System.out.println("Matches: "+matches);
		
		// List<Team> teams = teamRepository.getTeams(tournament);
		
		// System.out.println("Teams: "+teams);
	}
    
}
