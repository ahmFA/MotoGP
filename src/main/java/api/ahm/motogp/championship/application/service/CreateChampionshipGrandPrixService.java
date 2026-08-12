package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipGrandPrixAlreadyExistsException;
import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipGrandPrixCommand;
import api.ahm.motogp.championship.application.port.in.CreateChampionshipGrandPrixUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipGrandPrixRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;
import api.ahm.motogp.grandprix.application.exception.GrandPrixNotFoundException;
import api.ahm.motogp.grandprix.application.port.out.GrandPrixRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateChampionshipGrandPrixService implements CreateChampionshipGrandPrixUseCase {

    private final ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;
    private final GrandPrixRepositoryPort grandPrixRepositoryPort;

    public CreateChampionshipGrandPrixService(ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort,
                                              ChampionshipRepositoryPort championshipRepositoryPort,
                                              GrandPrixRepositoryPort grandPrixRepositoryPort) {
        this.championshipGrandPrixRepositoryPort = championshipGrandPrixRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
        this.grandPrixRepositoryPort = grandPrixRepositoryPort;
    }

    @Override
    public ChampionshipGrandPrixView addChampionshipGrandPrix(CreateChampionshipGrandPrixCommand createCommand) {
        if (!championshipRepositoryPort.existsChampionshipById(createCommand.championshipId())) {
            throw new ChampionshipNotFoundException(createCommand.championshipId());
        }
        if (!grandPrixRepositoryPort.existsGrandPrixById(createCommand.grandPrixId())) {
            throw new GrandPrixNotFoundException(createCommand.grandPrixId());
        }
        if (championshipGrandPrixRepositoryPort.existsChampionshipGrandPrixByChampionshipIdAndGrandPrixId(
                createCommand.championshipId(),
                createCommand.grandPrixId())) {
            throw new ChampionshipGrandPrixAlreadyExistsException(createCommand.championshipId(), createCommand.grandPrixId());
        }
        return championshipGrandPrixRepositoryPort.createChampionshipGrandPrix(createCommand);
    }
}
