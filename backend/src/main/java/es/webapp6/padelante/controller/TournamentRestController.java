package es.webapp6.padelante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.TeamService;
import es.webapp6.padelante.service.TournamentService;
import es.webapp6.padelante.service.UserService;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
    @Autowired
	private TournamentService tournamentService;	

    @Autowired
	private UserService userService;	

	@Autowired
	private MatchService matchService;	

	@Autowired
	private TeamService teamService;

    
    
}
