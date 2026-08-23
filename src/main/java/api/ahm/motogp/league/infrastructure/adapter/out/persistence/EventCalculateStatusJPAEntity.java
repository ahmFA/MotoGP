package api.ahm.motogp.league.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.EventJPAEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="event_calculate_status",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_ecs_event",
                        columnNames = {"event_id"})
        })
public class EventCalculateStatusJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private EventJPAEntity event;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private CalculateStatus status;

    @Column(name="last_updated")
    private LocalDateTime lastUpdated;

    public enum CalculateStatus {
        PENDING,
        FAILED,
        DONE
    }

    public EventCalculateStatusJPAEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventJPAEntity getEvent() {
        return event;
    }

    public void setEvent(EventJPAEntity event) {
        this.event = event;
    }

    public CalculateStatus getStatus() {
        return status;
    }

    public void setStatus(CalculateStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
