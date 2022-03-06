package es.webapp6.Padelante.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;
import javax.persistence.Lob;


@Entity(name = "UserTable")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private String realName;
	private String location;
	private String country;
	private String phone;
	private int numWins;
	private int numLoses;
	private double numMatchesPlayed; // Double cause it will have to be the argument of Math.log(Double a)
	private double karma; // Double
	private ArrayList<Integer> historicalKarma = new ArrayList<>(); // Integer because karma will be truncated before added to the list
	private boolean status;

	//Profile Img To Do	
	@Lob
	private Blob imageFile;

	private boolean image;


	private String encodedPassword;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public User() {
	}

	public User(String name, String encodedPassword,String email, String realName, String... roles) {
		this.name = name;
		this.encodedPassword = encodedPassword;
		this.email = email;
		this.realName = realName;
		this.location = "";
		this.country = "";
		this.phone = "";
		this.numWins=0;
		this.numLoses=0;
		this.numMatchesPlayed=0;
		this.karma=500;
		this.status=true;
		this.roles = List.of(roles);
	}

	public String getName() {
		if (this.status){
			return name;
		} else{
			return "none";
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStatus(Boolean bol){
		this.status=bol;
	}

	public boolean getStatus(){
		return status;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getNumWins() {
		return numWins;
	}

	public void setNumWins(int numWins) {
		this.numWins = numWins;
	}

	public int getNumLoses() {
		return numLoses;
	}

	public void setNumLoses(int numLoses) {
		this.numLoses = numLoses;
	}

	public double getNumMatchesPlayed() {
		return numMatchesPlayed;
	}

	// It should only increment by one each time
	public void setNumMatchesPlayed(int numPlayed) {
		this.numMatchesPlayed = numPlayed;
	}

	public int getKarma() {
		return (int) Math.round(this.karma);
	}

	public void setKarma(int karma) {
		this.karma = karma;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
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

	public ArrayList<Integer> getHistoricalKarma() {
		return historicalKarma;
	}

	public void setHistoricalKarma(ArrayList<Integer> historicalKarma) {
		this.historicalKarma = historicalKarma;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", realName="+realName+ "]";
	}

}