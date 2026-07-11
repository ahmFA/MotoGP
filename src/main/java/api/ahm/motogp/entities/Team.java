package api.ahm.motogp.entities;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="team")
public class Team {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @Column(name="name")
    private String name;

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
