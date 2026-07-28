package api.ahm.motogp.team.application.port.in;

import api.ahm.motogp.team.domain.model.Team;

public interface CreateTeamUseCase {
    Team createTeam(CreateTeamCommand teamCommand);
}
