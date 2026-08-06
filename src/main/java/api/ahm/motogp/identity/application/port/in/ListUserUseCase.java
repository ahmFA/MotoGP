package api.ahm.motogp.identity.application.port.in;

import api.ahm.motogp.identity.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface ListUserUseCase {
    List<User> getUsers();
    Optional<User> getUser(int id);
}
