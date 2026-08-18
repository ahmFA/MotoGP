package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipRiderCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipRiderView;

final class ChampionshipRiderMapper {

    private ChampionshipRiderMapper() {
    }

    static CreateChampionshipRiderCommand toCommand(CreateChampionshipRiderRequest championshipRiderRequest, int championshipId) {
        return new CreateChampionshipRiderCommand(
                championshipRiderRequest.riderId(),
                championshipRiderRequest.championshipTeamId(),
                championshipId,
                championshipRiderRequest.number()
        );
    }

    static ChampionshipRiderResponse toResponse(ChampionshipRiderView championshipRider) {
        return new ChampionshipRiderResponse(
                championshipRider.id(),
                championshipRider.riderId(),
                championshipRider.riderName(),
                championshipRider.teamId(),
                championshipRider.teamName(),
                championshipRider.number()
        );
    }
}
