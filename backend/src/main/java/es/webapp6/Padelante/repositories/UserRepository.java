package es.webapp6.Padelante.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // @Query("SELECT distinct player FROM Team team, User player WHERE (:u IN team.players AND team.players != :u)")
	// public List<User> getPlayerPairs(User u);

    // @Query("SELECT distinct player FROM User player WHERE t.players != :u)")
	// public List<User> getPlayerPairs(Team t, User u);

    Optional<User> findByName(String name);

}
