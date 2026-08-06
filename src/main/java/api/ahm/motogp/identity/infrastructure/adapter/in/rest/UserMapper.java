package api.ahm.motogp.identity.infrastructure.adapter.in.rest;

import api.ahm.motogp.identity.application.port.in.CreateUserCommand;
import api.ahm.motogp.identity.domain.model.User;

final class UserMapper {

    private UserMapper() {
    }

    static CreateUserCommand toCommand(CreateUserRequest userRequest) {
        return new CreateUserCommand(
                userRequest.username(),
                userRequest.email(),
                userRequest.password(),
                userRequest.role()
        );
    }

    static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername().username(),
                user.getEmail().email(),
                user.getRole().name()
        );
    }
}
