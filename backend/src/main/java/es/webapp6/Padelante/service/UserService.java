package es.webapp6.Padelante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.repositories.UserRepository;

@Service 
public class UserService {

    @Autowired
	private UserRepository userRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;



    public void registerNewUser(String userName, String encodedPassword, String email, String realName){
        
        userRepository.save(new User(userName, passwordEncoder.encode(encodedPassword),email,realName, "USER"));

    }

    public void save(User user) {
		userRepository.save(user);
	}

    public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}
    
}
