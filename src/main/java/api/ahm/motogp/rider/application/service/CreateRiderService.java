package api.ahm.motogp.rider.application.service;

import api.ahm.motogp.rider.application.exception.RiderNameAlreadyExistsException;
import api.ahm.motogp.rider.application.exception.RiderNumberAlreadyExistException;
import api.ahm.motogp.rider.application.port.in.CreateRiderCommand;
import api.ahm.motogp.rider.application.port.in.CreateRiderUseCase;
import api.ahm.motogp.rider.application.port.out.RiderRepositoryPort;
import api.ahm.motogp.rider.domain.model.Rider;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateRiderService implements CreateRiderUseCase {

    private final RiderRepositoryPort riderRepositoryPort;

    public CreateRiderService(RiderRepositoryPort riderRepositoryPort) {
        this.riderRepositoryPort = riderRepositoryPort;
    }

    public Rider createRider(CreateRiderCommand riderCommand){
        if(riderRepositoryPort.existsRiderByName(riderCommand.name())){
            throw new RiderNameAlreadyExistsException(riderCommand.name());
        }
        if(riderRepositoryPort.existsRiderByNumber(riderCommand.number())){
            throw new RiderNumberAlreadyExistException(riderCommand.number());
        }
        return riderRepositoryPort.createRider(riderCommand);
    }
}
