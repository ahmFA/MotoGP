package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipTeamCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipTeamView;

final class ChampionshipTeamMapper {

    static CreateChampionshipTeamCommand toCommand(CreateChampionshipTeamRequest championshipTeamRequest) {
        return new CreateChampionshipTeamCommand(
                championshipTeamRequest.teamId(),
                championshipTeamRequest.constructorId(),
                championshipTeamRequest.championshipId(),
                championshipTeamRequest.name()
        );
    }

    static ChampionshipTeamResponse toResponse(ChampionshipTeamView championshipTeam) {
        return new ChampionshipTeamResponse(
                championshipTeam.id(),
                championshipTeam.teamId(),
                championshipTeam.name(),
                championshipTeam.constructorId(),
                championshipTeam.constructorName()
        );
    }
}
