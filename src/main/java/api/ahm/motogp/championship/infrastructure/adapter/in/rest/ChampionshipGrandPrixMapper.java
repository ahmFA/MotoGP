package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipGrandPrixCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;

final class ChampionshipGrandPrixMapper {

    private ChampionshipGrandPrixMapper() {
    }

    static CreateChampionshipGrandPrixCommand toCommand(CreateChampionshipGrandPrixRequest request, int championshipId) {
        return new CreateChampionshipGrandPrixCommand(
                request.grandPrixId(),
                championshipId,
                request.date(),
                request.roundNumber()
        );
    }

    static ChampionshipGrandPrixResponse toResponse(ChampionshipGrandPrixView championshipGrandPrix) {
        return new ChampionshipGrandPrixResponse(
                championshipGrandPrix.id(),
                championshipGrandPrix.grandPrixId(),
                championshipGrandPrix.grandPrixName(),
                championshipGrandPrix.circuitName(),
                championshipGrandPrix.date(),
                championshipGrandPrix.roundNumber()
        );
    }
}
