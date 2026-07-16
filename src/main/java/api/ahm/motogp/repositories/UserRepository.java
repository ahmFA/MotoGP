package api.ahm.motogp.repositories;

import api.ahm.motogp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
