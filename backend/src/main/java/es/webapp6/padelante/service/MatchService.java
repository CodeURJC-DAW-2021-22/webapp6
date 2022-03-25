package es.webapp6.padelante.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.webapp6.padelante.model.Match;
import es.webapp6.padelante.model.Team;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.repositories.MatchRepository;
import es.webapp6.padelante.repositories.UserRepository;

@Service
public class MatchService {
    
	@Autowired
	private TournamentService tournamentService;

    @Autowired
	private MatchRepository matches;

	@Autowired
	private UserRepository users;

	@Autowired
	private UserService userService;

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

	public List<Match> findAll(){
		return matches.findAll();
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
			ArrayList<Integer> sets = calculateSets(match, games1, games2, games3, games4, games5, games6);
			match.setSetsTeamOne((int) sets.get(0));
			match.setSetsTeamTwo((int) sets.get(1));
			match.setHasWinner(true);

			setWinnerNormalMatch(match, sets);
		}
		return cheked;
	}

	private ArrayList<Integer> calculateSets (Match match, int games1, int games2, int games3, int games4, int games5, int games6){
		Integer teamOne = 0;
		Integer teamTwo = 0;
		if (games1>games2){
			teamOne++;
		} else {
			teamTwo++;
		}
		if (games3>games4){
			teamOne++;
		} else {
			teamTwo++;
		}
		if (games5 != 0 && games6 != 0){
			if (games5>games6){
				teamOne++;
			} else {
				teamTwo++;
			}
		}
		ArrayList<Integer> sets = new ArrayList<>();
		sets.add(teamOne);
		sets.add(teamTwo);
		return sets;
	}

	public void setWinnerNormalMatch (Match match, ArrayList<Integer> sets){
		User teamOneUserA = match.getTeamOne().getUserA();
		User teamOneUserB = match.getTeamOne().getUserB();
		User teamTwoUserA = match.getTeamTwo().getUserA();
		User teamTwoUserB = match.getTeamTwo().getUserB();

		if (sets.get(0)>sets.get(1)){
			match.setWinnerTeamOne(true);

			teamOneUserA.setNumWins(teamOneUserA.getNumWins()+1);
			teamOneUserA.setNumMatchesPlayed(teamOneUserA.getNumMatchesPlayed()+1);
			teamOneUserB.setNumWins(teamOneUserB.getNumWins()+1);
			teamOneUserB.setNumMatchesPlayed(teamOneUserB.getNumMatchesPlayed()+1);
			teamTwoUserA.setNumLoses(teamTwoUserA.getNumLoses()+1);
			teamTwoUserA.setNumMatchesPlayed(teamTwoUserA.getNumMatchesPlayed()+1);
			teamTwoUserB.setNumLoses(teamTwoUserB.getNumLoses()+1);
			teamTwoUserB.setNumMatchesPlayed(teamTwoUserB.getNumMatchesPlayed()+1);

			

			if (match.getRound()!=1){
				tournamentService.moveNextRound(match.getTournament(), match, match.getTeamOne());
			}

		} else {
			match.setWinnerTeamTwo(true);

			teamOneUserA.setNumLoses(teamOneUserA.getNumLoses()+1);
			teamOneUserA.setNumMatchesPlayed(teamOneUserA.getNumMatchesPlayed()+1);
			teamOneUserB.setNumLoses(teamOneUserB.getNumLoses()+1);
			teamOneUserB.setNumMatchesPlayed(teamOneUserB.getNumMatchesPlayed()+1);
			teamTwoUserA.setNumWins(teamTwoUserA.getNumWins()+1);
			teamTwoUserA.setNumMatchesPlayed(teamTwoUserA.getNumMatchesPlayed()+1);
			teamTwoUserB.setNumWins(teamTwoUserB.getNumWins()+1);
			teamTwoUserB.setNumMatchesPlayed(teamTwoUserB.getNumMatchesPlayed()+1);			

			if (match.getRound()!=1){
				tournamentService.moveNextRound(match.getTournament(), match, match.getTeamTwo());
			}
		}

		assignKarma(getWinner(match), match);
		matches.save(match);
		users.save(teamOneUserA);
		users.save(teamOneUserB);
		users.save(teamTwoUserA);
		users.save(teamTwoUserB);
	}


	public Team getWinner(Match match){
		if (match.isHasWinner()){
			if (match.isWinnerTeamOne()){
				return match.getTeamOne();
			} else {
				return match.getTeamTwo();
			}
		} else {
			return null;
		}
	}

	private double generateAvgPairKarma(Team team) {
        double karmaPlayer1 = team.getUserA().getKarma();
        double karmaPlayer2 = team.getUserB().getKarma();

        return (karmaPlayer1 + karmaPlayer2) / 2;
    }



	private double calculateMatchKarmaFactor(Match match) {
        if (generateAvgPairKarma(match.getTeamOne()) >= generateAvgPairKarma(match.getTeamTwo())) {
            return generateAvgPairKarma(match.getTeamOne()) / generateAvgPairKarma(match.getTeamTwo());
        } else {
            return generateAvgPairKarma(match.getTeamTwo()) / generateAvgPairKarma(match.getTeamOne());
        }
    }

	private Team calculateFavorite(Match match) {
        if (generateAvgPairKarma(match.getTeamOne()) >= generateAvgPairKarma(match.getTeamTwo())) {
            return match.getTeamOne();
        } else {
            return match.getTeamTwo();
        }
    }

	public void assignKarma(Team winner, Match match) {

		Team favorite = calculateFavorite(match);
		double matchKarmaFactor = calculateMatchKarmaFactor(match);
		User userATeam1 = match.getTeamOne().getUserA();
		User userBTeam1 = match.getTeamOne().getUserB();
		User userATeam2 = match.getTeamTwo().getUserA();
		User userBTeam2 = match.getTeamTwo().getUserB();

        if (winner.getId() == match.getTeamOne().getId()) { //TeamOne is winner
            if (favorite.getId() == match.getTeamOne().getId()) { // TeamOne is favourite
                userService.calculateKarma(1 / matchKarmaFactor, true, userATeam1);
                userService.calculateKarma(1 / matchKarmaFactor, true, userBTeam1);
                userService.calculateKarma((1 / matchKarmaFactor), false, userATeam2);
                userService.calculateKarma((1 / matchKarmaFactor), false, userBTeam2);
            } else { // Team 2 is favourite
                userService.calculateKarma(matchKarmaFactor, true, userATeam1);
                userService.calculateKarma(matchKarmaFactor, true, userBTeam1);
                userService.calculateKarma(matchKarmaFactor, false, userATeam2);
                userService.calculateKarma(matchKarmaFactor, false, userBTeam2);
            }
        } else { // TeamTwo is winner
            if (match.getTeamTwo().equals(favorite)) { // TeamTwo is favourite
                userService.calculateKarma(1 / matchKarmaFactor, true, userATeam2);
                userService.calculateKarma(1 / matchKarmaFactor, true, userBTeam2);
                userService.calculateKarma((1 / matchKarmaFactor), false, userATeam1);
                userService.calculateKarma((1 / matchKarmaFactor), false, userBTeam1);
            } else { // Team 1 is favourtie
                userService.calculateKarma(matchKarmaFactor, true, userATeam2);
                userService.calculateKarma(matchKarmaFactor, true, userBTeam2);
                userService.calculateKarma(matchKarmaFactor, false, userATeam1);
                userService.calculateKarma(matchKarmaFactor, false, userBTeam1);
            }
        }
    }
}
