package es.webapp6.Padelante.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT distinct u FROM UserTable u, Team t WHERE (t.userA = u AND t.userB = :user) OR (t.userB = u AND t.userA = :user)")
	public List<User> findPairsOf(User user);

    Optional<User> findByName(String name);

    @Query("SELECT distinct u FROM UserTable u WHERE u.name <> 'none' ORDER BY id ASC")
    public Page<User> findAllUsers(Pageable pageable);

    Page<User> findAll(Pageable pageable);
}
