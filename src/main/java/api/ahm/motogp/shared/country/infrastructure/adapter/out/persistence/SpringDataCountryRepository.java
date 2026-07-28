package api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence.CountryJPAEntity;

public interface SpringDataCountryRepository extends JpaRepository<CountryJPAEntity, Integer> {

}
