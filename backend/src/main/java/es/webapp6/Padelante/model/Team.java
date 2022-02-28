package es.webapp6.Padelante.model;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private boolean tbd;

	@ManyToMany(mappedBy="teams")
	private List<User> players;


    private String data; //just to add and atributte and check the below methods
    
    public Team() {
		super();
	}

	public Team(boolean tbd) {
		super();
		this.tbd = tbd;
	}

	public boolean isTbd() {
		return tbd;
	}

	public void setTbd(boolean tbd) {
		this.tbd = tbd;
	}

    public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", data=" + data + "]";
	}
    
}
