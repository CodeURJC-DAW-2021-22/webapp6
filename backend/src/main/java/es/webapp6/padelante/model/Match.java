package es.webapp6.padelante.model;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(User.Mostrar.class)
    private long id;
    @JsonView(User.Mostrar.class)
    private int round;
    @JsonView(User.Mostrar.class)
    private ArrayList<Integer> result;
    @JsonView(User.Mostrar.class)
    private int setsTeamOne;
    @JsonView(User.Mostrar.class)
    private int setsTeamTwo;
    @JsonView(User.Mostrar.class)
    private boolean hasWinner;
    @JsonView(User.Mostrar.class)
    private boolean winnerTeamOne;
    @JsonView(User.Mostrar.class)
    private boolean winnerTeamTwo;

    @ManyToOne 
    @JsonView(User.Mostrar.class)
    private Team teamOne;

    @ManyToOne
    @JsonView(User.Mostrar.class)
    private Team teamTwo;

    @ManyToOne
    @JsonView(User.Mostrar.class)
    Tournament tournament;

    public Match() {
		super();
	}

    public Match(int round, Team teamOne, Team teamTwo, Tournament tournament) {
        this.round = round;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.tournament = tournament;
        this.result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            this.result.add(0);
        }
        this.setsTeamOne = 0;
        this.setsTeamTwo = 0;
        this.hasWinner = false;
        this.winnerTeamOne = false;
        this.winnerTeamTwo = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public ArrayList<Integer> getResult() {
        return result;
    }

    public void setResult(ArrayList<Integer> result) {
        this.result = result;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

    public boolean isHasWinner() {
        return hasWinner;
    }

    public void setHasWinner(boolean hasWinner) {
        this.hasWinner = hasWinner;
    }

    public int getSetsTeamOne() {
        return setsTeamOne;
    }

    public void setSetsTeamOne(int setsTeamOne) {
        this.setsTeamOne = setsTeamOne;
    }

    public int getSetsTeamTwo() {
        return setsTeamTwo;
    }

    public void setSetsTeamTwo(int setsTeamTwo) {
        this.setsTeamTwo = setsTeamTwo;
    }

    public boolean isWinnerTeamOne() {
        return winnerTeamOne;
    }

    public void setWinnerTeamOne(boolean winnerTeamOne) {
        this.winnerTeamOne = winnerTeamOne;
    }

    public boolean isWinnerTeamTwo() {
        return winnerTeamTwo;
    }

    public void setWinnerTeamTwo(boolean winnerTeamTwo) {
        this.winnerTeamTwo = winnerTeamTwo;
    }
}
