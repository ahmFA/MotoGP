package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.rider.infrastructure.adapter.out.persistence.RiderJPAEntity;
import jakarta.persistence.*;

@Entity
@Table(name="championship_rider",
uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_championshiprider_rider_championship",
                columnNames = {
                        "rider_id",
                        "championship_id"
                }
        )
})
public class ChampionshipRiderJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="rider_id")
    private RiderJPAEntity rider;

    @ManyToOne
    @JoinColumn(name="championship_id")
    private ChampionshipJPAEntity championship;

    @ManyToOne
    @JoinColumn(name="team_id")
    private ChampionshipTeamJPAEntity team;

    @Column(name="number")
    private int number;

    public ChampionshipRiderJPAEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RiderJPAEntity getRider() {
        return rider;
    }

    public void setRider(RiderJPAEntity rider) {
        this.rider = rider;
    }

    public ChampionshipJPAEntity getChampionship() {
        return championship;
    }

    public void setChampionship(ChampionshipJPAEntity championship) {
        this.championship = championship;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ChampionshipTeamJPAEntity getTeam() {
        return team;
    }

    public void setTeam(ChampionshipTeamJPAEntity team) {
        this.team = team;
    }
}
