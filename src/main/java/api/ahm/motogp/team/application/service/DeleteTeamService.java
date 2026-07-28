package api.ahm.motogp.team.application.service;

import api.ahm.motogp.team.application.exception.TeamNotFoundException;
import api.ahm.motogp.team.application.port.in.DeleteTeamUseCase;
import api.ahm.motogp.team.application.port.out.TeamRepositoryPort;
import api.ahm.motogp.team.domain.model.Team;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteTeamService implements DeleteTeamUseCase {

    private final TeamRepositoryPort teamRepositoryPort;

    public DeleteTeamService(TeamRepositoryPort teamRepositoryPort) {
        this.teamRepositoryPort = teamRepositoryPort;
    }

    @Override
    public Team deleteTeam(int id) {
        Optional<Team> team = teamRepositoryPort.getTeam(id);
        if (team.isEmpty()) {
            throw new TeamNotFoundException(id);
        }
        return teamRepositoryPort.deleteTeam(team.get());
    }
}
