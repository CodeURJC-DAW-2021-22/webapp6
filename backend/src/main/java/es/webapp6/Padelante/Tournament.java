package es.webapp6.Padelante;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int numParticipants;
    private String about;
    private String ruleset;
    private String location;
    private Date inscriptionDate;
    private Date startDate;
    private String format;
    private String tournamentImage;

    protected Tournament() {
    }

    public Tournament(String name, int numParticipants,String about,String ruleset,String location,Date inscriptionDate,
        Date startDate,String format,String tournamentImage) {
                this.name=name;
                this.numParticipants = numParticipants;
                this.about=about;
                this.ruleset = ruleset;
                this.location= location;
                this.inscriptionDate=inscriptionDate;
                this.startDate=startDate;
                this.format=format;
                this.tournamentImage=tournamentImage;

    }

    

}
