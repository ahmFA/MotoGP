package api.ahm.motogp.repositories;

import api.ahm.motogp.entities.ChampionshipTeam;
import api.ahm.motogp.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChampionshipTeamRepository extends JpaRepository<ChampionshipTeam, Integer> {

    List<ChampionshipTeam> findByChampionshipId(Integer championshipId);

    Optional<ChampionshipTeam> findByChampionshipIdAndId(Integer championshipId, Integer id);
}
