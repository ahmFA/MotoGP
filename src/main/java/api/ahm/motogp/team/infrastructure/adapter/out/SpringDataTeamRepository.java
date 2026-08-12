package api.ahm.motogp.team.infrastructure.adapter.out;

import api.ahm.motogp.team.infrastructure.adapter.out.persistence.TeamJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataTeamRepository extends JpaRepository<TeamJPAEntity, Integer> {
    List<TeamJPAEntity> findByActiveTrue();
    Boolean existsTeamByName(String name);
    Boolean existsTeamByIdNotAndName(Integer id, String name);
    Boolean existsTeamByIdAndActiveTrue(Integer id);
}
