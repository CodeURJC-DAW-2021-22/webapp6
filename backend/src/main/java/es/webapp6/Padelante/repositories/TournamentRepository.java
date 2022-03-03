package es.webapp6.Padelante.repositories;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

  //give a list of tournament where the team plays
  @Query("SELECT distinct t FROM Match m JOIN m.tournament t " +
      "WHERE m.teamOne = :team OR m.teamTwo = :team")
  public List<Tournament> getTeamTournaments(Team team);

  @Query("SELECT distinct t FROM Match m JOIN m.tournament t " +
      "WHERE m.teamOne.userA = :user OR m.teamOne.userB = :user OR m.teamTwo.userA = :user OR m.teamTwo.userB = :user")
  public List<Tournament> getUserTournaments(User user);

  List<Tournament> findByTournamentName(String tournamentName); 

  Page<Tournament> findAll(Pageable pageable);
}
