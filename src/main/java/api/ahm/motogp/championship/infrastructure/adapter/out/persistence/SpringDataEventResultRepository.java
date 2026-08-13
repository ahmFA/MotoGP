package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEventResultRepository extends JpaRepository<EventResultJPAEntity, Integer> {
    boolean existsByChampionshipEventIdAndChampionshipRiderId(int championshipEventId, int championshipRiderId);
}
