package es.webapp6.Padelante.model;



import java.util.ArrayList;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private ArrayList<Integer> result; //To be defined correctly   

    private Date dateOfMatch;
    private Team winnerTeam; 

    @ManyToOne 
    private Team teamOne;
    @ManyToOne
    private Team teamTwo;
    @ManyToOne
    Tournament tournament; // Needs to be in Constructor?

    public void Match(Date dateOfMatch){
        this.dateOfMatch = dateOfMatch;
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

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    public ArrayList<Integer> getResult() {
        return result;
    }

    public void setResult(ArrayList<Integer> result) {
        this.result = result;
    }

    public Date getDateOfMatch() {
        return dateOfMatch;
    }

    public void setDateOfMatch(Date dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Tournament getTournament() {
        return tournament;
    }
}
