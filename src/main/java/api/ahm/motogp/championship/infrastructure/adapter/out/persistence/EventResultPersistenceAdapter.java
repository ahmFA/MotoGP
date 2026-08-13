package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.in.command.CreateEventResultCommand;
import api.ahm.motogp.championship.application.port.in.command.EventResultCommand;
import api.ahm.motogp.championship.application.port.out.EventResultRepositoryPort;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventResultPersistenceAdapter implements EventResultRepositoryPort {

    private final SpringDataEventResultRepository springDataChampionshipGrandPrixEventResultRepository;
    private final EntityManager em;

    public EventResultPersistenceAdapter(SpringDataEventResultRepository springDataChampionshipGrandPrixEventResultRepository,
                                         EntityManager em) {
        this.springDataChampionshipGrandPrixEventResultRepository = springDataChampionshipGrandPrixEventResultRepository;
        this.em = em;
    }

    @Override
    public boolean existsChampionshipGrandPrixEventResultByChampionshipEventIdAndChampionshipRiderId(int championshipEventId,
                                                                                                     int championshipRiderId) {
        return springDataChampionshipGrandPrixEventResultRepository.existsByChampionshipEventIdAndChampionshipRiderId(
                championshipEventId,
                championshipRiderId
        );
    }

    @Override
    public void createChampionshipGrandPrixEventResults(CreateEventResultCommand resultsCommand) {
        List<EventResultJPAEntity> entities = resultsCommand.results()
                .stream()
                .map(result -> toEntity(resultsCommand.championshipEventId(), result))
                .toList();
        springDataChampionshipGrandPrixEventResultRepository.saveAll(entities);
    }

    private EventResultJPAEntity toEntity(int championshipEventId, EventResultCommand resultCommand) {
        EventResultJPAEntity entity = new EventResultJPAEntity();
        entity.setId(0);
        entity.setChampionshipEvent(em.getReference(EventJPAEntity.class, championshipEventId));
        entity.setChampionshipRider(em.getReference(ChampionshipRiderJPAEntity.class, resultCommand.championshipRiderId()));
        entity.setPosition(resultCommand.position());
        entity.setPoints(resultCommand.points());
        return entity;
    }
}
