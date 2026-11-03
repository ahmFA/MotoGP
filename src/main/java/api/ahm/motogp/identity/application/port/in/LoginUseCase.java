package api.ahm.motogp.identity.application.port.in;

import api.ahm.motogp.identity.application.port.out.AccessToken;

public interface LoginUseCase {
    AccessToken login(LoginCommand loginCommand);
}
