package es.webapp6.Padelante.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	// @ManyToMany(mappedBy="Team")
	// private List<User> players;


    private String data; //just to add and atributte and check the below methods
    
    public Team() {
		super();
	}

	public Team(String data) {
		super();
		this.data = data;
	}

    public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", data=" + data + "]";
	}
    
}
