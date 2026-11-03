package api.ahm.motogp.identity.infrastructure.adapter.in.rest;

import api.ahm.motogp.identity.application.port.in.LoginCommand;
import api.ahm.motogp.identity.application.port.out.AccessToken;

final class AuthMapper {

    private AuthMapper() {
    }

    static LoginCommand toCommand(LoginRequest loginRequest) {
        return new LoginCommand(loginRequest.username(), loginRequest.password());
    }

    static LoginResponse toResponse(AccessToken accessToken) {
        return new LoginResponse(accessToken.value(), "Bearer", accessToken.expiresInSeconds());
    }
}
