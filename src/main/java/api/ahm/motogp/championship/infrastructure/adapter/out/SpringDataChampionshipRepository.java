package api.ahm.motogp.championship.infrastructure.adapter.out;

import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.ChampionshipJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataChampionshipRepository extends JpaRepository<ChampionshipJPAEntity, Integer> {
    Boolean existsByCategoryIdAndYear(Integer categoryId, Integer year);
}
