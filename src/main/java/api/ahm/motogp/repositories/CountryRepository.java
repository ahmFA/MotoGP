package api.ahm.motogp.repositories;

import api.ahm.motogp.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
