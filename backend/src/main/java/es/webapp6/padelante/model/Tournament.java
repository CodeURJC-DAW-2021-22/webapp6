package es.webapp6.padelante.model;

import java.sql.Blob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(User.Mostrar.class)
    private long id;

    @JsonView(User.Mostrar.class)
    private String owner;

    @JsonView(User.Mostrar.class)
    private String tournamentName; 

    @JsonView(User.Mostrar.class)
    private int numParticipants;

    @JsonView(User.Mostrar.class)
    private int numSignedUp;
    
    @JsonView(User.Mostrar.class)

    private int rounds;
    @JsonView(User.Mostrar.class)

    private String about;
    @JsonView(User.Mostrar.class)

    private String ruleset;
    @JsonView(User.Mostrar.class)

    private String location;
    @JsonView(User.Mostrar.class)

    private String inscriptionDate;
    @JsonView(User.Mostrar.class)

    private String startDate;
    @JsonView(User.Mostrar.class)

    private boolean started;
   
    @JsonIgnore 
    @Lob
	private Blob imageFile;

    @JsonView(User.Mostrar.class)
	private boolean image;


    public Tournament() {
        super();
    }

    public Tournament(String tournamentName, int numParticipants,
    String about,String ruleset,String location,String inscriptionDate,
    String startDate, String owner
        ) {
                super();
                this.tournamentName=tournamentName;
                this.numParticipants = numParticipants;
                this.numSignedUp = 0;
                this.rounds = 0;
                this.about=about;
                this.ruleset = ruleset;
                this.location= location;
                this.inscriptionDate=inscriptionDate;
                this.startDate=startDate;
                this.owner = owner;
                this.started = false;
              
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

    public int getNumSignedUp() {
        return numSignedUp;
    }

    public void setNumSignedUp(int numSignedUp) {
        this.numSignedUp = numSignedUp;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInscriptionDate() {
        return this.inscriptionDate;
    }

    public void setInscriptionDate(String inscriptionDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = (Date)formatter.parse(inscriptionDate); 
        DateFormat formatter2 = new SimpleDateFormat("HH:mm  dd/MM/yyyy");
        this.inscriptionDate = formatter2.format(date);
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = (Date)formatter.parse(startDate);
        DateFormat formatter2 = new SimpleDateFormat("HH:mm  dd/MM/yyyy");
        this.startDate = formatter2.format(date);
    }

    public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob image) {
		this.imageFile = image;
	}

	public boolean getImage(){
		return this.image;
	}

	public void setImage(boolean image){
		this.image = image;
	}

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
