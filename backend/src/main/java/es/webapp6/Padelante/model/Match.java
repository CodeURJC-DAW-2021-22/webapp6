package es.webapp6.Padelante.model;



import java.util.ArrayList;

//import java.util.ArrayList;
//import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int round;
    private ArrayList<Integer> result;

    @ManyToOne 
    private Team teamOne;

    @ManyToOne
    private Team teamTwo;

    @ManyToOne
    Tournament tournament; // Needs to be in Constructor?
    

    //this var commented, if I descommented the project doesnt work
    //private ArrayList<Integer> result; //To be defined correctly   
    //private Date dateOfMatch;
    //private Team winnerTeam; 
    private String data; //just to try easily, in the future it can be eliminated

    public Match() {
		super();
	}

    public Match(int round, Team teamOne, Team teamTwo, Tournament tournament) {
        this.round = round;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.tournament = tournament;
        this.result = new ArrayList<>();
        for (int i = 0; i < 6; i = i + 1) {
            this.result.add(0);
        }
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

    //just to make ir easier, i commment dateofmatch 
    // public Date getDateOfMatch() {
    //     return dateOfMatch;
    // }

    // public void setDateOfMatch(Date dateOfMatch) {
    //     this.dateOfMatch = dateOfMatch;
    // }

    //at the momment of create o match we dont have a winner, is this ok?
    // public Team getWinnerTeam() {
    //     return winnerTeam;
    // }

    // public void setWinnerTeam(Team winnerTeam) {
    //     this.winnerTeam = winnerTeam;
    // }


    @Override
	public String toString() {
		return "Match [id=" + id + ", data=" + data + "]";
	}
}
