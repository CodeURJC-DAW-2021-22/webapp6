package es.webapp6.Padelante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;

public interface TeamRepository  extends JpaRepository<Team,Long>{
	//it give a list of a team that play a tournament
    @Query("SELECT distinct team FROM Match m, Team team "
			+ "WHERE (m.teamOne = team OR m.teamTwo = team) AND  m.tournament = :t")
	public List<Team> getTeams(Tournament t);

	@Query("SELECT team FROM Team team WHERE :u IN team.players")
	public List<Team> getPlayerTeams(User u);
    
}
