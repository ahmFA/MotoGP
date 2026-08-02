package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataChampionshipRepository extends JpaRepository<ChampionshipJPAEntity, Integer> {
    Boolean existsByCategoryIdAndYear(Integer categoryId, Integer year);
}
