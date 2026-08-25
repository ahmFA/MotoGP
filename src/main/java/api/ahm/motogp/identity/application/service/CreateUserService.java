package api.ahm.motogp.identity.application.service;

import api.ahm.motogp.identity.application.exception.UserEmailAlreadyExistsException;
import api.ahm.motogp.identity.application.exception.UsernameAlreadyExistsException;
import api.ahm.motogp.identity.application.port.in.CreateUserCommand;
import api.ahm.motogp.identity.application.port.in.CreateUserUseCase;
import api.ahm.motogp.identity.application.port.out.UserRepositoryPort;
import api.ahm.motogp.identity.domain.model.User;
import api.ahm.motogp.identity.domain.model.valueobjects.UserId;
import api.ahm.motogp.league.application.port.in.command.CreateUserLeagueCommand;
import api.ahm.motogp.league.application.port.out.UserLeagueRepositoryPort;
import api.ahm.motogp.league.application.port.query.UserLeagueView;
import api.ahm.motogp.league.infrastructure.adapter.out.persistence.UserLeaguePersistenceAdapter;
import api.ahm.motogp.shared.league.aop.OfficialLeague;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final UserLeagueRepositoryPort userLeagueRepositoryPort;

    public CreateUserService(UserRepositoryPort userRepositoryPort,
                             UserLeagueRepositoryPort userLeagueRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.userLeagueRepositoryPort = userLeagueRepositoryPort;
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

        User newUser = userRepositoryPort.createUser(user);

        CreateUserLeagueCommand createUserCommand = new CreateUserLeagueCommand(OfficialLeague.getOfficialLeagueId(), new UserId(newUser.getId()));
        userLeagueRepositoryPort.createUserLeague(createUserCommand);

        return newUser;
    }
}
