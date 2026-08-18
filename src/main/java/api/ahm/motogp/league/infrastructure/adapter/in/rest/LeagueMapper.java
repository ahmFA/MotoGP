package api.ahm.motogp.league.infrastructure.adapter.in.rest;

import api.ahm.motogp.league.application.port.in.command.CreateLeagueCommand;
import api.ahm.motogp.league.domain.model.League;

final class LeagueMapper {

    static CreateLeagueCommand toCommand(CreateLeagueRequest createLeagueRequest) {
        return new CreateLeagueCommand(
                createLeagueRequest.championshipId(),
                createLeagueRequest.name()
        );
    }

    static LeagueResponse toResponse(League league) {
        return new LeagueResponse(
                league.id(),
                league.championshipId(),
                league.name()
        );
    }
}
