package api.ahm.motogp.grandprix.infrastructure.adapter.out;

import api.ahm.motogp.grandprix.infrastructure.adapter.out.persistence.GrandPrixJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataGrandPrixRepository extends JpaRepository<GrandPrixJPAEntity, Integer> {
    Boolean existsGrandPrixByName(String name);
    Boolean existsGrandPrixByIdNotAndName(Integer id, String name);
}
