package api.ahm.motogp.rider.application.service;

import api.ahm.motogp.rider.application.exception.RiderNotFoundException;
import api.ahm.motogp.rider.application.port.in.ListRiderUseCase;
import api.ahm.motogp.rider.application.port.out.RiderRepositoryPort;
import api.ahm.motogp.rider.domain.model.Rider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ListRidersService implements ListRiderUseCase {

    private final RiderRepositoryPort riderRepositoryPort;

    public ListRidersService(RiderRepositoryPort riderRepositoryPort) {
        this.riderRepositoryPort = riderRepositoryPort;
    }

    @Override
    public List<Rider> getRiders() {
        return riderRepositoryPort.getActiveRiders();
    }

    @Override
    public Optional<Rider> getRider(int id) {
        Optional<Rider> rider = riderRepositoryPort.getRider(id);
        if(rider.isEmpty()){
            throw new RiderNotFoundException(id);
        }
        return rider;
    }
}
