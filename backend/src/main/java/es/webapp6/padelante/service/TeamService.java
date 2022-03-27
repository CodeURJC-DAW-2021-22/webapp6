package es.webapp6.padelante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.webapp6.padelante.model.Team;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.repositories.TeamRepository;

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

    public boolean exist(long id) {
		return teamRepository.existsById(id);
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

    public List<Team> getParticipantsOfTournament(Tournament t){
        return teamRepository.getParticipantsOfTournament(t);
    }

    public List<Team> getPlayersTeams(User u1, User u2){
        return getPlayersTeams(u1, u2);
    }

    public Team makeTeam (User u1, User u2){
        List<Team> teams = teamRepository.getPlayersTeams(u1, u2);
        if (teams.size() > 0){
            return teams.get(0);
        } else {
            Team newteam = new Team(false, u1, u2);
            teamRepository.save(newteam);
            return newteam;
        }
    }
}
