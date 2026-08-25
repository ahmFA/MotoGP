package api.ahm.motogp.prediction.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.EventJPAEntity;
import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.ChampionshipRiderJPAEntity;
import api.ahm.motogp.league.infrastructure.adapter.out.persistence.UserLeagueJPAEntity;
import jakarta.persistence.*;

@Entity
@Table(name="prediction",
uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_event",
                columnNames = {
                        "user_league_id",
                        "event_id"
                })
})
public class PredictionJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_league_id")
    private UserLeagueJPAEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private EventJPAEntity event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="first")
    private ChampionshipRiderJPAEntity firstRider;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="second")
    private ChampionshipRiderJPAEntity secondRider;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="third")
    private ChampionshipRiderJPAEntity thirdRider;

    public PredictionJPAEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserLeagueJPAEntity getUser() {
        return user;
    }

    public void setUser(UserLeagueJPAEntity user) {
        this.user = user;
    }

    public EventJPAEntity getEvent() {
        return event;
    }

    public void setEvent(EventJPAEntity event) {
        this.event = event;
    }

    public ChampionshipRiderJPAEntity getFirstRider() {
        return firstRider;
    }

    public void setFirstRider(ChampionshipRiderJPAEntity firstRider) {
        this.firstRider = firstRider;
    }

    public ChampionshipRiderJPAEntity getSecondRider() {
        return secondRider;
    }

    public void setSecondRider(ChampionshipRiderJPAEntity secondRider) {
        this.secondRider = secondRider;
    }

    public ChampionshipRiderJPAEntity getThirdRider() {
        return thirdRider;
    }

    public void setThirdRider(ChampionshipRiderJPAEntity thirdRider) {
        this.thirdRider = thirdRider;
    }
}
