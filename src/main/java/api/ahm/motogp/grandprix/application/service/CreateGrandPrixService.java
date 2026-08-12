package api.ahm.motogp.grandprix.application.service;

import api.ahm.motogp.grandprix.application.exception.GrandPrixNameAlreadyExistsException;
import api.ahm.motogp.grandprix.application.port.in.CreateGrandPrixCommand;
import api.ahm.motogp.grandprix.application.port.in.CreateGrandPrixUseCase;
import api.ahm.motogp.grandprix.application.port.out.GrandPrixRepositoryPort;
import api.ahm.motogp.grandprix.domain.model.GrandPrix;
import org.springframework.stereotype.Service;

@Service
public class CreateGrandPrixService implements CreateGrandPrixUseCase {

    private final GrandPrixRepositoryPort grandPrixRepositoryPort;

    public CreateGrandPrixService(GrandPrixRepositoryPort grandPrixRepositoryPort) {
        this.grandPrixRepositoryPort = grandPrixRepositoryPort;
    }

    @Override
    public GrandPrix createGrandPrix(CreateGrandPrixCommand grandPrixCommand) {
        if (grandPrixRepositoryPort.existsGrandPrixByName(grandPrixCommand.name())) {
            throw new GrandPrixNameAlreadyExistsException(grandPrixCommand.name());
        }
        return grandPrixRepositoryPort.createGrandPrix(grandPrixCommand);
    }
}
