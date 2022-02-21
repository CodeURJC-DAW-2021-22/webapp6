package es.webapp6.Padelante.controller;



import javax.annotation.PostConstruct;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToBytesTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.repositories.TournamentRepository;
import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Team;

import es.webapp6.Padelante.repositories.MatchRepository;
import es.webapp6.Padelante.repositories.TeamRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;







@Controller
public class indexController {
    
    // private Collection<Tournament> tournaments;

    // @Autowired
    // private TournamentRepository tournamentRepository;

    // @PostConstruct
    // public void init(){
    //     tournamentRepository.save(new Tournament("Álvaro", 5));
    //     tournamentRepository.save(new Tournament("Rubén", 5));
    //     tournamentRepository.save(new Tournament("Dani", 5));
    // }

    // @GetMapping("/")
    // public Collection<Tournament> getTournament() {
    //     tournaments = tournamentRepository.findByTournamentName("Dani");
    //     return tournaments;
    // }

    

    @Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private TeamRepository teamRepository;

    @Autowired
	private TournamentController tournamentController;
	
	@PostConstruct
	public void init() {
		
		Team t1 = new Team("Team1");
		Team t2 = new Team("Team2");
		Team t3 = new Team("Team3");
		
		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		
		// Tournament tournament = new Tournament("T",5);
        // Tournament tournament1= new Tournament("Torneo 1", 5);
        // Tournament tournament2= new Tournament("Torneo 2", 6);
        // Tournament tournament3= new Tournament("T11223344", 7);
        // Tournament tournament4= new Tournament("Torneo 4", 8);

		// tournamentRepository.save(tournament);
        // tournamentRepository.save(tournament1);
        // tournamentRepository.save(tournament2);
        // tournamentRepository.save(tournament3);
        // tournamentRepository.save(tournament4);
		
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

    @GetMapping("/")
    public String greeting(Model model) {
      
        
        List<Tournament> tourns = tournamentController.getTournament();
        

        model.addAttribute("tourns",tourns);// tournamentRepository.findByTournamentName("Dani").get(0).getTournamentName()
        return "main";
    }

    @GetMapping("/create_tournament")
    public String create_tournament(Model model) {
        //model.addAttribute();
        return "create-tournament";
    }

    @GetMapping("/errorPage")
    public String errorPage(Model model) {
       // model.addAttribute("tournamentName","Dani");// tournamentRepository.findByTournamentName("Dani").get(0).getTournamentName()
        return "errorPage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        //model.addAttribute("tournamentName","Dani");// tournamentRepository.findByTournamentName("Dani").get(0).getTournamentName()
        return "login";
    }
    @GetMapping("/match")
    public String match(Model model) {
        //model.addAttribute("tournamentName","Dani");// tournamentRepository.findByTournamentName("Dani").get(0).getTournamentName()
        return "match";
    }
    @GetMapping("/register")
    public String register(Model model) {
        //model.addAttribute("tournamentName","Dani");// tournamentRepository.findByTournamentName("Dani").get(0).getTournamentName()
        return "register";
    }
    @GetMapping("/tournament")
    public String tournament(Model model) {
        //model.addAttribute("tournamentName","Dani");// tournamentRepository.findByTournamentName("Dani").get(0).getTournamentName()
        return "tournament";
    }
    @GetMapping("/user_profile")
    public String user_profile(Model model) {
        //model.addAttribute("tournamentName","Dani");// tournamentRepository.findByTournamentName("Dani").get(0).getTournamentName()
        return "users-profile";
    }


    

   

    



    
}
