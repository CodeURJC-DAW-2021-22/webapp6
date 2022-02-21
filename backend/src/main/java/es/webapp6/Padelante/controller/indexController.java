package es.webapp6.Padelante.controller;



import javax.annotation.PostConstruct;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToBytesTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.repositories.TournamentRepository;
import es.webapp6.Padelante.service.TournamentService;
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
	private TournamentService tournamentService;	
	
    @GetMapping("/")
    public String greeting(Model model) {     
        List<Tournament> tourns = tournamentService.getTournaments();      
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
