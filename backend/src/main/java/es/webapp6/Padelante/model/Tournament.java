package es.webapp6.Padelante.model;

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

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String owner;
    private String tournamentName; 
    private int numParticipants;
    private String about;
    private String ruleset;
    private String location;
    private Date inscriptionDate;
    private Date startDate;
    private String format;
   

    @Lob
	private Blob imageFile;

	private boolean image;

    


    public Tournament() {
        super();
    }

    public Tournament(String tournamentName, int numParticipants,
    String about,String ruleset,String location,Date inscriptionDate,
        Date startDate,String format, String owner
        ) {
                super();
                this.tournamentName=tournamentName;
                this.numParticipants = numParticipants;
                this.about=about;
                this.ruleset = ruleset;
                this.location= location;
                this.inscriptionDate=inscriptionDate;
                this.startDate=startDate;
                this.format=format;
                this.owner = owner;
              
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
        DateFormat formatter = new SimpleDateFormat("hh:mm  dd/MM/yyyy");
        return formatter.format(this.inscriptionDate);
    }

    public void setInscriptionDate(String inscriptionDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date date = (Date)formatter.parse(inscriptionDate); 
        this.inscriptionDate = date;
    }

    public String getStartDate() {
        DateFormat formatter = new SimpleDateFormat("hh:mm  dd/MM/yyyy");
        return formatter.format(this.startDate);
    }

    public void setStartDate(String startDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date date = (Date)formatter.parse(startDate); 
        this.startDate = date;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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

}
