package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SpringDataEventRepository extends JpaRepository<EventJPAEntity, Integer> {
    boolean existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(int championshipGrandPrixId,
                                                                                  EventJPAEntity.EventType eventType);

    List<EventJPAEntity> findByChampionshipGrandPrixIdIn(Collection<Integer> championshipGrandPrixId);

}
