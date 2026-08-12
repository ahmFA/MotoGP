package api.ahm.motogp.shared.constructor.infrastructure.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataConstructorRepository extends JpaRepository<ConstructorJPAEntity, Integer> {
}
