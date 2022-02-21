package es.webapp6.Padelante.model;

import java.sql.Date;

//import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tournamentName; 
    private int numParticipants;
    private String about;
    private String ruleset;
    private String location;
    private Date inscriptionDate;
    private Date startDate;
    private String format;
    private String tournamentImage;

    public Tournament() {
        super();
    }

    public Tournament(String tournamentName, int numParticipants
    // String about,String ruleset,String location,Date inscriptionDate,
    //     Date startDate,String format,String tournamentImage
        ) {
                super();
                this.tournamentName=tournamentName;
                this.numParticipants = numParticipants;
                // this.about=about;
                // this.ruleset = ruleset;
                // this.location= location;
                // this.inscriptionDate=inscriptionDate;
                // this.startDate=startDate;
                // this.format=format;
                // this.tournamentImage=tournamentImage;
    }

    @Override
	public String toString() {
		return "Tournament [id=" + id + ", tournamentName=" + tournamentName + ", numParticipants="+numParticipants+ "]";
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public int getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRuleset() {
        return ruleset;
    }

    public void setRuleset(String ruleset) {
        this.ruleset = ruleset;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTournamentImage() {
        return tournamentImage;
    }

    public void setTournamentImage(String tournamentImage) {
        this.tournamentImage = tournamentImage;
    }

    

}