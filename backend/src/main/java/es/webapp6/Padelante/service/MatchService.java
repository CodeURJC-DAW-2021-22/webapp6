package es.webapp6.Padelante.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.webapp6.Padelante.repositories.MatchRepository;
import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Tournament;
import es.webapp6.Padelante.model.User;

@Service
public class MatchService {
    
    @Autowired
	private MatchRepository matches;

    public List<Match> getMatches() {
		return matches.findAll();
	}

    public List<Match> getTournamentMatches(Tournament t){
        return matches.getMatches(t);
    }

    public List<Match> getRoundMatches(Tournament t, int r){
        return matches.getRoundMatches(t, r);
    }

	
	public List<Match> getUserMatches(User u){
        return matches.getUserMatches(u);
    }


    public Optional<Match> findById(long id) {
		return matches.findById(id);
	}
	
	public boolean exist(long id) {
		return matches.existsById(id);
	}

	public void save(Match match) {
		matches.save(match);
	}

	public void delete(long id) {
		matches.deleteById(id);
	}


	public boolean checkResult(String r1, String r2, String r3, String r4, String r5, String r6, Match match ){
		int games1 = Integer.parseInt (r1);
		int games2 = Integer.parseInt (r2);
		int games3 = Integer.parseInt (r3);
		int games4 = Integer.parseInt (r4);
		int games5 = Integer.parseInt (r5);
		int games6 = Integer.parseInt (r6);
		ArrayList<Integer> result =new ArrayList<>();
		result.add(games1);
		result.add(games2);
		result.add(games3);
		result.add(games4);
		result.add(games5);
		result.add(games6);

		boolean cheked = true;

		if((games1==6 && (games2>4 && games2!=7)) || ((games1>4 && games1!=7) && games2==6) || (games1==7 && games2==7) || (games1==7 && games2<5) 
		|| (games1<5 && games2==7) || (games1<6 && games2<6)) {
				cheked = false;
				return cheked;
		}
		if((games3==6 && (games4>4 && games4!=7)) || ((games3>4 && games3!=7) && games4==6) || (games3==7 && games4==7) || (games3==7 && games4<5) 
		|| (games3<5 && games4==7) || (games3<6 && games4<6)){
			cheked = false;
				return cheked;
		}

		if(games1>games2 && games3>games4 && (games5!=0 || games6!=0)){
			cheked = false;
				return cheked;
		}

		if(games1<games2 && games3<games4 && (games5!=0 || games6!=0)){
			cheked = false;
				return cheked;
		}

		if(games1<games2 && games3>games4 && ((games5==6 && (games6>4 && games6!=7)) || ((games5>4 && games5!=7) && games6==6) || (games5==7 && games6==7)
		 || (games5==7 && games6<5) || (games5<5 && games6==7) || (games5<6 && games6<6))){
			cheked = false;
				return cheked;
		}

		if(games1>games2 && games3<games4 && ((games5==6 && (games6>4 && games6!=7)) || ((games5>4 && games5!=7) && games6==6) || (games5==7 && games6==7)
		 || (games5==7 && games6<5) || (games5<5 && games6==7) || (games5<6 && games6<6))){
			cheked = false;
				return cheked;
		}

		if(cheked){
			match.setResult(result);
			matches.save(match);
		}

		return cheked;

	}
    //Asigne Teams al partido, eliminando de la BBDD a los teams que fueran TBD
    
    //Dar victoria al ganador

    //Dar free win si hay TBD
}
