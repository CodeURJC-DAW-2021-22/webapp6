package es.webapp6.padelante;

import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import es.webapp6.padelante.model.Team;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.repositories.TeamRepository;
import es.webapp6.padelante.repositories.TournamentRepository;
import es.webapp6.padelante.repositories.UserRepository;
import es.webapp6.padelante.service.TournamentService;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.net.URISyntaxException;




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
	public void init() throws IOException,URISyntaxException{

		if (userRepository.count()>0){
			return;
		}
		//
		// ------ USERS AND TEAMS ------
		//

		User userNone = new User("none", passwordEncoder.encode("pass"), "none@correo.com", "None", "USER");
		userNone.setStatus(false);

		User user1 = new User("user", passwordEncoder.encode("pass"), "user@correo.com", "User", "USER");		
		User user2 = new User("admin", passwordEncoder.encode("adminpass"), "admin@correo.com", "Admin", "USER",
				"ADMIN");

		User user3 = new User("Paco", passwordEncoder.encode("pass"), "paco@correo.com", "Paco Navarro", "USER");
		User user4 = new User("KevinAnd", passwordEncoder.encode("pass"), "kevinand@correo.com", "Kevin Anderson",
				"USER");
		User user5 = new User("Dani", passwordEncoder.encode("pass"), "dani@correo.com", "Daniel Haro", "USER");
		User user6 = new User("Ruben", passwordEncoder.encode("pass"), "ruben@correo.com", "Ruben Catalan", "USER");
		User user7 = new User("Silvia", passwordEncoder.encode("pass"), "silvia@correo.com", "Silvia Ventura", "USER");
		User user8 = new User("Alvaro", passwordEncoder.encode("pass"), "alvaro@correo.com", "Alvaro Gonzalez", "USER");
		User user9 = new User("Sara", passwordEncoder.encode("pass"), "sara@correo.com", "Sara Gonzalez", "USER");
		User user10 = new User("Diego", passwordEncoder.encode("pass"), "diego@correo.com", "Diego del Amo", "USER");

		User user11 = new User("Pepe", passwordEncoder.encode("pass"), "pepe@correo.com", "Pepe Navarro", "USER");
		User user12 = new User("Francis", passwordEncoder.encode("pass"), "francis@correo.com", "Francis Anderson",
				"USER");
		User user13 = new User("Daniela", passwordEncoder.encode("pass"), "daniela@correo.com", "Daniela Murcia", "USER");
		User user14 = new User("Rubenchu", passwordEncoder.encode("pass"), "rubenchu@correo.com", "Rubenchu Medina", "USER");
		User user15 = new User("Salvador", passwordEncoder.encode("pass"), "salvador@correo.com", "Salvador Cabrejas",
				"USER");
		User user16 = new User("Paquito", passwordEncoder.encode("pass"), "paquito@correo.com", "Paquito Gonzalez",
				"USER");
		User user17 = new User("Alex", passwordEncoder.encode("pass"), "alex@correo.com", "Alex Terroba", "USER");
		User user18 = new User("Almudena", passwordEncoder.encode("pass"), "almudena@correo.com", "Almudena Arias", "USER");

		ArrayList<Integer> array = user1.getHistoricalKarma();
		array.add(550);
		array.add(450);
		array.add(500);
		array.add(375);
		array.add(400);
		array.add(450);
		array.add(500);
		array.add(525);
		array.add(540);
		user1.setHistoricalKarma(array);
		user1.setNumMatchesPlayed(9);
		user1.setNumWins(7);
		user1.setNumLoses(2);
		user1.setLocation("Madrid");
		user1.setCountry("Espa??a");

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

		//
		// ------ TOURNAMENTS AND MATCHES ------
		//

		String fecha1 = "16:00  16/12/2022";
		String fecha2 = "17:00  20/12/2022";

		String fecha3 = "17:00  08/10/2022";
		String fecha4 = "18:00  30/10/2022";

		String fecha5 = "18:00  10/08/2022";
		String fecha6 = "19:00  20/08/2022";

		Tournament tournament = new Tournament("Torneo la turra", 16,
				"Participa en el torneo de la turra y gana el prestigioso torneo donde se apuntan los mejores padeleros del momento",
				"Este torneo sigue las reglas oficiales del padel", "Madrid", fecha1, fecha2, 
				"user");
		Tournament tournament1 = new Tournament("Torneo Iberian Cup", 4, "Torneo peninsular donde juegan los mejores",
				"Este torneo sigue las reglas oficiales del padel espa??ol", "Mostoles", fecha3, fecha4,
				 "user");
		Tournament tournament2 = new Tournament("Torneo la Lora", 4, "Ven y juega",
				"Este torneo sigue las reglas oficiales del padel", "Sevilla", fecha5, fecha6, 
				"user");
		Tournament tournament3 = new Tournament("Copa Pist??n", 8, "Ven y juega las ic??nica copa de Cars, la pel??cula",
				"Este torneo sigue las reglas oficiales del padel espa??ol", "Valencia", fecha1, fecha2,
				 "user");
		Tournament tournament4 = new Tournament("Copa Arcoirirs", 16, "Ven y enfrentate a nuestros rivales en la copa m??s colorida",
				"Este torneo sigue las reglas oficiales del padel espa??ol", "Madrid", fecha3, fecha4,
				 "admin");
		Tournament tournament5 = new Tournament("Copa Alonso", 8, "Copa a toda velocidad, copa Alonso. Patrocinada por Fernando Alonso",
				"Este torneo sigue las reglas oficiales del padel espa??ol", "Ciudad Real", fecha5, fecha6,
				 "admin");
		Tournament tournament6 = new Tournament("Copa de vino", 16, "Ven a jugar y disfruta despu??s de una cata de vinos",
				"Este torneo sigue las reglas oficiales del padel espa??ol", "La Rioja", fecha1, fecha2,
				 "admin");
		Tournament tournament7 = new Tournament("Copa aqu?? copa all??", 16,
				"Este torneo sigue las reglas oficiales del padel espa??ol", "Y m??rate y m??rate...", "Almeria", fecha3, fecha4,
				 "admin");
		Tournament tournament8 = new Tournament("Torneo benefico XIX", 16,
				"Este torneo sigue las reglas oficiales del padel espa??ol", "Torneo con causa benefica, todos los ingresos genereados ser??n donados",
				 "Barcelona", fecha5, fecha6, "admin");
		Tournament tournament9 = new Tournament("Copa caliz de fuego", 2, "Solo podr??n participar los mayores de 16, aquellos que ganen conseguir??n la fama eterna.", 
				"Este torneo sigue las reglas oficiales del padel espa??ol", "Vitoria", fecha1, fecha2,
				 "admin");

		setTournamentImage(tournament, "/examples/copaTurra.jpg");
		setTournamentImage(tournament1, "/examples/iberianCup.jpg");
		setTournamentImage(tournament3, "/examples/copaPiston.jpg");
		setTournamentImage(tournament4, "/examples/copaArcoiris.jpg");
		setTournamentImage(tournament6, "/examples/copaVino.jpg");
		setTournamentImage(tournament9, "/examples/copaCalizFuego.jpg");
		

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

		Tournament tour = tournamentRepository.findByTournamentName("Torneo la turra").get(0);

		// Add a team to the tournament
		tournamentService.addParticipant(tour, t1);

		// As user1 is also in t2 y t3 and this user is already inscripted with t1, it doesnt
		// let t2 or t3 join the tournament
		tournamentService.addParticipant(tour, t2);
		tournamentService.addParticipant(tour, t3);

		// Add more teams to the tournament
		tournamentService.addParticipant(tour, t4);
		tournamentService.addParticipant(tour, t5);
		tournamentService.addParticipant(tour, t6);
		tournamentService.addParticipant(tour, t7);
		tournamentService.addParticipant(tour, t8);
		tournamentService.addParticipant(tour, t9);
		tournamentService.addParticipant(tour, t10);
		tournamentService.addParticipant(tour, t11);
	}
	
	private void setTournamentImage(Tournament tournament, String classpathResource) throws IOException {
		tournament.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		tournament.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

}
