package es.webapp6.Padelante.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.webapp6.Padelante.model.Team;
import es.webapp6.Padelante.model.User;
import es.webapp6.Padelante.repositories.UserRepository;

@Service 
public class UserService {

    @Autowired
	private UserRepository userRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
	private TeamService teamService;



    public void registerNewUser(String userName, String encodedPassword, String email, String realName){
        
        userRepository.save(new User(userName, passwordEncoder.encode(encodedPassword),email,realName, "USER"));

    }

    public void save(User user) {
		userRepository.save(user);
	}

    public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}

    public void delete(long id) {
        Optional<User> user = userRepository.findById(id);
        List<Team> playerTeams = teamService.getPlayerTeams(user.get());
			for(int i = 0; i<playerTeams.size();i++){
				//for the moment i take null, maybe delate this team
				playerTeams.get(i).setUserA(userRepository.findByName("none").get());
				playerTeams.get(i).setUserB(userRepository.findByName("none").get());
				teamService.save(playerTeams.get(i));
			}
		userRepository.deleteById(id);
	}

	public void calculateKarma(double karmaFromMatch, boolean winner, User user) {        
        int truncatedKarma; 
		double karma;  
		ArrayList<Integer> arrayKarma = user.getHistoricalKarma();     

        if(winner){
			karma = user.getKarma() + karmaFromMatch + calculateNumPlayedGamesFactor(user);            
        } else{
            karma = user.getKarma() - karmaFromMatch - calculateNumPlayedGamesFactor(user);
        }        
        truncatedKarma = (int) Math.round(karma);

		System.out.println("KarmaPrint: " + truncatedKarma);

		arrayKarma.add(truncatedKarma);
		if (arrayKarma.size() > 10){
			arrayKarma.remove(0);
		}
		user.setHistoricalKarma(arrayKarma);
		
		userRepository.save(user);
    }

	private double calculateNumPlayedGamesFactor(User user){
        if(user.getNumMatchesPlayed() == 1){
            return  1/Math.log(1.5) * 10;
        }
        else{
            return  1/Math.log(user.getNumMatchesPlayed()) * 10;
        }
    }
    
    public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

    

    public Page<User> listUserPageable(){
        return userRepository.findAllUsers(PageRequest.of(0, 6));
    }

	public Page<User> getUsers(int page) {
		return userRepository.findAllUsers(PageRequest.of(page, 4));
	}

	public Page<User> getUsersNoAdmin(int page) {
		return userRepository.findAllUsersNoAdmin(PageRequest.of(page, 6));
	}

	public Page<User> findPairsOf(int page, User user){
		return userRepository.findPairsOf(PageRequest.of(page, 4), user);
	}
}
