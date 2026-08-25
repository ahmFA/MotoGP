package api.ahm.motogp.identity.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<UserJPAEntity, Integer> {
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    boolean existsById(Long id);
}
