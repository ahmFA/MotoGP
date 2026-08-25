package api.ahm.motogp.league.application.port.in.command;

import api.ahm.motogp.identity.domain.model.valueobjects.UserId;
import api.ahm.motogp.shared.league.aop.OfficialLeague;

public record CreateUserLeagueCommand(
        long leagueId,
        UserId userId
) {
}
