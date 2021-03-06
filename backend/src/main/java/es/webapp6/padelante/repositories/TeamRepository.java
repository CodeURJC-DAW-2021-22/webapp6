package es.webapp6.padelante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.webapp6.padelante.model.Team;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;

public interface TeamRepository  extends JpaRepository<Team,Long>{
	
    @Query("SELECT distinct team FROM Match m, Team team "
			+ "WHERE (m.teamOne = team OR m.teamTwo = team) AND  m.tournament = :t")
	public List<Team> getTeams(Tournament t);

	@Query("SELECT team FROM Team team WHERE (team.userA = :u OR team.userB = :u)")
	public List<Team> getPlayerTeams(User u);

	@Query("SELECT team FROM Team team WHERE team.tbd = TRUE")
	public List<Team> getTBDTeam();    

	@Query("SELECT distinct team FROM Match m, Team team "
	+ "WHERE (m.teamOne = team OR m.teamTwo = team) AND team.tbd = FALSE AND  m.tournament = :t")
	public List<Team> getParticipantsOfTournament(Tournament t); 
	
	@Query("SELECT team FROM Team team WHERE (team.userA = :u1 OR team.userB = :u1) AND (team.userA = :u2 OR team.userB = :u2)")
	public List<Team> getPlayersTeams(User u1, User u2);
}
