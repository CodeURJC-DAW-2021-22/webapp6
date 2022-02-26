package es.webapp6.Padelante.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

  //give a list of tournament where the team plays
  @Query("SELECT distinct t FROM Match m JOIN m.tournament t " +
      "WHERE m.teamOne = :team OR m.teamTwo = :team")
  public List<Tournament> getTournaments(Team team);

  List<Tournament> findByTournamentName(String tournamentName); 
}
