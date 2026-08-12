package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipTeamAlreadyExistsException;
import api.ahm.motogp.championship.application.exception.ChampionshipTeamNameAlreadyExistsException;
import api.ahm.motogp.championship.application.exception.ConstructorNotFoundException;
import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipTeamCommand;
import api.ahm.motogp.championship.application.port.in.CreateChampionshipTeamUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipTeamRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ConstructorRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipTeamView;
import api.ahm.motogp.team.application.exception.TeamIsNotActiveException;
import api.ahm.motogp.team.application.exception.TeamNotFoundException;
import api.ahm.motogp.team.application.port.out.TeamRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateChampionshipTeamService implements CreateChampionshipTeamUseCase {

    private final ChampionshipTeamRepositoryPort championshipTeamRepositoryPort;
    private final TeamRepositoryPort teamRepositoryPort;
    private final ConstructorRepositoryPort constructorRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public CreateChampionshipTeamService(ChampionshipTeamRepositoryPort championshipTeamRepositoryPort,
                                         TeamRepositoryPort teamRepositoryPort,
                                         ConstructorRepositoryPort constructorRepositoryPort,
                                         ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipTeamRepositoryPort = championshipTeamRepositoryPort;
        this.teamRepositoryPort = teamRepositoryPort;
        this.constructorRepositoryPort = constructorRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public ChampionshipTeamView addChampionshipTeam(CreateChampionshipTeamCommand createCommand) {
        if(championshipTeamRepositoryPort.existsChampionshipTeamByChampionshipIdAndTeamId(createCommand.championshipId(), createCommand.teamId())){
            throw new ChampionshipTeamAlreadyExistsException(createCommand.championshipId(), createCommand.teamId());
        }
        if(championshipTeamRepositoryPort.existsChampionshipTeamByChampionshipIdAndName(createCommand.championshipId(), createCommand.name())){
            throw new ChampionshipTeamNameAlreadyExistsException(createCommand.name());
        }
        if(!teamRepositoryPort.existsTeamById(createCommand.teamId())){
            throw new TeamNotFoundException(createCommand.teamId());
        }
        if(!teamRepositoryPort.isActiveTeam(createCommand.teamId())){
            throw new TeamIsNotActiveException(createCommand.teamId());
        }
        if(!championshipRepositoryPort.existsChampionshipById(createCommand.championshipId())){
            throw new ChampionshipNotFoundException(createCommand.championshipId());
        }
        if(!constructorRepositoryPort.existsConstructorById(createCommand.constructorId())){
            throw new ConstructorNotFoundException(createCommand.constructorId());
        }
        return championshipTeamRepositoryPort.createChampionshipTeam(createCommand);
    }
}
