package api.ahm.motogp.identity.application.service;

import api.ahm.motogp.identity.application.port.in.ListUserUseCase;
import api.ahm.motogp.identity.application.port.out.UserRepositoryPort;
import api.ahm.motogp.identity.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListUserService implements ListUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public ListUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public List<User> getUsers() {
        return userRepositoryPort.getUsers();
    }

    @Override
    public Optional<User> getUser(int id) {
        return userRepositoryPort.getUser(id);
    }
}
