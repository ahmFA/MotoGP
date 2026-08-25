package api.ahm.motogp.league.domain.model;

import api.ahm.motogp.identity.domain.model.valueobjects.UserId;
import api.ahm.motogp.league.domain.model.valueobjects.UserLeagueId;

public record UserLeague(
        UserLeagueId id,
        UserId userId,
        Long leagueId
) {
    public UserLeague {
        if (leagueId <= 0) {
            throw new IllegalArgumentException("League ID must be positive");
        }
    }
}
