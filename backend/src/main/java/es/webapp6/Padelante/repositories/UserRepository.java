package es.webapp6.Padelante.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.webapp6.Padelante.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // @Query("SELECT distint player FROM User player WHERE EXISTS team IN " + 
    // "(SELECT team FROM Team team WHERE player IN :u.teams.players)")
	// public List<User> getPlayerPairs(User u);

    // @Query("SELECT distint player FROM User player WHERE EXISTS " + 
    // "(SELECT team FROM Team team WHERE :u IN team.players AND player IN team.players)")
	// public List<User> getPlayerPairs2(User u);

    

    // @Query("SELECT distinct player FROM User player WHERE t.players != :u)")
	// public List<User> getPlayerPairsOLD(Team t, User u);

    Optional<User> findByName(String name);

}
