package api.ahm.motogp.prediction.domain.model;

import api.ahm.motogp.championship.domain.model.valueobjects.EventId;
import api.ahm.motogp.championship.domain.model.valueobjects.RiderId;
import api.ahm.motogp.league.domain.model.valueobjects.UserLeagueId;

public class Prediction {
    private Long id;
    private UserLeagueId userLeagueId;
    private EventId eventId;
    private RiderId firstRider;
    private RiderId secondRider;
    private RiderId thirdRider;

    public Prediction(Long id, UserLeagueId userLeagueId, EventId eventId, RiderId firstRider, RiderId secondRider, RiderId thirdRider) {
        this.id = id;
        this.userLeagueId = userLeagueId;
        this.eventId = eventId;
        this.firstRider = firstRider;
        this.secondRider = secondRider;
        this.thirdRider = thirdRider;
    }

    public boolean checkRiderPositions(){
        return !firstRider.equals(secondRider) && !thirdRider.equals(secondRider) && !thirdRider.equals(firstRider);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserLeagueId getUserLeagueId() {
        return userLeagueId;
    }

    public void setUserLeagueId(UserLeagueId userId) {
        this.userLeagueId = userId;
    }

    public EventId getEventId() {
        return eventId;
    }

    public void setEventId(EventId eventId) {
        this.eventId = eventId;
    }

    public RiderId getFirstRider() {
        return firstRider;
    }

    public void setFirstRider(RiderId firstRider) {
        this.firstRider = firstRider;
    }

    public RiderId getSecondRider() {
        return secondRider;
    }

    public void setSecondRider(RiderId secondRider) {
        this.secondRider = secondRider;
    }

    public RiderId getThirdRider() {
        return thirdRider;
    }

    public void setThirdRider(RiderId thirdRider) {
        this.thirdRider = thirdRider;
    }
}
