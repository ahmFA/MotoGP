package api.ahm.motogp.team.application.service;

import api.ahm.motogp.team.application.exception.TeamNameAlreadyExistsException;
import api.ahm.motogp.team.application.port.in.CreateTeamCommand;
import api.ahm.motogp.team.application.port.in.CreateTeamUseCase;
import api.ahm.motogp.team.application.port.out.TeamRepositoryPort;
import api.ahm.motogp.team.domain.model.Team;
import org.springframework.stereotype.Service;

@Service
public class CreateTeamService implements CreateTeamUseCase {

    private final TeamRepositoryPort teamRepositoryPort;

    public CreateTeamService(TeamRepositoryPort teamRepositoryPort) {
        this.teamRepositoryPort = teamRepositoryPort;
    }

    @Override
    public Team createTeam(CreateTeamCommand teamCommand) {
        if (teamRepositoryPort.existsTeamByName(teamCommand.name())) {
            throw new TeamNameAlreadyExistsException(teamCommand.name());
        }
        return teamRepositoryPort.createTeam(teamCommand);
    }
}
