package api.ahm.motogp.repositories;

import api.ahm.motogp.entities.ChampionshipEventResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChampionshipEventResultRepository extends JpaRepository<ChampionshipEventResult, Integer> {
    List<ChampionshipEventResult> getChampionshipEventResultByChampionshipEventId(int eventId);

    Optional<ChampionshipEventResult> getChampionshipEventResultByChampionshipEventIdAndChampionshipRiderId(int eventId, int riderId);
}
