package api.ahm.motogp.rider.application.port.in;

import api.ahm.motogp.rider.domain.model.Rider;

public interface FullUpdateRiderUseCase {
    Rider fullUpdateRider(FullUpdateRiderCommand riderCommand);
}
