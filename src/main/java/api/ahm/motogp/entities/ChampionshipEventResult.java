package api.ahm.motogp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="championship_event_result",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_championshipeventresult_cei_cri",
                        columnNames = {
                                "championship_event_id",
                                "championship_rider_id"
                        }
                )
        }
)
public class ChampionshipEventResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="championship_event_id")
    private ChampionshipEvent championshipEvent;

    @ManyToOne
    @JoinColumn(name="championship_rider_id")
    private ChampionshipRider championshipRider;

    @Column(name="position")
    private int position;

    @Column(name="points")
    private float points;

    /*
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private ResultStatus status;

    public enum ResultStatus {
        FINISHED,
        NO_START,
        DNS,
        DSQ
    }
    */

    public ChampionshipEventResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChampionshipEvent getChampionshipEvent() {
        return championshipEvent;
    }

    public void setChampionshipEvent(ChampionshipEvent championshipEvent) {
        this.championshipEvent = championshipEvent;
    }

    public ChampionshipRider getChampionshipRider() {
        return championshipRider;
    }

    public void setChampionshipRider(ChampionshipRider championshipRider) {
        this.championshipRider = championshipRider;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }
}
