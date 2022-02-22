package es.webapp6.Padelante.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.webapp6.Padelante.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

}
