package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "event_result",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_eventresult_ei_cri",
                        columnNames = {
                                "event_id",
                                "championship_rider_id"
                        }
                )
        }
)
public class EventResultJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "championship_event_id")
    private EventJPAEntity championshipEvent;

    @ManyToOne
    @JoinColumn(name = "championship_rider_id")
    private ChampionshipRiderJPAEntity championshipRider;

    @Column(name = "position")
    private int position;

    @Column(name = "points")
    private float points;

    public EventResultJPAEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventJPAEntity getChampionshipEvent() {
        return championshipEvent;
    }

    public void setChampionshipEvent(EventJPAEntity championshipEvent) {
        this.championshipEvent = championshipEvent;
    }

    public ChampionshipRiderJPAEntity getChampionshipRider() {
        return championshipRider;
    }

    public void setChampionshipRider(ChampionshipRiderJPAEntity championshipRider) {
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
