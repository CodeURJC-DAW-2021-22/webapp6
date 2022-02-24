package es.webapp6.Padelante.service;

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



    public void registerNewUser(String userName, String encodedPassword){
        
        userRepository.save(new User(userName, passwordEncoder.encode(encodedPassword), "USER"));

    }

    public void save(User user) {
		userRepository.save(user);
	}
    
}
