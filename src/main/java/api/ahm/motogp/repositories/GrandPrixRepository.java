package api.ahm.motogp.repositories;

import api.ahm.motogp.entities.GrandPrix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrandPrixRepository extends JpaRepository<GrandPrix, Integer> {
}
