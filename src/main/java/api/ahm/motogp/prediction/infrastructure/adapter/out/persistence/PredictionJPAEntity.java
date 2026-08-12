package api.ahm.motogp.prediction.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.ChampionshipEventJPAEntity;
import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.ChampionshipRiderJPAEntity;
import api.ahm.motogp.identity.infrastructure.adapter.out.persistence.UserJPAEntity;
import jakarta.persistence.*;

@Entity
@Table(name="prediction",
uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_event",
                columnNames = {
                        "user_id",
                        "event_id"
                })
})
public class PredictionJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserJPAEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private ChampionshipEventJPAEntity event;

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

    public UserJPAEntity getUser() {
        return user;
    }

    public void setUser(UserJPAEntity user) {
        this.user = user;
    }

    public ChampionshipEventJPAEntity getEvent() {
        return event;
    }

    public void setEvent(ChampionshipEventJPAEntity event) {
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
