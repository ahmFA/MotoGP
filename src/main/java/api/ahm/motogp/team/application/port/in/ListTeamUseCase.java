package api.ahm.motogp.team.application.port.in;

import api.ahm.motogp.team.domain.model.Team;

import java.util.List;
import java.util.Optional;

public interface ListTeamUseCase {
    List<Team> getTeams();
    Optional<Team> getTeam(int id);
}
