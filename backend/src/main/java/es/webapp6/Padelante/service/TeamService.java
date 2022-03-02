package es.webapp6.Padelante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.repositories.TeamRepository;

@Service 
public class TeamService {
    
    @Autowired
	private TeamRepository teamRepository;

    public void save(Team user) {
		teamRepository.save(user);
	}
    
    public Optional<Team> findById(long id) {
		return teamRepository.findById(id);
	}

    public List<Team> getTeams(Tournament t){
        return teamRepository.getTeams(t);
    }

    public List<Team> getPlayerTeams(User u){
        return teamRepository.getPlayerTeams(u);
    }

    public List<Team> getTBDTeam(){
        return teamRepository.getTBDTeam();
    }
}
