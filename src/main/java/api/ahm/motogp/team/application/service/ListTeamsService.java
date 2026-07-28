package api.ahm.motogp.team.application.service;

import api.ahm.motogp.team.application.exception.TeamNotFoundException;
import api.ahm.motogp.team.application.port.in.ListTeamUseCase;
import api.ahm.motogp.team.application.port.out.TeamRepositoryPort;
import api.ahm.motogp.team.domain.model.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ListTeamsService implements ListTeamUseCase {

    private final TeamRepositoryPort teamRepositoryPort;

    public ListTeamsService(TeamRepositoryPort teamRepositoryPort) {
        this.teamRepositoryPort = teamRepositoryPort;
    }

    @Override
    public List<Team> getTeams() {
        return teamRepositoryPort.getActiveTeams();
    }

    @Override
    public Optional<Team> getTeam(int id) {
        Optional<Team> team = teamRepositoryPort.getTeam(id);
        if (team.isEmpty()) {
            throw new TeamNotFoundException(id);
        }
        return team;
    }
}
