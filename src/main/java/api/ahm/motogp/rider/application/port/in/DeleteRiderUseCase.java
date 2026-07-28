package api.ahm.motogp.rider.application.port.in;

import api.ahm.motogp.rider.domain.model.Rider;

public interface DeleteRiderUseCase {
    Rider deleteRider(int id);
}
