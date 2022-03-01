package es.webapp6.Padelante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.webapp6.Padelante.repositories.MatchRepository;
import es.webapp6.Padelante.model.Match;
import es.webapp6.Padelante.model.Tournament;

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

    //Asigne Teams al partido, eliminando de la BBDD a los teams que fueran TBD
    
    //Dar victoria al ganador

    //Dar free win si hay TBD
}
