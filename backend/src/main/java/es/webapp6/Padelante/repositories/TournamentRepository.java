package es.webapp6.Padelante.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.webapp6.Padelante.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{
    List<Tournament> findByTournamentName(String tournamentName);   

}




