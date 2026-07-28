package api.ahm.motogp.rider.application.service;

import api.ahm.motogp.rider.application.exception.RiderNameAlreadyExistsException;
import api.ahm.motogp.rider.application.exception.RiderNotFoundException;
import api.ahm.motogp.rider.application.exception.RiderNumberAlreadyExistException;
import api.ahm.motogp.rider.application.port.in.FullUpdateRiderCommand;
import api.ahm.motogp.rider.application.port.in.FullUpdateRiderUseCase;
import api.ahm.motogp.rider.application.port.out.RiderRepositoryPort;
import api.ahm.motogp.rider.domain.model.Rider;
import org.springframework.stereotype.Service;

@Service
public class FullUpdateRiderService implements FullUpdateRiderUseCase {

    private final RiderRepositoryPort riderRepositoryPort;

    public FullUpdateRiderService(RiderRepositoryPort riderRepositoryPort) {
        this.riderRepositoryPort = riderRepositoryPort;
    }

    @Override
    public Rider fullUpdateRider(FullUpdateRiderCommand riderCommand){
        if(!riderRepositoryPort.existsRiderById(riderCommand.id())){
            throw new RiderNotFoundException(riderCommand.id());
        }
        if(riderRepositoryPort.existsAnotherRiderByName(riderCommand.id(), riderCommand.name())){
            throw new RiderNameAlreadyExistsException(riderCommand.name());
        }
        if(riderRepositoryPort.existsAnotherRiderByNumber(riderCommand.id(), riderCommand.number())){
            throw new RiderNumberAlreadyExistException(riderCommand.number());
        }
        return riderRepositoryPort.fullUpdateRider(riderCommand);
    }
}
