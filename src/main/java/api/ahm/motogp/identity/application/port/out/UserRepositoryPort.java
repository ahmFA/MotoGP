package api.ahm.motogp.identity.application.port.out;

import api.ahm.motogp.identity.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    List<User> getUsers();
    Optional<User> getUser(int id);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    User createUser(User user);
}
