package api.ahm.motogp.repositories;

import api.ahm.motogp.entities.ChampionshipRider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChampionshipRiderRepository extends JpaRepository<ChampionshipRider, Integer> {

    List<ChampionshipRider> findByChampionshipId(Integer championshipId);

    Optional<ChampionshipRider> findByChampionshipIdAndId(Integer championshipId, Integer id);
}
