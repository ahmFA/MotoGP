package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.exception.EventNotFoundException;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.EventRepositoryPort;
import api.ahm.motogp.championship.application.port.query.EventView;
import api.ahm.motogp.championship.domain.model.Event;
import api.ahm.motogp.championship.domain.model.valueobjects.EventStatus;
import api.ahm.motogp.championship.domain.model.valueobjects.EventType;
import api.ahm.motogp.grandprix.infrastructure.adapter.out.persistence.GrandPrixJPAEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventPersistenceAdapter implements EventRepositoryPort {

    private final SpringDataEventRepository springDataEventRepository;
    private final SpringDataChampionshipGrandPrixRepository springDataChampionshipGrandPrixRepository;
    private final EntityManager em;

    public EventPersistenceAdapter(SpringDataEventRepository springDataEventRepository,
                                   SpringDataChampionshipGrandPrixRepository springDataChampionshipGrandPrixRepository,
                                   EntityManager em) {
        this.springDataEventRepository = springDataEventRepository;
        this.springDataChampionshipGrandPrixRepository = springDataChampionshipGrandPrixRepository;
        this.em = em;
    }

    @Override
    public EventCommand getEventByEventId(int eventId) {
        return springDataEventRepository.findById(eventId).stream().map(this::toCommand).findFirst().orElse(null);
    }

    @Override
    public Event getEventById(int eventId) {
        Optional<EventJPAEntity> eventJPA = springDataEventRepository.findById(eventId);
        if(eventJPA.isEmpty()){
            throw new EventNotFoundException(eventId);
        }
        return toDomain(eventJPA.get());
    }

    @Override
    public List<EventView> getEventsByChampionship(int championshipId) {
        List<ChampionshipGrandPrixJPAEntity> championshipGPs = springDataChampionshipGrandPrixRepository.findByChampionshipId(championshipId);
        List<Integer> ids = new ArrayList<>();
        for(ChampionshipGrandPrixJPAEntity gp : championshipGPs){
            ids.add(gp.getId());
        }
        List<EventJPAEntity> events = springDataEventRepository.findByChampionshipGrandPrixIdIn(ids);
        List<EventView> eventViews = new ArrayList<>();
        for(EventJPAEntity event : events){
            GrandPrixJPAEntity gp = em.find(GrandPrixJPAEntity.class, event.getChampionshipGrandPrix().getId());
            eventViews.add(toView(event, gp.getName()));
        }
        return eventViews;
    }

    @Override
    public boolean existsChampionshipGrandPrixEventById(int championshipGrandPrixEventId) {
        return springDataEventRepository.existsById(championshipGrandPrixEventId);
    }

    @Override
    public boolean existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(int championshipGrandPrixId,
                                                                                        EventType eventType) {
        return springDataEventRepository.existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(
                championshipGrandPrixId,
                toEntityEventType(eventType)
        );
    }

    @Override
    public void createChampionshipGrandPrixEvents(List<EventCommand> events) {
        List<EventJPAEntity> entities = events.stream()
                .map(this::toEntity)
                .toList();
        springDataEventRepository.saveAll(entities);
    }

    @Override
    public void updateEventStatus(EventCommand eventCommand) {
        EventJPAEntity championshipEvent = toEntity(eventCommand);
        springDataEventRepository.save(championshipEvent);
    }

    private EventJPAEntity toEntity(EventCommand eventCommand) {
        EventJPAEntity entity = new EventJPAEntity();
        entity.setId(0);
        entity.setChampionshipGrandPrix(em.getReference(ChampionshipGrandPrixJPAEntity.class, eventCommand.championshipGrandPrixId()));
        entity.setEventType(toEntityEventType(eventCommand.eventType()));
        entity.setStartDate(eventCommand.startDate());
        return entity;
    }

    private EventJPAEntity.EventType toEntityEventType(EventType eventType) {
        return EventJPAEntity.EventType.valueOf(eventType.name());
    }


    private EventType toEventTypeDomain(EventJPAEntity.EventType eventType) {
        return EventType.valueOf(eventType.name());
    }

    private EventStatus toEventStatusDomain(EventJPAEntity.EventStatus eventStatus) {
        return EventStatus.valueOf(eventStatus.name());
    }

    private EventCommand toCommand(EventJPAEntity entity) {
        return new EventCommand(
                entity.getId(),
                entity.getChampionshipGrandPrix().getId(),
                toEventTypeDomain(entity.getEventType()),
                entity.getStartDate(),
                toEventStatusDomain(entity.getEventStatus())
        );
    }

    private Event toDomain(EventJPAEntity entity) {
        return new Event(
                entity.getId(),
                entity.getChampionshipGrandPrix().getId(),
                toEventTypeDomain(entity.getEventType()),
                entity.getStartDate(),
                toEventStatusDomain(entity.getEventStatus())
        );
    }

    private EventView toView(EventJPAEntity entity, String grandPrixName) {
        return new EventView(
                entity.getId(),
                toEventTypeDomain(entity.getEventType()),
                entity.getStartDate(),
                entity.getChampionshipGrandPrix().getId(),
                grandPrixName
        );
    }
}
