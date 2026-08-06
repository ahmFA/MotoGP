package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SpringDataChampionshipEventRepository extends JpaRepository<ChampionshipEventJPAEntity, Integer> {
    boolean existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(int championshipGrandPrixId,
                                                                                  ChampionshipEventJPAEntity.EventType eventType);

    List<ChampionshipEventJPAEntity> findByChampionshipGrandPrixIdIn(Collection<Integer> championshipGrandPrixId);

}
