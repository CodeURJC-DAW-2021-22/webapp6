package es.webapp6.Padelante.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;

public interface MatchRepository extends JpaRepository<Match,Long> {
    //it give me a list of the matches at tournament t
    @Query("SELECT m FROM Match m WHERE m.tournament = :t")
	public List<Match> getMatches(Tournament t);

    @Query("SELECT m FROM Match m WHERE m.tournament = :t AND m.round = 0 ORDER BY id ASC")
	public List<Match> getAuxMatches(Tournament t);

    @Query("SELECT m FROM Match m WHERE m.tournament = :t AND m.round = :r ORDER BY id ASC")
	public List<Match> getRoundMatches(Tournament t, int r);

    // @Query("SELECT m FROM Match m WHERE m.teamOne = :t OR m.teamTwo = :t")
	// public List<Match> getTeamMatches(Team t);

    @Query("SELECT m FROM Match m WHERE (m.teamOne = :t OR m.teamTwo = :t) AND m.round != 0 ORDER BY id ASC")
	public List<Match> getTeamMatches(Team t);

    //to get all the matches I have
    @Query("""
        SELECT m FROM Match m WHERE (m.teamOne.userA = :user OR m.teamOne.userB = :user
        OR m.teamTwo.userA = :user OR m.teamTwo.userB = :user)AND m.round != 0 AND m.hasWinner = FALSE
        AND m.teamOne.tbd = FALSE AND m.teamTwo.tbd = FALSE ORDER BY id ASC
     """)
	public List<Match> getUserMatches(User user);
    
    @Query("""
        SELECT m FROM Match m WHERE (m.teamOne = :t OR m.teamTwo = :t) AND m.round = 0
        AND m.tournament = :tour ORDER BY id ASC
        """)
	public List<Match> getTeamAuxMatches(Tournament tour, Team t);
    
}
