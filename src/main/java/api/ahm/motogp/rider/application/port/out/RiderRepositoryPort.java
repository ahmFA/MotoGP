package api.ahm.motogp.rider.application.port.out;

import api.ahm.motogp.rider.application.port.in.CreateRiderCommand;
import api.ahm.motogp.rider.application.port.in.FullUpdateRiderCommand;
import api.ahm.motogp.rider.domain.model.Rider;

import java.util.List;
import java.util.Optional;

public interface RiderRepositoryPort {
    List<Rider> getRiders();
    List<Rider> getActiveRiders();
    Optional<Rider> getRider(int id);
    Boolean existsRiderById(int id);
    Boolean existsRiderByName(String name);
    Boolean existsRiderByNumber(Integer number);
    Rider createRider(CreateRiderCommand riderCommand);
    Rider fullUpdateRider(FullUpdateRiderCommand riderCommand);
    Boolean existsAnotherRiderByName(Integer myId,String name);
    Boolean existsAnotherRiderByNumber(Integer myId, Integer number);
    Rider deleteRider(Rider rider);
    Boolean isActiveRider(int riderId);
}
