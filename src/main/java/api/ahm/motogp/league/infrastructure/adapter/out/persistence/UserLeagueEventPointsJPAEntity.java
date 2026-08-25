package api.ahm.motogp.league.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.EventJPAEntity;
import jakarta.persistence.*;

@Entity
@Table(name="userleague_event_points",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_ulep_userleague_event",
                        columnNames = {"user_league_id", "event_id"})
        })
public class UserLeagueEventPointsJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_league_id")
    private UserLeagueJPAEntity userLeague;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private EventJPAEntity event;

    @Column(name="points")
    private int points;

    public UserLeagueEventPointsJPAEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserLeagueJPAEntity getUserLeague() {
        return userLeague;
    }

    public void setUserLeague(UserLeagueJPAEntity userLeague) {
        this.userLeague = userLeague;
    }

    public EventJPAEntity getEvent() {
        return event;
    }

    public void setEvent(EventJPAEntity event) {
        this.event = event;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
