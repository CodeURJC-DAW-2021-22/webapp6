package es.webapp6.Padelante.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


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
	private int numPlayed;
	private int karma;

	
	// private List<Tournament> myTournaments;

	@ManyToMany
	private List<Team> teams;


	//Profile Img To Do

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
		this.numPlayed=0;
		this.karma=500;
		this.roles = List.of(roles);
	}

	public String getName() {
		return name;
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

	// public List<Tournament> getMyTournaments() {
	// 	return myTournaments;
	// }

	// public void setMyTournaments(List<Tournament> myTournaments) {
	// 	this.myTournaments = myTournaments;
	// }

	// public List<Team> getTeams() {
	// 	return teams;
	// }

	// public void setMyPartners(List<Team> teams) {
	// 	this.teams = teams;
	// }

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

	public int getNumPlayed() {
		return numPlayed;
	}

	public void setNumPlayed(int numPlayed) {
		this.numPlayed = numPlayed;
	}

	public int getKarma() {
		return karma;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", realName="+realName+ "]";
	}

}