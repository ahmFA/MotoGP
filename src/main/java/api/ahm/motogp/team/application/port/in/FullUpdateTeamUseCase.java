package api.ahm.motogp.team.application.port.in;

import api.ahm.motogp.team.domain.model.Team;

public interface FullUpdateTeamUseCase {
    Team fullUpdateTeam(FullUpdateTeamCommand teamCommand);
}
