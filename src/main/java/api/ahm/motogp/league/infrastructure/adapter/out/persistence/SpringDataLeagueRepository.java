package api.ahm.motogp.league.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLeagueRepository extends JpaRepository<LeagueJPAEntity, Long> {
}
