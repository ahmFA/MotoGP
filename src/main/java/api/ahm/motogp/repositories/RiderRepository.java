package api.ahm.motogp.repositories;

import api.ahm.motogp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Integer> {
}