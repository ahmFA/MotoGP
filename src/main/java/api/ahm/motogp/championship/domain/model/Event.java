package api.ahm.motogp.championship.domain.model;

import api.ahm.motogp.championship.domain.model.valueobjects.EventStatus;
import api.ahm.motogp.championship.domain.model.valueobjects.EventType;

import java.util.Date;

public class Event {

    private int id;
    private int championshipGrandPrixId;
    private EventType eventType;
    private Date startDate;
    private EventStatus eventStatus;

    public Event(int id, int championshipGrandPrixId, EventType eventType, Date startDate, EventStatus eventStatus) {
        this.id = id;
        this.championshipGrandPrixId = championshipGrandPrixId;
        this.eventType = eventType;
        this.startDate = startDate;
        this.eventStatus = eventStatus;
    }

    public Event createEvent(int id, int championshipGrandPrixId, EventType eventType, Date startDate, EventStatus eventStatus) {
        if(id <= 0){
            throw new IllegalArgumentException("ChampionshipEvent ID must be greater than 0");
        }
        if(championshipGrandPrixId <= 0){
            throw new IllegalArgumentException("Championship Grand Prix ID must be greater than 0");
        }
        if(eventType == null){
            throw new IllegalArgumentException("Championship Event Type is not valid");
        }
        if(startDate == null){
            throw new IllegalArgumentException("Championship Start Date is null");
        }
        return new Event(id, championshipGrandPrixId, eventType, startDate, eventStatus);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChampionshipGrandPrixId() {
        return championshipGrandPrixId;
    }

    public void setChampionshipGrandPrixId(int championshipGrandPrixId) {
        this.championshipGrandPrixId = championshipGrandPrixId;
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

    public void changeStatus(EventStatus status) {
        if(this.getEventStatus() == EventStatus.FINISHED) {
            throw new IllegalArgumentException("Championship Event Status is already finished. Status cannot be changed");
        }
        if(status == null){
            throw new IllegalArgumentException("Championship Event Status is not valid");
        }
        this.setEventStatus(status);
    }

    public void changeStartDate(Date startDate) {
        if(startDate == null){
            throw new IllegalArgumentException("Event Start date is null");
        }
        if(startDate.before(new Date())){
            throw new IllegalArgumentException("Event Start date cannot be before now");
        }
        this.setStartDate(startDate);
    }

    public boolean canBePredicted(){
       return this.getEventStatus() == EventStatus.OPEN;
    }
}
