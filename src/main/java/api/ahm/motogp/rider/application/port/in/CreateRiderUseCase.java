package api.ahm.motogp.rider.application.port.in;

import api.ahm.motogp.rider.domain.model.Rider;

public interface CreateRiderUseCase {
    Rider createRider(CreateRiderCommand riderCommand);
}
