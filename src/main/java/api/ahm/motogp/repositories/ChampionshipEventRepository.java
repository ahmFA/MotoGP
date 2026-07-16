package api.ahm.motogp.repositories;

import api.ahm.motogp.entities.ChampionshipEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipEventRepository extends JpaRepository<ChampionshipEvent, Integer> {
}
