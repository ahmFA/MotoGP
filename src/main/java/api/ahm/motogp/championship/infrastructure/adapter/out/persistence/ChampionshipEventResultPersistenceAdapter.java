package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipEventResultCommand;
import api.ahm.motogp.championship.application.port.in.command.EventResultCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipEventResultRepositoryPort;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChampionshipEventResultPersistenceAdapter implements ChampionshipEventResultRepositoryPort {

    private final SpringDataChampionshipEventResultRepository springDataChampionshipGrandPrixEventResultRepository;
    private final EntityManager em;

    public ChampionshipEventResultPersistenceAdapter(SpringDataChampionshipEventResultRepository springDataChampionshipGrandPrixEventResultRepository,
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
    public void createChampionshipGrandPrixEventResults(CreateChampionshipEventResultCommand resultsCommand) {
        List<ChampionshipEventResultJPAEntity> entities = resultsCommand.results()
                .stream()
                .map(result -> toEntity(resultsCommand.championshipEventId(), result))
                .toList();
        springDataChampionshipGrandPrixEventResultRepository.saveAll(entities);
    }

    private ChampionshipEventResultJPAEntity toEntity(int championshipEventId, EventResultCommand resultCommand) {
        ChampionshipEventResultJPAEntity entity = new ChampionshipEventResultJPAEntity();
        entity.setId(0);
        entity.setChampionshipEvent(em.getReference(ChampionshipEventJPAEntity.class, championshipEventId));
        entity.setChampionshipRider(em.getReference(ChampionshipRiderJPAEntity.class, resultCommand.championshipRiderId()));
        entity.setPosition(resultCommand.position());
        entity.setPoints(resultCommand.points());
        return entity;
    }
}
