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
		int sets1 = Integer.parseInt (r1);
		int sets2 = Integer.parseInt (r2);
		int sets3 = Integer.parseInt (r3);
		int sets4 = Integer.parseInt (r4);
		int sets5 = Integer.parseInt (r5);
		int sets6 = Integer.parseInt (r6);
		ArrayList<Integer> result =new ArrayList<>();;
		result.add(sets1);
		result.add(sets2);
		result.add(sets3);
		result.add(sets4);
		result.add(sets5);
		result.add(sets6);

		boolean cheked = true;

		if((sets1<6 && sets2<6) || (sets1==6 && sets2==6) || (sets1==7 && sets2==7)){
				cheked = false;
				return cheked;
		}
		if((sets3<6 && sets4<6) || (sets3==6 && sets4==6) || (sets3==7 && sets4==7)){
			cheked = false;
				return cheked;
		}

		if(sets1>sets2 && sets3>sets4 && (sets5!=0 || sets6!=0)){
			cheked = false;
				return cheked;
		}

		if(sets1<sets2 && sets3<sets4 && (sets5!=0 || sets6!=0)){
			cheked = false;
				return cheked;
		}

		if(sets1<sets2 && sets3>sets4 && (sets5<6 && sets6<6)){
			cheked = false;
				return cheked;
		}

		if(sets1<sets2 && sets3>sets4 && ((sets5==6 && sets6==6)||(sets5==7 && sets6==7))){
			cheked = false;
				return cheked;
		}

		if(sets1>sets2 && sets3<sets4 && (sets5<6 && sets6<6)){
			cheked = false;
				return cheked;
		}

		if(sets1>sets2 && sets3<sets4 && ((sets5==6 && sets6==6)||(sets5==7 && sets6==7))){
			cheked = false;
				return cheked;
		}

		if(sets1-sets2==0 || (sets1==6 && sets2==5) || (sets1==5 && sets2==6)){
			cheked = false;
				return cheked;
		}
		if(sets3-sets4==0 || (sets3==6 && sets4==5) || (sets3==5 && sets4==6)){
			cheked = false;
				return cheked;
		}
		if((sets5==5 && sets6==6) || (sets5==6 && sets6==5)){
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
