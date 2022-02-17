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
    
    // public void Team(){
    // }

    // Many to One Relation with Match. Need to think about specific attributes.
}
