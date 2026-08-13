package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="event",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_event_cgp_eventtype",
                        columnNames = {
                                "championship_grandprix_id",
                                "event_type"
                        }
                )
        }
)

public class EventJPAEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="championship_grandprix_id")
    private ChampionshipGrandPrixJPAEntity championshipGrandPrix;

    @Enumerated(EnumType.STRING)
    @Column(name="event_type")
    private EventType eventType;

    @Column(name="start_date")
    private Date startDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private EventStatus eventStatus;

    public enum EventType {
        QUALIFYING,
        SPRINT,
        MAIN_RACE
    }

    public enum EventStatus {
        CLOSED,
        OPEN,
        FINISHED
    }

    public EventJPAEntity() {
    }

    public EventJPAEntity(int id, ChampionshipGrandPrixJPAEntity championshipGrandPrix, EventType eventType, Date startDate) {
        this.id = id;
        this.championshipGrandPrix = championshipGrandPrix;
        this.eventType = eventType;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChampionshipGrandPrixJPAEntity getChampionshipGrandPrix() {
        return championshipGrandPrix;
    }

    public void setChampionshipGrandPrix(ChampionshipGrandPrixJPAEntity championshipGrandPrix) {
        this.championshipGrandPrix = championshipGrandPrix;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
