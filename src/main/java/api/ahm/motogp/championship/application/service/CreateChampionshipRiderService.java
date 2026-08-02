package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipRiderAlreadyExistsException;
import api.ahm.motogp.championship.application.exception.ChampionshipTeamNotFoundException;
import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipRiderCommand;
import api.ahm.motogp.championship.application.port.in.CreateChampionshipRiderUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipTeamRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipRiderView;
import api.ahm.motogp.rider.application.exception.RiderIsNotActiveException;
import api.ahm.motogp.rider.application.exception.RiderNotFoundException;
import api.ahm.motogp.rider.application.port.out.RiderRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateChampionshipRiderService implements CreateChampionshipRiderUseCase {

    private final ChampionshipRiderRepositoryPort championshipRiderRepositoryPort;
    private final ChampionshipTeamRepositoryPort championshipTeamRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;
    private final RiderRepositoryPort riderRepositoryPort;

    public CreateChampionshipRiderService(ChampionshipRiderRepositoryPort championshipRiderRepositoryPort,
                                          ChampionshipTeamRepositoryPort championshipTeamRepositoryPort,
                                          ChampionshipRepositoryPort championshipRepositoryPort,
                                          RiderRepositoryPort riderRepositoryPort) {
        this.championshipRiderRepositoryPort = championshipRiderRepositoryPort;
        this.championshipTeamRepositoryPort = championshipTeamRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
        this.riderRepositoryPort = riderRepositoryPort;
    }

    @Override
    public ChampionshipRiderView addChampionshipRider(CreateChampionshipRiderCommand createCommand) {
        if (!championshipRepositoryPort.existsChampionshipById(createCommand.championshipId())) {
            throw new ChampionshipNotFoundException(createCommand.championshipId());
        }
        if (!riderRepositoryPort.existsRiderById(createCommand.riderId())) {
            throw new RiderNotFoundException(createCommand.riderId());
        }
        if (!riderRepositoryPort.isActiveRider(createCommand.riderId())) {
            throw new RiderIsNotActiveException(createCommand.riderId());
        }
        if (!championshipTeamRepositoryPort.existsChampionshipTeamByChampionshipIdAndId(createCommand.championshipId(), createCommand.teamId())) {
            throw new ChampionshipTeamNotFoundException(createCommand.teamId());
        }
        if (championshipRiderRepositoryPort.existsChampionshipRiderByChampionshipIdAndRiderId(createCommand.championshipId(), createCommand.riderId())) {
            throw new ChampionshipRiderAlreadyExistsException(createCommand.championshipId(), createCommand.riderId());
        }
        return championshipRiderRepositoryPort.createChampionshipRider(createCommand);
    }
}
