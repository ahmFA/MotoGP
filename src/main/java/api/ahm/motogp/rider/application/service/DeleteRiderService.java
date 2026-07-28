package api.ahm.motogp.rider.application.service;

import api.ahm.motogp.rider.application.exception.RiderNotFoundException;
import api.ahm.motogp.rider.application.port.in.DeleteRiderUseCase;
import api.ahm.motogp.rider.application.port.out.RiderRepositoryPort;
import api.ahm.motogp.rider.domain.model.Rider;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteRiderService implements DeleteRiderUseCase {

    private final RiderRepositoryPort riderRepositoryPort;

    public DeleteRiderService(RiderRepositoryPort riderRepositoryPort) {
        this.riderRepositoryPort = riderRepositoryPort;
    }

    @Override
    public Rider deleteRider(int id) {
        Optional<Rider> rider = riderRepositoryPort.getRider(id);
        if(rider.isEmpty()){
            throw new RiderNotFoundException(id);
        }
        return riderRepositoryPort.deleteRider(rider.get());
    }
}
