package api.ahm.motogp.identity.application.port.in;

import api.ahm.motogp.identity.domain.model.User;

public interface CreateUserUseCase {
    User createUser(CreateUserCommand userCommand);
}
