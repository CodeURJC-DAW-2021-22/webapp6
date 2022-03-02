package es.webapp6.Padelante.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private boolean tbd;

	@ManyToOne
	private User userA;

	@ManyToOne
	private User userB;

    private String data; //just to add and atributte and check the below methods
    
    public Team() {
		super();
	}

	public Team(boolean tbd, User a, User b) {
		super();
		this.userA = a;
		this.userB = b;
		this.tbd = tbd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isTbd() {
		return tbd;
	}

	public void setTbd(boolean tbd) {
		this.tbd = tbd;
	}

	public User getUserA() {
		return userA;
	}

	public void setUserA(User userA) {
		this.userA = userA;
	}

	public User getUserB() {
		return userB;
	}

	public void setUserB(User userB) {
		this.userB = userB;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", data=" + data + "]";
	}
    
}
