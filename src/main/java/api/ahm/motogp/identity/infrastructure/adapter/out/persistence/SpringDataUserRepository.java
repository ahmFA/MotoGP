package api.ahm.motogp.identity.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserJPAEntity, Integer> {
    Optional<UserJPAEntity> findByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    boolean existsById(Long id);
}
