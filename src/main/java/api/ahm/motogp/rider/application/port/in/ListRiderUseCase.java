package api.ahm.motogp.rider.application.port.in;

import api.ahm.motogp.rider.domain.model.Rider;

import java.util.List;
import java.util.Optional;

public interface ListRiderUseCase {
    List<Rider> getRiders();
    Optional<Rider> getRider(int id);
}
