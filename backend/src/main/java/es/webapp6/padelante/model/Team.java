package es.webapp6.padelante.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;



@Entity
public class Team {
	
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(User.Mostrar.class)
    private long id;

	@JsonView(User.Mostrar.class)
	private boolean tbd;

	@ManyToOne
	@JsonView(User.Mostrar.class)	private User userA;

	@ManyToOne
	@JsonView(User.Mostrar.class)	private User userB;
    
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
}
