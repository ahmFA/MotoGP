package api.ahm.motogp.entities;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="championship_team")
public class ChampionshipTeam {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="constructor_id")
    private Constructor constructor;

    @ManyToOne
    @JoinColumn(name="championship_id")
    private Championship championship;

    public ChampionshipTeam() {
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

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
}
