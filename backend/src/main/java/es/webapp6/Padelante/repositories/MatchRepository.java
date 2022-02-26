package es.webapp6.Padelante.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Tournament;

public interface MatchRepository extends JpaRepository<Match,Long> {
    //it give me a list of the matches at tournament t
    @Query("SELECT m FROM Match m WHERE m.tournament = :t")
	public List<Match> getMatches(Tournament t);
    
}
