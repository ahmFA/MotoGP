package api.ahm.motogp.identity.application.service;

import api.ahm.motogp.identity.application.exception.InvalidCredentialsException;
import api.ahm.motogp.identity.application.port.in.LoginCommand;
import api.ahm.motogp.identity.application.port.in.LoginUseCase;
import api.ahm.motogp.identity.application.port.out.AccessToken;
import api.ahm.motogp.identity.application.port.out.PasswordHasherPort;
import api.ahm.motogp.identity.application.port.out.TokenGeneratorPort;
import api.ahm.motogp.identity.application.port.out.UserRepositoryPort;
import api.ahm.motogp.identity.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordHasherPort passwordHasherPort;
    private final TokenGeneratorPort tokenGeneratorPort;

    public LoginService(UserRepositoryPort userRepositoryPort,
                        PasswordHasherPort passwordHasherPort,
                        TokenGeneratorPort tokenGeneratorPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordHasherPort = passwordHasherPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
    }

    @Override
    public AccessToken login(LoginCommand loginCommand) {
        User user = userRepositoryPort.getUserByUsername(loginCommand.username())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordHasherPort.matches(loginCommand.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return tokenGeneratorPort.generate(user);
    }
}
