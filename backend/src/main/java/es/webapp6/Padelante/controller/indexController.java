package es.webapp6.Padelante.controller;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.repositories.TournamentRepository;

@Controller
public class indexController {
    
    private Collection<Tournament> tournaments;

    @Autowired
    private TournamentRepository tournamentRepository;

    @PostConstruct
    public void init(){
        tournamentRepository.save(new Tournament("Álvaro", 5));
        tournamentRepository.save(new Tournament("Rubén", 5));
        tournamentRepository.save(new Tournament("Dani", 5));
    }

    // @GetMapping("/")
    // public Collection<Tournament> getTournament() {
    //     tournaments = tournamentRepository.findByTournamentName("Dani");
    //     return tournaments;
    // }

    
    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("tournamentName", tournamentRepository.findByTournamentName("Dani").get(0).getTournamentName());
        return "main";
    }
}
