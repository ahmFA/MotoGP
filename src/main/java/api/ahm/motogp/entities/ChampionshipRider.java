package api.ahm.motogp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="championship_rider")
public class ChampionshipRider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="rider_id")
    private Rider rider;

    @ManyToOne
    @JoinColumn(name="championship_id")
    private Championship championship;

    @ManyToOne
    @JoinColumn(name="team_id")
    private ChampionshipTeam team;

    @Column(name="number")
    private int number;

    public ChampionshipRider() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ChampionshipTeam getTeam() {
        return team;
    }

    public void setTeam(ChampionshipTeam team) {
        this.team = team;
    }
}
