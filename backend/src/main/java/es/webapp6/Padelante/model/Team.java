package es.webapp6.Padelante.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;



@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private boolean tbd;

	@ManyToMany
	@JoinTable(name = "user_table_teams")
	private List<User> players;


    private String data; //just to add and atributte and check the below methods
    
    public Team() {
		super();
	}

	public Team(boolean tbd) {
		super();
		this.tbd = tbd;
		this.players = new ArrayList<>();
	}

	public boolean isTbd() {
		return tbd;
	}

	public void setTbd(boolean tbd) {
		this.tbd = tbd;
	}

	public void addPlayer(User u){
		this.players.add(u);
	}

    public List<User> getPlayers() {
		return players;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", data=" + data + "]";
	}
    
}
