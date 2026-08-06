package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipEventRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipGranPrixEventView;
import api.ahm.motogp.grandprix.infrastructure.adapter.out.persistence.GrandPrixJPAEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChampionshipEventPersistenceAdapter implements ChampionshipEventRepositoryPort {

    private final SpringDataChampionshipEventRepository springDataChampionshipEventRepository;
    private final SpringDataChampionshipGrandPrixRepository springDataChampionshipGrandPrixRepository;
    private final EntityManager em;

    public ChampionshipEventPersistenceAdapter(SpringDataChampionshipEventRepository springDataChampionshipEventRepository,
                                               SpringDataChampionshipGrandPrixRepository springDataChampionshipGrandPrixRepository,
                                               EntityManager em) {
        this.springDataChampionshipEventRepository = springDataChampionshipEventRepository;
        this.springDataChampionshipGrandPrixRepository = springDataChampionshipGrandPrixRepository;
        this.em = em;
    }

    @Override
    public EventCommand getEventByEventId(int eventId) {
        return springDataChampionshipEventRepository.findById(eventId).stream().map(this::toCommand).findFirst().orElse(null);
    }

    @Override
    public List<ChampionshipGranPrixEventView> getEventsByChampionship(int championshipId) {
        List<ChampionshipGrandPrixJPAEntity> championshipGPs = springDataChampionshipGrandPrixRepository.findByChampionshipId(championshipId);
        List<Integer> ids = new ArrayList<>();
        for(ChampionshipGrandPrixJPAEntity gp : championshipGPs){
            ids.add(gp.getId());
        }
        List<ChampionshipEventJPAEntity> events = springDataChampionshipEventRepository.findByChampionshipGrandPrixIdIn(ids);
        List<ChampionshipGranPrixEventView> eventViews = new ArrayList<>();
        for(ChampionshipEventJPAEntity event : events){
            GrandPrixJPAEntity gp = em.find(GrandPrixJPAEntity.class, event.getChampionshipGrandPrix().getId());
            eventViews.add(toView(event, gp.getName()));
        }
        return eventViews;
    }

    @Override
    public boolean existsChampionshipGrandPrixEventById(int championshipGrandPrixEventId) {
        return springDataChampionshipEventRepository.existsById(championshipGrandPrixEventId);
    }

    @Override
    public boolean existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(int championshipGrandPrixId,
                                                                                        EventCommand.EventType eventType) {
        return springDataChampionshipEventRepository.existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(
                championshipGrandPrixId,
                toEntityEventType(eventType)
        );
    }

    @Override
    public void createChampionshipGrandPrixEvents(List<EventCommand> events) {
        List<ChampionshipEventJPAEntity> entities = events.stream()
                .map(this::toEntity)
                .toList();
        springDataChampionshipEventRepository.saveAll(entities);
    }

    private ChampionshipEventJPAEntity toEntity(EventCommand eventCommand) {
        ChampionshipEventJPAEntity entity = new ChampionshipEventJPAEntity();
        entity.setId(0);
        entity.setChampionshipGrandPrix(em.getReference(ChampionshipGrandPrixJPAEntity.class, eventCommand.championshipGrandPrixId()));
        entity.setEventType(toEntityEventType(eventCommand.eventType()));
        entity.setStartDate(eventCommand.startDate());
        return entity;
    }

    private ChampionshipEventJPAEntity.EventType toEntityEventType(EventCommand.EventType eventType) {
        return ChampionshipEventJPAEntity.EventType.valueOf(eventType.name());
    }

    private EventCommand.EventType toCommandEventType(ChampionshipEventJPAEntity.EventType eventType) {
        return EventCommand.EventType.valueOf(eventType.name());
    }

    private ChampionshipGranPrixEventView.EventType toViewEventType(ChampionshipEventJPAEntity.EventType eventType) {
        return ChampionshipGranPrixEventView.EventType.valueOf(eventType.name());
    }

    private EventCommand toCommand(ChampionshipEventJPAEntity entity) {
        return new EventCommand(
                entity.getId(),
                entity.getChampionshipGrandPrix().getId(),
                toCommandEventType(entity.getEventType()),
                entity.getStartDate()
        );
    }

    private ChampionshipGranPrixEventView toView(ChampionshipEventJPAEntity entity, String grandPrixName) {
        return new ChampionshipGranPrixEventView(
                entity.getId(),
                toViewEventType(entity.getEventType()),
                entity.getStartDate(),
                entity.getChampionshipGrandPrix().getId(),
                grandPrixName
        );
    }
}
