package es.webapp6.Padelante.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.webapp6.Padelante.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT distinct u FROM UserTable u, Team t WHERE (t.userA = u AND t.userB = :user) OR (t.userB = u AND t.userA = :user) AND u.status = TRUE")
	public Page<User> findPairsOf(Pageable pageable, User user);
    
    Optional<User> findByName(String name);

    @Query("SELECT distinct u FROM UserTable u WHERE u.status = TRUE AND u.name <> 'admin' ORDER BY id ASC")
    public Page<User> findAllUsersNoAdmin(Pageable pageable);

    @Query("SELECT distinct u FROM UserTable u WHERE u.status = TRUE ORDER BY id ASC")
    public Page<User> findAllUsers(Pageable pageable);

    Page<User> findAll(Pageable pageable);
    
}
