package api.ahm.motogp.grandprix.application.service;

import api.ahm.motogp.grandprix.application.exception.GrandPrixNameAlreadyExistsException;
import api.ahm.motogp.grandprix.application.exception.GrandPrixNotFoundException;
import api.ahm.motogp.grandprix.application.port.in.FullUpdateGrandPrixCommand;
import api.ahm.motogp.grandprix.application.port.in.FullUpdateGrandPrixUseCase;
import api.ahm.motogp.grandprix.application.port.out.GrandPrixRepositoryPort;
import api.ahm.motogp.grandprix.domain.model.GrandPrix;
import org.springframework.stereotype.Service;

@Service
public class FullUpdateGrandPrixService implements FullUpdateGrandPrixUseCase {

    private final GrandPrixRepositoryPort grandPrixRepositoryPort;

    public FullUpdateGrandPrixService(GrandPrixRepositoryPort grandPrixRepositoryPort) {
        this.grandPrixRepositoryPort = grandPrixRepositoryPort;
    }

    @Override
    public GrandPrix fullUpdateGrandPrix(FullUpdateGrandPrixCommand grandPrixCommand) {
        if (!grandPrixRepositoryPort.existsGrandPrixById(grandPrixCommand.id())) {
            throw new GrandPrixNotFoundException(grandPrixCommand.id());
        }
        if (grandPrixRepositoryPort.existsAnotherGrandPrixByName(grandPrixCommand.id(), grandPrixCommand.name())) {
            throw new GrandPrixNameAlreadyExistsException(grandPrixCommand.name());
        }
        return grandPrixRepositoryPort.fullUpdateGrandPrix(grandPrixCommand);
    }
}
