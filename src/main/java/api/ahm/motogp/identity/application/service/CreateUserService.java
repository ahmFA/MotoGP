package api.ahm.motogp.identity.application.service;

import api.ahm.motogp.identity.application.exception.UserEmailAlreadyExistsException;
import api.ahm.motogp.identity.application.exception.UsernameAlreadyExistsException;
import api.ahm.motogp.identity.application.port.in.CreateUserCommand;
import api.ahm.motogp.identity.application.port.in.CreateUserUseCase;
import api.ahm.motogp.identity.application.port.out.UserRepositoryPort;
import api.ahm.motogp.identity.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUser(CreateUserCommand userCommand) {
        User user = User.create(
                userCommand.username(),
                userCommand.email(),
                userCommand.password(),
                userCommand.role()
        );

        if (userRepositoryPort.existsUserByUsername(user.getUsername().username())) {
            throw new UsernameAlreadyExistsException(user.getUsername().username());
        }
        if (userRepositoryPort.existsUserByEmail(user.getEmail().email())) {
            throw new UserEmailAlreadyExistsException(user.getEmail().email());
        }

        return userRepositoryPort.createUser(user);
    }
}
