package api.ahm.motogp.team.application.service;

import api.ahm.motogp.team.application.exception.TeamNameAlreadyExistsException;
import api.ahm.motogp.team.application.exception.TeamNotFoundException;
import api.ahm.motogp.team.application.port.in.FullUpdateTeamCommand;
import api.ahm.motogp.team.application.port.in.FullUpdateTeamUseCase;
import api.ahm.motogp.team.application.port.out.TeamRepositoryPort;
import api.ahm.motogp.team.domain.model.Team;
import org.springframework.stereotype.Service;

@Service
public class FullUpdateTeamService implements FullUpdateTeamUseCase {

    private final TeamRepositoryPort teamRepositoryPort;

    public FullUpdateTeamService(TeamRepositoryPort teamRepositoryPort) {
        this.teamRepositoryPort = teamRepositoryPort;
    }

    @Override
    public Team fullUpdateTeam(FullUpdateTeamCommand teamCommand) {
        if (!teamRepositoryPort.existsTeamById(teamCommand.id())) {
            throw new TeamNotFoundException(teamCommand.id());
        }
        if (teamRepositoryPort.existsAnotherTeamByName(teamCommand.id(), teamCommand.name())) {
            throw new TeamNameAlreadyExistsException(teamCommand.name());
        }
        return teamRepositoryPort.fullUpdateTeam(teamCommand);
    }
}
