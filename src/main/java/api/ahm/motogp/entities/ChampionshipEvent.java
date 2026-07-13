package api.ahm.motogp.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="championship_event",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_championshipevent_cgp_eventtype",
                columnNames = {
                        "championship_grandprix_id",
                        "event_type"
                }
        )
    }
)
public class ChampionshipEvent {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="championship_grandprix_id")
    private ChampionshipGrandPrix championshipGrandPrix;

    @Enumerated(EnumType.STRING)
    @Column(name="event_type")
    private EventType eventType;

    @Column(name="start_date")
    private Date startDate;

    public enum EventType {
        QUALIFYING,
        SPRINT,
        MAIN_RACE
    }

    public ChampionshipEvent() {
    }

    public ChampionshipEvent(int id, ChampionshipGrandPrix championshipGrandPrix, EventType eventType, Date startDate) {
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

    public ChampionshipGrandPrix getChampionshipGrandPrix() {
        return championshipGrandPrix;
    }

    public void setGrandPrix(ChampionshipGrandPrix championshipGrandPrix) {
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
}
