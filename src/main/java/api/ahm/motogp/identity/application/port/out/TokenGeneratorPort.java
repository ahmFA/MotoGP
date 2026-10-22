package api.ahm.motogp.identity.application.port.out;

import api.ahm.motogp.identity.domain.model.User;

public interface TokenGeneratorPort {
    AccessToken generate(User user);
}
